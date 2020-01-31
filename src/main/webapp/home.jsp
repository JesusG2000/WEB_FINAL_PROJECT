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

<c:choose>
    <c:when test="${currentUser.role eq 'HR'}">


        <form action="/updateVacancy" method="get">
            <input type="hidden" name="id" value="${vacancy.id}">
            <input type="submit" value="update">
        </form>
        <form action="/deleteVacancy" method="post">
            <input type="hidden" name="id" value="${vacancy.id}">
            <input type="submit" value="delete">
        </form>

    </c:when>
    <c:when test="${currentUser.role eq 'SEEKER'}">
        <form action="/subVacancy" method="post">
            <input type="hidden" name="vacId" value="${vacancy.id}">
            <input type="hidden" name="userId" value="${currentUser.id}">
            <input type="submit" value="subscribe">
        </form>
    </c:when>
</c:choose>

<p>
    </c:forEach>

    <c:if test="${currentUser.role eq 'HR'}">
    <a href="/addVacancy">
        <button>add vacancy</button>
    </a>
    </c:if>

</body>
</html>
