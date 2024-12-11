<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Покупка билетов</title>
</head>
<body>
<h1>Покупка билетов</h1>

<!-- Отображение сообщения об ошибке или успехе -->
<c:if test="${not empty message}">
    <div style="color: green;">
            ${message}
    </div>
</c:if>

<c:if test="${not empty error}">
    <div style="color: red;">
            ${error}
    </div>
</c:if>

<form action="PurchaseTicketServlet" method="post">
    <label for="userName">Имя:</label>
    <input type="text" id="userName" name="userName" required><br><br>

    <label for="userEmail">Email:</label>
    <input type="email" id="userEmail" name="userEmail" required><br><br>

    <label for="routeId">ID маршрута:</label>
    <input type="number" id="routeId" name="routeId" required><br><br>

    <button type="submit">Купить билет</button>
</form>
</body>
</html>