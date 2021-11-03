/**
 * --------------------------------------------------------------------------
 * File name: LetterGrades.java
 * Project name: Homework07Bonus
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 10/20/2020
 * --------------------------------------------------------------------------
 */
import javax.swing.JOptionPane;

/**
 * Prints a letter grade based on
 * a number grade retrieved from the user.
 * 
 * Date created: October 20, 2020
 * 
 * @author Michael Ng, ngmw01@etsu.edu
 */
public class LetterGrades 
{
    /**
     * Method to run the program. Accepts user input
     * for a number grade and shows a letter grade based on input.
     * 
     * Date created: October 20, 2020
     * @param args
     */
    public static void main(String[] args) 
    {
        //Declares an int and initalilzes a Scanner.
        int testScore;
        //Scanner scan = new Scanner(System.in);

        //System.out.print("Please input a number score for a letter grade: ");
        testScore = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input a number score for a letter grade: ", "Number Grade", JOptionPane.DEFAULT_OPTION));

        /**
         * If the score is below 60, outputs an F.
         * If the score is below 70, outputs an D.
         * If the score is below 80, outputs an C.
         * If the score is below 90, outputs an B.
         * Else, outputs an A.
         */
        if(testScore < 60)
        {
            JOptionPane.showMessageDialog(null, "Your Letter Grade is: F", "Latter Grade", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(testScore < 70)
        {
            JOptionPane.showMessageDialog(null, "Your Letter Grade is: D", "Latter Grade", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(testScore < 80)
        {
            JOptionPane.showMessageDialog(null, "Your Letter Grade is: C", "Latter Grade", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(testScore < 90)
        {
            JOptionPane.showMessageDialog(null, "Your Letter Grade is: B", "Latter Grade", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Your Letter Grade is: A", "Latter Grade", JOptionPane.INFORMATION_MESSAGE);
        }
        
        //Closes the main method.
        System.exit(0);
    }
}
