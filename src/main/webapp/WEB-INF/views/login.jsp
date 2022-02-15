<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/login.css"/>" type="text/css"/>
    <title>Авторизация</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<form action="/do_login" method="post">
    <div class="container">
        <h1>Авторизация</h1>
        <p>Пожалуйста заполните все поля чтобы войти.</p>
        <hr>

        <label for="login"><b>Логин</b></label>
        <input type="text" placeholder="Enter Login" name="login" id="login" pattern="[A-Za-zА-Яа-я0-9]{4,20}" title="пример: login1" required>

        <label for="password"><b>Пароль</b></label>
        <input type="password" placeholder="Enter Password" name="password" id="password" pattern="[A-Za-zА-Яа-я0-9]{8,20}" title="пример: 12345qwer" required>
        <hr>
        <input type="hidden" name="command" value="logination"/>
        <button type="submit" class="loginbtn">Войти</button>
    </div>
    <div class="container signin">
        <p>Если у вас не зарегистрирован акаунт, создайте его <a href="/registration">сейчас</a></p>
    </div>
</form>

<br />
</body>
</html>