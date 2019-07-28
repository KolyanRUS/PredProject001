<%@ page import="dao.UserDaoJDBCimpl" %>
<%@ page import="model.User" %>
<%@ page import="executor.Executor" %>
<%@ page import="java.lang.String"%>
<%@ page import="servlets.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Update_User</title>
</head>
<body>
<div>
    <a href="/admin">Admin_Menu</a>
    <a href="/user">User_Menu</a>
    <%--><c:forEach var="rolle" items="${rolesList}">
        <a href="/user"><c:out value="${rolle.getRole()}" /></a>
    </c:forEach><--%>
</div>
<div>
    <form action="/admin/updateuser" method="post">
        <p><small>Role selection</small>
            <%-->
            <c:if test="${param.age > 24}">
                Возраст более 24 лет
            </c:if>
            <--%>
            <c:forEach var="rolle" items="${rolesList}">
                <select name="role">
                    <option selected="selected" value="${rolle[0]}">${rolle[0]}</option>
                    <option value="${rolle[1]}">${rolle[1]}</option>
                </select>
            </c:forEach></p>
        <p><small>Id</small>
            <small>${user_id}</small>
            <input type="hidden" name="id" value="${user_id}"/>
        </p><%----%>
        <p><small>Name</small>
            <input type="text" name="name" value="${user_name}" size="5" />
        </p>
        <p><small>Login</small>
            <input type="text" name="login" value="${user_login}" size="5" />
        </p>
        <p><small>Password</small>
            <input type="text" name="password" value="${user_password}" size="5" />
        </p>
        <input type="submit" name="ButtonName" value="Update_User"/>
    </form>
</div>
</body>
</html>