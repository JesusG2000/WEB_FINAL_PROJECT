<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>

</head>
<body>


<c:forEach var="seekers" items="${seekers}">
    <c:out value="${seekers}"/>
</c:forEach>

<c:forEach var="hrs" items="${hrs}">
    <c:out value="${hrs}"/>
</c:forEach>
</body>
</html>
