package com.javamaster.service;

import com.javamaster.dao.*;
import com.javamaster.model.User;
import com.javamaster.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.sql.SQLException;
import java.util.List;
@Service("userService")
public class UserServiceImple implements UserService {
    /*static int u = 0;
    static {
        if(u==0) {
            (new UserServiceImple(new UserDaoHibernateImpl(Util.getInstance()))).sstatic();
            u++;
        }
    }*/
    /*public void sstatic() {
        try {
            //UserServiceImple usii = new UserServiceImple(new UserDaoHibernateImpl(Util.getInstance().getMySqlConfiguration()));
            insertUser("admin","admin", "admin");
            insertUser("user","user", "user");
        } catch (Throwable throwable) {
            System.out.println("ERROR::Start_static::"+throwable.toString());
        }
    }*/
    @Autowired
    private UserDAO dao;
    @Autowired
    public UserServiceImple(UserDAO dao) /*throws java.io.FileNotFoundException, java.io.IOException*/ {
        try {
            this.dao = dao;//new UserDaoHibernateImpl();
        } catch (Throwable throwable) {
            System.out.println("ERROR::UserServiceImple::"+throwable.toString());
        }
    }
    public void cleanTable() throws SQLException {
        dao.cleanTable();
    }
    public void deleteId(int id) throws SQLException {
        dao.deleteId(id);
    }
    public void updateId(int id, String name, String login, String password) throws SQLException {
        dao.updateId(id,name,login,password);
    }
    public void insertUser(String name, String password, String login) throws SQLException {
        dao.insertUser(name,password,login);
    }
    public long getUserId(String login) throws SQLException {
        return dao.getUserId(login);
    }
    public List<User> getListUsers() throws SQLException {
        return dao.getListUsers();
    }
    public User getUser(String login) throws  SQLException {
        return dao.getUser(login);
    }
    public User get(long id) throws SQLException {
        return dao.get(id);
    }
}