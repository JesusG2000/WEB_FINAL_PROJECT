<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp" />
<html>
<head>
    <title></title>
</head>
<body>
<script>
    var link = document.querySelector('link[rel=import]');
    var content = link.import.querySelector('#some');
    document.body.appendChild(content.cloneNode(true));
</script>

<c:if test="${locale eq 'en' or locale eq null}">
    <fmt:setLocale value="en"/>
    <fmt:setBundle basename="text"/>
</c:if>

<c:if test="${locale eq 'ru'}">
    <fmt:setLocale value="ru"/>
    <fmt:setBundle basename="text"/>
</c:if>



<c:forEach var="seeker" items="${seekers}">
<c:out value="${seeker.id} - ${seeker.name}- ${seeker.role}"/>
<form action="/welcome" method="post">
    <input type="hidden" name="id" value="${seeker.id}">
    <input type="hidden" name="command" value="delete_user">
    <input type="submit" value="<fmt:message key="profile.delete"/>">

</form>

<p>
    </c:forEach>

    <c:forEach var="hr" items="${hrs}">
        <c:out value="${hr.id} - ${hr.name} - ${hr.role}"/>
<form action="/deleteUser" method="post">
    <input type="hidden" name="id" value="${hr.id}">
    <input type="hidden" name="command" value="delete_user">
    <input type="submit" value="<fmt:message key="profile.delete"/>">
</form>
</c:forEach>

<%--<form action="/welcome" method="post">--%>
<%--    <input type="hidden" name="command" value="profile_page">--%>
<%--    <input type="submit" value="<fmt:message key="adminHome.home"/>">--%>
<%--</form>--%>
</body>
</html>
