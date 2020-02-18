<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${vacancy.name}</title>
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

<form action="/welcome" method="post">
    Vacancy id
    <p><textarea rows="1" name="id" cols="5" readonly>${vacancy.id}</textarea></p>
    Vacancy name
    <p><textarea rows="1" cols="20" name="name">${vacancy.name}</textarea></p>
    Vacancy description
    <p><textarea rows="10" cols="45" name="description">${vacancy.description}</textarea></p>
       <input type="hidden" name="command" value="update_vacancy">
       <input type="submit" value="<fmt:message key="vacancyInfo.update"/>">
</form>

<c:forEach var="seeker" items="${seekers}">
<c:out value="${seeker.id}"/>
<c:out value="${seeker.name}"/>

<form action="/welcome" method="post">
    <input type="hidden" name="vacId" value="${vacancy.id}">
    <input type="hidden" name="seekerId" value="${seeker.id}">
    <input type="hidden" name="command" value="interview_page">
    <input type="submit" value="<fmt:message key="vacancyInfo.submitInterview"/>">
</form>
<p>
    </c:forEach>

<form action="/welcome" method="post">
    <input type="hidden" name="command" value="home_page">
    <input type="submit" value="<fmt:message key="vacancyInfo.home"/>">
</form>

</body>
</html>
