/**
 * ---------------------------------------------------------------------------
 * File name: Face.java
 * Project name: Cards
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Feb 8, 2021
 * ---------------------------------------------------------------------------
 */

/**
 * The Face enum class stores all constant values 
 * for card faces.
 *
 * <hr>
 * Date created: Feb 8, 2021
 * <hr>
 * @author Michael Ng
 */
public enum Face
{
	ACE,
	DEUCE,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	NINE,
	TEN,
	JACK,
	QUEEN,
	KING;

	/**
	 * 
	 * The getRandomFace() method returns a random face.      
	 *
	 * <hr>
	 * Date created: Feb 8, 2021
	 *
	 * <hr>
	 * @return Face
	 */
	public static Face getRandomFace() 
	{
		//randomNum is a random number with bounds 0 through Face.values().length.
		//Because Face.values().length is not inclusive, there is no need to subtract 1.
		int randomNum = (int)(Math.random()*Face.values().length);
		
		//This for-each loop iterates through each constant and sees if they match randomNum.
		//If they do, the value of the constant is returned.
		for(Face face: Face.values()) 
		{
			if(face.ordinal() == randomNum) 
			{
				return face;
			}
		}
		//This return statement is added in case nothing is returned in the for-each loop.
		return Face.ACE;
	}

}