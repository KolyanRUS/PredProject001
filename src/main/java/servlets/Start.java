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

@WebServlet(name="Start",urlPatterns={"/start"})
public class Start extends HttpServlet{
    UserServiceImple usi = UserServiceImple.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("ButtonName").equals("Delete_All_Users")) {
            try{
                usi.cleanTable();
            } catch (Throwable throwable) {
                System.out.println("ERROR::usi.cleanTable()::"+throwable.toString());
            }
        } else {
            try{
                int number = Integer.parseInt(req.getParameter("ButtonName").substring(12));
                usi.deleteId(number);
            } catch (Throwable throwable) {
                System.out.println("throwable[usi.getListUsers()]: "+throwable.toString());
            }
        }
        resp.sendRedirect("/start");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = new ArrayList<User>();
        try {
            userList = usi.getListUsers();
        } catch (Throwable throwable) {
            System.out.println("throwable [usi.getListUsers()]: "+throwable.toString());
        }
        req.setAttribute("users", userList);
        getServletContext().getRequestDispatcher("/start.jsp").forward(req, resp);
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