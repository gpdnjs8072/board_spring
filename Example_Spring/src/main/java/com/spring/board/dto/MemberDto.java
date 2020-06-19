package com.spring.board.dto;

public class MemberDto {
	private String mem_id;
	private String mem_pwd;
	private String mem_email;
	private String mem_name;	
	private String mem_stateCode;
	private String mem_authCode;
	private String mem_regdate;
	private int mem_loginFailCount;
	private String stateName;
	private String authName;
	
	public MemberDto() {}
	

	public MemberDto(String mem_id, String mem_pwd, String mem_email, String mem_name, String mem_stateCode,
			String mem_authCode, String mem_regdate, int mem_loginFailCount, String stateName, String authName) {
		super();
		this.mem_id = mem_id;
		this.mem_pwd = mem_pwd;
		this.mem_email = mem_email;
		this.mem_name = mem_name;
		this.mem_stateCode = mem_stateCode;
		this.mem_authCode = mem_authCode;
		this.mem_regdate = mem_regdate;
		this.mem_loginFailCount = mem_loginFailCount;
		this.stateName = stateName;
		this.authName = authName;
	}


	public String getMem_id() {
		return mem_id;
	}


	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}


	public String getMem_pwd() {
		return mem_pwd;
	}


	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}


	public String getMem_email() {
		return mem_email;
	}


	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}


	public String getMem_name() {
		return mem_name;
	}


	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}


	public String getMem_stateCode() {
		return mem_stateCode;
	}


	public void setMem_stateCode(String mem_stateCode) {
		this.mem_stateCode = mem_stateCode;
	}


	public String getMem_authCode() {
		return mem_authCode;
	}


	public void setMem_authCode(String mem_authCode) {
		this.mem_authCode = mem_authCode;
	}


	public String getMem_regdate() {
		return mem_regdate;
	}


	public void setMem_regdate(String mem_regdate) {
		this.mem_regdate = mem_regdate;
	}


	public int getMem_loginFailCount() {
		return mem_loginFailCount;
	}


	public void setMem_loginFailCount(int mem_loginFailCount) {
		this.mem_loginFailCount = mem_loginFailCount;
	}


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}


	public String getAuthName() {
		return authName;
	}


	public void setAuthName(String authName) {
		this.authName = authName;
	}
	
	
}
