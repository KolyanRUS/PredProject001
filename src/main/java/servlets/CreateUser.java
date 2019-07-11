package servlets;
import dbService.UserServiceImple;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(name="CreateUser",urlPatterns = "/createuser")
public class CreateUser extends HttpServlet{
    UserServiceImple usi = UserServiceImple.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        try {
            usi.insertUser(name,password,login);
        } catch (Throwable throwable) {
            try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                writer.write("\r\n"+"throwable [usi.insertUser(name,password,login)]: "+throwable.toString()+"\r\n");
                writer.flush();
            }
            //ignore
        }
        resp.sendRedirect("/start");
        //getServletContext().getRequestDispatcher("/start").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/createuserform.jsp").forward(req, resp);
    }
}