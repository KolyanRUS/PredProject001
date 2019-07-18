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
</div>
<div>
    <form action="updateuser" method="post">
        <p><small>Role selection</small>
            <select name="role">
                <option selected="selected" value="user">user</option>
                <option value="admin">admin</option>
            </select></p>
        <p><small>Id</small>
            <input type="text" name="id" value="${user__id}" size="5" />
        </p><%----%>
        <p><small>Name</small>
            <input type="text" name="name" value="${user__name}" size="5" />
        </p>
        <p><small>Login</small>
            <input type="text" name="login" value="${user__login}" size="5" />
        </p>
        <p><small>Password</small>
            <input type="text" name="password" value="${user__password}" size="5" />
        </p>
        <input type="submit" name="ButtonName" value="Update_User"/>
    </form>
</div>
</body>
</html>