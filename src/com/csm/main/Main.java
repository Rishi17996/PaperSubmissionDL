package com.csm.main;

import java.util.Calendar;
import java.util.Date;

import com.csm.dao.Affiliation;
import com.csm.dao.PaperAuthor;
import com.csm.dao.User;

public class Main {
	
	public static void main (String[] args) {
		
		Affiliation affiliation = new Affiliation("School of Visual Arts");
		
		int affiliationId = affiliation.fetchNextAffiliationId();
		int affSaved = affiliation.post();
		System.out.println("affSaved: " + affSaved);
		
//		User user = new PaperAuthor("LastName",
//				"FirstName", 
//				"asdf@asdf.com", 
//				"password", 
//				"20250101000000", 
//				0,
//				133,
//				"1");
//		
//		
//		int userSaved = user.post();
//		System.out.println("userSaved: " + userSaved);
	}
}
