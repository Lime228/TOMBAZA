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
    <title>Клиенты</title>
</head>
<body >
<h1>Клиент</h1>
<form action="" method="post" name="clientform" id="clientform">
    <p><input type="text" name="id" id="id" value="" size="25" />
        <small> Паспортные данные</small>
    </p>
    <p><input type="text" name="phoneNumber" id="phoneNumber" value="" size="25" />
        <small> Номер телефона</small>
    </p>
    <p><input type="text" name="address" id="address" value="" size="25" />
        <small> Адрес</small>
    </p>
    <p><input type="text" name="name" id="name" value="" size="25" />
        <small> ФИО</small>
    </p>
    <a><input name="get" type="submit" id="get" value="Получить клиента по паспорту" />
    </a>
    <a><input name="create" type="submit" id="create" value="Внести в базу нового клиента" />
    </a>
    <p></p>
    <a><input name="update" type="submit" id="update" value="Обновить данные клиента" />
    </a>
    <a><input name="delete" type="submit" id="delete" value="Удалить клиента из базы по паспорту" />
    </a>
    <a><input name="getAll" type="submit" id="getAll" value="Посмотреть всех клиентов" />
    </a>
</form>
<a>Что-то пошло не так: ${error}</a><p></p>
<a>Создалось: ${wasCreated}</a> <p></p>
<a>Обновилось: ${wasUpdated}</a> <p></p>
<a>Удалилось: ${wasDeleted}</a><p></p>
<h2>По искомому клиенту:</h2>
<a>ID: ${id}</a>
<a>PHONENUMBER: ${phoneNumber}</a>
<a>ADDRESS: ${address}</a>
<a>NAME: ${name}</a>
<h1>Клиенты</h1>
<c:forEach var="client" items="${clients}">
    <div>
        <c:out value="${client.allInString()}" />
    </div>
</c:forEach>

</body>
</html>
