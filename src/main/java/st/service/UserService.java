package st.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.dto.UserRegistration;
import st.entity.UserEntity;
import st.repository.UserRepository;

import static st.domain.Role.ROLE_USER;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void createUser(UserRegistration userRegistration) {
        if (userRepository.exists(userRegistration.getEmail())) {
            throw new IllegalStateException("There is an account with that email adress: " + userRegistration.getEmail());
        }

        UserEntity userEntity = modelMapper.map(userRegistration, UserEntity.class);

        userEntity.setRole(ROLE_USER);

        userRepository.save(userEntity);
    }
}
