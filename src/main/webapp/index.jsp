<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Поиск билетов</title>
</head>
<body>
<h1>Поиск билетов</h1>
<form action="search" method="post">
    <label for="departure">Город отправления:</label>
    <input type="text" id="departure" name="departure"><br>

    <label for="arrival">Город прибытия:</label>
    <input type="text" id="arrival" name="arrival"><br>

    <label for="date">Дата поездки:</label>
    <input type="date" id="date" name="date"><br>

    <button type="submit">Найти билеты</button>
</form>
</body>
</html>