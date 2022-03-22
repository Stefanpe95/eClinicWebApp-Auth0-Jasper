package com.example.eClinic.dto;

public class UserAddDto {

	private String userid;
	private String email;
	private String name;

	private Long patientid;
	private Long doctorid;
	private Long roleid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPatientid() {
		return patientid;
	}

	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}

	public Long getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(Long doctorid) {
		this.doctorid = doctorid;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public UserAddDto(String userid, String email, String name, Long patientid, Long doctorid, Long roleid) {
		super();
		this.userid = userid;
		this.email = email;
		this.name = name;
		this.patientid = patientid;
		this.doctorid = doctorid;
		this.roleid = roleid;
	}

	@Override
	public String toString() {
		return "UserAddDto [userid=" + userid + ", email=" + email + ", name=" + name + ", patientid=" + patientid
				+ ", doctorid=" + doctorid + ", roleid=" + roleid + "]";
	}

}
