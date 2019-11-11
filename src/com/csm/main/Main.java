package com.csm.main;

import java.util.Calendar;
import java.util.Date;

import com.csm.dao.Affiliation;
import com.csm.dao.PaperAuthor;
import com.csm.dao.User;

public class Main {
	
	public static void main (String[] args) {
		
		Date expirationDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(expirationDate);
		cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + 6));
		expirationDate = cal.getTime();
		
		Affiliation affiliation = new Affiliation("School of Visual Arts");
		
		int affiliationId = affiliation.fetchNextAffiliationId();
		
		User user = new PaperAuthor("LastName", 
				"FirstName", 
				"asdf@asdf.com", 
				"password", 
				expirationDate.toString(), 
				0,
				affiliationId,
				"true");

		System.out.println("userId: " + user.getUserId());
		
//		int userSaved = user.post();
		
//		System.out.println("User saved: " + userSaved);
	}
}
