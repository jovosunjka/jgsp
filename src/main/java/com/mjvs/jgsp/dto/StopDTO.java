package com.mjvs.jgsp.dto;

import com.mjvs.jgsp.model.Stop;

public class StopDTO {

    private String name;

    private double latitude;

    private double longitude;

    public StopDTO()
    {

    }

    public StopDTO(Stop stop) {
        this.name = stop.getName();
        this.latitude = stop.getLatitude();
        this.longitude = stop.getLongitude();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
