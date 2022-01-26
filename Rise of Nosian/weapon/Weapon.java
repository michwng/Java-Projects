/**
 * --------------------------------------------------------------------------
 * File name: Weapon.java
 * Project name: Rise of Nosian
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Creation Date: 10/25/2021
 * Completion Date: --/--/2021
 * @version 1.00
 * --------------------------------------------------------------------------
 */

package weapon;


/**
 * The Base Class for a weapon. Will extend to all weapons.
 *
 * <hr>
 * Date created: Mar 28, 2021
 * <hr>
 * @author Michael Ng
 */
public class Weapon
{
	private String weaponName, description;
	private int physicalDamage, magicalDamage, weight;
	private double accuracy;
	
	/**
	 * The default Constructor for the Weapon class.   
	 *
	 * <hr>
	 * Date created: Mar 28, 2021 
	 *
	 *
	 */
	public Weapon() 
	{
		weaponName = "Weapon";
		description = "";
		physicalDamage = 20;
		magicalDamage = 20;
		accuracy = 0.8;
	}
	
	/**
	 * The Parameterized Constructor for the Weapon class.
	 *
	 * <hr>
	 * Date created: Mar 28, 2021 
	 *
	 * 
	 * @param weaponName
	 * @param clipSize
	 * @param damage
	 * @param bonusDamage
	 * @param accuracy
	 * @param bonusAccuracy
	 */
	public Weapon(String weaponName, String description, int physicalDamage, int magicalDamage, int weight, double accuracy) 
	{
		this.weaponName = weaponName;
		this.description = description;
		this.physicalDamage = physicalDamage;
		this.magicalDamage = magicalDamage;
		this.weight = weight;
		this.accuracy = accuracy;
	}

		
	/**
	 * @return weaponName
	 */
	public String getWeaponName ( )
	{
		return weaponName;
	}
	
	/**
	 * @return description
	 */
	public String getDescription ( )
	{
		return description;
	}

	/**
	 * return the amount of physical damage the weapon does.
	 * 
	 * @return damage
	 */
	public int getPATK()
	{
		return physicalDamage;
	}

	/**
	 * return the amount of magical damage the weapon does.
	 * 
	 * @return damage
	 */
	public int getMATK()
	{
		return magicalDamage;
	}
	
	/**
	 * @return weight
	 */
	public int getWeight ( )
	{
		return weight;
	}

	/**
	 * Return the accuracy of the weapon.
	 * 
	 * @return accuracy
	 */
	public double getAccuracy()
	{
		return accuracy;
	}

	
	/**
	 * The toString method of the Weapon class.
	 * Returns a description of the weapon.   
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
			
		message += weaponName;
		message += "\n---------------";
		message += "\nDamage: " + physicalDamage + "PDMG / " + magicalDamage + "MDMG";
		message += "\nAccuracy: " + (accuracy*100) + "%";
		message += "\nWeight: " + weight + "lbs";
		message += "\nDescription: " + description;
		
		return message;
	}
	
}
