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
<fmt:message bundle="${loc}" key="local.capacity" var="capacity"/>
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.users" var="users"/>
<fmt:message bundle="${loc}" key="local.orders" var="orders"/>
<fmt:message bundle="${loc}" key="local.start_of_contract" var="start_of_contract"/>
<fmt:message bundle="${loc}" key="local.end_of_contract" var="end_of_contract"/>
<fmt:message bundle="${loc}" key="local.rent_ship" var="rent_ship"/>
<fmt:message bundle="${loc}" key="local.free_ships" var="free_ships"/>
<fmt:message bundle="${loc}" key="local.contracts_rent_ships" var="contracts_rent_ships"/>
<body>
<div align='center'>
    <ul>
        <li><a href="controller?command=pageAdminCruises&page=1">${cruises}</a></li>
        <li><a href="controller?command=adminPage&page=1">${users}</a></li>
        <li><a href="controller?command=pageAdminOrders&page=1">${orders}</a></li>
        <li><a href="controller?command=adminGoToPageCruiseHasShip&page=1">${contracts_rent_ships}</a></li>

        <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    </ul>
</div>
<div class='mydiv'>
    <h1 align='center'>${admin_page}</h1>
    <table border='1'>
        <h2>${free_ships}</h2>

        <tr>
            <td>№</td>
            <td>${ship}</td>
            <td>${description}</td>
            <td>${capacity}</td>
            <td>${start_of_contract}</td>
            <td>${end_of_contract}</td>
        </tr>
        <%--@elvariable id="allFreeShips" type="java.util.List"--%>
        <c:forEach var="ship" items="${allFreeShips}">
            <tr>
                <td>${ship.id}</td>
                <td>${ship.name}</td>
                <td>${ship.description}</td>
                <td>${ship.capacity}</td>
                <td>${ship.startOfContract}</td>
                <td>${ship.endOfContract}</td>
                <td>
<%--                    <c:if test="${ship.startOfContract eq startOfCruiseForRent}">--%>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="adminRentingPageAddShipToCruiseHasShip"/>
                        <input type="hidden" name="cruiseIdForRent" value="${cruiseIdForRent}"/>
                        <input type="hidden" name="capacityOfCruiseForRent" value="${capacityOfCruiseForRent}"/>
                        <input type="hidden" name="startOfContract" value="${startOfCruiseForRent}"/>
                        <input type="hidden" name="endOfContract" value="${endOfCruiseForRent}"/>
                        <input type="hidden" name="capacityOfCruiseForRent" value="${capacityOfCruiseForRent}"/>
                        <button type="submit" name="shipIdForRent" value="${ship.id}"
                                class='btn btn-primary btn-block btn-large'>${rent_ship}</button>
                    </form>
<%--                    </c:if>--%>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

