<%--
  Created by IntelliJ IDEA.
  User: fpfpf
  Date: 07.01.2020
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<form id="addVacancyForm" method="post" action="/welcome">
    <label>Name: <input type="text" minlength="4"  required placeholder="enter your name" name="name"> </label> <br>
    <label>Description: <input type="text" minlength="4"  required placeholder="enter your description" name="description"></label> <br>
    <input type="hidden" name = "command" value="add_vacancy">
    <input type="submit" value="add">
</form>
<form action="/welcome" method="get">
    <input type="hidden" name="command" value="home_page">
    <input type="submit" value="home">
</form>

</body>
</html>
