package com.csm.utility;

import java.util.Random;

public class Utilities {

	public static int generateId(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
