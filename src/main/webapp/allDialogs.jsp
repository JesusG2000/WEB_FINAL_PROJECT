<%@ page import="bean.dto.Dialog" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>

<c:forEach var="dialog" items="${dialogs}">
    <form action="/welcome" method="get">
        <input type="hidden" name="otherUserId" value="${dialog.otherUser.id}">
        <input type="hidden"  name="command" value="dialog_page">
        <input type="submit"  value="${dialog.otherUser.name}">
    </form>
</c:forEach>

<form action="/welcome" method="post">
    <p><input type="search" name="name" placeholder="Write name">
        <input type="hidden"  name="command" value="add_dialog">
        <input type="submit" value="add to dialogs"></p>
</form>

<form action="/welcome" method="post">
    <p><input type="search" name="name" placeholder="Write name">
        <input type="hidden"  name="command" value="delete_dialog">
        <input type="submit" value="delete from dialog"></p>
</form>

<form action="/welcome" method="post">
    <input type="hidden" name="command" value="profile_page">
    <input type="submit" value="profile">
</form>

</body>
</html>
