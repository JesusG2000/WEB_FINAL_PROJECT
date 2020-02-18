<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title> Tag Example</title>
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


<c:forEach var="vacancy" items="${vacancies}">

<c:out value="${vacancy.name}"/>

<c:choose>
    <c:when test="${currentUser.role eq 'HR'}">


        <form action="/welcome" method="post">
            <input type="hidden" name="id" value="${vacancy.id}">
            <input type="hidden" name="command" value="update_vacancy_page">
            <input type="submit" value="<fmt:message key="home.update"/>">
        </form>
        <form action="/welcome" method="post">
            <input type="hidden" name="id" value="${vacancy.id}">
            <input type="hidden" name="command" value="delete_vacancy">
            <input type="submit" value="<fmt:message key="home.delete"/>">
        </form>

    </c:when>
    <c:when test="${currentUser.role eq 'SEEKER'}">
        <form action="/welcome" method="post">
            <input type="hidden" name="vacId" value="${vacancy.id}">
            <input type="hidden" name="userId" value="${currentUser.id}">
            <input type="hidden" name="command" value="subscribe_to_vacancy">
            <input type="submit" value="<fmt:message key="home.subscribe"/>">
        </form>
    </c:when>
</c:choose>
<p>
    </c:forEach>

    <c:if test="${currentUser.role eq 'HR'}">
<form action="/welcome" method="post">
    <input type="hidden" name="command" value="add_vacancy_page">
    <input type="submit" value="<fmt:message key="home.addVacancy"/>">
</form>
    </c:if>

<form action="/welcome" method="post">
    <input type="hidden" name="command" value="profile_page">
    <input type="submit" value="<fmt:message key="home.profile"/>">
</form>

</body>
</html>
