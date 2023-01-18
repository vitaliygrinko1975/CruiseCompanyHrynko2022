<%--@elvariable id="user" type="ua.nure.hrynko.models.User"--%>
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
<fmt:message bundle="${loc}" key="local.my_profile" var="my_profile"/>



<body>
<ul>
    <li><a href="controller?command=ClientPageGoToMyProfile&userId=${user.id}">${my_profile}</a></li>

    <li style="float:right"><a href="controller?command=logout">${logout}</a></li>
</ul>
<h1 align='center'>${client_page}</h1>

    <div align='center'>

        <table border='1'>
            <caption><h2>${orders}</h2></caption>
            <tr>
                <td>№</td>
                <td>${first_name}</td>
                <td>${last_name}</td>
                <td>${email}</td>
                <td>${description}</td>
                <td>${status_of_cruise}</td>
                <td>${status}</td>
            </tr>
            <%--@elvariable id="allItemOfOrdersViewWithLimit" type="java.util.List"--%>
            <c:forEach var="ordersView" items="${allItemOfOrdersViewWithLimit}">
                <tr>
                            <td>${ordersView.id}</td>
                            <td>${ordersView.usersFirstName}</td>
                            <td>${ordersView.usersLastName}</td>
                            <td>${ordersView.usersEmail}</td>
                            <td>${ordersView.cruisesDescription}</td>
                            <td>${ordersView.statusOfCruises}</td>
                            <td>${ordersView.status}</td>
                </tr>
            </c:forEach>
        </table>
        <div class="pagination">
            <%--@elvariable id="countAllCruises" type="java"--%>
            <c:forEach begin="1" end="${Math.ceil(countAllCruises*1.0/5)}" var="i">
                <a href="controller?command=clientPageMyOrders&userId=${user.id}&page=${i}">${i}</a>
            </c:forEach>
        </div>
    </div>

</body>
</html>
