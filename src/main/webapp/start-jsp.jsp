<%@ page import="dao.UserDaoJDBCimpl" %>
<%@ page import="model.User" %>
<%@ page import="executor.Executor" %>
<%@ page import="java.lang.String"%>
<%@ page import="servlets.*"%>
<%@ page import="servlets.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Start</title>
</head>
<body>
<div>
    <form action="/start" method="post">
        <p><small>Login</small>
            <input type="text" name="login" value="" size="5" />
        </p>
        <p><small>Password</small>
            <input type="text" name="password" value="" size="5" />
        </p>
        <input type="submit" name="ButtonName" value="log in"/>
    </form>
</div>
</body>
</html>