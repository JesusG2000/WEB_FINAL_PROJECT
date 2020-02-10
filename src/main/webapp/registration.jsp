<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration</title>
</head>
<body>
<form id="registrationForm" method="post" action="/welcome">
    <label>Username: <input type="text" minlength="4" maxlength="20" required placeholder="enter your username"
                            name="name"> </label> <br>
    <label>Password: <input type="password" minlength="4" maxlength="20" required placeholder="enter your password"
                            name="password"></label> <br>
    <input type="hidden" name="command" value="registration">
    <input type="submit" value="submit">

    <c:if test="${message !=null}">
        <c:out value="${message}"/>
    </c:if>
</form>

<a href="/login.jsp">back</a>
</body>
</html>
