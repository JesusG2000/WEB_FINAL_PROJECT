<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<fmt:setLocale value="${sessionScope.locale}" scope="session"/>--%>
<%--<fmt:setBundle basename="locale" var="bundle"/>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form id="loginForm" method="post" action="/welcome">
    <label>Username: <input type="text" required placeholder="enter your username" name="name"> </label> <br>
    <label>Password: <input type="password" required placeholder="enter your password" name="password"></label> <br>
    <input type="hidden" name="command" value="login">
    <input type="submit" value="login">

</form>

<form id="something" method="post" action="/welcome">
    <input type="hidden" name="command" value="registration_page">
    <input type="submit" value="registration">
</form>

<p>
    <c:if test="${message !=null}">
        <c:out value="${message}"/>
    </c:if>
</p>
</body>
</html>
