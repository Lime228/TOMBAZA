<%--
  Created by IntelliJ IDEA.
  User: Volodya
  Date: 10.11.2024
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Штрафы</title>
    <style>
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

        label, small {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="submit"] {
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
    <h1>Парковка</h1>
</header>
<main>
    <home><a href="index">Домой</a></home>
    <form action="" method="post" name="parkingform" id="parkingform">
        <div class="section">
            <p>
                <label for="id">Номер парковки</label>
                <input type="text" name="id" id="id" placeholder="Введите номер парковки" value="${fn:escapeXml(idRet)}">
                <input name="get" type="submit" id="get" value="Получить парковку по номеру">
            </p>
            <p>
                <label for="maxCapacity">Максимальная вместимость</label>
                <input type="text" name="maxCapacity" id="maxCapacity" placeholder="Введите максимальную вместимость"
                       value="${fn:escapeXml(maxCapacityRet)}">
            </p>
            <p>
                <label for="parkingAddress">Адрес</label>
                <input type="text" name="parkingAddress" id="parkingAddress" placeholder="Введите адрес парковки"
                       value="${fn:escapeXml(parkingAddressRet)}">
            </p>
            <p>
                <input name="create" type="submit" id="create" value="Внести в базу новую парковку с номером">
                <input name="createWithoutId" type="submit" id="createWithoutId" value="Внести в базу новую парковку">
            <p></p>
            <input name="update" type="submit" id="update" value="Обновить данные парковки">
            <input name="getAll" type="submit" id="getAll" value="Посмотреть все парковки">
            </p>
        </div>
    </form>
    <div class="info">
        <p>${fn:escapeXml(error)}</p>
    </div>

    <c:if test="${not empty parkings}">
        <div class="section">
            <h2>Парковки</h2>
            <c:forEach var="parking" items="${parkings}">
                <div class="card">
                    <form action="" method="post" name="oneParkingform" id="oneParkingform">
                        <c:out value="${fn:escapeXml(parking.allInString())}"/>
                        <input type="hidden" name="idOther" id="idOther" value="${fn:escapeXml(parking.getId())}">
                        <input type="hidden" name="maxCapacityOther" id="maxCapacityOther"
                               value="${fn:escapeXml(parking.getMaxCapacity())}">
                        <input type="hidden" name="parkingAddressOther" id="parkingAddressOther"
                               value="${fn:escapeXml(parking.getParkingAddress())}">


                        <input name="change" type="submit" id="change" value="Изменить парковку">
                        <input name="deleteOther" type="submit" id="deleteOther" value="Удалить парковку">
                            <%--                    <p style="text-indent: 25px;">Car: ${carsInfo.removeFirst().allInString()}</p>--%>
                            <%--                    <p style="text-indent: 25px;">Client: ${clientsInfo.removeFirst().allInString()}</p>--%>
                    </form>
                </div>
            </c:forEach>
        </div>
    </c:if>

</main>
</body>
</html>

