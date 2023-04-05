<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value="/css/registration.css"/>" type="text/css"/>
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/a931579f7d.js" crossorigin="anonymous"></script>
</head>
<body>
<%--<jsp:include page="/WEB-INF/views/header.jsp"/>--%>
<jsp:include page="/WEB-INF/views/main2.jsp"/>

<form action="/do_signup" method="post">
    <div class="container">
        <h1>Регистрация</h1>
        <p>Пожалуйста заполните все поля чтобы создать аккаунт.</p>
        <hr>
        <div class="row" style="margin-bottom: 10px">
            <div class="col">
               <b>
                   Имя
               </b>
                <input type="text" class="form-control" placeholder="Имя" aria-label="Имя">
            </div>
            <div class="col">
                <b>Фамилия</b>
                <input type="text" class="form-control" placeholder="Фамилия" aria-label="Фамилия">
            </div>
        </div>
        <div class="col">
            <b>E-mail</b>
            <input type="text" class="form-control" placeholder="E-mail адрес"
                   aria-label="E-mail адрес">
        </div>
        <div class="mb-3">
            <label for="login" class="form-label"><b>Логин</b></label>
            <input type="text" class="form-control" placeholder="Введите логин" name="login" id="login"
                   pattern="[A-Za-zА-Яа-я0-9]{4,20}"
                   title="пример: login1" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label"><b>Пароль</b></label>
            <input type="password" class="form-control" placeholder="Введите пароль" name="password" id="password"
                   pattern="[A-Za-zА-Яа-я0-9]{8,20}" title="пример: 12345qwer" required>
        </div>
        <%-- <label for="login"><b>Логин</b></label>
         <input type="text" placeholder="Enter Login" name="login" id="login" pattern="[A-Za-zА-Яа-я0-9]{4,20}" title="пример: login1" required>

         <label for="password"><b>Пароль</b></label>
         <input type="password" placeholder="Enter Password" name="password" id="password" pattern="[a-zA-ZА-Яа-я0-9]{8,20}"  title="пример: 12345qwer" required>--%>
        <hr>
        <div class="modal-footer" style="display: flex; justify-content: center; margin-bottom: 5px;">
            <input type="hidden" name="command" value="registration"/>
            <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
        </div>
        <div class="container-signin">
            <p>У вас уже есть аккаунт? <a href="/login">Войти</a></p>
        </div>
    </div>
</form>
</body>
</html>