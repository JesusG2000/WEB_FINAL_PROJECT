<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp" />
<html>
<head>
    <title>Title</title>
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
<div class="container row">
    <div class="col-lg-4">
        <p>
        <fmt:message key="interviewPage.hrName"/> - <c:out value="${hr.name}"/><p>
        <fmt:message key="interviewPage.vacancyName"/> - <c:out value="${vacancy.name}"/><p>
        <fmt:message key="interviewPage.vacancyDescription"/> - <c:out value="${vacancy.description}"/><p>

        <form action="/welcome" method="post">
            <input type="hidden" name="hrId" value="${hr.id}">
            <input type="hidden" name="seekerId" value="${seeker.id}">
            <label><fmt:message key="interviewPage.date"/> - <input required type="date" name="date"></label>
            <p><textarea required rows="2" cols="45" name="comment"></textarea></p>
            <input type="hidden" name="vacancyId" value="${vacancy.id}">
            <input type="hidden" name="command" value="submit_interview">
            <input type="submit" value="<fmt:message key="interviewPage.submit"/>">
        </form>

    </div>
    <div class="col-lg-8">
        <p>
            <fmt:message key="interviewPage.seekerNickname"/> - <c:out value="${seeker.name}"/><p>
        <fmt:message key="resume.name"/> - <c:out value="${resume.userName}"/><p>
        <fmt:message key="resume.surname"/> - <c:out value="${resume.userSurname}"/><p>
        <fmt:message key="resume.age"/> - <c:out value="${resume.userAge}"/><p>
        <fmt:message key="resume.language"/> - <c:out value="${resume.userLanguage}"/><p>
        <fmt:message key="resume.work.experience"/> - <c:out value="${resume.userWorkExperience}"/><p>
        <fmt:message key="resume.extra.info"/> - <c:out value="${resume.userInfo}"/><p>
    </div>
</div>


</body>
</html>
