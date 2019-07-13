package dbService;

import dao.UsersDAOImple;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImple implements UserService {
    public static UserServiceImple usi = new UserServiceImple();
    public static UserServiceImple getInstance() {
        return usi;
    }
    private UsersDAOImple dao;
    public UserServiceImple(){
        this.dao = new UsersDAOImple();
    }
    public void createTable() throws SQLException {
        /*оставил на будущее (вдруг снова потребуется узнавать, откуда был вызван метод)
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println("======\r\ncreateTable() вызвал: "+stackTrace[2].getMethodName()+"\r\n======");*/
        dao.createTable();
    }
    public void dropTable() throws SQLException {
        dao.dropTable();
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