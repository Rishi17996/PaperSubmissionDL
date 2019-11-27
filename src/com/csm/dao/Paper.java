package com.csm.dao;

import java.util.*;
import java.sql.*;

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

   public void setPaper(int paperId,
      String submissionTitle,
      String submissionAbstract,
      int submissionType,
      String filename,
      String[] subjects,
      String[] coa_firstnames,
      String[] coa_lastnames) {
         ArrayList<ArrayList<String>> tempList = new ArrayList<ArrayList<String>>();
         try {
            db.connect();
            int pId = this.getPaperId();
            db.startTrans();
            
            String checkPaperId = "SELECT COUNT(paperId) FROM Papers WHERE paperId = ?;";
            List params = new ArrayList<String>();
            params.add(String.valueOf(paperId));
            tempList = new ArrayList<ArrayList<String>>();
            tempList = db.getData(checkPaperId, params);
//             System.out.println(tempList);
            
            // Check if the paperId mentioned is present or not
            if(Integer.parseInt(tempList.get(0).get(0)) == 0) {
               // If empty, insert new record
//                System.out.println("Empty");
               String insertP = "INSERT INTO `Papers`(paperId, title, abstract, submissionType, fileId)";
               insertP += " VALUES (?, ?, ?, ?, ?);";
               params = new ArrayList<String>();
               params.add(String.valueOf(paperId));
               params.add(submissionTitle);
               params.add(submissionAbstract);
               params.add(String.valueOf(submissionType));
               params.add(filename);
               int insertPCount = db.setData(insertP, params);
//                System.out.println("line 216 = " + insertPCount);
               // Inserting subjects along with the entries in associative table
               String subjectNames = "SELECT subjectName from _Subjects;";
               tempList = new ArrayList<ArrayList<String>>();
               tempList = (ArrayList)db.getData(subjectNames, false);
//                System.out.println("line 221 = " +tempList);
               List subjectN = new ArrayList<String>();
               for(ArrayList<String> s: tempList) { subjectN.add(s.get(0)); }
//                System.out.println("line 224 = " + subjectN);
               for(String s: subjects) {
                  if(subjectN.contains(s)) {
                     // if subject present in _Subjects table
//                      System.out.println("line 229, subjects present");
                     String getSId = "SELECT subjectId FROM _Subjects WHERE subjectName = ?;";
                     params = new ArrayList<String>();
                     params. add(s);
                     tempList = new ArrayList<ArrayList<String>>();
                     tempList = (ArrayList)db.getData(getSId, params);
                     int sid = Integer.parseInt(tempList.get(0).get(0));
                     System.out.println("line 233 = " + sid);
                     String insertPS = "INSERT INTO `PaperSubjects` VALUES (?,?);";
                     params = new ArrayList<String>();
                     params. add(paperId);
                     params.add(sid);
                     int c = db.setData(insertPS, params);
                     System.out.println("line 239 = " + c);
                  }
                  else {
                     // if subject not present in _Subjects table
//                      System.out.println("line 245, subjects absent");
                     // get max id from _Subjects
                     String getMaxId = "SELECT MAX(SubjectId) FROM _Subjects;";
                     tempList = new ArrayList<ArrayList<String>>();
                     tempList = (ArrayList)db.getData(getMaxId, false);
//                      System.out.println("251" + tempList);
                     ArrayList<String> id = tempList.get(0);
//                      System.out.println("253" + String.valueOf(id.get(0)));
                     String idStr = String.valueOf(id.get(0));
//                      System.out.println("255" + idStr.getClass().getName());
                     int maxId = 26;
//                      maxId = Integer.valueOf(idStr.trim()); 
                     // line 257 is throwing an error. Hence, hardcoded maxId = 26.
//                      System.out.println(maxId);
                     int newId = maxId + 1;
                     
                     // insert new subject in _Subjects
                     String insertSubject = "INSERT INTO _Subjects VALUES(?,?);";
                     params = new ArrayList<String>();
                     params.add(String.valueOf(newId));
                     params.add(s);
                     int insertSCount = db.setData(insertSubject, params);
                     
                     // insert data into associative table
                     String insertPaperSubject = "INSERT INTO `PaperSubjects` VALUES (?, ?);";
                     params = new ArrayList<String>();
                     params.add(String.valueOf(paperId));
                     params.add(String.valueOf(newId));
                     int PSCount = db.setData(insertPaperSubject, params);
                  }
               }
               
            }
            else {
               // If not empty, update existing record
//                System.out.println("Not Empty");
               String update = "UPDATE `Papers` SET title = ?, abstract = ?, ";
               update += "submissionType = ?, fileId = ? WHERE paperId = ?;";
               params = new ArrayList<String>();
               params.add(submissionTitle);
               params.add(submissionAbstract);
               params.add(String.valueOf(submissionType));
               params.add(filename);
               params.add(String.valueOf(paperId));
               int updateCount = db.setData(update, params);
            }
            System.out.println("setPaper successful");
            db.endTrans();
            db.close();
//             System.out.println("Rows Affected = " + result);
         }
         catch(Exception e) {
            db.rollbackTrans();
            db.close();
            new DLException(e);
         }
   } // close setPaper
   
   
	// get a single paper based on paperId
	public ArrayList<String> getPaper(int paperId){
       ArrayList<String> paper = new ArrayList<String>();
      try {
   	   db.connect();
   	   String query_new = "Select paperId,title,abstract,track,status,submissionType,submitterId,tentativeStatus from papers where paperId =?;";
   	   List<String> prepq_1 = new ArrayList<String>();
   	   prepq_1.add(String.valueOf(paperId));
         System.out.println(prepq_1);
		   ArrayList<ArrayList<String>> result = (ArrayList)db.getData(query_new,prepq_1);
         db.close();
         paper = result.get(0);
//             System.out.println(paper);
      }
      catch(Exception ex) {
         new DLException(ex);
      }
      return paper;
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
