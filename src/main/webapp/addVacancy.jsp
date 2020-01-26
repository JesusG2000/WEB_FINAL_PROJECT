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
<form id="addVacancyForm" method="post" action="/addVacancy">
    <label>Name: <input type="text" minlength="4"  required placeholder="enter your name" name="name"> </label> <br>
    <label>Description: <input type="text" minlength="4"  required placeholder="enter your description" name="description"></label> <br>
    <input type="submit" value="add">


</form>
</body>
</html>
