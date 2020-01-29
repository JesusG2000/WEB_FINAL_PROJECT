<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>

</head>
<body>


<c:forEach var="seekers" items="${seekers}">
<c:out value="${seekers}"/>
<form action="/deleteUser" method="post">
    <input type="hidden" name="id" value="${seekers.id}">
    <input type="submit" value="delete">
</form>

<p>
    </c:forEach>

    <c:forEach var="hrs" items="${hrs}">
        <c:out value="${hrs}"/>
<form action="/deleteUser" method="post">
    <input type="hidden" name="id" value="${hrs.id}">
    <input type="submit" value="delete">
</form>
</c:forEach>
</body>
</html>
