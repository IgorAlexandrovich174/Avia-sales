package ru.venomgopro.aviasales.repository.mapper;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.venomgopro.aviasales.model.Flight;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class FlightMapper implements RowMapper<Flight> {
    @Override
    public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Flight(
                rs.getInt("id"),
                rs.getString("departure_airport"),
                rs.getString("arrival_airport"),
                rs.getDate("date").toLocalDate());
    }
}
