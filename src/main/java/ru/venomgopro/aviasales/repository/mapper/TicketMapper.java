package ru.venomgopro.aviasales.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.venomgopro.aviasales.model.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TicketMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Ticket(rs.getString("id"),
                rs.getInt("seat"),
                rs.getString("passengers_last_name"),
                rs.getInt("flight_id"));
    }
}
