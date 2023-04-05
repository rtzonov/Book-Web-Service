<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/book.css"/>" type="text/css"/>
    <meta charset="utf-8">
    <title>Список книг</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<c:if test="${sessionScope.user.role.role eq 'Admin'}">
    <div class="admin_button_panel">
        <a href="#demo-modal" style="font-size: 20px; text-decoration: none; ">Добавить книгу</a>
    </div>

</c:if>

<div>
    <div class="main_block">
        <c:forEach var="book" items="${requestScope.books}">
            <c:if test="${not book.isDeleted}">
                <div class="car_block">
                    <div class="car_block__brand">
                            ${book.name}
                    </div>
                    <div class="car_block__img">
                        <img src="${book.imagePath}"/>
                    </div>
                    <div class="car_block__characteristics">
                        <strong>Автор:</strong> ${book.author}
                    </div>
                    <br style="background: black">
                    <div class="car_block__characteristics" style="text-transform: capitalize; margin: 0 10px 0 10px;">
                        <strong>Жанр:</strong> ${book.genre}
                    </div>
                    <div class="car_block__price">
                        <strong>Цена:</strong> ${book.cost} BYN
                    </div>
                    <div class="car_block__button">
                        <c:choose>
                            <c:when test="${sessionScope.user.role.role eq 'Admin'}">
                                <a href="#edit${book.id}">Редактировать</a>
                                <a href="/book/delete/${book.id}" style="background-color: red">Удалить</a>
                                <div id="edit${book.id}" class="modal">
                                    <div class="modal__content">
                                        <div class="login">
                                            <form action="/book/update_book/${book.id}" method="post" enctype="multipart/form-data">
                                                <label>
                                                    Название книги
                                                    <input type="text" name="name" pattern="[a-zA-Zа-яёА-ЯЁ\d\s]{2,60}" title="пример: Дубровский" value="${book.name}" required>
                                                </label>
                                                <label>
                                                    Автор
                                                    <select name="author" required>
                                                        <c:forEach var="a" items="${requestScope.authors}">
                                                            <c:choose>
                                                                <c:when test="${a eq book.author}">
                                                                    <option value="${a.id}" selected>${a}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${a.id}">${a}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </label>
                                                <label>
                                                    Жанр
                                                    <select name="genre" required>
                                                        <c:forEach var="g" items="${requestScope.genres}">
                                                            <c:choose>
                                                                <c:when test="${a eq book.author}">
                                                                    <option value="${g.id}"
                                                                            style="text-transform: capitalize"
                                                                            selected>${g}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${g.id}"
                                                                            style="text-transform: capitalize">${g}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <option value="${g.id}"
                                                                    style="text-transform: capitalize">${g}</option>
                                                        </c:forEach>
                                                    </select>
                                                </label>
                                                <label>
                                                    Цена
                                                    <input name="cost" value="${book.cost}" min="0" step="0.01"  pattern="[\d]{1,6}(\.\d{1,2})?"  title="пример: 123.22" required>
                                                </label>
                                                <label>
                                                    Обложка
                                                    <input id="image_input" name="imageFile" accept="image/*" type='file'/>
                                                </label>
                                                <input class="button" type="submit" value="Сохранить">
                                            </form>

                                            <a href="#" class="modal__close">&times;</a>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <a href="/book/add_to_cart/${book.id}">Добавить в корзину</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>

<div id="demo-modal" class="modal">
    <div class="modal__content">
        <div class="login">
            <form action="/book/new_book" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="login">
                <label>
                    Имя книги
                    <input type="text" name="name" pattern="[a-zA-Zа-яёА-ЯЁ\d\s]{2,60}" title="пример: дубровский" required>
                </label>
                <label>
                    Автор
                    <select name="author" required>
                        <c:forEach var="a" items="${requestScope.authors}">
                            <option value="${a.id}">${a}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>
                    Жанр
                    <select name="genre">
                        <c:forEach var="g" items="${requestScope.genres}">
                            <option value="${g.id}" style="text-transform: capitalize">${g}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>
                    Цена
                    <input type="number" min="0" step="0.01" name="cost" pattern="[\d]{1,6}(\.\d{1,2})?"  title="пример: 123.22" required>
                </label>

                <label>
                    Обложка
                    <input id="image_input" name="imageFile" type='file'/>
                </label>
                <input class="button" type="submit" value="Сохранить">
            </form>
            <a href="#" class="modal__close">&times;</a>
        </div>
    </div>
</div>

</body>

</html>