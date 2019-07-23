package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter("/*")//
// ФИЛЬТР ДЛЯ АДМИНА (ЕСЛИ РОЛЬ АВТОРИЗОВАННОГО ПОЛЬЗОВАТЕЛЯ НЕ АДМИН, ТО ЗАПРЕТЫ [НА РЕДАКТИРОВАНИЕ, УДАЛЕНИЕ, ПРНОСМОТР])
public class AdminFilter implements Filter {
    private FilterConfig filterConfig;
    private static ArrayList<String> pages;  // хранилище страниц
    /**
     * Конструктор по умолчанию
     */
    public AdminFilter()
    {
        // Создание хранилища страниц
        if (pages == null)
            pages = new ArrayList<String>();
    }
    /**
     * Метод фильтрации
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession httpsession = req.getSession();
        String autorization = (String)httpsession.getAttribute("autorization");
        String role = (String)httpsession.getAttribute("role");
        if(("true".equals(autorization))&&("user".equals(role))) {//если авторизован НЕ админ
            System.out.println("IF_FFF_FFF");
            String uri = req.getRequestURI();
            if ((uri.toLowerCase().indexOf("createuser") != -1)||(uri.toLowerCase().indexOf("deleteuser") != -1)||(uri.toLowerCase().indexOf("updateuser") != -1)||(uri.toLowerCase().indexOf("admin") != -1)) {
                ((HttpServletResponse)response).sendRedirect("/user");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
