<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.fitr.bntu.bookwebservice.util.SessionAttribute" %>
<%@ page import="com.fitr.bntu.bookwebservice.DTO.BookDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/a931579f7d.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/css/cart2.css"/>" type="text/css">
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"</script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"</script>
    <script type="text/javascript" src="js/cartJS.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/main2.jsp"/>

<div class="container-fl">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Товар</th>
            <th scope="col"></th>
            <th scope="col" style="text-align: center">Цена</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <c:forEach var="b" items="${sessionScope.cart}">
            <tbody>
            <tr>
                <td>
                    <img src="${b.imagePath}">
                </td>
                <td>
                    <a>
                            ${b.name} <br>
                        Автор: ${b.author} <br>
                        Жанр: ${b.genre} <br>
                    </a>
                </td>

                <td>
                    <div class="price-cart">
                        <a>
                                ${b.cost} руб.
                        </a>
                    </div>
                </td>
                <td>
                    <div class="garbage-col">

                        <a href="/book/remove_from_cart/${b.id}">
                            <i class="fa-solid fa-trash"></i>
                        </a>

                    </div>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
    <div class="price-total">
        <c:choose>
            <c:when test="${sessionScope.cart.size() > 1}">
                <%
                    BigDecimal totalCost = new BigDecimal("0");

                    for (BookDTO book : (List<BookDTO>) session.getAttribute(SessionAttribute.CART)) {
                        totalCost = totalCost.add(book.getCost());
                    }

                    request.setAttribute("total_cost", totalCost);
                %>
                <p>
                    Стоимость товаров: ${requestScope.total_cost} руб.
                </p>
                <div class="modal-footer">
                        <%-- <form method="post" action="/payment">--%>
                    <button name="payment" class="btn btn-primary" value="${requestScope.total_cost}"
                            data-bs-toggle="modal" data-bs-target="#modal-order">Оформить заказ
                    </button>
                        <%--</form>--%>
                </div>
            </c:when>
            <c:when test="${sessionScope.cart.size() == 1}">
                <p>
                    Стоимость товаров: ${sessionScope.cart[0].getCost()} руб.
                </p>
                <div class="modal-footer">
                        <%--<form method="get" action="/payment">--%>
                        <%--Кнопка оплатить--%>
                    <button name="payment" class="btn btn-primary" value="${sessionScope.cart[0].getCost()}"
                            data-bs-toggle="modal" data-bs-target="#modal-order">Оформить заказ
                    </button>
                        <%--</form>--%>
                </div>
            </c:when>
            <c:otherwise>
                <div class="empty-cart">
                    <p>
                        <i style="font-size: 90px;
    margin-bottom: 15px;
    margin-top: 10px" class="fa-regular fa-face-sad-tear"></i><br>
                        Ваша корзина пуста
                    </p>
                </div>
            </c:otherwise>
        </c:choose>
        <script>
            function getValue(){
                var data = document.getElementsByName("r");
                var a = document.getElementById('firsttry1'); //or grab it by tagname etc

               /* var firsttry = document.getElementById("firsttry1")*/

                for(var i = 0; i<=data.length;i++){
                    if(data[i].checked){

                        console.log(data[i].value)
                        if(data[0].checked){
                            console.log(1234)
                            /*a.href = "/payment";*/
                            a.action = "/payment";
                            /*$("#firsttry1").addHref('hide');*/
                        }
                    }

                }
            }
        </script>
<%--        <c:choose>
            <c:when>

            </c:when>
        </c:choose>--%>



        <%--<button class="b-7">input</button>--%>

    </div>
    <div class="modal fade" id="modal-order" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Оформление покупки</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="head-text">
                        <P>
                            Данные покупателя
                        </P>
                    </div>
                    <c:choose>
                        <c:when test="${sessionScope.user eq null}">
                            <div class="row" style="margin-bottom: 10px">
                                <div class="col">
                                    Имя
                                    <input type="text" class="form-control" placeholder="Имя" aria-label="Имя">
                                </div>
                                <div class="col">
                                    Фамилия
                                    <input type="text" class="form-control" placeholder="Фамилия" aria-label="Фамилия">
                                </div>
                            </div>
                            <div class="row" style="margin-bottom: 10px">
                                <div class="col">
                                    Телефон
                                    <input type="text" class="form-control" placeholder="Номер телефона"
                                           aria-label="Номер телефона">
                                </div>
                                <div class="col">
                                    E-mail
                                    <input type="text" class="form-control" placeholder="E-mail адрес"
                                           aria-label="E-mail адрес">
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="row" style="margin-bottom: 10px">
                                <div class="col">
                                    Телефон
                                    <input type="text" class="form-control" placeholder="Номер телефона"
                                           aria-label="Номер телефона">
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>


                    <div class="head-text">
                        Адрес <br>
                    </div>
                    <div class="row" style="margin-bottom: 10px">
                        <div class="col">
                            Город(Населенный пункт (например: Минск, Молодечно, Залесье))
                            <input type="text" class="form-control" placeholder="Например: Минск, Молодечно, Залесье"
                                   aria-label="Например: Минск, Молодечно, Залесье">
                        </div>
                        <div class="col">
                            Улица(ул., дом, корпус, кв. (например: ул.Ивановская 7 к.2 кв.33))
                            <input type="text" class="form-control" placeholder="например: ул.Ивановская 7 к.2 кв.33"
                                   aria-label="например: ул.Ивановская 7 к.2 кв.33">
                        </div>
                    </div>

                    <div class="head-text">
                        <p>
                            Способ доставки
                        </p>
                    </div>
                    <%--<form>--%>
                    <div class="form-check" style="margin-top: 5px">
                        <input class="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault1"
                               checked>
                        <label class="form-check-label" for="flexRadioDefault1">
                            <p>
                                Самовывоз (г. ..., ул. ..., ...) - 0 руб.<br>
                                Режим работы: 10:00 - 19:00, обед 14:00 - 15:00, выходные вс., пн.<br>
                                Срок хранения 7 дней, за каждый последующий день взимается плата.
                            </p>

                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault2">
                        <label class="form-check-label" for="flexRadioDefault2">
                            <p>
                                Почтой (..., по РБ) - 5.6 руб.<br>
                                Заказ высылается почтой по Республике Беларусь.<br>
                                Срок хранения в почтовом отделении 10 дней.
                            </p>
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault3">
                        <label class="form-check-label" for="flexRadioDefault3">
                            <p>
                                Курьером (..., по РБ) - 9 руб.<br>
                                Доставка курьером по Республике Беларусь.
                            </p>
                        </label>
                    </div>
                    <%--</form>--%>
                    <%-- тут кнопка по которой открываеться модальное окно где появляется список с точками которые можно выбрать раздел в бутстрапе радиос--%>
                    <%-- <script src="/WEB-INF/cartJS.js"></script>--%>
                    <div class="head-text">
                        <p>
                            Способ оплаты
                        </p>
                    </div>
                    <form>
                        <label>
                            <input type="radio" value="1" name="r">
                            1
                        </label>
                        <label>
                            <input type="radio" value="2" name="r">
                            2
                        </label>
                        <label>
                            <input type="radio" value="3" name="r">
                            3
                        </label>
                        <a type="button"  class="button" onclick="getValue()">Radio</a>
                        <%--<a class="123" href="" data-bs-dismiss="modal" id="firsttry1">2345623456</a>--%>
                        <%--<div class="form-check" style="margin-top: 5px">
                            <input class="form-check-input" type="radio" name="flexRadioDefault2" value="firstCheck"
                                   id="flexRadioDefault11"
                                   checked>
                            <label class="form-check-label" for="flexRadioDefault11">
                                <p>
                                    Оплата наличными (при получении заказа)
                                </p>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="flexRadioDefault2" value="secondCheck"
                                   id="flexRadioDefault22">
                            <label class="form-check-label" for="flexRadioDefault22">
                                <p>
                                    Оплата банковской карточкой (предварительная оплата)
                                </p>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="flexRadioDefault2" value="thirdCheck"
                                   id="flexRadioDefault33">
                            <label class="form-check-label" for="flexRadioDefault33">
                                <p>
                                    Оплата банковской карточкой (при получении заказа)
                                </p>
                            </label>
                        </div>--%>
<%--                        <div>
                            <button id="submit1">Submit</button>
                        </div>--%>
                    </form>
                    <div class="head-text">
                        <p>
                            <c:choose>
                                <c:when test="${sessionScope.cart.size() > 1}">
                                    Стоимость товаров: ${requestScope.total_cost} руб.<br>
                                    + выбраный способ доставки руб.

                                </c:when>
                                <c:otherwise>
                                    Стоимость товаров: ${sessionScope.cart[0].getCost()} руб.<br>
                                    + выбраный способ доставки руб.
                                </c:otherwise>
                            </c:choose>
                        </p>
                    </div>
                </div>
                <%--тут кнопка--%>


                <div class="modal-footer">
                    <%--<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> /payment--%>
                    <form method="get" action="" id="firsttry1">
                    <button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Оформить заказ</button>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/Footer.jsp"/>

</body>
</html>
