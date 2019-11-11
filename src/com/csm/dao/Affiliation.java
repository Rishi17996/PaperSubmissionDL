package com.csm.dao;

import java.util.ArrayList;
import com.csm.database.MySQLDatabase;
import com.csm.utility.Utilities;

public class Affiliation {
	
	private int affiliationId;
	private String affiliationName;
	private MySQLDatabase db;
	
	public Affiliation(String affiliationName) {
		
		// instantiate new mysql
		// database instance
		db = new MySQLDatabase();
		this.affiliationName = affiliationName;
		this.affiliationId = fetchNextAffiliationId();
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

	public int generateId() {
		Utilities.generateId(1, 5000);
		return 0;
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
