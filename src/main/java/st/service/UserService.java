package st.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.dto.UserRegistration;
import st.entity.UserEntity;
import st.repository.UserRepository;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static st.domain.Role.ROLE_USER;

@Service
public class UserService {
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
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUser = authentication.getName();
            return userRepository.findOne(currentUser);
        }

        return null;
    }
}
