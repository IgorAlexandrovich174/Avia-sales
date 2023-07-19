package ru.venomgopro.aviasales.repository;

import org.springframework.stereotype.Component;
import ru.venomgopro.aviasales.controller.dto.FlightCreateRequest;
import ru.venomgopro.aviasales.model.Flight;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class FlightRepository {

    private final Connection connection;
    private int counter;
    private Map<Integer, Flight> allFlights = new HashMap<>();

    public Collection<Flight> getAllFlights() {
        return allFlights.values();
    }

    public FlightRepository(Connection connection) {
        this.connection = connection;
    }

    public Flight create(FlightCreateRequest flightCreateRequest) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO flight(departure_airport,arrival_airport,date) VALUES(?,?,?) RETURNING id");
        preparedStatement.setString(1, flightCreateRequest.getDepartureAirport());
        preparedStatement.setString(2, flightCreateRequest.getArrivalAirport());
        preparedStatement.setDate(3, Date.valueOf(flightCreateRequest.getDate()));
        preparedStatement.executeUpdate();
        return flight;
    }

    public Flight change(int id, FlightCreateRequest flightCreateRequest) {
        Flight flight = getById(id);
        if (flight == null) {
            return null;
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE flight SET departure_airport = ?, arrival_airport = ?, date = ? WHERE id = ?")) {

            preparedStatement.setString(1, flightCreateRequest.getDepartureAirport());
            preparedStatement.setString(2, flightCreateRequest.getArrivalAirport());
            preparedStatement.setDate(3, Date.valueOf(flightCreateRequest.getDate()));
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getById(id);
    }

    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM flight where id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Flight getById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM flight where id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet result = stmt.executeQuery()) {
                if (!result.next()) {
                    return null;
                } else {
                    var departure_airport = result.getString("departure_airport");
                    var arrival_airport = result.getString("arrival_airport");
                    LocalDate date = result
                            .getDate("date")
                            .toLocalDate();
                    return new Flight(id, departure_airport, arrival_airport, date);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

