package ua.nure.hrynko.models;
import java.util.Date;
import java.util.Objects;

public class ShipView extends Entity {

    private static final long serialVersionUID = -6889036256149495388L;
    private String name;
    private String description;
    private int capacity;
    private Date startOfContract;
    private Date endOfContract;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public int getCapacity() {return capacity;}
    public void setCapacity(int capacity) {this.capacity = capacity;}
    public Date getStartOfContract() {return startOfContract;}
    public void setStartOfContract(Date startOfContract) {this.startOfContract = startOfContract;}
    public Date getEndOfContract() {return endOfContract;}
    public void setEndOfContract(Date endOfContract) {this.endOfContract = endOfContract;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShipView)) return false;
        ShipView shipView = (ShipView) o;
        return capacity == shipView.capacity && name.equals(shipView.name) && description.equals(shipView.description) && startOfContract.equals(shipView.startOfContract) && endOfContract.equals(shipView.endOfContract);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, description, capacity, startOfContract, endOfContract);
    }
    @Override
    public String toString() {
        return "ShipView{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", startOfContract=" + startOfContract +
                ", endOfContract=" + endOfContract +
                '}';
    }

}
