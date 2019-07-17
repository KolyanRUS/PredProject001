package factory;

import dao.UserDAO;
import dao.UserDaoHibernateImpl;
import dao.UserDaoJDBCimpl;
import factory.UserDaoFactory;

public class UserDAOFactoryImpl implements UserDaoFactory {
    @Override
    public UserDAO getUserDAO() {
        String strFromProperties = (new ReadProperties()).stringFromProperties();
        if (strFromProperties.equals("UserDaoJDBCimpl")) {
            return getUserDaoJDBCimpl();
        } else if (strFromProperties.equals("UserDaoHibernateImpl")) {
            return getUserDaoHibernateImpl();
        }
        return null;
    }
    @Override
    public UserDAO getUserDaoHibernateImpl() {
        return new UserDaoHibernateImpl();
    }
    @Override
    public UserDAO getUserDaoJDBCimpl() {
        return new UserDaoJDBCimpl();
    }
    /*@Override
    public UserDAO getUserDAO(String UserDAOname) {
        try {
            Class<?> clazz = Class.forName(UserDAOname);
            //Object d = clazz.newInstance();
            UserDAO dao = (UserDAO) clazz.newInstance();
            return dao;
        } catch (Throwable throwable) {
            System.out.println("ERROR::UserDAO_Factory_getUserDao()::"+throwable.toString());
        }
        return null;
    }*/
}