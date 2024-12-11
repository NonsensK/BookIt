<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link rel="stylesheet" href="styles.css"> <!-- Подключаем внешний CSS файл -->
</head>
<body>
<div class="container">
    <h1>Регистрация</h1>
    <form action="register" method="post" class="registration-form">
        <div class="form-group">
            <label for="name">Имя:</label>
            <input type="text" id="name" name="name" required class="form-control">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required class="form-control">
        </div>
        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required class="form-control">
        </div>
        <button type="submit" class="submit-btn">Зарегистрироваться</button>
    </form>
</div>
</body>
</html>