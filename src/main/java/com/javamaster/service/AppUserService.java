package com.javamaster.service;
import com.javamaster.dao.AppUserRepo;
import com.javamaster.model.AppUser;
import org.springframework.security.core.userdetails.User;
import com.javamaster.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service("userDetailsService")
public class AppUserService implements UserDetailsService {
    @Autowired
    private AppUserRepo appUserRepo;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        AppUser user = appUserRepo.findByUserName(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user
                .getAuthorities());

        return buildUserForAuthentication(user, authorities);
    }

    private UserDetails buildUserForAuthentication(AppUser user,
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

    }
}