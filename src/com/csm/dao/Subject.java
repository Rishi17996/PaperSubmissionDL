package com.csm.dao;

import com.csm.database.MySQLDatabase;

public class Subject {
	
	private int subjectId;
	private String subjectName;
	private MySQLDatabase db;
	
	public Subject() {
		db = new MySQLDatabase();
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public MySQLDatabase getDb() {
		return db;
	}

	public void setDb(MySQLDatabase db) {
		this.db = db;
	}
}
