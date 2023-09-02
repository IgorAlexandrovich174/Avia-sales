package ru.venomgopro.aviasales.repository;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.venomgopro.aviasales.controller.dto.TicketCreateRequest;
import ru.venomgopro.aviasales.model.Ticket;
import ru.venomgopro.aviasales.repository.mapper.TicketMapper;

@Component
public class TicketRepository {
    private JdbcTemplate jdbcTemplate;
    private TicketMapper ticketMapper;

    public TicketRepository(JdbcTemplate jdbcTemplate, TicketMapper ticketMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.ticketMapper = ticketMapper;
    }

    // TODO: 04.08.2023 доделать метод
    public Ticket create(Long flightId, TicketCreateRequest ticketCreateRequest) {
        String sql = "INSERT INTO tickets (id, seat, passengers_last_name, flight_id) VALUES (?,?,?,?) RETURNING *";
//        String ticketsCountSql = "SELECT COUNT(*) FROM tickets WHERE flight_id = ?";
        String availableSeatCountSql = "SElECT seats_count " +
                "FROM aircrafts JOIN flights ON flights.aircraft_id = aircrafts.id WHERE flights.id = ?";
        Integer seatsCount = jdbcTemplate.queryForObject(availableSeatCountSql,
                new Object[]{flightId},
                Integer.class);
        if (ticketCreateRequest.getSeat() > seatsCount) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Все билеты на самолёт выкуплены!");
        }
        // TODO: 02.09.2023 сделать валидацию дубликатов мест
        return jdbcTemplate.query(sql,
                        new Object[]{
                                ticketCreateRequest.getId(),
                                ticketCreateRequest.getSeat(),
                                ticketCreateRequest.getPassengersLastName(),
                                flightId
                        },
                        ticketMapper)
                .stream()
                .findAny()
                .orElse(null);
    }

    public Ticket change(String ticketId, TicketCreateRequest ticketCreateRequest) {
        // TODO: 02.09.2023 сделать проверку на дубликат места
        String sql = "UPDATE tickets SET seat = ?, passengers_last_name = ? WHERE id = ? RETURNING *";
        Ticket ticket = jdbcTemplate.query(sql,
                        new Object[]{ticketCreateRequest.getSeat(),
                                ticketCreateRequest.getPassengersLastName(),
                                ticketId},
                        ticketMapper)
                .stream()
                .findAny()
                .orElse(null);
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Билет не найден");
        }
        return ticket;
    }

    public void delete(String id) {
        jdbcTemplate.update("DELETE FROM tickets WHERE id = ?", id);
    }
}
