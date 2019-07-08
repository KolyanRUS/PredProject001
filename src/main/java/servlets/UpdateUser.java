package servlets;
import dbService.UserServiceImple;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
@WebServlet(name="UpdateUser",urlPatterns = "/updateuser")
public class UpdateUser extends HttpServlet{
    UserServiceImple usi = UserServiceImple.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        int id = -1;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(Throwable throwable) {
            //ignore
        }
        try {
            usi.updateId(id,name,login,password);
        } catch (Throwable throwable) {
            //ignore
        }
        resp.sendRedirect("/start");
        //getServletContext().getRequestDispatcher("/start.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/updateuserform.jsp").forward(req, resp);
    }
}