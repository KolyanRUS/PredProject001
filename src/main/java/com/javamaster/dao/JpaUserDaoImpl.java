package com.javamaster.dao;

import com.javamaster.model.AppUser;

import java.sql.SQLException;
import java.util.*;

import org.springframework.stereotype.*;
import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class JpaUserDaoImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;
    public void cleanTable() throws SQLException {
        List<AppUser> users_list = getListUsers();
        for (AppUser appUser : users_list
        ) {
            deleteId(appUser.getId());
        }
    }
    public void deleteId(long id) throws SQLException {
        Query q = entityManager.createQuery("DELETE AppUser WHERE id = :idd");
        q.setParameter("idd", id);
        q.executeUpdate();
    }
    public void updateId(int id, String role, String name, String login, String password) throws SQLException {
        String hql = "update AppUser "
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
        entityManager.persist(new AppUser(getListUsers().size()+1,name,password,login,role));
    }
    public long getUserId(String login) throws SQLException {
        AppUser us = getUser(login);
        return us.getId();
    }
    public List<AppUser> getListUsers() throws SQLException {
        try {
            List<AppUser> appUserList = entityManager.createQuery("select u from AppUser u", AppUser.class).getResultList();
            return appUserList;
        } catch (Throwable t) {
            System.out.println("ERROR::getListUsers()::"+t.toString());
        }
        return null;
    }
    public AppUser getUser(String login) throws SQLException {
        //String hql = "SELECT e FROM AppUser e WHERE e.getLogin() = :"+login;
        String hql = "FROM AppUser WHERE login = :loginn";
        Query q = entityManager.createQuery(hql);
        q.setParameter("loginn", login);
        return (AppUser) q.getSingleResult();
    }
    public AppUser get(long id) throws SQLException {
        //String hql = "SELECT e FROM AppUser e WHERE e.getId() = :"+id;
        String hql = "FROM AppUser WHERE id = :idd";
        Query q = entityManager.createQuery(hql);
        q.setParameter("idd",id);
        return (AppUser) q.getSingleResult();
    }
}