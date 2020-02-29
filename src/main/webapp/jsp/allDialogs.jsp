<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp"/>

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
<div class="row">
    <div class="col-lg-4">
        <c:forEach var="dialog" items="${dialogs}">
            <form id="${dialog.otherUser.id}" action="/welcome" method="post">
                <input type="hidden" name="otherUserId" value="${dialog.otherUser.id}">
                <input type="hidden" name="command" value="dialog_page">
                <a href="#" class="list-group-item list-group-item-action flex-column align-items-start list-group-item-info"
                   onclick="document.getElementById(${dialog.otherUser.id}).submit()">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1"><c:out value="${dialog.otherUser.name}"/></h5>
                    </div>
                </a>
            </form>
        </c:forEach>
    </div>
    <div class="col-lg-4">
        <form action="/welcome" method="post">
                <p><input type="search" name="name" placeholder=<fmt:message key="allDialogs.writeName"/>>
                    <input type="hidden" name="command" value="add_dialog">
                    <input type="submit" class="btn-success" value="<fmt:message key="allDialogs.add"/>"></p>
        </form>

        <form action="/welcome" method="post">
            <p><input type="search" name="name" placeholder="<fmt:message key="allDialogs.writeName"/>">
                <input type="hidden" name="command" value="delete_dialog">
                <input type="submit" class=" btn-danger" value="<fmt:message key="allDialogs.delete"/>"></p>
        </form>
    </div>
</div>

</body>
</html>
