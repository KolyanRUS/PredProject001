package dao;

import executor.Executor;
import model.User;
import util.Util;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import model.User;
import java.io.FileWriter;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import util.Util;

public class UserDaoJDBCimpl implements UserDAO {
    private Executor executor;
    public UserDaoJDBCimpl() {
        this.executor = new Executor(Util.getInstance().getMySQLConnection());
        try {
            createTable();
        } catch (Throwable throwable) {
            System.out.println("ERROR::UserDaoJDBCimpl()_createTable()::"+throwable.toString());
        }
    }
    public void createTable() throws SQLException {
        executor.execUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT, " +
                "role VARCHAR(255), login VARCHAR(255), name VARCHAR(255), password VARCHAR(255), " +
                "PRIMARY KEY (id))");
    }
    public void dropTable() throws SQLException {
        executor.execUpdate("DROP TABLE IF EXISTS users");
    }
    public void cleanTable() throws SQLException {
        dropTable();
        createTable();
    }
    public void deleteId(int id) throws SQLException {
        executor.execUpdate("DELETE FROM users WHERE Id = '"+id+"'");
    }
    public void updateId(int id, String role, String name, String login, String password) throws SQLException {
        executor.execUpdate("UPDATE users SET role = '"+role+"', name = '"+name+"', login = '"+login+"', password = '"+password+"' WHERE Id = '"+Integer.toString(id)+"'");
    }
    public void insertUser(String role, String name, String password, String login) throws SQLException {
        executor.execUpdate("INSERT INTO users (role, name, login, password) VALUES ('" + role + "', '" + name + "','" + login + "','" + password + "')");
    }
    public long getUserId(String login) throws SQLException {
        return executor.execQuery("SELECT * FROM users WHERE login='" + login + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }
    public Executor getExecutor() {
        return executor;
    }
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
    public List<User> getListUsers() throws SQLException {
        return executor.execQuery("SELECT * FROM users", result -> {
            List<User> llistUsers = new ArrayList<User>();
            while(result.next()) {
                llistUsers.add(new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
            }
            return llistUsers;
        });
    }
    public User getUser(String login) throws  SQLException {
        return executor.execQuery("SELECT * FROM users WHERE login='" + login + "'", result -> {
            result.next();
            return new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
        });
    }
    public User get(long id) throws SQLException {
        return executor.execQuery("SELECT * FROM users WHERE Id=" + id, result -> {
            result.next();
            return new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
        });
    }
}