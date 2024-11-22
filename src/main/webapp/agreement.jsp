<%--
  Created by IntelliJ IDEA.
  User: Volodya
  Date: 10.11.2024
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>договор</title>
</head>
<body >
    <h1>Договор</h1>
    <form action="" method="post" name="agreementform" id="agreementform">
        <p><input type="text" name="id" id="id" value="" size="25" />
            <small> Номер договора</small>
        </p>
        <p><input type="text" name="rentPrice" id="rentPrice" value="" size="25" />
            <small> Цена аренды</small>
        </p>
        <p><input type="text" name="rentPeriod" id="rentPeriod" value="" size="25" />
            <small> Период аренды</small>
        </p>
        <p><input type="text" name="vinNumber" id="vinNumber" value="" size="25" />
            <small> Вин-номер арендуемого автомобиля. Обязательно должен существовать.</small> <a><input name="getCars" type="submit" id="getCars" value="Посмотреть все автомобили" /></a>
        </p>
        <p><input type="text" name="passportNumber" id="passportNumber" value="" size="25" />
            <small> Номер паспорта. Обязательно должен существовать.</small> <a>   </a> <a><input name="getPassports" type="submit" id="getPassports" value="Посмотреть все паспорта" /></a>
        </p>
        <a><input name="get" type="submit" id="get" value="Получить договор по номеру" />
        </a>
        <a><input name="create" type="submit" id="create" value="Внести в базу новый договор" />
        </a>
        <p></p>
        <a><input name="update" type="submit" id="update" value="Обновить условия договора" />
        </a>
        <a><input name="delete" type="submit" id="delete" value="Удалить договор из базы по номеру" />
        </a>
        <a><input name="getAll" type="submit" id="getAll" value="Посмотреть все договоры" />
        </a>
    </form>
    <a>Что-то пошло не так: ${error}</a><p></p>
    <a>Создалось: ${wasCreated}</a> <p></p>
    <a>Обновилось: ${wasUpdated}</a> <p></p>
    <a>Удалилось: ${wasDeleted}</a><p></p>
    <h2>По искомому договору:</h2>
    <a>ID: ${id}</a>
    <a>RENTPRICE: ${rentPrice}</a>
    <a>RENTPERIOD: ${rentPeriod}</a>
    <a>VINNUMBER: ${vinNumber}</a>
    <a>PASSPORTNUMBER: ${passportNumber}</a>
    <h1>Договоры</h1>
    <c:forEach var="agreement" items="${agreements}">
        <div>
            <c:out value="${agreement.allInString()}" />
        </div>
    </c:forEach>

    <h1>Паспорта</h1>
    <c:forEach var="passport" items="${passports}">
        <div>
            <c:out value="${passport.allInString()}" />
        </div>
    </c:forEach>
    <h1>Автомобили</h1>
    <c:forEach var="car" items="${cars}">
        <div>
            <c:out value="${car.allInString()}" />
        </div>
    </c:forEach>
</body>
</html>
