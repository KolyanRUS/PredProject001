package com.javamaster.securityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
@Configuration
@ComponentScan("com.javamaster.*")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;
    @Autowired
    public void configureGlobal(UserDetailsService userDetailsService, AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                    .antMatchers("/createuser")
                    .hasRole("ADMIN")
                    .antMatchers("/updateuser")
                    .hasRole("ADMIN")
                .antMatchers("/user")
                .hasAnyRole("ADMIN","USER")
                .and().formLogin()//настройка входа
                    .loginPage("/login").successHandler(customizeAuthenticationSuccessHandler)
                .usernameParameter("login").passwordParameter("password")
                .and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/403");
    }
}