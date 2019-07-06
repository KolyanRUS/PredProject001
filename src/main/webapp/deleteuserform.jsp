<%--
  Created by IntelliJ IDEA.
  User: Kolyan1998
  Date: 16.06.2019
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="dao.UsersDAOImple" %>
<%@ page import="model.User" %>
<%@ page import="executor.Executor" %>
<%@ page import="java.lang.String"%>
<%@ page import="servlets.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Delete_User</title>
</head>
<body>
<div>
    <a href="/start">Start_Menu</a>
</div>
<div>
    <form action="deleteuser" method="post">
        <input type="submit" name="ButtonName" value="Delete_all_users"/>
    </form>
</div>
<div>
    <form action="deleteuser" method="post">
        <p><small>User number</small>
            <input type="text" name="number" value="" size="5" />
        </p>
        <input type="submit" name="ButtonName" value="Remove user with this number"/>
    </form>
</div>
</body>
</html>