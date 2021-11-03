/**
 * --------------------------------------------------------------------------
 * File name: FatGramCalc.java
 * Project name: Homework07Bonus
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 10/20/2020
 * --------------------------------------------------------------------------
 */
import javax.swing.JOptionPane;

/**
 * Calculates the amount of fat and fat calories
 * in a food item by asking the user for the
 * amount of calories and fat.
 * 
 * Date created: October 20, 2020
 * 
 * @author Michael Ng, ngmw01@etsu.edu
 */
public class FatGramCalc 
{
    /**
     * Method to run the program. Accepts user input for calories
     * and fat and makes calculations based on the input.
     * 
     * Date created: October 20, 2020
     * @param args
     */
    public static void main(String[] args) 
    {
        //Declares 4 variables and initializes a scanner.
        int totalCalories;
        int fat;
        int caloriesFromFat;
        double percentFatCalories;
        //Scanner scan = new Scanner(System.in);

        //Asks the user for the amount of calories and fat from a food item.
        System.out.println("Please enter the number of Calories and Fat in a food item.");
        System.out.print("Calories: ");
        totalCalories = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the amount of Calories in the food item.", "Amount of Calories", JOptionPane.DEFAULT_OPTION));
        fat = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the amount of Fat in the food item.", "Amount of Fat", JOptionPane.DEFAULT_OPTION));

        caloriesFromFat = fat * 9;
        percentFatCalories = (1.0 * caloriesFromFat/totalCalories)*100;
        
        //Checks if the calories from fat is greater than the total calories. Prints an error message if so.
        if(caloriesFromFat > totalCalories)
        {
            JOptionPane.showMessageDialog(null, "Error: The amount of calories from fat\ncannot exceed the total calories!\n\nFor reference, 1 gram of Fat is equal to 9 calories!", "Error", JOptionPane.ERROR_MESSAGE);
            //System.out.println("\nError: The amount of calories from fat cannot exceed the total calories!");
            //System.out.println("For reference, 1 gram of Fat is equal to 9 calories!\n");
        }
        else
        {
            //System.out.println("\nThe amount of calories from fat is: " + caloriesFromFat);
            //System.out.println("Therefore, the percent of calories that come from fat is: " + percentFatCalories + "%.");
            JOptionPane.showMessageDialog(null, "The amount of calories from fat is: " + caloriesFromFat + ".\nTherefore, the percent of calories that come from fat is: " + percentFatCalories + "%.", "Calories from Fat", JOptionPane.INFORMATION_MESSAGE);
            //Checks if the percent of fact calories is less than 30% of the food's total calories.
            if(percentFatCalories < 30)
            {
                //System.out.println("Hooray! This food is low fat!\n");
                JOptionPane.showMessageDialog(null, "Hooray! This food is low fat!", "Hooray!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        //Closes the main method.
        System.exit(0);
    }    
}
