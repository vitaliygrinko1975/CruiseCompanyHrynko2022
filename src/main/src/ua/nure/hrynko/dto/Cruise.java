package ua.nure.hrynko.dto;

import java.util.Date;
import java.util.Objects;


public class Cruise extends Entity {

    private static final long serialVersionUID = 4716395168539434663L;

    private String name;

    private String description;

    private double price;

    private int shipId;

    private int  capacity;

    private Date startOfCruise;

    private int duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getStartOfCruise() {
        return startOfCruise;
    }

    public void setStartOfCruise(Date startOfCruise) {
        this.startOfCruise = startOfCruise;
    }

    public int getDuration() { return duration;}

    public void setDuration(int duration) {this.duration = duration;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cruise)) return false;
        Cruise cruise = (Cruise) o;
        return Double.compare(cruise.price, price) == 0 && shipId == cruise.shipId && capacity == cruise.capacity
                && duration == cruise.duration && name.equals(cruise.name) && description.equals(cruise.description)
                && startOfCruise.equals(cruise.startOfCruise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, shipId, capacity, startOfCruise, duration);
    }

    @Override
    public String toString() {
        return "Cruise{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", shipId=" + shipId +
                ", capacity=" + capacity +
                ", startOfCruise=" + startOfCruise +
                ", duration=" + duration +
                '}';
    }
}
