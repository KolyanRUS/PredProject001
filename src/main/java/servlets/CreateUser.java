package servlets;

import dbService.UserServiceImple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="CreateUser",urlPatterns = "/createuser")
public class CreateUser extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        try {
            UserServiceImple.getInstance().insertUser(name,password,login);
        } catch (Throwable throwable) {
            //ignore
        }
        getServletContext().getRequestDispatcher("/start").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/createuserform.jsp").forward(req, resp);
    }
}