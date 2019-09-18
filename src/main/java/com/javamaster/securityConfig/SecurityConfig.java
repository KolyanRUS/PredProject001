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

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan("com.javamaster.*")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/Users");
        dataSource.setUsername("Kolyan1998");
        dataSource.setPassword("admin");
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT name, password, 'true' FROM users WHERE name=?")
                //.authoritiesByUsernameQuery("SELECT U.name"+/*", A.AUTHORITY"+*/  /*"\n" +
                        //"        \t FROM AUTHORITIES A, USER U WHERE U.name = A.name AND U.name = ?");
                .authoritiesByUsernameQuery("SELECT id_role, role"+"\n" +
                        "FROM roles WHERE id_role in "+
                        "(SELECT role_id FROM user_roles WHERE role_id in "+
                        "(SELECT id_user FROM users WHERE name=?)"
                        +")");


                        //+"        \t FROM roles A, users U WHERE .id_role=.role_id AND U.name = A.name AND U.name = ?");//
    }*/
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
                    .antMatchers("/admin")
                    .hasRole("admin")
                    .antMatchers("/createuser")
                    .hasRole("admin")
                    .antMatchers("/updateuser")
                    .hasRole("admin")
                .and().formLogin()//настройка входа
                    .loginPage("/login")/*.failureUrl("/start?error")*/
                .usernameParameter("login").passwordParameter("password")
                .and().logout().permitAll()//logoutSuccessUrl("/login?logout").and().csrf()
                .and().exceptionHandling().accessDeniedPage("/403")
                //intercepted urls
                ;

    }
}