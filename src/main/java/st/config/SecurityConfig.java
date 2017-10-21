package st.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import st.service.UserDetailsService;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

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

        configureRestProfiles(http);
        configureBasicAuth(http);

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    private void configureRegistration(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(POST, "/registration").permitAll();
    }

    private void configureRestProfiles(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(GET, "/profile/**").permitAll();
    }

    private void configureBooks(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(GET, "/book", "/book/*").permitAll();
    }

    private void configureBasicAuth(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    private void configureRoot(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll();
    }

    private void configureH2(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2/**").permitAll();
    }

    @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }
}
