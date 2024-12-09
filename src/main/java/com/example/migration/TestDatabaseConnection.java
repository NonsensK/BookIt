package com.example.migration;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try {
            System.out.println("Проверка подключения к базе данных...");
            if (DatabaseConnectionManager.getConnection() != null) {
                System.out.println("Успешное подключение к базе данных.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка подключения к базе данных.");
            e.printStackTrace();
        }
    }
}