<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Update_User</title>
</head>
<body>
<div>
    <a href="/admin">Admin_Menu</a>
</div>
<div>
    <form action="/updateuser" method="post">
        <p><small>Role selection</small>
            <select name="role">
                <option selected="selected" value="${rolesArray[0]}">${rolesArray[0]}</option>
                <option value="${rolesArray[1]}">${rolesArray[1]}</option>
            </select></p>
        <p><small>Id</small>
            <small>${us.getId_user()}</small>
            <input type="hidden" name="id" value="${us.getId_user()}"/>
        </p><%----%>
        <p><small>Name</small>
            <input type="text" name="name" value="${us.getUsername()}" size="5" />
        </p>
        <p><small>Password</small>
            <input type="text" name="password" value="${us.getPassword()}" size="5" />
        </p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" name="ButtonName" value="Update_User"/>
    </form>
</div>
</body>
</html>