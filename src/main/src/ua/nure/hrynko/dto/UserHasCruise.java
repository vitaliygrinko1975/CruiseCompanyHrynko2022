package ua.nure.hrynko.dto;

import java.util.Objects;

public class UserHasCruise extends Entity {

    private static final long serialVersionUID = -6889036256149495388L;

    private int userId;

    private int cruiseId;

    private String status;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
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
        if (!(o instanceof UserHasCruise)) return false;
        UserHasCruise that = (UserHasCruise) o;
        return userId == that.userId && cruiseId == that.cruiseId && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, cruiseId, status);
    }

    @Override
    public String toString() {
        return "UsersHasCruises{" +
                "userId=" + userId +
                ", cruiseId=" + cruiseId +
                ", status='" + status + '\'' +
                '}';
    }
}
