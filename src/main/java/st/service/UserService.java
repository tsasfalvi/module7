package st.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.dto.Profile;
import st.dto.UserRegistration;
import st.entity.UserEntity;
import st.repository.UserRepository;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static st.dto.Role.ROLE_USER;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(UserRegistration userRegistration) {
        if (userRepository.exists(userRegistration.getEmail())) {
            throw new IllegalStateException("There is an account with that email adress: " + userRegistration.getEmail());
        }

        UserEntity userEntity = modelMapper.map(userRegistration, UserEntity.class);

        userEntity.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        userEntity.setRole(ROLE_USER);

        userRepository.save(userEntity);
    }

    @Transactional(propagation = REQUIRED)
    public void update(UserEntity userEntity) {
        userRepository.save(userEntity);
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


    public Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveOrUpdate(UserRegistration userRegistration) {
        UserEntity userEntity;
        userEntity = userRepository.findOne(userRegistration.getEmail());
        if (userEntity != null) {
            LOG.info("Loaded userEntity: {}", userEntity);
            userEntity.setName(userRegistration.getName());
            // TODO: save the hash instead of the password
            userEntity.setPassword(userRegistration.getPassword());
            userRepository.save(userEntity);
        } else {
            createUser(userRegistration);
        }
    }

    public UserEntity getUser(String eMail) {
        return userRepository.findOne(eMail);
    }

    public void delete(String eMail) {
        userRepository.delete(eMail);
    }

    public Profile getProfile() {
        UserEntity currentUser = getCurrentUser();
        return modelMapper.map(currentUser, Profile.class);
    }
}
