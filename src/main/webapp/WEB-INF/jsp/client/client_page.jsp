<%--@elvariable id="user" type="ua.nure.hrynko.models.User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="mytaglib" prefix="mt" %>
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
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.start_of_cruise" var="start_of_cruise"/>
<fmt:message bundle="${loc}" key="local.find_cruise" var="find_cruise"/>
<fmt:message bundle="${loc}" key="local.start_date" var="start_date"/>
<fmt:message bundle="${loc}" key="local.cruise_duration" var="cruise_duration"/>
<fmt:message bundle="${loc}" key="local.deposit" var="deposit"/>
<fmt:message bundle="${loc}" key="local.my_profile" var="my_profile"/>
<fmt:message bundle="${loc}" key="local.status" var="status"/>
<fmt:message bundle="${loc}" key="local.en" var="en"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru"/>

<body>
<ul>
    <li><a href="controller?command=clientPageGoToMyProfile&userId=${user.id}&accountsId=${user.accountsId}">${my_profile}</a></li>

    <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    <li style="float:right"><a href="controller?command=goToBasket">${basket}</a></li>
    <li style="float:right"><a href="controller?command=clientPage&local=en">${en}</a></li>
    <li style="float:right"><a href="controller?command=clientPage&local=ru">${ru}</a></li>
</ul>
<div align='right'>
    <c:set var="count" value="0"/>
    <c:forEach var="cruise" items="${mapForBasket}">
        <c:set var="count" value="${count + cruise.value}"/>
    </c:forEach>
    <c:out value="Выбрано - ${count} штук"/>
</div>
<div align='left'>
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
<div align='left'>
    <mt:myTag/>
</div>
<%--<div align='right'>--%>
<%--@elvariable id="mapForBasket" type="java.util.Map"--%>
<%--    <c:out value="${basket} - ${mapForBasket.size()} ${positions}"/></div>--%>
<div align='right'>
<h1 align='center'>${client_page}</h1>
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
                    <c:if test="${cruise.capacity ne '0' and cruise.status eq 'Не начался'}">
                    <form method="get" action="controller">
                        <input type="hidden" name="command" value="addToBasket"/>
                        <input type="hidden" name="userIdForBasketUsersHasCruisesButt" value=${user.id}>
                        <button type="submit" name="cruiseIdForBasketUsersHasCruisesButt" value="${cruise.id}"
                                class="btn btn-primary btn-block btn-large">${Add_to_cart}
                    </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<form class="form-inline" action="controller" method="get">
    <input type="hidden" name="command" value="cruiseFindByStartDate">
    <div class="form-group" style="display: inline-block; margin-right: 25px;">
        <legend>${start_date}:</legend>
        <input type="date" name="startDate" required>
    </div>
    <div class="form-group" style="display: inline-block; margin-right: 25px;">
        <input type="submit" name="selectOption" value="${find_cruise}">
    </div>
</form>
<form class="form-inline" action="controller" method="get">
    <input type="hidden" name="command" value="cruiseFindByDuration">
    <div class="form-group" style="display: inline-block; margin-right: 25px;">
        <legend>${cruise_duration}:</legend>
        <input type="number" min="1" max="60" name="duration" required>
    </div>
    <div class="form-group" style="display: inline-block; margin-right: 25px;">
        <input type="submit" name="selectOption" value="${find_cruise}">
    </div>
</form>
</body>
</html>
