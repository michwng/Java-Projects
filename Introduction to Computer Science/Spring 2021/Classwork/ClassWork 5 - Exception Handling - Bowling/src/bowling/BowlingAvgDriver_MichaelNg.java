/**
 * ---------------------------------------------------------------------------
 * File name: BowlingAvgDriver_MichaelNg.java
 * Project name: Classwork - Exception Handling
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Mar 10, 2021
 * ---------------------------------------------------------------------------
 */

package bowling;

import javax.swing.JOptionPane;

/**
 * The BowlingAverageDriver class uses the
 * BowlingAverage class to calculate the average
 * of bowling scores.
 *
 * <hr>
 * Date created: Mar 10, 2021
 * <hr>
 * @author Michael Ng
 */
public class BowlingAvgDriver_MichaelNg
{
	/**
	 * The main method for the BowlingAverageDriver class.
	 * Calculates bowling average scores.     
	 *
	 * <hr>
	 * Date created: Mar 10, 2021
	 *
	 * <hr>
	 * @param args
	 */
	public static void main (String [ ] args)
	{
		boolean repeat = true;
		//This is initialized here to prevent a compilation error.
		BowlingAverage average = new BowlingAverage();
		
		JOptionPane.showMessageDialog(null, "Welcome to Bowling Average Driver!\nYou can calculate Bowling Averages here.", "Welcome!", JOptionPane.PLAIN_MESSAGE);
		
		//While the user wants to repeat inputting games.
		while(repeat) 
		{
			int choice = 1;
			int arraySize = 0;
			boolean unconfirmed = true;
			//This while loop acts as a validator for the user inputting how many games they bowled.
			while(unconfirmed) 
			{
				try 
				{
					//Could throw NumberFormatException.
					arraySize = Integer.parseInt(JOptionPane.showInputDialog(null, "How many Bowling Games were played?", "Input Number of Games", JOptionPane.QUESTION_MESSAGE));
					choice = 0;
				}
				//If the value inputted was not a number.
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog (null, "Sorry, but this is not a numerical value.\nPlease try again.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				//If the user played more than 10 games.
				if(arraySize > 10) 
				{
					choice = JOptionPane.showConfirmDialog (null, "Are you sure you played " + arraySize + " Bowling Games?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
					//If the user did not pick yes.
					if(choice == 0) 
					{
						unconfirmed = false;
					}
				}
				else 
				{
					unconfirmed = false;
				}
			} // end of the while(choice != 0) loop.
			//A temporary array. It is used for validation and to find the average score.
			int[] scores = new int[arraySize];
			for(int i = 0; i < scores.length; i++) 
			{
				try 
				{
					//Could throw a NumberFormatException.
					scores[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input the score for game #" + (i+1) + ".", "Input Score for Game #" + (i+1), JOptionPane.QUESTION_MESSAGE));
					//Could throw a BowlingException.
					average = new BowlingAverage(scores, scores.length);
				}
				//If the input is invalid in the BowlingAverage class.
				catch(BowlingException e)
				{
					JOptionPane.showMessageDialog (null, e.getMessage ( ) + "\nPlease try again.", "Error", JOptionPane.ERROR_MESSAGE);
					i--;
				}
				//If the user's input was not a number.
				catch(NumberFormatException e) 
				{
					JOptionPane.showMessageDialog(null, "Sorry, but this is not a number. \nPlease input a number value.\n" + e.getMessage ( ), "Error: Not a Number.", JOptionPane.ERROR_MESSAGE);
					i--;
				}
			}
			//There is no need for a try/catch here since all values are validated.
			JOptionPane.showMessageDialog (null, average.toString ( ), "Average Score", JOptionPane.INFORMATION_MESSAGE);
			choice = JOptionPane.showConfirmDialog (null, "Would you like to continue?", "Continue?", JOptionPane.YES_NO_OPTION);
			//if the user chose "yes" in the confirmation dialog.
			if(choice != 0) 
			{
				repeat = false;
			}
		} //end of the while(repeat) loop.
		JOptionPane.showMessageDialog (null, "Thank you for using BowlingAverageDriver!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
		System.exit (0);
	} //end of the main method.
}
