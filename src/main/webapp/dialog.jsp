<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<form>

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
    <p><textarea rows="2" cols="45" name="message" required placeholder="enter your message"></textarea></p>
    <input type="hidden" name="ownUserId" value="${finalDialog.ownUser.id}">
    <input type="hidden" name="otherUserId" value="${finalDialog.otherUser.id}">
    <input type="hidden" name ="command" value="dialog">
    <input type="submit" value="send">
</form>
<form action="/welcome" method="get">
    <input type="hidden" name ="command" value="all_dialogs_page">
    <input type="submit" value="dialogs">
</form>
</body>
</html>
