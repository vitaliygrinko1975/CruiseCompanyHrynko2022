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
<fmt:message bundle="${loc}" key="local.status" var="status"/>
<body>
<ul>
    <li><a href="controller?command=pageAdminShips">${ships}</a></li>
    <li><a href="controller?command=adminPage">${users}</a></li>
    <li><a href="controller?command=pageAdminOrders&page=1">${orders}</a></li>

    <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
</ul>
<h1 align='center'>${admin_page}</h1>

<div align='center'>

    <table border='1'>
        <caption><h2>${cruises}</h2></caption>
        <tr>
            <td>№</td>
            <td>${cruise_name}</td>
            <td>${description}</td>
            <td>${price}</td>
            <td>${availability}</td>
            <td>${start_of_cruise}</td>
            <td>${cruise_duration}</td>
            <td>${status}</td>
        </tr>
        <c:set var="k" value="0"/>
        <%--@elvariable id="allCruises" type="java.util.List"--%>
        <c:forEach var="cruise" items="${allCruises}">
            <c:set var="k" value="${k+1}"/>
            <tr>
                <td><c:out value="${k}"/></td>
                <td>${cruise.name}</td>
                <td>${cruise.description}</td>
                <td>${cruise.price}</td>
                <td>${cruise.capacity}</td>
                <td>${cruise.startOfCruise}</td>
                <td>${cruise.duration}</td>
                <td>${cruise.status}</td>
                <td>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="adminPageCruiseRemoveCruise"/>
                        <button type="submit" name="cruiseIdForRemoveCruiseButt" value="${cruise.id}"
                                class="btn btn-primary btn-block btn-large">${remove}</button>
                    </form>
                </td>
                <td>
                    <form method="get" action="controller">
                        <input type="hidden" name="command" value="adminGoToPageForUpdatingCruise"/>
                        <button type="submit" name="cruiseIdForUpdateCruiseButt" value="${cruise.id}"
                                class="btn btn-primary btn-block btn-large">${update}</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div align='center' style="display: inline-block;">
        <a href="controller?command=adminGoToPageForAddingCruise">
            <button class="btn btn-primary btn-block btn-large">${add_cruise}</button>
        </a>
    </div>
</div>
</body>
</html>
