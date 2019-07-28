package servlets;

import dbService.UserServiceImple;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="UpdateUser",urlPatterns = "/admin/updateuser")
public class UpdateUser extends HttpServlet{
    UserServiceImple usi = UserServiceImple.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        int id = -1;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(Throwable throwable) {
            System.out.println("ERROR::id = Integer.parseInt(req.getParameter(\"id\"))::"+throwable.toString());
        }
        try {
            usi.updateId(id,role,name,login,password);
        } catch (Throwable throwable) {
            System.out.println("ERROR::usi.updateId(id,name,login,password)::"+throwable.toString());
        }
        resp.sendRedirect("/admin");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("user_role", req.getParameter("user_role"));

        List<String[]> rolesList = new ArrayList<String[]>();
        if(req.getParameter("user_role").equals("admin")) {
            rolesList.add(new String[]{"admin","user"});
        } else if(req.getParameter("user_role").equals("user")) {
            rolesList.add(new String[]{"user","admin"});
        }
        req.setAttribute("rolesList", rolesList);







        req.getSession().setAttribute("user_id", req.getParameter("user_id"));
        req.getSession().setAttribute("user_name", req.getParameter("user_name"));
        req.getSession().setAttribute("user_login", req.getParameter("user_login"));
        req.getSession().setAttribute("user_password", req.getParameter("user_password"));
        System.out.println("UpdateUser::doGet::req.getParameter(\"user_role\") = "+req.getParameter("user_role"));
        System.out.println("UpdateUser::doGet::req.getParameter(\"user_id\") = "+req.getParameter("user_id"));
        System.out.println("UpdateUser::doGet::req.getParameter(\"user_name\") = "+req.getParameter("user_name"));
        System.out.println("UpdateUser::doGet::req.getParameter(\"user_login\") = "+req.getParameter("user_login"));
        System.out.println("UpdateUser::doGet::req.getParameter(\"user_password\") = "+req.getParameter("user_password"));
        getServletContext().getRequestDispatcher("/admin/updateuserform-jsp.jsp").forward(req, resp);
    }
}