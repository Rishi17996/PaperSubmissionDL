package com.csm.utility;

import java.security.SecureRandom;
import java.util.Random;

public class Utilities {

	public final static String RANDOM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static SecureRandom secureRnd = new SecureRandom();
	
	public static int generateId(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static String randomString(int length) {
	    StringBuilder sb = new StringBuilder(length);
	    for (int i = 0; i < length; i++) {
	    	sb.append(RANDOM.charAt(secureRnd.nextInt(RANDOM.length())));
	    }
	    return sb.toString();
	  }
}
