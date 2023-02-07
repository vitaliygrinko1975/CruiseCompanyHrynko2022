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
<fmt:message bundle="${loc}" key="local.en" var="en"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru"/>
<fmt:message bundle="${loc}" key="local.home" var="home"/>
<body>
<ul>
    <li><a href="controller?command=goToWelcomePage">${home}</a>

    <li style="float:right"><a href="controller?command=loginPage&local=en">${en}</a></li>
    <li style="float:right"><a href="controller?command=loginPage&local=ru">${ru}</a></li>
</ul>
<div class="login">
    <h1>${login}</h1>
    <form id="login" method="post" action="controller">
        <input type="hidden" name="command" value="login"/>
        <input type="text" name="login" placeholder="login" required="required"/>
        <input type="password" name="password" placeholder="password" required="required"/>
        <button type="submit" class="btn btn-primary btn-block btn-large">${login}</button>
    </form>
    <form  id="registration" method="get" action="controller">
        <input type="hidden" name="command" value="signUpPage"/>
        <button type="submit" class="btn btn-primary btn-block btn-large">${registration}</button>
    </form>
</div>
<div align='center' class="g-recaptcha" data-sitekey="6LcnqxkkAAAAAEiO0dh5hcpAmxy-sgibiy3hbbAx" required="required"></div>
<div align='center' class="text-danger" id="error"></div>
</body>
<script src="https://www.google.com/recaptcha/api.js"></script>
<script>
    window.onload = function () {
        const form = document.getElementById("login");
        const form1 = document.getElementById("registration");
        const error = document.getElementById("error");
        form.addEventListener("submit", function (event) {
            event.preventDefault();
            const response = grecaptcha.getResponse();
            if (response) {
                form.submit();
            }else {
                error.innerHTML = "Complete the captcha";
            }
        });
        form1.addEventListener("submit", function (event) {
            event.preventDefault();
            const response = grecaptcha.getResponse();
            if (response) {
                form1.submit();
            }else {
                error.innerHTML = "Complete the captcha";
            }
        });
    }
</script>
</html>

