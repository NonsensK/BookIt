package com.example.bookit.dao;

import com.example.bookit.model.Ticket;
import com.example.migration.DatabaseConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    // Метод для добавления нового билета
    public void addTicket(Ticket ticket) throws SQLException {
        String query = "INSERT INTO tickets (user_name, user_email, route_id, purchase_date) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, ticket.getUserName());
            preparedStatement.setString(2, ticket.getUserEmail());
            preparedStatement.setInt(3, ticket.getRouteId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(ticket.getPurchaseDate()));
            preparedStatement.executeUpdate();
        }
    }

    // Метод для получения всех билетов
    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Ticket ticket = mapResultSetToTicket(resultSet);
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    // Метод для поиска билетов по email пользователя
    public List<Ticket> getTicketsByEmail(String email) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets WHERE user_email = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Ticket ticket = mapResultSetToTicket(resultSet);
                    tickets.add(ticket);
                }
            }
        }
        return tickets;
    }

    // Вспомогательный метод для преобразования ResultSet в объект Ticket
    private Ticket mapResultSetToTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getInt("id"));
        ticket.setUserName(resultSet.getString("user_name"));
        ticket.setUserEmail(resultSet.getString("user_email"));
        ticket.setRouteId(resultSet.getInt("route_id"));
        ticket.setPurchaseDate(resultSet.getTimestamp("purchase_date").toLocalDateTime());
        return ticket;
    }
}
