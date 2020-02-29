<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html >
<head>
    <title>Title</title>
<%--    <link rel="import" href="../html/bootstrap.html">--%>
    <link rel="stylesheet" href="../css/login.css">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <style>
        body{
            background-image: url("../picture/loginn.jpg") ; /* Цвет фона веб-страницы */
        }
        a {
            color: lightsalmon;
            display:inline-block;
            text-decoration: none;
            font-weight: 400;
        }

        h2 {
            text-align: center;
            font-size: 16px;
            font-weight: 600;
            text-transform: uppercase;
            display:inline-block;
            margin: 40px 8px 10px 8px;
            color: #cccccc;
        }
        img{
            height: 30px;
            width: 2px;
        }
    </style>
</head>
<body>

<fmt:setLocale value="en"/>
<fmt:setBundle basename="text"/>


<div class="wrapper fadeInDown">
    <div id="formContent">
        <form method="post" action="/welcome">
            <input minlength="4" maxlength="16" class="fadeIn second" type="text" required placeholder= <fmt:message key="login.enterName"/> name="name">
            <input minlength="4" maxlength="16" class="fadeIn third" type="password" required placeholder= <fmt:message key="login.enterPassword"/> name="password">
            <input type="hidden" name="command" value="login">
            <input class="btn btn-success fadeIn fourth" type="submit" value="<fmt:message key="login.login"/>">
        </form>

        <c:if test="${message !=null}">
            <p  class="text-danger fadeIn fifth"><c:out value="${message}"/></p>
        </c:if>

        <div id="formFooter">
            <a class="underlineHover" href="../jsp/registration.jsp">Registration</a>
        </div>

    </div>
</div>
</html>
