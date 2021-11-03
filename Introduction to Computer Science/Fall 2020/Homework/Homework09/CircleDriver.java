/**
 * ---------------------------------------------------------------------------
 * File name: CircleDriver.java
 * Project name: Homework09
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1250-942
 * Creation Date: November 10, 2020
 * ---------------------------------------------------------------------------
*/
import javax.swing.JOptionPane;
/**
 * Simulates a Payroll by creating an employee
 * and calculating their wage based on user input
 * in PayrollDriver.java.
 *
 * Date created: November 10, 2020
 * 
 * @author Michael Ng
 */
public class CircleDriver
{
    /**
     * Main method to run the program.
     * Displays output based the radius entered 
     * by the user.
     * 
     * Date created: November 10, 2020
     * @param args
     */
    public static void main(String[] args)
    {
        double radius = Double.parseDouble(JOptionPane.showInputDialog(null, "What is the Radius of the Circle?", "Circle Radius", JOptionPane.QUESTION_MESSAGE));
        Circle circle = new Circle(radius);

        JOptionPane.showMessageDialog(null, circle.toString(), "Circle Characteristics", JOptionPane.INFORMATION_MESSAGE);
    }
}
