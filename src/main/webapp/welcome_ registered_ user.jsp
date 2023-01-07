<%--
  Created by IntelliJ IDEA.
  User: LocalAdmin
  Date: 28.08.2022
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
     <title>Message</title>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.entrance" var="entrance"/>


<body>

<div class='mydiv'>
    <h1 align='center'>${messageAboutPay} </h1>
    <h1 align='center'>${message} ${userRole.name} ${user.firstName} ${user.lastName}</h1>
    <h1 align='center'>${messageForUnregistered} </h1>


</div>

<div align='right'>
    <div style="display: inline-block; padding-right: 50px;">
        <a href="controller?command=logout">
            <button class="btn btn-primary btn-block btn-large">${logout}</button>
        </a>
    </div>
</div>

<c:if test="${userRole.name eq 'client'}">
<div align='right'>
    <div style="display: inline-block; padding-right: 50px;">
        <a href="controller?command=clientPage">
            <button class="btn btn-primary btn-block btn-large">${entrance}</button>
        </a>
    </div>
    </c:if>
    <c:if test="${userRole.name eq 'admin'}">
    <div align='right'>
        <div style="display: inline-block; padding-right: 50px;">
            <a href="controller?command=adminPage">
                <button class="btn btn-primary btn-block btn-large">${entrance}</button>
            </a>
        </div>
    </div>
    </c:if>
</body>
</html></title>
</head>
<body>

</body>
</html>
