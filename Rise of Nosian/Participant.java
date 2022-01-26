import java.util.ArrayList;

/**
 * --------------------------------------------------------------------------
 * File name: Participant.java
 * Project name: Rise of Nosian
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Creation Date: 10/25/2021
 * Completion Date: --/--/2021
 * @version 1.00
 * --------------------------------------------------------------------------
 */

//This class was made after HeroAttributes had already been created.
//HeroAttributes has been altered to inherit from this class.

/**
 * The Participant acts as an abstract superclass for 
 * the HeroAttributes and Enemy classes.
 *
 * Date created: Apr 2, 2021
 * @author Michael Ng
 */
public abstract class Participant
{
	//Basic stats necessary for character aptitude.
	protected String name;
	protected String description;
	protected Equipment equipment;
	protected int maxHP, HP, maxSP, SP, constitution, affinity, armor, resistance, speed;
	protected int channelRating, carryWeight; 

	//Determines character growth
	protected String specialization;
	protected double hpUP, spUP, constitutionUP, affinityUP, armorUP, resistanceUP, speedUP, crUP, cwUP;
	protected int level = 1;
	protected int experience = 0;
	protected int experienceLevelUp = 100;
	
	//Keep track of participant's regular and special skills and their special effect ability.
	protected ArrayList<Skill> regularSkills = new ArrayList<>();
	protected ArrayList<Skill> specialSkills = new ArrayList<>();
	
	/**
	 * set basic stats for the participant.
	 * 
	 * @param maxHP
	 * @param HP
	 * @param maxSP
	 * @param SP
	 * @param constitution
	 * @param affinity
	 * @param armor
	 * @param resistance
	 * @param speed
	 */
	public void setBasicStats(int maxHP, int HP, int maxSP, int SP, int constitution, int affinity, int armor, int resistance, int speed)
	{
		this.maxHP = maxHP;
		this.HP = HP;
		this.maxSP = maxSP;
		this.SP = SP;
		this.constitution = constitution;
		this.affinity = affinity;
		this.armor = armor;
		this.resistance = resistance;
		this.speed = speed;
	}

	/**
	 * Set the chance for increasing the participant's stats after level up.
	 * 
	 * @param hpUP
	 * @param spUP
	 * @param constitutionUP
	 * @param affinityUP
	 * @param resistanceUP
	 * @param speedUP
	 * @param crUP
	 * @param cwUP
	 */
	public void setLevelUpStats(int hpUP, int spUP, int constitutionUP, int affinityUP, int armorUP, int resistanceUP, int speedUP, int crUP, int cwUP)
	{
		this.hpUP = hpUP;
		this.spUP = spUP;
		this.constitutionUP = constitutionUP;
		this.affinityUP = affinityUP;
		this.armorUP = armorUP;
		this.resistanceUP = resistanceUP;
		this.speedUP = speedUP;
		this.crUP = crUP;
		this.cwUP = cwUP;
	}

	/**
	 * @return name
	 */
	public String getName ( )
	{
		return name;
	}

	/**
	 * @return specialization
	 */
	public String getSpecialization()
	{
		return specialization;
	}

	/**
	 * return the description of the participant.
	 * 
	 * @return description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return equipment
	 */
	public Equipment getEquipment ( )
	{
		return equipment;
	}


	//BASIC STATS
	/**
	 * @return maxHP
	 */
	public int getMaxHP()
	{
		return maxHP;
	}

	public void addMaxHP(int maxHP)
	{
		this.maxHP += maxHP;
	}

	/**
	 * @param maxHP
	 */
	public void setMaxHP(int maxHP)
	{
		this.maxHP = maxHP;
	}

	/**
	 * @return HP
	 */
	public int getHP()
	{
		return HP;
	}

	/**
	 * Add the specified amount of HP.
	 * If HP becomes greater than maxHP, sets HP equal to maxHP.
	 * 
	 * @param HP
	 */
	public void addHP(int HP)
	{
		this.HP += HP;
		if(this.HP > maxHP)
		{
			this.HP = maxHP;
		}
	}

	/**
	 * @param HP
	 */
	public void setHP(int HP)
	{
		this.HP = HP;
	}

	/**
	 * @return SP
	 */
	public int getSP()
	{
		return SP;
	}

	/**
	 * Add the specified amount of SP.
	 * If SP becomes greater than maxSP, sets SP equal to maxSP.
	 * 
	 * @param SP
	 */
	public void addSP(int SP)
	{
		this.SP += SP;
		if(this.SP > maxSP)
		{
			this.SP = maxSP;
		}
	}

	/**
	 * @param SP
	 */
	public void setSP(int SP)
	{
		this.SP = SP;
	}

	/**
	 * @return maxSP
	 */
	public int getMaxSP()
	{
		return maxSP;
	}

	public void addMaxSP(int maxSP)
	{
		this.maxSP += maxSP;
	}

	/**
	 * @param maxSP
	 */
	public void setMaxSP(int maxSP)
	{
		this.maxSP = maxSP;
	}

	/**
	 * @return constitution
	 */
	public int getConstitution()
	{
		return constitution;
	}

	/**
	 * Add the specified amount of constitution.
	 * 
	 * @param constitution
	 */
	public void addConstitution(int constitution)
	{
		this.constitution += constitution;
	}

	/**
	 * @param constitution
	 */
	public void setConstitution(int constitution)
	{
		this.constitution = constitution;
	}

	/**
	 * @return affinity
	 */
	public int getAffinity()
	{
		return affinity;
	}

	/**
	 * Add the specified amount of affinity.
	 * 
	 * @param affinity
	 */
	public void addAffinity(int affinity)
	{
		this.affinity += affinity;
	}

	/**
	 * @param affinity
	 */
	public void setAffinity(int affinity)
	{
		this.affinity = affinity;
	}

	/**
	 * @return armor
	 */
	public int getArmor()
	{
		return armor;
	}

	/**
	 * add the amount indicated to armor. 
	 * 
	 * @param armor
	 */
	public void addArmor(int armor)
	{
		this.armor += armor;
	}

	/**
	 * @param armor
	 */
	public void setArmor(int armor)
	{
		this.armor = armor;
	}

	/**
	 * @return resistance
	 */
	public int getResistance()
	{
		return resistance;
	}

	/**
	 * add the specified amount of resistance.
	 * 
	 * @param resistance
	 */
	public void addResistance(int resistance)
	{
		this.resistance += resistance;
	}

	/**
	 * @param resistance
	 */
	public void setResistance(int resistance)
	{
		this.resistance = resistance;
	}


	/**
	 * @return speed
	 */
	public int getSpeed()
	{
		return speed;
	}

	/**
	 * Add the specified amount of speed.
	 * 
	 * @param speed
	 */
	public void addSpeed(int speed)
	{
		this.speed += speed;
	}

	/**
	 * @param speed
	 */
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}


	/**
	 * @return channelRating
	 */
	public int getChannelRating()
	{
		return channelRating;
	}

	/**
	 * add the specified amount of Channel Rating.
	 * 
	 * @param CR
	 */
	public void addChannelRating(int CR)
	{
		channelRating += CR;
	}

	/**
	 * @param CR
	 */
	public void setChannelRating(int CR)
	{
		channelRating = CR;
	}

	/**
	 * @return carryWeight
	 */
	public int getCarryWeight()
	{
		return carryWeight;
	}

	/**
	 * Add the specified amount of carry weight.
	 * 
	 * @param carryWeight
	 */
	public void addCarryWeight(int CW)
	{
		carryWeight += CW;
	}

	/**
	 * @param carryWeight
	 */
	public int setCarryWeight()
	{
		return carryWeight;
	}

	/**
	 * @return specialSkills
	 */
	public ArrayList<Skill> getSpecialSkills()
	{
		return specialSkills;
	}

	/**
	 * set the specialSkills array equal to the new array.
	 * 
	 * @param newSkills
	 */
	public void setSkills(ArrayList<Skill> newSkills)
	{
		specialSkills = newSkills;
	}

	/**
	 * return the regularSkills array.
	 * 
	 * Created: 11/10/2021
	 * @return regularSkills
	 */
	public ArrayList<Skill> getRegularSkills()
	{
		return regularSkills;
	}

	/**
     * Returns an array of skill names.
     * Used in the battle class when getting regular skill names.
     * 
     * Date Created: November 10, 2021
     * 
     * @return skills - String[]
     */
    public String[] getRegularSkillNames()
    {
        String[] skills = new String[regularSkills.size()];
        for(int i = 0; i < regularSkills.size(); i++)
        {
            skills[i] = regularSkills.get(i).getSkillName();
        }
        return skills;
    }

	/**
     * Returns an array of skill strings.
     * Used in the battle class when getting special skill names.
     * 
     * Date Created: November 10, 2021
     * 
     * @return skills - String[]
     */
    public String[] getSpecialSkillNames()
    {
        String[] skills = new String[specialSkills.size()];
        for(int i = 0; i < specialSkills.size(); i++)
        {
            skills[i] = specialSkills.get(i).getSkillName();
        }
        return skills;
    }

	/**
     * returns a list of the participant's skill names.
     * 
     * Date created: October 9, 2020
     * 
     * @return temp - String
     */
    public String getSkillSummary(boolean special)
    {
		String temp = "";

		//If boolean "special" is true - we return special skills.
		if(special) //return special skills
		{
			temp = "Your current special skills are...\n";
			//Returns a series of special skills.
			for(int i = 0; i < specialSkills.size(); i++)
			{
				temp += " - " + specialSkills.get(i).getSkillName() + "\n";
				//Looks like " - Attack"
			}
		}
		else //return regular skills.
		{
			temp = "Your current regular skills are...\n";
			//Returns a series of regular skills.
			for(int i = 0; i < regularSkills.size(); i++)
			{
				temp += " - " + regularSkills.get(i).getSkillName() + "\n";
				//Looks like " - Attack"
			}
		}
        
        return temp;
    }

	/**
	 * Add a skill to the skills array.
	 * 
	 * @param skill
	 */
	public void addSkill(Skill skill)
	{
		specialSkills.add(skill);
	}

	/**
	 * returns the skill at the specified index.
	 * 
	 * @param index		- int
	 * @return Skill	- Regular Skill
	 */
	public Skill getRegularSkill(int index)
	{
		return regularSkills.get(index);
	}

	/**
	 * returns the skill at the specified index.
	 * 
	 * @param index		- int
	 * @return Skill	- Special Skill
	 */
	public Skill getSpecialSkill(int index)
	{
		return specialSkills.get(index);
	}

	/**
	 * return the level of the character
	 * 
	 * @return level
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * return the chance of increasing each basic stat in an array.
	 * 
	 * @return statArray
	 */
	public double[] increaseStatChance()
	{
		double[] statArray = {hpUP, spUP, constitutionUP, affinityUP, armorUP, resistanceUP, speedUP, crUP, cwUP};
		return statArray;
	}



	/**
	 * The toString method of the class.     
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
		message += "\nName: " + name;
		message += "\nEquipment: " + equipment.toString ( );
		
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
	 * Level up increases the hero's level and stats, based on each stat's aptitude.
	 * 
	 * Date Created: 11/03/2021
	 */
	public String levelUp()
	{
		String resultMessage = "";

		//returns a value from 1 - 100.
		int randomNum = (int)(Math.random() * 100) + 1;
		
		//returns a value from 1 - 10.
		int randomNum2 = (int)(Math.random() * 10) + 1;

		//Increase maximum HP if the aptitude for HP is less than randomNum.
		if(hpUP <= randomNum)
		{
			maxHP += randomNum2*10;
			resultMessage += "Max HP increased by " + (randomNum2 * 10) + "HP!\n";

			//Reset randomNum 2 after each successful stat increase.
			randomNum2 = (int)(Math.random()*10) + 1;
		}

		//Reset randomNum after each if statement.
		randomNum = (int)(Math.random() * 100) + 1;
		//Increase maximum SP if the aptitude for SP is less than randomNum.
		if(spUP <= randomNum)
		{
			maxSP += randomNum2*5;
			resultMessage += "Max SP increased by " + (randomNum2 * 5) + "SP!\n";
			
			randomNum2 = (int)(Math.random()*10) + 1;
		}

		randomNum = (int)(Math.random() * 100) + 1;
		//Increase constitution if the aptitude for constitution is less than randomNum.
		if(constitutionUP <= randomNum)
		{
			constitution += randomNum2;
			resultMessage += "Constitution increased by " + randomNum2 + "!\n";
			
			randomNum2 = (int)(Math.random()*10) + 1;
		}

		randomNum = (int)(Math.random() * 100) + 1;
		//Increase affinity if the aptitude for affinity is less than randomNum.
		if(affinityUP <= randomNum)
		{
			affinity += randomNum2;
			resultMessage += "Affinity increased by " + randomNum2 + "!\n";
			
			randomNum2 = (int)(Math.random()*10) + 1;
		}

		randomNum = (int)(Math.random() * 100) + 1;
		//Increase armor if the aptitude for armor is less than randomNum.
		if(armorUP <= randomNum)
		{
			armor += randomNum2;
			resultMessage += "Armor increased by " + randomNum2 + "!\n";
			
			randomNum2 = (int)(Math.random()*10) + 1;
		}

		randomNum = (int)(Math.random() * 100) + 1;
		//Increase resistance if the aptitude for resistance is less than randomNum.
		if(resistanceUP <= randomNum)
		{
			resistance += randomNum2;
			resultMessage += "Resistance increased by " + randomNum2 + "!\n";
			
			randomNum2 = (int)(Math.random()*10) + 1;
		}

		randomNum = (int)(Math.random() * 100) + 1;
		//Increase speed if the aptitude for speed is less than randomNum.
		if(speedUP <= randomNum)
		{
			speed += randomNum2;
			resultMessage += "Speed increased by " + randomNum2 + "!\n";
			
			randomNum2 = (int)(Math.random()*10) + 1;
		}

		randomNum = (int)(Math.random() * 100) + 1;
		//Increase channel rating if the aptitude for channel rating is less than randomNum.
		if(crUP <= randomNum)
		{
			channelRating += randomNum2;
			resultMessage += "Channel Rating increased by " + randomNum2 + "!\n";
			
			randomNum2 = (int)(Math.random()*10) + 1;
		}

		randomNum = (int)(Math.random() * 100) + 1;
		//Increase carry weight if the aptitude for carry weight is less than randomNum.
		if(cwUP <= randomNum)
		{
			carryWeight += randomNum2;
			resultMessage += "Carry Weight increased by " + randomNum2 + "!\n";
			
			randomNum2 = (int)(Math.random()*10) + 1;
		}

		return resultMessage;
	}

	/**
	 * return skill informatiom.
	 * This contains the skill's name,
	 * effect, secondary effect, and damage.
	 * 
	 * @param skillName
	 * @return returnString
	 */
    public String getSkillInformation(String skillName)
    {
		Skill skillUsed = null;

        for(int i = 0; i < regularSkills.size(); i++)
        {
			String temp = regularSkills.get(i).getSkillName();
            if(temp.equals(skillName))
			{
				skillUsed = regularSkills.get(i);
				break;
			}
        }

		int damage = skillUsed.getDamage();
		int chanceProc = skillUsed.getProcChance();
		
		String effect = skillUsed.getEffect();
		String secondaryEffect = skillUsed.getSecondaryEffect();
		String target = skillUsed.getTarget();
		

		String returnString = "";

		//If there is a valid primary and secondary effect and the skill targets an enemy.
		if(!effect.equalsIgnoreCase("None") && !secondaryEffect.equalsIgnoreCase("None") && target.equals("Enemy"))
		{
			return returnString += skillName + " does " + damage + "DMG, with a " + chanceProc + "% chance of inflicting " + effect + " and " + secondaryEffect + " on the " + target + ".\n";
		}
		//If there is a valid primary and secondary effect and the skill targets an Ally.
		else if(!effect.equalsIgnoreCase("None") && !secondaryEffect.equalsIgnoreCase("None") && target.equals("Ally"))
		{
			return returnString += skillName + " heals " + damage + "HP, with a " + chanceProc + "% chance of inflicting " + effect + " and " + secondaryEffect + " on an " + target + ".\n";
		}
		//If there is a valid primary effect and the skill targets an Enemy.
		else if(!effect.equalsIgnoreCase("None") && target.equals("Enemy"))
		{
			return returnString += skillName + " does " + damage + "DMG, with a " + chanceProc + "% chance of inflicting " + effect + " on the " + target + ".\n";
		}
		//If there is a valid primary effect and the skill targets an Ally.
		else if(!effect.equalsIgnoreCase("None") && target.equals("Ally"))
		{
			return returnString += skillName + " heals " + damage + "HP, with a " + chanceProc + "% chance of inflicting " + effect + " on the " + target + ".\n";
		}
		//If there is a valid primary effect and the skill targets an Enemy.
		else if(target.equals("Enemy"))
		{
			return returnString += skillName + " does " + damage + "DMG to the " + target + ".\n";
		}
		//If there is a valid primary effect and the skill targets an Enemy.
		else if(target.equals("Ally"))
		{
			return returnString += skillName + " heals " + damage + "HP for the " + target + ".\n";
		}
		else //theoretically impossible to reach, but used in case a bug may appear.
		{
			System.out.println("Error: Target is an invalid value.\n Target value: " + target);
			return returnString += skillName + " does " + damage + "DMG to the " + target + ".\n";
		}
    }
}
