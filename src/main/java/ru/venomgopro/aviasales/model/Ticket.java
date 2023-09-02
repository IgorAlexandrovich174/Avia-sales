package ru.venomgopro.aviasales.model;

import java.util.Objects;

public class Ticket {
    private String id;
    private Integer seat;
    private String passengersLastName;
    private Integer flightId;

    public Ticket(String id, Integer seat, String passengersLastName, Integer flightId) {
        this.id = id;
        this.seat = seat;
        this.passengersLastName = passengersLastName;
        this.flightId = flightId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getPassengersLastName() {
        return passengersLastName;
    }

    public void setPassengersLastName(String passengersLastName) {
        this.passengersLastName = passengersLastName;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seat=" + seat +
                ", passengersLastName=" + passengersLastName +
                ", flightId=" + flightId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(seat, ticket.seat) && Objects.equals(passengersLastName, ticket.passengersLastName) && Objects.equals(flightId, ticket.flightId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seat, passengersLastName, flightId);
    }
}
