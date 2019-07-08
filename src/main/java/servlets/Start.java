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
        int id = -1;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(Throwable throwable) {
            //ignore
        }
        if(req.getParameter("ButtonName").equals("Delete_All_Users")) {
            try{
                usi.cleanTable();
            } catch (Throwable throwable) {
            }
        } else {
            try{
                int number = Integer.parseInt(req.getParameter("ButtonName").substring(12));
                usi.deleteId(number);
            } catch (Throwable throwable) {
                //ignore
            }
        }
        this.doGet(req,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = new ArrayList<User>();
        try {
            userList = usi.getListUsers();
        } catch (Throwable throwable) {
            try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                writer.write("\r\n"+"throwable: "+throwable.toString()+"\r\n");
                writer.flush();
            }
            //ignore
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