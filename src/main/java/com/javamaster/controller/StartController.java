package com.javamaster.controller;

import com.javamaster.service.UserServiceImple;
import com.javamaster.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class StartController {

    @Autowired
    private UserServiceImple usi;


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
        return "createuser";
    }
    @RequestMapping(value="/createuser", method=RequestMethod.POST)
    public String getCreateuserPagePost(Model model, @RequestParam(value="name") String name, @RequestParam(value="password") String password, @RequestParam(value="login") String login, HttpServletResponse resp) throws SQLException {
        usi.insertUser(name,password,login);
        return "redirect:/admin";
    }







    @RequestMapping(value="/updateuser", method=RequestMethod.GET)
    public String getUpdateuserPageGet(Model model, @RequestParam(value="user_id") String user_id) {
        int id = -1;
        try {
            id = Integer.parseInt(user_id);
            User us = usi.get(id);
            model.addAttribute("us",us);
        } catch(Throwable throwable) {
            System.out.println("ERROR::id = Integer.parseInt(user_id)::"+throwable.toString()+"::::user_id::"+user_id+"::id::"+id);
        }

        List<String[]> rolesList = new ArrayList<String[]>();
        rolesList.add(new String[]{"admin","user"});
        model.addAttribute("rolesList",rolesList);
        return "updateuser";
    }
    @RequestMapping(value="/updateuser", method=RequestMethod.POST)
    public String getUpdateuserPagePost(Model model, @RequestParam(value="name") String name, @RequestParam(value="password") String password, @RequestParam(value="login") String login, @RequestParam(value="id") String id, HttpServletResponse resp) throws SQLException {
        int idd = -1;
        try {
            idd = Integer.parseInt(id);
            usi.updateId(idd,name,login,password);
            return "redirect:/admin";
        } catch(Throwable throwable) {
            System.out.println("ERROR::id = Integer.parseInt(req.getParameter(\"idd\"))::"+throwable.toString());
        }
        return null;
    }
}