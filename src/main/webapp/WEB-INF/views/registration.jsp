<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value="/css/registration.css"/>" type="text/css"/>
    <title>Регистрация</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<form action="/do_signup" method="post">
    <div class="container">
        <h1>Регистрация</h1>
        <p>Пожалуйста заполните все поля чтобы создать аккаунт.</p>
        <hr>

        <label for="login"><b>Логин</b></label>
        <input type="text" placeholder="Enter Login" name="login" id="login" pattern="[A-Za-zА-Яа-я0-9]{4,20}" title="пример: login1" required>

        <label for="password"><b>Пароль</b></label>
        <input type="password" placeholder="Enter Password" name="password" id="password" pattern="[a-zA-ZА-Яа-я0-9]{8,20}"  title="пример: 12345qwer" required>
        <hr>
        <input type="hidden" name="command" value="registration" />
        <button  type="submit"  class="registerbtn">Зарегистрироваться </button>
    </div>

    <div class="container signin">
        <p>У вас уже есть аккаунт? <a href="/login">Войти</a></p>
    </div>
</form>
</body>
</html>