package com.csm.dao;

import java.util.ArrayList;
import java.util.List;

import com.csm.database.MySQLDatabase;

public class PaperSubject extends Subject {

	private int paperId;
	private int subjectId;
	private MySQLDatabase db;
	
	public PaperSubject(int paperId, String subjectId) {
		super(paperId, subjectId);
		
		// instantiate new
		// database instance
		db = new MySQLDatabase();
	}
	
	/**
	 * Post new record to database
	 *
	 * @return int
	 */
	public int post() {

		// create post query
		String postQuery = "INSERT into `papers` (paperId, title, abstract, track, "
				+ "status, submissionType, submitterId, fileId, tentativeStatus) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// create string list and set string values
		List<String> stringList = new ArrayList<String>();
		stringList.add(0, String.valueOf(this.paperId));
		stringList.add(0, String.valueOf(this.subjectId));

		// connect to database
		db.connect();

		// post data
		int postDataResult = db.setData(postQuery, stringList);

		// close database connection
		db.close();
		
		// return records created count
		return postDataResult;
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
