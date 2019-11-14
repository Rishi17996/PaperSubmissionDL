package com.csm.dao;

import java.util.ArrayList;
import java.util.List;

import com.csm.database.DLException;
import com.csm.database.MySQLDatabase;

public class Paper {
	
	private int paperId;
	private String title;
	private String abstr;
	private String track;
	private String status;
	private int submissionType;
	private int submitterId;
	private String fileId;
	private String tentativeStatus;
	private MySQLDatabase db;
	
	public Paper(String title,
			String abstr,
			String track,
			String status,
			int submissionType,
			int submitterId,
			String fileId,
			String tentativeStatus) {
		
		// create new database instance
		db = new MySQLDatabase();
		
		// set paper attributes
		this.paperId = this.fetchNextPaperId();
		this.title = title;
		this.abstr = abstr;
		this.track = track;
		this.status = status;
		this.submissionType = submissionType;
		this.submitterId = submitterId;
		this.fileId = fileId;
		this.tentativeStatus = tentativeStatus;
	}
	
	public String generateRandomString() {
		
		return null;
	}
	/**
	 * Fetch equipment arraylist
	 *
	 * @return int
	 */
	public int fetchNextPaperId() {

		// query database for equipment by id
		ArrayList<ArrayList<Object>> tempList = new ArrayList<ArrayList<Object>>();

		// create and execute query passing in
		// table format boolean and return
		// equipment collection
		String query = "SELECT MAX(paperId) FROM papers";
		
		// connect to database
		db.connect();

		// query database with get method
		tempList = db.getData(query, 1);
		
		// close database connection
		db.close();
		
		// convert and store incremented paperid
		int recordCount = Integer.parseInt((String) tempList.get(0).get(0)) + 1;
				
		return recordCount;
	}
	
	/**
	 * Post new record to database
	 *
	 * @return int
	 */
	public int post(int subjectId) {

		// create post query
		String postQuery = "INSERT into `papers` (paperId, title, abstract, track, "
				+ "status, submissionType, submitterId, fileId, tentativeStatus) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// create paper string list and set string values
		List<String> paperList = new ArrayList<String>();
		paperList.add(0, String.valueOf(this.paperId));
		paperList.add(1, this.title);
		paperList.add(2, this.abstr);
		paperList.add(3, this.track);
		paperList.add(4, this.status);
		paperList.add(5, String.valueOf(this.submissionType));
		paperList.add(6, String.valueOf(this.submitterId));
		paperList.add(7, this.fileId);
		paperList.add(8, this.tentativeStatus);
		
		// create subject post query
		String subjectQuery = "INSERT into `papersubjects` (paperId, subjectId) "
				+ "VALUES (?, ?)";
		
		// create string list and set string values
		List<String> subjectList = new ArrayList<String>();
		subjectList.add(0, String.valueOf(this.paperId));
		subjectList.add(1, String.valueOf(subjectId));
		
		int postDataResult = 0;
		int postSubjectResult = 0;

		try {
			// connect to database
			db.connect();

			// start transaction
			db.startTrans();
			
			// post data
			postDataResult = db.setData(postQuery, paperList);
			postSubjectResult = db.setData(subjectQuery, subjectList);

			// end transaction
			db.endTrans();
			
			// close database connection
			db.close();
		} catch(Exception e) {
			try {
				// rollback transaction
				db.rollbackTrans();
				
				// throw dlexception and pass error info
				String[] errorInfo = { String.valueOf(e.getStackTrace()) };
				throw new DLException(e, errorInfo);
			} catch (DLException e1) {
				System.out.println("There was an error completing an operation.");
			}
		}
		
		// return records created count
		return postDataResult + postSubjectResult;
	}
	
	public ArrayList<Paper> fetchPapers(int userId) {
		ArrayList<Paper> papers = new ArrayList<Paper>();
		
		
		return papers;
	}

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstr() {
		return abstr;
	}

	public void setAbstr(String abstr) {
		this.abstr = abstr;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSubmissionType() {
		return submissionType;
	}

	public void setSubmissionType(int submissionType) {
		this.submissionType = submissionType;
	}

	public int getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(int submitterId) {
		this.submitterId = submitterId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setField(String fileId) {
		this.fileId = fileId;
	}

	public String getTentativeStatus() {
		return tentativeStatus;
	}

	public void setTentativeStatus(String tentativeStatus) {
		this.tentativeStatus = tentativeStatus;
	}

	public MySQLDatabase getDb() {
		return db;
	}

	public void setDb(MySQLDatabase db) {
		this.db = db;
	}
}
