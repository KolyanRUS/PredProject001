package com.javamaster.controller;

import com.javamaster.dao.JpaUserDaoImpl;
import com.javamaster.model.User;
import com.javamaster.model.Role;
import com.javamaster.service.UserServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.*;

@Controller
public class StartController {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserServiceImple usi;


    @RequestMapping(value="/user", method=RequestMethod.GET)
    public String getAdminPagePost() throws SQLException {
        return "user";
    }



    @RequestMapping(value="/admin", method=RequestMethod.GET)
    public String getAdminPageGet(Model model) throws SQLException {
        List<User> userList = usi.getListUsers();
        userList = usi.getListUsers();
        model.addAttribute("users", userList);
        return "admin";
    }
    @RequestMapping(value="/admin", method=RequestMethod.POST)
    public String getAdminPagePost(Model model, @RequestParam(value="ButtonName") String butname, HttpServletResponse resp) throws SQLException {
        if(butname.equals("Delete_All_Users")) {
            usi.cleanTable();
        } else {
            try {
                int number = Integer.parseInt(butname);
                usi.deleteId(number);
                return "redirect:/admin";
            } catch (Throwable throwable) {
                System.out.println("throwable[Admin_doPost_deleteUser]: "+throwable.toString());
            }
        }
        return "admin";
    }







    @RequestMapping(value="/createuser", method=RequestMethod.GET)
    public String getCreateuserPageGet(Model model) {
        //rolesList.add(new String[]{usi.getRoleById(1).getRole(),usi.getRoleById(2).getRole()});
        return "createuser";
    }
    @RequestMapping(value="/createuser", method=RequestMethod.POST)
    public String getCreateuserPagePost(Model model, @RequestParam(value="role") String role, @RequestParam(value="name") String name, @RequestParam(value="password") String password, HttpServletResponse resp) throws SQLException {
        Set<Role> userRole = new HashSet<>();
        if(role.equals(usi.getRoleById(1).getRole())) {
            userRole = Collections.singleton(usi.getRoleById(1));
        } else if(role.equals(usi.getRoleById(2).getRole())) {
            userRole = Collections.singleton(usi.getRoleById(2));
        }
        User u = new User(name,password,userRole);
        usi.saveUser(u);
        return "redirect:/admin";
    }







    @RequestMapping(value = "/updateuser", method = RequestMethod.GET)
    public String getUpdateuserPagePost(Model model, @RequestParam(value="user_id") String user_id) throws SQLException {
        int id;
        String role = null;
        try {
            id = Integer.parseInt(user_id);
            User us = usi.get(id);
            role = ((Role)(us.getRoles().toArray()[0])).getRole();
            model.addAttribute("us",us);
        } catch(Throwable throwable) {
            System.out.println("ERROR::id = Integer.parseInt(user_id)::"+throwable.toString()+"::::user_id::"+user_id);
        }

        List<String[]> rolesList = new ArrayList<String[]>();
        if(role.equals(usi.getRoleById(1).getRole())) {
            rolesList.add(new String[]{usi.getRoleById(1).getRole(),usi.getRoleById(2).getRole()});
        } else if(role.equals(usi.getRoleById(2).getRole())) {
            rolesList.add(new String[]{usi.getRoleById(2).getRole(),usi.getRoleById(1).getRole()});
        }
        model.addAttribute("rolesList",rolesList);
        return "updateuser";
    }
    @RequestMapping(value="/updateuser", method=RequestMethod.POST)
    public String getUpdateuserPagePost(Model model, @RequestParam(value="role") String role, @RequestParam(value="name") String name, @RequestParam(value="password") String password, @RequestParam(value="id") String id) throws SQLException {
        int idd;
        Set<Role> userRole = new HashSet<>();
        String hql = "FROM Role WHERE role = :rollle";
        Query q = entityManager.createQuery(hql);
        q.setParameter("rollle", role);
        Role ris = (Role) q.getSingleResult();
        userRole.add(ris);
        try {
            idd = Integer.parseInt(id);
            int ins = 0;
            User user = new User();
            user.setId_user(idd);
            user.setName(name);
            user.setPassword(password);
            Set<Role> roleSet = Collections.singleton((usi.getDao()).getRole(role));
            user.setRoles(roleSet);
            usi.updateUser(user);
            return "redirect:/admin";
        } catch(Throwable throwable) {
            System.out.println("ERROR::id = Integer.parseInt(req.getParameter(\"idd\"))::"+throwable.toString());
        }
        return null;
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String getUpdateuserPageGet() {
        return "login";
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println("userDetail::::"+userDetail);
            model.addObject("username", userDetail.getUsername());
        }
        model.setViewName("403");
        return model;

    }
}