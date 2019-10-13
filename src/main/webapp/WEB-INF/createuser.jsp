<%--
  Created by IntelliJ IDEA.
  User: Kolyan1998
  Date: 16.06.2019
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create_User</title>
</head>
<body>
<div>
    <a href="/admin">Admin_Menu</a>
</div>
<div>
    <form action="/createuser" method="post">
        <p><small>Role selection</small>
            <select name="role">
                <option selected="selected" value="ROLE_USER">ROLE_USER</option>
                <option value="ROLE_ADMIN">ROLE_ADMIN</option>
            </select></p>
        <p><small>Name</small>
            <input type="text" name="name" value="" size="5" />
        </p>
        <p><small>Password</small>
            <input type="text" name="password" value="" size="5" />
        </p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" name="ButtonName" value="Create_User"/>
    </form>
</div>
</body>
</html>