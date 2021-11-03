/**
 * --------------------------------------------------------------------------
 * File name: Calculator.java
 * Project name: Homework07
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 10/20/2020
 * Completion Date: 10/20/2020
 * --------------------------------------------------------------------------
 */
import javax.swing.JOptionPane;

/**
 * Creates a calculator with the ability
 * to add, subtract, multiply, divide,
 * modulus, and square root.
 * 
 * Date created: October 20, 2020
 * 
 * @author Michael Ng, ngmw01@etsu.edu
 */
public class Calculator
{
    /**
     * Method to run the program. Accepts user input on two numbers
     * and an operation and makes calculations based on the input.
     * 
     * Date created: October 20, 2020
     * @param args
     */
    public static void main(String[] args) 
    {
        double numOne = 0.0;
        double numTwo = 0.0;
        String operation;

        System.out.println("Welcome to Calculator! Type 'Exit' in the operation input box to exit.");
        //Calculating History shows in the Terminal so the User can trace their calculation history.
        System.out.println("Calculating History: ");
        do
        {
            operation = JOptionPane.showInputDialog(null, "Please input the operation. (Add, Subtract,\n Multiply, Divide, Modulus, Square Root)", "Operation", JOptionPane.DEFAULT_OPTION);
            //If operation is not the word "Exit"
            if(!operation.equalsIgnoreCase("Exit") && operation.length() > 2)
            {
                numOne = Double.parseDouble(JOptionPane.showInputDialog(null, "Please input the first number.", "First Number", JOptionPane.DEFAULT_OPTION));
                //Nested if statement. If the operation is not a square root, asks the user for a second number.
                if(!operation.equalsIgnoreCase("Square Root"))
                {
                    numTwo = Double.parseDouble(JOptionPane.showInputDialog(null, "Please input the second number.", "Second Number", JOptionPane.DEFAULT_OPTION));
                }
                menu(numOne, numTwo, operation);
            }
            //Empty method used to skip the else statement and end the loop when the operation is "Exit".
            else if(operation.equalsIgnoreCase("Exit"))
            {
                
            }
            //Shows an error message.
            else
            {
                JOptionPane.showMessageDialog(null, "Sorry, this input is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("This is not a valid operation.");
            }

        }while(!operation.equalsIgnoreCase("Exit"));
        System.out.println("You have successfully exited the program. Thank you for using this calculator!");
        System.exit(0);
    }
    /**
     * The method menu appropriately displays the value of
     * an operation based on user input. 
     * 
     * @param numOne
     * @param numTwo
     * @param operation
     */
    public static void menu(double numOne, double numTwo, String operation)
    {
        //result is used to display the final calculation.
        double result = 0.0;
        //valid detmerines whether or not the user's input is valid.
        boolean valid = true;
        switch(operation)
        {
            /**
             * 2 cases of the same word are added
             * to allow user input of both upper and
             * lower case operations.
             */
            case "Add":
                operation = "+";
                result = add(numOne, numTwo);
                break;
            case "add":
                operation = "+";
                result = add(numOne, numTwo);
                break;
            case "Subtract":
                operation = "-";
                result = subtract(numOne, numTwo);
                break;
            case "subtract":
                operation = "-";
                result = subtract(numOne, numTwo);
                break;
            case "Multiply":
               operation = "*";
               result = multiply(numOne, numTwo);
               break;
            case "multiply":
                operation = "*";
                result = multiply(numOne, numTwo);
                break;
            case "Divide":
                operation = "/";
                result = divide(numOne, numTwo);
                break;
            case "divide":
                operation = "/";
                result = divide(numOne, numTwo);
                break;
            case "Modulus":
                operation = "%";
                result = modulus(numOne, numTwo);
                break;
            case "modulus":
                operation = "%";
                result = modulus(numOne, numTwo);
                break;
            case "Square Root":
                result = squareRoot(numOne);
                break;
            case "square root":
                result = squareRoot(numOne);
                break;
            case "Exit":
                break;
            case "exit":
                break;
            //Allows the use of an error message by setting valid to false.
            default:
                valid = false;
                break;
        }
        //Works only if operation equals square root.
        if(!operation.equalsIgnoreCase("Square Root") && valid)
        {
            JOptionPane.showMessageDialog(null, numOne + " " + operation + " " + numTwo + " = " + result, "Result", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(numOne + " " + operation + " " + numTwo + " = " + result);
        }
        //Works if the operation is valid.
        else if(valid)
        {
            JOptionPane.showMessageDialog(null, operation + " of " + numOne + " = " + result, "Result", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(operation + " of " + numOne + " = " + result);
        }
        //Shows an error message.
        else
        {
            JOptionPane.showMessageDialog(null, "Sorry, this input is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("This is not a valid operation.");
            valid = true;
        }
    }
    /**
     * Adds numOne and numTwo, returning the value.
     * 
     * @param numOne
     * @param numTwo
     * @return numOne + numTwo
     */
    public static double add(double numOne, double numTwo)
    {
        return numOne + numTwo;
    }
    /**
     * Subtracts numOne and numTwo, returning the value.
     * 
     * @param numOne
     * @param numTwo
     * @return numOne - numTwo
     */
    public static double subtract(double numOne, double numTwo)
    {
        return numOne - numTwo;
    }
    /**
     * Multiplies numOne and numTwo, returning the value.
     * 
     * @param numOne
     * @param numTwo
     * @return numOne * numTwo
     */
    public static double multiply(double numOne, double numTwo)
    {
        return numOne * numTwo;
    }
    /**
     * Divides numOne and numTwo, returning the value.
     * 
     * @param numOne
     * @param numTwo
     * @return numOne / numTwo
     */
    public static double divide(double numOne, double numTwo)
    {
        return numOne / numTwo;
    }
    /**
     * Returns the Modulus of numOne and numTwo.
     * 
     * @param numOne
     * @param numTwo
     * @return numOne % numTwo
     */
    public static double modulus(double numOne, double numTwo)
    {
        return numOne % numTwo;
    }
    /**
     * Square Roots numOne, returning the value.
     * 
     * @param numOne
     * @return Math.sqrt(numOne)
     */
    public static double squareRoot(double numOne)
    {
        return Math.sqrt(numOne);
    }
}