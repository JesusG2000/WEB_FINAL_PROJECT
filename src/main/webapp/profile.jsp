<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><c:out value="${user.name}"/></title>
</head>
<body>
<div>
    User id = <c:out value="${user.id}"/><br>
    User name = <c:out value="${user.name}"/><br>
    User role = <c:out value="${user.role}"/><br>
</div>
<a href="/home">home</a>
</body>
</html>
