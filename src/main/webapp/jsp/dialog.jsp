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
    <div style="margin-left: 5px" class="col-lg-5">
        <c:forEach var="message" items="${finalDialog.messages}">

            <c:if test="${finalDialog.ownUser.id ==message.senderId}">
                <div class="list-group">
                    <a class="list-group-item-secondary list-group-item-action flex-column align-items-start ">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">${finalDialog.ownUser.name}</h5>
                        </div>
                        <p class="mb-1">${message.content}</p>
                    </a>
                </div>
            </c:if>
            <c:if test="${finalDialog.otherUser.id ==message.senderId}">
                <div class="list-group">
                    <a class="list-group-item-light list-group-item-action flex-column align-items-start ">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">${finalDialog.otherUser.name}</h5>
                        </div>
                        <p class="mb-1">${message.content}</p>
                    </a>
                </div>
            </c:if>

        </c:forEach>
    </div>
    <div class="col-lg-4">
        <form action="/welcome" method="post">
            <p><textarea class="form-control" rows="2" cols="45" name="message" required placeholder=<fmt:message
                    key="dialog.message"/>></textarea>
            </p>
            <input type="hidden" name="ownUserId" value="${finalDialog.ownUser.id}">
            <input type="hidden" name="otherUserId" value="${finalDialog.otherUser.id}">
            <input type="hidden" name="command" value="dialog">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="dialog.send"/>">
        </form>
    </div>
</div>
</body>
</html>
