/**
 * --------------------------------------------------------------------------
 * File name: Equipment.java
 * Project name: Rise of Nosian
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Creation Date: 10/25/2021
 * Completion Date: --/--/2021
 * @version 1.00
 * --------------------------------------------------------------------------
 */

import java.util.ArrayList;
import weapon.*;
import armor.*;

/**
 * The Equipment class keeps track of the equipment that the player owns,
 * as well as what they have equipped.
 *
 * <hr>
 * Date created: Mar 28, 2021
 * <hr>
 * @author Michael Ng
 */
public class Equipment
{
	private int totalArmor, totalLuck, weight;
	private Weapon primaryWeapon;
	private Armor headArmor, torsoArmor, handArmor, legArmor, footArmor, accessory, accessory2;
	//private Crystal crysal1, crystal2, crystal3;
	private ArrayList<Weapon> ownedWeapons = new ArrayList<Weapon>();
	private ArrayList<Armor> ownedArmors = new ArrayList<Armor>();

	
	/**
	 * The Default Constructor of the Equipment Class.   
	 *
	 * <hr>
	 * Date created: Mar 31, 2021 
	 *
	 *
	 */
	public Equipment() 
	{
									//Weapon Name,    Description,						  Pdamage, Mdamage, weight, accuracy
		ownedWeapons.add (new Weapon("Rusty Knife", "A rusted knife that carries sentimental value.", 8, 8, 1, 1));
		ownedWeapons.add (new Weapon("Stick", "A stick found on the ground.", 1, 12, 2, .95));
		primaryWeapon = ownedWeapons.get (0);
		
		
		ownedArmors.add (new Armor(BodyPart.Head, "Cap", "A lucky hat from home that has much sentimental value.", "Lucky - Luck is increased.", 0, 0, 0, 10)); //0
		ownedArmors.add (new Armor(BodyPart.Head, "Helmet", "A standard issue army helmet.", "Security - Wearing this helmet makes one feel safe.", 5, 0, 0, 1));
		ownedArmors.add (new Armor(BodyPart.Torso, "Jacket", "Standard issue jacket.", "Pockets - Store a lot of things!", 10, 0, 1, 2));
		ownedArmors.add (new Armor(BodyPart.Hands, "Tactical Gloves", "Standard gloves used to improve grip on a weapon.", "Grip - No butterfingers here.", 1, 0, 0, 3));
		ownedArmors.add (new Armor(BodyPart.Legs, "Cargo Pants", "Standard pants that allow flexibility and protection for the wearer.", "Flexible - Technology is nice to have.", 3, 1, 1, 1));
		ownedArmors.add (new Armor(BodyPart.Feet, "Boots", "Standard boots that were made to traverse any terrain. Comfortable.", "Comfortable - Ah... Is that Polyester?", 3, 1, 1, 2));
		ownedArmors.add (new Armor(BodyPart.Accessory, "Necklace", "An accessory that reminds you of your old self.", "Reminiscent - Old memories bring hope.", 0, 0, 0, 5));
		ownedArmors.add (new Armor(BodyPart.Accessory, "Water Canteen", "Quick access to hydration.", "Tactical - This could be used anywhere.", 0, 1, 0, 5));
		ownedArmors.add (new Armor(BodyPart.Accessory2, "Ring", "A cheap plastic ring from vending machines.", "Revisiting - I remember when I first got that ring.", 0, 0, 0, 3));
		ownedArmors.add (new Armor(BodyPart.Accessory2, "Chain", "A chain that connects to a small cap with a picture of a loved one in it.", "Protection - Fighting for loved ones.", 0, 0, 0, 5)); //9
		
		headArmor = ownedArmors.get (1);
		torsoArmor = ownedArmors.get (2);
		handArmor = ownedArmors.get (3);
		legArmor = ownedArmors.get (4);
		footArmor = ownedArmors.get (5);
		accessory = ownedArmors.get (7);
		accessory2 = ownedArmors.get (9);
		calculateTotal();
	}
	
	
	/**
	 * A customer constructor generated exculsively for the Enemy class.
	 * Equips the enemy with the specified weapon and armors.      
	 *
	 * <hr>
	 * Date created: Apr 2, 2021 
	 *
	 * 
	 * @param primaryWeapon
	 * @param armors
	 */
	public Equipment(Weapon primaryWeapon, Armor[] armors) 
	{
		this.primaryWeapon = primaryWeapon;
		headArmor = armors[0];
		torsoArmor = armors[1];
		handArmor = armors[2];
		legArmor = armors[3]; 
		footArmor = armors[4];

		calculateTotal();
	}
	
	
	/**
	 * Calculates the total amount of armor points the player has.
	 *
	 * <hr>
	 * Date created: Mar 31, 2021
	 *
	 * <hr>
	 */
	private void calculateTotal() 
	{
		totalArmor = headArmor.getDefense() + torsoArmor.getDefense ( ) 
		+ handArmor.getDefense ( ) + legArmor.getDefense ( ) + footArmor.getDefense ( );
		
		totalLuck = headArmor.getLuck() + torsoArmor.getLuck() + 
					handArmor.getLuck() + legArmor.getLuck() + footArmor.getLuck();
		
		weight = headArmor.getWeight() + torsoArmor.getWeight() + 
				handArmor.getWeight() + legArmor.getWeight() + footArmor.getWeight() + 
				primaryWeapon.getWeight();
		
		if(accessory != null) 
		{
			totalArmor += accessory.getDefense ( ) + accessory2.getDefense ( );
			totalLuck += accessory.getLuck ( ) + accessory2.getLuck ( );
			weight += accessory.getWeight( ) + accessory2.getWeight ( );
		}
	}
	
	/**
	 * Returns the total amount of armor.    
	 *
	 * <hr>
	 * Date created: Mar 31, 2021
	 *
	 * <hr>
	 * @return totalArmor
	 */
	public int getTotalArmor() 
	{
		return totalArmor;
	}
	
	
	/**
	 * @return headArmor
	 */
	public Armor getHeadArmor ( )
	{
		return headArmor;
	}

	
	/**
	 * @param headArmor the headArmor to set
	 */
	public void setHeadArmor (Armor headArmor)
	{
		this.headArmor = headArmor;
		calculateTotal();
	}

	
	/**
	 * @return torsoArmor
	 */
	public Armor getTorsoArmor ( )
	{
		return torsoArmor;
	}

	
	/**
	 * @param torsoArmor the torsoArmor to set
	 */
	public void setTorsoArmor (Armor torsoArmor)
	{
		this.torsoArmor = torsoArmor;
		calculateTotal();
	}

	
	/**
	 * @return handArmor
	 */
	public Armor getHandArmor ( )
	{
		return handArmor;
	}

	
	/**
	 * @param handArmor the handArmor to set
	 */
	public void setHandArmor (Armor handArmor)
	{
		this.handArmor = handArmor;
		calculateTotal();
	}

	
	/**
	 * @return legArmor
	 */
	public Armor getLegArmor ( )
	{
		return legArmor;
	}

	
	/**
	 * @param legArmor the legArmor to set
	 */
	public void setLegArmor (Armor legArmor)
	{
		this.legArmor = legArmor;
		calculateTotal();
	}

	
	/**
	 * @return footArmor
	 */
	public Armor getFootArmor ( )
	{
		return footArmor;
	}

	
	/**
	 * @param footArmor the footArmor to set
	 */
	public void setFootArmor (Armor footArmor)
	{
		this.footArmor = footArmor;
		calculateTotal();
	}

	
	/**
	 * @return accessory
	 */
	public Armor getAccessory ( )
	{
		return accessory;
	}

	
	/**
	 * @param accessory the accessory to set
	 */
	public void setAccessory (Armor accessory)
	{
		this.accessory = accessory;
		calculateTotal();
	}

	
	/**
	 * @return accessory2
	 */
	public Armor getAccessory2 ( )
	{
		return accessory2;
	}

	
	/**
	 * @param accessory2 the accessory2 to set
	 */
	public void setAccessory2 (Armor accessory2)
	{
		this.accessory2 = accessory2;
		calculateTotal();
	}
	
	/**
	 * @return primaryWeapon
	 */
	public Weapon getPrimaryWeapon ( )
	{
		return primaryWeapon;
	}

	
	/**
	 * @param primaryWeapon the primaryWeapon to set
	 */
	public void setPrimaryWeapon (Weapon primaryWeapon)
	{
		this.primaryWeapon = primaryWeapon;
		calculateTotal();
	}

	
	/**
	 * @return totalLuck
	 */
	public int getTotalLuck ( )
	{
		return totalLuck;
	}

	
	/**
	 * @return weight
	 */
	public int getWeight ( )
	{
		return weight;
	}
	

	/**
	 * Returns an armor arraylist depending on type.
	 *
	 * <hr>
	 * Date created: Mar 31, 2021
	 *
	 * <hr>
	 * @param armorType
	 * @return Armor
	 */
	public ArrayList<Armor> getArmorArrayList(String armorType)
	{
		armorType = armorType.toUpperCase ( ).trim ( );
		ArrayList<Armor> returnedArmors = new ArrayList<>();
		
		//Originally, this code was a lot of if statements until polishing was done.
		if(armorType.equals ("HEAD")) 
		{
			for(Armor armor: ownedArmors) 
			{
				if(armor.getBodyPart ( ).toUpperCase ( ).equals ("HEAD")) 
				{
					returnedArmors.add (armor);
				}
			}
		}
		else if(armorType.equals ("TORSO")) 
		{
			for(Armor armor: ownedArmors) 
			{
				if(armor.getBodyPart ( ).toUpperCase ( ).equals ("TORSO")) 
				{
					returnedArmors.add (armor);
				}
			}
		}
		else if(armorType.equals ("HAND")) 
		{
			for(Armor armor: ownedArmors) 
			{
				if(armor.getBodyPart ( ).toUpperCase ( ).equals ("HANDS")) 
				{
					returnedArmors.add (armor);
				}
			}
		}
		else if(armorType.equals ("LEG")) 
		{
			for(Armor armor: ownedArmors) 
			{
				if(armor.getBodyPart ( ).toUpperCase ( ).equals ("LEGS")) 
				{
					returnedArmors.add (armor);
				}
			}
		}
		else if(armorType.equals ("FOOT")) 
		{
			for(Armor armor: ownedArmors) 
			{
				if(armor.getBodyPart ( ).toUpperCase ( ).equals ("FEET")) 
				{
					returnedArmors.add (armor);
				}
			}
		}
		else if(armorType.equals ("ACCESSORY")) 
		{
			for(Armor armor: ownedArmors) 
			{
				if(armor.getBodyPart ( ).toUpperCase ( ).equals ("ACCESSORY")) 
				{
					returnedArmors.add (armor);
				}
			}
		}
		else if(armorType.equals ("ACCESSORY2")) 
		{
			for(Armor armor: ownedArmors) 
			{
				if(armor.getBodyPart ( ).toUpperCase ( ).equals ("ACCESSORY2")) 
				{
					returnedArmors.add (armor);
				}
			}
		}
		return returnedArmors;
	}
	
	/**
	 * Returns a weapon arraylist depending on its type.
	 *
	 * <hr>
	 * Date created: Apr 2, 2021
	 *
	 * <hr>
	 * @param weaponCategory
	 * @return Weapon
	 */
	public ArrayList<Weapon> getWeaponArrayList(String weaponCategory) 
	{
		ArrayList<Weapon> returnWeapons = new ArrayList<>();
		
		weaponCategory = weaponCategory.toUpperCase ( );
		//Return all the pistols that the hero owns.
		if(weaponCategory.equals("PISTOL")) 
		{
			for(Weapon weapon: ownedWeapons) 
			{
				if(weapon.getWeaponName ( ).equals ("Amber M7")) 
				{
					returnWeapons.add (new Weapon());
				}
				else if(weapon.getWeaponName ( ).equals ("Carris M3"))
				{
					returnWeapons.add (new Weapon());
				}
				else if(weapon.getWeaponName ( ).equals ("Legion 5li"))
				{
					returnWeapons.add (new Weapon());
				}
				else if(weapon.getWeaponName ( ).equals ("Cal M4B"))
				{
					returnWeapons.add (new Weapon());
				}
				else if(weapon.getWeaponName ( ).equals ("Smoke A8"))
				{
					returnWeapons.add (new Weapon());
				}
			}
		}
		//Return all the rifles that the hero owns.
		else if(weaponCategory.equals("RIFLE")) 
		{
			for(Weapon weapon: ownedWeapons) 
			{
				if(weapon.getWeaponName ( ).equals ("SBR 1100")) 
				{
					returnWeapons.add (new Weapon());
				}
				else if(weapon.getWeaponName ( ).equals ("EBR 1100X"))
				{
					returnWeapons.add (new Weapon());
				}
				else if(weapon.getWeaponName ( ).equals ("Amber Carbine"))
				{
					returnWeapons.add (new Weapon());
				}
				else if(weapon.getWeaponName ( ).equals ("Smoke BR715"))
				{
					returnWeapons.add (new Weapon());
				}
				else if(weapon.getWeaponName ( ).equals ("Legion BRM280"))
				{
					returnWeapons.add (new Weapon());
				}
			}
		}
		//Return all the assualt rifles that the hero owns.
		else if(weaponCategory.equals("ASSAULT RIFLE")) 
		{
			for(Weapon weapon: ownedWeapons) 
			{
				if(weapon.getWeaponName ( ).equals ("Amber AAR50")) 
				{
					returnWeapons.add (new Weapon());
				}
				else if(weapon.getWeaponName ( ).equals ("Cal ARX")) 
				{
					returnWeapons.add (new Weapon());
				}
				else if(weapon.getWeaponName ( ).equals ("Carris AR90")) 
				{
					returnWeapons.add (new Weapon());
				}
			}
		}
		
		return returnWeapons;
	}
	
	/**
	 * Adds a weapon to the weapons arraylist.
	 *
	 * <hr>
	 * Date created: Apr 2, 2021
	 *
	 * <hr>
	 * @param weapon
	 * @return boolean
	 */
	public void addWeapon(Weapon weapon) 
	{
		if(ownedWeapons.contains (weapon)) 
		{
			//do nothing. Does not add the weapon.
		}
		else 
		{
			ownedWeapons.add(weapon);
		}
	}
	
	/**
	 * Adds an armor to the armors array.
	 *
	 * <hr>
	 * Date created: Apr 2, 2021
	 *
	 * <hr>
	 * @param armor
	 * @return boolean
	 */
	public void addArmor(Armor armor) 
	{
		if(ownedArmors.contains (armor)) 
		{
			//do nothing. Does not add the Armor.
		}
		else 
		{
			ownedArmors.add(armor);
		}
	}
	
	
	/**
	 * Returns the ArrayList for all the weapons the player owns.     
	 *
	 * <hr>
	 * Date created: Mar 31, 2021
	 *
	 * <hr>
	 * @return weapons
	 */
	public ArrayList<Weapon> getOwnedWeapons()
	{
		return ownedWeapons;
	}
	
	/**
	 * Returns the ArrayList for all the armors the player owns.   
	 *
	 * <hr>
	 * Date created: Mar 31, 2021
	 *
	 * <hr>
	 * @return armors
	 */
	public ArrayList<Armor> getOwnedArmors()
	{
		return ownedArmors;
	}
	
	/**
	 * Imports weapons from a save file.
	 *
	 * <hr>
	 * Date created: Apr 3, 2021
	 *
	 * <hr>
	 * @param equipmentNames
	 */
	public void importEquipment(ArrayList<String> equipmentNames, String primary, String head, String torso, String hands, String leg, String foot, String accessory, String accessory2) 
	{
		ownedWeapons.clear();
		//Imports weapons into the hero's owned equipment based on weapon name.
		for(String equipmentName: equipmentNames) 
		{
			//TODO change weapons
			//Note: equipmentNames[0] represents the primary weapon equipped.
			if(equipmentName.equals("Amber M7")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) //Because the weapon was recently added to the ArrayList, it appears on the last index. We can refer to it using this.
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("Carris M3")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("Legion 5li")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("Cal M4B")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("Smoke A8")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("SBR 1100")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("EBR 1100X")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("Amber Carbine")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("Smoke BR715")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("Legion BRM280")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("Amber AAR50")) 
			{
				ownedWeapons.add(new Weapon());	
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("Cal ARX")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}
			else if(equipmentName.equals("Carris AR90")) 
			{
				ownedWeapons.add(new Weapon());
				if(primary.equals(ownedWeapons.get (ownedWeapons.size()-1).getWeaponName ( ))) 
				{
					primaryWeapon = ownedWeapons.get (ownedWeapons.size()-1);
				}
			}


			ownedArmors.clear ( );
			//Imports all armors that can be 'found' in-game.
			//Imports armors into the hero's owned equipment based on armor name.
			//Separated into a different for loop for organization.
			if(equipmentName.equals ("Cap")) 
			{
				ownedArmors.add (new Armor(BodyPart.Head, "Cap", "A lucky hat from home that has much sentimental value.", "Lucky - Luck is increased.", 0, 0, 0, 10));
				//equipmentNames[1] represents head armor.
				if(head.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) //Because the armor was recently added to the ArrayList, it appears on the last index. We can refer to it using this.
				{
					headArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Helmet")) 
			{
				ownedArmors.add (new Armor(BodyPart.Head, "Helmet", "A standard issue army helmet.", "Security - Wearing this helmet makes one feel safe.", 5, 0, 0, 1));
				if(head.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					headArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Headset")) 
			{
				ownedArmors.add (new Armor(BodyPart.Head, "Headset", "A standard issue army helmet with a modification.", "Headset - Talk and chat freely with nearby teammates.", 5, 0, 1, 5));
				if(head.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					headArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Kevlar Helmet")) 
			{
				ownedArmors.add (new Armor(BodyPart.Head, "Kevlar Helmet", "A handy kevlar helmet. The padding is nice.", "Padding - One can get comfortable here.", 10, 2, 2, 7));
				if(head.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					headArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Head Plate")) 
			{
				ownedArmors.add (new Armor(BodyPart.Head, "Head Plate", "A metal helmet that provides supreme protection.", "Security 2 - Wearing this makes one feel *very* safe.", 10, 5, 3, 0));
				if(head.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					headArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Jacket")) 
			{
				ownedArmors.add (new Armor(BodyPart.Torso, "Jacket", "Standard issue jacket.", "Pockets - Store a lot of things!", 10, 0, 1, 2));
				if(torso.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					torsoArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Vest")) 
			{
				ownedArmors.add (new Armor(BodyPart.Torso, "Vest", "Bullet-proof vest (mostly).", "Pockets - Store many things!", 20, 0, 1, 2));
				if(torso.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					torsoArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Suit")) 
			{
				ownedArmors.add (new Armor(BodyPart.Torso, "Suit", "A business suit. Give those enemies the business.", "Business - Give em' the Business.", 3, 2, 0, 10));
				if(torso.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					torsoArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Kevlar Jacket")) 
			{
				ownedArmors.add (new Armor(BodyPart.Torso, "Kevlar Jacket", "A Kevlar Jacket. Man, this is heavy.", "Security - This is a lot of padding.", 25, 5, 14, 0));
				if(torso.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					torsoArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Chestplate")) 
			{
				ownedArmors.add (new Armor(BodyPart.Torso, "Chestplate", "A medieval knight's chestplate. Super heavy.", "Ricochet - Those bullets will bounce right off!", 35, 5, 20, 1));
				if(torso.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					torsoArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Tactical Gloves")) 
			{
				ownedArmors.add (new Armor(BodyPart.Hands, "Tactical Gloves", "Standard gloves used to improve grip on a weapon.", "Grip - No butterfingers here.", 1, 0, 0, 3));
				if(hands.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					handArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Half Gloves")) 
			{
				ownedArmors.add (new Armor(BodyPart.Hands, "Half Gloves", "Gloves that improve grip on a weapon without covering the entire hand.", "Grip - No butterfingers here.", 0, 0, 0, 5));
				if(hands.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					handArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Latex Gloves")) 
			{
				ownedArmors.add (new Armor(BodyPart.Hands, "Latex Gloves", "Gloves that keep your hands safe and clean.", "Sanitary - No germs here.", 0, 1, 0, 5));
				if(hands.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					handArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Kevlar Gloves")) 
			{
				ownedArmors.add (new Armor(BodyPart.Hands, "Kevlar Gloves", "Kevlar gloves used to improve grip on a weapon and keep your hands safe.", "Safe - these hands are safe.", 5, 2, 3, 2));
				if(hands.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					handArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Metal Gloves")) 
			{
				ownedArmors.add (new Armor(BodyPart.Hands, "Metal Gloves", "Metal gloves used to keep one's hands safe. Not so great for weapon handling.", "No Grip - Oops. Butterfingers.", 10, 0, 5, -2));
				if(hands.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					handArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Cargo Pants")) 
			{
				ownedArmors.add (new Armor(BodyPart.Legs, "Cargo Pants", "Standard pants that allow flexibility and protection for the wearer.", "Flexible - Technology is nice to have.", 3, 1, 1, 1));
				if(leg.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					legArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Shorts")) 
			{
				ownedArmors.add (new Armor(BodyPart.Legs, "Shorts", "Even more flexible than Cargo Pants!", "Flexible 2 - Leg day is a great day.", 0, 0, 0, 10));
				if(leg.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					legArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Suit Pants")) 
			{
				ownedArmors.add (new Armor(BodyPart.Legs, "Suit Pants", "Black pants that allow for a formal look.", "Lucky - Those Allies won't mind.", 0, 1, 0, 15));
				if(leg.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					legArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Kevlar Pants")) 
			{
				ownedArmors.add (new Armor(BodyPart.Legs, "Kevlar Pants", "Kevlar pants that provide protection for the wearer.", "Safety - These pants are safe to wear.", 10, 5, 7, 1));
				if(leg.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					legArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Metal Pants")) 
			{
				ownedArmors.add (new Armor(BodyPart.Legs, "Metal Pants", "Metal pants that provide immense protection for the wearer.", "Ricochet - Those bullets will bounce right off.", 15, 5, 10, 1));
				if(leg.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					legArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Boots")) 
			{
				ownedArmors.add (new Armor(BodyPart.Feet, "Boots", "Standard boots that were made to traverse any terrain. Comfortable.", "Comfortable - Ah... Is that Polyester?", 3, 1, 1, 2));
				if(foot.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					footArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Slippers")) 
			{
				ownedArmors.add (new Armor(BodyPart.Feet, "Slippers", "Standard boots may get too hot. Wear these cool sandals instead!", "Cool - That foot will catch that nice breeze.", 0, 0, 0, 10));
				if(foot.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					footArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Dress Shoes")) 
			{
				ownedArmors.add (new Armor(BodyPart.Feet, "Dress Shoes", "Look formal by walking in black dress shoes.", "Formal - It's easy to set yourself apart from the others.", 0, 2, 1, 12));
				if(foot.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					footArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Kevlar Boots")) 
			{
				ownedArmors.add (new Armor(BodyPart.Feet, "Kevlar Boots", "Protect those feet with Kevlar Boots!", "Hot - That's too much padding on boots.", 7, 0, 3, 0));
				if(foot.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					footArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Metal Boots")) 
			{
				ownedArmors.add (new Armor(BodyPart.Feet, "Metal Boots", "Metal boots made to protect a knight's feet. Heavy.", "Metal Feet - Lifting a foot is harder than it looks.", 5, 5, 5, 0));
				if(foot.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					footArmor = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Necklace")) 
			{
				ownedArmors.add (new Armor(BodyPart.Accessory, "Necklace", "An accessory that reminds you of your old self.", "Reminiscent - Old memories bring hope.", 0, 0, 0, 5));
				if(accessory.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					this.accessory = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Water Canteen")) 
			{
				ownedArmors.add (new Armor(BodyPart.Accessory, "Water Canteen", "Quick access to hydration.", "Tactical - This could be used anywhere.", 0, 1, 0, 5));
				if(accessory.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					this.accessory = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Spyglass")) 
			{
				ownedArmors.add (new Armor(BodyPart.Accessory, "Spyglass", "Become a pirate and gaze into the horizon.", "Far Sight - This must be what an Eagle sees.", 0, 0, 0, 9));
				if(accessory.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					this.accessory = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Mask")) 
			{
				ownedArmors.add (new Armor(BodyPart.Accessory, "Mask", "Change your own persona! Actually, this is a surgical mask...", "Sanitary - No germs are getting through that mask.", 0, 1, 0, 8));
				if(accessory.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					this.accessory = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Necktie")) 
			{
				ownedArmors.add (new Armor(BodyPart.Accessory, "Necktie", "Formality at its finest. Tying a tie is harder than it sounds.", "Tied Up - Up, Down, Across... What?", 0, 0, 0, 12));
				if(accessory.equals (ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					this.accessory = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Ring")) 
			{
				ownedArmors.add (new Armor(BodyPart.Accessory2, "Ring", "A cheap plastic ring from vending machines.", "Revisiting - I remember when I first got that ring.", 0, 0, 0, 3));
				if(accessory2.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					this.accessory2 = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Chain")) 
			{
				ownedArmors.add (new Armor(BodyPart.Accessory2, "Chain", "A chain that connects to a small cap with a picture of a loved one in it.", "Protection - Fighting for loved ones.", 0, 0, 0, 5));
				if(accessory2.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					this.accessory2 = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Wristwatch")) 
			{
				ownedArmors.add (new Armor(BodyPart.Accessory2, "Wristwatch", "Easily keep track of the time by looking at your wrist.", "Track Time - It's no place to be late.", 0, 1, 0, 5));
				if(accessory2.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					this.accessory2 = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Rose")) 
			{
				ownedArmors.add (new Armor(BodyPart.Accessory2, "Rose", "Nice to have handy in unexpected situations.", "Rose - A healthy red keeps your hopes high.", 0, 0, 0, 6));
				if(accessory2.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					this.accessory2 = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			else if(equipmentName.equals ("Deodorant")) 
			{
				ownedArmors.add (new Armor(BodyPart.Accessory2, "Deodorant", "In case one doesn't have time to shower.", "Deodorant - The scent of greatness.", 0, 0, 0, 7));
				if(accessory2.equals(ownedArmors.get (ownedArmors.size ( )-1).getArmorName ( ))) 
				{
					this.accessory2 = ownedArmors.get (ownedArmors.size ( )-1);
				}
			}
			//TODO Add crystals
		}
		calculateTotal();
	}
	
	
	
	/**
	 * The default toString method for the Equipment class.     
	 *
	 * <hr>
	 * Date created: Apr 1, 2021 
	 *
	 * <hr>
	 * @return message
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		String message = "";
		
		message += "\nEquipped Weapon";
		message += "\n___________________\n";
		message += primaryWeapon.toString ( );
		
		message += "\n\nEquipped Armor";
		message += "\n___________________\n";
		message += headArmor.toString ( );
		message += "\n" + torsoArmor.toString ( );
		message += "\n" + handArmor.toString ( );
		message += "\n" + legArmor.toString ( );
		message += "\n" + footArmor.toString ( );
		message += "\n" + accessory.toString ( );
		message += "\n" + accessory2.toString ( );
		
		return message;
	}
	
	/**
	 * An overloaded toString method for the Equipment class.
	 * Gets a specific section.
	 *
	 * <hr>
	 * Date created: Apr 1, 2021 
	 *
	 * <hr>
	 * @return message
	 * @see java.lang.Object#toString()
	 */
	public String toString(String type) 
	{
		String message = "";
		
		if(type.equals("WEAPON")) 
		{
			message += "Equipped Weapons";
			message += "\n___________________\n";
			message += primaryWeapon.toString ( );
		}
		else if (type.equals("ARMOR"))
		{
			message += "Equipped Armor";
			message += "\n___________________\n";
			message += headArmor.toString ( );
			message += torsoArmor.toString ( );
			message += handArmor.toString ( );
			message += legArmor.toString ( );
			message += footArmor.toString ( );
			
		}
		else //if type is accessory
		{
			message += "Equipped Accessories";
			message += "\n___________________\n";
			message += accessory.toString ( );
			message += accessory2.toString ( );
		}

		return message;
	}
}
