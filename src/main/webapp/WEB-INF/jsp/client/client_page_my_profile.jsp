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
<fmt:message bundle="${loc}" key="local.availability" var="availability"/>
<fmt:message bundle="${loc}" key="local.start_of_cruise" var="start_of_cruise"/>
<fmt:message bundle="${loc}" key="local.find_cruise" var="find_cruise"/>
<fmt:message bundle="${loc}" key="local.start_date" var="start_date"/>
<fmt:message bundle="${loc}" key="local.cruise_duration" var="cruise_duration"/>
<fmt:message bundle="${loc}" key="local.deposit" var="deposit"/>
<fmt:message bundle="${loc}" key="local.my_profile" var="my_profile"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.phone" var="phone"/>
<fmt:message bundle="${loc}" key="local.blocked" var="blocked"/>
<fmt:message bundle="${loc}" key="local.role_id" var="role_id"/>
<fmt:message bundle="${loc}" key="local.accounts_id" var="accounts_id"/>
<fmt:message bundle="${loc}" key="local.update" var="update"/>
<fmt:message bundle="${loc}" key="local.balance_recharge" var="balance_recharge"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.basket" var="basket"/>
<body>
<ul>
    <li><a href="controller?command=ClientPageGoToTopUpYourAccount&userIdForTopUpYourAccount=${user.id}">
        ${balance_recharge}</a></li>
    <li><a href="controller?command=clientPage">${cruises}</a>

    <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    <li style="float:right"><a href="controller?command=goToBasket">${basket}</a></li>
</ul>
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

<div align='right'>
<div style="display: inline-block; padding-right: 50px;">
    <h3> Загрузите данные </h3>
    <form align='center' action="upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <button class="btn btn-primary btn-block btn-large">Загрузить</button>
    </form>
</div>
</div>
    <div class='mydiv'>
        <h1 align='center'>${update}</h1>
        <form method='post' action="controller">
            <input type="hidden" name="command" value="adminUpdatingPageUpdateUser"/>
            ${first_name}: <input type='text' name='updateFirstNameUser' value=${user.firstName}>
            ${last_name}: <input type='text' name='updateLastNameUser' value=${user.lastName}>
            ${email}: <input type='email' name='updateEmailUser' value=${user.email}>
            ${phone}: <input type='text' pattern="^[+3]\d{12,12}$" title="В формате +3ххххххххххх" name='updatePhoneUser' value=${user.phone}>
            <button type='submit' name='userForUpdateButt' value="${user.id}" class='btn btn-primary btn-block btn-large'>
                ${update}
            </button>
        </form>
    </div>
</body>
</html>
