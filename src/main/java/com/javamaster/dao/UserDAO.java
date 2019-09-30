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

    void updateId(int id, String role, String name, String login, String password) throws SQLException;

    void updateId(int id, Set<Role> userRole, String name, String login, String password) throws SQLException;

    void insertUser(boolean enabled, String role, String name, String password, String login) throws SQLException;

    void insertUser(boolean enabled, Set<Role> userRole, String name, String password, String login) throws SQLException;

    long getUserId(String login) throws SQLException;

    List<User> getListUsers() throws SQLException;

    User getUser(String login) throws SQLException;

    //User getUserByName(String name) throws SQLException;

    User get(long id) throws SQLException;
}
