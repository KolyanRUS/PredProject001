package servlets;

import dbService.UserServiceImple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(name="DeleteUser",urlPatterns = "/deleteuser")
public class DeleteUser extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonName = req.getParameter("ButtonName");
        if (buttonName.equals("Remove user with this number")) {
            try{
                int number = Integer.parseInt(req.getParameter("number"));
                UserServiceImple.getInstance().deleteId(number);
            } catch (Throwable throwable) {
                //ignore
            }
        } else {
            try{
                UserServiceImple.getInstance().cleanTable();
            } catch (Throwable throwable) {
                //ignore
                try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                    writer.write("Delete_all_users не удалось :("+"\r\n");
                    writer.flush();
                }
                catch (IOException ex) {
                    //ignore
                }
            }
        }
        /*if (buttonName.equals("Delete_all_users")) {
            try{
                UserServiceImple.getInstance().cleanTable();
            } catch (Throwable throwable) {
                //ignore
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
                int number = Integer.parseInt(req.getParameter("number"));
                UserServiceImple.getInstance().deleteId(number);//
            } catch (Throwable throwable) {
                //ignore
            }
        }*/
        getServletContext().getRequestDispatcher("/deleteuserform.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/deleteuserform.jsp").forward(req, resp);
    }
}