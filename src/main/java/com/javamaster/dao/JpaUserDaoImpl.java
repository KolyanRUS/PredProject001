package com.javamaster.dao;

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
@Transactional
public class JpaUserDaoImpl implements UserDAO/*, UserRepo */{
    @PersistenceContext
    private EntityManager entityManager;
    public void cleanTable() throws SQLException {
        List<User> users_list = getListUsers();
        for (User user : users_list
        ) {
            deleteId(user.getId());
        }
    }
    public void deleteId(long id) throws SQLException {
        Query q = entityManager.createQuery("DELETE User WHERE id = :idd");
        q.setParameter("idd", id);
        q.executeUpdate();
    }
    public void updateId(int id, String role, String name, String login, String password) throws SQLException {
        String hql = "update User "
                + "SET role = :role,"
                + "login  = :login"
                +   ", name = :name"
                +   ", password = :password"
                +  " where id = :idParam";
        Query query =  entityManager.createQuery(hql);
        query.setParameter("idParam"  , (long)id);
        query.setParameter("name"     , name);
        query.setParameter("login" , login);
        query.setParameter("password", password);
        query.executeUpdate();
    }
    public void insertUser(String role, String name, String password, String login) throws SQLException {
        entityManager.persist(new User(getListUsers().size()+1,name,password,login,role));
    }
    public long getUserId(String login) throws SQLException {
        User us = getUser(login);
        return us.getId();
    }
    public List<User> getListUsers() throws SQLException {
        try {
            List<User> userList = entityManager.createQuery("select u from User u", User.class).getResultList();
            return userList;
        } catch (Throwable t) {
            System.out.println("ERROR::getListUsers()::"+t.toString());
        }
        return null;
    }
    public User getUser(String login) throws SQLException {
        //String hql = "SELECT e FROM User e WHERE e.getLogin() = :"+login;
        String hql = "FROM AppUser WHERE login = :loginn";
        Query q = entityManager.createQuery(hql);
        q.setParameter("loginn", login);
        return (User) q.getSingleResult();
    }
    /*public User findByUserName(@Param("username") String username) {
        String hql = "FROM AppUser WHERE login = :loginn";
        Query q = entityManager.createQuery(hql);
        q.setParameter("loginn", username);
        return (User) q.getSingleResult();
    }*/
    public User get(long id) throws SQLException {
        //String hql = "SELECT e FROM User e WHERE e.getId() = :"+id;
        String hql = "FROM AppUser WHERE id = :idd";
        Query q = entityManager.createQuery(hql);
        q.setParameter("idd",id);
        return (User) q.getSingleResult();
    }
}