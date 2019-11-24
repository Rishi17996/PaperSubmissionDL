package com.csm.database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
// public class DLException extends Exception {
// 
// 	public DLException(Exception e) {
// 		super(e);
// 	}
// 
// 	public DLException(Exception e, String... values) {
// 		super(e);
// 		try {
// 			writeLog(e, values);
// 			System.out.println("ERROR: " + e);
// 		} catch (IOException e1) {
// 			System.out.println("There was an error completing an operation.");
// 		}
// 	}
// 
// 	// write errors to log file by
// 	// parsing exception and string 
// 	// values
// 	public void writeLog(Exception e, String... values) throws IOException {
// 		BufferedWriter writer = new BufferedWriter(new FileWriter("ErrorLog.txt", true));
// 		try {
// 			// create new simple date format
// 			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' hh:mm:ss a z");
// 			Date date = new Date(System.currentTimeMillis());
// 			writer.append("DateTime: " + formatter.format(date));
// 
// 			// create stacktrace element array
// 			StackTraceElement[] stackTrace = e.getStackTrace();
// 			int count = 0;
// 
// 			for (int i = 0; i < stackTrace.length; i++) {
// 				if (stackTrace[i].getClassName().contains("com.main") && count == 0) {
// 					writer.append("\n");
// 					writer.append("Class name: " + stackTrace[i].getClassName());
// 					writer.append("\n");
// 					writer.append("Line number: " + stackTrace[i].getLineNumber());
// 					writer.append("\n");
// 					writer.append("Error message: " + e.getMessage());
// 					writer.append("\n\n");
// 					count++;
// 				}
// 			}
// 		} catch (IOException e2) {
// 			System.out.println("There was an error completing an operation.");
// 		} finally {
// 			writer.close();
// 		}
// 	}
// }






public class DLException extends Exception {
   
   private Exception exception = null;
   ArrayList<String> messages = new ArrayList<String>();
   private final String FILENAME = "log.txt";
   
   public DLException(Exception ex){
      this.exception = ex;
      writeLog();
   }
   
   
   public DLException(Exception ex, String... params){
      this.exception = ex;
      for(String msg: params) {
         messages.add(msg);
      }
      writeLog();
   }
   
   
   /**
     * Method:       writeLog
     * Description:  this method is used to write the error/exception
     *               log to a file.             
     */
   public void writeLog() {
      LocalDateTime now = LocalDateTime.now();
      try {
         File logFile = new File(FILENAME);
         FileWriter fw = new FileWriter(logFile, true);  // true for appending
         fw.write("\n");
         fw.write("\n");
         fw.write(String.valueOf(now));
         fw.write("\n");
         if(!messages.isEmpty()) {
            for(String msg: messages) {
               fw.write(msg);
               fw.write("\n");
            }
         }
         fw.write(exception.getMessage());
         fw.write("\n");
         fw.write("Line: " + exception.getStackTrace()[
            exception.getStackTrace().length -2].getLineNumber());
         fw.write("\n");
         fw.write("Class: " + exception.getStackTrace()[
            exception.getStackTrace().length -2].getClassName());
         fw.write("\n");
         fw.write("\n");
         fw.flush();    // flush the filewriter buffer
         fw.close();    // close the filewriter
      }
      catch (IOException ioe) {}
      catch (NullPointerException npe) {}
   }
   
}