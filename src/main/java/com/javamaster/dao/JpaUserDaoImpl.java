package com.javamaster.dao;

import com.javamaster.model.Role;
import com.javamaster.model.User;

import java.sql.SQLException;
import java.util.*;

import org.springframework.stereotype.*;
import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class JpaUserDaoImpl implements UserDAO{
    @PersistenceContext
    private EntityManager entityManager;
    public void cleanTable() {
        List<User> users_list = getListUsers();
        for (User user : users_list
        ) {
            deleteId(user.getId_user());
        }
    }
    public void deleteId(long id) {
        Query q = entityManager.createQuery("DELETE User WHERE id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }
    public void updateUser(User user) {
        entityManager.merge(user);
    }
    public void userPersist(User user) {
        entityManager.persist(user);
    }
    public void insertUser(String name, String password, Set<Role> roles) throws SQLException {
        entityManager.persist(new User(name,password,roles));
    }
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }
    public long getUserId(String login) {
        User us = getUser(login);
        return us.getId_user();
    }
    public List<User> getListUsers() {
        try {
            List<User> userList = entityManager.createQuery("FROM User").getResultList();
            return userList;
        } catch (Throwable t) {
            System.out.println("ERROR::getListUsers()::"+t.toString());
        }
        return null;
    }
    public User getUser(String login) {
        return (User) entityManager.createNativeQuery("SELECT * FROM users WHERE name = ?", User.class)
                .setParameter(1, login).getSingleResult();
    }
    public Role getRole(String role) {
        return (Role) entityManager.createQuery("FROM Role WHERE role = :role")
                .setParameter("role", role).getSingleResult();
    }
    public Role getRoleById(long id) {
        return (Role) entityManager.createQuery("FROM Role WHERE id = :id")
                .setParameter("id", id).getSingleResult();
    }
    public User get(long id) {
        return (User) entityManager.createQuery("FROM User WHERE id = :id")
                .setParameter("id", id).getSingleResult();
    }
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}