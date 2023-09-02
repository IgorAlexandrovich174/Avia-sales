package ru.venomgopro.aviasales.controller;

public class AircraftController {
    private char aircraft_code;

    private String model;

    private Integer range;

    public char getAircraft_code() {
        return aircraft_code;
    }

    public void setAircraft_code(char aircraft_code) {
        this.aircraft_code = aircraft_code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }
}
