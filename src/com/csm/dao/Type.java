package com.csm.dao;

import com.csm.database.MySQLDatabase;

public class Type {

	private int typeId;
	private String typeName;
	private MySQLDatabase db;
	
	public Type() {
		db = new MySQLDatabase();
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public MySQLDatabase getDb() {
		return db;
	}

	public void setDb(MySQLDatabase db) {
		this.db = db;
	}
}
