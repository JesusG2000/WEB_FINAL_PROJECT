<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: fpfpf
  Date: 02.02.2020
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
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
<form>

    <c:if test="${locale eq 'en' or locale eq null}">
        <fmt:setLocale value="en"/>
        <fmt:setBundle basename="text"/>
    </c:if>

    <c:if test="${locale eq 'ru'}">
        <fmt:setLocale value="ru"/>
        <fmt:setBundle basename="text"/>
    </c:if>

<c:forEach var="message" items="${finalDialog.messages}">
        <c:if test="${finalDialog.ownUser.id ==message.senderId}">
            <c:out value="${finalDialog.ownUser.name}-${message.content}"/>
        </c:if>
        <c:if test="${finalDialog.otherUser.id ==message.senderId}">
            <c:out value="${finalDialog.otherUser.name}-${message.content}"/>
    </c:if><p>
    </c:forEach>

</form>
<form action="/welcome" method="post">
    <p><textarea rows="2" cols="45" name="message" required placeholder=<fmt:message key="dialog.message"/>></textarea></p>
    <input type="hidden" name="ownUserId" value="${finalDialog.ownUser.id}">
    <input type="hidden" name="otherUserId" value="${finalDialog.otherUser.id}">
    <input type="hidden" name ="command" value="dialog">
    <input type="submit" value="<fmt:message key="dialog.send"/>">
</form>
<form action="/welcome" method="post">
    <input type="hidden" name ="command" value="all_dialogs_page">
    <input type="submit" value="<fmt:message key="dialog.dialogs"/>">
</form>
</body>
</html>
