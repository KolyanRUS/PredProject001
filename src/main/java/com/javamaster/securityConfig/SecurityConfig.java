package com.javamaster.securityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;//отключить
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
                .authorizeRequests()//проверяем на предмет авторизации перед переходом
                //.antMatchers("/admin","/createuser","/updateuser")
                //.access("hasRole('admin')");
                    .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                    .antMatchers("/createuser")
                    .hasRole("ADMIN")
                    .antMatchers("/updateuser")
                    .hasRole("ADMIN")
                .and().formLogin()//настройка входа
                    .loginPage("/login").successHandler(customizeAuthenticationSuccessHandler)/*.failureUrl("/start?error")*/
                .usernameParameter("login").passwordParameter("password")
                .and().logout().permitAll()//logoutSuccessUrl("/login?logout").and().csrf()
                .and().exceptionHandling().accessDeniedPage("/403")
                //intercepted urls
                ;
    }
}