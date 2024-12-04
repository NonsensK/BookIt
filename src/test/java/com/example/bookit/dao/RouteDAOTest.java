package com.example.bookit.dao;

import com.example.bookit.model.Route;
import com.example.migration.DatabaseConnectionManager;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RouteDAOTest {
    private RouteDAO routeDAO;

    @BeforeAll
    void setUp() {
        routeDAO = new RouteDAO();
    }

    @BeforeEach
    void setUpDatabase() throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            connection.createStatement().execute("DELETE FROM routes");
        }
    }

    @Test
    void testAddRoute() throws SQLException {
        Route route = new Route();
        route.setDepartureCity("Warsaw");
        route.setArrivalCity("Krakow");
        route.setDepartureTime(LocalDateTime.of(2024, 12, 3, 8, 0));
        route.setArrivalTime(LocalDateTime.of(2024, 12, 3, 11, 0));
        route.setPrice(100.00);

        routeDAO.addRoute(route);

        List<Route> routes = routeDAO.getAllRoutes();
        assertEquals(1, routes.size());
        assertEquals("Warsaw", routes.get(0).getDepartureCity());
    }

    @Test
    void testGetAllRoutes() throws SQLException {
        Route route1 = new Route();
        route1.setDepartureCity("Warsaw");
        route1.setArrivalCity("Krakow");
        route1.setDepartureTime(LocalDateTime.of(2024, 12, 3, 8, 0));
        route1.setArrivalTime(LocalDateTime.of(2024, 12, 3, 11, 0));
        route1.setPrice(100.00);

        Route route2 = new Route();
        route2.setDepartureCity("Gdansk");
        route2.setArrivalCity("Poznan");
        route2.setDepartureTime(LocalDateTime.of(2024, 12, 4, 14, 30));
        route2.setArrivalTime(LocalDateTime.of(2024, 12, 4, 18, 45));
        route2.setPrice(120.50);

        routeDAO.addRoute(route1);
        routeDAO.addRoute(route2);

        List<Route> routes = routeDAO.getAllRoutes();
        assertEquals(2, routes.size());
    }
}