<%@ page import="dao.UserDaoJDBCimpl" %>
<%@ page import="model.User" %>
<%@ page import="executor.Executor" %>
<%@ page import="java.lang.String"%>
<%@ page import="servlets.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<div>
    <form action="/admin" method="post">
        <input type="submit" name="ButtonName" value="Delete_All_Users"/>
    </form>
    <table>
        <tr>
            <th>Id</th>
            <th>User_role</th>
            <th>User_name</th>
            <th>User_login</th>
            <th>User_password</th>
            <th>Button for removing</th>
            <th>Button for updating</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.getId()}" /></td>
                <td><c:out value="${user.getRole()}" /></td>
                <td><c:out value="${user.getName()}" /></td>
                <td><c:out value="${user.getLogin()}" /></td>
                <td><c:out value="${user.getPassword()}" /></td>
                <td>
                    <form action="/admin" method="post">
                        <c:set var="userid" scope="application" value="${user.getId()}"/>
                        <input type="submit" name="ButtonName" value="${user.getId()}"/>
                    </form>
                </td>
                <td>
                    <form action="/admin/updateuser" method="get">
                        <input type="hidden" name="user_role" value="${user.getRole()}"/>
                        <input type="hidden" name="user_id" value="${user.getId()}"/>
                        <input type="hidden" name="user_name" value="${user.getName()}"/>
                        <input type="hidden" name="user_login" value="${user.getLogin()}"/>
                        <input type="hidden" name="user_password" value="${user.getPassword()}"/>

                        <c:set var="user__role" scope="application" value="${user.getRole()}"/>
                        <c:set var="user__id" scope="application" value="${user.getId()}"/>
                        <c:set var="user__name" scope="application" value="${user.getName()}"/>
                        <c:set var="user__login" scope="application" value="${user.getLogin()}"/>
                        <c:set var="user__password" scope="application" value="${user.getPassword()}"/>

                        <input type="submit" name="ButtonName" value="Update User"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href = "/admin/createuser">Create_User</a>
</div>
</body>
</html>