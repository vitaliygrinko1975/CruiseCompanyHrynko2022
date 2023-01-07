<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<fmt:message bundle="${loc}" key="local.blocked" var="blocked"/>
<fmt:message bundle="${loc}" key="local.role_id" var="role_id"/>
<fmt:message bundle="${loc}" key="local.accounts_id" var="accounts_id"/>
<body>
<div id="rightHeader">
    <c:out value="${user.firstName} ${user.lastName}"/>
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
<h1 align='center'>${admin_page}</h1>
<div align='center'>
    <div style="display: inline-block; padding-right: 50px;">
        <a href="controller?command=pageAdminCruises">
            <button class="btn btn-primary btn-block btn-large">${cruises}</button>
        </a>
    </div>
</div>
<div align='center'>
    <div style="display: inline-block; padding-right: 50px;">
        <form method="get" action="controller">
            <input type="hidden" name="command" value="pageAdminOrders"/>
            <button type="submit" name="page" value="1"
                    class="btn btn-primary btn-block btn-large">${orders}</button>
        </form>
    </div>
</div>
<div align='center'>
    <table border='1'>
        <caption><h2>${users}</h2></caption>
        <tr>
            <td>№</td>
            <td>${login}</td>
            <td>${password}</td>
            <td>${first_name}</td>
            <td>${last_name}</td>
            <td>${email}</td>
            <td>${phone}</td>
            <td>${role_id}</td>
        </tr>
        <c:set var="k" value="0"/>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>${user.roleId}</td>
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
</body>
</html>