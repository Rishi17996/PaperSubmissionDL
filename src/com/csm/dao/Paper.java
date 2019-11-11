package com.csm.dao;

import java.util.ArrayList;

import com.csm.database.MySQLDatabase;
import com.csm.utility.Utilities;

public class Paper {
	
	private int paperId;
	private String title;
	private String abstr;
	private String track;
	private String status;
	private int submissionType;
	private int submitterId;
	private String field;
	private String tentativeStatus;
	private PaperSubject paperSubject;
	private String submissionTitle;
	private String submissionAbstract;
	private Object fileName;
	private MySQLDatabase db;
	
	public Paper(String title,
			String abstr,
			String track,
			String status,
			int submissionType,
			int submitterId,
			String field,
			String tentativeStatus,
			PaperSubject paperSubject,
			String submissionTitle,
			String submissionAbstract,
			String fileName) {
		
		// create new database instance
		db = new MySQLDatabase();
		
		// set paper attributes
		this.paperId = Utilities.generateId(1, 5000);
		this.title = title;
		this.abstr = abstr;
		this.status = status;
		this.submissionType = submissionType;
		this.submitterId = submitterId;
		this.field = field;
		this.tentativeStatus = tentativeStatus;
		this.paperSubject = paperSubject;
		this.submissionTitle = submissionTitle;
		this.submissionAbstract = submissionAbstract;
		this.fileName = fileName;
	}
	
	public ArrayList<Paper> getPapers(int userId) {
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

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
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

	public PaperSubject getPaperSubject() {
		return paperSubject;
	}

	public void setPaperSubject(PaperSubject paperSubject) {
		this.paperSubject = paperSubject;
	}

	public String getSubmissionTitle() {
		return submissionTitle;
	}

	public void setSubmissionTitle(String submissionTitle) {
		this.submissionTitle = submissionTitle;
	}

	public String getSubmissionAbstract() {
		return submissionAbstract;
	}

	public void setSubmissionAbstract(String submissionAbstract) {
		this.submissionAbstract = submissionAbstract;
	}

	public Object getFileName() {
		return fileName;
	}

	public void setFileName(Object fileName) {
		this.fileName = fileName;
	}
}
