<%--
  Created by IntelliJ IDEA.
  User: Volodya
  Date: 10.11.2024
  Time: 16:19
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
                <input type="text" name="id" id="id" placeholder="Введите Вин-номер" value="${fn:escapeXml(idRet)}">
                <input name="get" type="submit" id="get" value="Найти по Вин-номеру">
            </p>
            <p>
                <label for="color">Цвет</label>
                <input type="text" name="color" id="color" placeholder="Введите цвет" value="${fn:escapeXml(colorRet)}">
                <input name="getByColor" type="submit" id="getByColor" value="Найти по цвету">
            </p>
            <p>
                <label for="brand">Бренд</label>
                <input type="text" name="brand" id="brand" placeholder="Введите бренд"
                       value="${fn:escapeXml(brandRet)}">
                <input name="getByBrand" type="submit" id="getByBrand" value="Найти по бренду">
            </p>
            <p>
                <label for="modelName">Название модели</label>
                <input type="text" name="modelName" id="modelName" placeholder="Введите название модели"
                       value="${fn:escapeXml(modelNameRet)}">
                <input name="getByModel" type="submit" id="getByModel" value="Найти по модели">
            </p>
            <p>
                <label for="releaseYear">Год выпуска</label>
                <input type="text" name="releaseYear" id="releaseYear" placeholder="Введите год выпуска"
                       value="${fn:escapeXml(releaseYearRet)}">
                <input name="getByYear" type="submit" id="getByYear" value="Найти по году выпуска">
            </p>
            <p>
                <label for="parkingPlaceId">Место на парковке</label>
                <input type="text" name="parkingPlaceId" id="parkingPlaceId" placeholder="Введите место на парковке"
                       value="${fn:escapeXml(parkingPlaceIdRet)}">
                <input name="getParkingPlaces" type="submit" id="getParkingPlaces" value="Посмотреть все места">
                <input name="getByPPlace" type="submit" id="getByPPlace" value="Найти по месту">
            </p>
            <p>
                <label for="number">Гос. Номер</label>
                <input type="text" name="number" id="number" placeholder="Введите гос. номер"
                       value="${fn:escapeXml(numberRet)}">
                <input name="getByNumber" type="submit" id="getByNumber" value="Найти по номеру">
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
        <p>${fn:escapeXml(error)}</p>
    </div>

    <c:if test="${not empty cars}">
        <div class="section">
            <h2>Автомобили</h2>
            <a>Найдено автомобилей: ${fn:escapeXml(cars.size())}</a>
            <c:forEach var="car" items="${cars}">
                <div class="card">
                    <form action="" method="post" name="carform" id="oneCarform">
                        <label for="idOther">ВИН-номер</label>
                        <input type="text" name="idOther" id="idOther" value="${fn:escapeXml(car.getId())}" >
                        <label for="colorOther">Цвет</label>
                        <input type="text" name="colorOther" id="colorOther" value="${fn:escapeXml(car.getColor())}"
                               >
                        <label for="brandOther">Бренд</label>
                        <input type="text" name="brandOther" id="brandOther" value="${fn:escapeXml(car.getBrand())}"
                               >
                        <label for="modelNameOther">Модель</label>
                        <input type="text" name="modelNameOther" id="modelNameOther"
                               value="${fn:escapeXml(car.getModelName())}" >
                        <label for="releaseYearOther">Год выпуска</label>
                        <input type="text" name="releaseYearOther" id="releaseYearOther"
                               value="${fn:escapeXml(car.getReleaseYear())}" >
                        <label for="parkingPlaceIdOther">Парковочное место</label>
                        <input type="text" name="parkingPlaceIdOther" id="parkingPlaceIdOther"
                               value="${fn:escapeXml(car.getParkingPlaceId())}" >
                        <label for="numberOther">Гос. номер</label>
                        <input type="text" name="numberOther" id="numberOther"
                               value="${fn:escapeXml(car.getNumber())}" >
                        <p></p>
                        <input name="change" type="submit" id="change" value="Изменить данные автомобиля">
                        <input name="deleteOther" type="submit" id="deleteOther" value="Удалить автомобиль">
                            <%--            пример ниже    здесь еще выводить место, парковку (договор?)--%>
                            <%--                    <p style="text-indent: 25px;">Car: ${carsInfo.removeFirst().allInString()}</p>--%>
                            <%--                    <p style="text-indent: 25px;">Client: ${clientsInfo.removeFirst().allInString()}</p>--%>
                    </form>

                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${not empty places}">
        <div class="section">
            <h2>Места</h2>
            <a>Найдено мест: ${fn:escapeXml(places.size())}</a>
            <c:forEach var="place" items="${places}">
                <div class="card">
                    <c:out value="${place.allInString()}"/>
                </div>
            </c:forEach>
        </div>
    </c:if>
</main>
</body>
</html>