<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <link href='style/style2.css' rel='stylesheet' type='text/css'>
</head>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.back" var="back"/>
<fmt:message bundle="${loc}" key="local.admin_page" var="admin_page"/>
<fmt:message bundle="${loc}" key="local.cruise_name" var="cruise_name"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.ship" var="ship"/>
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.start_of_cruise" var="start_of_cruise"/>
<fmt:message bundle="${loc}" key="local.cruise_duration" var="cruise_duration"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.price" var="price"/>
<fmt:message bundle="${loc}" key="local.remove" var="remove"/>
<fmt:message bundle="${loc}" key="local.update" var="update"/>
<fmt:message bundle="${loc}" key="local.users" var="users"/>
<fmt:message bundle="${loc}" key="local.orders" var="orders"/>
<fmt:message bundle="${loc}" key="local.add_cruise" var="add_cruise"/>
<fmt:message bundle="${loc}" key="local.ships" var="ships"/>
<fmt:message bundle="${loc}" key="local.add_ship" var="add_ship"/>
<fmt:message bundle="${loc}" key="local.capacity" var="capacity"/>
<fmt:message bundle="${loc}" key="local.rent" var="rent"/>
<fmt:message bundle="${loc}" key="local.start_date" var="start_date"/>
<fmt:message bundle="${loc}" key="local.end_date" var="end_date"/>
<fmt:message bundle="${loc}" key="local.find_free_ships" var="find_free_ships"/>
<fmt:message bundle="${loc}" key="local.en" var="en"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru"/>


<body>
<ul>
    <li><a href="controller?command=pageAdminCruises">${cruises}</a></li>
    <li><a href="controller?command=pageAdminOrders&page=1">${orders}</a></li>
    <li><a href="controller?command=adminPage">${users}</a></li>

    <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    <li style="float:right"><a href="controller?command=pageAdminShips&local=en">${en}</a></li>
    <li style="float:right"><a href="controller?command=pageAdminShips&local=ru">${ru}</a></li>
</ul>
<h1 align='center'>${admin_page}</h1>

<div align='center'>

    <table border='1'>
        <h2>${ships}</h2>

        <tr>
            <td>№</td>
            <td>${ship}</td>
            <td>${description}</td>
            <td>${capacity}</td>
        </tr>
        <%--@elvariable id="allShips" type="java.util.List"--%>
        <c:forEach var="ship" items="${allShips}">
            <tr>
                <td>${ship.id}</td>
                <td>${ship.name}</td>
                <td>${ship.description}</td>
                <td>${ship.capacity}</td>
                <td>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="adminPageShipsRemoveShip"/>
                        <button type="submit" name="shipIdForRemoveShipButt" value="${ship.id}"
                                class="btn btn-primary btn-block btn-large">${remove}</button>
                    </form>
                </td>
                <td>
                    <form method=get" action="controller">
                        <input type="hidden" name="command" value="adminGoToPageForUpdatingShip"/>
                        <button type="submit" name="shipIdForUpdateShipButt" value="${ship.id}"
                                class="btn btn-primary btn-block btn-large">${update}</button>
                    </form>
                </td>
                <td>
                    <c:if test="${freeShipsFromRange ne null}">
                    <form method="get" action="controller">
                        <input type="hidden" name="command" value="adminGoToPageForRentShip"/>
                        <button type="submit" name="shipIdForRentButt" value="${ship.id}"
                                class="btn btn-primary btn-block btn-large">${rent}</button>
                    </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div align='center' style="display: inline-block;">
        <a href="controller?command=adminGoToPageForAddingShip">
            <button class="btn btn-primary btn-block btn-large">${add_ship}</button>
        </a>
    </div>
<%--    <form class="form-inline" action="controller" method="get">--%>
<%--        <input type="hidden" name="command" value="findFreeShipByRange">--%>
<%--        <div class="form-group" style="display: inline-block; margin-right: 25px;">--%>
<%--            <legend>${start_date}:</legend>--%>
<%--            <input type="date" name="startDate" required>--%>
<%--        </div>--%>
<%--        <div class="form-group" style="display: inline-block; margin-right: 25px;">--%>
<%--            <legend>${end_date}:</legend>--%>
<%--            <input type="date" name="endDate" required>--%>
<%--        </div>--%>
<%--        <div class="form-group" style="display: inline-block; margin-right: 25px;">--%>
<%--            <input type="submit" name="selectOption" value="${find_free_ships}">--%>
<%--        </div>--%>
<%--    </form>--%>
</body>
</html>
