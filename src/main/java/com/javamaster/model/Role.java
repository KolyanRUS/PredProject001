package com.javamaster.model;

import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Column(length = 45, nullable = false)
    private String role;
    /*@ManyToMany(mappedBy = "roles")
    private Set<User> users;*/

    /**
     * @return the userRoleId
     */
    public long getUserRoleId() {
        return id;
    }

    /**
     * @param userRoleId
     *            the userRoleId to set
     */
    public void setUserRoleId(Integer userRoleId) {
        this.id = userRoleId;
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
