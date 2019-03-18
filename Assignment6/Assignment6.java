/*
 * Assignment 6
 * This program will read the vmg file(contact record)
 * and printing out the information while saving to NSA.txt
 * @author Po-I Wu
 * @version 20170228
 */

import java.util.*;
import java.io.*;

/*
 * In main, it reads the fileList.txt to get the names of vmg files
 * and open each vmg file, if it exists, gets the information inside the vmg file and records into NSA.txt
 * if not, records 'not found' into NSA.txt
 * @param none
 * @return none
 */
public class Assignment6 {
   public static void main(String[] args) 
   		throws FileNotFoundException {
      Scanner file = new Scanner(new File("fileList.txt"));
      PrintStream output = new PrintStream(new File("NSA.txt"));
      while(file.hasNextLine()){
         String list = file.nextLine();
         File content = new File(list);
         if (content.canRead()) {
            getInformation(content, output, list);
         } else {
            System.out.println("File: " + list + " no found");
            output.println("File: " + list + " no found");
            System.out.println();
            output.println();
         }
      }
   }
   
   /*
    * In this method, it will get the information of name1, name2, number1, number2, date, time, and message
    * Using the string line by line, and substring to get the words what we want
    * and format the information, print in console and NSA.txt
    * @param File content, PrintStream output, String list
    * @return none
    */
   public static void getInformation(File content, PrintStream output, String list)
   		throws FileNotFoundException{
      Scanner vmg = new Scanner(content);
      String line = ""; 
      for(int i = 0; i <= 4; i++){
         line = vmg.nextLine();
      }   
      String name1 = line.substring(line.indexOf(":")+1, line.length());
      line = vmg.nextLine();
      String name2;
      // cannot use while (vmg.hasNextLine()) as it will keep on reading the file
      if (line.charAt(0) != 'F'){
         name2 = "unknown";
      } else {
         name2 = line.substring(line.indexOf(":")+1, line.length());
         line = vmg.nextLine();
      }
      String num1 = line.substring(line.indexOf(":")+1, line.length());
      num1 = num1.substring(0, 3) + "-" + num1.substring(3, 6) + "-" + num1.substring(6);
      line = vmg.nextLine();
      String num2;
      if (line.charAt(0) != 'T'){
         num2 = "unknown";
      } else {
         num2 = line.substring(line.indexOf(":") + 1, line.length());
         num2 = num2.substring(0, 3) + "-" + num2.substring(3, 6) + "-" + num2.substring(6);
         line = vmg.nextLine();
      }
      String date = line.substring(4, 8) + "/" + line.substring(9, 11) + "/" + line.substring(12, 14);
      String time = line.substring(16, 23);
      for(int i = 0; i <= 7; i++){
         line = vmg.nextLine();
      }
      String message;
      if (line.charAt(0) != 'T' && line.charAt(1) != 'E' && line.charAt(2) != 'X' && line.charAt(3) != 'T') {
         message = "unknown";
      } else {
         message = line.substring(line.indexOf(":")); 
         line = vmg.nextLine();
      }
   /* 
    * Display and write into NSA.txt
    */
      System.out.println(content.getAbsolutePath());
      output.println(content.getAbsolutePath());
      System.out.println();
      output.println();
      System.out.println(list);
      output.println(list);
      System.out.println("Caller Name 1: " + name1);
      output.println("Caller Name 1: " + name1);
      System.out.println("Caller Name 2: " + name2);
      output.println("Caller Name 2: " + name2);
      System.out.println("Caller Number 1: " + num1);
      output.println("Caller Number 1: " + num1);
      System.out.println("Caller Number 2: " + num2);
      output.println("Caller Number 2: " + num2);
      System.out.println("Date: " + date);
      output.println("Date:" + date);
      System.out.println("Time: "+ time);
      output.println("Time: " + time);
      System.out.println("Message: " + message);
      output.println("Message: " + message);
      System.out.println();
      output.println();
   }
}
