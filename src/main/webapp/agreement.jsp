<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <home>
        <a href="index">Домой</a>
    </home>
    <form action="" method="post" name="agreementform" id="agreementform">
        <h2>Данные договора</h2>
        <p>
<%--            <label for="id">Номер договора</label>--%>
            <input type="hidden" name="id" id="id" placeholder="Введите номер договора" value="${idRet}">
        </p>
        <p>
            <label for="rentPrice">Цена аренды</label>
            <input type="text" name="rentPrice" id="rentPrice" placeholder="Введите цену аренды" value="${rentPriceRet}">
        </p>
        <p>
            <label for="rentPeriod">Период аренды</label>
            <input type="text" name="rentPeriod" id="rentPeriod" placeholder="Введите период аренды" value="${rentPeriodRet}">
        </p>
        <p>
            <label for="passportNumber">Номер паспорта</label>
            <input type="text" name="passportNumber" id="passportNumber" placeholder="Введите номер паспорта" value="${passportNumberRet}">
            <input name="getPassports" type="submit" id="getPassports" value="Посмотреть все паспорта">
        </p>
        <p>
            <label for="vinNumber">Вин-номер автомобиля</label>
            <input type="text" name="vinNumber" id="vinNumber" placeholder="Введите вин-номер автомобиля" value="${vinNumberRet}">
            <input name="getCars" type="submit" id="getCars" value="Посмотреть все автомобили">
        </p>
        <p>
            <input name="get" type="submit" id="get" value="Получить договор">
            <input name="create" type="submit" id="create" value="Создать новый договор с номером">
            <input name="createWithoutId" type="submit" id="createWithoutId" value="Создать новый договор без номера">
            <input name="update" type="submit" id="update" value="Обновить условия договора">
            <input name="getAll" type="submit" id="getAll" value="Посмотреть все договоры">
        </p>
    </form>

    <div class="info">
        <div>${error}</div>
    </div>
    <div class="section">
        <h2>По искомому договору</h2>
        <div>${agrem.allInString()}</div>
    </div>

    <div class="section">
        <h2>Договоры</h2>
        <c:forEach var="agreement" items="${agreements}">
            <div class="card">
                <form action="" method="post" name="agreementform" id="oneAgreementform">
                    <c:out value="${agreement.allInString()}"></c:out>
                    <input type="hidden" name="idOther" id="idOther" value="${agreement.getId()}">
                    <input type="hidden" name="rentPriceOther" id="rentPriceOther" value="${agreement.getRentPrice()}">
                    <input type="hidden" name="rentPeriodOther" id="rentPeriodOther" value="${agreement.getRentPeriod()}">
                    <input type="hidden" name="passportNumberOther" id="passportNumberOther" value="${agreement.getPassportNumber()}">
                    <input type="hidden" name="vinNumberOther" id="vinNumberOther" value="${agreement.getVinNumber()}" >

                    <input name="change" type="submit" id="change" value="Изменить условия договора">
                    <input name="deleteOther" type="submit" id="deleteOther" value="Удалить договор">
                    <p style="text-indent: 25px;">Car: ${carsInfo.removeFirst().allInString()}</p>
                    <p style="text-indent: 25px;">Client: ${clientsInfo.removeFirst().allInString()}</p>
                </form>
            </div>
        </c:forEach>
    </div>

    <div class="section">
        <h2>Паспорта</h2>
        <c:forEach var="passport" items="${passports}">
            <div class="card">
                <c:out value="${passport.allInString()}"></c:out>
            </div>
        </c:forEach>
    </div>

    <div class="section">
        <h2>Автомобили</h2>
        <c:forEach var="car" items="${cars}">
            <div class="card">
                <c:out value="${car.allInString()}"></c:out>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>