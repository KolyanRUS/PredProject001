package com.javamaster.service;
import com.javamaster.dao.*;
import com.javamaster.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import java.sql.SQLException;
import java.util.List;
@Service("userService")
public class UserServiceImple implements UserService {
    private UserDAO dao;
    @Autowired
    public UserServiceImple(UserDAO dao) {
        this.dao = dao;
    }
    public void cleanTable() throws SQLException {
        dao.cleanTable();
    }
    public void deleteId(int id) throws SQLException {
        dao.deleteId(id);
    }
    public void updateId(int id, String role, String name, String login, String password) throws SQLException {
        dao.updateId(id,role,name,login,password);
    }
    public void insertUser(String role, String name, String password, String login) throws SQLException {
        dao.insertUser(role,name,password,login);
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