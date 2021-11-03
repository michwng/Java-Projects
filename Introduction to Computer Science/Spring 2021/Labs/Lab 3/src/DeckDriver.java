/**
 * ---------------------------------------------------------------------------
 * File name: DeckDriver.java
 * Project name: Lab 2
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Feb 17, 2021
 * ---------------------------------------------------------------------------
 */

import javax.swing.JOptionPane;

/**
 * DeckDriver utilizes the Deck to simulate the dealing of cards.
 *
 * <hr>
 * Date created: Feb 17, 2021
 * <hr>
 * @author Michael Ng
 */
public class DeckDriver
{

	/**
	 * The main method for the DeckDriver class.  
	 * This method runs the program.     
	 *
	 * <hr>
	 * Date created: Feb 17, 2021
	 *
	 * <hr>
	 * @param args
	 */
	public static void main (String [ ] args)
	{
		Deck deck = new Deck();
		
		int handSize;
		int numPlayers;
		boolean shuffled = false;
		
		do
		{
			//String cardTemp and playerTemp are made to check if the user did not put anything.
			//After each answer is typed in, they are parsed.
			String cardTemp = JOptionPane.showInputDialog (null, "How many cards are in one hand?", "Cards in a Hand", JOptionPane.QUESTION_MESSAGE);
			if(cardTemp.isBlank())
			{
				handSize = 5;
			}
			else 
			{
				handSize = Integer.parseInt(cardTemp);				
			}

			
			//Default values for handSize and numPlayers are 6 and 2, respectively.
			//They will be substituted if the user's answer for cardTemp or playerTemp is blank.
			String playerTemp = JOptionPane.showInputDialog (null, "How many players are playing?", "Amount of Players", JOptionPane.QUESTION_MESSAGE);
			if(playerTemp.isBlank())
			{
				numPlayers = 3;
			}
			else 
			{
				numPlayers = Integer.parseInt(playerTemp);				
			}

			
			//If the while condition isn't met, this error message will show and repeat the loop again.
			if((handSize * numPlayers) > 52) 
			{
				JOptionPane.showMessageDialog (null, "There are not enough cards in the deck to deal " + numPlayers + " hands of " + handSize + " cards.\nPlease Try Again.", "Error: Not Enough Cards", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}while((handSize * numPlayers) > 52);
		
		
		int optionChosen = 0;
		String[] options = {"Show Cards", "Shuffle Cards", "Deal Cards", "Swap Cards", "Exit Program"};
		
		//While the user does not exit the program, continually repeats the program.
		do
		{
			optionChosen = JOptionPane.showOptionDialog (null, "Welcome!\n\nPlease Select An Option.", "Choose An Option", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
			
			//Show Cards chosen
			if(optionChosen == 0) 
			{
				JOptionPane.showMessageDialog (null, deck.toString(), "Order of Cards", JOptionPane.INFORMATION_MESSAGE);
			}
			//Shuffle Cards chosen
			else if(optionChosen == 1) 
			{
				deck.shuffle();
				shuffled = true;
				JOptionPane.showMessageDialog(null, "Shuffle Successful!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
			}
			//Deal Cards chosen
			else if(optionChosen == 2) 
			{
				
				//if the user did not shuffle the cards first.
				if(!shuffled) 
				{
					deck.shuffle();
					shuffled = true;
					JOptionPane.showMessageDialog(null, "Your cards have been shuffled automatically.", "Shuffled!", JOptionPane.INFORMATION_MESSAGE);
				}
				
				//Displays a list of every player and their cards.
				String display = "";
				for(int i = 1; i < numPlayers + 1; i++) 
				{
					display += "Player " + i + ":\n";
					display += deck.dealAHand(handSize).toString() + "\n";
				}
				JOptionPane.showMessageDialog(null, display, "Current Hands", JOptionPane.INFORMATION_MESSAGE);
				
				//The shuffled boolean is reset to false after displaying the message.
				shuffled = false;
			}
			//Swap Cards chosen
			else if(optionChosen == 3) 
			{
				int numOne;
				int numTwo;
				
				do 
				{
					numOne = Integer.parseInt(JOptionPane.showInputDialog (null, "Please type in a number between 0 and 51.", "First Card", JOptionPane.DEFAULT_OPTION));	
					if(numOne < 0 || numOne > 51) 
					{
						JOptionPane.showMessageDialog (null, "Sorry, this number is not between 0 and 51. \nPlease try again.", "Invalid Number", JOptionPane.INFORMATION_MESSAGE);
					}
				}while(numOne < 0 || numOne > 51);
				
				do 
				{
					numTwo = Integer.parseInt(JOptionPane.showInputDialog (null, "Please type in a second number between 0 and 51.", "Second Card", JOptionPane.DEFAULT_OPTION));
					if(numTwo < 0 || numTwo > 51) 
					{
						JOptionPane.showMessageDialog (null, "Sorry, this number is not between 0 and 51. \nPlease try again.", "Invalid Number", JOptionPane.INFORMATION_MESSAGE);
					}
				}while(numTwo < 0 || numTwo > 51);	
				
				deck.swap(numOne, numTwo);
				JOptionPane.showMessageDialog (null, "The playing cards at position " + numOne + " and " + numTwo + " have successfully swapped!", "Swapped!", JOptionPane.INFORMATION_MESSAGE);
			}
			//Exit Program is chosen
			else 
			{
				JOptionPane.showMessageDialog (null, "Thank you for using DeckDriver!", "Thank You!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		//While the user does not exit the program, continually repeats the program.
		}while(optionChosen != 4 && optionChosen != -1);
		
	}
}
