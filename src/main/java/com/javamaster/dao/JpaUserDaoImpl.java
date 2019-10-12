package com.javamaster.dao;

import com.javamaster.model.Role;
import com.javamaster.model.User;

import java.sql.SQLException;
import java.util.*;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;
import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional//(noRollbackFor = Exception.class)
public class JpaUserDaoImpl implements UserDAO/*, UserRepo */{
    @PersistenceContext
    private EntityManager entityManager;
    public void cleanTable() throws SQLException {
        List<User> users_list = getListUsers();
        for (User user : users_list
        ) {
            deleteId(user.getId_user());
        }
    }
    public void deleteId(long id) throws SQLException {
        Query q = entityManager.createQuery("DELETE User WHERE id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }
    public void updateUser(User user) throws SQLException {
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
    public long getUserId(String login) throws SQLException {
        User us = getUser(login);
        return us.getId_user();
    }
    public List<User> getListUsers() throws SQLException {
        try {
            List<User> userList = entityManager.createQuery("FROM User").getResultList();
            return userList;
        } catch (Throwable t) {
            System.out.println("ERROR::getListUsers()::"+t.toString());
        }
        return null;
    }
    public User getUser(String login) throws SQLException {
        //return (User) entityManager.createQuery("FROM User WHERE login = :login")
                //.setParameter("login", login).getSingleResult();
        List<User> listUsers = getListUsers();
        for(User us: listUsers) {
            if(login.equals(us.getName())) {
                return us;
            }
        }
        return null;
    }
    public Role getRole(String role) throws SQLException {
        return (Role) entityManager.createQuery("FROM Role WHERE role = :role")
                .setParameter("role", role).getSingleResult();
    }
    public Role getRoleById(long id) throws SQLException {
        return (Role) entityManager.createQuery("FROM Role WHERE id = :id")
                .setParameter("id", id).getSingleResult();
    }
    public User get(long id) throws SQLException {
        return (User) entityManager.createQuery("FROM User WHERE id = :id")
                .setParameter("id", id).getSingleResult();
    }
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}