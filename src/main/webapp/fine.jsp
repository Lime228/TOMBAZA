<%--
  Created by IntelliJ IDEA.
  User: Volodya
  Date: 10.11.2024
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Штрафы</title>
</head>
<body >
<h1>Штраф</h1>
<form action="" method="post" name="fineform" id="fineform">
    <p><input type="text" name="id" id="id" value="" size="25" />
        <small> Номер</small>
    </p>
    <p><input type="text" name="fineDescription" id="fineDescription" value=" " size="25" />
        <small> Описание</small>
    </p>
    <p><input type="text" name="fineCost" id="fineCost" value=" " size="25" />
        <small> Стоимость оплаты</small>
    </p>
    <p><input type="text" name="agreementId" id="agreementId" value=" " size="25" />
        <small> Номер договора. Обязательно должен существовать.</small> <a><input name="getAgreements" type="submit" id="getAgreements" value="Посмотреть все договоры" /></a>
    </p>
    <a><input name="get" type="submit" id="get" value="Получить штраф по номеру" />
    </a>
    <a><input name="create" type="submit" id="create" value="Внести в базу новый штраф" />
    </a>
    <p></p>
    <a><input name="update" type="submit" id="update" value="Обновить данные штрафа" />
    </a>
    <a><input name="delete" type="submit" id="delete" value="Удалить штраф из базы по номеру" />
    </a>
    <a><input name="getAll" type="submit" id="getAll" value="Посмотреть все штрафы" />
    </a>
</form>
<a>Что-то пошло не так: ${error}</a><p></p>
<a>Создалось: ${wasCreated}</a> <p></p>
<a>Обновилось: ${wasUpdated}</a> <p></p>
<a>Удалилось: ${wasDeleted}</a><p></p>
<h2>По искомому штрафу:</h2>
<a>ID: ${id}</a>
<a>FINEDESCRIPTION: ${fineDescription}</a>
<a>FINECOST: ${fineCost}</a>
<a>AGREEMENTID: ${agreementId}</a>

<h1>Автомобили</h1>
<c:forEach var="fine" items="${fines}">
    <div>
        <c:out value="${fine.allInString()}" />
    </div>
</c:forEach>
<h1>Договоры</h1>
<c:forEach var="agreement" items="${agreements}">
    <div>
        <c:out value="${agreement.allInString()}" />
    </div>
</c:forEach>


</body>
</html>
ТЫ ЕБЛАН ПОМЕНЯЙ ПРИНЦИП УДАЛЕНИЯ И ПУСТЫЕ ВВОДНЫЕ СТРОЧКИ