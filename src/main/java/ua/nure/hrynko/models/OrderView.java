package ua.nure.hrynko.models;


import java.util.Objects;

public class OrderView extends Entity {

    private static final long serialVersionUID = -6889036256149495388L;

    private String usersFirstName;

    private String usersLastName;

    private String usersEmail;

    private int usersId;

    private String cruisesDescription;

    private String statusOfCruises;

    private String status;

    public String getUsersFirstName() {
        return usersFirstName;
    }

    public void setUsersFirstName(String usersFirstName) {
        this.usersFirstName = usersFirstName;
    }

    public String getUsersLastName() {
        return usersLastName;
    }

    public void setUsersLastName(String usersLastName) {
        this.usersLastName = usersLastName;
    }

    public String getUsersEmail() {
        return usersEmail;
    }

    public void setUsersEmail(String usersEmail) {
        this.usersEmail = usersEmail;
    }
    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }
    public String getCruisesDescription() {
        return cruisesDescription;
    }

    public void setCruisesDescription(String cruisesDescription) {
        this.cruisesDescription = cruisesDescription;
    }

    public String getStatusOfCruises() {
        return statusOfCruises;
    }
    public void setStatusOfCruises(String statusOfCruises) {
        this.statusOfCruises = statusOfCruises;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderView)) return false;
        OrderView orderView = (OrderView) o;
        return usersId == orderView.usersId && usersFirstName.equals(orderView.usersFirstName) && usersLastName.equals(orderView.usersLastName) && usersEmail.equals(orderView.usersEmail) && cruisesDescription.equals(orderView.cruisesDescription) && statusOfCruises.equals(orderView.statusOfCruises) && status.equals(orderView.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersFirstName, usersLastName, usersEmail, usersId, cruisesDescription, statusOfCruises, status);
    }

    @Override
    public String toString() {
        return "OrderView{" +
                "usersFirstName='" + usersFirstName + '\'' +
                ", usersLastName='" + usersLastName + '\'' +
                ", usersEmail='" + usersEmail + '\'' +
                ", usersId=" + usersId +
                ", cruisesDescription='" + cruisesDescription + '\'' +
                ", statusOfCruises='" + statusOfCruises + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

