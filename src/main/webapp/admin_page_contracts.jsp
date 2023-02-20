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
<fmt:message bundle="${loc}" key="local.users" var="users"/>
<fmt:message bundle="${loc}" key="local.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="local.status" var="status"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.orders" var="orders"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.start_of_cruise" var="start_of_cruise"/>
<fmt:message bundle="${loc}" key="local.cruise_duration" var="cruise_duration"/>
<fmt:message bundle="${loc}" key="local.status_of_cruise" var="status_of_cruise"/>
<fmt:message bundle="${loc}" key="local.add_cruise" var="add_cruise"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.price" var="price"/>
<fmt:message bundle="${loc}" key="local.services_id" var="services_id"/>
<fmt:message bundle="${loc}" key="local.remove" var="remove"/>
<fmt:message bundle="${loc}" key="local.pay_from_balance" var="pay_from_balance"/>
<fmt:message bundle="${loc}" key="local.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.start_of_contract" var="start_of_contract"/>
<fmt:message bundle="${loc}" key="local.end_of_contract" var="end_of_contract"/>
<fmt:message bundle="${loc}" key="local.ships_id" var="ships_id"/>
<fmt:message bundle="${loc}" key="local.cruises_id" var="cruises_id"/>
<fmt:message bundle="${loc}" key="local.contracts" var="contracts"/>
<fmt:message bundle="${loc}" key="local.en" var="en"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru"/>

<body>
<ul>
    <li><a href="controller?command=adminPage&page=1">${users}</a></li>
    <li><a href="controller?command=pageAdminCruises&page=1">${cruises}</a></li>

    <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    <li style="float:right"><a href="controller?command=adminGoToPageCruiseHasShip&local=en">${en}</a></li>
    <li style="float:right"><a href="controller?command=adminGoToPageCruiseHasShip&local=ru">${ru}</a></li>

</ul>
<h1 align='center'>${admin_page}</h1>

<div align='center'>

    <table border='1'>
        <caption><h2>${contracts}</h2></caption>
        <tr>
            <td>№</td>
            <td>${cruises_id}</td>
            <td>${ships_id }</td>
            <td>${start_of_contract}</td>
            <td>${end_of_contract }</td>
            <td>${status_of_cruise}</td>
        </tr>
        <%--@elvariable id="allCruisesHasShipList" type="java.util.List"--%>
        <c:forEach var="itemCruisesHasShip" items="${allCruisesHasShipList}">
            <tr>
                <td>${itemCruisesHasShip.id}</td>
                <td>${itemCruisesHasShip.cruiseId}</td>
                <td>${itemCruisesHasShip.shipId}</td>
                <td>${itemCruisesHasShip.startOfContract}</td>
                <td>${itemCruisesHasShip.endOfContract}</td>
                <td>${itemCruisesHasShip.status}</td>
                <td>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="pageAdminRemoveContract"/>
                        <button type="submit" name="cruisesHasShipIdForRemoveCruisesHasShipButt" value="${itemCruisesHasShip.id}"
                                class="btn btn-primary btn-block btn-large">${remove}</button>
                    </form>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%--    <div class="pagination">--%>
    <%--        <c:forEach begin="1" end="${Math.ceil(countAllCruises*1.0/5)}" var="i">--%>
    <%--            <a href="controller?command=pageAdminOrders&page=${i}">${i}</a>--%>
    <%--        </c:forEach>--%>
    <%--    </div>--%>
    <%--</div>--%>

</body>
</html>
