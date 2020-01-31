<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${vacancy.name}</title>
</head>
<body>

<form action="/updateVacancy" method="post">
    Vacancy id
    <p><textarea rows="1" name="id" cols="5" readonly>${vacancy.id}</textarea></p>
    Vacancy name
    <p><textarea rows="1" cols="20" name="name">${vacancy.name}</textarea></p>
    Vacancy description
    <p><textarea rows="10" cols="45" name="description">${vacancy.description}</textarea></p>
    <c:forEach var="user" items="${users}">
        <c:out value="${user.id}"/>
        <c:out value="${user.name}"/>
        <c:out value="${user.password}"/>
        <c:out value="${user.role}"/>
    </c:forEach>
    <p><input type="submit" value="update"></p>
</form>
</body>
</html>
