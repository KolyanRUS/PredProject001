package com.javamaster.dao;

import com.javamaster.model.AppUser;

import java.sql.SQLException;
import java.util.List;

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

    void insertUser(String role, String name, String password, String login) throws SQLException;

    long getUserId(String login) throws SQLException;

    List<AppUser> getListUsers() throws SQLException;

    AppUser getUser(String login) throws SQLException;

    AppUser get(long id) throws SQLException;
}
