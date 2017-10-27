package st.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.dto.Role;
import st.dto.User;
import st.dto.UserRegistration;
import st.entity.UserEntity;
import st.exception.BadRequestException;
import st.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static st.dto.Role.ROLE_LIBRARIAN;
import static st.dto.Role.ROLE_USER;

@Service
public class UserService {
    private static final String DEFAULT_PASSWORD = "abc123";

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(UserRegistration userRegistration) throws BadRequestException {
        if (userRepository.exists(userRegistration.getEmail())) {
            throw new BadRequestException("There is an account with that email adress: " + userRegistration.getEmail());
        }

        UserEntity userEntity = modelMapper.map(userRegistration, UserEntity.class);

        initUserEntity(userEntity, ROLE_USER, userRegistration.getPassword());

        return modelMapper.map(userRepository.save(userEntity), User.class);
    }

    @Transactional(propagation = REQUIRED)
    public User update(User user) {
        UserEntity userEntity = getUserEntity(user.getEmail());

        if (userEntity == null) {
            userEntity = modelMapper.map(user, UserEntity.class);
            initUserEntity(userEntity, user.getRole(), DEFAULT_PASSWORD);
        } else {
            userEntity.setName(user.getName());
            userEntity.setSuspended(user.isSuspended());
        }
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
        UserEntity currentUser = getCurrentUser();
        if (Objects.equals(currentUser.getRole(), ROLE_LIBRARIAN)) {
            return modelMapper.map(userRepository.findAll(), new TypeToken<List<User>>() {
            }.getType());
        } else {
            return Collections.singletonList(modelMapper.map(currentUser, User.class));
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

    public User update(UserEntity user) {
        return modelMapper.map(userRepository.save(user), User.class);
    }

    private void initUserEntity(UserEntity userEntity, Role roleUser, String password) {
        if (password == null) {
            password = DEFAULT_PASSWORD;
        }

        if (userEntity.getSubscriptions() != null) {
            userEntity.setSubscriptions(null);
        }

        if (userEntity.getBorrows() != null) {
            userEntity.setBorrows(null);
        }

        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setRole(roleUser);
    }

    public User patch(JsonNode field) throws BadRequestException {
        JsonNode suspendedNode = field.get("suspended");
        if (isNull(suspendedNode)) {
            throw new BadRequestException("only suspended allowed");
        }
        boolean suspended = suspendedNode.asBoolean();

        UserEntity currentUser = getCurrentUser();
        currentUser.setSuspended(suspended);

        return modelMapper.map(userRepository.save(currentUser), User.class);
    }
}
