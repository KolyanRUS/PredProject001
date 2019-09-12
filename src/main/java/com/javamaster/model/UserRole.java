package com.javamaster.model;

import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user_roles")
public class UserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", nullable = false, unique = true)
    private long userRoleId;
    @Column(length = 45, nullable = false)
    private String role;

    /**
     * @return the userRoleId
     */
    public long getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId
     *            the userRoleId to set
     */
    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    public String getAuthority() {
        return role;
    }
}
