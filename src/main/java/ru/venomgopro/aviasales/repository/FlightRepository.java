package ru.venomgopro.aviasales.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.venomgopro.aviasales.controller.dto.FlightCreateRequest;
import ru.venomgopro.aviasales.model.Flight;
import ru.venomgopro.aviasales.repository.mapper.FlightMapper;

import java.sql.SQLException;
import java.util.List;

@Component
public class FlightRepository {
    private final FlightMapper flightMapper;

    private final JdbcTemplate jdbcTemplate;

    public FlightRepository(FlightMapper flightMapper, JdbcTemplate jdbcTemplate) {
        this.flightMapper = flightMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Flight> getAllFlight() throws SQLException {
        return jdbcTemplate.query("SELECT * FROM flight", flightMapper);
    }

    public Flight create(FlightCreateRequest flightCreateRequest) throws SQLException {
        Integer id = jdbcTemplate.queryForObject(
                "INSERT INTO flight(departure_airport,arrival_airport,date) VALUES(?,?,?) RETURNING id",
                Integer.class);
        return new Flight(id,
                flightCreateRequest.getDepartureAirport(),
                flightCreateRequest.getArrivalAirport(),
                flightCreateRequest.getDate());
    }

    public Flight change(int id, FlightCreateRequest flightCreateRequest) {
//        Flight flight = getById(id);
//        if (flight == null) {
//            return null;
//        }
//        try (PreparedStatement preparedStatement = connection.prepareStatement(
//                "UPDATE flight SET departure_airport = ?, arrival_airport = ?, date = ? WHERE id = ?")) {
//
//            preparedStatement.setString(1, flightCreateRequest.getDepartureAirport());
//            preparedStatement.setString(2, flightCreateRequest.getArrivalAirport());
//            preparedStatement.setDate(3, Date.valueOf(flightCreateRequest.getDate()));
//            preparedStatement.setInt(4, id);
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return getById(id);
        String sql = "UPDATE flight SET departure_airport = ?, arrival_airport = ?, date = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                flightCreateRequest.getDepartureAirport(),
                flightCreateRequest.getArrivalAirport(),
                flightCreateRequest.getDate());
        return getById(id);
    }

    public void delete(int id) {
//        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM flight where id = ?")) {
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public Flight getById(int id) {
//        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM flight where id = ?")) {
//            stmt.setInt(1, id);
//            try (ResultSet result = stmt.executeQuery()) {
//                if (!result.next()) {
//                    return null;
//                } else {
//                    var departure_airport = result.getString("departure_airport");
//                    var arrival_airport = result.getString("arrival_airport");
//                    LocalDate date = result
//                            .getDate("date")
//                            .toLocalDate();
//                    return new Flight(id, departure_airport, arrival_airport, date);
//                }
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
        return null;
    }
}

