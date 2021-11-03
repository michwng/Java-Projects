/**
 * --------------------------------------------------------------------------
 * File name: FatGramCalc.java
 * Project name: Homework06
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 9/30/2020
 * --------------------------------------------------------------------------
 */
import java.util.Scanner;
public class FatGramCalc 
{
    public static void main(String[] args) 
    {
        //Declares 4 variables and initializes a scanner.
        int totalCalories;
        int fat;
        int caloriesFromFat;
        double percentFatCalories;
        Scanner scan = new Scanner(System.in);

        //Asks the user for the amount of calories and fat from a food item.
        System.out.println("Please enter the number of Calories and Fat in a food item.");
        System.out.print("Calories: ");
        totalCalories = scan.nextInt();

        System.out.print("Fat (in grams): ");
        fat = scan.nextInt();

        caloriesFromFat = fat * 9;
        percentFatCalories = (1.0 * caloriesFromFat/totalCalories)*100;
        
        //Checks if the calories from fat is greater than the total calories. Prints an error message if so.
        if(caloriesFromFat > totalCalories)
        {
            System.out.println("\nError: The amount of calories from fat cannot exceed the total calories!");
            System.out.println("For reference, 1 gram of Fat is equal to 9 calories!\n");
        }
        else
        {
            System.out.println("\nThe amount of calories from fat is: " + caloriesFromFat);
            System.out.println("Therefore, the percent of calories that come from fat is: " + percentFatCalories + "%.");
            //Checks if the percent of fact calories is less than 30% of the food's total calories.
            if(percentFatCalories < 30)
            {
                System.out.println("Hooray! This food is low fat!\n");
            }
        }
        
        //Closes the scanner scan.
        scan.close();
    }    
}
