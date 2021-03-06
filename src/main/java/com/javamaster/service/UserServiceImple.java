package com.javamaster.service;
import com.javamaster.dao.*;
import com.javamaster.model.Role;
import com.javamaster.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

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
    public void insertUser(String name, String password, Set<Role> roles) throws SQLException {
        dao.insertUser(name, password, roles);
    }
    public void saveUser(User user) throws SQLException {
        dao.saveUser(user);
    }
    public void updateUser(User user) throws SQLException {
        dao.updateUser(user);
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
    public Role getRoleById(long id) throws  SQLException {
        return dao.getRoleById(id);
    }
    public Role getRole(String role) throws  SQLException {
        return dao.getRole(role);
    }
    public User get(long id) throws SQLException {
        return dao.get(id);
    }
    public UserDAO getDao() {
        return dao;
    }
}