package com.javamaster.securityService;
import com.javamaster.dao.UserDAO;
import com.javamaster.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class AppUserService implements UserDetailsService {
    @Autowired
    private UserDAO userRepo;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = null;
        try{
            user = userRepo.getUser(username);
        } catch (Throwable throwable) {
            System.out.println("user======="+throwable.toString());
        }
        if(user==null) {
            System.out.println("user==null");
        }
        return user;
    }
}