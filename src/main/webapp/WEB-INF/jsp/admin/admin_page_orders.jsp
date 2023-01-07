<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.back" var="back"/>
<fmt:message bundle="${loc}" key="local.admin_page" var="admin_page"/>
<fmt:message bundle="${loc}" key="local.cruise_name" var="cruise_name"/>
<fmt:message bundle="${loc}" key="local.users" var="users"/>
<fmt:message bundle="${loc}" key="local.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="local.status" var="status"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.orders" var="orders"/>
<fmt:message bundle="${loc}" key="local.ships_id" var="ships_id"/>
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.start_of_cruise" var="start_of_cruise"/>
<fmt:message bundle="${loc}" key="local.cruise_duration" var="cruise_duration"/>
<fmt:message bundle="${loc}" key="local.add_cruise" var="add_cruise"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.price" var="price"/>
<fmt:message bundle="${loc}" key="local.services_id" var="services_id"/>
<fmt:message bundle="${loc}" key="local.remove" var="remove"/>
<fmt:message bundle="${loc}" key="local.change_status" var="change_status"/>


<body>
<div align='right'>
    <div style="display: inline-block; padding-right: 50px;">
        <a href="controller?command=logout">
            <button class="btn btn-primary btn-block btn-large">${logout}</button>
        </a>
    </div>
</div>
<div align='left'>
    <div style="display: inline-block; padding-right: 50px;">
        <a href="controller?command=adminPage">
            <button class="btn btn-primary btn-block btn-large">${back}</button>
        </a>
    </div>
    <h1 align='center'>${admin_page}</h1>

    <div align='center'>

        <table border='1'>
            <caption><h2>${orders}</h2></caption>
            <tr>
                <td>№</td>
                <td>${users}</td>
                <td>${cruises}</td>
                <td>${status}</td>
            </tr>
            <c:set var="k" value="0"/>
            <%--@elvariable id="allUsersHasCruises" type="java.util.List"--%>
            <c:forEach var="usersHasCruises" items="${allUsersHasCruises}">
                <c:set var="k" value="${k+1}"/>
                <tr>
                    <td><c:out value="${k}"/></td>
                    <td>${usersHasCruises.userId}</td>
                    <td>${usersHasCruises.cruiseId}</td>
                    <td>${usersHasCruises.status}</td>
                    <td>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="adminPageTariffsRemoveTariff"/>
                            <button type="submit" name="removeTariffButt" value="${tariff.id}"
                                    class="btn btn-primary btn-block btn-large">${remove}</button>
                        </form>
                    </td>
                    <td>
                        <form method="get" action="controller">
                            <input type="hidden" name="command" value="adminGoToPageForUpdatingTariff"/>
                            <button type="submit" name="tariffIdForUpdateButt" value="${tariff.id}"
                                    class="btn btn-primary btn-block btn-large">${change_status}</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="pagination">
            <%--@elvariable id="Math" type="java"--%>
            <%--@elvariable id="countAllCruises" type="java"--%>
            <c:forEach begin="1" end="${Math.ceil(countAllCruises*1.0/5)}" var="i">
                <a href="controller?command=pageAdminOrders&page=${i}">${i}</a>
            </c:forEach>
        </div>
    </div>
</body>
</html>
