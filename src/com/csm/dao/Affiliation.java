package com.csm.dao;

import java.util.ArrayList;
import java.util.List;

import com.csm.database.MySQLDatabase;

public class Affiliation {
	
	private int affiliationId;
	private String affiliationName;
	private MySQLDatabase db;
	
	public Affiliation(String affiliationName) {
		
		// instantiate new mysql
		// database instance
		db = new MySQLDatabase();
		this.affiliationId = this.fetchNextAffiliationId();
		this.affiliationName = affiliationName;
	}
	
	/**
	 * Fetch equipment arraylist
	 *
	 * @return int
	 */
	public int fetchNextAffiliationId() {

		// query database for equipment by id
		ArrayList<ArrayList<Object>> tempList = new ArrayList<ArrayList<Object>>();

		// create and execute query passing in
		// table format boolean and return
		// equipment collection
		String query = "select MAX(affiliationId) from _affiliations";

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
	public int post() {

		// create post query
		String postQuery = "INSERT into `_affiliations` (affiliationName, affiliationId) VALUES (?, ?)";
		
		// create string list and set string values
		List<String> stringList = new ArrayList<String>();
		stringList.add(0, affiliationName);
		stringList.add(1, String.valueOf(this.affiliationId));


		// connect to database
		db.connect();

		// post data
		int postDataResult = db.setData(postQuery, stringList);

		// close database connection
		db.close();
		
		// return records changed count
		return postDataResult;
	}

	public int getAffiliationId() {
		return affiliationId;
	}

	public void setAffiliationId(int affiliationId) {
		this.affiliationId = affiliationId;
	}

	public String getAffiliationName() {
		return affiliationName;
	}

	public void setAffiliationName(String affiliationName) {
		this.affiliationName = affiliationName;
	}

	public MySQLDatabase getDb() {
		return db;
	}

	public void setDb(MySQLDatabase db) {
		this.db = db;
	}
}
