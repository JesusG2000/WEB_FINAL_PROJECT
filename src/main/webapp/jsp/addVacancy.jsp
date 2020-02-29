<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<%--<link rel="stylesheet" href="../css/addVacancy.css">--%>
<html>
<head>

    <title></title>
</head>
<body>
<script>
    var link = document.querySelector('link[rel=import]');
    var content = link.import.querySelector('#some');
    document.body.appendChild(content.cloneNode(true));
</script>

<c:if test="${locale eq 'en' or locale eq null}">
    <fmt:setLocale value="en"/>
    <fmt:setBundle basename="text"/>
</c:if>

<c:if test="${locale eq 'ru'}">
    <fmt:setLocale value="ru"/>
    <fmt:setBundle basename="text"/>
</c:if>

<body>

<div class="row">
    <div class="col-lg-4"></div>
    <div class="col-lg-6">
    <form method="post" action="/welcome">
        <div class="form-group col-md-6">
            <label><fmt:message key="addVacancy.name"/></label>
            <input type="text" name="vacancyName" class="form-control" required
                   placeholder="<fmt:message key="addVacancy.enterName"/>">
        </div>

        <div class="col-md-9">
            <label><fmt:message key="addVacancy.description"/></label>
            <textarea name="description" required class="md-textarea form-control" rows="3"></textarea>
        </div>
        <div class="col-md-9">
            <input type="hidden" name="command" value="add_vacancy">
            <input type="submit" class="btn btn-success" value="<fmt:message key="addVacancy.add"/>">
        </div>
    </form>
    </div>
</div>
</body>
</html>
