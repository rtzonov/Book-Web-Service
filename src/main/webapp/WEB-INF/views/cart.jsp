<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.fitr.bntu.bookwebservice.util.SessionAttribute" %>
<%@ page import="com.fitr.bntu.bookwebservice.DTO.BookDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/cart.css"/>" type="text/css">
    <title>cart</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="shopping-cart">
    <!-- Title -->
    <div class="title">
        Корзина
    </div>

    <c:forEach var="b" items="${sessionScope.cart}">
        <div class="item">
            <div class="buttons">
                <span class="delete-btn"></span>
                <span class="like-btn"></span>
            </div>

            <div class="image">
                <img src="${b.imagePath}" alt="" />
            </div>

            <div class="description">
                <span style="font-size: 28px; color: black; margin-bottom: 25px; text-transform: uppercase">${b.name}</span>
                <span style="font-size: 16px; margin-bottom: 15px;"><a href="https://www.google.by/search?q=${b.author.name}%20${b.author.lastName}">${b.author}</a></span>
                <span style="font-size: 16px; margin-bottom: 15px; text-transform: capitalize""><a href="https://www.google.by/search?q=${b.genre}">${b.genre}</a></span>
                <%--Кнопка убрать--%>
                <span style="font-size: 22px; margin-top: 10px">Убрать:<a href="/book/remove_from_cart/${b.id}" style="font-size: 24px; color: red; text-decoration: none">&times;</a></span>
            </div>

            <div class="total-price" style="font-size: 24px; color:black; text-align: right">BYN: ${b.cost}</div>
        </div>
    </c:forEach>

    <br>

    <%--Это расчет цены в корзине--%>
    <div style="text-align: center">
        <c:choose>
            <c:when test="${sessionScope.cart.size() > 1}">
                <%
                    BigDecimal totalCost = new BigDecimal("0");

                    for (BookDTO book : (List<BookDTO>) session.getAttribute(SessionAttribute.CART)) {
                        totalCost = totalCost.add(book.getCost());
                    }

                    request.setAttribute("total_cost", totalCost);
                %>
                Полная цена: ${requestScope.total_cost}
                <br>

                <div class="payment" style="text-align: center">
                    <form method="post" action="">
                        <button name="payment" value="${requestScope.total_cost}" style="padding: 10px; background-color: lawngreen; border-style: none">Оплатить</button>
                    </form>
                </div>
            </c:when>
            <c:when test="${sessionScope.cart.size() == 1}">
                Полная цена: ${sessionScope.cart[0].getCost()}
                <br>

                <div class="payment" style="text-align: center">
                    <form method="get" action="/payment">
                        <%--Кнопка оплатить--%>
                        <button name="payment" value="${sessionScope.cart[0].getCost()}" style="padding: 10px; background-color: lawngreen; border-style: none">Оплатить</button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <p style="margin-bottom: 20px;">Ваша корзина пуста</p>
            </c:otherwise>
        </c:choose>
    </div>

</div>
</style>
</body>
</html>
