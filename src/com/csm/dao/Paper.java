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
	 * Fetch next available
	 * equipment id
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
	 * Post new paper to database
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
		} finally {
			// close database connection
			db.close();
		}

		// return records created count
		return postDataResult + postSubjectResult;
	}


	// get a list of papers baser on userId
	public ArrayList<Paper> getPapers(int userId) {
		ArrayList<Paper> papers = new ArrayList<Paper>();
    try{
		MySQLDatabase db = new MySQLDatabase();
      db.connect();
      String query = "SELECT title,abstract,track,status,submissionType,submitterId,fileId,tentativeStatus";
      query += " FROM Papers JOIN PaperAuthors using(paperId) JOIN Users using (userId) WHERE userId=?; ";
      List <String> prepq = new ArrayList<String>();
      prepq.add(String.valueOf(userId));
      ArrayList<ArrayList<String>> result = (ArrayList)db.getData(query,prepq);
      db.close();
      System.out.println(result);
      for(int i = 0; i < result.size();i++ ){
        ArrayList<String> row = new ArrayList<String>();
        row = result.get(i);
        Paper p1 = new Paper(row.get(0),
															row.get(1),
															row.get(2),
															row.get(3),
															Integer.parseInt(row.get(4)),
															Integer.parseInt(row.get(5)),
															row.get(6),
															row.get(7));
        papers.add(p1);
      }
      }catch(Exception e){
         e.getMessage();
      }
		return papers;
	}


	// get a single paper based on paperId
	public ArrayList<Paper> getPaper(int paperId){
   ArrayList<Paper> newPaper = new ArrayList<Paper>();
   try{
   	db.connect();
   	String query_new = "Select paperId,title,abstract,track,status,submissionType,submitterId,tentativeStatus from papers where paperId =?;";
   	List<String> prepq_1 = new ArrayList<String>();
   	prepq_1.add(String.valueOf(paperId));
   	System.out.println(prepq_1);
		ArrayList<ArrayList<String>> result = (ArrayList)db.getData(query_new,prepq_1);
    db.close();
    for(int i = 0; i < result.size();i++ ) {
      ArrayList<String> row = new ArrayList<String>();
      row = result.get(i);
      Paper p2 = new Paper(row.get(0),
				row.get(1),
				row.get(2),
				row.get(3),
				Integer.parseInt(row.get(4)),
				Integer.parseInt(row.get(5)),
				row.get(6),
				row.get(7));
      newPaper.add(p2);
         // System.out.println(newPaper);
    }
   }
   catch(Exception e){
      System.out.println(e.getMessage());
   }
   return newPaper;
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
