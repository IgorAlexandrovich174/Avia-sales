package ru.venomgopro.aviasales.model;

import java.util.Objects;

public class Aircraft {
    private Integer id;

    private String aircraftCode;

    private String model;

    private Integer seatsCount;

    public Aircraft(Integer id, String aircraftCode, String model, Integer seatsCount) {
        this.id = id;
        this.aircraftCode = aircraftCode;
        this.model = model;
        this.seatsCount = seatsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public Integer getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(Integer seatsCount) {
        this.seatsCount = seatsCount;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return Objects.equals(id, aircraft.id) && Objects.equals(aircraftCode, aircraft.aircraftCode) && Objects.equals(model, aircraft.model) && Objects.equals(seatsCount, aircraft.seatsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aircraftCode, model, seatsCount);
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", aircraftCode='" + aircraftCode + '\'' +
                ", model='" + model + '\'' +
                ", seatsCount=" + seatsCount +
                '}';
    }
}
