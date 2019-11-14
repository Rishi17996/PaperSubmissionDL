package com.csm.main;

import com.csm.dao.Affiliation;
import com.csm.dao.Paper;
import com.csm.dao.PaperAuthor;
import com.csm.dao.User;
import com.csm.utility.Utilities;

public class Main {
	
	public static void main (String[] args) {
		
		// create new affiliation for user
		Affiliation affiliation = new Affiliation("School of Visual Arts");
		
		int affiliationId = affiliation.fetchNextAffiliationId();
		int affSaved = affiliation.post();

		System.out.println("affiliation saved: " + affSaved);
		
		// new user generates next available id
		User user = new PaperAuthor("LastName",
				"FirstName", 
				"asdf@asdf.com", 
				"password", 
				"20250101000000", 
				0,
				affiliationId,
				"1");
		
		// create new user
		int userSaved = user.post();
		System.out.println("user saved: " + userSaved);
		
		// create new paper
		Paper paper = new Paper("Example title", 
				"Example abstract", 
				"Example track", 
				"Accepted", 
				7, 
				765, 
				Utilities.randomString(10),
				"Accepted");
		
		int paperSaved = paper.post();
		System.out.println("paper saved: " + paperSaved);
	}
}
