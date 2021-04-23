package com.csm.dao;

import java.time.LocalDate;
import java.util.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  


import com.csm.database.MySQLDatabase;
import com.csm.database.DLException;

public class Configuration {
	
	private Date submissionOpen;
	private Date submissionClose;
	private Date reviewOpen;
	private Date reviewClose;
	private Date reviewerOpen;
	private Date reviewerClose;
	private Date FinalUploadClose;
	private String PCEmail;
	private String PCName;
	private String PC2Email;
	private String PC2Name;
	private String shortName;
	private String logoFile;
	private String conferenceLocation;
	private String conferenceHost;
   private String conferenceDates;
	private String conferenceHotel;
	private String conferenceURL;
	private String registrationURL;
	private Date authorRegistrationClose;
	
	private MySQLDatabase db;
	
	
	public Configuration() {
		db = new MySQLDatabase();
	}

   /**
    * Method:        put
	 * Description :  Update specified values in Configuration table
    *                <BR>
    * @param       Date submissionOpen,
	                Date submissionClose,
	                Date reviewOpen,
	                Date reviewClose,
               	 Date reviewerOpen,
               	 Date reviewerClose,
               	 Date FinalUploadClose,
               	 String PCEmail,
               	 String PCName,
               	 String PC2Email,
               	 String PC2Name,
               	 String shortName,
               	 String logoFile,
               	 String conferenceLocation,
               	 String conferenceHost,
                   String conferenceDates,
               	 String conferenceHotel,
               	 String conferenceURL,
               	 String registrationURL,
               	 Date authorRegistrationClose
    * 
	 */
   public void put(	
      Date submissionOpen,
	   Date submissionClose,
	   Date reviewOpen,
	   Date reviewClose,
	   Date reviewerOpen,
	   Date reviewerClose,
	   Date FinalUploadClose,
	   String PCEmail,
	   String PCName,
	   String PC2Email,
	   String PC2Name,
	   String shortName,
	   String logoFile,
	   String conferenceLocation,
	   String conferenceHost,
      String conferenceDates,
	   String conferenceHotel,
	   String conferenceURL,
	   String registrationURL,
	   Date authorRegistrationClose)  {
         db.connect();
    
         String q2 = "UPDATE '_configuration' ";
         q2 += "SET submissionOpen=?";
         q2 += ", submissionClose=?";
         q2 += ", reviewOpen=?";
         q2 += ", reviewClose=?";
         q2 +=", reviewerOpen=?";
         q2 +=", reviewerClose=?";
         q2 +=", finalUploadClose=?";
         q2 +=", PCEmail=?";
         q2 +=", PC2Email=?";
         q2 +=", PC2Name=?";
         q2 +=", shortName=?";
         q2 +=", logoFile=?";
         q2 +=", conferenceLocation=?";
         q2 +=", conferenceHost=?";
         q2 +=", conferenceDates=?";
         q2 +=", conferenceHotel=?";
         q2 +=", conferenceURL=?";
         q2 +=", registrationURL=?";
         q2 +=", authorRegistrationClose=?;";
        
         List<String> q = new ArrayList<String>();
         q.add(DateToString(submissionOpen));
         q.add(DateToString(submissionClose));
         q.add(DateToString(reviewOpen));
         q.add(DateToString(reviewClose));
         q.add(DateToString(reviewerOpen));
         q.add(DateToString(reviewerClose));
         q.add(DateToString(FinalUploadClose));
         q.add(PCEmail);
         q.add(PC2Email);
         q.add(PCName);
         q.add(PC2Name);
         q.add(shortName);
         q.add(logoFile);
         q.add(conferenceLocation);
         q.add(conferenceHost);
         q.add(conferenceDates);
         q.add(conferenceHotel);
         q.add(conferenceURL);
         q.add(registrationURL);
         q.add(DateToString(authorRegistrationClose));
         
         int result = db.setData(q2, q);
         db.close();
         System.out.println("Rows affected = " + result);
      }

   /**
    * Method:        post
	 * Description :  Insert specified values in Configuration table
    *                <BR>
    * @param       Date submissionOpen,
	                Date submissionClose,
	                Date reviewOpen,
	                Date reviewClose,
               	 Date reviewerOpen,
               	 Date reviewerClose,
               	 Date FinalUploadClose,
               	 String PCEmail,
               	 String PCName,
               	 String PC2Email,
               	 String PC2Name,
               	 String shortName,
               	 String logoFile,
               	 String conferenceLocation,
               	 String conferenceHost,
                   String conferenceDates,
               	 String conferenceHotel,
               	 String conferenceURL,
               	 String registrationURL,
               	 Date authorRegistrationClose
    *@return       int number of rows affected
    * 
	 */
   public int post(
      Date submissionOpen,
	   Date submissionClose,
	   Date reviewOpen,
	   Date reviewClose,
	   Date reviewerOpen,
	   Date reviewerClose,
	   Date FinalUploadClose,
	   String PCEmail,
	   String PCName,
	   String PC2Email,
	   String PC2Name,
	   String shortName,
	   String logoFile,
	   String conferenceLocation,
	   String conferenceHost,
      String conferenceDates,
	   String conferenceHotel,
	   String conferenceURL,
	   String registrationURL,
	   Date authorRegistrationClose)  {
   
         db.connect();
         String query = "INSERT INTO _configuration (submissionOpen,submissionClose,reviewOpen,reviewClose,reviewerOpen,reviewerClose,finalUploadClose,PCEmail,PC2Email,PCName,PC2Name,shortName,logoFile,conferenceLocation,conferenceHost,conferenceDates,conferenceHotel,conferenceURL,registrationURL,authorRegistrationClose)";
         query += " Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
         List <String> q1 = new ArrayList <String> ();
         q1.add(DateToString(submissionOpen));
         q1.add(DateToString(submissionClose));
         q1.add(DateToString(reviewOpen));
         q1.add(DateToString(reviewClose));
         q1.add(DateToString(reviewerOpen));
         q1.add(DateToString(reviewerClose));
         q1.add(DateToString(FinalUploadClose));
         q1.add(PCEmail);
         q1.add(PC2Email);
         q1.add(PCName);
         q1.add(PC2Name);
         q1.add(shortName);
         q1.add(logoFile);
         q1.add(conferenceLocation);
         q1.add(conferenceHost);
         q1.add(conferenceDates);
         q1.add(conferenceHotel);
         q1.add(conferenceURL);
         q1.add(registrationURL);
         q1.add(DateToString(authorRegistrationClose));
         int result = db.setData(query, q1);
         db.close();
         return result;
   }
   
   /**
    * Method:       DateToString
	 * Description : returns Dates in String format 
	 *               <BR>
    * @param       Date date
	 * @return      Dateformat in String
	 */
   public static String DateToString(Date date){  
            
//       Date dt = Calendar.getInstance().getTime();  
      DateFormat dateFormat = null;
      try {
         dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");  
//         String strDate = dateFormat.format(date);
      }
      catch (Exception ex) {
         new DLException(ex);
      }  
      //String strDate = dateFormat.format(date); 
      //System.out.println("Converted String: " + strDate);  
      return dateFormat.format(date);
             
} 

	public Date getSubmissionOpen() {
		return submissionOpen;
	}


	public void setSubmissionOpen(Date submissionOpen) {
		this.submissionOpen = submissionOpen;
	}


	public Date getSubmissionClose() {
		return submissionClose;
	}


	public void setSubmissionClose(Date submissionClose) {
		this.submissionClose = submissionClose;
	}


	public Date getReviewOpen() {
		return reviewOpen;
	}


	public void setReviewOpen(Date reviewOpen) {
		this.reviewOpen = reviewOpen;
	}


	public Date getReviewClose() {
		return reviewClose;
	}


	public void setReviewClose(Date reviewClose) {
		this.reviewClose = reviewClose;
	}


	public Date getReviewerOpen() {
		return reviewerOpen;
	}


	public void setReviewerOpen(Date reviewerOpen) {
		this.reviewerOpen = reviewerOpen;
	}


	public Date getReviewerClose() {
		return reviewerClose;
	}


	public void setReviewerClose(Date reviewerClose) {
		this.reviewerClose = reviewerClose;
	}


	public Date getFinalUploadClose() {
		return FinalUploadClose;
	}


	public void setFinalUploadClose(Date FinalUploadClose) {
		this.FinalUploadClose = FinalUploadClose;
	}


	public String getPCEmail() {
		return PCEmail;
	}


	public void setPCEmail(String pCEmail) {
		PCEmail = pCEmail;
	}


	public String getPCName() {
		return PCName;
	}


	public void setPCName(String pCName) {
		PCName = pCName;
	}


	public String getPC2Email() {
		return PC2Email;
	}


	public void setPC2Email(String pC2Email) {
		PC2Email = pC2Email;
	}


	public String getPC2Name() {
		return PC2Name;
	}


	public void setPC2Name(String pC2Name) {
		PC2Name = pC2Name;
	}


	public String getShortName() {
		return shortName;
	}


	public void setShortName(String shortName) {
		this.shortName = shortName;
	}


	public String getLogoFile() {
		return logoFile;
	}


	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}


	public String getConferenceLocation() {
		return conferenceLocation;
	}


	public void setConferenceLocation(String conferenceLocation) {
		this.conferenceLocation = conferenceLocation;
	}


	public String getConferenceHost() {
		return conferenceHost;
	}


	public void setConferenceHost(String conferenceHost) {
		this.conferenceHost = conferenceHost;
	}


	public String getConferenceHotel() {
		return conferenceHotel;
	}


	public void setConferenceHotel(String conferenceHotel) {
		this.conferenceHotel = conferenceHotel;
	}


	public String getConferenceURL() {
		return conferenceURL;
	}


	public void setConferenceURL(String conferenceURL) {
		this.conferenceURL = conferenceURL;
	}


	public String getRegistrationURL() {
		return registrationURL;
	}


	public void setRegistrationURL(String registrationURL) {
		this.registrationURL = registrationURL;
	}


	public Date getAuthorRegistrationClose() {
		return authorRegistrationClose;
	}


	public void setAuthorRegistrationClose(Date authorRegistrationClose) {
		this.authorRegistrationClose = authorRegistrationClose;
	}


	public String getConferenceDates() {
		return conferenceDates;
	}


	public void setConferenceDates(String conferenceDates) {
		this.conferenceDates = conferenceDates;
	}


	public MySQLDatabase getDb() {
		return db;
	}


	public void setDb(MySQLDatabase db) {
		this.db = db;
	}
}
