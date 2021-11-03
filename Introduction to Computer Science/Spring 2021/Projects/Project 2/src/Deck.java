/**
 * ---------------------------------------------------------------------------
 * File name: Deck.java
 * Project name: Lab 2
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Feb 17, 2021
 * ---------------------------------------------------------------------------
 */

import java.util.Random;

/**
 * The Deck class utilizes the Card class to show playing cards
 * to the user.
 *
 * <hr>
 * Date created: Feb 17, 2021
 * <hr>
 * @author Michael Ng
 */
public class Deck
{
	private Card[] deck = new Card[52];
	private int nextCard;
	
	/**
	 * 
	 * Default Constructor for the Deck Class.
	 *
	 * <hr>
	 * Date created: Feb 17, 2021 
	 *
	 */
	public Deck() 
	{
		for(int i = 0; i < 52; i++) 
		{
			Card card = new Card(i);
			deck[i] = card;
		}
		nextCard = 0;
	}
	
	/**
	 * 
	 * Secondary Constructor that takes in an existing deck.
	 * This performs a Deep Copy, as opposed to a shallow copy.
	 *
	 * <hr>
	 * Date created: Feb 17, 2021 
	 *
	 * 
	 * @param existingDeck
	 */
	public Deck(Deck existingDeck) 
	{
		for(Card card: existingDeck.getDeck()) 
		{
			int i = 0;
			deck[i] = card;
			i++;
		}
		nextCard = 0;
	}
	
	
	/**
	 * 
	 * This method allows 2 specific cards to be swapped.      
	 *
	 * <hr>
	 * Date created: Feb 22, 2021
	 *
	 * <hr>
	 * @param n - int
	 * @param m - int
	 */
	public void swap(int n, int m) 
	{
		deck[n] = new Card(deck[m]);
		deck[m] = new Card(n);
	}
	
	
	
	
	/**
	 * 
	 * This method shuffles all cards in the deck.
	 *
	 * <hr>
	 * Date created: Feb 17, 2021
	 *
	 * <hr>
	 */
	public void shuffle() 
	{
		Random rand = new Random();
		int randomNum = rand.nextInt(51) + 1;
		
		for(int i = 0; i < 52; i++) 
		{
			randomNum = rand.nextInt(51) + 1;
			Card temp = new Card(deck[i]);
			deck[i] = new Card(deck[randomNum]);
			deck[randomNum] = new Card(temp);
		}
		
		nextCard = 0;
	}
	
	/**
	 * This method returns a card in the deck at position nextCard.       
	 *
	 * <hr>
	 * Date created: Feb 17, 2021
	 *
	 * <hr>
	 * @return card (deck[nextCard])
	 */
	public Card dealACard() 
	{
		if(nextCard < 52) 
		{
			Card card = deck[nextCard];
			nextCard++;
			
			return card;
		}
		else 
		{
			System.out.println("There are no more cards on the deck. Shuffling...");
			shuffle();
			System.out.println("Shuffling Complete.");
			return deck[nextCard];
		}
	}
	
	/**
	 * 
	 * This method deals a hand to the user.         
	 *
	 * <hr>
	 * Date created: Feb 17, 2021
	 * Date Modified: Feb 22, 2021
	 * 
	 * <hr>
	 * @return Hand
	 */
	public Hand dealAHand(int handSize) 
	{
		Hand hand = new Hand(handSize);
		for(int i = 0; i < handSize; i++) 
		{
			hand.addCard(deck[nextCard]);
			nextCard++;
		}
		
		return hand;
	}
	
	/**
	 * Returns the instance's deck array of the Card type.
	 * 
	 * Date created: Feb 17, 2021
	 * 
	 * @return deck
	 */
	public Card[] getDeck ()
	{
		return deck;
	}

	/**
	 * 
	 * The toString method for the Deck class returns
	 * a String that contains all 52 cards. 
	 *
	 * <hr>
	 * Date created: Feb 17, 2021 
	 *
	 * <hr>
	 * @return message
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		int newLineMarker = 0;
		String message = "";
		
		for(Card card: deck) 
		{ 
			message += card.toString() + ", ";
			newLineMarker++;
			if(newLineMarker % 2 == 0) 
			{
				message += "\n";
			}
		}
		
		return message;
	}
}
















