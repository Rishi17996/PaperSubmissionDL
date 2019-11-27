package com.csm.dao;

import java.util.*;

import com.csm.database.MySQLDatabase;
import com.csm.database.DLException;
import com.csm.utility.Utilities;

public class PaperAuthor extends User {

	private MySQLDatabase db;
	private int paperId;
	private int userId;

	public PaperAuthor() {
		super();

		// instantiate new mysql
		// database instance
		this.db = new MySQLDatabase();
	}

	public PaperAuthor(String lastName,
			String firstName,
			String email,
			String pswd,
			String expiration,
			int isAdmin,
			int affiliationId,
			String canReview) {

		super(lastName, firstName, email, pswd, expiration, isAdmin, affiliationId, canReview);

		// instantiate new mysql
		// database instance
		this.db = new MySQLDatabase();

		// set user id
		this.userId = this.fetchNextUserId();
	}

	/**
	 * Fetch userid int
	 *
	 * @return int
	 */
	@Override
	public int fetchNextUserId() {

		// query database for equipment by id
		ArrayList<ArrayList<Object>> tempList = new ArrayList<ArrayList<Object>>();

		// create and execute query passing in
		// table format boolean and return
		// equipment collection
		String query = "SELECT MAX(userId) FROM users";

		// connect to database
		db.connect();

		// query database with get method
		tempList = db.getData(query, 1);

		// close database connection
		db.close();

		// convert and store incremented affiliationid
		int recordCount = Integer.parseInt((String) tempList.get(0).get(0)) + 1;

		return recordCount;
	}

	/**
	 * Post new record to database
	 *
	 * @return int
	 */
	public int postPaperSubject(int subjectId) {

		// create post query
		String postQuery = "INSERT into `papersubjects` (paperId, subjectId) VALUES (?, ?)";

		// create string list and set string values
		List<String> stringList = new ArrayList<String>();
		stringList.add(0, String.valueOf(this.paperId));
		stringList.add(1, String.valueOf(subjectId));

		// connect to database
		db.connect();

		// post data
		int postDataResult = db.setData(postQuery, stringList);

		// close database connection
		db.close();

		// return records changed count
		return postDataResult;
	}

	/**
	 * Post new user record
	 * to database
	 *
	 * @return int
	 */
	@Override
	public int post() {

		// create post query
		String postQuery = "INSERT into `users` (userId, lastName, firstName, "
				+ "email, pswd, expiration, isAdmin, affiliationId, canReview) "
				+ "VALUES (?, ?, ?, ?, SHA1(?), ?, ?, ?, ?);";

		// create string list and set string values
		List<String> stringList = new ArrayList<String>();
		stringList.add(0, String.valueOf(this.userId));
		stringList.add(1, this.lastName);
		stringList.add(2, this.firstName);
		stringList.add(3, this.email);
		stringList.add(4, this.pswd);
		stringList.add(5, this.expiration);
		stringList.add(6, String.valueOf(this.isAdmin));
		stringList.add(7, String.valueOf(this.affiliationId));
		stringList.add(8, this.canReview);

		// connect to database
		db.connect();

		// post data
		int postDataResult = db.setData(postQuery, stringList);

		// close database connection
		db.close();

		// return records changed count
		return postDataResult;
	}

	/**
	 * Post new paperauthor
	 * record to database
	 *
	 * @return int
	 */
	@Override
	public int postPaperAuthor(int paperId) {

		// disable/enable foreign key checks
		String disableFKQuery = "SET FOREIGN_KEY_CHECKS=0;";
		String enableFKQuery = "SET FOREIGN_KEY_CHECKS=1;";

		// create post query
		String postQuery = "INSERT into `paperauthors` (userId, paperId) "
				+ "VALUES (?, ?)";

		// create string list and set string values
		List<String> stringList = new ArrayList<String>();
		stringList.add(0, String.valueOf(paperId));
		stringList.add(0, String.valueOf(this.userId));

		// result set count
		int postDataResult = 0;

		try {
			// connect to database
			db.connect();

			// start transaction
			db.startTrans();

			db.setData(disableFKQuery, 1);

			// post data
			postDataResult = db.setData(postQuery, stringList);

			db.setData(enableFKQuery, 1);

			// end transaction
			db.endTrans();
		} catch (Exception e) {
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

		// return records changed count
		return postDataResult;
	}

	@Override
	public int submitPaper() {
		return 0;
	}

	@Override
	public ArrayList<Paper> getPapers() {
		return null;
	}

	@Override
	public String[] getProfile() {
		String[] userProfile = new String[3];
		userProfile[0] = this.lastName;
		userProfile[1] = this.firstName;
		userProfile[2] = this.email;
		return userProfile;
	}

	@Override
	public void setProfile(String lastName,
			String firstName,
			String email,
			String password,
			int affiliationId) {

		// set partial user profile
		this.userId = this.fetchNextUserId();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.pswd = password;
		this.affiliationId = affiliationId;
	}

	@Override
	public void resetPassword(String email) {

	}

	@Override
	public int login(String _email, String password) {
      int returnValue = -1;
		try {
         ArrayList<ArrayList<String>> tempList = new ArrayList<ArrayList<String>>();
			this.fetch(_email);
         
         tempList = new ArrayList<ArrayList<String>>();
         String query = "SELECT SHA1(?) FROM Users LIMIT 1";
         ArrayList<String> params = new ArrayList<String>();
         params.add(password);
         db.connect();
         tempList = (ArrayList)db.getData(query, params);
         db.close();
          
         String hashedPswd = tempList.get(0).get(0);
// 			System.out.println("pswd = " + pswd);
//          System.out.println("hp = " + hashedPswd);
         if(pswd.equals(hashedPswd)) {
			   int token = this.isAdmin;
			   if(token == 1) {
               System.out.println("Login Successful and User is Admin.");
			   	returnValue = 1;
			   }
			   else {
               System.out.println("Login Successful and User is not an Admin.");
			   	returnValue = 0;
			   }
			}
			else {
            System.out.println("Login unsuccessful");
			  	returnValue = -1;
			}
		}
		catch(Exception e) {
			try {
            returnValue = -1;
				String msg = "NoSuchAlgorithmException in Users.login()";
				new DLException(e, msg);
			}
			catch(Exception ex) {}
		}
      
      return returnValue;
   }


	// Fetch method
   public void fetch(String _email) {
      MySQLDatabase mysqld = new MySQLDatabase();
      String sql = "SELECT userId, lastName, firstName, email, pswd, canReview, expiration, isAdmin, affiliationId FROM users WHERE email = ?;";
		List<String> arg= new ArrayList<String>();
		arg.add(_email);
      mysqld.connect();
    	ArrayList<String> result = (ArrayList)mysqld.getData(sql, arg).get(0);
      mysqld.close();
      try {
				this.userId = Integer.parseInt(result.get(0));
				this.lastName = result.get(1);
				this.firstName = result.get(2);
				this.email = result.get(3);
				this.pswd = result.get(4);
				this.canReview = result.get(5);
				this.expiration = result.get(6);
				this.isAdmin = Integer.parseInt(result.get(7));
				this.affiliationId = Integer.parseInt(result.get(8));
      }
      catch(IndexOutOfBoundsException ioobe) {
         try {
            String msg = "IndexOutOfBoundsException in Users.fetch()";
            new DLException(ioobe, msg, "SQL - " + sql);
         }
         catch(Exception ex) {}
      }
   }

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
}
