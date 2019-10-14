package com.javamaster.dao;

import com.javamaster.model.Role;
import com.javamaster.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public interface UserDAO {

    void cleanTable() throws SQLException;

    void deleteId(long id) throws SQLException;

    void insertUser(String name, String password, Set<Role> roles) throws SQLException;

    void saveUser(User user) throws SQLException;

    long getUserId(String login) throws SQLException;

    List<User> getListUsers() throws SQLException;

    User getUser(String login) throws SQLException;

    Role getRoleById(long id) throws SQLException;

    Role getRole(String role) throws SQLException;

    void updateUser(User user) throws SQLException;

    User get(long id) throws SQLException;

    void saveRole(Role role) throws SQLException;
}
