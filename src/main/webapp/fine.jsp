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
    <h1>Штраф</h1>
</header>
<main>
    <home><a href="index">Домой</a></home>
    <form action="" method="post" name="fineform" id="fineform">
        <div class="section">
            <p>
                <label for="id">Номер штрафа</label>
                <input type="text" name="id" id="id" placeholder="Введите номер штрафа" value="${idRet}">
                <input name="get" type="submit" id="get" value="Получить штраф по номеру">
            </p>
            <p>
                <label for="fineDescription">Описание</label>
                <input type="text" name="fineDescription" id="fineDescription" placeholder="Введите описание" value="${fineDescriptionRet}">
            </p>
            <p>
                <label for="fineCost">Стоимость</label>
                <input type="text" name="fineCost" id="fineCost" placeholder="Введите стоимость оплаты" value="${fineCostRet}">
            </p>
            <p>
                <label for="agreementId">Номер договора</label>
                <input type="text" name="agreementId" id="agreementId" placeholder="Введите номер договора" value="${agreementIdRet}">
                <input name="getAgreements" type="submit" id="getAgreements" value="Посмотреть все договоры">
                <input name="getByAgId" type="submit" id="getByAgId" value="Получить штраф по номеру договора">
            </p>
            <p>
                <input name="create" type="submit" id="create" value="Внести в базу новый штраф с номером">
                <input name="createWithoutId" type="submit" id="createWithoutId" value="Внести в базу новый штраф">
                <input name="update" type="submit" id="update" value="Обновить данные штрафа">
            <p></p>
                <input name="delete" type="submit" id="delete" value="Удалить штраф из базы по номеру">
                <input name="getAll" type="submit" id="getAll" value="Посмотреть все штрафы">
            </p>
        </div>
    </form>
    <div class="info">
        <p>${error}</p>
    </div>

    <div class="section">
        <h2>Штрафы</h2>
        <c:forEach var="fine" items="${fines}">
            <div class="card">
                <form action="" method="post" name="agreementform" id="oneAgreementform">
                    <c:out value="${fine.allInString()}"></c:out>
                    <input type="hidden" name="idOther" id="idOther" value="${fine.getId()}">
                    <input type="hidden" name="fineDescriptionOther" id="fineDescriptionOther" value="${fine.getFineDescription()}">
                    <input type="hidden" name="fineCostOther" id="fineCostOther" value="${fine.getFineCost()}">
                    <input type="hidden" name="agreementIdOther" id="agreementIdOther" value="${fine.getAgreementId()}">


                    <input name="change" type="submit" id="change" value="Изменить штраф">
                    <input name="deleteOther" type="submit" id="deleteOther" value="Удалить штраф">
<%--                    <p style="text-indent: 25px;">Car: ${carsInfo.removeFirst().allInString()}</p>--%>
<%--                    <p style="text-indent: 25px;">Client: ${clientsInfo.removeFirst().allInString()}</p>--%>
                </form>
            </div>
        </c:forEach>
    </div>

    <div class="section">
        <h2>Договоры</h2>
        <c:forEach var="agreement" items="${agreements}">
            <div class="card">
                <c:out value="${agreement.allInString()}"></c:out>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>

