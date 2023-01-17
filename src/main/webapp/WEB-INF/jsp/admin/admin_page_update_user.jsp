<%--@elvariable id="user" type="ua.nure.hrynko.models.User"--%>
<<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>AdminUpdatingPage</title>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.phone" var="phone"/>
<fmt:message bundle="${loc}" key="local.blocked" var="blocked"/>
<fmt:message bundle="${loc}" key="local.accounts_id" var="accounts_id"/>
<fmt:message bundle="${loc}" key="local.update" var="update"/>

<body>
<div align='right'>
    <div style="display: inline-block; padding-right: 50px;">
        <a href="controller?command=logout">
            <button class="btn btn-primary btn-block btn-large">${logout}</button>
        </a>
    </div>
</div>
<div class='mydiv'>
    <h1 align='center'>${update}</h1>
    <form method='post' action="controller">
        <input type="hidden" name="command" value="adminUpdatingPageUpdateUser"/>
        ${login}: <input type='text' name='updateLoginUser' value=${user.login}>
        ${password}: <input type='text' name='updatePasswordUser' value=${user.password}>
        ${first_name}: <input type='text' name='updateFirstNameUser' value=${user.firstName}>
        ${last_name}: <input type='text' name='updateLastNameUser' value=${user.lastName}>
        ${email}: <input type='email' name='updateEmailUser' value=${user.email}>
        ${phone}: <input type='text' pattern="^[+3]\d{12,12}$" title="В формате +3ххххххххххх" name='updatePhoneUser' value=${user.phone}>
        ${accounts_id}: <input type='text' name='updateAccountsIdUser' value=${user.accountsId}>
        <button type='submit' name='userForUpdateButt' value="${user.id}" class='btn btn-primary btn-block btn-large'>
            ${update}
        </button>
    </form>
</div>

</body>
</html>

