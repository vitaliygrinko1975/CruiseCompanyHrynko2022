<%--@elvariable id="user" type="ua.nure.hrynko.models.User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@page isELIgnored="false" %>
<html>
<head>
       <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.admin_page" var="admin_page"/>
<fmt:message bundle="${loc}" key="local.add_cruise" var="add_cruise"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.price" var="price"/>
<fmt:message bundle="${loc}" key="local.services_id" var="services_id"/>
<fmt:message bundle="${loc}" key="local.cruise_name" var="cruise_name"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.ship" var="ship"/>
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.start_of_cruise" var="start_of_cruise"/>
<fmt:message bundle="${loc}" key="local.cruise_duration" var="cruise_duration"/>
<fmt:message bundle="${loc}" key="local.ships_id" var="ships_id"/>
<fmt:message bundle="${loc}" key="local.price" var="price"/>
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.users" var="users"/>
<fmt:message bundle="${loc}" key="local.orders" var="orders"/>
<fmt:message bundle="${loc}" key="local.start_of_contract" var="start_of_contract"/>
<fmt:message bundle="${loc}" key="local.end_of_contract" var="end_of_contract"/>
<fmt:message bundle="${loc}" key="local.rent" var="rent"/>



<body>
<div align='center'>
    <ul>
        <li><a href="controller?command=pageAdminCruises">${cruises}</a></li>
        <li><a href="controller?command=adminPage">${users}</a></li>
        <li><a href="controller?command=pageAdminOrders&page=1">${orders}</a></li>

        <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    </ul>
</div>
<div class='mydiv'>
    <h1 align='center'>${admin_page}</h1>
    <form method='post' action="controller">
        <input type="hidden" name="command" value="adminRentingPageAddShipToCruiseHasShip"/>
        ${cruises}: <input type='text' name='cruiseIdForRent' required='required'/>
        <input type="hidden" name='shipIdForRent' value="${shipId}"/>
        ${start_of_contract}: <input type='date' name='startOfContract' required='required'/>
        ${end_of_contract }: <input type='date' name='endOfContract' required='required'/>
        <button type='submit' name='Butt' value='0' class='btn btn-primary btn-block btn-large'>${rent}</button>
    </form>
</div>
</body>
</html>

