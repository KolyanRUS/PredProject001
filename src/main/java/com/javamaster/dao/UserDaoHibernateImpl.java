package com.javamaster.dao;

import com.javamaster.model.User;

import java.sql.SQLException;
import java.util.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.*;
import javax.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoHibernateImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;
    public void cleanTable() throws SQLException {
        List<User> users_list = getListUsers();
        for (User user: users_list
        ) {
            deleteId((int)user.getId());
        }
    }
    public void deleteId(int id) throws SQLException {
        Query q = entityManager.createQuery("DELETE User WHERE id = :id");
        q.executeUpdate();
    }
    public void updateId(int id, String name, String login, String password) throws SQLException {
        String hql = "update User "
                + "SET login  = :login"
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
    public void insertUser(String name, String password, String login) throws SQLException {
        entityManager.persist(new User(getListUsers().size()+1,name,password,login));
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
        String hql = "SELECT e FROM User e WHERE e.getLogin() = :"+login;
        Query q = entityManager.createQuery(hql);
        return (User) q.getSingleResult();
    }
    public User get(long id) throws SQLException {
        String hql = "SELECT e FROM User e WHERE e.getId() = :"+id;
        Query q = entityManager.createQuery(hql);
        return (User) q.getSingleResult();
    }
}