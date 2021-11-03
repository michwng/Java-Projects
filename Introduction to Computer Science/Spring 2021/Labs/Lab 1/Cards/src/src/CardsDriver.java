package src;
/**
 * ---------------------------------------------------------------------------
 * File name: CardsDriver.java
 * Project name: Cards
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Feb 8, 2021
 * ---------------------------------------------------------------------------
 */

import java.util.Scanner;

/**
 * The CardsDriver class. Randomly chooses cards 
 * and allows the user to choose a random card
 * by typing in a number from 1-52.
 * Afterward, prints all possible cards.
 *
 * <hr>
 * Date created: Feb 8, 2021
 * <hr>
 * @author Michael Ng
 */
public class CardsDriver
{

	/**
	 * The main method for the CardsDriver class.    
	 *
	 * <hr>
	 * Date created: Feb 8, 2021
	 *
	 * <hr>
	 * @param args
	 */
	public static void main (String [ ] args)
	{
		Scanner kb = new Scanner(System.in);
		
		Card defaultCard = new Card();
		System.out.println("The default card is the " + defaultCard.toString());
		
		while(true) 
		{
			System.out.println("\n--------------------------------------------------------------" +
							"\nPlease type in an integer between 1 and 52 to generate a card! " +
							"\nType -1 to stop.");
			System.out.print ("Your input: ");
			int input = kb.nextInt();
			
			if(input == -1) 
			{
				break;
			}
			else if(input < 1 || input > 52) 
			{
				System.out.println ("Sorry, this number is not between 1 to 52.");
			}
			else 
			{
				Card integerCard = new Card(input);
				System.out.println (integerCard.toString ( ));
			}
		}
		
		showDeck();
		
		kb.close();
	}
	
	private static void showDeck() 
	{
		System.out.println("\n-----------------------" +
						"\nAll 52 Cards Follow:\n");
		
		//Solution 1
		for(Suit suit: Suit.values()) 
		{
			for(Face face: Face.values()) 
			{
				System.out.println("The " + face + " of " + suit);
			}
		}

	}

}
