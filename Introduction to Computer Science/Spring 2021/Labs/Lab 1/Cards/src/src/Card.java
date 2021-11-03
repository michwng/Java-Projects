package src;
/**
 * ---------------------------------------------------------------------------
 * File name: Card.java
 * Project name: Cards
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Feb 8, 2021
 * ---------------------------------------------------------------------------
 */

/**
 * The Card class simulates a card from a standard card deck.
 * This class has 3 constructors for creating cards.
 *
 * <hr>
 * Date created: Feb 8, 2021
 * <hr>
 * @author Michael Ng
 */
public class Card
{
	Suit suit;
	Face face;
	
	/**
	 * The Default Constructor for the Card Class.
	 *
	 * <hr>
	 * Date created: Feb 8, 2021 
	 *
	 *
	 */
	public Card() 
	{
		suit = Suit.getRandomSuit ( );
		face = Face.getRandomFace ( );
	}
	
	/**
	 * 
	 * The Secondary Constructor for the Card Class.
	 * Takes in a Card parameter.
	 *
	 * <hr>
	 * Date created: Feb 8, 2021 
	 *
	 * 
	 * @param existingCard
	 */
	public Card(Card existingCard) 
	{
		
		face = existingCard.face;
		suit = existingCard.suit;
	}
	
	/**
	 * 
	 * The Tertiary Constructor for the Card class.
	 * Takes in an int parameter.
	 *
	 * <hr>
	 * Date created: Feb 8, 2021 
	 *
	 * 
	 * @param n
	 */
	public Card(int n) 
	{
		face = Face.values ( )[n%13];
		suit = Suit.values ( )[n%4];
	}

	
	/**
	 * 
	 * The toString() method for the Card class.
	 * Returns a string in the form of: FACE of SUIT.
	 *
	 * <hr>
	 * Date created: Feb 8, 2021 
	 *
	 * <hr>
	 * @return message
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		String message = face + " of " + suit;
		
		return message;
	}
}
