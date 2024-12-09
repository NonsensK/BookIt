//RouteDAO
package com.example.bookit.dao;

import com.example.bookit.model.Route;
import com.example.migration.DatabaseConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {

    // Метод для получения всех маршрутов
    public List<Route> getAllRoutes() throws SQLException {
        List<Route> routes = new ArrayList<>();
        String query = "SELECT * FROM routes";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Route route = mapResultSetToRoute(resultSet);
                routes.add(route);
            }
        }
        return routes;
    }

    // Метод для получения маршрутов по критериям
    public List<Route> getRoutesByCriteria(String departureCity, String arrivalCity, LocalDate travelDate) throws SQLException {
        List<Route> routes = new ArrayList<>();
        String query = "SELECT * FROM routes WHERE departure_city = ? AND arrival_city = ? AND DATE(departure_time) = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            System.out.println("Executing query: " + query);
            System.out.println("Parameters:");
            System.out.println("departureCity: " + departureCity);
            System.out.println("arrivalCity: " + arrivalCity);
            System.out.println("travelDate: " + travelDate);

            preparedStatement.setString(1, departureCity);
            preparedStatement.setString(2, arrivalCity);
            preparedStatement.setDate(3, Date.valueOf(travelDate));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Route route = mapResultSetToRoute(resultSet);
                    routes.add(route);
                }
            }
        }

        System.out.println("Маршруты из getRoutesByCriteria:");
        for (Route route : routes) {
            System.out.println(route);
        }
        return routes;
    }

    // Метод для добавления нового маршрута
    public void addRoute(Route route) throws SQLException {
        String query = "INSERT INTO routes (departure_city, arrival_city, departure_time, arrival_time, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            System.out.println("Добавляем маршрут:");
            System.out.println("Город отправления: " + route.getDepartureCity());
            System.out.println("Город прибытия: " + route.getArrivalCity());
            System.out.println("Время отправления: " + route.getDepartureTime());
            System.out.println("Время прибытия: " + route.getArrivalTime());
            System.out.println("Цена: " + route.getPrice());

            preparedStatement.setString(1, route.getDepartureCity());
            preparedStatement.setString(2, route.getArrivalCity());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(route.getDepartureTime()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(route.getArrivalTime()));
            preparedStatement.setDouble(5, route.getPrice());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Маршрут успешно добавлен.");
            } else {
                System.out.println("Не удалось добавить маршрут.");
            }
        }
    }

    // Вспомогательный метод для преобразования ResultSet в объект Route
    private Route mapResultSetToRoute(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt("id"));
        route.setDepartureCity(resultSet.getString("departure_city"));
        route.setArrivalCity(resultSet.getString("arrival_city"));
        route.setDepartureTime(resultSet.getTimestamp("departure_time").toLocalDateTime());
        route.setArrivalTime(resultSet.getTimestamp("arrival_time").toLocalDateTime());
        route.setPrice(resultSet.getDouble("price"));
        return route;
    }
}