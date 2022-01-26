/**
 * --------------------------------------------------------------------------
 * File name: Ally.java
 * Project name: Semester Project
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 11/3/2020
 * Completion Date: 11/23/2020
 * @version 
 * --------------------------------------------------------------------------
 */

import java.util.ArrayList;
/**
  * The Ally class keeps track of
  * Allied Attributes and Stats. 
  * As well as their skills.
  *
  * This class is used in the Battle class.
  *
  * Date created: November 3, 2020
  * 
  * @author Michael Ng, ngmw01@etsu.edu
  */
public class Ally extends Participant
{
    //All fields are provided from the extended class, Participant. 

    /**
     * Zacharias assumes the opposite class of the Hero.
     * Anthiera assumes the same class as the Hero.
     * They also account for weaknesses that the hero has.
     * 
     * Formatted similarly to both the Statistics and Enemy class constructors.
     * 
     * Date Created: November 3, 2020
     * 
     * @param specialization
     */
    public Ally(String heroClass, String allyName)
    {
        name = allyName;
        if(allyName.equals("Avise"))
        {
            //description extends from participant
            description = "Your friend. Adept at Swordplay.";
            if(heroClass.equals("Enchanting Prodigy"))
            {
                //specialization and the basic stats extend from participant.
                specialization = "Fencer Prodigy";
                maxHP = 150;
                HP = 150;
                constitution = 40;
                affinity = 0;
                armor = 25;
                resistance = 15;
                speed = 45;
                

                //Basic Skills
                Skill basicSkill = new Skill("Quick Strike", "Strike at the enemy, dealing damage equal to 140% of Constitution.", (int)(constitution * 1.4), "Enemy");
                Skill basicSkill2 = new Skill("Upper Slash", "Bleed", 40, 3, 7, constitution, "Slash upwards at the enemy after charging a slash, dealing damage equal to constitution.", "Bleeds the target for 7% of their HP every turn for 3 turns.", "Enemy", "Enemy", "Enemy");
                
                //Special Skills
                Skill specialSkill = new Skill("Invigorate", "Mending", "Recovery", 100, 3, 15*level, "Rejuvenation", 5*level, 0, "Invigorate: Refresh an Ally and their SP.", "Restore an ally's HP by 15*level and SP by 5*level per turn.", "Ally", "Ally", "Constant", "Constant");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
            else
            {
                specialization = "Sage";
                maxHP = 80;
                HP = 80;
                constitution = 0;
                affinity = 30;
                armor = 0;
                resistance = 10;
                speed = 18;

                //Basic Skills
                Skill basicSkill = new Skill("Boulder", "Summon a Boulder and cast it towards the enemy. Deals damage equal to affinity * 1.7.", (int)(affinity * 1.7), "Enemy");
                Skill basicSkill2 = new Skill("Orb", "Cast a regular magical spell at the target. Deals damage equal to affinity.", affinity, "Enemy");

                //Special Skills
                Skill specialSkill = new Skill("Tome of Draining", "Drain", 50, 0, 0, (int)(affinity * 1.2), "Cast a draining spell on the enemy. Deals damage equal to affinity * 1.2.", "Heal the User for 50% of the Damage Done.", "Enemy", "Self", "Constant");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
        }
        else //For ally Anthiera
        {
            //if hero is a mage
            if(heroClass.equals("Mage"))
            {
                specialization = "Sage";
                description = "Anthiera is a formidable Sage from the Fendalian Royal Guard. \nShe deals large amounts of Magical damage.";
                maxHP = 80;
                HP = 80;
                constitution = 0;
                affinity = 30;
                armor = 0;
                resistance = 10;
                speed = 18;

                //Basic Skills
                Skill basicSkill = new Skill("Soul Grab", "Reduce HP", 60, 3, 40, affinity, "Attempt to steal the target's soul. Deals damage equal to affinity.", "(60% PROC) Reduce the Target's HP by 40 for 3 turns.", "Enemy", "Enemy", "Constant");
                Skill basicSkill2 = new Skill("Orb", "Cast a regular magical spell at the target. Deals damage equal to affinity.", affinity, "Enemy");

                //Special Skills
                Skill specialSkill = new Skill("Charm", "Pacify", 40, 3, 0, (int)(affinity * 0.7), "Cast an illusion spell on the enemy to have the team appear more majestic. Deals damage equal to affinity * 0.7.", "(40% PROC) Pacify the Target for 3 turns.", "Enemy", "Enemy", "Constant");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
            else // ally is a beserker
            {
                specialization = "Berserker";
                description = "Anthiera is a formidable Beserker from the Fendalian Royal Guard. \nShe deals large amounts of Physical damage.";
                maxHP = 100;
                HP = 100;
                constitution = 35;
                affinity = 0;
                armor = 15;
                resistance = 5;
                speed = 5;

                //Basic Skills
                Skill basicSkill = new Skill("Stun", "Stun", 30, 2, 0, (int)(constitution * 0.8), "Bludgeon the enemy with the hilt of your sword. Deals damage equal to Constitution * 0.8.", "(30% PROC) Stun the enemy for 2 turns.", "Enemy", "Enemy", "Constant");
                Skill basicSkill2 = new Skill("Slash", "Slash the enemy with a sword. Deals damage equal to constitution.", constitution, "Enemy");

                //Special Skills
                Skill specialSkill = new Skill("Relent", "Jump and perform a devestating downwards slash upon the target. Deals damage equal to 350% of the user's constitution.", (int)(constitution*3.5), "Enemy");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
        }
    }

    /**
     * Secondary constructor. 
     * Used when laoding from a save file.
     * 
     * Date Created: September 1, 2021
     * 
     * @param specialization
     */
    public Ally(String allyName, int maxHP, int HP, int constitution, int affinity, int armor, int resistance, int speed, String specialization)
    {
        name = allyName;
        if(allyName.equals("Avise"))
        {
            description = "Your friend. Adept at Swordplay.";
            if(specialization.equals("Berserker"))
            {
                this.specialization = "Berserker";
                this.maxHP = maxHP;
                this.HP = HP;
                this.constitution = constitution;
                this.affinity = affinity;
                this.armor = armor;
                this.resistance = resistance;
                this.speed = speed;
                
                //Basic Skills
                Skill basicSkill = new Skill("Quick Strike", "Strike at the enemy, dealing damage equal to 140% of Constitution.", (int)(constitution * 1.4), "Enemy");
                Skill basicSkill2 = new Skill("Upper Slash", "Bleed", 40, 3, 7, constitution, "Slash upwards at the enemy after charging a slash, dealing damage equal to constitution.", "Bleeds the terget for 7% of their HP every turn for 3 turns.", "Enemy", "Enemy", "Enemy");
                
                //Special Skills
                Skill specialSkill = new Skill("Invigorate", "Mending", "Recovery", 100, 3, 15*level, "Rejuvenation", 5*level, 30*level, "Invigorate: Refresh an Ally for (30*Level) HP and their SP.", "Restore an ally's HP by 15*level and SP by 5*level per turn.", "Ally", "Ally", "Constant", "Constant");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
            else
            {
                this.specialization = "Sage";
                this.maxHP = maxHP;
                this.HP = HP;
                this.constitution = constitution;
                this.affinity = affinity;
                this.armor = armor;
                this.resistance = resistance;
                this.speed = speed;

                //Basic Skills
                Skill basicSkill = new Skill("Boulder", "Summon a Boulder and cast it towards the enemy. Deals damage equal to affinity * 1.7.", (int)(affinity * 1.7), "Enemy");
                Skill basicSkill2 = new Skill("Orb", "Cast a regular magical spell at the target. Deals damage equal to affinity.", affinity, "Enemy");

                //Special Skills
                Skill specialSkill = new Skill("Tome of Draining", "Drain", 50, 0, 0, (int)(affinity * 1.2), "Cast a draining spell on the enemy. Deals damage equal to affinity * 1.2.", "Heal the User for 50% of the Damage Done.", "Enemy", "Self", "Constant");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
        }
        else //For ally Anthiera
        {
            //if hero is a mage
            if(specialization.equals("Sage"))
            {
                this.specialization = "Sage";
                description = "Anthiera is a formidable Sage from the Fendalian Royal Guard. \nShe deals large amounts of Magical damage.";
                this.maxHP = maxHP;
                this.HP = HP;
                this.constitution = constitution;
                this.affinity = affinity;
                this.armor = armor;
                this.resistance = resistance;
                this.speed = speed;

                //Basic Skills
                Skill basicSkill = new Skill("Soul Grab", "Reduce HP", 60, 3, 40, affinity, "Attempt to steal the target's soul. Deals damage equal to affinity.", "(60% PROC) Reduce the Target's HP by 40 for 3 turns.", "Enemy", "Enemy", "Constant");
                Skill basicSkill2 = new Skill("Orb", "Cast a regular magical spell at the target. Deals damage equal to affinity.", affinity, "Enemy");

                //Special Skills
                Skill specialSkill = new Skill("Charm", "Pacify", 40, 3, 0, (int)(affinity * 0.7), "Cast an illusion spell on the enemy to have the team appear more majestic. Deals damage equal to affinity * 0.7.", "(40% PROC) Pacify the Target for 3 turns.", "Enemy", "Enemy", "Constant");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
            else // ally is a beserker
            {
                this.specialization = "Berserker";
                description = "Anthiera is a formidable Beserker from the Fendalian Royal Guard. \nShe deals large amounts of Physical damage.";
                this.maxHP = maxHP;
                this.HP = HP;
                this.constitution = constitution;
                this.affinity = affinity;
                this.armor = armor;
                this.resistance = resistance;
                this.speed = speed;
                
                //Basic Skills
                Skill basicSkill = new Skill("Stun", "Stun", 30, 2, 0, (int)(constitution * 0.8), "Bludgeon the enemy with the hilt of your sword. Deals damage equal to Constitution * 0.8.", "(30% PROC) Stun the enemy for 2 turns.", "Enemy", "Enemy", "Constant");
                Skill basicSkill2 = new Skill("Slash", "Slash the enemy with a sword. Deals damage equal to constitution.", constitution, "Enemy");

                //Special Skills
                Skill specialSkill = new Skill("Relent", "Jump and perform a devestating downwards slash upon the target. Deals damage equal to 350% of the user's constitution.", (int)(constitution*3.5), "Enemy");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
        }
    }

    //Mutator Methods
    /**
     * Lists all of the Ally's Current Attributes.
     * Can also be considered the toString() method.
     * 
     * Date created: November 4, 2020
     * 
     * @return allAttributes - String
     */
    public String listAttributes()
    {
        String allAttributes = "Here are " + name + "'s current Attributes. \n";
        allAttributes += "Max HP: " + maxHP + "\n";
        allAttributes += "HP: " + HP + "\n";
        allAttributes += "Constitution: " + constitution + "\n";
        allAttributes += "Affinity: " + affinity + "\n";
        allAttributes += "Armor: " + armor + "\n";
        allAttributes += "Resistance: " + resistance + "\n";
        allAttributes += "Speed: " + speed;

        return allAttributes;
    }

    /**
     * Lists all of the Ally's Attributes and 
     * skill information.
     * 
     * Date created: November 16, 2020
     * 
     * @return allAttributes - String
     */
    public String getAllyInformation()
    {
        String message = name + "\n____________________\n";
        message += "Ally Class: " + specialization + "\n";
        message += "Description: " + description + "\n";
        message += "Max HP: " + maxHP + "\n";
        message += "HP: " + HP + "\n";
        message += "Constitution: " + constitution + "\n";
        message += "Affinity: " + affinity + "\n";
        message += "Armor: " + armor + "\n";
        message += "Resistance: " + resistance + "\n";
        message += "Speed: " + speed;
        

        message += "\n\n-------------------\nRegular Skills\n-------------------";
        for(int i = 0; i < regularSkills.size(); i++)
        {
            message += "Skill " + (i + 1) + ": " + regularSkills.get(i) + "\n";
            message += regularSkills.get(i) + "\n";
            message += regularSkills.get(i).getDescription() + "\n";
        }
        
        message += "\n\n-------------------\nSpecial Skills\n-------------------";
        for(int i = 0; i < specialSkills.size(); i++)
        {
            message += "Skill " + (i + 1) + ": " + specialSkills.get(i) + "\n";
            message += specialSkills.get(i) + "\n";
            message += specialSkills.get(i).getDescription() + "\n";
        }

        return message;
    }

    /**
     * Lists the ally's skills in String format.
     * 
     * Date created: November 4, 2020
     * 
     * @return temp - String
     */
    public String getAllySkills()
    {
        String temp = name + "'s current skills are...\n";
        temp += "\n====================Regular Skills====================\n";
        for(int i = 0; i < regularSkills.size(); i++)
        {
            temp += "Skill " + (i + 1) + ": " + regularSkills.get(i) + "\n";
            //temp += regularSkills.get(i).getDescription() + "\n";
        }
        
        temp += "\n====================Special Skills====================\n";
        for(int i = 0; i < specialSkills.size(); i++)
        {
            temp += "Skill " + (i + 1) + ": " + specialSkills.get(i) + "\n";
            //temp += specialSkills.get(i).getDescription() + "\n";
        }
        return temp;
    }


    /**
     * returns an array of the ally's skills.
     * 
     * Date created: November 4, 2020
     * 
     * @return temp - String
     */
    public ArrayList<Skill> getAllySkillsArrayList()
    {
        ArrayList<Skill> temp = new ArrayList<>();
        //Add regular skills to the temp array.
        for(int i = 0; i < regularSkills.size(); i++)
        {
            temp.add(regularSkills.get(i));
        }
        
        //Add special skills to the temp array.
        for(int i = 0; i < specialSkills.size(); i++)
        {
            temp.add(specialSkills.get(i));
        }
        return temp;
    }


    


    /**
     * returns a string informing the user about the ally gaining a new skill
     * if the Hero reaches a certain level. If not, returns an empty string.
     * Ally Level grows alongside the Hero's level.
     * 
     * Date created: November 4, 2020
     * 
     * @param allyLevel - int
     * @return String - return depends on the heroLevel.
     */
    public String checkAllyLevel(int heroLevel)
    {
        Skill specialSkill = new Skill();
        updateSkillDamage();
        if(specialization.equals("Sage"))
        {
            if(heroLevel == 3)
            {
                specialSkill = new Skill("Restore", "Heal the user or an ally with an amount equal to Affinity * 0.8, depending on who has lower HP percentage.", (int)(affinity*0.8), "Ally/Self");
                specialSkills.add(specialSkill);
                return "\nThe Ally has gained a new skill!: Restore - Heal the user or an ally with an amount equal to Affinity * 0.8, depending on who has lower HP percentage.";
            }
            if(heroLevel == 5)
            {
                specialSkill = new Skill("Enlightening", "Shower the Enemy in Holy Light. Deals damage equal to Affinity * 2", affinity*2, "Enemy");
                specialSkills.add(specialSkill);
                return "\nThe Ally has gained a new skill!: Enlightening - Deal damage to the target with an amount equal to Affinity * 2.";
            }
            if(heroLevel == 15)
            {
                specialSkill = new Skill("Spear of Light", "Bleed", 40, 5, 15, (int)(affinity * 2.3), "Summon a Spear of Light to eviscerate the enemy. Deals damage equal to Affinity * 2.3.", "(40% PROC) Bleed the target for 15HP over 5 turns.", "Enemy", "Enemy", "Constant");
                specialSkills.add(specialSkill);
                return "\nThe Ally has gained a new skill!: Spear of Light - Deal damage to the target with an amount equal to Affinity * 2.3 with a 40% chance to Bleed the target for 15HP over 5 turns.";
            }
        }
        else
        {
            if(heroLevel == 3)
            {
                specialSkill = new Skill("Courage", "Heal the user or an ally with an amount equal to Constitution * 0.8, depending on who has lower HP percentage.", (int)(constitution*0.8), "Ally/Self");
                specialSkills.add(specialSkill);
                return "\nThe Ally has gained a new skill!: Courage - Heal the user or an ally with an amount equal to Constitution * 0.8, depending on who has lower HP percentage.";
            }
            if(heroLevel == 5)
            {
                specialSkill = new Skill("Weaken", "Weaken", 40, 4, 15, constitution, "Slash at the Enemy's arms to attempt to weaken them. Deals damage equal to constitution.", "(40% PROC) Weaken the enemy for 15 constitution for 4 turns", "Enemy", "Enemy", "Constant");
                specialSkills.add(specialSkill);
                return "\nThe Ally has gained a new skill!: Weaken: Slash at the Enemy's arms to attempt to weaken them. Deals damage equal to constitution and has a 40% chance to weaken the enemy for 15 constitution for 4 turns.";
            }
            if(heroLevel == 15)
            {
                specialSkill = new Skill("Banesmark", "Bleed", 50, 15, 10, (int)(constitution * 2.3), "Unsheathe the Legendary Banesmark and perform a flurry of attacks on the enemy. Deals damage equal to constitution * 2.3.", "(50% PROC) Bleed the Target for 10HP every turn for 5 turns.", "Enemy", "Enemy", "Constant");
                specialSkills.add(specialSkill);
                return "\nThe Ally has gained a new skill!: Banesmark - Unsheathe the Legendary Banesmark and perform a flurry of attacks on the enemy. Deals damage equal to constitution * 2.3 with a 50% chance of bleeding the target for 10HP every turn for 5 turns.";
            }
        }
        return "";
    }

    /**
     * Used mainly for constructing Anthiera. 
     * Restores lost skills in previous level ups.
     * 
     * Date created: November 19, 2020
     * 
     * @param heroLevel
     * @return String
     */
    public String addSkills(int heroLevel)
    {
        String message = "";
        Skill specialSkill = new Skill();
        updateSkillDamage();
        if(specialization.equals("Sage"))
        {
            if(heroLevel == 3)
            {
                specialSkill = new Skill("Restore", "Heal the user or an ally with an amount equal to Affinity * 0.8, depending on who has lower HP percentage.", (int)(affinity*0.8), "Ally/Self");
                specialSkills.add(specialSkill);
                message += "\nThe Ally has gained a new skill!: Restore - Heal the user or an ally with an amount equal to Affinity * 0.8, depending on who has lower HP percentage.";
            }
            if(heroLevel == 5)
            {
                specialSkill = new Skill("Enlightening", "Shower the Enemy in Holy Light. Deals damage equal to Affinity * 2", affinity*2, "Enemy");
                specialSkills.add(specialSkill);
                message += "\nThe Ally has gained a new skill!: Enlightening - Deal damage to the target with an amount equal to Affinity * 2.";
            }
            if(heroLevel == 15)
            {
                specialSkill = new Skill("Spear of Light", "Bleed", 40, 5, 15, (int)(affinity * 2.3), "Summon a Spear of Light to eviscerate the enemy. Deals damage equal to Affinity * 2.3.", "(40% PROC) Bleed the target for 15HP over 5 turns.", "Enemy", "Enemy", "Constant");
                specialSkills.add(specialSkill);
                message += "\nThe Ally has gained a new skill!: Spear of Light - Deal damage to the target with an amount equal to Affinity * 2.3 with a 40% chance to Bleed the target for 15HP over 5 turns.";
            }
        }
        else
        {
            if(heroLevel == 3)
            {
                specialSkill = new Skill("Courage", "Heal the user or an ally with an amount equal to Constitution * 0.8, depending on who has lower HP percentage.", (int)(constitution*0.8), "Ally/Self");
                specialSkills.add(specialSkill);
                message += "\nThe Ally has gained a new skill!: Courage - Heal the user or an ally with an amount equal to Constitution * 0.8, depending on who has lower HP percentage.";
            }
            if(heroLevel == 5)
            {
                specialSkill = new Skill("Weaken", "Weaken", 40, 4, 15, constitution, "Slash at the Enemy's arms to attempt to weaken them. Deals damage equal to constitution.", "(40% PROC) Weaken the enemy for 15 constitution for 4 turns", "Enemy", "Enemy", "Constant");
                specialSkills.add(specialSkill);
                message += "\nThe Ally has gained a new skill!: Weaken: Slash at the Enemy's arms to attempt to weaken them. Deals damage equal to constitution and has a 40% chance to weaken the enemy for 15 constitution for 4 turns.";
            }
            if(heroLevel == 15)
            {
                specialSkill = new Skill("Banesmark", "Bleed", 50, 15, 10, (int)(constitution * 2.3), "Unsheathe the Legendary Banesmark and perform a flurry of attacks on the enemy. Deals damage equal to constitution * 2.3.", "(50% PROC) Bleed the Target for 10HP every turn for 5 turns.", "Enemy", "Enemy", "Constant");
                specialSkills.add(specialSkill);
                message += "\nThe Ally has gained a new skill!: Banesmark - Unsheathe the Legendary Banesmark and perform a flurry of attacks on the enemy. Deals damage equal to constitution * 2.3 with a 50% chance of bleeding the target for 10HP every turn for 5 turns.";
            }
        }
        return message;
    }

    public void updateSkillDamage()
    {
        if(name.equals("Avise"))
        {
            //description extends from participant
            description = "Your friend. Adept at Swordplay.";
            if(specialization.equals("Fencer Prodigy"))
            {
                //Basic Skills
                Skill basicSkill = new Skill("Quick Strike", "Strike at the enemy, dealing damage equal to 140% of Constitution.", (int)(constitution * 1.4), "Enemy");
                Skill basicSkill2 = new Skill("Upper Slash", "Bleed", 40, 3, 7, constitution, "Slash upwards at the enemy after charging a slash, dealing damage equal to constitution.", "Bleeds the terget for 7% of their HP every turn for 3 turns.", "Enemy", "Enemy", "Enemy");
                
                //Special Skills
                Skill specialSkill = new Skill("Invigorate", "Mending", "Recovery", 100, 3, 15*level, "Rejuvenation", 5*level, 0, "Invigorate: Refresh an Ally and their SP.", "Restore an ally's HP by 15*level and SP by 5*level per turn.", "Ally", "Ally", "Constant", "Constant");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
            else
            {
                //Basic Skills
                Skill basicSkill = new Skill("Boulder", "Summon a Boulder and cast it towards the enemy. Deals damage equal to affinity * 1.7.", (int)(affinity * 1.7), "Enemy");
                Skill basicSkill2 = new Skill("Orb", "Cast a regular magical spell at the target. Deals damage equal to affinity.", affinity, "Enemy");

                //Special Skills
                Skill specialSkill = new Skill("Tome of Draining", "Drain", 50, 0, 0, (int)(affinity * 1.2), "Cast a draining spell on the enemy. Deals damage equal to affinity * 1.2.", "Heal the User for 50% of the Damage Done.", "Enemy", "Self", "Constant");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
        }
        else //For ally Anthiera
        {
            //if hero is a mage
            if(specialization.equals("Mage"))
            {
                //Basic Skills
                Skill basicSkill = new Skill("Soul Grab", "Reduce HP", 60, 3, 40, affinity, "Attempt to steal the target's soul. Deals damage equal to affinity.", "(60% PROC) Reduce the Target's HP by 40 for 3 turns.", "Enemy", "Enemy", "Constant");
                Skill basicSkill2 = new Skill("Orb", "Cast a regular magical spell at the target. Deals damage equal to affinity.", affinity, "Enemy");

                //Special Skills
                Skill specialSkill = new Skill("Charm", "Pacify", 40, 3, 0, (int)(affinity * 0.7), "Cast an illusion spell on the enemy to have the team appear more majestic. Deals damage equal to affinity * 0.7.", "(40% PROC) Pacify the Target for 3 turns.", "Enemy", "Enemy", "Constant");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
            else // ally is a beserker
            {
                //Basic Skills
                Skill basicSkill = new Skill("Stun", "Stun", 30, 2, 0, (int)(constitution * 0.8), "Bludgeon the enemy with the hilt of your sword. Deals damage equal to Constitution * 0.8.", "(30% PROC) Stun the enemy for 2 turns.", "Enemy", "Enemy", "Constant");
                Skill basicSkill2 = new Skill("Slash", "Slash the enemy with a sword. Deals damage equal to constitution.", constitution, "Enemy");

                //Special Skills
                Skill specialSkill = new Skill("Relent", "Jump and perform a devestating downwards slash upon the target. Deals damage equal to 350% of the user's constitution.", (int)(constitution*3.5), "Enemy");
                
                //Add skills to skills arrays.
                regularSkills.add(basicSkill);
                regularSkills.add(basicSkill2);
                specialSkills.add(specialSkill);
            }
        }
    }
}
