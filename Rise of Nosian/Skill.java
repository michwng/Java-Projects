/**
 * --------------------------------------------------------------------------
 * File name: Skill.java
 * Project name: Rise of Nosian
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Creation Date: 10/25/2021
 * Completion Date: --/--/2021
 * @version 1.00
 * --------------------------------------------------------------------------
 */

 /**
  * This class keeps track of a skill, its status effects, and its damage.
  * 
  * 
  * Date Created: 11/03/2021
  * Date Completed: 
  */
public class Skill
{
    private String skillName, effectName, skillDescription, effectDescription, effect, secondaryEffect;
    private String target, effectTarget, efficacyStatBase, secondaryEfficacyStatBase;
    private int procChance, effectLength;
    private int effectEfficacy, secondaryEffectEfficacy;
    private int damage;
    //Add SP Usage

    /**
     * The primary constructor for the Skills class.
     * 
     * @param skillName
     * @param effect
     * @param procChance
     * @param effectLength
     * @param effectEfficacy
     */
    public Skill(String skillName, String effectName, String effect, int procChance, int effectLength, int effectEfficacy, 
                String secondaryEffect, int secondaryEffectEfficacy, 
                int damage, String skillDescription, String effectDescription, 
                String target, String effectTarget, String efficacyStatBase, String secondaryEfficacyStatBase)
    {
        this.skillName = skillName;
        this.effectName = effectName;
        this.skillDescription = skillDescription;
        this.effectDescription = effectDescription;

        this.effect = effect;
        this.procChance = procChance;
        this.effectLength = effectLength;
        this.effectEfficacy = effectEfficacy;
        this.efficacyStatBase = efficacyStatBase;
        this.damage = damage;

        this.target = target;
        this.effectTarget = effectTarget;

        this.secondaryEffect = secondaryEffect;
        this.secondaryEffectEfficacy = secondaryEffectEfficacy;
        this.secondaryEfficacyStatBase = secondaryEfficacyStatBase;
    }

    /**
     * The secondary constructor for the Skills class.
     * Typically used when skills do not have a secondary effect or the skillName is the same as the effectName. 
     * 
     * 
     * @param skillName
     * @param effect
     * @param procChance
     * @param effectLength
     * @param effectEfficacy
     */
    public Skill(String skillName, 
                String effect, int procChance, int effectLength, int effectEfficacy, 
                int damage, String skillDescription, String effectDescription, 
                String target, String effectTarget, String efficacyStatBase)
    {
        this.skillName = skillName;
        this.effectName = effect;
        this.skillDescription = skillDescription;
        this.effectDescription = effectDescription;

        this.effect = effect;
        this.procChance = procChance;
        this.effectLength = effectLength;
        this.effectEfficacy = effectEfficacy;
        this.efficacyStatBase = efficacyStatBase;
        this.damage = damage;

        this.target = target;
        this.effectTarget = effectTarget;
        
        this.secondaryEffect = "None";
        this.secondaryEffectEfficacy = 0;
        this.secondaryEfficacyStatBase = "None";
    }


    /**
     * The tertiary constructor for the Skills class.
     * Typically used when skills do not have any effects.
     * The simplest constructor.
     * 
     * 
     * @param skillName
     * @param effect
     * @param procChance
     * @param effectLength
     * @param effectEfficacy
     */
    public Skill(String skillName, String skillDescription, int damage, String target)
    {
        this.skillName = skillName;
        this.effectName = skillName;
        this.skillDescription = skillDescription;
        this.effectDescription = "";
        
        this.effect = "None";
        this.procChance = 0;
        this.effectLength = 0;
        this.effectEfficacy = 0;
        this.efficacyStatBase = target;
        this.damage = damage;

        this.target = target;
        this.effectTarget = target;
        
        this.secondaryEffect = "None";
        this.secondaryEffectEfficacy = 0;
        this.secondaryEfficacyStatBase = "None";
    }

    /**
     * A placeholder constructor.
     */
    public Skill()
    {
        //Does nothing.
    }

    /**
     * return the effect of the skill.
     * 
     * @return
     */
    public String getEffect()
    {
        return effect;
    }

    /**
     * Return the secondary effect of a skill.
     * 
     * @return secondaryEffect
     */
    public String getSecondaryEffect()
    {
        return secondaryEffect;
    }

    /**
     * return the name of the skill.
     * 
     * @return skillName
     */
    public String getSkillName()
    {
        return skillName;
    }

    /**
     * return the skill's effect name.
     * 
     * @return effectName
     */
    public String getEffectName()
    {
        return effectName;
    }

    /**
     * return the skill's description.
     * 
     * @return skillDescription
     */
    public String getDescription()
    {
        return skillDescription;
    }

    /**
     * return the effect's description.
     * 
     * @return effectDescription
     */
    public String getEffectDescription()
    {
        return effectDescription;
    }

    /**
     * return the skill's target.
     * 
     * @return target
     */
    public String getTarget()
    {
        return target;
    }

    /**
     * return the effect's target.
     * 
     * @return effectTarget
     */
    public String getEffectTarget()
    {
        return effectTarget;
    }

    /**
     * returns who the effect takes stats from.
     * 
     * @return efficacyStatBase
     */
    public String getEfficacyStatBase()
    {
        return efficacyStatBase;
    }

    /**
     * returns who the secondary effect takes stats from.
     * 
     * @return secondaryEfficacyStatBase
     */
    public String getSecondaryEfficacyStatBase()
    {
        return secondaryEfficacyStatBase;
    }

    /**
     * return the proc chance of the effect.
     * 
     * @return procChance
     */
    public int getProcChance()
    {
        return procChance;
    }

    /**
     * return the strength of the effect.
     * 
     * @return effectEfficacy
     */
    public int getEffectEfficacy()
    {
        //Note: EffectEfficacy can also return negative values.
        return effectEfficacy;
    }

    /**
     * return the secondary effect's strength.
     * 
     * @return secondaryEffectEfficacy
     */
    public int getSecondaryEffectEfficacy()
    {
        //Note: secondaryEffectEfficacy can also return negative values.
        return secondaryEffectEfficacy;
    }

    /**
     * Return the length of the effect.
     * 
     * @return effectLength
     */
    public int getEffectLength()
    {
        return effectLength;
    }

    /**
     * return the skill's damage.
     * 
     * @return damage
     */
    public int getDamage()
    {
        return damage;
    }

    /**
     * set the skill's damage equal to the parameter.
     * 
     * @param damage
     */
    public void setDamage(int damage)
    {
        this.damage = damage;
    }


    /**
     * Update the skill's damage to the value indicated.
     * 
     * @param damage
     */
    public void updateDamage(int damage)
    {
        this.damage = damage;
    }

    /**
     * Calling this method returns a detailed description of the Skill. 
     * 
     * @see toString
     */
    public String toString()
    {
        String summary = "";

        summary += skillName;
        summary += "\nSkill Description: " + skillDescription;
        if(effectName != "None" && !effectName.equals(skillName))
        {
            summary += "\nInflicts " + effectName + ".";
            summary += "\nEffect Description: " + effectDescription;
        }
        summary += "\n--------------------\n";

        //Adds Effect Descriptions if not empty.
        if(!effectName.equals(skillName) && !effect.equals("None"))
        {
            summary += effect;
            summary += "\nPrimary Effect: " + effect;
            summary += "\nPrimary Effect Strength: " + effectEfficacy + "\n";
        }
        //Adds Secondary Effect Descriptions if not empty or null.
        if(!secondaryEffect.equals("None"))
        {
            summary += "\nSecondary Effect: " + secondaryEffect;
            summary += "\nSecondary Effect Strength: " + secondaryEffectEfficacy;
            summary += "\nChance to Proc Effects: " + procChance + "%";
            summary += "\nEffect Length: " + effectLength + "\n";
        }
        summary += "Targets: " + target;
        summary += "\nBase Damage: " + damage + "HP\n";

        return summary;
    }
}
