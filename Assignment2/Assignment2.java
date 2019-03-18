/**
* This class creates a chart of heart rate for different patients to check during a workout
* @author Po-I Wu
* @version 20170125.1
*/

public class Assignment2 {
   public static final int LOWER_RHR = 60;
   public static final int HIGHER_RHR = LOWER_RHR + 18;
   public static final int LOWER_AGE = 55;
   public static final int HIGHER_AGE = LOWER_AGE + 20;
   public static final int PR = 80;  //PR is percentage rate expressed as an integer
   
   /**
   * This method prints the resting heart rate from lower to higher(incremented by 2) 10 terms
   * prints the age from lower to higher (lower+20)
   * and calculates the maximum heart rate for each corresponding resting heart rate and age
   */
   public static void main(String[] args) {
      System.out.println("\t\t\t\t\t" + (int)PR + " Percent Maximum Rate");
      System.out.print("Resting Heart Rate");
      for (int i = LOWER_RHR; i <= HIGHER_RHR; i = i + 2) {
         System.out.print("\t" + i);
      }
      System.out.println();
      System.out.println("Maximum Heart Rates");
      seperationLine();
      for (int i = LOWER_AGE; i <= HIGHER_AGE; i++) {
         System.out.print("Age "+ i +"\t\t");
         for (int j = LOWER_RHR; j <= HIGHER_RHR; j = j + 2) {
            double mhr = j + (((220.0 - i - j) / 100) * PR);  //the formula MR=RHR+(((220-AGE-RHR)/100)*PR
            System.out.print("\t" + (int) mhr);
         }
         System.out.println();
      }
      seperationLine();
   }
   
   // draw a seperation line
   public static void seperationLine() {
      System.out.print("+");
      for (int i = 1; i <= 100; i++) {
         System.out.print("-");
      }
      System.out.println("+");
   }
 }      