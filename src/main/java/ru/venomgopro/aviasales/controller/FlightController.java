package ru.venomgopro.aviasales.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.venomgopro.aviasales.controller.dto.FlightCreateRequest;
import ru.venomgopro.aviasales.model.Flight;
import ru.venomgopro.aviasales.repository.FlightRepository;

import java.sql.SQLException;
import java.util.List;

@RestController //спринг увидит эту аннотацию, когда будет запускаться (он сканирует все классы пакета)
public class FlightController {
    private FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("flights")
    public List<Flight> allFlight() throws SQLException {
        return flightRepository.getAllFlight();
    }

    @GetMapping("flights/{id}")
    public Flight getById(@PathVariable Integer id) {
        return flightRepository.getById(id);
    }

    @PostMapping("flights")
    public Flight create(@RequestBody FlightCreateRequest flightCreateRequest) throws SQLException {
        return flightRepository.create(flightCreateRequest);
    }

    @PutMapping(value = "flights/{id}")
    public Flight change(@PathVariable Integer id, @RequestBody FlightCreateRequest flightCreateRequest) {
        Flight flight = flightRepository.change(id, flightCreateRequest);
        if (flight == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Рейс не найден");
        }
        return flight;
    }

    @DeleteMapping("flights/{id}")
    public void delete(@PathVariable Integer id) {
        flightRepository.delete(id);
    }
}
