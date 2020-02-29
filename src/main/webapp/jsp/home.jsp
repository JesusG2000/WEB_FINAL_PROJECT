<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <div class="col-lg-5">
        <div class=" col-lg-12 list-group">
            <c:forEach var="vacancy" items="${vacancies}">
                <c:if test="${currentUser.role eq 'SEEKER'}">
                    <form id="${vacancy.id}" action="/welcome" method="post">
                        <input type="hidden" name="vacId" value="${vacancy.id}">
                        <input type="hidden" name="userId" value="${currentUser.id}">
                        <input type="hidden" name="command" value="subscribe_to_vacancy">
                        <a href="#"
                           class="list-group-item list-group-item-action flex-column align-items-start list-group-item-secondary"
                           onclick="document.getElementById(${vacancy.id}).submit()">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1"><c:out value="${vacancy.name}"/></h5>
                            </div>
                            <p class="mb-1">${vacancy.description}</p>
                        </a>
                    </form>
                </c:if>
                <c:if test="${currentUser.role eq 'HR'}">
                    <div class="row">
                        <div class="col-lg-11">
                            <form id="${vacancy.id}" action="/welcome" method="post">
                                <input type="hidden" name="id" value="${vacancy.id}">
                                <input type="hidden" name="command" value="update_vacancy_page">
                                <a href="#"
                                   class="list-group-item list-group-item-action flex-column align-items-start list-group-item-secondary"
                                   onclick="document.getElementById(${vacancy.id}).submit()">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1"><c:out value="${vacancy.name}"/></h5>
                                    </div>
                                    <p class="mb-1">${vacancy.description}</p>
                                </a>
                            </form>
                        </div>
                        <div class="col-lg-1">
                            <form action="/welcome" method="post">
                                <input type="hidden" name="id" value="${vacancy.id}">
                                <input type="hidden" name="command" value="delete_vacancy">
                                <input type="submit" class="btn-danger" value="<fmt:message key="home.delete"/>">
                            </form>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <div class="col-lg-3 text-danger">
        <c:if test="${message ne null}">
            <c:out value="${message}"/>
        </c:if>
    </div>
    <div class="col-lg-3">
        <c:if test="${currentUser.role eq 'HR'}">


            <div class="dropdown open">
                <button class="btn btn-outline-primary dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="home.addVacancy"/>
                </button>

                <div class="dropdown-menu col-lg-12" aria-labelledby="dropdownMenuButton">
                    <p class="dropdown-item">

                        <form method="post" action="/welcome">
                            <div class="form-group col-md-8">
                                <label><fmt:message key="addVacancy.name"/></label>
                                <input type="text" name="vacancyName" class="form-control" required
                                       placeholder="<fmt:message key="addVacancy.enterName"/>">
                            </div>

                            <div class="col-md-11">
                                <label><fmt:message key="addVacancy.description"/></label>
                                <textarea name="description" required class="md-textarea form-control"
                                          rows="3"></textarea>
                            </div>
                    <p>
                    <div class="col-md-9">
                        <input type="hidden" name="command" value="add_vacancy">
                        <input type="submit" class="btn btn-success" value="<fmt:message key="addVacancy.add"/>">
                    </div>

                    </form>

                </div>
            </div>
        </c:if>
    </div>

</div>
</body>
</html>
