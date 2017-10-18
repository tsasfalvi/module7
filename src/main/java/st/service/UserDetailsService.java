//package st.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import st.entity.UserEntity;
//import st.repository.UserRepository;
//
//import java.util.List;
//
//import static java.util.Collections.singletonList;
//
//@Service
//@Transactional
//public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        UserEntity userEntity = userRepository.findOne(email);
//        if (userEntity == null) {
//            throw new UsernameNotFoundException("No userEntity found with username: " + email);
//        }
//
//        String password = userEntity.getPassword().toLowerCase();
//        boolean enabled = !userEntity.isSuspended();
//        List<SimpleGrantedAuthority> authorities = singletonList(new SimpleGrantedAuthority(userEntity.getRole().toString()));
//
//        return new User(userEntity.getEmail(), password, enabled, true, true, true, authorities);
//    }
//
//}
