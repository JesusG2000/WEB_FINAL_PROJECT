<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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


<fmt:setLocale value="en"/>
<fmt:setBundle basename="text"/>

<div class="container">
    
    <div class="b-form b-form_responsive b-form_compact b-form_popup b-form_no-margin-bottom b-form_no-margin-top b-form_scroll-to-error b-form_wide-columns ">
        <form method="post" action="/welcome">
            <label><fmt:message key="login.username"/>: <input type="text" required placeholder=
            <fmt:message key="login.enterName"/> name="name"> </label>
            <br>
            <label><fmt:message key="login.password"/>: <input type="password" required placeholder=
            <fmt:message key="login.enterPassword"/> name="password"></label>
            <br>
            <input type="hidden" name="command" value="login">
            <input class="btn btn-success" type="submit" value="<fmt:message key="login.login"/>">
        </form>

        <form  method="post" action="/welcome">
            <input type="hidden" name="command" value="registration_page">
            <input class="btn btn-info" type="submit" value="<fmt:message key="login.registration"/>">
        </form>
    </div>

    <c:if test="${message !=null}">
        <p class="text-danger"><c:out value="${message}"/></p>
    </c:if>
</div>

</body>
</html>
