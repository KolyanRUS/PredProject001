package com.javamaster.controller;

import com.javamaster.model.User;
import com.javamaster.model.UserRole;
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

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class StartController {

    @Autowired
    private UserServiceImple usi;


    @RequestMapping(value="/user", method=RequestMethod.POST)
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
        return "createuser";
    }
    @RequestMapping(value="/createuser", method=RequestMethod.POST)
    public String getCreateuserPagePost(Model model, @RequestParam(value="role") String role, @RequestParam(value="name") String name, @RequestParam(value="password") String password, @RequestParam(value="login") String login, HttpServletResponse resp) throws SQLException {
        usi.insertUser(role,name,password,login);
        return "redirect:/admin";
    }







    @RequestMapping(value="/updateuser", method=RequestMethod.GET)
    public String getUpdateuserPageGet(Model model, @RequestParam(value="user_id") String user_id) {
        int id;
        String role = null;
        try {
            id = Integer.parseInt(user_id);
            User us = usi.get(id);
            role = ((UserRole)(us.getAuthorities().toArray()[0])).getRole();
            model.addAttribute("us",us);
        } catch(Throwable throwable) {
            System.out.println("ERROR::id = Integer.parseInt(user_id)::"+throwable.toString()+"::::user_id::"+user_id);
        }

        List<String[]> rolesList = new ArrayList<String[]>();
        if(role.equals("admin")) {
            rolesList.add(new String[]{"admin","user"});
        } else if(role.equals("user")) {
            rolesList.add(new String[]{"user","admin"});
        }
        model.addAttribute("rolesList",rolesList);
        return "updateuser";
    }
    @RequestMapping(value="/updateuser", method=RequestMethod.POST)
    public String getUpdateuserPagePost(Model model, @RequestParam(value="role") String role, @RequestParam(value="name") String name, @RequestParam(value="password") String password, @RequestParam(value="login") String login, @RequestParam(value="id") String id) throws SQLException {
        int idd;
        try {
            idd = Integer.parseInt(id);
            usi.updateId(idd,role,name,login,password);
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
    /*@RequestMapping(value={"/", "/login"}, method=RequestMethod.POST)
    public String getUpdateuserPagePost(Model model, @RequestParam(value="login") String login, @RequestParam(value="password") String password, HttpServletResponse resp) throws SQLException {
        try {
            User us = usi.getUser(login);
            if(us.getPassword().equals(password)) {
                if(((UserRole)(us.getAuthorities().toArray()[0])).getRole().equals("admin")) {
                    //model.addAttribute("autorization", "true");
                    //model.addAttribute("role", "admin");//rights = true;
                    return "redirect:/admin";
                } else {
                    return "redirect:/user";
                }
            }
        } catch (Throwable throwable) {
            System.out.println("ERROR::Start_doPost()::"+throwable.toString());
        }
        return "login";
    }*/





    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }
}