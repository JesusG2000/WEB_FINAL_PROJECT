<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
HR name - <c:out value="${hr.name}"/><p>
Seeker name - <c:out value="${seeker.name}"/><p>
Vacancy name - <c:out value="${vacancy.name}"/><p>
Vacancy description - <c:out value="${vacancy.description}"/><p>

<form action="/welcome" method="post">
    <input type="hidden" name="hrId" value="${hr.id}">
    <input type="hidden" name="seekerId" value="${seeker.id}">
    <label>date<input required type="date" name="date"></label>
    <p><textarea required rows="2" cols="45" name="comment"></textarea></p>
    <input type="hidden" name="vacancyId" value="${vacancy.id}">
    <input type="hidden" name="command" value="submit_interview">
    <input type="submit" value="submit">
</form>

<a href="/welcome?id=${vacancy.id}&command=update_vacancy_page">back</a>

</body>
</html>
