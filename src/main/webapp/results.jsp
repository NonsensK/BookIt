<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Available Routes</title>
</head>
<body>
<h1>Доступные маршруты</h1>

<c:if test="${empty routes}">
    <p>Нет доступных маршрутов для заданных критериев.</p>
</c:if>

<c:if test="${not empty routes}">
    <table border="1">
        <tr>
            <th>Город отправления</th>
            <th>Город прибытия</th>
            <th>Время отправления</th>
            <th>Время прибытия</th>
            <th>Цена</th>
        </tr>
        <c:forEach var="route" items="${routes}">
            <tr>
                <td>${route.departureCity}</td>
                <td>${route.arrivalCity}</td>
                <td>${route.departureTime}</td>
                <td>${route.arrivalTime}</td>
                <td>${route.price}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="index.jsp">Вернуться к поиску</a>
</body>
</html>