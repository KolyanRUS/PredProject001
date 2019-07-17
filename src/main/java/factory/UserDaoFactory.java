package factory;

import model.User;
import dao.UserDAO;

import java.sql.SQLException;
import java.util.List;

public interface UserDaoFactory {
    UserDAO getUserDAO();
    UserDAO getUserDaoHibernateImpl();
    UserDAO getUserDaoJDBCimpl();
}