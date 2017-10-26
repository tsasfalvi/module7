package st.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.dto.User;
import st.dto.UserRegistration;
import st.entity.UserEntity;
import st.repository.UserRepository;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static st.dto.Role.ROLE_USER;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(UserRegistration userRegistration) {
        if (userRepository.exists(userRegistration.getEmail())) {
            throw new IllegalStateException("There is an account with that email adress: " + userRegistration.getEmail());
        }

        UserEntity userEntity = modelMapper.map(userRegistration, UserEntity.class);

        userEntity.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        userEntity.setRole(ROLE_USER);

        return modelMapper.map(userRepository.save(userEntity), User.class);
    }

    @Transactional(propagation = REQUIRED)
    public User update(UserEntity userEntity) {
        return modelMapper.map(userRepository.save(userEntity), User.class);
    }

    @Transactional(propagation = REQUIRED)
    UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUser = authentication.getName();
            return userRepository.findOne(currentUser);
        }

        return null;
    }

    public Iterable<User> getAllUsers() {
        return modelMapper.map(userRepository.findAll(), new TypeToken<List<User>>() {
        }.getType());
    }

    @Transactional(propagation = REQUIRED)
    public User saveOrUpdate(UserRegistration userRegistration) {
        UserEntity userEntity;
        userEntity = userRepository.findOne(userRegistration.getEmail());
        if (userEntity != null) {
            LOG.info("Loaded userEntity: {}", userEntity);
            userEntity.setName(userRegistration.getName());
            // TODO: save the hash instead of the password
            userEntity.setPassword(userRegistration.getPassword());
            return modelMapper.map(userRepository.save(userEntity), User.class);
        } else {
            return createUser(userRegistration);
        }
    }

    public UserEntity getUserEntity(String eMail) {
        return userRepository.findOne(eMail);
    }

    public User getUser(String eMail) {
        return modelMapper.map(getUserEntity(eMail), User.class);
    }

    public void delete(String eMail) {
        userRepository.delete(eMail);
    }

    public User getProfile() {
        UserEntity currentUser = getCurrentUser();
        return modelMapper.map(currentUser, User.class);
    }
}
