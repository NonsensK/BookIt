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
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchRoutesServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchRoutesServlet.class.getName());
    private final RouteDAO routeDAO = new RouteDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получение данных из формы
        String departureCity = request.getParameter("departureCity");
        String arrivalCity = request.getParameter("arrivalCity");
        String date = request.getParameter("date");

        // Лог для входных данных
        LOGGER.info("Полученные данные из формы:");
        LOGGER.info("departureCity: " + departureCity);
        LOGGER.info("arrivalCity: " + arrivalCity);
        LOGGER.info("date: " + date);

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

            // Лог перед вызовом метода DAO
            LOGGER.info("Перед вызовом getRoutesByCriteria:");
            LOGGER.info("departureCity: " + departureCity);
            LOGGER.info("arrivalCity: " + arrivalCity);
            LOGGER.info("travelDate: " + travelDate);

            // Получение маршрутов
            List<Route> routes = routeDAO.getRoutesByCriteria(departureCity, arrivalCity, travelDate);

            // Лог результата DAO
            if (routes.isEmpty()) {
                LOGGER.info("Маршруты не найдены.");
                request.setAttribute("message", "Маршруты не найдены.");
            } else {
                for (Route route : routes) {
                    LOGGER.info(route.toString());
                }
                request.setAttribute("routes", routes);
            }

            // Передача данных на JSP
            request.getRequestDispatcher("/results.jsp").forward(request, response);
        } catch (Exception e) {
            logErrorAndRespond(request, response, "Ошибка обработки данных. Проверьте введенные данные.", HttpServletResponse.SC_BAD_REQUEST, e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Перенаправляем запросы GET на главную страницу
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void logErrorAndRespond(HttpServletRequest request, HttpServletResponse response, String errorMessage, int statusCode, Exception e) throws ServletException, IOException {
        LOGGER.log(Level.SEVERE, errorMessage, e);
        response.setStatus(statusCode);
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}