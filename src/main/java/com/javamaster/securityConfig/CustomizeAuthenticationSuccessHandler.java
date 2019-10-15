package com.javamaster.securityConfig;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamaster.model.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Role r = (Role) authorities.toArray()[0];
        boolean admin = "ROLE_ADMIN".equals(r.getRole());
        if(admin){
            response.sendRedirect("/admin");
        } else {
            response.sendRedirect("/user");
        }
    }
}