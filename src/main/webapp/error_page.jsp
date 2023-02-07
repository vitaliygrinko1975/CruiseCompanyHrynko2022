<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href='./style/style2.css' rel='stylesheet' type='text/css'>
</head>
<c:set var="title" value="Error" scope="page"/>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.my_profile" var="my_profile"/>
<fmt:message bundle="${loc}" key="local.cruises" var="cruises"/>
<fmt:message bundle="${loc}" key="local.basket" var="basket"/>
<fmt:message bundle="${loc}" key="local.users" var="users"/>
<fmt:message bundle="${loc}" key="local.orders" var="orders"/>
<body>
<table id="main-container">
    <ul>
        <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
    </ul>
    <c:if test="${userRole.name eq 'client'}">
        <ul>
            <li><a href="controller?command=clientPageGoToMyProfile&userId=${user.id}&accountsId=${user.accountsId}">${my_profile}</a></li>
            <li><a href="controller?command=clientPage">${cruises}</a>
            <li style="float:right"><a href="controller?command=goToBasket">${basket}</a></li>
        </ul>
        </c:if>
        <c:if test="${userRole.name eq 'admin'}">
        <ul>
            <li><a href="controller?command=pageAdminCruises">${cruises}</a></li>
            <li><a href="controller?command=adminPage">${users}</a></li>
            <li><a href="controller?command=pageAdminOrders&page=1">${orders}</a></li>
        </ul>
        </c:if>
    <div align='center'>

        ${messageAboutPay}<br>
        ${message}<br>

    <c:forEach  items = "${errors}" var = "i">
        ${i}<br>
    </c:forEach>
    </div>
        <tr>
            <td class="content">
                <%-- this way we obtain an information about an exception (if it has been occurred) --%>
                <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
                <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
                <c:if test="${requestScope['javax.servlet.error.message'] eq null}">
                    <c:set var="message" value="${errorMessageFromSession}"/>
                </c:if>
                <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

                <c:if test="${not empty code}">
                    <h3>Error code: ${code}</h3>
                </c:if>

                <c:if test="${not empty message}">
                    <h3>${message}</h3>
                </c:if>

                <c:if test="${not empty exception}">
                    <% exception.printStackTrace(new PrintWriter(out)); %>
                </c:if>

                <%-- if we get this page using forward --%>
                <c:if test="${not empty requestScope.errorMessage}">
                    <h3>${requestScope.errorMessage}</h3>
                </c:if>

            </td>
        </tr>
</table>
</body>
</html>
