<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/a931579f7d.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>" type="text/css"/>
</head>
<body>
<footer>
    <section class="footer">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-6">
                    <h4>
                        Информация
                    </h4>
                    <ul class="list-unstyled">
                        <li>Общество с ограниченной ответственностью «Мир-Бел»
                            Предприятие зарегистрировано в ЕГР за № 123456789 решением Мингорисполкома от 01.01.2020г.
                            Интернет-магазин Мир книг.by зарегистрирован в Торговом реестре Республики Беларусь.</li>
                     <%--     <li> <a>О магазине</a></li>
                           <li><a>Оплата и доставка</a></li>
                          <li> <a>Контакты</a></li>--%>

                    </ul>
                </div>
                <div class="col-md-3 col-6">
                    <h4>
                        Время работы:
                    </h4>
                    <ul class="list-unstyled">
                        <li>г. Минск, ул. Пушкинская, 1</li>
                        <li>Пн-Вс: 9:00 - 18:00</li>
                        <li>без перерыва</li>
                    </ul>
                </div>
                <div class="col-md-3 col-6">
                    <h4>
                        Контакты
                    </h4>
                    <ul class="list-unstyled">
                        <li><a href="tel: +375(33)3456787" style="color: black">+375(33)345-67-87 </a> </li>
                        <li><a href="tel: +375(33)3456788" style="color: black">+375(33)345-67-88 </a> </li>
                        <li><a href="tel: +375(33)3456789" style="color: black">+375(33)345-67-89  </a> </li>
                    </ul>
                </div>
                <div class="col-md-3 col-6">
                    <h4>
                        Мы в социальных сетях:
                    </h4>
                    <div class="footer-icons">
                        <a href="#"><i class="fa-brands fa-facebook" style="color: #085ae7;"></i></a>
                        <a href="#"><i class="fa-brands fa-vk" style="color: #0758e4;"></i></a>
                        <a href="#"><i class="fa-brands fa-youtube" style="color: #ed0c2e;"></i></a>
                        <a href="#"><i class="fa-brands fa-instagram" style="color: #fc9403;"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
</footer>
</body>
</html>
