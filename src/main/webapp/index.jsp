<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Аренда автомобилей</title>
    <style>
        /* Основные стили */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
            text-align: center;
        }

        header {
            background-color: #58b092;
            color: white;
            padding: 20px;
        }

        header h1 {
            margin: 0;
        }

        nav {
            margin: 20px 0;
        }

        nav a {
            display: inline-block;
            margin: 0 10px;
            padding: 10px 20px;
            color: white;
            background-color: #58b092;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        nav a:hover {
            background-color: #3c7562;
        }
    </style>
</head>

<body>
<!-- Заголовок -->
<header>
    <h1>Аренда автомобилей</h1>
</header>

<!-- Навигация -->
<nav>
    <a href="agreement">Договор</a>
    <a href="car">Машина</a>
    <a href="client">Клиент</a>
    <a href="fine">Штрафы</a>
    <a href="parking">Парковка</a>
    <a href="parkingplace">Парковочное место</a>
    <a href="stat">Статистика</a>
</nav>
</body>

</html>

