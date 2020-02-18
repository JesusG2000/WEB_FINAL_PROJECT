<%@ page import="bean.dto.Dialog" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: fpfpf
  Date: 01.02.2020
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Dialogs</title>
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

<c:forEach var="dialog" items="${dialogs}">
    <form action="/welcome" method="post">
        <input type="hidden" name="otherUserId" value="${dialog.otherUser.id}">
        <input type="hidden"  name="command" value="dialog_page">
        <input type="submit"  value="${dialog.otherUser.name}">
    </form>
</c:forEach>

<form action="/welcome" method="post">
    <p><input type="search" name="name" placeholder=<fmt:message key="allDialogs.writeName"/>>
        <input type="hidden"  name="command" value="add_dialog">
        <input type="submit" value="<fmt:message key="allDialogs.add"/>"></p>
</form>

<form action="/welcome" method="post">
    <p><input type="search" name="name" placeholder="<fmt:message key="allDialogs.writeName"/>">
        <input type="hidden"  name="command" value="delete_dialog">
        <input type="submit" value="<fmt:message key="allDialogs.delete"/>"></p>
</form>

<form action="/welcome" method="post">
    <input type="hidden" name="command" value="profile_page">
    <input type="submit" value="<fmt:message key="allDialogs.profile"/>">
</form>

</body>
</html>
