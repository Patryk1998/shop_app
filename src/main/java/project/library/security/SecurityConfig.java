package project.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.library.dao.UserDao;
import project.library.services.MyUserDetailsService;


@Configuration
@EnableWebSecurity(debug = true)
@EnableJpaRepositories(basePackageClasses = UserDao.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsSerive;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsSerive)
                .passwordEncoder(getPasswordEncoder());
    }

    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/h2**").permitAll()
                .antMatchers( "/user/test").permitAll()
                .antMatchers("/user/login**").permitAll()
                .antMatchers("/shop/shop").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/login/asd")
                .and()
                .httpBasic();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }


    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return true;
            }
        };
    }

}
