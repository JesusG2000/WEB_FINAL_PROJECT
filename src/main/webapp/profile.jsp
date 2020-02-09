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

<c:forEach var="i" items="${interview}">
    HR id - <c:out value="${i.hrId}"/><p>
    Date - <c:out value="${i.date}"/><p>
    Vacancy id - <c:out value="${i.vacancyId}"/><p>
    Comment - <c:out value="${i.comment}"/><p>
<p>

    </c:forEach>

<form action="/welcome" method="get">
    <input type="hidden" name="command" value="home_page">
    <input type="submit" value="home">
</form>

<p>

<c:if test="${user.role eq 'ADMIN'}">
  <form action="/welcome" method="get">
    <input type="hidden" name="command" value="admin_home_page">
    <input type="submit" value="admin home">
  </form>
</c:if>

<form action="/welcome" method="get">
    <input type="hidden" name="command" value="all_dialogs_page">
    <input type="submit" value="dialogs">
</form>

<form action="/welcome" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logout">
</form>

</body>
</html>
