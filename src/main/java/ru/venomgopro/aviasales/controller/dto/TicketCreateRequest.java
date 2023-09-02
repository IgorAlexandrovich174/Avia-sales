package ru.venomgopro.aviasales.controller.dto;

import java.util.Objects;

public class TicketCreateRequest {
    private String id;
    private Integer seat;
    private String passengersLastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeat() {
        return seat;
    }

//    public void setSeat(Integer seat) {
//        this.seat = seat;
//    }

    public String getPassengersLastName() {
        return passengersLastName;
    }

    public void setPassengersLastName(String passengersLastName) {
        this.passengersLastName = passengersLastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCreateRequest that = (TicketCreateRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(seat, that.seat) && Objects.equals(passengersLastName, that.passengersLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seat, passengersLastName);
    }

    @Override
    public String toString() {
        return "TicketCreateRequest{" +
                "id='" + id + '\'' +
                ", seat=" + seat +
                ", passengersLastName='" + passengersLastName + '\'' +
                '}';
    }
}
