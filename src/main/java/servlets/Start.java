package servlets;
import dao.UserDaoJDBCimpl;
import dbService.UserServiceImple;
import model.User;
import util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="Start",urlPatterns={"/start"})
public class Start extends HttpServlet{
    public static boolean rights = false;//true если режим админа
    UserServiceImple usi = UserServiceImple.getInstance();
    static {
        try {
            UserServiceImple.getInstance().insertUser("admin","admin", "admin", "admin");
            UserServiceImple.getInstance().insertUser("user","user", "user", "user");
        } catch (Throwable throwable) {
            System.out.println("ERROR::Start_static::"+throwable.toString());
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        try {
            User us = usi.getUser(login);
            if(us.getPassword().equals(password)) {
                if(us.getRole().equals("admin")) {
                    req.getSession().removeAttribute("autorization");
                    req.getSession().setAttribute("autorization", "true");
                    req.getSession().removeAttribute("role");
                    req.getSession().setAttribute("role", "admin");//rights = true;
                    resp.sendRedirect("/admin");
                    return;
                } else {
                    req.getSession().removeAttribute("autorization");
                    req.getSession().setAttribute("autorization", "true");
                    req.getSession().removeAttribute("role");
                    req.getSession().setAttribute("role", "user");//rights = false;
                    resp.sendRedirect("/user");
                    return;
                }
            }
        } catch (Throwable throwable) {
            System.out.println("ERROR::Start_doPost()::"+throwable.toString());
        }
        this.doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("y==============================y");
        String autorization = (String)req.getSession().getAttribute("autorization");
        System.out.println("AAAutorization::"+autorization);
        System.out.println("y==============================y");
        getServletContext().getRequestDispatcher("/start-jsp.jsp").forward(req, resp);
        System.out.println("y==============================y");
    }
}
/*
try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
            writer.write("12345");
            writer.flush();
        }
        catch (IOException ex) {
            //ignore
        }
 */