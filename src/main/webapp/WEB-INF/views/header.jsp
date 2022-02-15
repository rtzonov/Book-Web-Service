<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/book.css"/>" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="header.bookWebService" var="bookWebService"/>
        <fmt:message key="header.title" var="title"/>
        <fmt:message key="header.log_in" var="log_in"/>
        <fmt:message key="header.Log_out" var="log_out"/>
        <fmt:message key="header.menu" var="menu"/>
        <fmt:message key="header.sign_up" var="sign_up"/>
        <fmt:message key="header.cart" var="cart"/>
    </fmt:bundle>
    <title>Title</title>

</head>
<body>
<div class="header">
    <a class="header__logo">
        ${bookWebService}
    </a>

    <div class="header__links">
        <c:if test="${requestScope.books ne null}">
            <a href="#search"><i class="fa fa-search"></i></a>
        </c:if>
        <a href="/book/menu">${menu}</a>
        <a href="/cart">${cart}</a>
        <c:if test="${sessionScope.user != null}">
            <a href="Controller?command=gotoorderspage">${orders}</a>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.user eq null}">
                <a href="/login">${log_in}</a>
                <a href="/registration">${sign_up}</a>
            </c:when>
            <c:otherwise>
                <a href="/logOut">${log_out}</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div id="search" class="modal">
    <div class="modal__content">
        <div class="login">
            <form action="/book/find_book" method="post">
                <input type="hidden" name="command" value="login">
                <label>
                    Название книги
                    <input type="text" name="name" >
                </label>
                <label>
                    Автор
                    <select name="author">
                        <option value=""></option>
                        <c:forEach var="a" items="${requestScope.authors}">
                            <option value="${a.id}" style="text-transform: capitalize">${a}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>
                    Жанр
                    <select name="genre">
                        <option value=""></option>
                        <c:forEach var="g" items="${requestScope.genres}">
                            <option value="${g.id}" style="text-transform: capitalize">${g}</option>
                        </c:forEach>
                    </select>
                </label>
                <input class="button" type="submit" value="Поиск">
            </form>

            <a href="#" class="modal__close">&times;</a>
        </div>


    </div>
</div>
</body>
</html>