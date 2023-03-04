<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="mytaglib" prefix="mt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <link href="./style/style2.css" rel="stylesheet" type="text/css">
</head>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.admin_page" var="admin_page"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.orders" var="orders"/>
<fmt:message bundle="${loc}" key="local.users" var="users"/>
<fmt:message bundle="${loc}" key="local.remove" var="remove"/>
<fmt:message bundle="${loc}" key="local.update" var="update"/>
<fmt:message bundle="${loc}" key="local.add_new_user" var="add_new_user"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.phone" var="phone"/>
<fmt:message bundle="${loc}" key="local.role" var="role"/>
<fmt:message bundle="${loc}" key="local.accounts_id" var="accounts_id"/>
<fmt:message bundle="${loc}" key="local.contracts_rent_ships" var="contracts_rent_ships"/>
<fmt:message bundle="${loc}" key="local.en" var="en"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru"/>

<body>
<ul>
    <li><a href="controller?command=pageAdminCruises&page=1">${cruises}</a></li>
    <li><a href="controller?command=pageAdminOrders&page=1">${orders}</a></li>
    <li><a href="controller?command=adminGoToPageCruiseHasShip&page=1">${contracts_rent_ships}</a></li>

    <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    <li style="float:right"><a href="controller?command=adminPage&page=1&local=en">${en}</a></li>
    <li style="float:right"><a href="controller?command=adminPage&page=1&local=ru">${ru}</a></li>
</ul>
<div align='right'>
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
<mt:myTag/>
</div>

<h1 align='center'>${admin_page}</h1>
<div align='center'>
    <table border='1'>
        <caption><h2>${users}</h2></caption>
        <tr>
            <td>№</td>
            <td>${first_name}</td>
            <td>${last_name}</td>
            <td>${email}</td>
            <td>${phone}</td>
            <td>${role}</td>
        </tr>
        <c:set var="k" value="0"/>
        <%--@elvariable id="allItemOfUserWithLimit" type="java.util.List"--%>
        <c:forEach var="user" items="${allItemOfUserWithLimit}">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <c:if test="${user.roleId eq '1'}">
                <td>admin</td>
                </c:if>
                <c:if test="${user.roleId eq '2'}">
                    <td>client</td>
                </c:if>
                <c:if test="${user.roleId eq '3'}">
                    <td>employee</td>
                </c:if>
                <td>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="adminPageRemoveUser"/>
                        <button type="submit" name="removeButt" value="${user.id}"
                                class="btn btn-primary btn-block btn-large">${remove}</button>
                    </form>
                </td>
                <td>
                    <form method="get" action="controller">
                        <input type="hidden" name="command" value="adminGoToUpdatingPage"/>
                        <button type="submit" name="userIdForUpdateButt" value="${user.id}"
                                class="btn btn-primary btn-block btn-large">${update}</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div align='center' style="display: inline-block;">
        <a href="controller?command=adminGoToPageAddingUser">
            <button class="btn btn-primary btn-block btn-large">${add_new_user}</button>
        </a>
    </div>
</div>
<div align='center'>
<div class="pagination">
    <%--@elvariable id="countAllUsers" type="java"--%>
    <c:forEach begin="1" end="${Math.ceil(countAllUsers*1.0/2)}" var="i">
        <a href="controller?command=adminPage&page=${i}">${i}</a>
    </c:forEach>
</div>
</div>
</body>
</html>