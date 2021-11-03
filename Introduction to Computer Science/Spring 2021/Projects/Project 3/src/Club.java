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
import src.Member;
import src.MemberType;

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
}
