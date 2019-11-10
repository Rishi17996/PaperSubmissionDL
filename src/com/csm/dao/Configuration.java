package com.csm.dao;

import java.sql.Date;

import com.csm.database.MySQLDatabase;

public class Configuration {
	
	private Date submissionOpen;
	private Date submissionClose;
	private Date reviewOpen;
	private Date reviewClose;
	private Date reviewerOpen;
	private Date reviewerClose;
	private Date fileUploadClose;
	private String PCEmail;
	private String PCName;
	private String PC2Email;
	private String PC2Name;
	private String shortName;
	private String logoFile;
	private String conferenceLocation;
	private String conferenceHost;
	private String conferenceHotel;
	private String conferenceURL;
	private String registrationURL;
	private String authorRegistrationClose;
	private String conferenceDates;
	private MySQLDatabase db;
	
	
	public Configuration() {
		db = new MySQLDatabase();
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


	public Date getFileUploadClose() {
		return fileUploadClose;
	}


	public void setFileUploadClose(Date fileUploadClose) {
		this.fileUploadClose = fileUploadClose;
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


	public String getAuthorRegistrationClose() {
		return authorRegistrationClose;
	}


	public void setAuthorRegistrationClose(String authorRegistrationClose) {
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
