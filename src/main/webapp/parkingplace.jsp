<%--
  Created by IntelliJ IDEA.
  User: Volodya
  Date: 10.11.2024
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h1>Парковочное место</h1>
</header>
<main>
    <home><a href="index">Домой</a></home>
    <form action="" method="post" name="parkingform" id="parkingform">
        <div class="section">
            <p>
                <label for="id">ID места</label>
                <input type="text" name="id" id="id" placeholder="Введите id места" value="${idRet}">
                <input name="get" type="submit" id="get" value="Получить место по ID">
            </p>
            <p>
                <label for="occupiedSlot">Номер места</label>
                <input type="text" name="occupiedSlot" id="occupiedSlot" placeholder="Введите номер места" value="${occupiedSlotRet}">
                <input name="getByOccupiedSlot" type="submit" id="getByOccupiedSlot" value="Получить место по номеру">
            </p>
            <p>
                <label for="parkingId">Номер парковки</label>
                <input type="text" name="parkingId" id="parkingId" placeholder="Введите номер парковки" value="${parkingIdRet}">
                <input name="getByParkingId" type="submit" id="getByParkingId" value="Получить место по номеру парковки">
                <input name="getParkings" type="submit" id="getParkings" value="Посмотреть все парковки">
            </p>
            <p>
                <label for="floor">Этаж</label>
                <input type="text" name="floor" id="floor" placeholder="Введите этаж места" value="${floorRet}">
                <input name="getByFloor" type="submit" id="getByFloor" value="Получить место по этажу">
            </p>
            <p>
                <input name="create" type="submit" id="create" value="Внести в базу новое место с ID">
                <input name="createWithoutId" type="submit" id="createWithoutId" value="Внести в базу новое место">
            <p></p>
            <input name="update" type="submit" id="update" value="Обновить данные места">
            <input name="getAll" type="submit" id="getAll" value="Посмотреть все места">
            </p>
        </div>
    </form>
    <div class="info">
        <p>${error}</p>
    </div>

    <div class="section">
        <h2>Парковочные места</h2>
        <c:forEach var="pp" items="${parkingPlaces}">
            <div class="card">
                <form action="" method="post" name="oneParkingform" id="oneParkingform">
                    <c:out value="${pp.allInString()}"></c:out>
                    <input type="hidden" name="idOther" id="idOther" value="${pp.getId()}">
                    <input type="hidden" name="occupiedSlotOther" id="occupiedSlotOther" value="${pp.getOccupiedSlot()}">
                    <input type="hidden" name="parkingIdOther" id="parkingIdOther" value="${pp.getParkingId()}">
                    <input type="hidden" name="floorOther" id="floorOther" value="${pp.getFloor()}">


                    <input name="change" type="submit" id="change" value="Изменить место">
                    <input name="deleteOther" type="submit" id="deleteOther" value="Удалить место">
                        <%--                    <p style="text-indent: 25px;">Car: ${carsInfo.removeFirst().allInString()}</p>--%>
                        <%--                    <p style="text-indent: 25px;">Client: ${clientsInfo.removeFirst().allInString()}</p>--%>
                </form>
            </div>
        </c:forEach>
    </div>

    <div class="section">
        <h2>Парковки</h2>
        <c:forEach var="pp" items="${parkings}">
            <div class="card">
                <c:out value="${pp.allInString()}"></c:out>
            </div>
        </c:forEach>
    </div>

</main>
</body>
</html>

