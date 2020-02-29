<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<link rel="import" href="../html/bootstrap.html">
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
    <div class="col-md-2">
        <div class="container">
            <form action="/welcome" method="post">
                <h5><label><fmt:message key="profile.for.change.password"/></label></h5>
                <div class="form-group">
                    <label><fmt:message key="profile.old.password"/></label>
                    <input type="password" name="oldPassword" class="form-control" minlength="4" maxlength="16" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="profile.new.password"/></label>
                    <input type="password" name="newPassword" class="form-control" minlength="4" maxlength="16" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="profile.confirm.password"/></label>
                    <input type="password" name="confirmPassword" class="form-control" minlength="4" maxlength="16"
                           required>
                </div>
                <input type="hidden" name="command" value="change_password">
                <input type="submit" value="<fmt:message key="profile.change.password"/>">
                <div>
                    <c:if test="${message!=null}">
                        <p class="text-danger"><c:out value="${message}"/></p>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-7">
        <c:if test="${user.role eq 'SEEKER'}">
            <c:if test="${interview.size() != 0 or start > interview.size()}">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class=" p-3"><fmt:message key="profile.hr.name"/></th>
                            <th class=" p-3"><fmt:message key="profile.Date"/></th>
                            <th class=" p-3"><fmt:message key="profile.vacancy.name"/></th>
                            <th class=" p-3"><fmt:message key="profile.comment"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="interview" items="${interview}">
                            <tr>
                                <th><c:out value="${userService.readById(interview.hrId).name}"/></th>
                                <th><c:out value="${interview.date}"/></th>
                                <th><c:out value="${vacancyService.readById(interview.vacancyId).name}"/></th>
                                <th><c:out value="${interview.comment}"/></th>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="btn-group" role="group" aria-label="...">
                    <form action="/welcome" method="post">
                        <input type="hidden" name="command" value="profile_page">
                        <input type="hidden" name="start" value=${start-count}>
                        <input type="hidden" name="finish" value=${finish-count}>
                        <input class="btn btn-warning" type="submit" value="<fmt:message key="profile.prev"/>">
                    </form>
                    <form action="/welcome" method="post">
                        <input type="hidden" name="command" value="profile_page">
                        <input type="hidden" name="start" value=${start+count}>
                        <input type="hidden" name="finish" value=${finish+count}>
                        <input class="btn btn-warning" type="submit" value=" <fmt:message key="profile.next"/> ">
                    </form>
                </div>
            </c:if>
        </c:if>
        <c:if test="${user.role eq 'HR'}">
            <c:if test="${vacancies.size() != 0 or start > vacancies.size()}">

                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="w-20 p-3"><fmt:message key="profile.user.id"/></th>
                            <th class="w-20 p-3"><fmt:message key="profile.user.name"/></th>
                            <th class="w-20 p-3"><fmt:message key="profile.vacancy.name"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="vacancy" items="${vacancies}">
                            <c:forEach var="seeker" items="${vacancyListUser.get(vacancy)}">
                                <c:forEach var="interview" items="${interviewService.getAllInterviewBySeeker(seeker)}">
                                   <c:if test="${interview.vacancyId == vacancy.id }">
                                <tr class="table-success">
                                    </c:if>
                                </c:forEach>
                                    <form id="table" action="/welcome" method="post">
                                        <th><c:out value="${seeker.id}"/></th>
                                        <th><c:out value="${seeker.name}"/></th>
                                        <th><c:out value="${vacancy.name}"/></th>
                                        <input type="hidden" name="vacId" value="${vacancy.id}">
                                        <input type="hidden" name="seekerId" value="${seeker.id}">
                                        <input type="hidden" name="command" value="interview_page">
                                        <th ><input type="submit" value="<fmt:message key="vacancyInfo.submitInterview"/>"></th>
                                    </form>
                                </tr>

                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="btn-group" role="group" aria-label="...">
                    <form action="/welcome" method="post">
                        <input type="hidden" name="command" value="profile_page">
                        <input type="hidden" name="start" value=${start-count}>
                        <input type="hidden" name="finish" value=${finish-count}>
                        <input class="btn btn-warning" type="submit" value="<fmt:message key="profile.prev"/>">
                    </form>
                    <form action="/welcome" method="post">
                        <input type="hidden" name="command" value="profile_page">
                        <input type="hidden" name="start" value=${start+count}>
                        <input type="hidden" name="finish" value=${finish+count}>
                        <input class="btn btn-warning" type="submit" value=" <fmt:message key="profile.next"/> ">
                    </form>
                </div>
            </c:if>
        </c:if>
        <c:if test="${user.role eq 'ADMIN'}">
            <c:if test="${allUsers.size() != 0 or start > allUsers.size()}">
                <form action="/welcome" method="post">
                    <p> <input type="number" name="id" placeholder="<fmt:message key="profile.write.id"/>">
                        <input type="hidden" name="command" value="delete_user">
                        <input type="submit" class="btn-outline-danger" value="<fmt:message key="profile.delete"/>">
                </form>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="w-20 p-3"><fmt:message key="profile.user.id"/></th>
                            <th class="w-20 p-3"><fmt:message key="profile.user.name"/></th>
                            <th class="w-20 p-3"><fmt:message key="profile.user.role"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="a" items="${allUsers}">
                            <tr>
                                <th><c:out value="${a.id}"/></th>
                                <th><c:out value="${a.name}"/></th>
                                <th><c:out value="${a.role}"/></th>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="btn-group" role="group" aria-label="...">
                    <form action="/welcome" method="post">
                        <input type="hidden" name="command" value="profile_page">
                        <input type="hidden" name="start" value=${start-count}>
                        <input type="hidden" name="finish" value=${finish-count}>
                        <input class="btn btn-warning" type="submit" value="<fmt:message key="profile.prev"/>">
                    </form>
                    <form action="/welcome" method="post">
                        <input type="hidden" name="command" value="profile_page">
                        <input type="hidden" name="start" value=${start+count}>
                        <input type="hidden" name="finish" value=${finish+count}>
                        <input class="btn btn-warning" type="submit" value=" <fmt:message key="profile.next"/> ">
                    </form>
                </div>
            </c:if>
        </c:if>
    </div>
</div>

</body>
</html>
