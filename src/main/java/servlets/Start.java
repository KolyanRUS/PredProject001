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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("ButtonName").equals("Delete_All_Users")) {
            try{
                UserServiceImple.getInstance().cleanTable();
            } catch (Throwable throwable) {
                try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                    writer.write("Delete_all_users не удалось :("+"\r\n");
                    writer.flush();
                }
                catch (IOException ex) {
                    //ignore
                }
            }
        } else {
            try{
                int number = Integer.parseInt(req.getParameter("ButtonName").substring(12));
                UserServiceImple.getInstance().deleteId(number);
            } catch (Throwable throwable) {
                try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                    writer.write("|-||"+throwable.toString()+"||-|"+"\r\n");
                    writer.flush();
                }
            }
        }
        this.doGet(req,resp);
        //getServletContext().getRequestDispatcher("/start.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = new ArrayList<User>();
        try {
            userList = UserServiceImple.getInstance().getListUsers();
        } catch (Throwable throwable) {
            //ignore
        }
        req.setAttribute("users", userList);
        getServletContext().getRequestDispatcher("/start.jsp").forward(req, resp);
    }
    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //UsersDAOImple.tree();
        getServletContext().getRequestDispatcher("/start.jsp").forward(req, resp);
    }*/
}