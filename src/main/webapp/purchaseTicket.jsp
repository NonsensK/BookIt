<!DOCTYPE html>
<html>
<head>
    <title>Покупка билетов</title>
</head>
<body>
<h1>Покупка билетов</h1>
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