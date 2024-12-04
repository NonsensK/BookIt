package com.example.bookit.dao;

import com.example.bookit.model.Route;
import com.example.migration.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Date; // для преобразования LocalDate в SQL Date

public class RouteDAO {

    // Метод для получения всех маршрутов
    public List<Route> getAllRoutes() throws SQLException {
        List<Route> routes = new ArrayList<>();
        String query = "SELECT * FROM routes";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Route route = new Route();
                route.setId(resultSet.getInt("id"));
                route.setDepartureCity(resultSet.getString("departure_city"));
                route.setArrivalCity(resultSet.getString("arrival_city"));
                route.setDepartureTime(resultSet.getTimestamp("departure_time").toLocalDateTime());
                route.setArrivalTime(resultSet.getTimestamp("arrival_time").toLocalDateTime());
                route.setPrice(resultSet.getDouble("price"));
                routes.add(route);
            }
        }
        return routes;
    }
    public List<Route> getRoutesByCriteria(String departureCity, String arrivalCity, LocalDate travelDate) throws SQLException {
        List<Route> routes = new ArrayList<>();
        String query = "SELECT * FROM routes WHERE departure_city = ? AND arrival_city = ? AND DATE(departure_time) = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Установите параметры в запросе
            preparedStatement.setString(1, departureCity);
            preparedStatement.setString(2, arrivalCity);
            preparedStatement.setDate(3, Date.valueOf(travelDate));

            // Выполните запрос и обработайте результаты
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Route route = new Route();
                    route.setId(resultSet.getInt("id"));
                    route.setDepartureCity(resultSet.getString("departure_city"));
                    route.setArrivalCity(resultSet.getString("arrival_city"));
                    route.setDepartureTime(resultSet.getTimestamp("departure_time").toLocalDateTime());
                    route.setArrivalTime(resultSet.getTimestamp("arrival_time").toLocalDateTime());
                    route.setPrice(resultSet.getDouble("price"));
                    routes.add(route);
                }
            }
        }

        return routes;
    }

    // Метод для добавления нового маршрута
    public void addRoute(Route route) throws SQLException {
        String query = "INSERT INTO routes (departure_city, arrival_city, departure_time, arrival_time, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, route.getDepartureCity());
            preparedStatement.setString(2, route.getArrivalCity());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(route.getDepartureTime()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(route.getArrivalTime()));
            preparedStatement.setDouble(5, route.getPrice());
            preparedStatement.executeUpdate();
        }
    }

    // Метод для поиска маршрутов
    public List<Route> searchRoutes(String departureCity, String arrivalCity, String date) throws SQLException {
        List<Route> routes = new ArrayList<>();
        String query = "SELECT * FROM routes WHERE departure_city = ? AND arrival_city = ? AND DATE(departure_time) = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, departureCity);
            preparedStatement.setString(2, arrivalCity);
            preparedStatement.setString(3, date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Route route = new Route();
                    route.setId(resultSet.getInt("id"));
                    route.setDepartureCity(resultSet.getString("departure_city"));
                    route.setArrivalCity(resultSet.getString("arrival_city"));
                    route.setDepartureTime(resultSet.getTimestamp("departure_time").toLocalDateTime());
                    route.setArrivalTime(resultSet.getTimestamp("arrival_time").toLocalDateTime());
                    route.setPrice(resultSet.getDouble("price"));
                    routes.add(route);
                }
            }
        }
        return routes;
    }
}