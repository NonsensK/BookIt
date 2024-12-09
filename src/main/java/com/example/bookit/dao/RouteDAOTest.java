package com.example.bookit.dao;

import com.example.bookit.dao.RouteDAO;
import com.example.bookit.model.Route;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class RouteDAOTest {
    public static void main(String[] args) {
        // Создаем объект DAO
        RouteDAO routeDAO = new RouteDAO();

        // Создаем новый маршрут
        Route newRoute = new Route();
        newRoute.setDepartureCity("Warsaw");
        newRoute.setArrivalCity("Krakow");
        newRoute.setDepartureTime(LocalDateTime.of(2024, 12, 15, 8, 0));
        newRoute.setArrivalTime(LocalDateTime.of(2024, 12, 15, 11, 0));
        newRoute.setPrice(120.50);

        // Добавляем маршрут в базу данных
        try {
            routeDAO.addRoute(newRoute);
            System.out.println("Маршрут успешно добавлен.");
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении маршрута:");
            e.printStackTrace();
        }
    }
}