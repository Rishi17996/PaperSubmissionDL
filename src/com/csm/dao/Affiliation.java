package com.csm.dao;

import com.main.MySQLDatabase;

public class Affiliation {
	
	private int affiliationId;
	private String affiliationName;
	private MySQLDatabase db;
	
	public Affiliation() {
		
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
}
