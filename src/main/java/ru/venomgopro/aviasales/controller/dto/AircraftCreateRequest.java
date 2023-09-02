package ru.venomgopro.aviasales.controller.dto;

import java.util.Objects;

public class AircraftCreateRequest {
    private String model;
    private Integer seatsCount;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(Integer seatsCount) {
        this.seatsCount = seatsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AircraftCreateRequest that = (AircraftCreateRequest) o;
        return Objects.equals(model, that.model) && Objects.equals(seatsCount, that.seatsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, seatsCount);
    }

    @Override
    public String toString() {
        return "AircraftCreateRequest{" +
                "model='" + model + '\'' +
                ", seatsCount=" + seatsCount +
                '}';
    }
}
