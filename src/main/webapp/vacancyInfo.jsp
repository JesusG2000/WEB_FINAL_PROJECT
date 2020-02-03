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
    <p><input type="submit" value="update"></p>
</form>

<c:forEach var="seeker" items="${seekers}">
<c:out value="${seeker.id}"/>
<c:out value="${seeker.name}"/>
<c:out value="${seeker.password}"/>
<c:out value="${seeker.role}"/>
<form action="/submitInterview" method="get">
    <input type="hidden" name="vacId" value="${vacancy.id}">
    <input type="hidden" name="seekerId" value="${seeker.id}">
    <input type="submit" value="submit interview">
</form>
<p>
    </c:forEach>

</body>
</html>
