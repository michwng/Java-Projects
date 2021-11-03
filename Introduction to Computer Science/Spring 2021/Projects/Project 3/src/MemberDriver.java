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
import src.Member;
import src.MemberType;
import java.util.ArrayList;
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
		String clubName, username;
		int yearFounded;

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
		
		String test = (String) JOptionPane.showInputDialog (null, "When was this club founded?", 
			"Club Founding Year", JOptionPane.DEFAULT_OPTION, null, null, "1990");
		//Test cannot be above any year after 2015 (exclusive).
		//This is added to allow the preset members to be within the club's date scope.
		if(Integer.parseInt(test) >  2015) 
		{
			boolean invalid = true;
			while(invalid) 
			{
				//This 
				JOptionPane.showMessageDialog (null, "Sorry, but this club must be at least 6 years old.\n(This club would be " + (2021 - Integer.parseInt(test)) + " years old.)\n\nPlease try again.");
				
				test = (String) JOptionPane.showInputDialog (null, "When was this club founded?", 
					"Club Founding Year", JOptionPane.DEFAULT_OPTION, null, null, "1990");
				
				if(Integer.parseInt(test) <= 2015) 
				{
					invalid = false;
				}
				//if the user does not input a year, sets the string to "1969".
				else if(test == null || test.isBlank()) 
				{
					test = "1990";
				}
			}
		}
		
		yearFounded = Integer.parseInt (test);
		Club roster = new Club(clubName, username, yearFounded);
		
		//Some preset members are added so testing can be expedited. 
		//All members vary in membertype and last name.
		roster.addMember(new Member("Marnie", "Jennings", "Johnson City", "TN", String.valueOf (MemberType.STUDENT), "02/17/2018", "MarnieJennings.jpg"));
		roster.addMember(new Member("Bob", "Terry", "Knoxville", "TN", String.valueOf (MemberType.SENIOR), "04/07/2020", "BobJenson.jpg"));
		roster.addMember(new Member("Byleth", "Eisner", "Garreg Mach", "FN", String.valueOf (MemberType.OFFICER), "10/20/2015", "BylethEisner.jpg"));
		roster.addMember(new Member("Velvet", "Crowe", "Aball", "BR", String.valueOf (MemberType.PROVISIONAL), "05/12/2020", "VelvetCrowe.jpg"));
		roster.addMember(new Member("Alm", "Rudolf", "Zofia", "VL", String.valueOf (MemberType.LIFETIME), "09/30/2017", "AlmRudolf.jpg"));
		roster.addMember(new Member("Chrom", "Ylisse", "Archanea", "YL", String.valueOf (MemberType.JUNIOR), "07/21/2019", "ChromYlisse.jpg"));
		
		
		//This is defined outside the while loop
		int optionChosen = 0;
		
		//The program repeats until X or 'end the program' is chosen.
		while(optionChosen != 5 && optionChosen != -1) 
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
					
					
					String dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2000");
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
									
									dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2000");
								}
								else 
								{
									JOptionPane.showMessageDialog (null, "Sorry, this is an invalid date format. \nPlease try again.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
									dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2000");
									if(dateJoined == null) 
									{
										break;
									}
								}
							}
							catch(ParseException e)
							{
								JOptionPane.showMessageDialog (null, "Sorry, this is an invalid date format. \nPlease try again.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
								dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2000");
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
						dateJoined = "01/01/2000";
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
						JOptionPane.showMessageDialog (null, roster.toString(), "Member Attributes", JOptionPane.INFORMATION_MESSAGE);
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
						//Sort by years of membership
						else if(choice == 1) 
						{
							int month, day, year, month2, day2, year2;
							
							
							ArrayList<Member> newRoster = new ArrayList<Member>();
							newRoster = roster.getRoster();
							
							for(int j = 0; j < roster.getRosterSize ( ); j++) 
							{
								for(int i = 0; i < roster.getRosterSize ( )-1; i++) 
								{
									//MM/DD/YYYY
									month = Integer.parseInt(newRoster.get (i).getDateJoined ( ).substring (0, 2));
									day = Integer.parseInt(newRoster.get (i).getDateJoined ( ).substring (3, 5));
									year = Integer.parseInt(newRoster.get (i).getDateJoined ( ).substring (6));

									month2 = Integer.parseInt(newRoster.get (i+1).getDateJoined ( ).substring (0, 2));
									day2 = Integer.parseInt(newRoster.get (i+1).getDateJoined ( ).substring (3, 5));
									year2 = Integer.parseInt(newRoster.get (i+1).getDateJoined ( ).substring (6));
									
									
									if(year >= year2) 
									{
										//swaps i and i+1.
										Member temp = newRoster.get(i);
										newRoster.set(i, newRoster.get(i+1));
										newRoster.set(i+1, temp);
									}
									else if(year == year2 && month >= month2) 
									{
										//swaps i and i+1.
										Member temp = newRoster.get(i);
										newRoster.set(i, newRoster.get(i+1));
										newRoster.set(i+1, temp);
									}
									else if(year == year2 && month == month2 && day > day2) 
									{
										//swaps i and i+1.
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
							members[roster.getRosterSize ( )] = "Cancel";
							
							int pick = JOptionPane.showOptionDialog (null, "Which member would you like to edit?", "Member to Edit", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, members, members[0]);
							
							if(pick == roster.getRosterSize()) 
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
									//First Name
									case 0:
										String firstName = (String) JOptionPane.showInputDialog (null, "Old First Name: " + roster.getMember (pick).getFirstName() + "\nPlease input the member's new first name.", "New First Name", JOptionPane.DEFAULT_OPTION, null, null, roster.getMember (pick).getFirstName ( ));
										if(firstName != null) 
										{
											roster.getMember (pick).setFirstName (firstName);	
										}
										break;
									//Last Name
									case 1:
										String lastName = (String) JOptionPane.showInputDialog (null, "Old Last Name: " + roster.getMember (pick).getLastName() + "\nPlease input the member's new last name.", "New Last Name", JOptionPane.DEFAULT_OPTION, null, null, roster.getMember (pick).getLastName ( ));
										if(lastName != null) 
										{
											roster.getMember (pick).setLastName (lastName);
										}
										break;
									//City
									case 2:
										String city = (String) JOptionPane.showInputDialog (null, "Old City: " + roster.getMember (pick).getCity() + "\nPlease input the member's new City.", "New City", JOptionPane.DEFAULT_OPTION, null, null, roster.getMember (pick).getCity ( ));
										if(city != null) 
										{
											roster.getMember (pick).setCity (city);
										}
										break;
									//State
									case 3:
										String state = (String) JOptionPane.showInputDialog (null, "Old State: " + roster.getMember (pick).getState() + "\nPlease input the member's new State.", "New State", JOptionPane.DEFAULT_OPTION, null, null, roster.getMember (pick).getState ( ));
										if(state != null) 
										{
											roster.getMember (pick).setState (state);
										}
										break;
									//Member Type
									case 4:
										String[] options1 = {MemberType.PROVISIONAL.toString(), MemberType.STUDENT.toString(), MemberType.JUNIOR.toString(), MemberType.SENIOR.toString(), MemberType.LIFETIME.toString(), MemberType.OFFICER.toString()};		
										int optionNum = JOptionPane.showOptionDialog(null, "Old Classification: " + roster.getMember (pick).getMemberType ( ) + "\nWhat is the member's new classification?", "Member's Classification", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options1, options1[0]);
										String memberType = options1[optionNum];
										roster.getMember (pick).setMemberType (memberType);
										break;
									//Date Joined
									case 5:
										String dateJoined = (String) JOptionPane.showInputDialog(null, "Old Date: " + roster.getMember(pick).getDateJoined ( ) + "\nWhen did the member [actually] join the club?\nEnter as mm/dd/yyyy.", "New Date Joined", JOptionPane.QUESTION_MESSAGE, null, null, "01/01/2000");
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
													}
													else
													{
														JOptionPane.showMessageDialog (null, "Sorry, this is an invalid date format. \nPlease try again.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
														dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2000");
														if(dateJoined == null) 
														{
															break;
														}
													}
												}
												catch(ParseException e)
												{
													JOptionPane.showMessageDialog (null, "Sorry, this is an invalid date format. \nPlease try again.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
													dateJoined = (String) JOptionPane.showInputDialog(null, "When did the member first join the club?\nEnter as mm/dd/yyyy.", "Member's Date Joined", JOptionPane.INFORMATION_MESSAGE, null, null, "01/01/2000");
													if(dateJoined == null) 
													{
														break;
													}
												}
											}
										}
										break;
									//Picture File Path
									case 6:
										String filepath = (String)JOptionPane.showInputDialog (null, "Old Picture File Path: " + roster.getMember (pick).getFilePath ( ) + "\nPlease input the member's new picture file path.", "New Picture File Path", JOptionPane.DEFAULT_OPTION, null, null, roster.getMember (pick).getFirstName ( ) + roster.getMember (pick).getLastName ( ) + ".jpg");
										if(filepath != null) 
										{
											roster.getMember (pick).setFilePath (filepath);
										}
										break;
									//Cancel
									case 7:
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
						
						int pick = JOptionPane.showOptionDialog (null, "Which member would you like to delete?", "Member to Deleete", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, members, members[0]);
						
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
				JOptionPane.showMessageDialog (null, "Thank you, " + username + ", for using Club Membership Roster!", "Thank you!", JOptionPane.DEFAULT_OPTION);
				System.exit(0);
			}
		}
	}
}
