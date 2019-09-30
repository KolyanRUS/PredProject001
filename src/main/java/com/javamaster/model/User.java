package com.javamaster.model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
//@NamedQuery(name="User.findByUserName", query="SELECT u from User u where LOWER(u.username) like LOWER(:username)")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private long id_user;
    @Column(name = "name", unique = false, updatable = false)
    private String name;
    @Column(name = "password", unique = false, updatable = false)
    private String password;
    @Column(name = "login", unique = true, updatable = false)
    private String login;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    //Каскадирование позволяет сказать JPA «сделай с владеемыми объектами класса тоже самое, что ты делаешь с владельцем».
    //То есть, когда мы удаляем гражданина из базы, JPA самостоятельно увидит, что гражданин владеет паспорт и удалит вначале паспорт, потом гражданина.


    @ManyToMany(fetch=FetchType.EAGER/*объекты коллекции сразу загружаются в память*/,cascade=CascadeType.REFRESH)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

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

    public void setUsername(String name) {this.name = name;}

    public String getUsername() {
        return name;
    }

    public long getId() {
        return id_user;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public User(/*long id, */boolean enabled, String name, String password, String login, String role) {
        //this.id_user = id;
        this.name = name;
        this.enabled = enabled;
        this.password = password;
        this.login = login;//доставлять роль из базы и вставлять(в сервлете)
        Role userRole = new Role();
        userRole.setRole(role);
        this.roles.add(userRole);
        //Set<Role> set = new HashSet<>();
        //set.add(userRole);
        //this.setUserRole(set);
    }

    public User(boolean enabled, String name, String password, String login, Set<Role> userRole) {
        this.name = name;
        this.enabled = enabled;
        this.password = password;
        this.login = login;//доставлять роль из базы и вставлять(в сервлете)
        this.setUserRole(userRole);
        //Role userRole = new Role();
        //userRole.setRole(role);
        //this.roles.add(userRole);
        //Set<Role> set = new HashSet<>();
        //set.add(userRole);
        //this.setUserRole(set);
    }

    public User() {
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
    //public Set<Role> getUserRole() {
    //    return userRole;
    //}

    public Set<Role> getAuthorities() {
        return roles;
    }

    /**
     * @param userRole
     *            the userRole to set
     */
    public void setUserRole(Set<Role> userRole) {
        this.roles = userRole;
    }
    public void setId(long id) {
        this.id_user = id;
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