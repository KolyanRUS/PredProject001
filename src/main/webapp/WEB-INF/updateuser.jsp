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
        <p><small>Id</small>
            <small>${us.getId()}</small>
            <input type="hidden" name="id" value="${us.getId()}"/>
        </p><%----%>
        <p><small>Name</small>
            <input type="text" name="name" value="${us.getName()}" size="5" />
        </p>
        <p><small>Login</small>
            <input type="text" name="login" value="${us.getLogin()}" size="5" />
        </p>
        <p><small>Password</small>
            <input type="text" name="password" value="${us.getPassword()}" size="5" />
        </p>
        <input type="submit" name="ButtonName" value="Update_User"/>
    </form>
</div>
</body>
</html>