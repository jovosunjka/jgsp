package com.mjvs.jgsp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Line extends LineZone {
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "active", unique = false, nullable = false)
    private boolean active;

    @Column(name = "minutes-required-for-whole-route")
    private int minutesRequiredForWholeRoute;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Zone zone;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Stop> stops;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transport> transports;

    // orphanRemoval znaci da kad izbrisemo neki Schedule iz ove liste, on ce postati siroce, i bice automatski obrisan
    // i iz tabele schedule
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules;

    public Line() {

    }

    public Line(String name)
    {
        this.name = name;
    }

    public Line(String name, int minutesRequiredForWholeRoute, Zone zone, List<Stop> stops, List<Transport> transports, List<Schedule> schedules) {
        this.name = name;
        this.minutesRequiredForWholeRoute = minutesRequiredForWholeRoute;
        this.zone = zone;
        this.active = true;
        this.stops = stops;
        this.transports = transports;
        this.schedules = schedules;
    }

    public Line(String name, Zone zone) {
        this.name = name;
        this.minutesRequiredForWholeRoute = minutesRequiredForWholeRoute;
        this.zone = zone;
        this.active = false;
        this.stops = new ArrayList<>();
        this.transports = new ArrayList<>();
        this.schedules = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinutesRequiredForWholeRoute() {
        return minutesRequiredForWholeRoute;
    }

    public void setMinutesRequiredForWholeRoute(int minutesRequiredForWholeRoute) {
        this.minutesRequiredForWholeRoute = minutesRequiredForWholeRoute;
    }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public Zone getZone() {
        return zone;
    }

	@Override
	protected double getPrice(PriceTicket priceTicket) {
		return priceTicket.getPriceLine();
	}

    @Override
    public String toString() {
        return name + " ("+ zone.getName() + ")";
    }
}
