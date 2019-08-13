package com.javamaster.dao;

import com.javamaster.model.User;

import java.sql.SQLException;
import java.util.*;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.*;

@Repository
public class UserDaoHibernateImpl implements UserDAO {
    private SessionFactory sessionFactory;
    private Session session;
    public UserDaoHibernateImpl() {
    }
    public void cleanTable() throws SQLException {
        session = sessionFactory.openSession();
        List<User> users_list = getListUsers();
        for (User user: users_list
        ) {
            deleteId((int)user.getId());
        }
    }
    public void deleteId(int id) throws SQLException {
        session = sessionFactory.openSession();
        session.createQuery("DELETE User WHERE id = :id").setLong("id", id).executeUpdate();
        session.close();
    }
    public void updateId(int id, String name, String login, String password) throws SQLException {
        session = sessionFactory.openSession();
        String hql = "update User "
                + "SET login  = :login"
                +   ", name = :name"
                +   ", password = :password"
                +  " where id = :idParam";
        Query query = session.createQuery(hql);
        query.setParameter("idParam"  , (long)id);
        query.setParameter("name"     , name);
        query.setParameter("login" , login);
        query.setParameter("password", password);
        query.executeUpdate();
        session.close();
    }
    public void insertUser(String name, String password, String login) throws SQLException {
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            long id = (Long) session.save(new User(-1, name, password, login));
            transaction.commit();
            session.close();
        } catch (Throwable t) {
            System.out.println("ERROR::insertUser()::"+t.toString());//ignore
        }
    }
    public long getUserId(String login) throws SQLException {
        User us = getUser(login);
        return us.getId();
    }
    public List<User> getListUsers() throws SQLException {
        try {
            session = sessionFactory.openSession();
            String sql = "SELECT * FROM users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            List<User> users = query.list();
            session.close();
            return users;
        } catch (Throwable t) {
            System.out.println("ERROR::getListUsers()::"+t.toString());
        }
        return null;
    }
    public User getUser(String login) throws  SQLException {
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class);
            User us = (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
            session.close();
            return us;
        } catch (Throwable t) {
            System.out.println("ERROR::getUser()::"+t.toString());
        }
        return null;
    }
    public User get(long id) throws SQLException {
        try {
            session = sessionFactory.openSession();
            User user = (User) session.load(User.class, id);
            User us = new User(user.getId(),user.getName(),user.getPassword(),user.getLogin());
            session.close();
            return us;
        } catch (Throwable t) {
            System.out.println("ERROR::get()::"+t.toString());
        }
        return null;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;//
        /*StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
    }
}