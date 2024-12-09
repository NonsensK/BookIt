package com.example.bookit.servlets;

import com.example.bookit.dao.RouteDAO;
import com.example.bookit.model.Route;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private final RouteDAO routeDAO = new RouteDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получение параметров из формы
        String departureCity = request.getParameter("departureCity");
        String arrivalCity = request.getParameter("arrivalCity");
        String date = request.getParameter("date");

        try {
            // Конвертация строки даты в LocalDate
            LocalDate travelDate = LocalDate.parse(date);

            // Поиск маршрутов
            List<Route> routes = routeDAO.getRoutesByCriteria(departureCity, arrivalCity, travelDate);

            // Проверка на наличие маршрутов
            if (routes.isEmpty()) {
                request.setAttribute("message", "Маршруты не найдены.");
            } else {
                request.setAttribute("routes", routes);
            }

            // Передача данных на JSP
            request.getRequestDispatcher("/results.jsp").forward(request, response);

        } catch (Exception e) {
            // Обработка ошибок
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Произошла ошибка при обработке запроса.");
        }
    }
}