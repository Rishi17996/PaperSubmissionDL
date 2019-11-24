package com.csm.database;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class DLException extends Exception {

	private Exception exception = null;
	ArrayList<String> messages = new ArrayList<String>();
	private final String FILENAME = "log.txt";

	public DLException(Exception ex) {
		this.exception = ex;
		writeLog();
	}

	public DLException(Exception ex, String... params) {
		this.exception = ex;
		for (String msg : params) {
			messages.add(msg);
		}
		writeLog();
	}

	/**
	 * Method: writeLog 
	 * Description: this method is used to write the
	 * error/exception log to a file.
	 */
	public void writeLog() {
		LocalDateTime now = LocalDateTime.now();
		try {
			File logFile = new File(FILENAME);
			FileWriter fw = new FileWriter(logFile, true); // true for appending
			fw.write("\n");
			fw.write("\n");
			fw.write(String.valueOf(now));
			fw.write("\n");
			if (!messages.isEmpty()) {
				for (String msg : messages) {
					fw.write(msg);
					fw.write("\n");
				}
			}
			fw.write(exception.getMessage());
			fw.write("\n");
			fw.write("Line: " + exception.getStackTrace()[exception.getStackTrace().length - 2].getLineNumber());
			fw.write("\n");
			fw.write("Class: " + exception.getStackTrace()[exception.getStackTrace().length - 2].getClassName());
			fw.write("\n");
			fw.write("\n");
			fw.flush(); // flush the filewriter buffer
			fw.close(); // close the filewriter
		} 
		catch (IOException ioe) {} 
		catch (NullPointerException npe) {}
	}
}