package ua.nure.hrynko.models;

import java.util.Objects;

/**
 * Role entity.
 */

public class Role extends Entity {

    private static final long serialVersionUID = -6889036256149495388L;

    private String roleName;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + getId() +
                ", roleName='" + roleName + '\'' +
                '}';
    }




}

