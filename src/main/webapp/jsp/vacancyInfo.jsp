<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp" />
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

<%--<form style="margin-left: 5px" action="/welcome" method="post">--%>
<%--    <fmt:message key="vacancyInfo.vacancy.id"/>--%>
<%--    <p><textarea rows="1" name="id" cols="5" readonly>${vacancy.id}</textarea></p>--%>
<%--    <fmt:message key="vacancyInfo.vacancy.name"/>--%>
<%--    <p><textarea rows="1" cols="20" name="name">${vacancy.name}</textarea></p>--%>
<%--    <fmt:message key="vacancyInfo.vacancy.description"/>--%>
<%--    <p><textarea rows="10" cols="45" name="description">${vacancy.description}</textarea></p>--%>
<%--       <input type="hidden" name="command" value="update_vacancy">--%>
<%--       <input type="submit" value="<fmt:message key="vacancyInfo.update"/>">--%>
<%--</form>--%>
<form method="post" action="/welcome">
    <div class="form-group col-md-3">
        <label > <fmt:message key="vacancyInfo.vacancy.id"/></label>
        <input type="text" name="id" readonly class="form-control" required value="${vacancy.id}">
    </div>

    <div class="form-group col-md-3">
        <label > <fmt:message key="vacancyInfo.vacancy.name"/></label>
        <input type="text" name="name" class="form-control" required value="${vacancy.name}">
    </div>

    <div class="col-md-3">
        <label> <fmt:message key="vacancyInfo.vacancy.description"/></label>
        <textarea name="description" class="md-textarea form-control" rows="3">${vacancy.description}</textarea>
    </div>
    <div class="col-md-3">
        <input type="hidden" name="command" value="update_vacancy">
        <input type="submit" class="btn-outline-primary" value="<fmt:message key="vacancyInfo.update"/>">
    </div>
</form>

</body>
</html>
