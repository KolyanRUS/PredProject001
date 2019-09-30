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
            <th>User_name</th>
            <th>User_login</th>
            <th>User_password</th>
            <th>Button for removing</th>
            <th>Button for updating</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.getId()}" /></td>
                <td><c:out value="${user.getUsername()}" /></td>
                <td><c:out value="${user.getLogin()}" /></td>
                <td><c:out value="${user.getPassword()}" /></td>
                <td>
                    <form action="/admin" method="post">
                        <c:set var="userid" scope="application" value="${user.getId()}"/>
                        <input type="submit" name="ButtonName" value="${user.getId()}"/>
                    </form>
                </td>
                <td>
                    <form action="/updateuser" method="get">
                        <input type="hidden" name="user_id" value="${user.getId()}"/>
                        <input type="submit" name="ButtonName" value="Update User"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href = "/createuser">Create_User</a>
</div>
</body>
</html>