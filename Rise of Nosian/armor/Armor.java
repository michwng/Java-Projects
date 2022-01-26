/**
 * ---------------------------------------------------------------------------
 * File name: Armor.java
 * Project name: Rise of Nosian
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Mar 28, 2021
 * ---------------------------------------------------------------------------
 */

package armor;


/**
 * The Base Class for Armor. Will be extended to all armors.
 *
 * <hr>
 * Date created: Mar 28, 2021
 * <hr>
 * @author Michael Ng
 */
public class Armor
{
	private String bodyPart, armorName, description;
	private int defense, weight, luck;
	
	/**
	 * 
	 * The Default Constructor for the Armor class.
	 *
	 * <hr>
	 * Date created: Mar 28, 2021 
	 *
	 * 
	 */
	public Armor() 
	{
		armorName = "Armor";
		description = "";
		bodyPart = BodyPart.Torso.toString ( );
		defense = 0;
		weight = 0;
		luck = 0;
	}
	
	/**
	 * The Parameterized Constructor for the Armor class.
	 *
	 * <hr>
	 * Date created: Mar 28, 2021 
	 *
	 * 
	 * @param bp
	 * @param defense
	 * @param bonusDefense
	 * @param weight
	 * @param luck
	 */
	public Armor(BodyPart bp, String armorName, String description, String modDescription, int defense, int bonusDefense, int weight, int luck) 
	{
		bodyPart = bp.toString ( );
		this.armorName = armorName;
		this.description = description;
		this.defense = defense;
		this.weight = weight;
		this.luck = luck;
	}
	
	
	/**
	 * @return bodyPart
	 */
	public String getBodyPart ( )
	{
		return bodyPart;
	}

	
	/**
	 * @return armorName
	 */
	public String getArmorName ( )
	{
		return armorName;
	}

	/**
	 * Returns the armor's description.
	 * 
	 * @return description
	 */
	public String getArmorDescription()
	{
		return description;
	}

	/**
	 * Return the defense stat of the armor.
	 * 
	 * @return defense
	 */
	public int getDefense()
	{
		return defense;
	}

	
	/**
	 * @return weight
	 */
	public int getWeight ( )
	{
		return weight;
	}

	
	/**
	 * @return luck
	 */
	public int getLuck ( )
	{
		return luck;
	}

	/**
	 * The toString method of the Armor class.
	 * Returns a description of the armor.   
	 *
	 * <hr>
	 * Date created: Mar 28, 2021 
	 *
	 * <hr>
	 * @return message
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		String message = "";
		
		message += armorName;
		message += "\n---------------";
		message += "\nEquipped on: " + bodyPart;
		message += "\nDefense: " + defense;
		message += "\nWeight: " + weight + "lbs" + " | Luck: " + luck;
		message += "\nDescription: " + description;
		
		return message;
	}
	
}
