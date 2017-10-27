package st.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import st.service.UserDetailsService;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static st.dto.Role.ROLE_LIBRARIAN;
import static st.dto.Role.ROLE_USER;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configureRoot(http);
        configureH2(http);
        configureBooks(http);
        configureRegistration(http);
        configureUser(http);

        configureBasicAuth(http);

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    private void configureUser(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(GET, "/user/profile").hasAuthority(ROLE_USER.toString());
    }

    private void configureRegistration(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(POST, "/registration").permitAll();
    }

    private void configureBooks(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(GET, "/book", "/book/*").permitAll();
        http.authorizeRequests().antMatchers(POST, "/book/borrow").hasAuthority(ROLE_USER.toString());
        http.authorizeRequests().antMatchers(PUT, "/book/*/handover").hasAuthority(ROLE_LIBRARIAN.toString());
    }

    private void configureBasicAuth(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    private void configureRoot(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/partials/home").permitAll();
        http.authorizeRequests().antMatchers("/partials/booklist").permitAll();
    }

    private void configureH2(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2/**").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
