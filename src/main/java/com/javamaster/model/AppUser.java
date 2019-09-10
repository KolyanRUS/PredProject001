package com.javamaster.model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@NamedQuery(name="AppUser.findByUserName", query="SELECT u from AppUser u where LOWER(u.username) like LOWER(:username)")
public class AppUser implements UserDetails {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name", unique = false, updatable = false)
    private String name;
    @Column(name = "password", unique = false, updatable = false)
    private String password;
    @Column(name = "login", unique = true, updatable = false)
    private String login;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="appUser")
    public Set<UserRole> userRole = new HashSet<UserRole>(0);

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return name;
    }

    public long getId() {
        return id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public AppUser(long id, String name, String password, String login, String role) {
        this.id = id;
        Integer integer = (int)id;
        this.name = name;
        this.password = password;
        this.login = login;
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUserRoleId(integer);
        userRole.setAppUser(this);
        this.userRole.add(userRole);
    }

    public AppUser() {
        //
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the userRole
     */

    /**
     * @return the userRole
     */
    //public Set<UserRole> getUserRole() {
    //    return userRole;
    //}

    public Set<UserRole> getAuthorities() {
        return userRole;
    }

    /**
     * @param userRole
     *            the userRole to set
     */
    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
    public boolean isAccountNonExpired() {
        return true;
    }
    public boolean isAccountNonLocked() {
        return true;
    }
    public boolean isCredentialsNonExpired() {
        return true;
    }
}