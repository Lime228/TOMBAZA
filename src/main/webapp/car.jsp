<%--
  Created by IntelliJ IDEA.
  User: Volodya
  Date: 10.11.2024
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<a href="index">Домой</a>
<head>
        <title>Машины</title>
</head>
<body >
<h1>Машина</h1>
<form action="" method="post" name="carform" id="carform">
    <p><input type="text" name="id" id="id" value="" size="25" />
        <small> Вин-номер</small>
    </p>
    <p><input type="text" name="color" id="color" value="" size="25" />
        <small> Цвет</small>
    </p>
    <p><input type="text" name="brand" id="brand" value="" size="25" />
        <small> Бренд</small>
    </p>
    <p><input type="text" name="modelName" id="modelName" value="" size="25" />
        <small> Название модели</small>
    </p>
    <p><input type="text" name="releaseYear" id="releaseYear" value="" size="25" />
        <small> Год выпуска</small>
    </p>
    <p><input type="text" name="parkingPlaceId" id="parkingPlaceId" value="" size="25" />
        <small> Место на парковке. Обязательно должно существовать.</small> <a><input name="getParkingPlaces" type="submit" id="getParkingPlaces" value="Посмотреть все места" /></a>
    </p>
    <p><input type="text" name="number" id="number" value="" size="25" />
        <small> Гос. номер</small>
    </p>
    <a><input name="get" type="submit" id="get" value="Получить автомобиль по Вин-номеру" />
    </a>
    <a><input name="create" type="submit" id="create" value="Внести в базу новый автомобиль" />
    </a>
    <p></p>
    <a><input name="update" type="submit" id="update" value="Обновить данные автомобиля" />
    </a>
    <a><input name="delete" type="submit" id="delete" value="Удалить автомобилб из базы по ВИН-у" />
    </a>
    <a><input name="getAll" type="submit" id="getAll" value="Посмотреть все автомобили" />
    </a>
</form>
<a>Что-то пошло не так: ${error}</a><p></p>
<a>Создалось: ${wasCreated}</a> <p></p>
<a>Обновилось: ${wasUpdated}</a> <p></p>
<a>Удалилось: ${wasDeleted}</a><p></p>
<h2>По искомому автомобилю:</h2>
<a>ID: ${id}</a>
<a>COLOR: ${color}</a>
<a>BRAND: ${brand}</a>
<a>MODELNAME: ${modelName}</a>
<a>RELEASEYEAR: ${releaseYear}</a>
<a>PARKINGPLACEID: ${parkingPlaceId}</a>
<a>NUMBER: ${number}</a>
<h1>Автомобили</h1>
<c:forEach var="car" items="${cars}">
    <div>
        <c:out value="${car.allInString()}" />
    </div>
</c:forEach>
<h1>Места</h1>
<c:forEach var="place" items="${places}">
    <div>
        <c:out value="${place.allInString()}" />
    </div>
</c:forEach>

</body>
</html>
