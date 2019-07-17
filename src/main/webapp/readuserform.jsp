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
    <title>Read_User</title>
</head>
<body>
<div>
    <a href="/start">Start_Menu</a>
</div>
<div>
    <form action="readusers" method="post">
        <input type="submit" name="ButtonName" value="Delete_All_Users"/>
    </form>
    <table>
        <tr>
            <th>Id</th>
            <th>User_name</th>
            <th>User_login</th>
            <th>User_password</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="user" items="${users}">
            <%--<p>${user}</p>--%>
            <tr>
                <td><c:out value="${user.getId()}" /></td>
                <td><c:out value="${user.getName()}" /></td>
                <td><c:out value="${user.getLogin()}" /></td>
                <td><c:out value="${user.getPassword()}" /></td>
                <td>
                    <form action="readusers" method="post">
                        <c:set var="userid" scope="application" value="${user.getId()}"/>
                        <input type="submit" name="ButtonName" value="Delete_User#${user.getId()}"/>
                    </form>
                </td>
                <td>
                    <form action="updateuser" method="get">
                        <c:set var="user__id" scope="application" value="${user.getId()}"/>
                        <c:set var="user__name" scope="application" value="${user.getName()}"/>
                        <c:set var="user__login" scope="application" value="${user.getLogin()}"/>
                        <c:set var="user__password" scope="application" value="${user.getPassword()}"/>
                        <input type="submit" name="ButtonName" value="Update_User"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="createuser" method="get">
        <input type="submit" name="ButtonName" value="Create_User"/>
    </form>
</div>
</body>
</html>