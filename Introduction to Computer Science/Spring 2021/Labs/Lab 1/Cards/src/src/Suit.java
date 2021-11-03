package src;
/**
 * ---------------------------------------------------------------------------
 * File name: Suit.java
 * Project name: Cards
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Feb 8, 2021
 * ---------------------------------------------------------------------------
 */

/**
 * The Suit enum class stores all constant values
 * for card suits.
 *
 * <hr>
 * Date created: Feb 8, 2021
 * <hr>
 * @author Michael Ng
 */
public enum Suit
{
	CLUBS,
	DIAMONDS,
	HEARTS,
	SPADES;

	
	/**
	 * 
	 * The getRandomSuit() method returns a random suit.
	 *
	 * <hr>
	 * Date created: Feb 8, 2021
	 *
	 * <hr>
	 * @return Suit
	 */
	public static Suit getRandomSuit() 
	{
		//randomNum is a random number with bounds 0 through Suit.values().length.
		//Because Suit.values().length is not inclusive, there is no need to subtract 1.
		int randomNum = (int)(Math.random()*Suit.values().length);
		
		//This for-each loop iterates through each constant and sees if they match randomNum.
		//If they do, the value of the constant is returned.
		for(Suit suit: Suit.values()) 
		{
			if(suit.ordinal() == randomNum) 
			{
				return suit;
			}
		}
		//This return statement is added in case nothing is returned in the for-each loop.
		return Suit.SPADES;
	}
}