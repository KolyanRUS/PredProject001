package com.javamaster.model;
import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", unique = false, updatable = false)
    private String name;
    @Column(name = "password", unique = false, updatable = false)
    private String password;
    @Column(name = "login", unique = true, updatable = false)
    private String login;

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

    @SuppressWarnings("UnusedDeclaration")
    public User() {
    }

    public User(long id, String name, String password, String login) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}