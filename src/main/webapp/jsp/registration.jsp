<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="import" href="../html/bootstrap.html"><%@ page isELIgnored="false" %>
<link rel="stylesheet" href="../css/registration.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<html>
<head>
    <title>Registration</title>
    <style>
        body {
            background-image: url("../picture/loginn.jpg");
        }
        span.input-group-addon i {
            color: blueviolet;
            font-size: 17px;
        }
        label{
             margin-bottom: 15px;
            color: #dddddd;
         }
        input,
        input::-webkit-input-placeholder {
            font-size: 11px;
            padding-top: 3px;
        }
        p{
            color: #ff0000;
        }


    </style>
</head>

<body>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="text"/>
<%--<c:if test="${locale eq 'en' or locale eq null}">--%>
<%--    <fmt:setLocale value="en"/>--%>
<%--    <fmt:setBundle basename="text"/>--%>
<%--</c:if>--%>

<%--<c:if test="${locale eq 'ru'}">--%>
<%--    <fmt:setLocale value="ru"/>--%>
<%--    <fmt:setBundle basename="text"/>--%>
<%--</c:if>--%>

<%--<div class="container main-form">--%>
<%--    <div class="row second-form">--%>
<%--        <form method="post" action="/welcome">--%>
<%--            <label>Username: <input type="text" minlength="4" maxlength="16" required placeholder="enter your username"--%>
<%--                                    name="name"> </label> <br>--%>
<%--            <label>Password: <input type="password" minlength="4" maxlength="16" required--%>
<%--                                    placeholder="enter your password"--%>
<%--                                    name="password"></label> <br>--%>
<%--            <input type="hidden" name="command" value="registration">--%>
<%--            <input class="btn btn-success" type="submit" value="<fmt:message key="registration.submit"/>">--%>
<%--            <div class="form-group">--%>
<%--                <a class="btn btn-primary" href="/jsp/login.jsp"><fmt:message key="registration.back"/></a>--%>
<%--            </div>--%>
<%--        </form>--%>

<%--        <c:if test="${message !=null}">--%>
<%--            <c:out value="${message}"/>--%>
<%--        </c:if>--%>
<%--    </div>--%>
<%--</div>--%>


<div class="container">
    <div class="row main-form">
        <form id="but2" method="post" action="/welcome">

<%--            <div class="form-group">--%>
<%--                <label for="name" class="cols-sm-2 control-label">Your Name</label>--%>
<%--                <div class="cols-sm-10">--%>
<%--                    <div class="input-group">--%>
<%--                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>--%>
<%--                        <input type="text" class="form-control" name="name" id="name" placeholder="Enter your Name"/>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <div class="form-group">--%>
<%--                <label for="email" class="cols-sm-2 control-label">Your Email</label>--%>
<%--                <div class="cols-sm-10">--%>
<%--                    <div class="input-group">--%>
<%--                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>--%>
<%--                        <input type="text" class="form-control" name="email" id="email" placeholder="Enter your Email"/>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>

            <div class="form-group">
                <label class="cols-sm-2 control-label">Username</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                        <input  type="text" class="form-control" name="name" minlength="4" maxlength="16" required placeholder="Enter your Username"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label  class="cols-sm-2 control-label">Password</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input  type="password" class="form-control" name="password" minlength="4" maxlength="16" required placeholder="Enter your Password"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label  class="cols-sm-2 control-label">Confirm Password</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input  type="password" class="form-control" name="confirmPassword" minlength="4" maxlength="16" required placeholder="Confirm your Password"/>
                    </div>
                </div>
            </div>

    <c:if test="${message !=null}">
        <p ><c:out value="${message}"/></p>
    </c:if>
    <div class="form-group ">
                <input type="hidden" name="command" value="registration">
                <a id="button" class="btn btn-primary btn-lg btn-block login-button"  href=# onclick="document.getElementById('but2').submit()"><fmt:message key="registration.submit"/></a>
                <a href="/jsp/login.jsp"  type="button"  class=" btn btn-primary btn-lg btn-block login-button">Back</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
