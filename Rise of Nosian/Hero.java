/**
 * ---------------------------------------------------------------------------
 * File name: Hero.java
 * Project name: Rise of Nosian
 * ---------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course:  CSCI 1260-940 (Intro Computer Sci II)
 * Creation Date: Mar 28, 2021
 * ---------------------------------------------------------------------------
 */

import java.util.ArrayList;
import weapon.Weapon;

/**
 * The HeroAttributes class is responsible for managing the
 * hero's experience and when they level up.
 * Also provides stats when needed.
 *
 * <hr>
 * Date created: Mar 28, 2021
 * <hr>
 * @author Michael Ng
 */
public class Hero extends Participant
{
	//equipment and basic stats extend from Participant.
	//Their getter and setter methods are included.
	private int exp, expLevelUp;
	private String specialization;
	private int money;
	
	/**
	 * The Default Constructor of the HeroAttributes class.        
	 *
	 * <hr>
	 * Date created: Mar 31, 2021 
	 *
	 *
	 */
	public Hero(String specialization) 
	{
		equipment = new Equipment();
		this.specialization = specialization;

		name = "Nosian";
		maxHP = 100;
		HP = maxHP;
		maxSP = 50;
		SP = maxSP;

		level = 1;
		exp = 0;
		expLevelUp = 100;
		
		channelRating = 1;
		carryWeight = 5;

		switch(specialization)
		{
			//Basic stats are ordered as follows:
			//Max HP, HP, Max SP, SP, Constitution, Affinity, Armor, Resistance, Speed.

			//Level Up Stats are ordered as follows:
			//Max HP, Max SP, Constitution, Affinity, (Armor), Resistance, Speed, Channel Rating, Carry Weight
			case "Mercenary":
				setBasicStats(150, 150, 50, 50, 50, 5, 30, 10, 10);
				setLevelUpStats(80, 50, 90, 20, 80, 50, 50, 90, 90);
				break;
			case "Mage":
				setBasicStats(100, 100, 150, 150, 5, 55, 15, 30, 15);
				setLevelUpStats(80, 50, 90, 20, 50, 50, 50, 90, 90);
				break;
			case "Medic":
				setBasicStats(100, 100, 100, 100, 5, 25, 15, 15, 45);
				setLevelUpStats(80, 90, 50, 60, 50, 80, 100, 95, 80);
				break;
			case "Pugilist":
				setBasicStats(125, 125, 90, 90, 75, 5, 10, 10, 5);
				setLevelUpStats(90, 95, 100, 30, 70, 70, 20, 80, 80);
				break;
			case "Paladin":
				setBasicStats(300, 300, 150, 150, 25, 25, 50, 50, 1);
				setLevelUpStats(100, 80, 70, 70, 90, 90, 20, 60, 60);
				break;
			case "Monk":
				setBasicStats(200, 200, 200, 200, 20, 20, 20, 20, 20);
				setLevelUpStats(80, 80, 80, 80, 80, 80, 80, 100, 100);
				break;
			case "Enchanting Prodigy":
				setBasicStats(120, 120, 100, 100, 20, 20, 10, 10, 50);
				setLevelUpStats(0, 0, 0, 0, 0, 0, 0, 0, 0);
		}
		updateSkillDamage(); //Also adds skills.
	}
	
	/**
	 * A parameterized Constructor.
	 * This constructor is used when a save file is imported. 
	 *
	 * <hr>
	 * Date created: Mar 31, 2021 
	 *
	 * 
	 * @param heroName
	 * @param equipment
	 * @param maxHP
	 * @param maxSP
	 */
	public Hero(int maxHP, int maxSP, int level, int exp, int expLevelUp, int channelRating, int carryWeight, int constitution, int affinity, int armor, int resistance, int speed, int money) 
	{		
		//This will be added to accordingly in the Driver's loadGame method.
		equipment = new Equipment();
		
		this.maxHP = maxHP;
		this.HP = maxHP;
		this.maxSP = maxSP;
		this.SP = maxSP;
		this.level = level;
		this.exp = exp;
		this.expLevelUp = expLevelUp;
		this.money = money;
		updateSkillDamage();
	}
	

	/**
	 * @return maxHP
	 */
	public int getMaxHP ( )
	{
		return maxHP;
	}

	
	/**
	 * @return hP
	 */
	public int getHP ( )
	{
		return HP;
	}

	
	/**
	 * @param hP the hP to set
	 */
	public void setHP (int hP)
	{
		HP = hP;
		if(HP < 0) 
		{
			HP = 0;
		}
		else if(HP > maxHP) 
		{
			HP = maxHP;
		}
	}

	
	/**
	 * @return maxSP
	 */
	public int getMaxSP ( )
	{
		return maxSP;
	}

	
	/**
	 * @param maxSP the maxSP to set
	 */
	public void setMaxSP (int maxSP)
	{
		this.maxSP = maxSP;
	}

	
	/**
	 * @return sP
	 */
	public int getSP ( )
	{
		return SP;
	}

	
	/**
	 * @param sP the sP to set
	 */
	public void setSP (int sP)
	{
		SP = sP;
		if(SP < 0) 
		{
			SP = 0;
		}
		else if (SP > maxSP) 
		{
			SP = maxSP;
		}
	}

	/**
	 * returns the participant's specialization.
	 */
	public String getSpecialization()
	{
		return specialization;
	}

	
	/**
	 * @return level
	 */
	public int getLevel ( )
	{
		return level;
	}

	
	/**
	 * @return exp
	 */
	public int getExp ( )
	{
		return exp;
	}

	
	/**
	 * @return expLevelUp
	 */
	public int getExpLevelUp ( )
	{
		return expLevelUp;
	}
	
	/**
	 * This method adds to experience.
	 *
	 * <hr>
	 * Date created: Apr 2, 2021
	 *
	 * <hr>
	 * @param amount
	 */
	public void addExperience(int amount) 
	{
		exp += amount;
	}
	
	
	/**
	 * returns a boolean based on if the player can level up.
	 *
	 * <hr>
	 * Date created: Apr 2, 2021
	 *
	 * <hr>
	 * @return boolean
	 */
	public boolean canLevelUp() 
	{
		//If the player has more experience than the leveling threshold.
		if(exp >= expLevelUp) 
		{
			return true;
		}
		return false;
	}
	

	/**
	 * Level up the player. Gain skills and equipment upon level up.     
	 *
	 * <hr>
	 * Date created: Apr 2, 2021
	 *
	 * <hr>
	 * @return message
	 */
	public String checkLevel() 
	{
		String message = "Congratulations! You have leveled up.";
		message += "\nYou are now level " + level + ".";
		message += "\nExp until next level up: " + (expLevelUp - exp) + "XP";
		
		return message;
	}

	/**
	 * Update the amount of damage a skill does.
	 * Also acts as a way of obtaining special skills.
	 * 
	 * Date Created: 11/03/2021
	 */
	public void updateSkillDamage()
	{
		int basePhysicalDamage = constitution + equipment.getPrimaryWeapon().getPATK(); //(+ equipment.getCrystals().getPATKBoost)
		int baseMagicalDamage = affinity + equipment.getPrimaryWeapon().getMATK();// (+ equipment.getCrystals().getMATKBoost)
		int baseHealing = speed - equipment.getWeight();// (+ equipment.getCrystals().getSpeedBoost)

		//Base Skills
		Skill baseSkill = new Skill();
		Skill baseSkill2 = new Skill();

		//Special Skills
		Skill skill = new Skill();
		Skill skill2 = new Skill();
		Skill skill3 = new Skill();
		Skill skill4 = new Skill();
		Skill skill5 = new Skill();

		switch(specialization)
		{
			case "Warrior":
								//name,  effect, effect proc chance, length, efficacy, damage, skill description, effect description, target, effect target, efficacy StatBase or
				skill = new Skill("Crush", "Broken Armor", 45, 3, 15*level, basePhysicalDamage, 
				"Crush: Wittle away the Enemy's Armor with a flurry of strikes. Deals damage equal to Constitution.", "45% chance to reduce the Enemy's Armor by (15 * level).", "Enemy", "Enemy", "Constant");
								//skill name, effect name, effect, effect proc chance, length, efficacy, secondaryeffect, secondary efficacy, damage, skill description, effect description, target, effect Target, efficacyStatBase, secondaryEffect StatBase
				skill2 = new Skill("Dearm", "Weaponless", "Weaken", 45, 2, 20*level, "Silence", 0, (int)(basePhysicalDamage*0.5),
				"Dearm: Strike at the enemy's weapon in an attempt to disarm them.", "Weakens for (20 * level)ATK and Silences.", "Enemy", "Enemy", "Constant", "Constant");
				//TODO add skills
				//skill3 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill4 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill5 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				break;
			case "Mage":
							//skill name, effect name, effect, proc chance, length, efficacy, secondaryeffect, secondary efficacy, damage, skill description, effect description, target, effect Target, efficacyStatBase, secondaryEffect StatBase
				skill = new Skill("Epiphany", "Epiphany", "Headache", 60, 4, 20, "Poison", -105, (int)(baseMagicalDamage*0.9), 
				"Epiphany: Knowledge isn't always power. Deal damage equal to Affinity * 0.9.", "60% to inflict Headache and Poison on the Enemy, reducing Enemy Affinity by 20% (one-time) and HP by 5% every turn.", "Enemy", "Enemy", "Enemy", "Constant");
				//skill2 = new Skill("Dearm", "Weaken", .20, 2, 10*level, "Headache", 10*level, baseMagicalDamage);
				//skill3 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill4 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill5 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				break;
			case "Medic":
				skill = new Skill("Restore", "Restore the HP of an Ally with an amount equal to (Speed - Equipment Weight).", baseHealing, "Ally");
				//skill2 = new Skill("Dearm", "Weaken", .20, 2, 10*level, "Headache", 10*level, baseMagicalDamage);
				//skill3 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill4 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill5 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				break;
			case "Pugilist":
						//skill name, effect, proc chance, length, efficacy, damage, skill description, effect description, target, effect Target, efficacyStatBase
				skill = new Skill("Steel Crush", "Armor Break", 70, 3, 25*level, (int)(basePhysicalDamage*0.5), 
				"\nSteel Crush: Annihilate Armor with a power-packed punch. Deals damage equal to a half of Constitution", "Reduces Enemy Armor by (25 * level).", "Enemy", "Enemy", "Constant");
				//skill2 = new Skill("Dearm", "Weaken", .20, 2, 10*level, "Headache", 10*level, baseMagicalDamage);
				//skill3 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill4 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill5 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				break;
			case "Paladin":
								//skill name, effect name, effect, proc chance, length, efficacy, secondaryeffect, secondary efficacy, damage, skill description, effect description, target, effect Target, efficacyStatBase, secondaryEffect StatBase
				skill = new Skill("Empower", "Empowered", "Invigoration", 100, 5, 35, "Courage", 35, 0, 
				"Empower: Implore an Ally to carry on the fight.", "Increases Affinity and Constitution by 35% of their Stats.", "Ally", "Ally", "Ally", "Ally");
				//skill2 = new Skill("Dearm", "Weaken", .20, 2, 10*level, "Headache", 10*level, baseMagicalDamage);
				//skill3 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill4 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill5 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				break;
			case "Monk":
			//skill name, effect name, effect, effect proc chance, length, efficacy, secondaryeffect, secondary efficacy, damage, skill description, effect description, target, effect Target, efficacyStatBase, secondaryEffect StatBase
				skill = new Skill("6th Sense", "Will", "Invigoration", 100, 3, 15*level, "Shield", HP, 0, 
				"6th Sense: Enlighten an Ally to open their 6th sense.", "Increase the target's affinity by (15*level) and shield them for an amount equal to the caster's HP.", "Ally", "Ally", "Ally", "Self");
				//skill2 = new Skill("Dearm", "Weaken", .20, 2, 10*level, "Headache", 10*level, baseMagicalDamage);
				//skill3 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill4 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				//skill5 = new Skill(effectName, effect, procChance, effectLength, effectEfficacy, secondaryEffect, secondaryEffectEfficacy, damage);
				break;
			case  "Enchanting Prodigy":
				baseSkill = new Skill("Counter Edge", "Weaken", 60, 3, 30, (int)(basePhysicalDamage*1.5), "Dodge an enemy attack and counter with a strong physical attack, dealing damage equal to 150% of Constitution.", "(60% PROC) Weaken the Target for 30% of their Physical ATK for 3 turns.", "Enemy", "Enemy", "Enemy");
				baseSkill2 = new Skill("Control", "Headache", 50, 3, 38, (int)(baseMagicalDamage*1.5), "Attempt to damage the target's brain. Deal damage equal to 150% of Affinity.", "(50% PROC) Weaken the Target for 38% of their Magical ATK for 3 turns.", "Enemy", "Enemy", "Enemy");

				skill = new Skill("Enchanted Slash", "Bogged", "Weaken", 75, 3, 25, "Headache", 25, basePhysicalDamage + baseMagicalDamage, "Nosian enchants their sword with negative affinity and slashes the target. Deals damage equal to Nosian's (Affinity + Constitution).", "Weaken and Headache the target, reducing their Constitution and Affinity by 25% for 3 turns.", "Enemy", "Enemy", "Enemy", "Enemy");
		}

		ArrayList<Skill> updatedBaseSkills = new ArrayList<>();
		ArrayList<Skill> updatedSpecialSkills = new ArrayList<>();
		//Level 1 Skills
		updatedBaseSkills.add(baseSkill);
		updatedBaseSkills.add(baseSkill2);
		updatedSpecialSkills.add(skill);

		//Level 5+ Skill
		if(level >= 5)
		{
			updatedSpecialSkills.add(skill2);
		}

		//Level 10+ Skill
		if(level >= 10)
		{
			updatedSpecialSkills.add(skill3);
		}

		//Level 15+ Skill
		if(level >= 15)
		{
			updatedSpecialSkills.add(skill4);
		}

		//Level 20+ Skill
		if(level >= 20)
		{
			updatedSpecialSkills.add(skill5);
		}

		regularSkills = updatedBaseSkills;
		specialSkills = updatedSpecialSkills;
	}
	

	/**
	 * Allow the player the ability to respecialize.
	 * They will keep their stats, but have access to different skills
	 * and level up potential.
	 * 
	 * Date Created: 11/05/2021
	 */
	public void respecialize(String specialization)
	{
		switch(specialization)
		{
			//Basic stats are ordered as follows:
			//Max HP, HP, Max SP, SP, Constitution, Affinity, Armor, Resistance, Speed.

			//Level Up Stats are ordered as follows:
			//Max HP, Max SP, Constitution, Affinity, (Armor), Resistance, Speed, Channel Rating, Carry Weight

			//Here, the aptitiude for each stat increasing is changed based on the new class.
			case "Mercenary":
				setLevelUpStats(80, 50, 90, 20, 80, 50, 50, 90, 90);
				break;
			case "Mage":
				setLevelUpStats(80, 50, 90, 20, 50, 50, 50, 90, 90);
				break;
			case "Medic":
				setLevelUpStats(80, 90, 50, 60, 50, 80, 100, 95, 80);
				break;
			case "Pugilist":
				setLevelUpStats(90, 95, 100, 30, 70, 70, 20, 80, 80);
				break;
			case "Paladin":
				setLevelUpStats(100, 80, 70, 70, 90, 90, 20, 60, 60);
				break;
			case "Monk":
				setLevelUpStats(80, 80, 80, 80, 80, 80, 80, 100, 100);
				break;
		}

		//Change skills.
		updateSkillDamage();
	}


	/**
	 * returns the amount of money the hero has.
	 * 
	 * @return money
	 */
	public int getMoney()
	{
		return money;
	}

	/**
	 * Add the specified amount of money to the hero's total money.
	 * 
	 * @return money
	 */
	public void addMoney(int money)
	{
		this.money += money;
	}
	
	
	/**
	 * The toString method of the HeroAttributes class.     
	 *
	 * <hr>
	 * Date created: Mar 31, 2021 
	 *
	 * <hr>
	 * @return String - Message
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		String message = name;
		
		message += "\n---------------";
		message += "\nHP:   ( " + HP + "HP / " + maxHP + "HP )";
		message += "\nSP:   ( " + SP + "SP / " + maxSP + "SP )";
		Weapon pw = equipment.getPrimaryWeapon ( );
		message += "\nWeapon:        " + pw.getWeaponName ( ) + " (" + pw.getPATK() + "PDMG / " + pw.getMATK() + "MDMG)";
		message += "\nLuck: " + equipment.getTotalLuck ( );
		message += "\nArmor: " + equipment.getTotalArmor ( ) + "AP";
		message += "\nCarry Weight: " + equipment.getWeight() + "lbs.";
		message += "\nLevel: " + level + "   |   Exp to Level Up: (" + exp + "/" + expLevelUp + ")";
		
		return message;
	}
	
	/**
	 * Gets information from the Equipment class.   
	 *
	 * <hr>
	 * Date created: Apr 1, 2021
	 *
	 * <hr>
	 * @param type
	 * @return message
	 */
	public String getEquipmentInfo(String type) 
	{
		String message = "";
		type = type.toUpperCase ( ).trim();
		if(type.equals ("WEAPON")) 
		{
			message += "\n" + equipment.toString ("WEAPON");
		}
		else if (type.equals("ARMOR"))
		{
			message += equipment.toString ("ARMOR");
		}
		else 
		{
			message += equipment.toString ("ACCESSORY");
		}
		
		return message;
	}
	
	
	/**
     * Lists all of the Hero's Current Attributes.
     * Can also be considered the toString() method.
     * 
     * Date created: October 8, 2020
     * 
     * @return allAttributes - String
     */
    public String listAttributes()
    {
        String allAttributes = "Here are your current Attributes. \n";
        allAttributes += "Max HP: " + maxHP + "\n";
        allAttributes += "HP: " + HP + "\n";
        allAttributes += "Constitution: " + constitution + "\n";
        allAttributes += "Affinity: " + affinity + "\n";
        allAttributes += "Armor: " + armor + "\n";
        allAttributes += "Resistance: " + resistance + "\n";
        allAttributes += "Speed: " + speed + "\n";
		allAttributes += "Channel Rating: " + channelRating + "CR\n";
		allAttributes += "Channel Weight: " + carryWeight + "CW\n";
        allAttributes += "Money: " + money;

        return allAttributes;
    }

	/**
     * Describes the usage of each Attribute.
     * 
     * Date created: October 8, 2020
     * 
     * @param heroClass
     * @return attributeDesc - String
     */
    public String attributeDescription(String heroClass)
    {
        String attributeDesc = "Description of Attributes: \n";
        attributeDesc += "Max HP: The maximum amount of HP the Hero can have.\n";
        attributeDesc += "HP: Represents your Hero's Life Force. The game ends when HP reaches 0.\n";
        attributeDesc += "Constitution: Represents the amount of Physical Damage your Hero can deal.\n";
        attributeDesc += "Affinity: Represents the amount of Magical Damage your Hero can deal.\n";
        attributeDesc += "Armor: Reduces the amount of Physical Damage Taken.\n";
        attributeDesc += "Resistance: Reduces the amount of Magical Damage Taken.\n";
        attributeDesc += "Speed: Determines who will go first in Battle. The higher, the better the chance.\n";
        if(heroClass.equalsIgnoreCase("Mage"))
        {
            attributeDesc += "Mage Class: A capable class capable of dishing out and resisting strong magical attacks. However, they are weak to Physical Attacks.\n";
        }
        if(heroClass.equalsIgnoreCase("Warrior"))
        {
            attributeDesc += "Warrior Class: A robust class capable of withstanding many attacks. Minor weakness to Magical attacks.\n";
        }

        return attributeDesc;
    }

	/**
     * Lists the description of skills based on hero constitution and affinity.
     * 
     * Date created: October 9, 2020
     * 
     * @return temp - String
     */
    public String listAllSkillDamage()
    {
        String temp = "";
		for(int i = 0; i < regularSkills.size(); i++)
        {
			Skill tempSkill = regularSkills.get(i);
			temp += "Base Skill " + (i + 1) + ": " + tempSkill.getSkillName() + " does \n" + regularSkills.get(i).getDamage() + " DMG - Enemy ";
			if(specialization.equals("Mercenary") || specialization.equals("Pugilist") || specialization.equals("Paladin"))
			{
				temp += "Armor.\n";
			}
			else 
			{
				temp += "Resistance.\n";
			}
        }

        for(int i = 0; i < specialSkills.size(); i++)
        {
			Skill tempSkill = specialSkills.get(i);
			temp += "Special Skill " + (i + 1) + ": " + tempSkill.getSkillName() + " does \n" + specialSkills.get(i).getDamage() + " DMG - Enemy ";
			if(specialization.equals("Mercenary") || specialization.equals("Pugilist") || specialization.equals("Paladin"))
			{
				temp += "Armor.\n";
			}
			else 
			{
				temp += "Resistance.\n";
			}
        }
        return temp;
    }
}

