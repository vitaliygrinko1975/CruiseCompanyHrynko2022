package ua.nure.hrynko.dto;

import java.util.Objects;

public class Ship extends Entity {

    private static final long serialVersionUID = -6889036256149495388L;

    private String name;

    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship)) return false;
        Ship ships = (Ship) o;
        return name.equals(ships.name) && description.equals(ships.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Ships{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
