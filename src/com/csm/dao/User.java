package com.csm.dao;

import java.util.ArrayList;

public abstract class User {
	
	protected int userId;
	protected String lastName;
	protected String firstName;
	protected String email;
	protected String pswd;
	protected String expiration;
	protected String canReview;
	protected int isAdmin;
	protected int affiliationId;
	protected ArrayList<Paper> paperList;

	public User(String lastName,
			String firstName,
			String email,
			String pswd,
			String expiration,
			int isAdmin,
			int affiliationId,
			String canReview) {
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.pswd = pswd;
		this.expiration = expiration;
		this.isAdmin = isAdmin;
		this.affiliationId = affiliationId;
		this.canReview = canReview;
	}
	
	public abstract int fetchNextUserId();
	
	public abstract int postPaperSubject(int subjectId);
	
	public abstract int postPaperAuthor(int paperId);
	
	public abstract int submitPaper();
	
	public abstract int post();
	
	public abstract ArrayList<Paper> getPapers();
	
	public abstract String[] getProfile();
	
	public abstract void setProfile(String lastName,
			String firstName,
			String email,
			String password,
			String affiliation);
	
	public abstract void resetPassword(String email);
	
	public abstract void login(String email, String password);

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
}
