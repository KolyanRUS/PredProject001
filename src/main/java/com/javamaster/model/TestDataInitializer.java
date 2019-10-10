package com.javamaster.model;
import com.javamaster.dao.UserDAO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Component
public class TestDataInitializer implements InitializingBean {
    @Autowired
    private UserDAO userDAO;
    private void testUpdate() throws SQLException {
        Set<Role> roleSet = Collections.singleton(userDAO.getRoleById(1L));
        User user = new User();
        user.setId_user(1);
        user.setName("upd_Ivan");
        user.setPassword("333");
        user.setRoles(roleSet);
        userDAO.updateUser(user);
    }
    private void datainit() throws SQLException {
        Role role = new Role();
        role.setRole("admin");
        Role role2 = new Role();
        role2.setRole("user");
        userDAO.saveRole(role);
        userDAO.saveRole(role2);
        Set<Role> roleSet = Collections.singleton(userDAO.getRoleById(1));
        User user = new User();
        user.setRoles(new HashSet<Role>());
        user.setName("Ivan");
        user.setRoles(roleSet);
        user.setPassword("123");
        userDAO.insertUser(user.getName(),user.getPassword(),user.getRoles());
        Set<Role> roleSet2 = Collections.singleton(userDAO.getRoleById(2));
        User user2 = new User();
        user2.setRoles(new HashSet<Role>());
        user2.setName("Vovan");
        user2.setRoles(roleSet2);
        user2.setPassword("9011");
        userDAO.insertUser(user2.getName(),user2.getPassword(),user2.getRoles());
    }
    private void init() throws SQLException {
        datainit();
        testUpdate();
    }
    @PostConstruct
    public void postConstruct() throws SQLException {
        init();
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        //
    }
}