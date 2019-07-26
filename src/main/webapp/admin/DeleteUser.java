import dbService.UserServiceImple;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="DeleteUser",urlPatterns={"/admin/deleteuser"})
public class DeleteUser extends HttpServlet{
    UserServiceImple usi = UserServiceImple.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//usi.deleteId(number);
        int id = -1;
        try {
            id = Integer.parseInt(req.getParameter("ButtonName"));
            usi.deleteId(id);
        } catch(Throwable throwable) {
            System.out.println("Throwable [Integer.parseInt(req.getParameter(\"id\"))]: "+throwable.toString());
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = new ArrayList<User>();
        try {
            userList = usi.getListUsers();
        } catch (Throwable throwable) {
            System.out.println("Throwable [usi.getListUsers()]: "+throwable.toString());
        }
        req.setAttribute("users", userList);
        getServletContext().getRequestDispatcher("/start-jsp.jsp").forward(req, resp);
    }
}