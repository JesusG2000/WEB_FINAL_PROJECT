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


        <form action="/welcome" method="get">
            <input type="hidden" name="id" value="${vacancy.id}">
            <input type="hidden" name="command" value="update_vacancy_page">
            <input type="submit" value="update">
        </form>
        <form action="/welcome" method="post">
            <input type="hidden" name="id" value="${vacancy.id}">
            <input type="hidden" name="command" value="delete_vacancy">
            <input type="submit" value="delete">
        </form>

    </c:when>
    <c:when test="${currentUser.role eq 'SEEKER'}">
        <form action="/welcome" method="post">
            <input type="hidden" name="vacId" value="${vacancy.id}">
            <input type="hidden" name="userId" value="${currentUser.id}">
            <input type="hidden" name="command" value="subscribe_to_vacancy">
            <input type="submit" value="subscribe">
        </form>
    </c:when>
</c:choose>
<p>
    </c:forEach>

    <c:if test="${currentUser.role eq 'HR'}">
<form action="/welcome" method="post">
    <input type="hidden" name="command" value="add_vacancy_page">
    <input type="submit" value="add vacancy">
</form>
    </c:if>

<form action="/welcome" method="post">
    <input type="hidden" name="command" value="profile_page">
    <input type="submit" value="profile">
</form>

</body>
</html>
