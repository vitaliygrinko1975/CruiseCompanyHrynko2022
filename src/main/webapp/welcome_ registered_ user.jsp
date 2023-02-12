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
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="mytaglib" prefix="mt" %>
<html>
<head>
    <title>Message</title>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.my_profile" var="my_profile"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.basket" var="basket"/>
<fmt:message bundle="${loc}" key="local.users" var="users"/>
<fmt:message bundle="${loc}" key="local.orders" var="orders"/>
<fmt:message bundle="${loc}" key="local.ships" var="ships"/>
<fmt:message bundle="${loc}" key="local.contracts_rent_ships" var="contracts_rent_ships"/>
<fmt:message bundle="${loc}" key="local.en" var="en"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru"/>

<body>
<c:if test="${userRole.name eq 'client'}">
    <ul>
        <li><a href="controller?command=clientPageGoToMyProfile&userId=${user.id}&accountsId=${user.accountsId}">${my_profile}</a></li>
        <li><a href="controller?command=clientPage">${cruises}</a>
        <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
        <li style="float:right"><a href="controller?command=goToBasket">${basket}</a></li>
        <li style="float:right"><a href="controller?command=goToWelcomeRegisteredUser&local=en">${en}</a></li>
        <li style="float:right"><a href="controller?command=goToWelcomeRegisteredUser&local=ru">${ru}</a></li>
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
    <div align='center'>
        <mt:myTag/>
    </div>
    <div class='mydiv'>
        <h1 align='center'>${messageAboutPay} </h1>
        <h1 align='center'>${messageAboutUpdate} </h1>
    </div>
</c:if>
<c:if test="${userRole.name eq 'admin'}">
    <ul>
        <li><a href="controller?command=pageAdminCruises&page=1">${cruises}</a></li>
        <li><a href="controller?command=pageAdminShips">${ships}</a></li>
        <li><a href="controller?command=adminPage&page=1">${users}</a></li>
        <li><a href="controller?command=pageAdminOrders&page=1">${orders}</a></li>
        <li><a href="controller?command=adminGoToPageCruiseHasShip&page=1">${contracts_rent_ships}</a></li>

        <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
        <li style="float:right"><a href="controller?command=goToWelcomeRegisteredUser&local=en">${en}</a></li>
        <li style="float:right"><a href="controller?command=goToWelcomeRegisteredUser&local=ru">${ru}</a></li>
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
    <div align='center'>
        <mt:myTag/>
    </div>
    <div class='mydiv'>
        <h1 align='center'>${messageAboutPay} </h1>
        <h1 align='center'>${messageAboutUpdate} </h1>
        <h1 align='center'>${messageAboutDelete} </h1>
    </div>
</c:if>
</body>
</html>
