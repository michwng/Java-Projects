/**
 * ---------------------------------------------------------------------------
 * File name: Hand.java
 * Project name: Lab 3
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Feb 22, 2021
 * ---------------------------------------------------------------------------
 */

import java.util.ArrayList;

/**
 * The Hand class holds a "hand" of playing cards.
 * The amount of hands there will be is determined by the
 * amount of players.
 *
 * <hr>
 * Date created: Feb 22, 2021
 * <hr>
 * @author Michael Ng
 */
public class Hand
{
	private int handSize = 0;
	private int cardsInHand = 0;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	/**
	 * 
	 * The Default Constructor for the Hand class. 
	 *
	 * <hr>
	 * Date created: Feb 22, 2021 
	 *
	 *
	 */
	public Hand() 
	{
		//Cards are automatically inputted into 
		//the Hand in the Deck class.
		handSize = 5;
	}
	
	/**
	 * 
	 * The Secondary Constructor for the Hand class.
	 * This class takes in an integer to determine hand size.
	 *
	 * <hr>
	 * Date created: Feb 22, 2021 
	 *
	 * 
	 * @param handSize
	 */
	public Hand(int handSize) 
	{
		//Cards are automatically inputted into 
		//the Hand in the Deck class.
		this.handSize = handSize;
	}
	
	/**
	 * 
	 * The Tertiary Constructor for the Hand class.
	 * This class does a deep copy of another Hand
	 * into this Hand.
	 * 
	 * <hr>
	 * Date created: Feb 22, 2021 
	 *
	 * 
	 * @param handIn
	 */
	public Hand(Hand handIn) 
	{
		this.handSize = handIn.hand.size();
		//Deep copies a hand from a previously made Hand.
		for(Card card: handIn.hand) 
		{
			hand.add(card);
			cardsInHand++;
		}
	}
	
	
	
	/**
	 * 
	 * This method adds the card in athe parameter to the hand ArrayList.
	 * Prints an error message if there are too many cards in the hand.
	 *
	 * <hr>
	 * Date created: Feb 22, 2021
	 *
	 * <hr>
	 * @param card
	 */
	public void addCard(Card card) 
	{
		if(handSize >= cardsInHand) 
		{
			hand.add(card);
			cardsInHand++;
		}
		else 
		{
			System.out.print ("Sorry, but you cannot add a Card. " +
							"\nReason: Aadding a new card would exceed the current hand size.");
		}
	}
	
	/**
	 * 
	 * The toString method for the Hand class.
	 * Returns a 2-column list of cards in the hand ArrayList.      
	 *
	 * <hr>
	 * Date created: Feb 22, 2021 
	 *
	 * <hr>
	 * @return message - String
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		String message = "";
		
		//This is made so a JOptionPane window that shows 15+ cards per person will fit the screen.
		//However, in games with lots of people, this will not work as well.
		int i = 0;
		
		for(Card card: hand) 
		{
			if(i % 2 == 0) 
			{
				message += "     | " + card.toString();
			}
			else 
			{
				message += "   |   " + card.toString() + "\n";
			}
			i++;
		}
		
		/**
		 * The previous version of this method. 
		message = "";
		
		for(Card card: hand) 
		{
			message += "     " + card.toString() + "\n";
		}
		*/
		return message;
	}
}
