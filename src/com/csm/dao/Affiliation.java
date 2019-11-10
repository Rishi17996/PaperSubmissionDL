package com.csm.dao;

import com.csm.database.MySQLDatabase;

public class Affiliation {
	
	private int affiliationId;
	private String affiliationName;
	private MySQLDatabase db;
	
	public Affiliation() {
		db = new MySQLDatabase();
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
