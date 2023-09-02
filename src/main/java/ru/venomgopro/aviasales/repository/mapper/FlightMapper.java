package ru.venomgopro.aviasales.repository.mapper;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.venomgopro.aviasales.model.Flight;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class FlightMapper implements RowMapper<Flight> {
    @Override
    public Flight mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Flight(
                resultSet.getInt("id"),
                resultSet.getString("departure_airport"),
                resultSet.getString("arrival_airport"),
                resultSet.getDate("date").toLocalDate(),
                resultSet.getInt("aircraft_id"));
    }
}
