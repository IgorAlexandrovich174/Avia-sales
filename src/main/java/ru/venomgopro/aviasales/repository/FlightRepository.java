package ru.venomgopro.aviasales.repository;

import org.springframework.stereotype.Component;
import ru.venomgopro.aviasales.controller.dto.FlightCreateRequest;
import ru.venomgopro.aviasales.model.Flight;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlightRepository {

    private final Connection connection;

    public FlightRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Flight> getAllFlight() throws SQLException {
        List<Flight> allFlight = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM flight");
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Flight flight = new Flight(
                        resultSet.getInt("id"),
                        resultSet.getString("departure_airport"),
                        resultSet.getString("arrival_airport"),
                        resultSet.getDate("date").toLocalDate());
                allFlight.add(flight);
            }
        }
        return allFlight;
    }

    public Flight create(FlightCreateRequest flightCreateRequest) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO flight(departure_airport,arrival_airport,date) VALUES(?,?,?) RETURNING id");
        preparedStatement.setString(1, flightCreateRequest.getDepartureAirport());
        preparedStatement.setString(2, flightCreateRequest.getArrivalAirport());
        preparedStatement.setDate(3, Date.valueOf(flightCreateRequest.getDate()));

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (!resultSet.next()) {
                return null;
            }
            return new Flight(resultSet.getInt("id"),
                    flightCreateRequest.getDepartureAirport(),
                    flightCreateRequest.getArrivalAirport(),
                    flightCreateRequest.getDate());
        }
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

//    public void delete(int id) {
//        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM flight where id = ?")) {
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

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

