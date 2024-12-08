<%--
  Created by IntelliJ IDEA.
  User: Volodya
  Date: 10.11.2024
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--доделать--%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Клиенты</title>
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
    <h1>Клиент</h1>
</header>
<main>
    <home><a href="index">Домой</a></home>
    <form action="" method="post" name="clientform" id="clientform">
        <div class="section">
            <p>
                <label for="id">Номер паспорта</label>
                <input type="text" name="id" id="id" placeholder="Введите паспортные данные" value="">
                <input name="get" type="submit" id="get" value="Найти по паспорту">
            </p>
            <p>
                <label for="phoneNumber">Номер телефона</label>
                <input type="text" name="phoneNumber" id="phoneNumber" placeholder="Введите номер телефона" value="">
                <input name="getByPNumber" type="submit" id="getByPNumber" value="Найти по номеру телефона">
            </p>
            <p>
                <label for="address">Адрес</label>
                <input type="text" name="address" id="address" placeholder="Введите адрес" value="">
                <input name="getByAddress" type="submit" id="getByAddress" value="Найти по адресу">
            </p>
            <p>
                <label for="name">ФИО</label>
                <input type="text" name="name" id="name" placeholder="Введите ФИО" value="">
                <input name="getByName" type="submit" id="getByName" value="Найти по имени">
            </p>
            <p>
                <input name="create" type="submit" id="create" value="Внести в базу нового клиента">
                <input name="update" type="submit" id="update" value="Обновить данные клиента">
            <p></p>
                <input name="delete" type="submit" id="delete" value="Удалить клиента из базы по паспорту">
                <input name="getAll" type="submit" id="getAll" value="Посмотреть всех клиентов">
            </p>
        </div>
    </form>
    <div class="info">
        <p>${error}</p>
    </div>

    <div class="section">
        <h2>Клиенты</h2>
        <c:forEach var="client" items="${clients}">
            <div class="card">
                <c:out value="${client.allInString()}"></c:out>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>
