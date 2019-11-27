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

		// hash password
// 		String userPassword = "password";
//       String securePassword = Utilities.getSHA1Password(userPassword);

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

		// post paper passing subject id
		int paperSaved = paper.post(25);
		System.out.println("paper saved: " + paperSaved);

		int paperAuthorSaved = user.postPaperAuthor(paper.getPaperId());
		System.out.println("paper author saved: " + paperAuthorSaved);


		// hash profile password
		String profilePassword = "password";
      String secureProfilePassword = Utilities.getSHA1Password(profilePassword);

		// create partial user profile
		User profileUser = new PaperAuthor();
		profileUser.setProfile("Werner", "Edward", "asdf@asdf.com", secureProfilePassword, 273);
		int profileSaved = profileUser.post();
		System.out.println("profile saved: " + profileSaved);

		// test password encryption
		String hashedPasswordTest = "hashedPassword";
      String secureHashedPassword = Utilities.getSHA1Password(hashedPasswordTest);
      System.out.println("hashed password: " + secureHashedPassword);

      // get paper with paperId = 21
      System.out.println("getPaper: ");
      paper.getPaper(21);

      // get papers for user 558
      System.out.println("getPapers: ");
      paper.getPapers(558);
      
      user.login("asdf@asdf.com", "password");
      
      System.out.println("setPaper: ");
      int existingPaperId = 123;
      int newPaperId = 200;
      String title = "Test title";
      String submissionAbstract = "Test abstract";
      int submissionType = 2;
      String fileName = "Test fileName";
      String[] subjects = {"Test Subject 1"};
      String[] coauthors_fn = {"Test_1_fn1", "Test_2_fn2"};
      String[] coauthors_ln = {"Test_1_ln1", "Test_2_ln2"};
      
      paper.setPaper(newPaperId,
         title,
         submissionAbstract,
         submissionType,
         fileName,
         subjects,
         coauthors_fn,
         coauthors_ln);
	}
}
