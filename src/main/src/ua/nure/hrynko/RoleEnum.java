package ua.nure.hrynko;

import ua.nure.hrynko.dto.User;

/**
 * Role enum.

 */

public enum RoleEnum {
	ADMIN, CLIENT;
	
	public static RoleEnum getRole(User user) {
		int roleId = user.getRoleId();
		return RoleEnum.values()[roleId - 1];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
