package ru.venomgopro.aviasales.model;

import java.time.LocalDate;

public class Flight {
    private Integer id;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate date;
    private Integer aircraftId;

    public Flight(Integer id, String departureAirport, String arrivalAirport, LocalDate date, Integer aircraftId) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.date = date;
        this.aircraftId = aircraftId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getAircraftId() {
        return aircraftId;
    }

    public void setAirplaneId(Integer aircraftId) {
        this.aircraftId = aircraftId;
    }

}
