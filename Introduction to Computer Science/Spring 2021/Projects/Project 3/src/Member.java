/**
 * ---------------------------------------------------------------------------
 * File name: Member.java
 * Project name: Project 1
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: February 3, 2021
 * ---------------------------------------------------------------------------
 */

/**
 * The Member class creates a new member of the club
 * with certain attributes tied to the new member.
 *
 * <hr>
 * Date created: February 3, 2021
 * <hr>
 * @author Michael Ng
 */
public class Member
{
	private String firstName;
	private String lastName;
	private String city;
	private String state;
	private String memberType;
	private String dateJoined;
	private String pictureFilePath;
	
	/**
	 * 
	 * The Primary Constructor. 
	 * Allows the user to determine a member's attributes.
	 *
	 * <hr>
	 * Date created: February 3, 2021 
	 *
	 * 
	 * @param lastName
	 * @param firstName
	 * @param city
	 * @param state
	 * @param memberType
	 */
	public Member(String firstName, String lastName, String city, String state, String memberType, String dateJoined, String pictureFilePath) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
		this.memberType = memberType;
		this.dateJoined = dateJoined;
		this.pictureFilePath = pictureFilePath;
	}
	
	/**
	 * @return firstName
	 */
	public String getFirstName ( )
	{
		return firstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName (String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return lastName
	 */
	public String getLastName ( )
	{
		return lastName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName (String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * @return city
	 */
	public String getCity ( )
	{
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity (String city)
	{
		this.city = city;
	}
	
	/**
	 * @return state
	 */
	public String getState ( )
	{
		return state;
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState (String state)
	{
		this.state = state;
	}
	
	/**
	 * @return memberType
	 */
	public String getMemberType ( )
	{
		return memberType;
	}
	
	/**
	 * @param memberType the memberType to set
	 */
	public void setMemberType (String memberType)
	{
		this.memberType = memberType;
	}
	
	/**
	 * @return dateJoined
	 */
	public String getDateJoined ( )
	{
		return dateJoined;
	}
	
	/**
	 * @param dateJoined the dateJoined to set
	 */
	public void setDateJoined (String dateJoined)
	{
		this.dateJoined = dateJoined;
	}

	
	/**
	 * @return filePath
	 */
	public String getFilePath ( )
	{
		return pictureFilePath;
	}

	
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath (String pictureFilePath)
	{
		this.pictureFilePath = pictureFilePath;
	}
	
	/**
	 * 
	 * The toString method for the Member class.
	 * Returns a string detailing the Member's attributes.     
	 *
	 * <hr>
	 * Date created: February 3, 2021 
	 *
	 * <hr>
	 * @return message
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		String message = lastName + ", " + firstName;
		
		message += "\n---------------";
		message += "\nAddress:              " + city + ", " + state;
		message += "\nMember Type:    " + memberType;
		message += "\nDate Joined:       " + dateJoined;
		message += "\nImage File Path: " + pictureFilePath;
		message += "\n---------------\n\n";
		
		return message;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
