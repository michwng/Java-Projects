/**
 * ---------------------------------------------------------------------------
 * File name: BowlingAverage.java<br/>
 * Project name: Classwork - Exception Handling<br/>
 * ---------------------------------------------------------------------------
 * Creator's name and email: Don Bailes, bailes@etsu.edu<br/>
 * Course: CSCI 1260<br/>
 * Creation Date: Oct 11, 2017<br/>
 * ---------------------------------------------------------------------------
 */

package bowling;

import java.text.DecimalFormat;
import util.Util;

/**
 * Calculator for bowling average<br>
 *
 * <hr>
 * Date created: Oct 11, 2017<br>
 * <hr>
 * 
 * @author Don Bailes
 */
public class BowlingAverage
{
	private int [ ]	scores;
	private int		numGames;
	private double	average;

	/**
	 * Default Constructor <br>
	 *
	 * <hr>
	 * Date created: Oct 11, 2017 <br>
	 *
	 * <hr>
	 */
	public BowlingAverage ( )
	{
		numGames = 0;
		average = 0.0;
		scores = null;
	}

	/**
	 * Parameterized Constructor <br>
	 *
	 * <hr>
	 * Date created: Oct 11, 2017 <br>
	 *
	 * <hr>
	 * 
	 * @param scores
	 *            array of scores
	 * @param numGames
	 *            number of scores
	 * @throws BowlingException
	 *             thrown for bad bowling score input
	 */
	public BowlingAverage (int [ ] scores, int numGames) throws BowlingException
	{
		if (numGames > scores.length)
			throw new BowlingException (
							"The number of games is less than the number of scores submitted");

		for (int score : scores)
			if (score < 0 || score > 300)
				throw new BowlingException ("Bowling score " + score +
								" is invalid.  It must be between 0 and 300.");

		this.scores = scores;
		if (numGames > 0)
			this.numGames = numGames;
		else
			throw new BowlingException ("Number of games must be a positive integer.");

		calculateAverage ( );
	}

	/**
	 * Calculate average score <br>
	 *
	 * <hr>
	 * Date created: Oct 11, 2017 <br>
	 *
	 * <hr>
	 */
	private void calculateAverage ( )
	{
		double sum = 0.0;
		if (numGames > 0)
		{
			for (int score : scores)
				sum += score;
			average = sum / numGames;
		}
	}

	/**
	 * Format object as a string <br>
	 *
	 * <hr>
	 * Date created: Oct 11, 2017 <br>
	 *
	 * <hr>
	 * 
	 * @return formatted string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ( )
	{
		DecimalFormat fmt = new DecimalFormat ("#,##0");
		DecimalFormat fmtAvg = new DecimalFormat ("##0.00");
		String result = "";
		result = "\nFor the following scores\n\n";
		if (scores != null)
		{
			for (int score : scores)
				result += Util.right (fmt.format (score), 18) + "\n";

			result += "---------------------\n";
			result += "The average is " + Util.right (fmtAvg.format (average), 6) + "\n";
		}
		else
			result = "There are no scores to average.\n";

		return result;
	}
}
