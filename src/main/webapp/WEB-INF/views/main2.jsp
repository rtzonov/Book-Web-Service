<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <title>Title</title>
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/a931579f7d.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/css/main2.css"/>" type="text/css"/>
</head>
<body>

<nav class="navbar navbar-expand-lg" style="background-color: cyan">
    <div class="container-fluid">
        <a class="navbar-brand" style="" href="#">Мир Книг</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" style="" aria-current="page" href="/menu">Каталог</a>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.user.role.role eq 'Admin'}">
                        <li class="nav-item">
                            <a class="nav-link active" data-bs-toggle="modal"
                               data-bs-target="#createModal" aria-current="page" href="#">Добавить книгу</a>
                        </li>
                    </c:when>
                </c:choose>
                <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="/new_book" method="post" enctype="multipart/form-data">
                                <div class="modal-header" style="text-align: center">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Редактирование</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body" >

                                    <label>
                                        Название книги
                                        <%--<input type="text"  placeholder="Имя" aria-label="Имя">--%>
                                        <input type="text" style="margin-bottom: 10px"  class="form-control" name="name" pattern="[a-zA-Zа-яёА-ЯЁ\d\s]{2,60}" title="пример: Дубровский" required>
                                    </label>
                                    <br>
                                    <label>
                                       <%-- <form action="/author/new_Author" method="post" enctype="multipart/form-data">--%>
                                        Автор
<%--                                        <select class="form-select" name="author" required style="margin-bottom: 10px">
&lt;%&ndash;                                            <c:forEach var="a" items="${requestScope.authors}">
                                                <option value="${a.id}">${a}</option>
                                            </c:forEach>&ndash;%&gt;
                                        </select>--%>

<%--                                            <div class="input-group">
                                                <input type="text" class="form-control" id="authorData" aria-label="Text input with segmented dropdown button" >
                                                <button onclick="arer()" onselect="arer()" type="button" id="dropDownAuthorData" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
                                                    <span class="visually-hidden">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu dropdown-menu-end">
                                                    <c:forEach var="a" items="${requestScope.authors}">
                                                    <li>
                                                        <a class="dropdown-item" value="${a.id}" href="#">${a}</a>
                                                    </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>--%>
                                                <div class="input-group">
                                                    <%--<span class="input-group-text">First and last name</span>--%>
                                                    <input type="text" placeholder="Имя" aria-label="First name" class="form-control" name="authorName" required>
                                                    <input type="text" placeholder="Фамилия" aria-label="Last name" class="form-control" name="authorLastname" required>
                                                </div>

                                           <%-- </form>--%>
                                    </label>
                                    <br>
                                    <label>
                                        Жанр
                                        <select class="form-select" style="margin-bottom: 10px" name="genre" required>
                                            <c:forEach var="g" items="${requestScope.genres}">
                                                <option value="${g.id}" style="text-transform: capitalize">${g}</option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                    <br>
                                    <label>
                                        Цена
                                        <input type="number" name="cost" class="form-control" style="margin-bottom: 10px"  <%--value="${book.cost}"--%> min="0" step="0.01"  pattern="[\d]{1,6}(\.\d{1,2})?"  title="пример: 123.22" required>
                                    </label>
                                    <br>
                                    <label>
                                        Обложка
                                        <input class="form-control" style="margin-bottom: 5px" id="image_input" name="imageFile" <%--accept="image/*"--%> type='file'/>
                                    </label>
                                    <%--<input class="button" type="submit" value="Сохранить">--%>
                                </div>
                                <div class="modal-footer" style="    display: flex;    justify-content: start;">
                                    <%-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
                                    <button type="submit" class="btn btn-primary" >Сохранить</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <%--пишу поиск--%>
                <form class="d-flex" role="search" action="/find_book" method="post">
                    <input class="form-control me-1" style="width: 400px; margin-left: 5px;" type="text" name="name"
                           placeholder="Введите книгу или автора" aria-label="Search">

                    <button class="button" type="submit">
                        <i class="fa-solid fa-magnifying-glass" style="font-size: x-large"></i>
                    </button>
                    <%-- <button class="fa-solid fa-magnifying-glass" style="background-color: cyan" type="submit"></button>--%>
                </form>

            </ul>

            <%-- с правой стороны (ms)--%>
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <%--с правой стороны корзина--%>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/cart">
                        <i class="fa-solid fa-cart-shopping" style="font-size: x-large"></i>
                    </a>
                </li>
                <%--//с правой стороны челик--%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#">
                        <i class="fa-regular fa-user" style="font-size: x-large"></i>
                    </a>
                    <ul class="dropdown-menu" style="    right: 0;
    left: auto;">
                        <c:choose>
                            <c:when test="${sessionScope.user eq null}">
                                <li><a class="dropdown-item" href="/registration">Регистрация </a></li>
                                <li><a class="dropdown-item" href="/login">Авторизация</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="dropdown-item" href="#" data-bs-toggle="modal"
                                       data-bs-target="#modal-edit">Настройки</a></li>
                                <li><a class="dropdown-item" href="/logOut">Выход</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="modal fade" id="modal-edit" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel1">Редактирование профиля</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
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
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Сохранить изменения</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous">

</script>
</body>
</html>
