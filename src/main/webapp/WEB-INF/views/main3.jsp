<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/a931579f7d.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/css/main3.css"/>" type="text/css"/>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>--%>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"</script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"</script>
    <script type="text/javascript" src="js/filter.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/main2.jsp"/>

<section class="main_content">

    <div class="container" >
        <div style="display: flex">
            <ul >
                <a href="#" <%--value="${g.id}"--%> style="text-transform: capitalize" data-filter="all">All</a>
                <c:forEach var="g" items="${requestScope.genres}">
                    <a href="#" value="${g.id}" style="text-transform: capitalize" data-filter="${g}">${g}</a>
                </c:forEach>

                <%-- <a href="#" &lt;%&ndash;value="${g.id}"&ndash;%&gt; style="text-transform: capitalize" data-filter="B">B</a>--%>
            </ul>
        </div>

        <div class="row">
            <c:forEach var="book" items="${requestScope.books}">
                <c:if test="${not book.isDeleted}">

                    <div class="col-lg-3 col-sm-6 mb-3" data-cat="${book.genre}">
                        <div class="product-card" >
                            <c:choose>
                                <c:when test="${sessionScope.user.role.role eq 'Admin'}">
                                    <div class="btn-d">
                                        <a type="button" <%--href="#edit${book.id}"--%> class="btn-edit"
                                           data-bs-toggle="modal"
                                           data-bs-target="#editModal${book.id}">
                                            <i class="fa-solid fa-gear"></i>
                                        </a>
                                        <div class="modal fade" id="editModal${book.id}" tabindex="-1"
                                             aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <form action="/update_book/${book.id}" method="post"
                                                          enctype="multipart/form-data">
                                                        <div class="modal-header" style="text-align: center">
                                                            <h1 class="modal-title fs-5" id="exampleModalLabel">
                                                                Редактирование</h1>
                                                            <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">

                                                            <label>
                                                                Название книги
                                                                    <%--<input type="text"  placeholder="Имя" aria-label="Имя">--%>
                                                                <input type="text" style="margin-bottom: 10px"
                                                                       class="form-control" name="name"
                                                                       pattern="[a-zA-Zа-яёА-ЯЁ\d\s]{2,60}"
                                                                       title="пример: Дубровский" value="${book.name}"
                                                                       required>
                                                            </label>
                                                            <br>
                                                            <label>
                                                                Автор
                                                                <select class="form-select" name="author" required
                                                                        style="margin-bottom: 10px">
                                                                    <c:forEach var="a" items="${requestScope.authors}">
                                                                        <c:choose>
                                                                            <c:when test="${a eq book.author}">
                                                                                <option value="${a.id}"
                                                                                        selected>${a}</option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${a.id}">${a}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                </select>
                                                            </label>
                                                            <br>
                                                            <label>
                                                                Жанр
                                                                <select class="form-select" style="margin-bottom: 10px"
                                                                        name="genre" required>
                                                                    <c:forEach var="g" items="${requestScope.genres}">
                                                                        <c:choose>
                                                                            <c:when test="${g eq book.genre}">
                                                                                <option value="${g.id}"
                                                                                        style="text-transform: capitalize"
                                                                                        selected>${g}</option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${g.id}"
                                                                                        style="text-transform: capitalize">${g}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                        <%--                                                                        <c:forEach var="g" items="${requestScope.genres}">
                                                                                                                                                    <option value="${g.id}" style="text-transform: capitalize">${g}</option>
                                                                                                                                                </c:forEach>--%>
                                                                    </c:forEach>
                                                                </select>
                                                            </label>
                                                            <br>
                                                            <label>
                                                                Цена
                                                                <input name="cost" type="number" class="form-control"
                                                                       style="margin-bottom: 10px" value="${book.cost}"
                                                                       min="0" step="0.01"
                                                                       pattern="[\d]{1,6}(\.\d{1,2})?"
                                                                       title="пример: 123.22" required>
                                                            </label>
                                                            <br>
                                                            <label>
                                                                Обложка
                                                                <input class="form-control" style="margin-bottom: 5px"
                                                                       id="image_input" name="imageFile"
                                                                       accept="image/*" type='file'/>
                                                            </label>
                                                                <%--<input class="button" type="submit" value="Сохранить">--%>

                                                        </div>
                                                        <div class="modal-footer"
                                                             style="    display: flex;    justify-content: start;">
                                                                <%-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
                                                            <button type="submit" class="btn btn-primary">Сохранить
                                                                изменения
                                                            </button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <a type="button" class="btn-delete" href="/delete/${book.id}">
                                            <i class="fa-solid fa-xmark"></i>
                                        </a>
                                    </div>
                                </c:when>
                            </c:choose>


                            <div class="product-thumb">
                                    <%--картинка--%>
                                <a> <img src="${book.imagePath}"/> </a>
                            </div>
                                <%--Название--%>
                            <div class="product-details">
                                <h4>
                                    <a href="">${book.name}</a>
                                </h4>
                                    <%--Жанр нужен и автор--%>
                                <p>
                                    Автор: ${book.author} <br>
                                    Жанр: ${book.genre}
                                </p>
                                <div class="product-bottom-details d-flex justify-content-betwen">
                                        <%--цена--%>
                                    <div class="product-price">
                                        <small> </small>${book.cost} руб.
                                    </div>
                                        <%--добавить в корзину--%>
                                    <div class="product-links">
                                        <a href="/add_to_cart/${book.id}"><i
                                                class="fa-solid fa-cart-shopping"
                                                style="font-size: x-large"></i> В корзину </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:if>
            </c:forEach>
        </div>
    </div>
</section>
<jsp:include page="/WEB-INF/views/Footer.jsp"/>

</body>
</html>
