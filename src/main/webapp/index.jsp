<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Routes</title>
</head>
<body>
<h1>Поиск маршрутов</h1>
<form action="searchRoutes" method="post">
    <label for="departureCity">Город отправления:</label>
    <input type="text" id="departureCity" name="departureCity" required><br>

    <label for="arrivalCity">Город прибытия:</label>
    <input type="text" id="arrivalCity" name="arrivalCity" required><br>

    <label for="date">Дата поездки:</label>
    <input type="date" id="date" name="date" required><br>

    <button type="submit">Найти маршруты</button>
</form>
</body>
</html>