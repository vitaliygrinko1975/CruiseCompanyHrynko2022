<%--@elvariable id="user" type="ua.nure.hrynko.dto.User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.basket" var="basket"/>
<fmt:message bundle="${loc}" key="local.positions" var="positions"/>
<fmt:message bundle="${loc}" key="local.client_page" var="client_page"/>
<fmt:message bundle="${loc}" key="local.accounts" var="accounts"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.Add_to_cart" var="Add_to_cart"/>
<fmt:message bundle="${loc}" key="local.cruise_name" var="cruise_name"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.price" var="price"/>
<fmt:message bundle="${loc}" key="local.ships_id" var="ships_id"/>
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.start_of_cruise" var="start_of_cruise"/>
<fmt:message bundle="${loc}" key="local.end_of_cruise" var="end_of_cruise"/>

<body>
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
<div align='right'>
    <div style="display: inline-block; padding-right: 50px;">
        <a href="controller?command=logout">
            <button class="btn btn-primary btn-block btn-large">${logout}</button>
        </a>
    </div>
</div>


<div align='right'>
    <%--@elvariable id="mapForBasket" type="java.util.Map"--%>
    <c:out value="${basket} - ${mapForBasket.size()} ${positions}"/></div>
>
<div align='right'>
    <c:set var="count" value="0"/>
    <c:forEach var="cruise" items="${mapForBasket}">
            <c:set var="count" value="${count + cruise.value}"/>
    </c:forEach>
    <c:out value="Всего количество - ${count} штук"/>
    <%--</div>--%>
    <div align='right'>
        <div style="display: inline-block; padding-right: 50px;">
            <a href="controller?command=goToBasket">
                <button class="btn btn-primary btn-block btn-large">${basket}</button>
            </a>
        </div>
    </div>

    <h1 align='center'>${client_page}</h1>

    <form align='center' method="get" action="controller">
        <div style="display: inline-block; padding-right: 50px;">
            <input type="hidden" name="command" value="ClientPageGoToTopUpYourAccount"/>
            <button type="submit" name="userIdForTopUpYourAccount" value="${user.id}"
                    class="btn btn-primary btn-block btn-large">${accounts}</button>
        </div>
    </form>
    <div align='center'>
        <table border='1'>
            <caption><h2>${cruises}</h2></caption>
            <tr>
                <td>№</td>
                <td>${cruise_name}</td>
                <td>${description}</td>
                <td>${price}</td>
                <td>${ships_id}</td>
                <td>${availability}</td>
                <td>${start_of_cruise}</td>
                <td>${end_of_cruise}</td>
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
                    <td>${cruise.shipId}</td>
                    <td>${cruise.capacity}</td>
                    <td>${cruise.startOfCruise}</td>
                    <td>${cruise.endOfCruise}</td>
                    <td>
                        <form method="get" action="controller">
                            <input type="hidden" name="command" value="AddToBasket"/>
                            <input type="hidden" name="userIdForBasketUsersHasCruisesButt" value=${user.id}>
                            <button type="submit" name="cruiseIdForBasketUsersHasCruisesButt" value="${cruise.id}"
                                    class="btn btn-primary btn-block btn-large">${Add_to_cart}
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
