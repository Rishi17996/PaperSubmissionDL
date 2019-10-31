package com.csm.dao;

public class User {
	
	private int userId;
	private String lastName;
	private String firstName;
	private String email;
	private String password;
	private String expiration;
	private int isAdmin;
	private int affiliationId;

	public User() {
		
	}
	
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
	
	public void setPassword(String password) {

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
		return password;
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
}
