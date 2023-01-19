package ua.nure.hrynko.models;
import java.util.Objects;

public class Ship extends Entity {

    private static final long serialVersionUID = -6889036256149495388L;
    private String name;
    private String description;
    private int capacity;


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public int getCapacity() {return capacity;}
    public void setCapacity(int capacity) {this.capacity = capacity;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship)) return false;
        Ship ship = (Ship) o;
        return capacity == ship.capacity && name.equals(ship.name) && description.equals(ship.description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, description, capacity);
    }
    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
