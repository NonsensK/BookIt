//database connection manager
package com.example.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnectionManager {

    private static final Logger LOGGER = Logger.getLogger(DatabaseConnectionManager.class.getName());

    // URL подключения с параметрами
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bookit?useUnicode=true&characterEncoding=UTF-8";
    private static final String DATABASE_USER = "bookit_user";
    private static final String DATABASE_PASSWORD = "your_password"; // Укажите правильный пароль для пользователя bookit_user

    // Метод для получения подключения
    public static Connection getConnection() throws SQLException {
        try {
            // Регистрация драйвера не требуется в современных версиях, но можно оставить для совместимости.
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Драйвер MySQL не найден!", e);
            throw new SQLException("Драйвер MySQL не найден!", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Ошибка подключения к базе данных", e);
            throw e;
        }
    }

    // Метод для закрытия подключения
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Ошибка при закрытии подключения", e);
            }
        }
    }
}