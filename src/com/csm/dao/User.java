package com.csm.dao;

public abstract class User {
	
	protected int userId;
	protected String lastName;
	protected String firstName;
	protected String email;
	protected String pswd;
	protected String expiration;
	protected int isAdmin;
	protected int affiliationId;
	protected Paper paper;

	public User() {
		
	}
	
	public abstract int submitPaper();
	
	public String[] getProfile() {
		String[] profile = new String[10];
		return profile;
	}
	
	public void setProfile(String lastName,
			String firstName,
			String email,
			String password,
			String affiliation) {
	}
	
	public void resetPassword(String email) {
		
	}
	
	public void login(String email, String password) {
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return pswd;
	}
	
	public void setPassword(String pswd) {
		this.pswd = pswd;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getAffiliationId() {
		return affiliationId;
	}

	public void setAffiliationId(int affiliationId) {
		this.affiliationId = affiliationId;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}
}
