package ru.venomgopro.aviasales.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.venomgopro.aviasales.controller.dto.FlightCreateRequest;
import ru.venomgopro.aviasales.model.Flight;
import ru.venomgopro.aviasales.repository.mapper.FlightMapper;

import java.sql.Date;
import java.util.List;

@Component
public class FlightRepository {
    private final FlightMapper flightMapper;

    private final JdbcTemplate jdbcTemplate;

    public FlightRepository(FlightMapper flightMapper, JdbcTemplate jdbcTemplate) {
        this.flightMapper = flightMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Flight> getAllFlight() {
        return jdbcTemplate.query("SELECT * FROM flight", flightMapper);
    }

    public Flight create(FlightCreateRequest flightCreateRequest) {
        return jdbcTemplate.query(
                        "INSERT INTO flight(departure_airport,arrival_airport,date)VALUES (?,?,?) RETURNING *",
                        new Object[]{
                                flightCreateRequest.getDepartureAirport(),
                                flightCreateRequest.getArrivalAirport(),
                                flightCreateRequest.getDate()}, flightMapper)
                .stream()
                .findAny()
                .orElse(null);
    }

    public Flight change(int id, FlightCreateRequest flightCreateRequest) {
        String sql = "UPDATE flight SET departure_airport = ?, arrival_airport = ?, date = ? WHERE id = ? RETURNING *";
        return jdbcTemplate.query(
                        sql,
                        new Object[]{flightCreateRequest.getDepartureAirport(),
                                flightCreateRequest.getArrivalAirport(),
                                Date.valueOf(flightCreateRequest.getDate()),
                                id}, flightMapper)
                .stream()
                .findAny()
                .orElse(null);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM flight WHERE id = ?", id);
    }

    public Flight getById(int id) {
        return jdbcTemplate.query("SELECT * FROM flight WHERE id = ?", new Object[]{id}, flightMapper)
                .stream()
                .findAny()
                .orElse(null);
    }
}


