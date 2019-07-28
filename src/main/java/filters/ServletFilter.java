package filters;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//2 2 ФИЛЬТР ДЛЯ АДМИНА (ЕСЛИ РОЛЬ АВТОРИЗОВАННОГО ПОЛЬЗОВАТЕЛЯ НЕ АДМИН, ТО ЗАПРЕТЫ [НА РЕДАКТИРОВАНИЕ, УДАЛЕНИЕ, ПРНОСМОТР])
/**
 * SimpleServletFilter реализует интерфейс Filter
 */
@WebFilter("/*")//ДЛЯ ВСЕХ АДРЕСОВ ПРОВЕРКА АВТОРИЗАЦИИ И ПЕРЕНАПРАВЛЕНИЕ НА СТАРТ ЕСЛИ НЕАВТОРИЗОВАН
/*
если лог ин не осуществлён, то переход на /старт
 */
public class ServletFilter implements Filter {
    /**
     * Конструктор по умолчанию
     */
    public ServletFilter()
    {
    }
    /**
     * Метод фильтрации
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest httpservletrequest = (HttpServletRequest)request;
        HttpSession httpsession = httpservletrequest.getSession();
        String autorization = (String)httpsession.getAttribute("autorization");
        String role = (String)httpsession.getAttribute("role");
        System.out.println("y===y");
        System.out.println("autorization::"+autorization);
        /*try{
            FileInputStream fstream = new FileInputStream("D:/problems.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
                if(strLine.indexOf("Отказано в доступе по пути")!=-1) {
                    strLine = strLine.replace("Отказано в доступе по пути \"","");
                    strLine = strLine.replace("\".","");
                    file = new File(strLine);
                    if(file.delete()){
                        System.out.println("Файл "+strLine+" был успешно удалён!");
                    } else {
                        System.out.println("Файл "+strLine+" не существует.");
                    }
                }
            }
        } catch (IOException e){
            System.out.println("Ошибка");
        }*/
        System.out.println("uuuffffFFFFFFFF::"+httpservletrequest.getRequestURI());//uuuffffFFFFFFFF::/start
        System.out.println("y===y");//!(role.equals(null))
        //if((!httpservletrequest.getRequestURI().equals("/start"))&&!"true".equals(autorization)) {
        if((!"/start".equals(httpservletrequest.getRequestURI()))&&(role==null)) {
            System.out.println("IFFFFFFFFF::"+httpservletrequest.getRequestURI());
            //httpservletrequest.getSession().setAttribute("autorization", "true");
            ((HttpServletResponse)response).sendRedirect("/start");
            return;
        }
        filterChain.doFilter(request, response);
    }
}