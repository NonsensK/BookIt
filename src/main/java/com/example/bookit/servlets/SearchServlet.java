package com.example.bookit.servlets;

import com.example.bookit.dao.RouteDAO;
import com.example.bookit.model.Route;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
            // Поиск маршрутов
            List<Route> routes = routeDAO.searchRoutes(departureCity, arrivalCity, date);

            // Передача данных на JSP
            request.setAttribute("routes", routes);
            request.getRequestDispatcher("/results.jsp").forward(request, response);

        } catch (Exception e) {
            // Обработка ошибок
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Произошла ошибка при обработке запроса.");
        }
    }
}