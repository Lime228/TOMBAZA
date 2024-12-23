<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Договоры</title>
    <style>
        /* Основные стили */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
        }

        header {
            background-color: #58b092;
            color: white;
            padding: 15px;
            text-align: center;
        }

        main {
            padding: 20px;
        }

        a {
            display: inline-block;
            margin: 10px 0;
            color: #58b092;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        h1, h2 {
            color: #000000;
        }

        form {
            margin-bottom: 20px;
        }

        form p {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="submit"],
        input[type="hidden"] {
            padding: 10px;
            margin-right: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            background-color: #58b092;
            color: white;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #3c7562;
        }

        .section {
            margin-top: 30px;
            padding: 20px;
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 10px;
        }

        .card {
            padding: 15px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        .info {
            color: #0d9d9d;
        }

        .info div:empty {
            display: none;
        }


        .card p {
            margin: 10px 0;
        }

        home {
            margin: 20px 0;
        }

        home a {
            display: inline-block;
            margin: 0 10px;
            padding: 10px 20px;
            color: white;
            background-color: #58b092;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        home a:hover {
            background-color: #3c7562;
        }
    </style>
</head>
<body>

<header>
    <h1>Управление договорами</h1>
</header>
<main>
    <%--    пагинация, крутая статистика--%>
    <home>
        <a href="index">Домой</a>
    </home>
    <form action="" method="post" name="agreementform" id="agreementform">
        <div class="section">
            <h2>Данные договора</h2>
            <p>
                <label for="id">Номер договора</label>
                <input type="text" name="id" id="id" placeholder="Введите номер договора"
                       value="${fn:escapeXml(idRet)}">
                <input name="get" type="submit" id="get" value="Найти по номеру">
            </p>
            <p>
                <label for="rentPrice">Цена аренды</label>
                <input type="text" name="rentPrice" id="rentPrice" placeholder="Введите цену аренды"
                       value="${fn:escapeXml(rentPriceRet)}">
            </p>
            <p>
                <label for="rentPeriod">Период аренды</label>
                <input type="text" name="rentPeriod" id="rentPeriod" placeholder="Введите период аренды"
                       value="${fn:escapeXml(rentPeriodRet)}">
            </p>
            <p>
                <label for="passportNumber">Номер паспорта</label>
                <input type="text" name="passportNumber" id="passportNumber" placeholder="Введите номер паспорта"
                       value="${fn:escapeXml(passportNumberRet)}">
                <input name="getPassports" type="submit" id="getPassports" value="Посмотреть все паспорта">
                <input name="getByPassport" type="submit" id="getByPassport" value="Найти по паспорту">
            </p>
            <p>
                <label for="vinNumber">Вин-номер автомобиля</label>
                <input type="text" name="vinNumber" id="vinNumber" placeholder="Введите вин-номер автомобиля"
                       value="${fn:escapeXml(vinNumberRet)}">
                <input name="getCars" type="submit" id="getCars" value="Посмотреть все автомобили">
                <input name="getByCar" type="submit" id="getByCar" value="Найти по автомобилю">
            </p>
            <p>
                <input name="create" type="submit" id="create" value="Создать новый договор с номером">
                <input name="createWithoutId" type="submit" id="createWithoutId"
                       value="Создать новый договор без номера">
            <p></p>
            <input name="update" type="submit" id="update" value="Обновить условия договора">
            <input name="getAll" type="submit" id="getAll" value="Посмотреть все договоры">
            </p>
        </div>
    </form>

    <div class="info">
        <div>${fn:escapeXml(error)}</div>
    </div>

    <c:if test="${not empty agreements}">
        <div class="section">
            <h2>Договоры</h2>
            <a>Найдено договоров: ${fn:escapeXml(allAgreements)}</a>
            <c:forEach var="agreement" items="${agreements}">
                <div class="card">
                    <form action="" method="post" name="agreementform" id="oneAgreementform">
                        <a>
                            <i>Номер договора: <b>${fn:escapeXml(agreement.getId())}</b></i>
                            <input type="hidden" name="idOther" id="idOther" value="${fn:escapeXml(agreement.getId())}"
                            >
                            <hr>
                            <i>Стоимость аренды: <b>${fn:escapeXml(agreement.getRentPrice())}</b> </i>
                            <input type="hidden" name="rentPriceOther" id="rentPriceOther"
                                   value="${fn:escapeXml(agreement.getRentPrice())}">
                            <hr>
                            <i>Длительность аренды: <b>${fn:escapeXml(agreement.getRentPeriod())}</b> </i>
                            <input type="hidden" name="rentPeriodOther" id="rentPeriodOther"
                                   value="${fn:escapeXml(agreement.getRentPeriod())}">
                            <hr>
                            <i>Номер паспорта: <b>${fn:escapeXml(agreement.getPassportNumber())}</b> </i>
                            <input type="hidden" name="passportNumberOther" id="passportNumberOther"
                                   value="${fn:escapeXml(agreement.getPassportNumber())}">
                            <hr>
                            <i>ВИН-номер автомобиля: <b>${fn:escapeXml(agreement.getVinNumber())}</b> </i>
                            <input type="hidden" name="vinNumberOther" id="vinNumberOther"
                                   value="${fn:escapeXml(agreement.getVinNumber())}">
                            <hr>
                        </a>
                        <p></p>
                        <input name="change" type="submit" id="change" value="Изменить условия договора">
                        <input name="deleteOther" type="submit" id="deleteOther" value="Удалить договор">
                        <div class="card">
                            <p>Car: ${fn:escapeXml(carsInfo.removeFirst().allInString())}</p>
                        </div>
                        <div class="card">
                            <p> Client: ${fn:escapeXml(clientsInfo.removeFirst().allInString())}</p>
                        </div>
                    </form>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${not empty passports}">
        <div class="section">
            <h2>Паспорта</h2>
            <a>Найдено паспортов: ${fn:escapeXml(passports.size())}</a>
            <c:forEach var="passport" items="${passports}">
                <div class="card">
                    <i>Номер паспорта: <b>${fn:escapeXml(passport.getId())}</b></i>
                    <hr>
                    <i>Номер телефона: <b>${fn:escapeXml(passport.getPhoneNumber())}</b></i>
                    <hr>
                    <i>Адрес: <b>${fn:escapeXml(passport.getAddress())}</b></i>
                    <hr>
                    <i>ФИО: <b>${fn:escapeXml(passport.getName())}</b></i>
                    <hr>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${not empty cars}">
        <div class="section">
            <h2>Автомобили</h2>
            <a>Найдено автомобилей: ${fn:escapeXml(cars.size())}</a>
            <c:forEach var="car" items="${cars}">
                <div class="card">
                    <i>ВИН-номер: <b>${fn:escapeXml(car.getId())}</b></i>
                    <hr>
                    <i>Цвет: <b>${fn:escapeXml(car.getColor())}</b></i>
                    <hr>
                    <i>Бренд: <b>${fn:escapeXml(car.getBrand())}</b></i>
                    <hr>
                    <i>Модель: <b>${fn:escapeXml(car.getModelName())}</b></i>
                    <hr>
                    <i>Год выпуска: <b>${fn:escapeXml(car.getReleaseYear())}</b></i>
                    <hr>
                    <i>Парковочное место: <b>${fn:escapeXml(car.getParkingPlaceId())}</b></i>
                    <hr>
                    <i>Гос. номер: <b>${fn:escapeXml(car.getNumber())}</b></i>
                    <hr>
                </div>
            </c:forEach>
        </div>
    </c:if>
</main>
</body>
</html>