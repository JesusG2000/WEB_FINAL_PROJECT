<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<html>

<head>
    <title>Title</title>
    <c:if test="${locale eq 'en' or locale eq null}">
        <fmt:setLocale value="en"/>
        <fmt:setBundle basename="text"/>
    </c:if>

    <c:if test="${locale eq 'ru'}">
        <fmt:setLocale value="ru"/>
        <fmt:setBundle basename="text"/>
    </c:if>
</head>
<body>
<script>
    var link = document.querySelector('link[rel=import]');
    var content = link.import.querySelector('#some');
    document.body.appendChild(content.cloneNode(true));
</script>
<form method="post" action="/welcome">
        <div class="form-group col-md-3">
            <label ><fmt:message key="resume.name"/></label>
            <input type="text" name="userName" class="form-control" required placeholder="<fmt:message key="resume.name.field"/>">
        </div>

        <div class="form-group col-md-3">
            <label ><fmt:message key="resume.surname"/></label>
            <input type="text" name="userSurname" class="form-control" required placeholder="<fmt:message key="resume.surname.field"/>">
        </div>

        <div class="form-group col-md-3">
            <label ><fmt:message key="resume.age"/></label>
            <input type="number" name="userAge" value="18" min="16" max="70" class="form-control" required >
        </div>

    <div class="col-md-3">
        <label class="mr-sm-2" ><fmt:message key="resume.language"/></label>
        <select name="userLanguage" required class="custom-select mr-sm-2" >
            <option  value="<fmt:message key="resume.language.rus"/>"><fmt:message key="resume.language.rus"/></option>
            <option  value="<fmt:message key="resume.language.en"/>"><fmt:message key="resume.language.en"/></option>
            <option  value="<fmt:message key="resume.language.both"/>"><fmt:message key="resume.language.both"/></option>
        </select>
    </div>

    <div class="col-md-3">
        <label class="mr-sm-2"><fmt:message key="resume.work.experience"/></label>
        <select required name="userWorkExperience" class="custom-select mr-sm-2">
            <option  value="less 1 year"><fmt:message key="resume.work.experience.1"/></option>
            <option  value="1 year"><fmt:message key="resume.work.experience.2"/></option>
            <option  value="2 year"><fmt:message key="resume.work.experience.3"/></option>
            <option  value="3 year"><fmt:message key="resume.work.experience.4"/></option>
            <option  value="4 year"><fmt:message key="resume.work.experience.5"/></option>
            <option  value="5 year"><fmt:message key="resume.work.experience.6"/></option>
            <option  value="more than 5 years"><fmt:message key="resume.work.experience.7"/></option>
        </select>
    </div>

    <div class="col-md-3">
           <label><fmt:message key="resume.extra.info"/></label>
            <textarea name="userInfo" class="md-textarea form-control" rows="3"></textarea>
        </div>
    <div class="col-md-3">
        <input type="hidden" name="userId" value=${userId}>
        <input type="hidden" name="vacId" value=${vacId}>
        <input type="hidden" name="command" value="submit_resume">
        <input type="submit" class="btn btn-success" value="<fmt:message key="resume.submit"/>">
    </div>
</form>

</body>
</html>
