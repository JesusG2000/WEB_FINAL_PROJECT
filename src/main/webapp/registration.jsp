<%--
  Created by IntelliJ IDEA.
  User: fpfpf
  Date: 30.12.2019
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
        <form id="registrationForm" method="post" action="/registration">
            <label>Username: <input type="text" placeholder="enter your username" name="name"> </label> <br>
            <label>Password:  <input type="text" placeholder="enter your password" name="password"></label> <br>
            <input type="submit" value="registration">
        </form>
    </body>
</html>
