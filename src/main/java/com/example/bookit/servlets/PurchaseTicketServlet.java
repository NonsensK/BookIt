package com.example.bookit.servlets;

import com.example.bookit.dao.TicketDAO;
import com.example.bookit.model.Ticket;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PurchaseTicketServlet extends HttpServlet {

    private final TicketDAO ticketDAO = new TicketDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем данные из формы
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String routeIdStr = request.getParameter("routeId");

        // Логируем входные данные
        System.out.println("Данные из формы: " + userName + ", " + userEmail + ", " + routeIdStr);

        if (userName == null || userEmail == null || routeIdStr == null ||
                userName.isEmpty() || userEmail.isEmpty() || routeIdStr.isEmpty()) {
            request.setAttribute("error", "Все поля должны быть заполнены.");
            request.getRequestDispatcher("/purchase.jsp").forward(request, response);
            return;
        }

        try {
            int routeId = Integer.parseInt(routeIdStr);

            // Создаем билет
            Ticket ticket = new Ticket();
            ticket.setUserName(userName);
            ticket.setUserEmail(userEmail);
            ticket.setRouteId(routeId);
            ticket.setPurchaseDate(LocalDateTime.now());

            // Сохраняем билет в базе данных
            ticketDAO.addTicket(ticket);

            // Сообщаем об успешной покупке
            request.setAttribute("message", "Билет успешно куплен!");
            request.getRequestDispatcher("/success.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Некорректный ID маршрута.");
            request.getRequestDispatcher("/purchase.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка базы данных. Попробуйте позже.");
            request.getRequestDispatcher("/purchase.jsp").forward(request, response);
        }
    }
}