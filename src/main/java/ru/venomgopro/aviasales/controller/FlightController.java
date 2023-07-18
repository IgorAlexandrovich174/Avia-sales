package ru.venomgopro.aviasales.controller;

import org.springframework.web.bind.annotation.*;
import ru.venomgopro.aviasales.controller.dto.FlightCreateRequest;
import ru.venomgopro.aviasales.model.Flight;
import ru.venomgopro.aviasales.repository.FlightRepository;

import java.util.Collection;

@RestController //спринг увидит эту аннотацию, когда будет запускаться (он сканирует все классы пакета)
public class FlightController {
    private FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("flights")
    public Collection<Flight> getAll() {
        return flightRepository.getAllFlights();
    }

    @GetMapping("flights/{id}")
    public Flight getById(@PathVariable Integer id) {
        return flightRepository.getById(id);
    }

    @PostMapping("flights")
    public Flight create(@RequestBody FlightCreateRequest flightCreateRequest) {
        return flightRepository.create(flightCreateRequest);
    }

    @PutMapping("flights/{id}")
    public Flight change(@PathVariable Integer id, @RequestBody FlightCreateRequest flightCreateRequest) throws Exception {
        return flightRepository.change(id,flightCreateRequest);
    }

    @DeleteMapping("flights/{id}")
    public void delete(@PathVariable Integer id){
        flightRepository.delete(id);
    }
}
