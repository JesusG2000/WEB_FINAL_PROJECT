<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
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

    <fmt:message key="interviewPage.hrName"/> - <c:out value="${hr.name}"/><p>
    <fmt:message key="interviewPage.seekerName"/> - <c:out value="${seeker.name}"/><p>
    <fmt:message key="interviewPage.vacancyName"/> - <c:out value="${vacancy.name}"/><p>
    <fmt:message key="interviewPage.vacancyDescription"/> - <c:out value="${vacancy.description}"/><p>

<form action="/welcome" method="post">
    <input type="hidden" name="hrId" value="${hr.id}">
    <input type="hidden" name="seekerId" value="${seeker.id}">
    <label>date<input required type="date" name="date"></label>
    <p><textarea required rows="2" cols="45" name="comment"></textarea></p>
    <input type="hidden" name="vacancyId" value="${vacancy.id}">
    <input type="hidden" name="command" value="submit_interview">
    <input type="submit" value="<fmt:message key="interviewPage.submit"/>">
</form>

<a href="/welcome?id=${vacancy.id}&command=update_vacancy_page"><fmt:message key="interviewPage.back"/></a>

</body>
</html>
