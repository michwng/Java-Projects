/**
 * --------------------------------------------------------------------------
 * File name: AverageScore.java
 * Project name: Homework07Bonus
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 10/20/2020
 * --------------------------------------------------------------------------
 */
import javax.swing.JOptionPane;

/**
 * Determines the average among three scores
 * by asking the user for the values of
 * the three scores.
 * 
 * Date created: October 20, 2020
 * 
 * @author Michael Ng, ngmw01@etsu.edu
 */
public class AverageScore
{
    /**
     * Method to run the program. Accepts user input for all three scores
     * and makes calculations based on the input.
     * 
     * Date created: October 20, 2020
     * @param args
     */
    public static void main(String[] args) 
    {
        //Declared variable names. 
        double score1;
        double score2;
        double score3;
        double average;
        //Scanner scan = new Scanner(System.in);

        /**Asks the user to input the first, second, and third score.
         * Initializes score1, score2, score3 to a double using JOptionPane.showInputDialog;
         */
        //System.out.print("Please enter the first score: ");
        score1 = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter the first score.", "First Score", JOptionPane.DEFAULT_OPTION));

        //System.out.print("Please enter the second score: ");
        score2 = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter the second score.", "Second Score", JOptionPane.DEFAULT_OPTION));

        //System.out.print("Please enter the third score: ");
        score3 = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter the third score.", "Third Score", JOptionPane.DEFAULT_OPTION));

        average = (score1 + score2 + score3)/3;
        JOptionPane.showMessageDialog(null, "The average is: " + average, "The Average Score", JOptionPane.INFORMATION_MESSAGE);

        //If the calculated average is above 95.0%, recieve a compliment.
        if(average > 95.0)
        {
            JOptionPane.showMessageDialog(null, "Congratulations!\nYour average is above 95%!", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
        }

        //Closes the main method.
        System.exit(0);
    }
}