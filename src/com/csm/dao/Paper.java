package com.csm.dao;

import java.util.ArrayList;

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
	
	public Paper() {
		
	}
	
	public ArrayList<Paper> getPapers(int userId) {
		ArrayList<Paper> papers = new ArrayList<Paper>();
		return papers;
	}
	
	public Paper getPaper(int paperId) {
		Paper paper = new Paper();
		return paper;
	}
	
	public void setPaper(int paperId, 
			String submissionTitle, 
			String submissionAbstract, 
			int submissionType,
			String filename,
			String[] subjects,
			String[] coauthorsfirstnames,
			String[] coauthorslastnames) {
		
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
}
