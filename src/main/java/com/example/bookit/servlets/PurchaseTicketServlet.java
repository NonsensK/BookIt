package com.example.bookit.servlets;

import com.example.bookit.dao.TicketDAO;
import com.example.bookit.model.Ticket;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseTicketServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PurchaseTicketServlet.class.getName());
    private final TicketDAO ticketDAO = new TicketDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем данные из формы
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        int routeId = Integer.parseInt(request.getParameter("routeId"));

        // Логируем входные данные
        LOGGER.info("Полученные данные из формы:");
        LOGGER.info("userName: " + userName);
        LOGGER.info("userEmail: " + userEmail);
        LOGGER.info("routeId: " + routeId);

        // Количество купленных билетов (в данном случае 1)
        int purchasedTickets = 1;

        // Проверка доступности билетов
        try {
            if (ticketDAO.areTicketsAvailable(routeId, purchasedTickets)) {
                // Создаем новый билет
                Ticket ticket = new Ticket();
                ticket.setUserName(userName);
                ticket.setUserEmail(userEmail);
                ticket.setRouteId(routeId);

                // Сохраняем билет в базе данных
                ticketDAO.addTicket(ticket);

                // Обновляем количество доступных билетов
                ticketDAO.updateAvailableQuantity(routeId, purchasedTickets);

                // Сообщаем об успешной покупке
                request.setAttribute("message", "Билет успешно куплен!");
            } else {
                // Если билетов нет в наличии
                request.setAttribute("error", "Нет доступных билетов на выбранный маршрут.");
            }

            // Перенаправляем на страницу с результатами
            request.getRequestDispatcher("/purchaseResult.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ошибка при покупке билета", e);
            request.setAttribute("error", "Ошибка при покупке билета.");
            request.getRequestDispatcher("/purchaseResult.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Перенаправляем GET запросы на главную страницу
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}