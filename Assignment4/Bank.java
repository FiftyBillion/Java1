/**
* Assignment 4
* This is a ATM program
* The user choose an option to do
* It will run loop until the user enter 'x' to exit
* @author Po-I Wu
* @version 20170214
*/

import java.util.*;
import java.awt.*;



public class Bank {
   
   /*
   * main method will run loop to ask options until the user type 'x'
   * the variable set in main method: inital, currentB, maxB, minB
   * if the user type 'd', 'w', 'b','r' it will run each corresponded method
   */
   public static void main (String[] args) {
      double initial = 1000.00;
      double currentB = 1000.00;
      double maxB = 1000.00;
      double minB = 1000.00;
      Scanner input = new Scanner(System.in);
      System.out.println("Welcome to First Interstellar Bank");
      System.out.println(); 
      
      
      boolean bool = false; //run loop
      while(!(bool)){
         bankMenu();
         char a = Character.toLowerCase(input.next().charAt(0));
         char b = a;
         if(b=='d'){
            currentB = deposit(currentB);
            if (currentB > maxB)
               maxB = currentB;  //replace the max
         }
         else if(b=='w'){
            currentB = withdraw(currentB);
            if (currentB < minB)
               minB = currentB;  //replace the min
         }else if(b=='b'){
            balance(currentB);
         }else if(b=='r'){
            report(initial, currentB, maxB, minB);
         }else if(b=='x'){
            bool = true; //to end loop
         }else{
            System.out.print("Invalid input \""+ a +"\" please try again");
         }
      }
   }
   
   /*
   * user enter a number for deposit, should be positive, else run loop for asking
   * after user deposit, print the information
   * @param currentB operand 1
   * @return currentB after user deposit
   */
   public static double deposit(double currentB) {
      Scanner deposit = new Scanner(System.in);
      System.out.printf("Please enter your deposit amount => $");
      double d = deposit.nextDouble();
      while(d < 0){  //if user enter a negative number
         System.out.print("Invalid input entered. Please try again: $");
         d = deposit.nextDouble();
      }
      System.out.printf("Beginning Balance: $ %8.2f\n", currentB);
      System.out.printf("Transaction:       $ %8.2f", d);
      System.out.println("  (+)");
      System.out.printf("Ending Balance:    $ %8.2f\n\n", currentB + d);
      currentB = currentB + d;
      return currentB;
   }
   
   /*
   * user enter a number for withdraw, should be positive, else run loop for asking
   * after user deposit, print the information
   * @param currentB operand 1
   * @return currentB after user withdraw
   */
   public static double withdraw(double currentB) {
      Scanner withdraw = new Scanner(System.in);
      System.out.printf("Please enter your withdrawal amount => $");
      double w = withdraw.nextDouble();
      while(w <0){  //if user enter a negative number
         System.out.print("Invalid input entered. Please try again: $");
         w = withdraw.nextDouble();
      }
      System.out.printf("Beginning Balance: $ %8.2f\n", currentB);
      System.out.printf("Transaction:       $ %8.2f", w);
      System.out.println("  (-)");
      System.out.printf("Ending Balance:    $ %8.2f\n\n", currentB - w);
      currentB = currentB - w;
      return currentB;
   }
   
   /*
   * print the current balance
   * @param currentB
   * @return none
   */
   public static void balance(double currentB) {
      System.out.printf("Current Balance: $ %8.2f\n\n", currentB);
   }
   
   /*
   * print all the information for the user
   * @param initial, currentB , maxB, minB
   * @return none
   */
   public static void report(double initial, double currentB, double maxB, double minB) {
      System.out.println("End of Banking Period Report");
      System.out.printf("Beginning Balance: $ %8.2f\n", initial);
      System.out.printf("Maximum Balance:   $ %8.2f\n", maxB);
      System.out.printf("Minimum Balance:   $ %8.2f\n", minB);
      System.out.printf("Current Balance:   $ %8.2f\n", currentB);
   }
   
   /*
   * print the menu
   */
   public static void bankMenu(){
      System.out.println("(D) Deposit");
      System.out.println("(W) Withdraw");
      System.out.println("(B) Balance Enquiry");
      System.out.println("(R) Monthly Report");
      System.out.println("(X) Exit");
      System.out.print("Please select a transaction =>" );
   }
}