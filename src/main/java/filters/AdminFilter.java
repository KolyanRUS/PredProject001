package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter("/admin/*")//
// ФИЛЬТР ДЛЯ АДМИНА (ЕСЛИ РОЛЬ АВТОРИЗОВАННОГО ПОЛЬЗОВАТЕЛЯ НЕ АДМИН, ТО ЗАПРЕТЫ [НА РЕДАКТИРОВАНИЕ, УДАЛЕНИЕ, ПРНОСМОТР])
public class AdminFilter implements Filter {
    /**
     * Конструктор по умолчанию
     */
    public AdminFilter()
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
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession httpsession = req.getSession();
        String autorization = (String)httpsession.getAttribute("autorization");
        String role = (String)httpsession.getAttribute("role");
        if("user".equals(role)) {//если авторизован НЕ админ
            System.out.println("IF_FFF_FFF::"+req.getRequestURI());
            ((HttpServletResponse)response).sendRedirect("/user");
            return;
        }
        filterChain.doFilter(request, response);
    }
}