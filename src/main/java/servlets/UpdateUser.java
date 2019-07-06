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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        int id = -1;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(Throwable throwable) {
            try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                writer.write("---"+throwable.toString()+"\r\n"+"\r\n");
                throwable.printStackTrace();
                writer.flush();
            }
            catch (IOException ex) {
                //ignore
            }
            //ignore
        }
        try {
            UserServiceImple.getInstance().updateId(id,name,login,password);
        } catch (Throwable throwable) {
            try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                writer.write("UpdateUser_UserServiceImple.getInstance().updateId(id,name,login,password) не сработало"+"\r\n"+"___ошибка "+throwable.toString()+"\r\n");
                writer.flush();
            }
            //ignore
        }
        //Start.doGet(req,resp);
        getServletContext().getRequestDispatcher("/start.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        getServletContext().getRequestDispatcher("/updateuserform.jsp").forward(req, resp);
    }
}