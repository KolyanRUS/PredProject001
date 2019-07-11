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

public class UsersDAOImple implements UserDAO {
    private final SessionFactory sessionFactory;
    private Session session;
    private Executor executor;
    public UsersDAOImple() {
        this.sessionFactory = createSessionFactory(Util.getInstance().getMySqlConfiguration());
        this.executor = new Executor(Util.getInstance().getMySQLConnection());
        /*try {
            createTable();
            try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                writer.write("\r\n"+"createTable() UDALOS"+"\r\n");
                writer.flush();
            } catch (Throwable t) {}
        } catch (Throwable throwable) {
            try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                writer.write("\r\n"+"throwable [createTable()]: "+throwable.toString()+"\r\n");
                writer.flush();
            } catch (Throwable t) {}
            //ignore
        }*/
    }
    public void createTable() throws SQLException {
        /*executor.execUpdate("CREATE TABLE IF NOT EXISTS users (Id BIGINT AUTO_INCREMENT, user" +
                "_name VARCHAR(256), user_password VARCHAR(256), user_login VARCHAR(256), " +
                "PRIMARY KEY (Id))");*/
        executor.execUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT, " +
                "name VARCHAR(256), password VARCHAR(256), login VARCHAR(256), " +
                "PRIMARY KEY (id))");
    }
    public void dropTable() throws SQLException {
        executor.execUpdate("DROP TABLE users");
    }
    public void cleanTable() throws SQLException {
        executor.execUpdate("DROP TABLE IF EXISTS users");
        createTable();
    }
    public void deleteId(int id) throws SQLException {
        /*
        Query query =  session.createQuery("delete ContactEntity where firstName = :param");
        query.setParameter("param", "Leonid");
        int result = query.executeUpdate();
         */
        session = sessionFactory.openSession();
        session.createQuery("DELETE FROM users WHERE id = "+id);
        session.close();
        //executor.execUpdate("DELETE FROM users WHERE Id = '"+id+"'");
    }
    public void updateId(int id, String name, String login, String password) throws SQLException {
        session = sessionFactory.openSession();
        session.createQuery("UPDATE users SET name = "+name+", login = "
                +login+", password = "+password+" WHERE id = "+Integer.toString(id));
        session.close();
        //executor.execUpdate("UPDATE users SET user_name = '"+name+"', user_login = '"+login+"', user_password = '"+password+"' WHERE Id = '"+Integer.toString(id)+"'");
    }
    public void insertUser(String name, String password, String login) throws SQLException {
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            long id = (Long) session.save(new User(-1, name, password, login));
            transaction.commit();
            session.close();
        } catch (Throwable t) {
            //ignore
        }
    }
    public long getUserId(String login) throws SQLException {
        User us = getUser(login);
        return us.getId();
    }
    public List<User> getListUsers() throws SQLException {
        try {
            session = sessionFactory.openSession();
            String sql = "SELECT * FROM users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            List<User> users = query.list();
            session.close();
            return users;
        } catch (Throwable t) {
            //ignore
        }
        return null;
    }
    public User getUser(String login) throws  SQLException {
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class);
            session.close();
            User us = (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
            return us;
        } catch (Throwable t) {
            //ignore
        }
        return null;
    }
    public User get(long id) throws SQLException {
        try {
            session = sessionFactory.openSession();
            User user = (User) session.load(User.class, id);
            session.close();
        } catch (Throwable t) {
            //ignore
        }
        return null;
    }
    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}