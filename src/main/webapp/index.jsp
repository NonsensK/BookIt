<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Поиск маршрутов</title>
    <!-- Подключение файла стилей -->
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body>

<div class="container">
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
</div>

</body>
</html>