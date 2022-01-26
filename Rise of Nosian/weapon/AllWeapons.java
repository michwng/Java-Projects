/**
 * ---------------------------------------------------------------------------
 * File name: AllWeapons.java
 * Project name: Project 5
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Mar 28, 2021
 * ---------------------------------------------------------------------------
 */

//This class and AllArmors was made early in anticipation of Project 5.

package weapon;
import java.util.ArrayList;

/**
 * The AllWeapons class stores a list of most weapons in the game.
 * This class is not used. It only acts as a reference.
 * A few enemies have custom weapons.
 *
 * <hr>
 * Date created: Mar 28, 2021
 * <hr>
 * @author Michael Ng
 */
public class AllWeapons extends Weapon
{
	//This arraylist will store all in-game weapons defined below in the constructor.
	private ArrayList<Weapon> weapons = new ArrayList<>();
	
	/**
	 * The Constructor for the AllWeapons class. Creates Weapon objects to be added to the weapons arraylist.
	 *
	 * <hr>
	 * Date created: Mar 28, 2021 
	 *
	 *
	 */
	public AllWeapons() 
	{
		//Note: All of these are made-up names. If they share a name with a real weapon, it is coincidental.
		//All In-Game Pisols. (Index 0 - 4)
		Weapon pistol = new Weapon();
		Weapon pistol2 = new Weapon();
		Weapon pistol3 = new Weapon();
		Weapon pistol4 = new Weapon();
		Weapon pistol5 = new Weapon();
		
		//Add the pistols above into the weapons ArrayList.
		weapons.add (pistol);
		weapons.add (pistol2);
		weapons.add (pistol3);
		weapons.add (pistol4);
		weapons.add (pistol5);
		
		//All In-Game Rifles. (Index 5 - 9)
		Weapon rifle = new Weapon();
		Weapon rifle2 = new Weapon();
		Weapon rifle3 = new Weapon();
		Weapon rifle4 = new Weapon();
		Weapon rifle5 = new Weapon();
		
		//Add the rifles into the weapons ArrayList.
		weapons.add (rifle);
		weapons.add (rifle2);
		weapons.add (rifle3);
		weapons.add (rifle4);
		weapons.add (rifle5);
		
		//All In-Game Assault Rifles. (Index 10 - 12)
		Weapon ar = new Weapon();
		Weapon ar2 = new Weapon();
		Weapon ar3 = new Weapon();
		
		//Add Assault Rifles into the weapons arraylist.
		weapons.add (ar);
		weapons.add (ar2);
		weapons.add (ar3);
		
	}	
}
