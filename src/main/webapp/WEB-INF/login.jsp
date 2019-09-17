<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
    <%--<form action="/login" class="">
        <p><small>Login</small>
            <input type="text" name="login" value="" size="5" />
        </p>
        <p><small>Password</small>
            <input type="text" name="password" value="" size="5" />
        </p>
        <input type="submit" name="ButtonName" value="log in"/>
    </form>--%>
        <form method="post" action="/login" class="form-signin">
            <h2 class="form-heading">Log in</h2>
            <div class="form-group">
                <input name="login" type="text" class="form-control" placeholder="Username"
                       autofocus="true"/>
                <input name="password" type="password" class="form-control" placeholder="Password"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            </div>
        </form>
</div>
</body>
</html>