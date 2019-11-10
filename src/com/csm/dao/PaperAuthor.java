package com.csm.dao;

import com.csm.database.MySQLDatabase;

public class PaperAuthor {
	
	private int paperId;
	private int userId;
	private int displayOrder;
	private MySQLDatabase db;
	
	public PaperAuthor() {
		db = new MySQLDatabase();
	}

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public MySQLDatabase getDb() {
		return db;
	}

	public void setDb(MySQLDatabase db) {
		this.db = db;
	}
}
