<%--
  Created by IntelliJ IDEA.
  User: Volodya
  Date: 10.11.2024
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Машины</title>
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
    <h1>Машина</h1>
</header>
<main>
    <home>
        <a href="index">Домой</a>
    </home>
    <form action="" method="post" name="carform" id="carform">
        <div class="section">
            <h2>Данные автомобиля</h2>
            <p>
                <label for="id">Вин-номер</label>
                <input type="text" name="id" id="id" placeholder="Введите Вин-номер" value="">
                <input name="get" type="submit" id="get" value="Найти по Вин-номеру">
            </p>
            <p>
                <label for="color">Цвет</label>
                <input type="text" name="color" id="color" placeholder="Введите цвет" value="">
                <input name="getByColor" type="submit" id="getByColor" value="Найти по цвету">
            </p>
            <p>
                <label for="brand">Бренд</label>
                <input type="text" name="brand" id="brand" placeholder="Введите бренд" value="">
                <input name="getByBrand" type="submit" id="getByBrand" value="Найти по бренду">
            </p>
            <p>
                <label for="modelName">Название модели</label>
                <input type="text" name="modelName" id="modelName" placeholder="Введите название модели" value="">
                <input name="getByModel" type="submit" id="getByModel" value="Найти по модели">
            </p>
            <p>
                <label for="releaseYear">Год выпуска</label>
                <input type="text" name="releaseYear" id="releaseYear" placeholder="Введите год выпуска" value="">
                <input name="getByYear" type="submit" id="getByYear" value="Найти по году выпуска">
            </p>
            <p>
                <label for="parkingPlaceId">Место на парковке</label>
                <input type="text" name="parkingPlaceId" id="parkingPlaceId" placeholder="Введите место на парковке" value="">
                <input name="getParkingPlaces" type="submit" id="getParkingPlaces" value="Посмотреть все места">
                <input name="getByPPlace" type="submit" id="getByPPlace" value="Найти по месту">
            </p>
            <p>
                <label for="number">Гос. Номер</label>
                <input type="text" name="number" id="number" placeholder="Введите гос. номер" value="">
                <input name="getByPPlace" type="submit" id="getByNumber" value="Найти по номеру">
            </p>
            <p>
                <input name="create" type="submit" id="create" value="Внести в базу новый автомобиль">
                <input name="update" type="submit" id="update" value="Обновить данные автомобиля">
            <p></p>
                <input name="delete" type="submit" id="delete" value="Удалить автомобиль по ВИН-у">
                <input name="getAll" type="submit" id="getAll" value="Посмотреть все автомобили">
            </p>
        </div>
    </form>
    <div class="info">
        <p>${error}</p>
    </div>

    <div class="section">
        <h2>Автомобили</h2>
        <c:forEach var="car" items="${cars}">
            <div class="card">
                <c:out value="${car.allInString()}"></c:out>
<%--                здесь еще выводить место, парковку (договор?)--%>
            </div>
        </c:forEach>
    </div>

    <div class="section">
        <h2>Места</h2>
        <c:forEach var="place" items="${places}">
            <div class="card">
                <c:out value="${place.allInString()}"></c:out>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>


<%--<html>--%>
<%--<a href="index">Домой</a>--%>
<%--<head>--%>
<%--    <title>Машины</title>--%>
<%--</head>--%>
<%--<body >--%>
<%--<h1>Машина</h1>--%>
<%--<form action="" method="post" name="carform" id="carform">--%>
<%--    <p><input type="text" name="id" id="id" value="" size="25" />--%>
<%--        <small> Вин-номер</small>--%>
<%--    </p>--%>
<%--    <p><input type="text" name="color" id="color" value="" size="25" />--%>
<%--        <small> Цвет</small>--%>
<%--    </p>--%>
<%--    <p><input type="text" name="brand" id="brand" value="" size="25" />--%>
<%--        <small> Бренд</small>--%>
<%--    </p>--%>
<%--    <p><input type="text" name="modelName" id="modelName" value="" size="25" />--%>
<%--        <small> Название модели</small>--%>
<%--    </p>--%>
<%--    <p><input type="text" name="releaseYear" id="releaseYear" value="" size="25" />--%>
<%--        <small> Год выпуска</small>--%>
<%--    </p>--%>
<%--    <p><input type="text" name="parkingPlaceId" id="parkingPlaceId" value="" size="25" />--%>
<%--        <small> Место на парковке. Обязательно должно существовать.</small> <a><input name="getParkingPlaces" type="submit" id="getParkingPlaces" value="Посмотреть все места" /></a>--%>
<%--    </p>--%>
<%--    <p><input type="text" name="number" id="number" value="" size="25" />--%>
<%--        <small> Гос. номер</small>--%>
<%--    </p>--%>
<%--    <a><input name="get" type="submit" id="get" value="Получить автомобиль по Вин-номеру" />--%>
<%--    </a>--%>
<%--    <a><input name="create" type="submit" id="create" value="Внести в базу новый автомобиль" />--%>
<%--    </a>--%>
<%--    <p></p>--%>
<%--    <a><input name="update" type="submit" id="update" value="Обновить данные автомобиля" />--%>
<%--    </a>--%>
<%--    <a><input name="delete" type="submit" id="delete" value="Удалить автомобилб из базы по ВИН-у" />--%>
<%--    </a>--%>
<%--    <a><input name="getAll" type="submit" id="getAll" value="Посмотреть все автомобили" />--%>
<%--    </a>--%>
<%--</form>--%>
<%--<a>Что-то пошло не так: ${error}</a><p></p>--%>
<%--<a>Создалось: ${wasCreated}</a> <p></p>--%>
<%--<a>Обновилось: ${wasUpdated}</a> <p></p>--%>
<%--<a>Удалилось: ${wasDeleted}</a><p></p>--%>
<%--<h2>По искомому автомобилю:</h2>--%>
<%--<a>ID: ${id}</a>--%>
<%--<a>COLOR: ${color}</a>--%>
<%--<a>BRAND: ${brand}</a>--%>
<%--<a>MODELNAME: ${modelName}</a>--%>
<%--<a>RELEASEYEAR: ${releaseYear}</a>--%>
<%--<a>PARKINGPLACEID: ${parkingPlaceId}</a>--%>
<%--<a>NUMBER: ${number}</a>--%>
<%--<h1>Автомобили</h1>--%>
<%--<c:forEach var="car" items="${cars}">--%>
<%--    <div>--%>
<%--        <c:out value="${car.allInString()}" />--%>
<%--    </div>--%>
<%--</c:forEach>--%>
<%--<h1>Места</h1>--%>
<%--<c:forEach var="place" items="${places}">--%>
<%--    <div>--%>
<%--        <c:out value="${place.allInString()}" />--%>
<%--    </div>--%>
<%--</c:forEach>--%>

<%--</body>--%>
<%--</html>--%>