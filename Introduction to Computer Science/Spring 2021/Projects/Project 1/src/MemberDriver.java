/**
 * ---------------------------------------------------------------------------
 * File name: MemberDriver.java
 * Project name: Project 1
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: February 3, 2021
 * ---------------------------------------------------------------------------
 */

import javax.swing.JOptionPane;

/**
 * The MemberDriver class allows the user to create and view club members 
 * through a combination of button input and typing input.
 *
 * <hr>
 * Date created: February 3, 2021
 * <hr>
 * @author Michael Ng
 */
public class MemberDriver
{
	/**
	 * The main method that is used to run Club Membership Roster.
	 * Allows the user to view and create club members.      
	 *
	 * <hr>
	 * Date created: February 3, 2021
	 *
	 * <hr>
	 * @param args
	 */
	public static void main(String [] args)
	{
		String username;
		Member member = null;

		username = JOptionPane.showInputDialog (null, "Welcome to Club Membership Roster! " +
						"\nThis program keeps track of all members for the club." +
						"\n\nWhat is your name?", "Welcome!", JOptionPane.DEFAULT_OPTION);
		
		//if the user does not input a name at all, sets the name to "Username".
		if(username.isBlank()) 
		{
			username = "Username";
		}
		
		//This is defined outside the while loop
		int optionChosen = 0;
		
		//The program repeats until X or 'end the program' is chosen.
		while(optionChosen != 2 && optionChosen != -1) 
		{
			String[] options = {"Create a New Member", "Display a Member", "End the Program"};
			optionChosen = JOptionPane.showOptionDialog (null, "Welcome, " + username + 
								"!\n\nPlease Select An Option.\n-----------------------------------", "Choose An Option", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
			
			//When the user selects "Create a New Member."
			if(optionChosen == 0) 
			{
				//This while statement is made so when the user 
				// presses the 'cancel' button, the user returns to the main menu.
				while(true) 
				{
					String firstName = JOptionPane.showInputDialog(null, "What is the member's first name?", "Member's First Name", JOptionPane.INFORMATION_MESSAGE);
					//These if statements take the user to the main screen when "cancel" is selected.
					if(firstName == null) 
					{
						break;
					}
					
					String lastName = JOptionPane.showInputDialog(null, "What is the member's last name?", "Member's Last Name", JOptionPane.INFORMATION_MESSAGE);
					if(lastName == null) 
					{
						break;
					}
					
					String city = JOptionPane.showInputDialog(null, "What city does the member live in?", "Member's City", JOptionPane.INFORMATION_MESSAGE);
					if(city == null) 
					{
						break;
					}
					
			
					String state = JOptionPane.showInputDialog(null, "What state does the member live in?", "Member's State", JOptionPane.INFORMATION_MESSAGE);
					if(state == null) 
					{
						break;
					}
					
					
					//This particular input statement is a button-dialog statement, 
					//where the user can pick the classification from a variety of buttons.
					//No if-statement is needed since there is no cancel option.
					String[] options1 = {MemberType.PROVISIONAL.toString(), MemberType.STUDENT.toString(), MemberType.JUNIOR.toString(), MemberType.SENIOR.toString(), MemberType.LIFETIME.toString(), MemberType.OFFICER.toString()};		
					int optionNum = JOptionPane.showOptionDialog(null, "What is the member's classification?", "Member's Classification", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options1, options1[0]);
					String memberType = options1[optionNum];
					
					String dateJoined = JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE);
					if(dateJoined == null) 
					{
						break;
					}
					
					String pictureFilePath = JOptionPane.showInputDialog(null, "What is the filepath to the member's photo?", "Member's Picture File Path", JOptionPane.INFORMATION_MESSAGE);
					if(pictureFilePath == null) 
					{
						break;
					}
					
				
					//The following if-statements assign default values 
					//if the user leaves any input space blank (not including memberType).
					if(firstName.isBlank())
					{
						firstName = "Barney";
					}
					if(lastName.isBlank())
					{
						lastName = "The-Dinosaur";
					}
					if(city.isBlank())
					{
						city = "Johnson City";
					}
					if(state.isBlank())
					{
						state = "TN";
					}
					if(dateJoined.isBlank())
					{
						dateJoined = "01/01/2000";
					}
					if(pictureFilePath.isBlank())
					{
						pictureFilePath = firstName + lastName + ".jpg";
					}
					
					member = new Member(firstName, lastName, city, state, memberType, dateJoined, pictureFilePath);
					break;
				}
			}
			//If the user selects "Display a Member"
			else if(optionChosen == 1) 
			{
				//If the Member has already been created
				if(!(member == null))
				{
					JOptionPane.showMessageDialog (null, member.toString(), "Member Attributes", JOptionPane.INFORMATION_MESSAGE);
				}
				else //if the member has not been created
				{
					JOptionPane.showMessageDialog(null, "Sorry, there are currently no members. \nPlease create a new member!", "No Members", JOptionPane.ERROR_MESSAGE);
				}
			}
			//If the user ends the program or presses the X button.
			else 
			{
				JOptionPane.showMessageDialog (null, "Thank you, " + username + ", for using Club Membership Roster!", "Thank you!", JOptionPane.DEFAULT_OPTION);
				System.exit(0);
			}
		}
	}
}
