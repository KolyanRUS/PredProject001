package com.javamaster.controller;

import dbService.UserServiceImple;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StartController {
    UserServiceImple usi = UserServiceImple.getInstance();
    static {
        try {
            UserServiceImple.getInstance().insertUser("admin","admin", "admin", "admin");
            UserServiceImple.getInstance().insertUser("user","user", "user", "user");
        } catch (Throwable throwable) {
            System.out.println("ERROR::Start_static::"+throwable.toString());
        }
    }


    @RequestMapping(value="/start", method=RequestMethod.GET)
    public String getStartPageG(/*Model model*/) {
        return "start";
    }
    @RequestMapping(value="/start", method=RequestMethod.POST)
    public String getStartPageP(Model model, @RequestParam(value="login") String login, @RequestParam(value="password") String password, HttpServletResponse resp) {
        try {
            User us = usi.getUser(login);
            if(us.getPassword().equals(password)) {
                resp.sendRedirect("/admin");
                //return "admin";
                /*if(us.getRole().equals("admin")) {
                    model.addAttribute("autorization","true");
                    //model.addAttribute("role","admin");
                    return "admin";
                } else {
                    model.addAttribute("autorization","true");
                    //model.addAttribute("role","admin");
                    return "user";
                }*/
            }
        } catch (Throwable throwable) {
            System.out.println("ERROR::StartController_doPost()::"+throwable.toString());
        }
        return "start";
    }







    @RequestMapping(value="/admin", method=RequestMethod.GET)
    public String getAdminPageG(Model model) {
        List<User> userList = new ArrayList<User>();
        try {
            userList = usi.getListUsers();
        } catch (Throwable throwable) {
            System.out.println("throwable [usi.getListUsers()]: "+throwable.toString());
        }
        model.addAttribute("users", userList);
        return "admin";
    }
    @RequestMapping(value="/admin", method=RequestMethod.POST)
    public String getAdminPageP(Model model, @RequestParam(value="ButtonName") String butname, HttpServletResponse resp) {
        if(butname.equals("Delete_All_Users")) {
            try{
                usi.cleanTable();
            } catch (Throwable throwable) {
                System.out.println("ERROR::usi.cleanTable()::"+throwable.toString());
            }
        } else {
            try {
                int number = Integer.parseInt(butname);
                usi.deleteId(number);
                resp.sendRedirect("/admin");
            } catch (Throwable throwable) {
                System.out.println("throwable[Admin_doPost_deleteUser]: "+throwable.toString());
            }
        }
        return "admin";
    }







    @RequestMapping(value="/createuser", method=RequestMethod.GET)
    public String getCreateuserPageG(Model model) {
        return "createuser";
    }
    @RequestMapping(value="/createuser", method=RequestMethod.POST)
    public String getCreateuserPageP(Model model, @RequestParam(value="name") String name, @RequestParam(value="password") String password, @RequestParam(value="login") String login, HttpServletResponse resp) {
        try {
            usi.insertUser("admin",name,password,login);
            resp.sendRedirect("/admin");
        } catch (Throwable throwable) {
            System.out.println("throwable [usi.insertUser(name,password,login)]: "+throwable.toString());
        }
        return "admin";
    }







    @RequestMapping(value="/updateuser", method=RequestMethod.GET)
    public String getUpdateuserPageG(Model model, @RequestParam(value="user_id") String user_id) {
        int id = -1;
        try {
            id = Integer.parseInt(user_id);
            User us = usi.get(id);//new User(id,""+usi.get(id).getRole(),""+usi.get(id).getName(),""+usi.get(id).getPassword(),""+usi.get(id).getLogin());
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
    public String getUpdateuserPageP(Model model, @RequestParam(value="name") String name, @RequestParam(value="password") String password, @RequestParam(value="login") String login, @RequestParam(value="id") String id, HttpServletResponse resp) {
        int idd = -1;
        try {
            idd = Integer.parseInt(id);
        } catch(Throwable throwable) {
            System.out.println("ERROR::id = Integer.parseInt(req.getParameter(\"idd\"))::"+throwable.toString());
        }
        try {
            usi.updateId(idd,"admin",name,login,password);
            resp.sendRedirect("/admin");
        } catch (Throwable throwable) {
            System.out.println("ERROR::usi.updateId(id,name,login,password)::"+throwable.toString());
        }
        return "admin";
    }
}