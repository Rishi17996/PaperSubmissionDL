package com.csm.dao;

import javax.mail.internet.*;
import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;  
import java.util.Calendar;
import javax.mail.*;
import javax.activation.*;

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
     * Method:       fetchNextUserId
     * Description:  this method is used to fetch userID  
     * @return       the returned value is a record count (int)
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
     * Method:       postPaperSubject
     * Description:  this method is used post new records to database  
     * @return       the returned value is a postDataResult (int)
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
     * Method:       post
     * Description:  this method is used post insert new records to database  
     * @return       the returned value is a postDataResult (int)
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
     * Method:       postPaperAuthor
     * Description:  this method is used Post new paperauthor record to database  
     * @return       the returned value is a postDataResult (int)
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


   /**
     * Method:       getProfile
     * Description:  returns the attributes of instanciated users.  
     * @return       String array consisting of user attributes
     */ 
	public String[] getProfile() {
		String[] userProfile = new String[3];
		userProfile[0] = this.lastName;
		userProfile[1] = this.firstName;
		userProfile[2] = this.email;
		return userProfile;
	}
   
   /**
     * Method:       setProfile
     * Description:  sets the profile of a user. If new user, creates new ID.
     *               If existing, updates it.
     */
	@Override
	public void setProfile(String lastName,
			String firstName,
			String email,
			String password,
			int affiliationId) {

		// set partial user profile
      ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
      int uId = 0;
      try {
         String checkUser = "SELECT COUNT(userId) FROM Users WHERE lastName = ? AND firstName = ? AND email = ?;";
         ArrayList<String> params = new ArrayList<String>();
         params.add(lastName);
         params.add(firstName);
         params.add(email);
         db.connect();
         result = db.getData(checkUser, params);
         int recordCount = Integer.parseInt((String) result.get(0).get(0));
         
         if(recordCount > 0) {
            String getU = "SELECT userId FROM Users WHERE lastName = ? AND firstName = ? AND email = ?;";
            params = new ArrayList<String>();
            params.add(lastName);
            params.add(firstName);
            params.add(email);
            result = db.getData(checkUser, params);
            uId = Integer.parseInt((String) result.get(0).get(0));
         }
         else {
            uId = this.fetchNextUserId();
         }
      }
      catch(Exception ex) {
         new DLException(ex);
      }
      
		this.userId = uId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.pswd = password;
		this.affiliationId = affiliationId;
	}
   
   
   /**
     * Method:       login
     * Description:  this method is used to perform login operation
     * @param        _email (email ID of the user)
                     password (password of the user)
     * @return       Returns token i.e 1= Successful login as Admin, 0 = Successful login but not admin and 
                     -1 = Not a successful login.
     */
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
   			new DLException(e);
			}
			catch(Exception ex) {}
		}
      
      return returnValue;
   }


	/**
     * Method:       fetch
     * Description:  this method is used to fetch other attributes of the logged in User
     * @param        _email (email ID of the user).
     */
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
            new DLException(ioobe);
         }
         catch(Exception ex) {}
      }
   }
   
   /**
     * Method:       resetPassword
     * Description:  this method is used to reset password of the user
     * @param        _email (email ID of the user).
     */
   public void resetPassword(String _email){
      boolean resetPwd = false;
      try{
         db.connect();
            ArrayList<ArrayList<String>> reslt = new ArrayList<ArrayList<String>>();
            ArrayList<String> arg = new ArrayList<String>();
            arg.add(_email);
            String query = "SELECT email, expiration FROM Users WHERE email = ?";
            reslt = db.getData(query, arg);
            Timestamp timestamp = new Timestamp(new Date().getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp.getTime());
            calendar.add(Calendar.MINUTE,5);
            calendar.clear(Calendar.MILLISECOND);
            timestamp = new Timestamp(calendar.getTime().getTime());
            String expiration = DateToString(timestamp);
            arg.clear();
            arg.add(expiration);
            arg.add(_email);
            this.sendEmail(_email);         
            String query2 = "UPDATE Users SET expiration = ? WHERE email = ?";
            int reset = db.setData(query2, arg);
            if(reset == 1){
               resetPwd = true;
            }
            db.close();
      }
      catch(Exception e){
         new DLException(e);
      }
   }
   
   @Override
	public int submitPaper() {
		return 0;
	}

	@Override
	public ArrayList<Paper> getPapers() {
		return null;
	}
   
   
   /**
     * Method:       sendEmail
     * Description:  this method is used to send email to user to reset the password
     * @param        _email (email ID of the user).
     */
   public void sendEmail(String _email) {
      final String username = "dcaproject123@gmail.com";
      final String password = "Dca_project123";
      String temp="";
      Properties prop = new Properties();
      prop.put("mail.smtp.host", "smtp.gmail.com");
      prop.put("mail.smtp.port", "587");
      prop.put("mail.smtp.auth", "true");
      prop.put("mail.smtp.starttls.enable", "true");
      try {
    	   Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });
         Random rand = new Random();
         temp= String.valueOf(rand.nextInt(100)); 
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress("conference.reset@gmail.com"));
         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(_email));
         message.setSubject("Password reset");
         message.setText("Your Temporary Password is: " + temp);
         Transport.send(message);
      } 
      catch (Exception e) {
         new DLException(e);
      }
   }
   
   
   /**
    * Method:       DateToString
	 * Description : returns Dates in String format 
    * @param       Date date
	 * @return      Dateformat in String
	 */
   public String DateToString(Date date) {  
        
      Date dt = Calendar.getInstance().getTime();  
      DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");  
      String strDate = dateFormat.format(dt);  
      System.out.println("Converted String: " + strDate);  
      return dateFormat.format(dt);
         
   }
   
   /**
     * Method:       setPassword
     * Description:  this method is used to set password of the user
     * @param        _email (email ID of the user).
                     _password (password of the user).
     */
   public void setPassword(String _email, String _password) {
      try {
         db.connect();
         this.email = _email;
         ArrayList<String> args = new ArrayList<String>();
         args.add(_email);
         ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
         String query ="SELECT email, expiration FROM Users where email=?";
         result = db.getData(query, args);
         if (!result.isEmpty()) {
            String expiration = result.get(0).get(1);
            Timestamp currentTime = new Timestamp(new Date().getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
            Timestamp dbTime = null;
            if(expiration != null) {
               dbTime = new java.sql.Timestamp((dateFormat.parse(expiration)).getTime());
            }
            else {
               dbTime = currentTime;
            }
            if (dbTime.equals(currentTime) || dbTime.after(currentTime)) {
               Timestamp newTime = new Timestamp(new Date().getTime());
               Calendar cal = Calendar.getInstance();
               Calendar cal1 = Calendar.getInstance();
               cal1.setTimeInMillis(newTime.getTime());
               cal1.clear(Calendar.MILLISECOND);
               cal.setTimeInMillis(newTime.getTime());
               cal.add(Calendar.YEAR, 5);
               cal.clear(Calendar.MILLISECOND);
               newTime = new Timestamp(cal.getTime().getTime());
               String newExpiration = DateToString(newTime);
               args.clear();
               args.add(_password);
               args.add(newExpiration);
               args.add(email);
               String query2 = "Update users set pswd=SHA(?), expiration=? where email=?";
               int rc = db.setData(query2, args);
            }
         }
         db.close();
      }
      catch (Exception e) {
         new DLException(e);
      }
   }
   
	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
}