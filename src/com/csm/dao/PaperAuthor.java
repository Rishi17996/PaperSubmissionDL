package com.csm.dao;

import java.util.ArrayList;
import java.util.List;

import com.csm.database.MySQLDatabase;

public class PaperAuthor extends User {
	
	private MySQLDatabase db;
	private int paperId;
	private int userId;
	private String affiliationName;
	
	public PaperAuthor(String lastName,
			String firstName,
			String email,
			String pswd,
			String expiration,
			int isAdmin,
			int affiliationId,
			String canReview) {
		
		super(lastName, firstName, email, pswd, expiration, isAdmin, affiliationId, canReview);
		
		// instantiate new mysql
		// database instance
		this.db = new MySQLDatabase();
		
		// set user attributes
		this.userId = this.fetchNextUserId();
	}
	
	/**
	 * Fetch userid arraylist
	 *
	 * @return int
	 */
	@Override
	public int fetchNextUserId() {

		// query database for equipment by id
		ArrayList<ArrayList<Object>> tempList = new ArrayList<ArrayList<Object>>();

		// create and execute query passing in
		// table format boolean and return
		// equipment collection
		String query = "SELECT MAX(userId) FROM users";
		
		// connect to database
		db.connect();

		// query database with get method
		tempList = db.getData(query, 1);
		
		// close database connection
		db.close();
		
		// convert and store incremented affiliationid
		int recordCount = Integer.parseInt((String) tempList.get(0).get(0)) + 1;
				
		return recordCount;
	}

	/**
	 * Post new record to database
	 *
	 * @return int
	 */
	public int postPaperSubject(int subjectId) {

		// create post query
		String postQuery = "INSERT into `papersubjects` (paperId, subjectId) VALUES (?, ?)";
		
		// create string list and set string values
		List<String> stringList = new ArrayList<String>();
		stringList.add(0, String.valueOf(this.paperId));
		stringList.add(1, String.valueOf(subjectId));

		// connect to database
		db.connect();

		// post data
		int postDataResult = db.setData(postQuery, stringList);

		// close database connection
		db.close();
		
		// return records changed count
		return postDataResult;
	}
	
	/**
	 * Post new record to database
	 *
	 * @return int
	 */
	@Override
	public int post() {

		// create post query
		String postQuery = "INSERT into `users` (userId, lastName, firstName, "
				+ "email, pswd, expiration, isAdmin, affiliationId, canReview) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// create string list and set string values
		List<String> stringList = new ArrayList<String>();
		stringList.add(0, String.valueOf(this.userId));
		stringList.add(1, this.lastName);
		stringList.add(2, this.firstName);
		stringList.add(3, this.email);
		stringList.add(4, this.pswd);
		stringList.add(5, this.expiration);
		stringList.add(6, String.valueOf(this.isAdmin));
		stringList.add(7, String.valueOf(this.affiliationId));
		stringList.add(8, this.canReview);

		// connect to database
		db.connect();

		// post data
		int postDataResult = db.setData(postQuery, stringList);
		
		// close database connection
		db.close();
		
		// return records changed count
		return postDataResult;
	}
	
	/**
	 * Post new record to database
	 *
	 * @return int
	 */
	@Override
	public int postPaperAuthor(int paperId) {

		// disable/enable foreign key checks
		String disableFKQuery = "SET FOREIGN_KEY_CHECKS=0;"; 
		String enableFKQuery = "SET FOREIGN_KEY_CHECKS=1;";
		
		// create post query
		String postQuery = "INSERT into `paperauthors` (userId, paperId) "
				+ "VALUES (?, ?)";
		
		// create string list and set string values
		List<String> stringList = new ArrayList<String>();
		stringList.add(0, String.valueOf(paperId));
		stringList.add(0, String.valueOf(this.userId));

		// connect to database
		db.connect();

		db.setData(disableFKQuery, 1);

		// post data
		int postDataResult = db.setData(postQuery, stringList);
		
		db.setData(enableFKQuery, 1);
		
		// close database connection
		db.close();
		
		// return records changed count
		return postDataResult;
	}

	@Override
	public int submitPaper() {
		return 0;
	}

	@Override
	public ArrayList<Paper> getPapers() {
		return null;
	}

	@Override
	public String[] getProfile() {
		return null;
	}

	@Override
	public void setProfile(String lastName, 
			String firstName, 
			String email, 
			String password, 
			String affiliationName) {
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.pswd = password;
		this.affiliationName = affiliationName;
	}

	@Override
	public void resetPassword(String email) {
		
	}

	@Override
	public void login(String email, String password) {
		
	}

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public String getAffiliationName() {
		return affiliationName;
	}

	public void setAffiliationName(String affiliationName) {
		this.affiliationName = affiliationName;
	}
}
