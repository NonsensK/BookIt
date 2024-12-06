package com.example.bookit.servlets;

import com.example.bookit.dao.RouteDAO;
import com.example.bookit.model.Route;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SearchRoutesServlet extends HttpServlet {

    private final RouteDAO routeDAO = new RouteDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получение данных из формы
        String departureCity = request.getParameter("departureCity");
        String arrivalCity = request.getParameter("arrivalCity");
        String date = request.getParameter("date");

        // Проверка корректности входных данных
        if (departureCity == null || arrivalCity == null || date == null ||
                departureCity.isEmpty() || arrivalCity.isEmpty() || date.isEmpty()) {
            request.setAttribute("error", "Все поля должны быть заполнены.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        try {
            // Конвертация строки даты
            LocalDate travelDate = LocalDate.parse(date);

            // Получение маршрутов
            List<Route> routes = routeDAO.getRoutesByCriteria(departureCity, arrivalCity, travelDate);

            // Проверка на пустой результат
            if (routes.isEmpty()) {
                request.setAttribute("message", "Маршруты не найдены.");
            } else {
                request.setAttribute("routes", routes);
            }

            // Передача данных на JSP
            request.getRequestDispatcher("/results.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("error", "Ошибка базы данных. Пожалуйста, повторите попытку позже.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("error", "Некорректный формат даты.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}