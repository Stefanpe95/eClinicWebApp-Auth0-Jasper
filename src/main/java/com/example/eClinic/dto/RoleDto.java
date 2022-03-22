package com.example.eClinic.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RoleDto {

	private long roleid;
	private String roleName;

	public long getRoleID() {
		return roleid;
	}

	public void setRoleID(long roleID) {
		this.roleid = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public RoleDto(long roleid, String roleName) {
		super();
		this.roleid = roleid;
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "RoleDto [roleID=" + roleid + ", roleName=" + roleName + "]";
	}

}
