<%--@elvariable id="user" type="ua.nure.hrynko.models.User"--%>
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
<fmt:message bundle="${loc}" key="local.entrance" var="entrance"/>
<fmt:message bundle="${loc}" key="local.back_to_cruise_selection" var="back_to_cruise_selection"/>
<fmt:message bundle="${loc}" key="local.basket" var="basket"/>
<fmt:message bundle="${loc}" key="local.positions" var="positions"/>
<fmt:message bundle="${loc}" key="local.client_page" var="client_page"/>
<fmt:message bundle="${loc}" key="local.remove" var="remove"/>
<fmt:message bundle="${loc}" key="local.confirm_the_order" var="confirm_the_order"/>
<fmt:message bundle="${loc}" key="local.accounts" var="accounts"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.Add_to_cart" var="Add_to_cart"/>
<fmt:message bundle="${loc}" key="local.cruise_name" var="cruise_name"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.total" var="total"/>
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.start_of_cruise" var="start_of_cruise"/>
<fmt:message bundle="${loc}" key="local.order_quantity" var="order_quantity"/>
<fmt:message bundle="${loc}" key="local.cruise_duration" var="cruise_duration"/>
<fmt:message bundle="${loc}" key="local.my_profile" var="my_profile"/>
<fmt:message bundle="${loc}" key="local.balance_recharge" var="balance_recharge"/>
<fmt:message bundle="${loc}" key="local.en" var="en"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru"/>
<body>
<ul>
    <li><a href="controller?command=clientPageGoToMyProfile&userId=${user.id}">${my_profile}</a></li>
    <li><a href="controller?command=clientPageGoToTopUpYourAccount&userIdForTopUpYourAccount=${user.id}">
        ${balance_recharge}</a></li>
    <li><a href="controller?command=clientPage">${cruises}</a></li>

    <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    <li style="float:right"><a href="controller?command=goToBasket&local=en">${en}</a></li>
    <li style="float:right"><a href="controller?command=goToBasket&local=ru">${ru}</a></li>
</ul>
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
    <h1 align='center'>${basket}</h1>
    <div align='center'>
        <table border='1'>
            <tr>
                <td>№</td>
                <td>${cruise_name}</td>
                <td>${description}</td>
                <td>${total}</td>
                <td>${start_of_cruise}</td>
                <td>${cruise_duration}</td>
                <td>${order_quantity}</td>
            </tr>
            <c:set var="k" value="0"/>
            <%--@elvariable id="mapForBasket" type="java.util.Map"--%>
            <c:forEach var="cruise" items="${mapForBasket}">
                <c:set var="k" value="${k+1}"/>
                <tr>
                    <td>${k}</td>
                    <td>${cruise.key.name}</td>
                    <td>${cruise.key.description}</td>
                    <td>${cruise.key.price * cruise.value}</td>
                    <td>${cruise.key.startOfCruise}</td>
                    <td>${cruise.key.duration}</td>
                    <td>${cruise.value}</td>
                    <td>
                        <c:if test="${cruise.value > 0}">
                            <form method="get" action="controller">
                                <input type="hidden" name="command" value="removeOneUnitFromBasket"/>
                                <button type="submit" name="cruiseIdForBasketUsersHasCruisesButt"
                                        value="${cruise.key.id}" class="btn btn-primary btn-block btn-large"> - </form>
                        </c:if>
                    </td>
                    <td>
                        <form method="get" action="controller">
                            <input type="hidden" name="command" value="addOneUnitInBasket"/>
                            <button type="submit" name="cruiseIdForBasketUsersHasCruisesButt" value="${cruise.key.id}"
                                    class="btn btn-primary btn-block btn-large"> + </form>
                    </td>
                    <td>
                        <form method="get" action="controller">
                            <input type="hidden" name="command" value="removeOnePositionFromBasket"/>
                            <button type="submit" name="cruiseIdForBasketUsersHasCruisesButt" value="${cruise.key.id}"
                                    class="btn btn-primary btn-block btn-large"> x
                        </form>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div align='center'>
        <div style="display: inline-block; padding-right: 50px;">
            <form method="post" action="controller">
                <input type="hidden" name="command" value="clientBasketConfirmOrderOfSelectedUnits"/>
                <input type="hidden" name="userIdForBasketUsersHasCruisesButt" value=${user.id}>
                <button type="submit" name="cruiseIdForBasketUsersHasCruisesButt" value="${cruise.key.id}"
                        class="btn btn-primary btn-block btn-large">${confirm_the_order}</button>
            </form>
        </div>
</body>
</html>
