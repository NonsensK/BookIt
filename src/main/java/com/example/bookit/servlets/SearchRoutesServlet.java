package com.example.bookit.servlets;

import com.example.bookit.dao.RouteDAO;
import com.example.bookit.model.Route;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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

        try {
            // Конвертация строки даты
            LocalDate travelDate = LocalDate.parse(date);

            // Получение маршрутов
            List<Route> routes = routeDAO.getRoutesByCriteria(departureCity, arrivalCity, travelDate);

            // Передача данных на JSP
            request.setAttribute("routes", routes);
            request.getRequestDispatcher("/results.jsp").forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Произошла ошибка при поиске маршрутов.");
        }
    }
}