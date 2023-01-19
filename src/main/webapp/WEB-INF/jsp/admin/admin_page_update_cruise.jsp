<%--@elvariable id="myTag" type=""--%>
<%--@elvariable id="cruise" type="ua.nure.hrynko.models.Cruise"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="mytaglib" prefix="mt" %>
<html>
<head>
    <title>AdminUpdatingPage</title>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.price" var="price"/>
<fmt:message bundle="${loc}" key="local.update" var="update"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.users" var="users"/>
<fmt:message bundle="${loc}" key="local.orders" var="orders"/>
<fmt:message bundle="${loc}" key="local.cruise_name" var="cruise_name"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.ship" var="ship"/>
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.start_of_cruise" var="start_of_cruise"/>
<fmt:message bundle="${loc}" key="local.cruise_duration" var="cruise_duration"/>
<fmt:message bundle="${loc}" key="local.ships_id" var="ships_id"/>
<fmt:message bundle="${loc}" key="local.price" var="price"/>
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.status" var="status"/>
<body>
<div align='center'>
    <ul>
        <li><a href="controller?command=pageAdminCruises">${cruises}</a></li>
        <li><a href="controller?command=adminPage">${users}</a></li>
        <li><a href="controller?command=pageAdminOrders&page=1">${orders}</a></li>

        <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    </ul>
</div>
<mt:myTag/>
<div class='mydiv'>
    <h1 align='center'>${update} ${cruises}</h1>
    <form method='post' action="controller">
        <input type="hidden" name="command" value="adminUpdatingPageUpdateCruise"/>
        ${cruise_name}: <input type='text' name='updateNameCruise' value=${cruise.name}>
        ${description}: <input type='text' name='updateDescriptionCruise' value=${cruise.description}>
        ${price}: <input type='text' name='updatePriceCruise' value=${cruise.price}>
        ${availability}: <input type='text' name='updateCapacityCruise' value=${cruise.capacity}>
        ${start_of_cruise}: <input type='date' name='updateStartOfCruise' placeholder="dd-mm-yyyy"
                                   value=${cruise.startOfCruise}>
<%--        min=<mt:myTag/> max="2030-12-31"--%>
        ${cruise_duration}: <input type='text' name='updateDurationCruise' value=${cruise.duration}>
        ${status}: <input type='text' name='updateStatusCruise' value=${cruise.status}>
        <button type='submit' name='cruiseIdForUpdateButt' value="${cruise.id}" class='btn btn-primary btn-block btn-large'>
            ${update}
        </button>
    </form>
</div>
</body>
</html>

<%--<div class="form-group" style="display: inline-block; margin-right: 25px;">--%>
<%--    <legend>${start_date}:</legend>--%>
<%--    <input type="date" name="startDate" required>--%>
<%--</div>--%>