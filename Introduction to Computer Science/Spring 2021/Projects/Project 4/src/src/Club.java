package src;

/**
 * ---------------------------------------------------------------------------
 * File name: Club.java
 * Project name: Project 3
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Mar 3, 2021
 * ---------------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * The club class keeps track of all members in the club.
 *
 * <hr>
 * Date created: Mar 3, 2021
 * <hr>
 * @author Michael Ng
 */
public class Club
{
	private boolean saveNeeded = false;
	private ArrayList <Member> roster;
	private String clubName, head;
	private int yearFounded;
	
	public Club(String clubName, String head, int yearFounded) 
	{
		roster = new ArrayList<Member>();
		this.clubName = clubName;
		this.head = head;
		this.yearFounded = yearFounded;
	}
	
	/**
	 * The addMember method adds a Member to the roster ArrayList.     
	 *
	 * Date created: Mar 3, 2021
	 *
	 * @param member
	 */
	public void addMember (Member member) 
	{
		roster.add (member);
		saveNeeded = true;
	}
	
	/**
	 * The getRoster method returns the roster ArrayList.       
	 *
	 * <hr>
	 * Date created: Mar 3, 2021
	 *
	 * <hr>
	 * @return roster
	 */
	public ArrayList<Member> getRoster() 
	{
		return roster;
	}
	
	/**
	 * The getRoster method returns the roster ArrayList.       
	 *
	 * <hr>
	 * Date created: Mar 3, 2021
	 *
	 * <hr>
	 * @return roster.size()
	 */
	public int getRosterSize() 
	{
		return roster.size();
	}
	
	/**
	 * The getMember method returns a member at the specified index.     
	 *
	 * Date created: Mar 3, 2021
	 *
	 * @param index
	 * @return Member
	 */
	public Member getMember(int index) 
	{
		return roster.get(index);
	}
	
	/**
	 * The getMember method returns a string containing member information. 
	 *
	 * Date created: Mar 3, 2021
	 *
	 * @param firstName, lastName - String
	 * @return String
	 */
	public String getMemberInformation(String firstName, String lastName) 
	{
		String message = "";
		//if there are multiple people with the same first and last name,
		//duplicate can tell and display a message (in the console) if so.
		int duplicates = 0;
		
		for(int i = 0; i < roster.size(); i++) 
		{
			//This if statement matches first and last names to see if they match a member.
			if(roster.get (i).getFirstName ( ).equalsIgnoreCase (firstName) && roster.get (i).getLastName ( ).equalsIgnoreCase (lastName)) 
			{
				message += roster.get (i).toString ( );
				duplicates++;
			}
		}
		
		if(duplicates > 1) 
		{
			System.out.println("Members with the same first and last name have been found.");
			System.out.println("All members with duplicate first/last names will be displayed.");
		}
		
		return message;
	}
	
	/**
	 * The secondary (overloaded) method of getMemberInformation.
	 * This method returns members that match the type in the parameter.
	 *
	 * <hr>
	 * Date created: Mar 3, 2021
	 *
	 * <hr>
	 * @param memberType
	 * @return message
	 */
	public String getMemberInformation(MemberType memberType) 
	{
		String message = "";
		//if there are multiple people with the same first and last name,
		//duplicate can tell and display a message (in the console) if so.
		int duplicates = 0;
		
		for(int i = 0; i < roster.size(); i++) 
		{
			if(roster.get (i).getMemberType ( ).equals (String.valueOf(memberType)))
			{
				message += roster.get (i).toString ( );
			}
		}
		
		if(duplicates > 1) 
		{
			System.out.println("Members with the same first and last name have been found.");
			System.out.println("All members with duplicate first/last names will be displayed.");
		}
		
		//Since the roster may not have people in certain types, may throw an error message
		return message;
	}
	
	/**
	 * The toString method for the Club class.       
	 *
	 * Date created: Mar 3, 2021 
	 *
	 * @return message - String
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		String message = "";
		
		for(Member member: roster) 
		{
			message += member.toString();
		}
		
		return message;
	}
	
	/**
	 * An overloaded toString method for the Club class. 
	 * This method is particularly essential for separating members into pages in the MemberDriver.      
	 *
	 * Date created: Mar 26, 2021 
	 *
	 * @return message - String
	 * @see java.lang.Object#toString()
	 */
	public String toString(int startingIndex) 
	{
		String message = "";
		
		//This if statement skips the loop if startingIndex is too high.
		if(startingIndex > roster.size()) 
		{
			return "";
		}
		
		//Returns the attributes of 5 members, starting at the startingIndex index.
		for(int i = startingIndex; i < startingIndex + 5; i++) 
		{
			//Breaks out of the for-loop when an IndexOutOfBoundsException is thrown.
			try 
			{
				message += roster.get(i).toString();
				//This if statement breaks out of the loop if startingIndex is equal to the roster's size.
				if(startingIndex == roster.size()) 
				{
					break;
				}
			}
			catch(IndexOutOfBoundsException e) 
			{
				break;
			}
		}
		
		return message;
	}
	
	/**
	 * the aboutClub method returns information for
	 * the club's name, president, and year it was found.      
	 *
	 * Date created: Mar 4, 2021 
	 *
	 * @return message - String
	 * @see java.lang.Object#toString()
	 */
	public String aboutClub() 
	{
		String message = "";
		
		message += clubName + "\n";
		message += "-------------------------";
		message += "\nClub President: " + head;
		message += "\nYear Founded: " + yearFounded;
		
		return message;
	}
	
	/**
	 * Mutator method for the saveNeeded field.     
	 *
	 * <hr>
	 * Date created: Mar 27, 2021
	 *
	 * <hr>
	 * @param input
	 */
	public void setSaveNeeded(boolean input) 
	{
		saveNeeded = input;
	}
	
	/**
	 * Accessor method for the saveNeeded field.
	 *
	 * <hr>
	 * Date created: Mar 27, 2021
	 *
	 * <hr>
	 * @return
	 */
	public boolean saveNeeded() 
	{
		return saveNeeded;
	} 
	
	/**
	 * The fillFromFile method populates the file provided in the filepath.  
	 * Note: This method is not called if the user decides not to import members.
	 * 
	 * 
	 *
	 * <hr>
	 * Date created: Mar 27, 2021
	 *
	 * <hr>
	 * @param filepath
	 */
	public void fillFromFile(String filepath) 
	{
		File path = new File(filepath);
		Scanner inputFile = null;
		//The filepath parameter has been heavily validated in the MemberDriver,
		//so it is *very* unlikely that an error is thrown. 
		//(Try-Catch is provided because of the Compilation Error)
		try
		{
			inputFile = new Scanner(path);
		}
		catch (FileNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		while(inputFile.hasNext ( )) 
		{
			//The clubMembers text file has a lot of validation errors. Can this code fix them all? - Answer: Yes.
			//These values are not validated: First Name, Last Name, City, and Picture File Path. 
			
			String str = inputFile.nextLine();
			String[] values = str.split ("\\|");
			
			//This for-loop increments by 7 everytime so indexes may use the same field every run. (First name, Last Name, ...)
			//This for-loop checks for all errors in a member's records and moves onto the next member when done.
			for(int i = 0; i < values.length - 1; i+=7) 
			{
				//Validates Member Type. If they are not one of the member types, they are automatically assigned to Provisional.
				String memType = values[i+4];
				//One big if-statement to check for all membertypes.
				if(memType.equals("PROVISIONAL") || memType.equals("STUDENT") || memType.equals("JUNIOR") || memType.equals("SENIOR") || memType.equals("LIFETIME") || memType.equals("OFFICER")) 
				{
					//Does nothing. Moves to validate the person's state.
				}
				else 
				{
					JOptionPane.showMessageDialog (null, "The Member Type for Member " + values[i] + " " + values[i+1] + " is not a valid Member Type." +
									"\nMember Type on Record: " + memType + 
									"\nTheir Member Type has automatically been assigned as \"PROVISIONAL\".", "Invalid Member Type", JOptionPane.INFORMATION_MESSAGE);
					values[i+4] = "PROVISIONAL";
				}
				
				
				
				//This validates the person's state. If more than two characters, the program automatically decides
				//to switch the position of city and state.
				int temp = values[i+3].length ( );
				if(temp == 2) 
				{
					//Does nothing because the input is the right length. Skips the error algorithm below and proceeds to date checking.
				}
				else if(temp > 2) //In other words, is not a value like TN, VA, or other 2-character state.
				{
					temp = values[i+2].length();
					if(temp == 2) //The program detects that the 2-character state is in the city position.
					{
						JOptionPane.showMessageDialog (null, "The position of City and State for Member " + values[i] + " " + values[i+1] + " appears to have been switched." + 
										"\nCity: " + values[i+2] + "\nState: " + values[i+3] +
										"\nTheir values have automatically been switched.", "Switched Position", JOptionPane.INFORMATION_MESSAGE);
						String tempString = values[i+2];
						values[i+2] = values[i+3];
						values[i+3] = tempString;
						saveNeeded = true;
					}
					else //If both values contain more than 2 characters (temp > 2)
					{
						JOptionPane.showMessageDialog (null, "The state for Member " + values[i] + " " + values[i+1] + " is invalid. \n" + "State: " + values[i+3] +
										"\nThe first 2 letters typed in for their state will be taken.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
						//This takes the first 2 letters of the input and capitalizes them.
						values[i+3] = values[i+3].substring (0, 2).toUpperCase ( );
						saveNeeded = true;
					}
				}
				else if(temp == 1) //The value is only 1 letter long. (This appends the letter 'A' to the answer.)
				{
					values[i+3].concat("A");
					saveNeeded = true;
					JOptionPane.showMessageDialog (null, "The state for Member " + values[i] + " " + values[i+1] + " is only 1 letter." + values[i+3] +
									"\nThe letter 'A' has been appended to validate the answer.", "Appended Letter", JOptionPane.INFORMATION_MESSAGE);
				}
				else //If there is no input. Uses a default value.
				{
					JOptionPane.showMessageDialog (null, "The state for Member " + values[i] + " " + values[i+1] + " is blank." +
									"\nThey have automatically been assigned a default value.", "No Input", JOptionPane.INFORMATION_MESSAGE);
					values[i+3] = "TN";
					saveNeeded = true;
				}
				
				//-----------
				
				//This validates the user input of MM/DD/YYYY. 
				SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
				try 
				{
					//TODO EDIT Date.
					//When the user enters numbers out of range or a bad format, will throw an error.
					//This is caught below and an error message is shown.
					dateFormat.parse(values[i+5]);
					int month = Integer.parseInt(values[i+5].substring (0, 2));
					int day = Integer.parseInt(values[i+5].substring (3, 5));
					int year = Integer.parseInt(values[i+5].substring (6));
					
					//Makes sure that months, days, and years are within correct ranges.
					if(month <= 12 && day <= 31 && year >= yearFounded && year <= 2021) 
					{
						dateFormat.parse(values[i+5]);
					}
					else if(year < yearFounded) 
					{
						JOptionPane.showMessageDialog (null, values[i] + " " + values[i+1] + "'s join date has been detected to be before the club's founding date. \n" +
										"Club's Founding Year: " + yearFounded + "\nDate on Record: " + values[i+5] + "\nTheir join date has been set to 01/01/2015 by default.", "Changed Date", JOptionPane.ERROR_MESSAGE);
						
						values[i+5] = "01/01/2015";
						saveNeeded = true;
					}
					else
					{
						JOptionPane.showMessageDialog (null, values[i] + " " + values[i+1] + "'s join date format is invalid." + "\nDate on Record: " + values[i+5] +  "\n\nTheir join date has been set to 01/01/2015 by default.", "Changed Date", JOptionPane.ERROR_MESSAGE);
						
						values[i+5] = "01/01/2015";
						saveNeeded = true;
					}
				}
				catch(ParseException e)
				{
					JOptionPane.showMessageDialog (null, values[i] + " " + values[i+1] + "'s join date format is invalid." + "\nDate on Record: " + values[i+5] +  "\nTheir join date has been set to 01/01/2015 by default.", "Changed Date", JOptionPane.ERROR_MESSAGE);
					
					values[i+5] = "01/01/2015";
					saveNeeded = true;
				}
				addMember (new Member(values[i],values[i+1],values[i+2],values[i+3], values[i+4], values[i+5], values[i+6]));
			}
		}
		inputFile.close ( );
	}
	
	/**
	 * The saveToFile method replaces the old file with a new file.   
	 *
	 * <hr>
	 * Date created: Mar 27, 2021
	 *
	 * <hr>
	 */
	public void saveToFile(String filepath) 
	{
		PrintWriter writer = null;
		//If the user not to populate the club, the text file will alternatively
		//be saved to the clubMembers2 text file.
		try
		{
			writer = new PrintWriter(filepath);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Member member: roster) 
		{
			writer.println(member.getFirstName ( ) + "|" + member.getLastName ( ) + "|" + member.getCity() + "|" + 
		member.getState ( ) + "|" + member.getMemberType ( ) + "|" + member.getDateJoined ( ) + "|" + member.getFilePath ( ));
		}
		
		writer.close();
		saveNeeded = false;
	}
}
