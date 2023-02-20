package ua.nure.hrynko.models;

import java.util.Date;

public class CruiseHasShip extends Entity {
    private static final long serialVersionUID = -6889036256149495388L;
    private int cruiseId;
    private int shipId;
    private Date startOfContract;
    private Date endOfContract;
    private String status;
    public int getCruiseId() {return cruiseId;}
    public void setCruiseId(int cruiseId) {this.cruiseId = cruiseId;}
    public int getShipId() {return shipId;}
    public void setShipId(int shipId) {this.shipId = shipId;}
    public Date getStartOfContract() {return startOfContract;}
    public void setStartOfContract(Date startOfContract) {this.startOfContract = startOfContract;}
    public Date getEndOfContract() {return endOfContract;}
    public void setEndOfContract(Date endOfContract) {this.endOfContract = endOfContract;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    @Override
    public String toString() {
        return "CruiseHasShip{" +
                "cruiseId=" + cruiseId +
                ", shipId=" + shipId +
                ", startOfContract=" + startOfContract +
                ", endOfContract=" + endOfContract +
                ", status='" + status + '\'' +
                '}';
    }
}

