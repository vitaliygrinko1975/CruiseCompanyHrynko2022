<%--@elvariable id="user" type="ua.nure.hrynko.models.User"--%>
<%--@elvariable id="account" type="ua.nure.hrynko.models.Account"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Client</title>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.client_page" var="client_page"/>
<fmt:message bundle="${loc}" key="local.back" var="back"/>
<fmt:message bundle="${loc}" key="local.accounts" var="accounts"/>
<fmt:message bundle="${loc}" key="local.number" var="number"/>
<fmt:message bundle="${loc}" key="local.balance" var="balance"/>
<fmt:message bundle="${loc}" key="local.top_up" var="top_up"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.my_profile" var="my_profile"/>
<fmt:message bundle="${loc}" key="local.balance_recharge" var="balance_recharge"/>
<fmt:message bundle="${loc}" key="local.basket" var="basket"/>
<fmt:message bundle="${loc}" key="local.en" var="en"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru"/>
<body>
<ul>
    <li><a href="controller?command=clientPageGoToMyProfile&userId=${user.id}">${my_profile}</a></li>
    <li><a href="controller?command=clientPage">${cruises}</a>
    <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    <li style="float:right"><a href="controller?command=goToBasket">${basket}</a></li>
    <li style="float:right"><a href="controller?command=clientPageGoToMyProfile&local=en">${en}</a></li>
    <li style="float:right"><a href="controller?command=clientPageGoToMyProfile&local=ru">${ru}</a></li>
</ul>
<div align='center'>
    <%--===========================================================================
    Type user name if the user object is presented in the current session.
    ===========================================================================--%>
    <c:out value="${user.firstName} ${user.lastName}"/>
    <%--===========================================================================
    Type user role name if the user object is presented in the current session.
    ===========================================================================--%>
    <c:if test="${not empty userRole}">
        <c:out value="(${userRole.name})"/>
    </c:if>
</div>
<h1 align='center'>${client_page}</h1>
<div align='center'>
<table border='1'>
    <caption><h2>${balance_recharge}</h2></caption>
<tr>
    <td>№</td>
    <td>${balance}</td>
</tr>
<tr>
    <td>${account.id}</td>
    <td>${account.balance}</td>
    <td>
        <form method="get" action="controller">
            <input type="hidden" name="command" value="clientPageGoToUpdatingAccountPage"/>
            <button  class="btn btn-primary btn-block btn-large">${top_up}</button>
        </form>
    </td>
</tr>
</table>
</div>
</body>
</html>
