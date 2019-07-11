package dbService;

import dao.UsersDAOImple;
import executor.Executor;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImple implements UserService {
    public static UserServiceImple getInstance() {
        return new UserServiceImple();
    }
    private UsersDAOImple dao;
    public UserServiceImple(){
        this.dao = new UsersDAOImple();
    }
    public void createTable() throws SQLException {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println("======\r\ncreateTable() вызвал: "+stackTrace[2].getMethodName()+"\r\n======");
        dao.createTable();
    }
    public void dropTable() throws SQLException {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println("======\r\ndropTable() вызвал: "+stackTrace[2].getMethodName()+"\r\n======");
        dao.dropTable();
    }
    public void cleanTable() throws SQLException {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println("======\r\ncleanTable() вызвал: "+stackTrace[2].getMethodName()+"\r\n======");
        dao.cleanTable();
    }
    public void deleteId(int id) throws SQLException {
        dao.deleteId(id);
    }
    public void updateId(int id, String name, String login, String password) throws SQLException {
        dao.updateId(id,name,login,password);
    }
    /*public static int countTable() throws SQLException {
        Integer integer = executor.<Integer>execQuery(/*Integer.valueOf()*///"SELECT COUNT(*) FROM users",);
    //}*/
    /*
    select * from users
контроллер
сервис хранит экземпляр дао и переиспользует методы
сервлеты это контроллеры
интерфейс к дао
интерфейс к сервису
-------------------------------
упростить с листом
    */
    //метод, который ретёрнует лист юзеров из базы!-!
    public void insertUser(String name, String password, String login) throws SQLException {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println("======\r\ninsertUser() вызвал: "+stackTrace[2].getMethodName()+"\r\n======");
        dao.insertUser(name,password,login);
    }
    public long getUserId(String login) throws SQLException {
        return dao.getUserId(login);
    }
    public List<User> getListUsers() throws SQLException {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println("======\r\ngetListUsers() вызвал: "+stackTrace[2].getMethodName()+"\r\n======");
        return dao.getListUsers();
    }
    public User getUser(String login) throws  SQLException {
        return dao.getUser(login);
    }
    public User get(long id) throws SQLException {
        return dao.get(id);
    }
}