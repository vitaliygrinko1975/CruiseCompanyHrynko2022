package ua.nure.hrynko.dto;


import java.util.Objects;

public class OrderView extends Entity {

    private static final long serialVersionUID = -6889036256149495388L;

    private String usersFirstName;

    private String usersLastName;

    private String usersEmail;

    private String cruisesDescription;

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

    public String getCruisesDescription() {
        return cruisesDescription;
    }

    public void setCruisesDescription(String cruisesDescription) {
        this.cruisesDescription = cruisesDescription;
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
        return Objects.equals(usersFirstName, orderView.usersFirstName) && Objects.equals(usersLastName, orderView.usersLastName) && Objects.equals(usersEmail, orderView.usersEmail) && Objects.equals(cruisesDescription, orderView.cruisesDescription) && Objects.equals(status, orderView.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersFirstName, usersLastName, usersEmail, cruisesDescription, status);
    }


    @Override
    public String toString() {
        return "OrderView{" +
                "usersFirstName='" + usersFirstName + '\'' +
                ", usersLastName='" + usersLastName + '\'' +
                ", usersEmail='" + usersEmail + '\'' +
                ", usersDescription='" + cruisesDescription + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

