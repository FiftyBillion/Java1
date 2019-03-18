/**
* Homework Assignment 3
* This class has three same method names and demonstrate overloaded method.
* User will enter the number, and the method will do the calculation.
* @author Po-I Wu
* @version 20170201
*/

import java.util.*;
public class Roots{

   /**
   * This main method ask user input a number
   * and it will run the first root method.
   * @param none
   */
   public static void main(String[] args){
      Scanner object = new Scanner(System.in);
      System.out.println("Enter a single integer value =>");
      int input1 = object.nextInt();  
      
      roots(input1);
   }
   
   /**
   * The first root method will run loop as many times as the number input in the main method
   * This method ask user to enter two numbers
   * then run the second root method, print my name
   * and print the variable c which return in second root method
   * @param input1 operand
   * @return none
   */
   public static void roots(int input1){
      for (int i = 0 ; i < input1; i++){
         Scanner object = new Scanner(System.in);
         System.out.println(" Please enter two interger values =>"); 
         int input2 = object.nextInt();
         int input3 = object.nextInt();
         double type = roots(input2, input3);
         System.out.println(" Po-I Wu");
         System.out.println(type);
      }
   }
   
   /**
   * The second root method will calculate the number which user input in the first method
   * and run the third root method then print the value which return in the third method
   * @param a operand 1, b operand 2
   * @return double c, which is calculated by a and b
   */
   public static double roots(int a, int b){
      double ax = a*1.0;
      double bx = b*1.0;
      double c = Math.pow(ax*ax + bx*bx, 0.5);
      double value = roots(ax, bx);
      System.out.println(value);
      return c;
   }
   
   /**
   * The third method will calculate the first parameter and print it
   * Then calculate both parameter and return to the answer
   * @param x operand 1, y operand 2
   * @return double value, which is calculated by x and y
   */
   public static double roots(double x, double y){
      double z = x * 3.0;
      System.out.println(z);
      double value = (x*100)/(y*3);
      return value;
   }
}