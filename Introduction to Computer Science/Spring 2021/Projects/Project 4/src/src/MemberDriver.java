package src;

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

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The MemberDriver class allows the user to create and view club members 
 * through a combination of button input and typing input.
 *
 * <hr>
 * Date created: February 3, 2021
 * Last Updated: March 4, 2021
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
	 * Last Updated: March 4, 2021
	 * <hr>
	 * @param args
	 */
	public static void main(String [] args)
	{
		//boolean saveNeeded = false;
		String clubName, username;
		int yearFounded;
		File memberFile = null;
		boolean validFile = false;

		username = (String) JOptionPane.showInputDialog (null, "Welcome to Club Membership Roster! " +
						"\nThis program keeps track of all members for the club." +
						"\n\nWhat is your name?", "Welcome!", JOptionPane.DEFAULT_OPTION, null, null, "Username");
		
		//if the user does not input a name at all, sets the name to "Username".
		if(username == null || username.isBlank()) 
		{
			username = "Username";
		}
		
		clubName = (String) JOptionPane.showInputDialog (null, "What is the name of the club?", 
			"Club Name", JOptionPane.DEFAULT_OPTION, null, null, "Kool Kidz");
		//if the user does not input a clubname at all, sets the name to "Kool Kidz".
		if(clubName == null || clubName.isBlank()) 
		{
			clubName = "Kool Kidz";
		}
		
		//Test cannot be above any year after 2015 (exclusive).
		//This is added so the program does not throw an error when "Cancel" is chosen.
		boolean invalid = true;
		String test = "1990";
		while(invalid) 
		{
			test = (String) JOptionPane.showInputDialog (null, "What year was this club founded?", 
				"Club Founding Year", JOptionPane.DEFAULT_OPTION, null, null, "1990");
			try 
			{
				//Test
				if(test != null) 
				{
					//This is added to allow the preset members to be within the club's date scope (0-2015 (inclusive) are acceptable values).
					if(Integer.parseInt(test) >  2015 || Integer.parseInt(test) < 0) 
					{
						//The user inputs a year past 2015. 2015 is a valid value.
						if(Integer.parseInt(test) >  2015) 
						{
							//The club must be at least 6 years old to allow lenience for the Date Joined for each pre-existing member.
							JOptionPane.showMessageDialog (null, "Sorry, but this club must be at least 6 years old.\n(This club would be " + (2021 - Integer.parseInt(test)) + " years old.)\n\nPlease try again.");
						}
						else //If the user inputs a negative number
						{
							//No negative years allowed!
							JOptionPane.showMessageDialog (null, "Sorry, but the year " + test + " is negative!\n\nPlease input a positive number.", "Error: Negative Year", JOptionPane.ERROR_MESSAGE);
						}
					}
					else //The input is valid.
					{
						invalid = false;
					}
				}
				else //If test is null. Sets yearFounded to a default value: 1990.
				{
					test = "1990";
					invalid = false;
				}
			}
			catch(NumberFormatException e) //This exception should be sent from the if(test != null) statement.
			{
				JOptionPane.showMessageDialog (null, "Sorry, this is not a valid number.\nPlease try again.", "Invalid Number", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		yearFounded = Integer.parseInt (test);
		Club roster = new Club(clubName, username, yearFounded);
		
		//Present members previously included here have been moved to the text file.
		//All members vary in Date Joined and Last Name.
		
		//This section of code 'imports' members into the driver class from the member text file.
		//The user has the choice to start a brand new club or import members.
		int memberChoice = JOptionPane.showConfirmDialog (null, "Would you like to import existing members?", "Import old members?", JOptionPane.YES_NO_OPTION);
		
		while(!validFile && memberChoice == 0) //While 
		{
			try 
			{
				memberFile = new File("src\\ClubData\\clubMembers");
				Scanner validator = new Scanner(memberFile);
				validator.close ( );
				validFile = true;
			}
			catch(FileNotFoundException e) 
			{
				JOptionPane.showMessageDialog(null,"The selected file was not found.\n" + e.getMessage ( ) + "\nPlease manually select the text file.", "Error: File Not Found", JOptionPane.ERROR_MESSAGE);
				
				//Fun Fact: JFileChooser can 'open' any file type and pass it onto the next while loop, with no error thrown.
				//However, it usually ends up with no data imported.
				JFileChooser pickFile = new JFileChooser("src\\ClubData");
				int pickFileChoice = pickFile.showOpenDialog(null);
				if(pickFileChoice == JFileChooser.APPROVE_OPTION) 
				{
					memberFile = pickFile.getSelectedFile ( );
					try 
					{
						Scanner validator = new Scanner(memberFile);
						validator.close ( );
						validFile = true;
					}
					catch(FileNotFoundException fnfe) 
					{
						JOptionPane.showMessageDialog(null,"The selected file was not found.\n" + fnfe.getMessage ( ) + "\nPlease manually select the text file.", "Error: File Not Found", JOptionPane.ERROR_MESSAGE);
					}
				}
				else //if the user chose cancel.
				{
					int confirm = JOptionPane.showConfirmDialog (null, "Are you sure you want to Cancel?\nExisting members will not be imported.", "Confirmation", JOptionPane.YES_NO_OPTION);
					if(confirm == 0) //The user chose "Yes". If they chose No, the while loop sends them back to find the file.
					{
						memberChoice = 1; //Changing this allows the user to leave the while loop.
					}
				}
			}
		}
		
		//Once the textfile has been validated... (Skips if the user choses not to import one)
		if(validFile) 
		{
			roster.fillFromFile (memberFile.getPath ( ));
		}
		
		//This is defined outside the while loop
		int optionChosen = 0;
		
		//The program repeats until X or 'end the program' is chosen.
		while(true) 
		{
			String[] options = {"Create a New Member", "Display Members", "Find a Member", "Edit Members", "About " + clubName, "End the Program"};
			optionChosen = JOptionPane.showOptionDialog (null, "Welcome, President " + username + 
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
					if(optionNum == -1) 
					{
						break;
					}
					String memberType = options1[optionNum];
					
					
					String dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2015");
					//If the user enters nothing, skips validation (and substitutes a valid date).
					if(dateJoined == null) 
					{
						break;
					}
					else if(!dateJoined.isBlank())
					{
						//This validates the user input of MM/DD/YYYY. 
						boolean wrong = true;
						while(wrong) 
						{
							SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
							try 
							{
								//When the user enters numbers out of range or a bad format, will throw an error.
								//This is caught below and an error message is shown.
								dateFormat.parse(dateJoined);
								int month = Integer.parseInt(dateJoined.substring (0, 2));
								int day = Integer.parseInt(dateJoined.substring (3, 5));
								int year = Integer.parseInt(dateJoined.substring (6));
								
								//Makes sure that months, days, and years are within correct ranges
								if(month <= 12 && month > 0 && day <= 31 && day > 0 && year >= yearFounded && year > 0 && year <= 2021) 
								{
									dateFormat.parse(dateJoined);
									wrong = false;
								}
								else if(year < yearFounded) 
								{
									JOptionPane.showMessageDialog (null, "Sorry, but this date is before the founding of the club. \n" +
													"Club's Founding Year: " + yearFounded + "\n\nPlease input a number after this date.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
									
									dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2015");
								}
								else 
								{
									JOptionPane.showMessageDialog (null, "Sorry, this is an invalid date format. \nPlease try again.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
									dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2015");
									if(dateJoined == null) 
									{
										break;
									}
								}
							}
							catch(ParseException e)
							{
								JOptionPane.showMessageDialog (null, "Sorry, this is an invalid date format. \nPlease try again.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
								dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2015");
								if(dateJoined == null) 
								{
									break;
								}
							}
						}
					}
					
					String pictureFilePath = (String) JOptionPane.showInputDialog(null, "What is the filepath to the member's photo?", "Member's Picture File Path", JOptionPane.INFORMATION_MESSAGE, null, null, firstName + lastName + ".jpg");
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
						dateJoined = "01/01/2015";
					}
					if(pictureFilePath.equals(".jpg") || pictureFilePath.isBlank())
					{
						pictureFilePath = firstName + lastName + ".jpg";
					}
					
					//These are added to capitalize each person's name.
					firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
					lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
					
					
					roster.addMember(new Member(firstName, lastName, city, state, memberType, dateJoined, pictureFilePath));
					JOptionPane.showMessageDialog(null, "This member has successfully been created!", "Created Member", JOptionPane.INFORMATION_MESSAGE);	
					roster.setSaveNeeded (true);
					break;
				}
			}
			//If the user selects "Display Members"
			else if(optionChosen == 1) 
			{
				//If a Member has already been created
				if(roster.getRosterSize() >= 1)
				{
					String[] options2 = {"Display All Members", "Display Members by Member Type"};
					int choice = JOptionPane.showOptionDialog(null, "Please choose a display option.", "Choose Option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
					
					//if the use chooses "Display all members"
					if(choice == 0) 
					{
						//this int variable calculates the total amount of pages needed. 
						//5 members show per page.
						int pages = roster.getRosterSize ( )/5;
						//fencepost - adds a page that contains the remaining members in the roster arraylist.
						if(roster.getRosterSize() % 5 != 0) 
						{
							pages++;
						}
						//TODO Partition into pages.
						
						boolean leave = false;
						int currentPage = 1;
						while(!leave) 
						{
							int pick;
							String[] onePageOptions = {"Main Menu"};
							String[] beginningOptions = {"Main Menu", "Next Page"};
							String[] traverseOptions = {"Previous Page", "Next Page", "Main Menu"};
							String[] lastPageOptions = {"Previous Page", "Main Menu"};
							
							//if this is the first page and there are more than 5 members.
							if(currentPage == 1 && roster.getRosterSize ( ) > 5) //Shows the Main Menu and Next Page button.
							{
								//curentPage is subtracted by 1 so the first page shows the first 5 members.
								pick = JOptionPane.showOptionDialog (null, roster.toString((currentPage-1)*5), "Member Attributes", JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, beginningOptions, beginningOptions[1]);
								if(pick == 0) //user chooses main menu.
								{
									leave = true;
								}
								else // user chooses next page
								{
									currentPage++;
								}
							}
							else if(currentPage == 1) //If this is the first page and there are 5 or less members.
							{
								JOptionPane.showOptionDialog (null, roster.toString((currentPage-1)*5), "Member Attributes", JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, onePageOptions, onePageOptions[0]);
								leave = true;
							}
							else if(currentPage > 0 && currentPage < pages) //This is a middle page. Shows the Previous Page, Next Page, and Main Menu Button.
							{
								pick = JOptionPane.showOptionDialog (null, roster.toString((currentPage-1)*5), "Member Attributes", JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, traverseOptions, traverseOptions[1]);
								if(pick == 0) //user chooses previous page.
								{
									currentPage--;
								}
								else if(pick == 1) // user chooses next page
								{
									currentPage++;
								}
								else //user chooses main menu.
								{
									leave = true;
								}
							}
							else //if currentpage is equal to the amount of pages. Shows Previous Page and Main Menu.
							{
								pick = JOptionPane.showOptionDialog (null, roster.toString((currentPage-1)*5), "Member Attributes", JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, lastPageOptions, lastPageOptions[1]);
								if(pick == 0) //user chooses previous page.
								{
									currentPage--;
								}
								else // user chooses main menu.
								{
									leave = true;
								}
							}
						}
					}
					//if the user chooses "Display Members by Member Type"
					else if(choice == 1) 
					{
						String[] options3 = {String.valueOf(MemberType.PROVISIONAL), String.valueOf(MemberType.STUDENT), 
										String.valueOf(MemberType.JUNIOR), String.valueOf(MemberType.SENIOR), 
										String.valueOf(MemberType.LIFETIME), String.valueOf(MemberType.OFFICER)};
						choice = JOptionPane.showOptionDialog (null, "Show members of what type?", "Member Type", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);
						
						//depending on what the user chooses, displays all members of that type.
						switch(choice) 
						{
							case 0:
								//These if statements make sure the string isn't empty before displaying.
								//If they are empty, an error message is displayed.
								if(!roster.getMemberInformation (MemberType.PROVISIONAL).isBlank ( )) 
								{
									JOptionPane.showMessageDialog (null, roster.getMemberInformation (MemberType.PROVISIONAL), "Provisional Members", JOptionPane.INFORMATION_MESSAGE);
								}
								else 
								{
									JOptionPane.showMessageDialog(null, "Sorry, there are currently no members that are Provisionals.", "No Provisional Members", JOptionPane.ERROR_MESSAGE);
								}
								break;
							case 1:
								if(!roster.getMemberInformation (MemberType.STUDENT).isBlank ( )) 
								{
									JOptionPane.showMessageDialog (null, roster.getMemberInformation (MemberType.STUDENT), "Student Members", JOptionPane.INFORMATION_MESSAGE);
								}
								else 
								{
									JOptionPane.showMessageDialog(null, "Sorry, there are currently no members that are Students.", "No Student Members", JOptionPane.ERROR_MESSAGE);
								}
								break;
							case 2:
								if(!roster.getMemberInformation (MemberType.JUNIOR).isBlank ( )) 
								{
									JOptionPane.showMessageDialog (null, roster.getMemberInformation (MemberType.JUNIOR), "Junior Members", JOptionPane.INFORMATION_MESSAGE);
								}
								else 
								{
									JOptionPane.showMessageDialog(null, "Sorry, there are currently no members that are Juniors.", "No Junior Members", JOptionPane.ERROR_MESSAGE);
								}
								break;
							case 3:
								if(!roster.getMemberInformation (MemberType.SENIOR).isBlank ( )) 
								{
									JOptionPane.showMessageDialog (null, roster.getMemberInformation (MemberType.SENIOR), "Senior Members", JOptionPane.INFORMATION_MESSAGE);
								}
								else 
								{
									JOptionPane.showMessageDialog(null, "Sorry, there are currently no members that are Seniors.", "No Senior Members", JOptionPane.ERROR_MESSAGE);
								}
								break;
							case 4:
								if(!roster.getMemberInformation (MemberType.LIFETIME).isBlank ( )) 
								{
									JOptionPane.showMessageDialog (null, roster.getMemberInformation (MemberType.LIFETIME), "Lifetime Members", JOptionPane.INFORMATION_MESSAGE);
								}
								else 
								{
									JOptionPane.showMessageDialog(null, "Sorry, there are currently no members that have Lifetime status.", "No Lifetime Members", JOptionPane.ERROR_MESSAGE);
								}
								break;
							case 5:
								if(!roster.getMemberInformation (MemberType.OFFICER).isBlank ( )) 
								{
									JOptionPane.showMessageDialog (null, roster.getMemberInformation (MemberType.OFFICER), "Officer Members", JOptionPane.INFORMATION_MESSAGE);
								}
								else 
								{
									JOptionPane.showMessageDialog(null, "Sorry, there are currently no members that are Officers.", "No Officer Members", JOptionPane.ERROR_MESSAGE);
								}
								break;
							default:
								break;
						}
					}
				}
				else //if the member has not been created
				{
					JOptionPane.showMessageDialog(null, "Sorry, there are currently no members. \nPlease create a new member!", "No Members", JOptionPane.ERROR_MESSAGE);
				}
			}
			//If the user selects "Find a Member"
			else if(optionChosen == 2) 
			{
				if(roster.getRosterSize ( ) > 0) 
				{
					//This asks the user for the first and last name of the person they want to check.						
					String firstName = JOptionPane.showInputDialog (null, "What is the person's first name?", "First Name", JOptionPane.QUESTION_MESSAGE);
					if(firstName != null) 
					{
						String lastName = JOptionPane.showInputDialog (null, "What is the person's last name?", "Last Name", JOptionPane.QUESTION_MESSAGE);
						if(lastName != null) 
						{
							//If the person was found...
							if(!roster.getMemberInformation (firstName.trim(), lastName.trim()).isBlank ()) 
							{																//Trim removes any spaces before or after the starting/ending letter.
								JOptionPane.showMessageDialog (null, roster.getMemberInformation (firstName.trim(), lastName.trim()), "Specified Member Information", JOptionPane.INFORMATION_MESSAGE);
							}
							else //display an error message
							{
								JOptionPane.showMessageDialog(null, "Sorry, but the member was not found." +
												"\nPlease validate that your input does not contain misspelling or a non-member.\n" +
												"--------------\nFirst Name: \"" + firstName + "\"\nLast Name: \"" + lastName + "\"");
							}
						}
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Sorry, there are currently no members. \nPlease create a new member!", "No Members", JOptionPane.ERROR_MESSAGE);
				}
			}
			//If the user selects "Edit members".
			//This features nesting so buttons do not all end up on the menu screen.
			else if(optionChosen == 3) 
			{
				//This makes sure the user has already created a member.
				if(roster.getRosterSize ( ) > 0) 
				{
					//Like the one above, options2's scope is confined to the if statement.
					String[] options2 = {"Sort Members", "Edit a Member", "Drop a Member", "Back"};
					int choice = JOptionPane.showOptionDialog (null, "Welcome, President " + username + 
										"!\n\nPlease Select An Option.\n-----------------------------------", 
										"Edit Members", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, 
										null, options2, options2[0]);
					
					//TODO complete task
					//user selects "Sort Members"
					if(choice == 0) 
					{
						String[] options3 = {"Sort by Name", "Sort by Years of Membership"};
						choice = JOptionPane.showOptionDialog (null, "How should the members be sorted?", "Sort Method", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);
						
						//Sort by name
						if(choice == 0) 
						{
							ArrayList<Member> newRoster = new ArrayList<Member>();
							newRoster = roster.getRoster();
							
							//This is looped multiple times to make sure each object moves until they are in the right position.
							for(int j = 0; j < roster.getRosterSize ( ); j++) 
							{
								for(int i = 0; i < roster.getRosterSize ( )-1; i++) 
								{
									//This statement checks if the object at i+1 is closer to the letter a than i is closer to a.
									if(newRoster.get (i).getLastName().compareTo (newRoster.get(i+1).getLastName()) >= 1) 
									{
										//swaps i and i+1.
										Member temp = newRoster.get(i);
										newRoster.set(i, newRoster.get(i+1));
										newRoster.set(i+1, temp);
									}
								}
							}
							
							//An alternative sorting method.
							//newRoster.sort ((o1, o2) -> o1.getLastName ( ).compareTo (o2.getLastName ( )));
							JOptionPane.showMessageDialog (null, "All members have been successfully sorted!\n(Sorted with Last Name in Alphabetical Order)", "Sorting Success", JOptionPane.INFORMATION_MESSAGE);
						}
						//Sort by years of membership.
						//Keep in mind that doing this also changes the order in the roster ArrayList and the save file.
						else if(choice == 1) 
						{
							int month, day, year, month2, day2, year2;
							
							ArrayList<Member> newRoster = new ArrayList<Member>();
							newRoster = roster.getRoster();
							
							//How many times the sorting happens. Makes sure all members are in the right positions.
							for(int j = 0; j < roster.getRosterSize ( ); j++) 
							{
								//This for-loop goes through the entire roster.
								for(int i = 0; i < roster.getRosterSize ( )-1; i++) 
								{
									//MM/DD/YYYY
									//Member 1
									month = Integer.parseInt(newRoster.get (i).getDateJoined ( ).substring (0, 2));
									day = Integer.parseInt(newRoster.get (i).getDateJoined ( ).substring (3, 5));
									year = Integer.parseInt(newRoster.get (i).getDateJoined ( ).substring (6));

									
									//Member 2
									month2 = Integer.parseInt(newRoster.get (i+1).getDateJoined ( ).substring (0, 2));
									day2 = Integer.parseInt(newRoster.get (i+1).getDateJoined ( ).substring (3, 5));
									year2 = Integer.parseInt(newRoster.get (i+1).getDateJoined ( ).substring (6));
									
									
									if(year > year2) 
									{
										//swaps Member 1 and 2 if Member 1 joined earlier.
										Member temp = newRoster.get(i);
										newRoster.set(i, newRoster.get(i+1));
										newRoster.set(i+1, temp);
									}
									else if(year == year2 && month > month2) 
									{
										//swaps Member 1 and 2 if Member 1 joined earlier in the same year.
										Member temp = newRoster.get(i);
										newRoster.set(i, newRoster.get(i+1));
										newRoster.set(i+1, temp);
									}
									else if(year == year2 && month == month2 && day > day2) 
									{
										//swaps Member 1 and 2 if Member 1 joined earlier in the same month and year.
										Member temp = newRoster.get(i);
										newRoster.set(i, newRoster.get(i+1));
										newRoster.set(i+1, temp);
									}
								}
							}
							JOptionPane.showMessageDialog (null, "All members have been successfully sorted!\n(Sorted with longest membership length first)", "Sorting Success", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					//The user selects "Edit a Member"
					else if(choice == 1) 
					{
						boolean keepEditing = true;
						boolean editMember = true;
						while(keepEditing)
						{
							String[] members = new String[roster.getRosterSize ( )+1];
							//This array puts the names of each member into the members string array.
							for(int i = 0; i < roster.getRosterSize ( ); i++) 
							{
								members[i] = roster.getMember(i).getFirstName() + " " + roster.getMember(i).getLastName();
							}
							//The last option on the list is Cancel.
							members[roster.getRosterSize ( )] = "Cancel";
							
							int pick = JOptionPane.showOptionDialog (null, "Which member would you like to edit?", "Member to Edit", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, members, members[0]);
							
							if(pick == roster.getRosterSize() || pick == -1) //The user picks "Cancel" or the X.
							{
								keepEditing = false;
								editMember = false;
							}
							while(editMember)
							{
								String[] picks = {"First Name", "Last Name", "City", "State", "Member Type", "Date Joined", "Picture", "Cancel"};
								int editChoice = JOptionPane.showOptionDialog (null, roster.getMember(pick) + "What would you like to edit?", "Edit Member", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, picks, picks[0]);
								
								switch(editChoice) 
								{
									//User selects First Name
									case 0:
										String firstName = (String) JOptionPane.showInputDialog (null, "Old First Name: " + roster.getMember (pick).getFirstName() + "\nPlease input the member's new first name.", "New First Name", JOptionPane.DEFAULT_OPTION, null, null, roster.getMember (pick).getFirstName ( ));
										if(firstName != null) //The user did not press "Cancel"
										{
											roster.getMember (pick).setFirstName (firstName);	
											roster.setSaveNeeded (true);
										} 
										break;
									//User selects Last Name
									case 1:
										String lastName = (String) JOptionPane.showInputDialog (null, "Old Last Name: " + roster.getMember (pick).getLastName() + "\nPlease input the member's new last name.", "New Last Name", JOptionPane.DEFAULT_OPTION, null, null, roster.getMember (pick).getLastName ( ));
										if(lastName != null) //The user did not press "Cancel"
										{
											roster.getMember (pick).setLastName (lastName);
											roster.setSaveNeeded (true);
										}
										roster.setSaveNeeded (true);
										break;
									//User selects City
									case 2:
										String city = (String) JOptionPane.showInputDialog (null, "Old City: " + roster.getMember (pick).getCity() + "\nPlease input the member's new City.", "New City", JOptionPane.DEFAULT_OPTION, null, null, roster.getMember (pick).getCity ( ));
										if(city != null) //The user did not press "Cancel"
										{
											roster.getMember (pick).setCity (city);
											roster.setSaveNeeded (true);
										} 
										break;
									//User selects State
									case 3:
										String state = (String) JOptionPane.showInputDialog (null, "Old State: " + roster.getMember (pick).getState() + "\nPlease input the member's new State.", "New State", JOptionPane.DEFAULT_OPTION, null, null, roster.getMember (pick).getState ( ));
										if(state != null) //The user did not press "Cancel"
										{
											int temp = state.length ( );
											if(temp > 2) //In other words, is not a value like TN, VA, or other 2-character state.
											{
												JOptionPane.showMessageDialog (null, "The state for " + roster.getMember (pick).getFirstName() + " " + roster.getMember (pick).getLastName() + " is invalid. " +
																"\nThe first 2 letters typed in for their state will be taken.");
												//This takes the first 2 letters of the input and capitalizes them.
												state = state.substring (0, 2).toUpperCase ( );
												roster.setSaveNeeded (true);
											}
											else if(temp == 1) //The value is only 1 letter long. (This appends the letter 'A' to the answer.)
											{
												JOptionPane.showMessageDialog (null, "The state for " + roster.getMember (pick).getFirstName() + " " + roster.getMember (pick).getLastName() + " is only 1 letter." +
																"\nThe letter 'A' has been appended to validate the answer.");
												state = state.concat("A");
												roster.setSaveNeeded (true);
											}
											roster.getMember (pick).setState (state);
										}
										break;
									//User selects Member Type
									case 4:
										String[] options1 = {MemberType.PROVISIONAL.toString(), MemberType.STUDENT.toString(), MemberType.JUNIOR.toString(), MemberType.SENIOR.toString(), MemberType.LIFETIME.toString(), MemberType.OFFICER.toString()};		
										int optionNum = JOptionPane.showOptionDialog(null, "Old Classification: " + roster.getMember (pick).getMemberType ( ) + "\nWhat is the member's new classification?", "Member's Classification", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options1, options1[0]);
										if(optionNum != -1) //The user does not press X.
										{
											String memberType = options1[optionNum];
											roster.getMember (pick).setMemberType (memberType);
											roster.setSaveNeeded (true);
										}
										break;
									//User selects Date Joined
									case 5:
										String dateJoined = (String) JOptionPane.showInputDialog(null, "Old Date: " + roster.getMember(pick).getDateJoined ( ) + "\nWhen did the member [actually] join the club?\nEnter as mm/dd/yyyy.", "New Date Joined", JOptionPane.QUESTION_MESSAGE, null, null, "01/01/2015");
										//If the user enters nothing, skips validation (and substitutes a valid date).
										if(dateJoined == null) 
										{
											break;
										}
										else 
										{
											//This validates the user input of MM/DD/YYYY. 
											boolean wrong = true;
											while(wrong) 
											{
												SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
												try 
												{
													//When the user enters numbers out of range or a bad format, will throw an error.
													//This is caught below and an error message is shown.
													dateFormat.parse(dateJoined);
													int month = Integer.parseInt(dateJoined.substring (0, 2));
													int day = Integer.parseInt(dateJoined.substring (3, 5));
													int year = Integer.parseInt(dateJoined.substring (6));
													
													//Makes sure that months, days, and years are within correct ranges.
													if(month <= 12 && day <= 31 && year >= yearFounded && year <= 2021) 
													{
														dateFormat.parse(dateJoined);
														wrong = false;
														roster.setSaveNeeded (true);
													}
													else if(year < yearFounded) 
													{
														JOptionPane.showMessageDialog (null, "Sorry, but this date is before the founding of the club. \n" +
																		"Club's Founding Year: " + yearFounded + "\n\nPlease input a number after this date.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
														
														dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2015");
													}
													else
													{
														JOptionPane.showMessageDialog (null, "Sorry, this is an invalid date format. \nPlease try again.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
														dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2015");
														
													}
												}
												catch(ParseException e)
												{
													JOptionPane.showMessageDialog (null, "Sorry, this is an invalid date format. \nPlease try again.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
													dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2015");
												}
												finally 
												{
													if(dateJoined == null) 
													{
														break;
													}
												}
											}
										}
										break;
									//User selects Picture File Path
									case 6:
										String filepath = (String)JOptionPane.showInputDialog (null, "Old Picture File Path: " + roster.getMember (pick).getFilePath ( ) + "\nPlease input the member's new picture file path.", "New Picture File Path", JOptionPane.DEFAULT_OPTION, null, null, roster.getMember (pick).getFirstName ( ) + roster.getMember (pick).getLastName ( ) + ".jpg");
										if(filepath != null) //The user did not press "Cancel"
										{
											roster.getMember (pick).setFilePath (filepath);
											roster.setSaveNeeded (true);
										}
										break;
									//User selects Cancel
									case 7:
										//Returns the user to the main menu.
										editMember = false;
										keepEditing = false;
										break;
									default:
										editMember = false;
										keepEditing = false;
										break;
								}
								if(editMember) 
								{
									String[] choices2 = {"Yes", "No", "Go to Main Menu"};
									//This shows even if the user pressed "Cancel" while editing a member's attributes. This helps the user get to the Main Menu or leave the member quicker.
									int numPick = JOptionPane.showOptionDialog (null, "Change complete!\nWould you like to continue editing " + roster.getMember (pick).getFirstName ( ) + " " + roster.getMember (pick).getLastName ( ) + "?", "Continue Editing", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices2, choices2[1]);
									
									if(numPick == 1) 
									{
										editMember = false;
									}
									if(numPick == 2 || numPick == -1) 
									{
										editMember = false;
										keepEditing = false;
									}
								}
							}
							//Edit member is set to false so the user can exit the while loop for editing one person.
							//It is reset to true here, for the next person to be edited.
							editMember = true;
						}
					}
					//The user selects "Drop a Member"
					else if(choice == 2) 
					{
						String[] members = new String[roster.getRosterSize ( ) + 1];
						//This array puts the names of each member into the members string array.
						for(int i = 0; i < roster.getRosterSize ( ); i++) 
						{
							members[i] = roster.getMember(i).getFirstName() + " " + roster.getMember(i).getLastName();
						}
						members[roster.getRosterSize ( )] = "Cancel";
						
						int pick = JOptionPane.showOptionDialog (null, "Which member would you like to delete?", "Member to Delete", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, members, members[0]);
						
						//if the user does not press "Cancel".
						if(pick != roster.getRosterSize ( )) 
						{
							Member deletedMember = roster.getRoster().remove (pick);
							JOptionPane.showMessageDialog (null, deletedMember.getFirstName ( ) + " " + deletedMember.getLastName ( ) + " has been successfully deleted.");
							System.out.println(deletedMember.getFirstName ( ) + " " + deletedMember.getLastName ( ) + " has been successfully deleted.");
						}
					}
					//When the user selects X or back.
					else
					{
						//Does nothing. Return to the Main Menu.
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Sorry, there are currently no members. \nPlease create a new member!", "No Members", JOptionPane.ERROR_MESSAGE);
				}
			}
			//If the user chooses "About [clubName]"
			else if (optionChosen == 4)
			{
				JOptionPane.showMessageDialog(null, roster.aboutClub(), "Club Information", JOptionPane.INFORMATION_MESSAGE);
			}
			//If the user ends the program or presses the X button.
			else
			{
				//If the text document needs to be overwritten to reflect the changes.
				if(roster.saveNeeded()) 
				{
					if(validFile) //validFile confirms that the user (or the program) selected a valid filepath for imported members. 
					{
						int pick = JOptionPane.showConfirmDialog (null, "Would you like to save your changes to the club?", "Save Changes", JOptionPane.YES_NO_CANCEL_OPTION);
						if(pick == 0) //the user chooses yes.
						{
							roster.saveToFile (memberFile.getPath());
							JOptionPane.showMessageDialog (null, "Changes have been saved. \nThank you, " + username + ", for using Club Membership Roster!", "Thank you!", JOptionPane.DEFAULT_OPTION);
							System.exit(0);
						}
						if(pick == 1) //the user chooses no.
						{
							pick = JOptionPane.showConfirmDialog (null, "Are you sure you do not want to save your changes? \nAll changes made in this session will be lost.", "Confirmation", JOptionPane.YES_NO_OPTION);
							if(pick == 0) //The user picks 'yes'. Changes are not saved and the program ends.
							{
								JOptionPane.showMessageDialog (null, "Thank you, " + username + ", for using Club Membership Roster!", "Thank you!", JOptionPane.DEFAULT_OPTION);
								System.exit(0);
							}
							if(pick == 1) //The user picks 'no'. Returns to the Main Menu.
							{
								//Does nothing. Returns to the Main Menu.
							}
						}
						else //the user picks cancel or the X button.
						{
							//Does nothing. Returns to the Main menu.
						}
					}
					else //The user chose to skip importing members while setting up the club. Saves into a separate text file (clubMembers2).
					{
						while(!validFile) 
						{
							//Since the file is unvalidated, it must go through validation here before saving the file.
							try 
							{
								memberFile = new File("src\\ClubData\\clubMembers2");
								Scanner validator = new Scanner(memberFile);
								validator.close ( );
								validFile = true;
							}
							catch(FileNotFoundException e) 
							{
								JOptionPane.showMessageDialog(null,"The save file was not found.\n" + e.getMessage ( ) + "\nPlease manually select the text file.", "Error: File Not Found", JOptionPane.ERROR_MESSAGE);
								
								//Fun Fact: JFileChooser can 'open' any file type and pass it onto the next while loop, with no error thrown.
								//However, it usually ends up with no data imported.
								JFileChooser pickFile = new JFileChooser("src\\ClubData");
								int pickFileChoice = pickFile.showOpenDialog(null);
								if(pickFileChoice == JFileChooser.APPROVE_OPTION) 
								{
									memberFile = pickFile.getSelectedFile ( );
									try 
									{
										Scanner validator = new Scanner(memberFile);
										validator.close ( );
										validFile = true;
									}
									//This is unlikely to happen since the user is choosing a file. However, it is provided just in case.
									catch(FileNotFoundException fnfe) 
									{
										JOptionPane.showMessageDialog(null,"The selected file was not found.\n" + fnfe.getMessage ( ) + "\nPlease manually select the text file.", "Error: File Not Found", JOptionPane.ERROR_MESSAGE);
									}
								}
								else //if the user chose cancel.
								{
									int confirm = JOptionPane.showConfirmDialog (null, "Are you sure you want to Cancel?\nYour changes will not be saved.", "Confirmation", JOptionPane.YES_NO_OPTION);
									if(confirm == 0) //The user chose "Yes". If they chose No, the while loop sends them back to find the file.
									{
										JOptionPane.showMessageDialog (null, "Thank you, " + username + ", for using Club Membership Roster!", "Thank you!", JOptionPane.DEFAULT_OPTION);
										System.exit(0);
									}
								}
							}
						}
						int pick = JOptionPane.showConfirmDialog (null, "Would you like to save your changes to the club?", "Save Changes", JOptionPane.YES_NO_CANCEL_OPTION);
						if(pick == 0) //the user chooses yes.
						{
							roster.saveToFile (memberFile.getPath());
							JOptionPane.showMessageDialog (null, "Changes have been saved. \nThank you, " + username + ", for using Club Membership Roster!", "Thank you!", JOptionPane.DEFAULT_OPTION);
							System.exit(0);
						}
						if(pick == 1) //the user chooses no.
						{
							pick = JOptionPane.showConfirmDialog (null, "Are you sure you do not want to save your changes? \nAll changes made in this session will be lost.", "Confirmation", JOptionPane.YES_NO_OPTION);
							if(pick == 0) //The user picks 'yes'. Changes are not saved and the program ends.
							{
								JOptionPane.showMessageDialog (null, "Thank you, " + username + ", for using Club Membership Roster!", "Thank you!", JOptionPane.DEFAULT_OPTION);
								System.exit(0);
							}
							if(pick == 1) //The user picks 'no'. Returns to the Main Menu.
							{
								//Does nothing. Returns to the Main Menu.
							}
						}
						else //the user picks cancel or the X button.
						{
							//Does nothing. Returns to the Main menu.
						}
					}
				}
				else 
				{
					JOptionPane.showMessageDialog (null, "Thank you, " + username + ", for using Club Membership Roster!", "Thank you!", JOptionPane.DEFAULT_OPTION);
					System.exit(0);
				}		
			}
		}
	}
}
