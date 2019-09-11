package com.javamaster.service;
import com.javamaster.dao.JpaUserDaoImpl;
import com.javamaster.dao.UserRepo;
import com.javamaster.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service("userDetailsService")
public class AppUserService implements UserDetailsService {
    @Autowired
    private JpaUserDaoImpl userRepo;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        //JpaUserDaoImpl userRepo = new JpaUserDaoImpl();
        User user = null;
        try{
            user = userRepo.getUser(username);/*findByUserName(username);*/
        } catch (Throwable throwable) {
            System.out.println("user======="+throwable.toString());
        }
        if(user==null) {
            System.out.println("user==null");
        }
        return user;
    }

    /*private UserDetails buildUserForAuthentication(User user,
                                                   List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;

    }*/
}