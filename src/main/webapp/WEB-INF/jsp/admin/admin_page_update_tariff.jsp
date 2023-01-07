<%--@elvariable id="tariff" type="ua.nure.hrynko.dto.Cruise"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<fmt:message bundle="${loc}" key="local.tariffs_name" var="tariffs_name"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.price" var="price"/>
<fmt:message bundle="${loc}" key="local.services_id" var="services_id"/>
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
    <h1 align='center'>${update} ${tariffs}</h1>
    <form method='post' action="controller">
        <input type="hidden" name="command" value="adminUpdatingPageUpdateTariff"/>
        ${tariffs_name}: <input type='text' name='updateNameTariff' value=${tariff.name}>
        ${description}: <input type='text' name='updateDescriptionTariff' value=${tariff.description}>
        ${price}: <input type='text' name='updatePriceTariff' value=${tariff.price}>
        ${services_id}: <input type='text' name='updateServicesIdTariff' value=${tariff.servicesId}>
        <button type='submit' name='tariffForUpdateButt' value="${tariff.id}" class='btn btn-primary btn-block btn-large'>
            ${update}
        </button>
    </form>
</div>
</body>
</html>

