package com.csm.dao;

public abstract class Subject {
	
	protected int subjectId;
	protected String subjectName;
	
	public Subject(int subjectId, String subjectName) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
