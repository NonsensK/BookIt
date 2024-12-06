<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторизация</title>
</head>
<body>
<h1>Авторизация</h1>
<form action="login" method="post">
    <label for="username">Имя пользователя:</label>
    <input type="text" id="username" name="username" required>
    <br><br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required>
    <br><br>
    <button type="submit">Войти</button>
</form>
</body>
</html>