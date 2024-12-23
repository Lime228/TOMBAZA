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
    <h1>Штраф</h1>
</header>
<main>
    <home><a href="index">Домой</a></home>
    <form action="" method="post" name="fineform" id="fineform">
        <div class="section">
            <p>
                <label for="id">Номер штрафа</label>
                <input type="text" name="id" id="id" placeholder="Введите номер штрафа" value="${fn:escapeXml(idRet)}">
                <input name="get" type="submit" id="get" value="Получить штраф по номеру">
            </p>
            <p>
                <label for="fineDescription">Описание</label>
                <input type="text" name="fineDescription" id="fineDescription" placeholder="Введите описание"
                       value="${fn:escapeXml(fineDescriptionRet)}">
            </p>
            <p>
                <label for="fineCost">Стоимость</label>
                <input type="text" name="fineCost" id="fineCost" placeholder="Введите стоимость оплаты"
                       value="${fn:escapeXml(fineCostRet)}">
            </p>
            <p>
                <label for="agreementId">Номер договора</label>
                <input type="text" name="agreementId" id="agreementId" placeholder="Введите номер договора"
                       value="${fn:escapeXml(agreementIdRet)}">
                <input name="getAgreements" type="submit" id="getAgreements" value="Посмотреть все договоры">
                <input name="getByAgId" type="submit" id="getByAgId" value="Получить штраф по номеру договора">
            </p>
            <p>
                <input name="create" type="submit" id="create" value="Внести в базу новый штраф с номером">
                <input name="createWithoutId" type="submit" id="createWithoutId" value="Внести в базу новый штраф">

            <p></p>
            <input name="update" type="submit" id="update" value="Обновить данные штрафа">
            <input name="getAll" type="submit" id="getAll" value="Посмотреть все штрафы">
            </p>
        </div>
    </form>
    <div class="info">
        <p>${fn:escapeXml(error)}</p>
    </div>

    <c:if test="${not empty fines}">
        <div class="section">
            <h2>Штрафы</h2>
            <a>Найдено штрафов: ${fn:escapeXml(fines.size())}</a>
            <c:forEach var="fine" items="${fines}">
                <div class="card">
                    <form action="" method="post" name="agreementform" id="oneAgreementform">
                        <i>Номер штрафа: <b>${fn:escapeXml(fine.getId())}</b></i>
                        <input type="hidden" name="idOther" id="idOther" value="${fn:escapeXml(fine.getId())}">
                        <hr>
                        <i>Описание: <b>${fn:escapeXml(fine.getFineDescription())}</b></i>
                        <input type="hidden" name="fineDescriptionOther" id="fineDescriptionOther"
                               value="${fn:escapeXml(fine.getFineDescription())}">
                        <hr>
                        <i>Стоимость: <b>${fn:escapeXml(fine.getFineCost())}</b></i>
                        <input type="hidden" name="fineCostOther" id="fineCostOther"
                               value="${fn:escapeXml(fine.getFineCost())}">
                        <hr>
                        <i>Номер договора: <b>${fn:escapeXml(fine.getAgreementId())}</b></i>
                        <input type="hidden" name="agreementIdOther" id="agreementIdOther"
                               value="${fn:escapeXml(fine.getAgreementId())}">
                        <hr>

                        <p></p>
                        <input name="change" type="submit" id="change" value="Изменить штраф">
                        <input name="deleteOther" type="submit" id="deleteOther" value="Удалить штраф">
                            <%--                    <p style="text-indent: 25px;">Car: ${carsInfo.removeFirst().allInString()}</p>--%>
                            <%--                    <p style="text-indent: 25px;">Client: ${clientsInfo.removeFirst().allInString()}</p>--%>
                    </form>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${not empty agreements}">
        <div class="section">
            <h2>Договоры</h2>
            <a>Найдено договоров: ${fn:escapeXml(agreements.size())}</a>
            <c:forEach var="agreement" items="${agreements}">
                <div class="card">
                    <c:out value="${agreement.allInString()}"/>
                    <i>Номер договора: <b>${fn:escapeXml(agreement.getId())}</b></i>
                    <hr>
                    <i>Стоимость аренды: <b>${fn:escapeXml(agreement.getRentPrice())}</b></i>
                    <hr>
                    <i>Период аренды: <b>${fn:escapeXml(agreement.getRentPeriod())}</b></i>
                    <hr>
                    <i>Номер паспорта: <b>${fn:escapeXml(agreement.getPassportNumber())}</b></i>
                    <hr>
                    <i>ВИН-номер: <b>${fn:escapeXml(agreement.getVinNumber())}</b></i>
                    <hr>
                </div>
            </c:forEach>
        </div>
    </c:if>
</main>
</body>
</html>

