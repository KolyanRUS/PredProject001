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
        Query q = entityManager.createQuery("DELETE User WHERE id = :idd");
        q.setParameter("idd", id);
        q.executeUpdate();
    }
    public void updateId(int id, Set<Role> userRole, String name, String login, String password) throws SQLException {
        User u = get(id);//new User(false,name,password,login,userRole);
        u.setName(name);
        u.setPassword(password);
        //Set<Role> usserRole = new HashSet<>();
        //Role r = getRole(((Role)userRole.toArray()[0]).getRole());
        //usserRole.add(r);
        u.setRoles(userRole);
        int y = 0;
        u.setId_user(id);
        updateUser(u);
        //entityManager.flush();
        y = 2;
    }
    public void updateUser(User user) throws SQLException {
        /*String hql = "update User "
                + "SET roles = :role,"
                +   " name = :name"
                +   ", password = :password"
                +  " where id_user = :idParam";
        Query query =  entityManager.createQuery(hql);
        query.setParameter("idParam"  , user.getId_user());
        query.setParameter("role"     , user.getAuthorities());
        query.setParameter("name"     , user.getUsername());
        query.setParameter("password", user.getPassword());
        query.executeUpdate();*/
        entityManager.merge(user);
        //userPersist(user);        entityManager.merge(user);
        entityManager.flush();
    }
    public void userPersist(User user) {
        entityManager.persist(user);
    }
    public void updateId(int id, String role, String name, String login, String password) throws SQLException {
        String hql = "update User "
                + "SET roles = :role,"
                +   " name = :name"
                +   ", password = :password"
                +  " where id = :idParam";
        Query query =  entityManager.createQuery(hql);
        query.setParameter("idParam"  , (long)id);
        query.setParameter("role"     , role);
        query.setParameter("name"     , name);
        query.setParameter("password", password);
        query.executeUpdate();
    }
    public void insertUser(String name, String password, Set<Role> roles) throws SQLException {
        int ti = 0;
        entityManager.persist(new User(name,password,roles));
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
        //String hql = "SELECT e FROM User e WHERE e.getLogin() = :"+login;
        String hql = "FROM User WHERE login = :loginn";
        Query q = entityManager.createQuery(hql);
        q.setParameter("loginn", login);
        try {
            //javax.persistence.PersistenceException: org.hibernate.exception.SQLGrammarException: could not extract ResultSetL
            q.getSingleResult();
        } catch(Throwable t) {
            System.out.println("EEEEEEEERROR:::"+t.toString());
        }
        return (User) q.getSingleResult();
    }
    public Role getRole(String role) throws SQLException {
        String hql = "FROM Role WHERE role = :rollle";
        Query q = entityManager.createQuery(hql);
        q.setParameter("rollle", role);
        try {
            q.getSingleResult();
        } catch(Throwable t) {
            System.out.println("EEEEEEEERROR_getRole:::"+t.toString());
        }
        return (Role) q.getSingleResult();
    }
    public Role getRoleById(long id) throws SQLException {
        String hql = "FROM Role WHERE id_role = :idd";
        Query q = entityManager.createQuery(hql);
        q.setParameter("idd", id);
        try {
            q.getSingleResult();
        } catch(Throwable t) {
            System.out.println("EEEEEEEERROR_getRoleById:::"+t.toString());
        }
        return (Role) q.getSingleResult();
    }
    /*public User getUserByName(String name) throws SQLException {
        //String hql = "SELECT e FROM User e WHERE e.getLogin() = :"+login;
        String hql = "FROM AppUser WHERE name = :nname";
        Query q = entityManager.createQuery(hql);
        q.setParameter("nname", name);
        return (User) q.getSingleResult();
    }*/
    public User get(long id) throws SQLException {
        String hql = "FROM User WHERE id = :idd";
        Query q = entityManager.createQuery(hql);
        q.setParameter("idd",id);
        return (User) q.getSingleResult();
    }
}