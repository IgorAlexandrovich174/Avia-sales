package ru.venomgopro.aviasales.repository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.venomgopro.aviasales.controller.dto.FlightCreateRequest;
import ru.venomgopro.aviasales.model.Flight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class FlightRepository {
    private int counter;
    private Map<Integer, Flight> allFlights = new HashMap<>();

    public Collection<Flight> getAllFlights() {
        return allFlights.values();
    }

    public Flight getById(int id) {
        if (!allFlights.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Рейс не найден");
        }
        return allFlights.get(id);
    }

    public Flight create(FlightCreateRequest flightCreateRequest) {
        counter++;
        Flight flight = new Flight(
                counter,
                flightCreateRequest.getDepartureAirport(),
                flightCreateRequest.getArrivalAirport(),
                flightCreateRequest.getDate());

        allFlights.put(counter, flight);
        return flight;
    }

    public Flight change(int id, FlightCreateRequest flightCreateRequest) throws ResponseStatusException {
        if (!allFlights.containsKey(id)) {
            // TODO: 09.07.2023 создать исключение
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Рейс не найден");
        }
        Flight flight = allFlights.get(id);
        flight.setDepartureAirport(flightCreateRequest.getDepartureAirport());
        flight.setArrivalAirport(flightCreateRequest.getArrivalAirport());
        flight.setDate(flightCreateRequest.getDate());
        return flight;
    }

    // TODO: 15.07.2023 Сделать с выбросом исключения без проверки содержания айдишника в списке 
    public void delete(int id) throws RuntimeException {
        allFlights.remove(id);
    }

    public void test() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/aviasales", "postgres", "password")) {
            try (Statement stmt = con.createStatement()) {
            stmt.execute("SELECT * FROM flight");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

