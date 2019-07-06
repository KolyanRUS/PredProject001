package dbService;

import dao.UsersDAOImple;
import executor.Executor;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImple implements UserService {
    private static UserServiceImple ourInstance = new UserServiceImple();

    public static UserServiceImple getInstance() {
        return ourInstance;
    }
    private UsersDAOImple dao;
    public UserServiceImple(){
        this.dao = new UsersDAOImple();
    }
    public void createTeble() throws SQLException {
        dao.createTeble();
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
        dao.insertUser(name,password,login);
    }
    public long getUserId(String login) throws SQLException {
        return dao.getUserId(login);
    }
    public String tree() {
        return dao.tree();
    }
    public Executor getExecutor() {
        return dao.getExecutor();
    }
    public void setExecutor(Executor executor) {
        dao.setExecutor(executor);
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