package dao;

import executor.Executor;
import model.User;
import util.Util;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class UsersDAOImple implements UserDAO {
    private Executor executor;
    public UsersDAOImple() {
        this.executor = new Executor(Util.getInstance().getMySQLConnection());
        try {
            createTeble();
        } catch (Throwable throwable) {
            try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                writer.write("createTable() [что в static`е] выполнить успешно не удалось, ошибка "+throwable.toString()+"\r\n");
                writer.flush();
            }
            catch (IOException ex) {
                //ignore
            }
            //ignore
        }
    }
    public void createTeble() throws SQLException {
        executor.execUpdate("CREATE TABLE IF NOT EXISTS users (Id BIGINT AUTO_INCREMENT, user" +
                "_name VARCHAR(256), user_password VARCHAR(256), user_login VARCHAR(256), " +
                "PRIMARY KEY (Id))");
    }
    public void dropTable() throws SQLException {
        executor.execUpdate("DROP TABLE users");
    }
    public void cleanTable() throws SQLException {
        //executor.execUpdate("TRUNCATE TABLE users");
        executor.execUpdate("DROP TABLE IF EXISTS users");
        executor.execUpdate("CREATE TABLE IF NOT EXISTS users (Id BIGINT AUTO_INCREMENT, user" +
                "_name VARCHAR(256), user_password VARCHAR(256), user_login VARCHAR(256), " +
                "PRIMARY KEY (Id))");
    }
    public void deleteId(int id) throws SQLException {
        executor.execUpdate("DELETE FROM users WHERE Id = '"+id+"'");
    }
    public void updateId(int id, String name, String login, String password) throws SQLException {
        /*executor.execUpdate("UPDATE users SET user_name = '"+name+"', user_login = '"+login+
                "', user_password = '"+password+"' WHERE Id = '"+String.valueOf(id)+"'");*/
        executor.execUpdate("UPDATE users SET user_name = '"+name+"', user_login = '"+login+"', user_password = '"+password+"' WHERE Id = '"+Integer.toString(id)+"'");
    }
    public void insertUser(String name, String password, String login) throws SQLException {
        executor.execUpdate("INSERT INTO users (user_name, user_password, user_login) VALUES ('" + name + "','" + password + "','" + login + "')");
    }
    public long getUserId(String login) throws SQLException {
        return executor.execQuery("SELECT * FROM users WHERE user_login='" + login + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }
    public String tree() {
        try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
            writer.write("UsersDAO_tree()..."+"\r\n");
            writer.flush();
        }
        catch (IOException ex) {
            //ignore
        }
        try {
            createTeble();
        } catch (Throwable throwable) {
            try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                writer.write("UsersDAO_tree()_createTeble() не удалось..."+"\r\n");
                writer.flush();
            }
            catch (IOException ex) {
                //ignore
            }
            //ignore
        }
        try{
            insertUser("Ivan1","123","ivan_1");
            insertUser("Ivan2","123","ivan_2");
            insertUser("Ivan3","123","ivan_3");
        } catch (/*SQLException*/Throwable sql) {
            try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                writer.write("UsersDAO_tree()_insertUser() не удалось... "+sql.toString()+"\r\n");
                writer.flush();
            }
            catch (IOException ex) {
                //ignore
            }
            //ignore
        }
        return "reX";
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
                llistUsers.add(new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4)));
            }
            return llistUsers;
        });
    }
    public User getUser(String login) throws  SQLException {
        return executor.execQuery("SELECT * FROM users WHERE user_login='" + login + "'", result -> {
            result.next();
            return new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4));
        });
    }
    public User get(long id) throws SQLException {
        return executor.execQuery("SELECT * FROM users WHERE Id=" + id, result -> {
            result.next();
            return new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4));
        });
    }
}
