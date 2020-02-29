
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <head>
</head>
<body>

<c:if test="${locale eq 'en' or locale eq null}">
    <fmt:setLocale value="en"/>
    <fmt:setBundle basename="text"/>
</c:if>

<c:if test="${locale eq 'ru'}">
    <fmt:setLocale value="ru"/>
    <fmt:setBundle basename="text"/>
</c:if>

<%--<div id = "some">--%>
<%--<div class="container-fluid">--%>
<%--    <div class="row">--%>
<%--        <nav class="navbar navbar-default">--%>
<%--            <div class="container-fluid">--%>
<%--                <div class="navbar-header">--%>
<%--                    <p class="navbar-brand"><fmt:message key="website"/></p>--%>
<%--                </div>--%>

<%--&lt;%&ndash;                <ul class="nav navbar-nav">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <li class="col-md-2">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <form id="but2" method="post" action="/welcome">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <input type="hidden" name="command" value="home_page" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <a class="navbar-brand"  href=# onclick="document.getElementById('but2').submit()"><fmt:message key="profile.home"/></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </form>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <li class="col-md-2">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <form id="but4" method="post" action="/welcome">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="hidden" name="command" value="profile_page" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <a class="navbar-brand" href=# onclick="document.getElementById('but4').submit()" ><fmt:message key="home.profile"/></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </form>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <li class="col-md-2">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <form id="but3" method="post" action="/welcome">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <input type="hidden" name="command" value="all_dialogs_page" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <a class="navbar-brand" href=# onclick="document.getElementById('but3').submit()" ><fmt:message key="profile.dialogs"/></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </form>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <li class="col-md-1">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <form id="but5" method="post" action="/welcome">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <input type="hidden" name="command" value="change_language">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <input type="hidden" name="language" value="en">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <a class="navbar-brand" href=# onclick="document.getElementById('but5').submit()"><fmt:message key="profile.en"/></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </form>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <li class="col-md-1">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <form id="but6" method="post" action="/welcome">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <input type="hidden" name="command" value="change_language">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <input type="hidden" name="language" value="ru">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <a class="navbar-brand" href=# onclick="document.getElementById('but6').submit()"><fmt:message key="profile.ru"/></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </form>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <li class="col-md-1">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <form id="but7" method="post" action="/welcome">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <input type="hidden" name="command" value="logout" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <a class="navbar-brand" href=# onclick="document.getElementById('but7').submit()" ><fmt:message key="profile.logout"/></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </form>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </ul>&ndash;%&gt;--%>

<%--            </div>--%>
<%--        </nav>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</div>--%>
<div id="some">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <p class="navbar-brand" ><fmt:message key="website"/></p>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${user.role ne 'ADMIN'}">
            <li class="col-md-2">
                <form id="but2" method="post" action="/welcome">
                    <input type="hidden" name="command" value="home_page" />
                    <a class="navbar-brand"  href=# onclick="document.getElementById('but2').submit()"><fmt:message key="profile.home"/></a>
                </form>
            </li>
            </c:if>
            <li class="col-md-3">
                <form id="but4" method="post" action="/welcome">
                    <input type="hidden" name="command" value="profile_page" />
                    <a class="navbar-brand" href=# onclick="document.getElementById('but4').submit()" ><fmt:message key="home.profile"/></a>
                </form>
            </li>

            <li class="col-md-3">
                <form id="but3" method="post" action="/welcome">
                    <input type="hidden" name="command" value="all_dialogs_page" />
                    <a class="navbar-brand" href=# onclick="document.getElementById('but3').submit()" ><fmt:message key="profile.dialogs"/></a>
                </form>
            </li>
            <c:if test="${locale eq 'ru'}">
            <li class="col-md-2">
                <form id="but5" method="post" action="/welcome">
                    <input type="hidden" name="command" value="change_language">
                    <input type="hidden" name="language" value="en">
                    <a class="navbar-brand" href=# onclick="document.getElementById('but5').submit()"><fmt:message key="profile.en"/></a>
                </form>
            </li>
            </c:if>
            <c:if test="${locale eq 'en'}">
            <li class="col-md-2">
                <form id="but6" method="post" action="/welcome">
                    <input type="hidden" name="command" value="change_language">
                    <input type="hidden" name="language" value="ru">
                    <a class="navbar-brand" href=# onclick="document.getElementById('but6').submit()"><fmt:message key="profile.ru"/></a>
                </form>
            </li>
            </c:if>
            <li class="col-md-1">
                <form id="but7" method="post" action="/welcome">
                    <input type="hidden" name="command" value="logout" />
                    <a class="navbar-brand" href=# onclick="document.getElementById('but7').submit()" ><fmt:message key="profile.logout"/></a>
                </form>
            </li>
        </ul>
        <div class="dropleft open">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <fmt:message key="profile.info"/>
            </button>

            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <p class="dropdown-item"><fmt:message key="profile.user.id"/> : <c:out value="${user.id}"/><br></p>
                <p class="dropdown-item"><fmt:message key="profile.user.name"/> : <c:out value="${user.name}"/><br></p>
                <p class="dropdown-item"><fmt:message key="profile.user.role"/> : <c:out value="${user.role}"/></p>
            </div>
        </div>

    </div>
</nav>
</div>
</body>
</html>