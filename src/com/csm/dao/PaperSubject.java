package com.csm.dao;

import com.csm.database.MySQLDatabase;

public class PaperSubject {

	private int paperId;
	private int subjectId;
	private MySQLDatabase db;
	
	public PaperSubject() {
		db = new MySQLDatabase();
	}

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public MySQLDatabase getDb() {
		return db;
	}

	public void setDb(MySQLDatabase db) {
		this.db = db;
	}
}
