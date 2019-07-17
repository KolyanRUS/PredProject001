<%--
  Created by IntelliJ IDEA.
  User: Kolyan1998
  Date: 16.06.2019
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="dao.UserDaoJDBCimpl" %>
<%@ page import="model.User" %>
<%@ page import="executor.Executor" %>
<%@ page import="java.lang.String"%>
<%@ page import="servlets.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create_User</title>
</head>
<body>
<div>
    <a href="/start">Start_Menu</a>
</div>
<div>
    <form action="createuser" method="post">
        <p><small>Name</small>
            <input type="text" name="name" value="" size="5" />
        </p>
        <p><small>Login</small>
            <input type="text" name="login" value="" size="5" />
        </p>
        <p><small>Password</small>
            <input type="text" name="password" value="" size="5" />
        </p>
        <input type="submit" name="ButtonName" value="Create_User"/>
    </form>
</div>
</body>
</html>