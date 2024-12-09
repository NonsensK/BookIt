package com.example.bookit.servlets;

import com.example.bookit.dao.TicketDAO;
import com.example.bookit.model.Ticket;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class PurchaseTicketServlet extends HttpServlet {

    private final TicketDAO ticketDAO = new TicketDAO();
    private final Random random = new Random();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String routeIdStr = request.getParameter("routeId");

        try {
            int routeId = Integer.parseInt(routeIdStr);

            // Случайная ошибка оплаты (1 из 10)
            if (random.nextInt(10) == 0) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("К сожалению, произошла ошибка при обработке оплаты. Попробуйте снова.");
                return;
            }

            // Создаем билет
            Ticket ticket = new Ticket();
            ticket.setUserName(userName);
            ticket.setUserEmail(userEmail);
            ticket.setRouteId(routeId);
            ticket.setPurchaseDate(LocalDateTime.now());

            // Сохраняем билет в базе
            ticketDAO.addTicket(ticket);

            // Успех
            response.getWriter().write("Спасибо за покупку! Детали отправлены на ваш email.");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Произошла ошибка при обработке вашего запроса.");
        }
    }
}