<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Договоры</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            color: #343a40;
        }
        header {
            background-color: #0e8585;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }
        h1, h2 {
            color: #343a40;
            margin-bottom: 10px;
        }
        form {
            max-width: 700px;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 6px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        form p {
            margin-bottom: 15px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #07a7a7;
            color: white;
            border: none;
            cursor: pointer;
            margin-top: 10px;
        }
        input[type="submit"]:hover {
            background-color: #034f4f;
        }
        .section {
            max-width: 800px;
            margin: 20px auto;
        }
        .card {
            background: white;
            padding: 15px;
            border-radius: 6px;
            margin: 10px 0;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .info {
            margin-top: 20px;
        }
        .info div {
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<header>
    <h1>Управление договорами</h1>
</header>
<main>
    <form action="" method="post" name="agreementform" id="agreementform">
        <h2>Данные договора</h2>
        <p>
            <label for="id">Номер договора</label>
            <input type="text" name="id" id="id" placeholder="Введите номер договора">
        </p>
        <p>
            <label for="rentPrice">Цена аренды</label>
            <input type="text" name="rentPrice" id="rentPrice" placeholder="Введите цену аренды">
        </p>
        <p>
            <label for="rentPeriod">Период аренды</label>
            <input type="text" name="rentPeriod" id="rentPeriod" placeholder="Введите период аренды">
        </p>
        <p>
            <label for="vinNumber">Вин-номер автомобиля</label>
            <input type="text" name="vinNumber" id="vinNumber" placeholder="Введите вин-номер автомобиля">
            <input name="getCars" type="submit" id="getCars" value="Посмотреть все автомобили">
        </p>
        <p>
            <label for="passportNumber">Номер паспорта</label>
            <input type="text" name="passportNumber" id="passportNumber" placeholder="Введите номер паспорта">
            <input name="getPassports" type="submit" id="getPassports" value="Посмотреть все паспорта">
        </p>
        <p>
            <input name="get" type="submit" id="get" value="Получить договор">
            <input name="create" type="submit" id="create" value="Создать новый договор">
            <input name="update" type="submit" id="update" value="Обновить условия договора">
            <input name="delete" type="submit" id="delete" value="Удалить договор">
            <input name="getAll" type="submit" id="getAll" value="Посмотреть все договоры">
        </p>
    </form>

    <div class="section info">
        <div>${error}</div>
        <div>${wasCreated}</div>
        <div>${wasUpdated}</div>
        <div>${wasDeleted}</div>
    </div>
    <div class="section">
        <h2>По искомому договору</h2>
        <div>${agrem.allInString()}</div>
    </div>

    <div class="section">
        <h2>Договоры</h2>
        <c:forEach var="agreement" items="${agreements}">
            <div class="card">
                <c:out value="${agreement.allInString()}"></c:out>
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