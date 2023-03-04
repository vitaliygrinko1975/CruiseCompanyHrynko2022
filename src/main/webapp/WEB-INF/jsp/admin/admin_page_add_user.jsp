<%--@elvariable id="user" type="ua.nure.hrynko.models.User"--%>
<<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="mytaglib" prefix="mt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>

<html>
<head>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.phone" var="phone"/>
<fmt:message bundle="${loc}" key="local.blocked" var="blocked"/>
<fmt:message bundle="${loc}" key="local.add_new_user" var="add_new_user"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.basket" var="basket"/>
<fmt:message bundle="${loc}" key="local.users" var="users"/>
<fmt:message bundle="${loc}" key="local.orders" var="orders"/>
<fmt:message bundle="${loc}" key="local.contracts_rent_ships" var="contracts_rent_ships"/>

<body>
<ul>
    <li><a href="controller?command=pageAdminCruises&page=1">${cruises}</a></li>
    <li><a href="controller?command=adminPage&page=1">${users}</a></li>
    <li><a href="controller?command=pageAdminOrders&page=1">${orders}</a></li>
    <li><a href="controller?command=adminGoToPageCruiseHasShip&page=1">${contracts_rent_ships}</a></li>

    <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
</ul>
<div align='right'>
    <c:out value="${user.firstName} ${user.lastName}"/>
    <c:if test="${not empty userRole}">
        <c:out value="(${userRole.name})"/>
    </c:if>
</div>
<div align='right'>
    <mt:myTag/>
</div>
<div class='mydiv'>
        <form method='post' action="controller">
        <input type="hidden" name="command" value="adminAddingPageAddUser"/>
        ${login}: <input type='text' name='addLoginUser' required='required'/>
        ${password}: <input type='text' name='addPasswordUser' required='required'/>
        ${first_name}: <input type='text' name='addFirstNameUser' required='required'/>
        ${last_name}: <input type='text' name='addLastNameUser' required='required'/>
        ${email}: <input type='email' name='addEmailUser' required='required'/>
        ${phone}: <input type='text' pattern="^[+3]\d{12,12}$" title="В формате +3ххххххххххх" name='addPhoneUser' required='required'/>
        <button type='submit' name='Butt' value='0' class='btn btn-primary btn-block btn-large'>${add_new_user}</button>
    </form>
</div>
</body>
</html>

