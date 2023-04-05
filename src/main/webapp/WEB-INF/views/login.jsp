<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/login.css"/>" type="text/css"/>
    <title>Авторизация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/a931579f7d.js" crossorigin="anonymous"></script>
</head>
<body>
<%--<jsp:include page="/WEB-INF/views/header.jsp"/>--%>
<jsp:include page="/WEB-INF/views/main2.jsp"/>


    <form action="/do_login" method="post">
        <div class="container-all">

                <h1 style="margin-top: 20px; margin-bottom: 10px">Авторизация</h1>
                <p>Пожалуйста заполните все поля чтобы войти.</p>
                <hr>
                    <div class="mb-3">
                        <c:if test="${error ne null}">
                            <%--<p> ${error} </p>--%>
                            <p style="color: red">Неправильный логин или пароль!</p>
                        </c:if>
                        <label for="login" class="form-label"><b>Логин</b></label>
                        <input type="text" class="form-control"  placeholder="Введите логин" name="login" id="login" pattern="[A-Za-zА-Яа-я0-9]{4,20}"
                               title="пример: login1" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label"><b>Пароль</b></label>
                        <input type="password" class="form-control" placeholder="Введите пароль" name="password" id="password"
                               pattern="[A-Za-zА-Яа-я0-9]{8,20}" title="пример: 12345qwer" required>
                    </div>
<%--                <label for="login"><b>Логин</b></label>
                <input type="text" placeholder="Enter Login" name="login" id="login" pattern="[A-Za-zА-Яа-я0-9]{4,20}"
                       title="пример: login1" required>

                <label for="password"><b>Пароль</b></label>
                <input type="password" placeholder="Enter Password" name="password" id="password"
                       pattern="[A-Za-zА-Яа-я0-9]{8,20}" title="пример: 12345qwer" required>--%>
                <hr>

                <div class="modal-footer" style="display: flex; justify-content: center; margin-bottom: 5px;">

                    <input type="hidden" name="command" value="logination"/>
                    <button type="submit" class="btn btn-primary">Войти</button>
                </div>

            <div class="container-signin">
                <p>Если у вас не зарегистрирован акаунт, создайте его <a href="/registration">сейчас</a></p>
            </div>

        </div>
    </form>

<br/>
</body>
</html>