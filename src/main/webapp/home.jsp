
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title> Tag Example</title>
</head>
<body>


<c:forEach var="vacancy" items="${vacancies}">
    <c:out value="${vacancy.name}"/>
    <form action="/updateVacancy" method="get">
    <input type="hidden" name = "id" value="${vacancy.id}">
    <input type="submit" value="update">
    </form>
    <form action="/deleteVacancy" method="post">
    <input type="hidden" name = "id" value="${vacancy.id}">
    <input type="submit" value ="delete">
    </form>
    <p>
</c:forEach>


<a href="/addVacancy">
    <button>add vacancy</button>
</a>

</body>
</html>
