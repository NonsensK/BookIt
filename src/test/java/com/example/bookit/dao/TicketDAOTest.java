package com.example.bookit.dao;

import com.example.bookit.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public class TicketDAOTest {
    public static void main(String[] args) {
        // Создание экземпляра DAO
        TicketDAO ticketDAO = new TicketDAO();

        // Тест: добавление нового билета
        Ticket ticket = new Ticket();
        ticket.setUserName("John Doe");
        ticket.setUserEmail("john.doe@example.com");
        ticket.setRouteId(105); // Укажите существующий route_id из вашей базы данных
        ticket.setPurchaseDate(LocalDateTime.now());

        try {
            ticketDAO.addTicket(ticket);
            System.out.println("Билет успешно добавлен!");
        } catch (Exception e) {
            System.err.println("Ошибка при добавлении билета:");
            e.printStackTrace();
        }

        // Тест: получение всех билетов
        try {
            List<Ticket> tickets = ticketDAO.getAllTickets();
            System.out.println("Все билеты:");
            for (Ticket t : tickets) {
                System.out.println(t);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при получении всех билетов:");
            e.printStackTrace();
        }

        // Тест: поиск билетов по email
        try {
            String testEmail = "john.doe@example.com";
            List<Ticket> ticketsByEmail = ticketDAO.getTicketsByEmail(testEmail);
            System.out.println("Билеты для пользователя с email " + testEmail + ":");
            for (Ticket t : ticketsByEmail) {
                System.out.println(t);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при поиске билетов по email:");
            e.printStackTrace();
        }
    }
}