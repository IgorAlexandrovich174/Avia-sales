package ru.venomgopro.aviasales.repository;

import org.springframework.stereotype.Component;
import ru.venomgopro.aviasales.controller.dto.FlightCreateRequest;
import ru.venomgopro.aviasales.model.Flight;

import java.time.LocalDate;
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

    public Flight change(int id, FlightCreateRequest flightCreateRequest) {
        return allFlights.put(id,
                new Flight(
                        id,
                        flightCreateRequest.getDepartureAirport(),
                        flightCreateRequest.getArrivalAirport(),
                        flightCreateRequest.getDate()));
    }

}
