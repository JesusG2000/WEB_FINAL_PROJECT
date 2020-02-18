<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title><c:out value="${user.name}"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <style>
        .brd {
            border: 4px double black; /* Параметры границы */
            background: #fc3; /* Цвет фона */
            padding: 10px; /* Поля вокруг текста */
            width: 30%;
        }</style>
</head>
<body>
<c:if test="${locale eq 'en' or locale eq null}">
    <fmt:setLocale value="en"/>
    <fmt:setBundle basename="text"/>
</c:if>

<c:if test="${locale eq 'ru'}">
    <fmt:setLocale value="ru"/>
    <fmt:setBundle basename="text"/>
</c:if>


<div>
    <fmt:message key="profile.user.id"/> = <c:out value="${user.id}"/><br>
    <fmt:message key="profile.user.name"/>= <c:out value="${user.name}"/><br>
    <fmt:message key="profile.user.role"/> = <c:out value="${user.role}"/><br>
</div>

<c:forEach var="i" items="${interview}">
<div class="brd">
    <fmt:message key="profile.hr.id"/> - <c:out value="${i.hrId}"/><p>
        <fmt:message key="profile.Date"/> - <c:out value="${i.date}"/><p>
        <fmt:message key="profile.vacancy.id"/> - <c:out value="${i.vacancyId}"/><p>
        <fmt:message key="profile.comment"/> - <c:out value="${i.comment}"/><p>
</div>
<p>

    </c:forEach>
    <c:if test="${user.role eq 'SEEKER'}">
<form action="/welcome" method="post">
    <input type="hidden" name="command" value="profile_page">
    <input type="hidden" name="start" value=${start+count}>
    <input type="hidden" name="finish" value=${finish+count}>
    <input class="btn btn-warning" type="submit" value=" <fmt:message key="profile.next"/> ">
</form>

<form action="/welcome" method="post">
    <input type="hidden" name="command" value="profile_page">
    <input type="hidden" name="start" value=${start-count}>
    <input type="hidden" name="finish" value=${finish-count}>
    <input class="btn btn-warning" type="submit" value="<fmt:message key="profile.prev"/>">
</form>
</c:if>
<form action="/welcome" method="post">
    <input type="hidden" name="command" value="home_page">
    <input class="btn btn-info" type="submit" value="<fmt:message key="profile.home"/>">
</form>

<p>

    <c:if test="${user.role eq 'ADMIN'}">
<form action="/welcome" method="post">
    <input type="hidden" name="command" value="admin_home_page">
    <input class="btn btn-info" type="submit" value="<fmt:message key="profile.adminHome"/>">
</form>
</c:if>

<form action="/welcome" method="post">
    <input type="hidden" name="command" value="all_dialogs_page">
    <input class="btn btn-secondary" type="submit" value="<fmt:message key="profile.dialogs"/>">
</form>

<form action="/welcome" method="post">
    <input type="hidden" name="command" value="logout">
    <input class="btn btn-danger" type="submit" value="<fmt:message key="profile.logout"/>">
</form>

<form action="/welcome" method="post">
    <input type="hidden" name="command" value="change_language">
    <input type="hidden" name="language" value="en">
    <input class="btn btn-primary" type="submit" value="<fmt:message key="profile.en"/>">
</form>

<form action="/welcome" method="post">
    <input type="hidden" name="command" value="change_language">
    <input type="hidden" name="language" value="ru">
    <input class="btn btn-primary" type="submit" value="<fmt:message key="profile.ru"/>">
</form>


</body>
</html>
