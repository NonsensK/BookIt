package com.example.bookit.dao;

import com.example.bookit.dao.TicketDAO;
import com.example.bookit.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public class TicketDAOTest {
    public static void main(String[] args) {
        TicketDAO ticketDAO = new TicketDAO();

        try {
            // 1. Создать новый билет и добавить его в базу
            Ticket ticket = new Ticket();
            ticket.setUserName("John Doe");
            ticket.setUserEmail("john.doe@example.com");
            ticket.setRouteId(105); // Укажите существующий route_id
            ticket.setPurchaseDate(LocalDateTime.now());
            ticket.setAvailableQuantity(50); // Примерное значение

            ticketDAO.addTicket(ticket);
            System.out.println("Билет успешно добавлен!");

            // 2. Получить все билеты и вывести их в консоль
            List<Ticket> tickets = ticketDAO.getAllTickets();
            System.out.println("Все билеты:");
            for (Ticket t : tickets) {
                System.out.println(t);
            }

            // 3. Обновить количество доступных билетов
            if (!tickets.isEmpty()) {
                Ticket firstTicket = tickets.get(0);
                int newQuantity = firstTicket.getAvailableQuantity() - 1; // Уменьшаем на 1
                ticketDAO.updateAvailableQuantity(firstTicket.getId(), newQuantity);
                System.out.println("Доступное количество билетов обновлено!");
            }

        } catch (Exception e) {
            System.err.println("Ошибка:");
            e.printStackTrace();
        }
    }
}