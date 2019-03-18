/*
* Assignment 5
* This program ask user to enter the number over 1.0
* And it will calculate the square root for the user
* @author Po-I Wu
* @version 20170214
*/

import java.util.*;

/*
* main method call the method
*/
public class SquareRoot {
   public static void main(String[] args){
      boolean bool = true;
      while(bool){
      double number = input();
      double answer = calculation(number);
      output(number, answer);
      System.out.println();
      }
   }
   
   /*
   * user enter a number grater than 1.0
   * enter -999.9 will exit the program
   * enter negative number will run loop until the user enter the positive number
   * @param none
   * @return number(user entered)
   */
   public static double input(){
      Scanner enter = new Scanner(System.in);
      double number = 1;
      while(number != -999.9){
         System.out.print("Please select a floating point number greater than 1.0 => ");
         number = enter.nextDouble();
         while(number < 1.0 && number != -999.9) {
         System.out.print("Please select a floating point number greater than 1.0 => ");
         number = enter.nextDouble();
         }
         if(number > 1.0){
            return number;
         }
         if(number == -999.9) {
            System.out.println("Program exiting.");
         java.lang.System.exit(0);
         }
      }
      return number;
   }
   
   /*
   * calculate the number's square root
   * run loop keep trying until the answer is close within four decimal places
   * @param number
   * @return 0
   */
   public static double calculation(double number){
      double low = 1.0;
      double high = number;
      double attempt;
      boolean bool = false;
      while(!bool){
         attempt = (high + low)/2;
         if((attempt * attempt - number) < 0.0001 && (attempt * attempt - number) > -0.0001)
         {
            bool=true;
            return attempt;
         }
         else if(attempt * attempt > number) {
            high = attempt;
         }
         else if(attempt * attempt < number) {
            low = attempt;
         }
      }
      return 0;
   }
   
   /*
   * display the number and the answer
   * @param number, answer
   * @return none
   */
   public static void output(double number, double answer){
      
      System.out.printf("Results input ==> %.4f square root ==> %.4f", number, answer);
   }
}