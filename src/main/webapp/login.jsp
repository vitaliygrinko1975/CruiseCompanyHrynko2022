<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="./style/style2.css" rel="stylesheet" type="text/css">
    <title>Login</title>
</head>
<c:if test="${local == null}">
    <fmt:setLocale value="en"/>
</c:if>
<c:if test="${local != null}">
    <fmt:setLocale value="${local}"/>
</c:if>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.registration" var="registration"/>


<body>
<div class="login">
    <h1>${login}</h1>
    <form method="post" action="controller">
        <input type="hidden" name="command" value="login"/>
        <input type="text" name="login" placeholder="login" required="required"/>
        <input type="password" name="password" placeholder="password" required="required"/>
        <button type="submit" class="btn btn-primary btn-block btn-large">${login}</button>
    </form>
    </form>

    <form method="get" action="controller">
        <input type="hidden" name="command" value="signUpPage"/>
        <button type="submit" class="btn btn-primary btn-block btn-large">${registration}
        </button>
    </form>
</div>
</body>
</html>

