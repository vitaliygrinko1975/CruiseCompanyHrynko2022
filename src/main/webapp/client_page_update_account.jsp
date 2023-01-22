<%--@elvariable id="user" type="ua.nure.hrynko.models.User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>

<html>
<head>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.accounts" var="accounts"/>
<fmt:message bundle="${loc}" key="local.replenish" var="replenish"/>
<fmt:message bundle="${loc}" key="local.replenish_this_amount" var="replenish_this_amount"/>
<fmt:message bundle="${loc}" key="local.deposit" var="deposit"/>
<fmt:message bundle="${loc}" key="local.basket" var="basket"/>
<fmt:message bundle="${loc}" key="local.my_profile" var="my_profile"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.en" var="en"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru"/>

<body>
<ul>
    <li><a href="controller?command=ClientPageGoToMyProfile&userId=${user.id}">${my_profile}</a></li>
    <li><a href="controller?command=clientPage">${cruises}</a>
    </li>
    <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    <li style="float:right"><a href="controller?command=goToBasket">${basket}</a></li>
    <li style="float:right"><a href="controller?command=clientPageGoToUpdatingAccountPage&local=en">${en}</a></li>
    <li style="float:right"><a href="controller?command=clientPageGoToUpdatingAccountPage&local=ru">${ru}</a></li>
</ul>
<div id="rightHeader">
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
<div class='mydiv'>
    <h1 align='center'>${replenish}  ${deposit}</h1>
    <form method='post' action="controller">
        <input type="hidden" name="command" value="clientUpdatingPageUpdateAccount"/>
        ${replenish_this_amount}  ${depositAmount}:<input type='text' pattern="^[0-9]*[.]?[0-9]+$" title="Только
        цифры и . для чисел с плавающей точкой" name='increaseTheBalanceBy' value=${depositAmount}>
        <input type="hidden" name='accountForUpdateButt' value=${user.accountsId}>
        <button type="submit" class="btn btn-primary btn-block btn-large">${replenish}</button>
    </form>
</div>
</body>
</html>

