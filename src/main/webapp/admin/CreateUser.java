import dbService.UserServiceImple;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(name="CreateUser",urlPatterns = "/admin/createuser")
public class CreateUser extends HttpServlet{
    UserServiceImple usi = UserServiceImple.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        try {
            usi.insertUser(role,name,password,login);
        } catch (Throwable throwable) {
            System.out.println("throwable [usi.insertUser(name,password,login)]: "+throwable.toString());
        }
        resp.sendRedirect("/admin");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/createuserform-jsp.jsp").forward(req, resp);
    }
}