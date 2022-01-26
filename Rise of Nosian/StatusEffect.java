/**
 * --------------------------------------------------------------------------
 * File name: StatusEffect.java
 * Project name: Rise of Nosian
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 11/3/2020
 * Completion Date: 11/23/2020
 * Updated: 09/01/2021
 * @version 1.1
 * --------------------------------------------------------------------------
 */
import java.util.Random;

/**
  * The StatusEffect Class keeps track of
  * status effects on the Hero and enemy
  * and their turns until expiration.
  *
  * This class is used in the Battle class.
  *
  * Date created: November 3, 2020
  * 
  * @author Michael Ng, ngmw01@etsu.edu
  */
public class StatusEffect 
{
    private Hero nosian;
    private String heroName;
    private String heroStatusEffect;
    private String secondaryHeroStatusEffect;
    private String heroStatusEffectDescription;
    private int heroEffectEfficacy;
    private int secondaryHeroEffectEfficacy;
    private int heroEffectLength;

    private Enemy enemy;
    private String enemyName;
    private String enemyStatusEffect;
    private String secondaryEnemyStatusEffect;
    private String enemyStatusEffectDescription;
    private int enemyEffectEfficacy;
    private int secondaryEnemyEffectEfficacy;
    private int enemyEffectLength;
    
    //May not be used. Depends on the battle.
    private Enemy enemy2;
    private String enemy2Name;
    private String enemy2StatusEffect;
    private String secondaryEnemy2StatusEffect;
    private String enemy2StatusEffectDescription;
    private int enemy2EffectEfficacy;
    private int secondaryEnemy2EffectEfficacy;
    private int enemy2EffectLength;
    
    private Ally ally;
    private String allyName;
    private String allyStatusEffect;
    private String secondaryAllyStatusEffect;
    private String allyStatusEffectDescription;
    private int allyEffectEfficacy;
    private int secondaryAllyEffectEfficacy;
    private int allyEffectLength;
    
    //May not be used. Depends on the battle.
    private Ally ally2;
    private String ally2Name;
    private String ally2StatusEffect;
    private String secondaryAlly2StatusEffect;
    private String ally2StatusEffectDescription;
    private int ally2EffectEfficacy;
    private int secondaryAlly2EffectEfficacy;
    private int ally2EffectLength;

    /**
     * The primary constructor for the StatusEffect Class.
     * Used for smaller battles, with 1 ally and/or enemy.
     * 
     * Date Created: November 3, 2020
     * 
     * @param nosian
     * @param enemy
     */
    public StatusEffect(Hero nosian, Enemy enemy, Ally ally)
    {
        this.nosian = nosian;
        heroName = nosian.getName();
        this.enemy = enemy;
        this.ally = ally;

        enemyName = enemy.getEnemyName();
        if(ally != null)
        {
            allyName = ally.getName();
        }

        heroStatusEffect = "Healthy";
        heroStatusEffectDescription = "Unaffected by any Status Effect.";
        heroEffectEfficacy = 0;
        heroEffectLength = 0;

        enemyStatusEffect = "Healthy";
        enemyStatusEffectDescription = "Unaffected by any Status Effect.";
        enemyEffectEfficacy = 0;
        enemyEffectLength = 0;

        allyStatusEffect = "Healthy";
        allyStatusEffectDescription = "Unaffected by any Status Effect.";
        allyEffectEfficacy = 0;
        allyEffectLength = 0;
    }

    
    /**
     * The secondary constructor for the StatusEffect Class.
     * Used for large battles with 2 enemies and allies.
     * 
     * Date Created: November 3, 2020
     * 
     * @param nosian
     * @param enemy
     */
    public StatusEffect(Hero nosian, Ally ally, Ally ally2, Enemy enemy, Enemy enemy2)
    {
        this.nosian = nosian;
        heroName = nosian.getName();
        this.ally = ally;
        this.ally2 = ally2;
        this.enemy = enemy;
        this.enemy2 = enemy2;

        enemyName = enemy.getEnemyName();
        //Ally 2 may not be initialized - this would make a 2V2 fight.
        if(ally2 != null)
        {
            allyName = ally.getName();
        }

        heroStatusEffect = "Healthy";
        secondaryHeroStatusEffect = "Healthy";
        heroStatusEffectDescription = "Unaffected by any Status Effect.";
        heroEffectEfficacy = 0;
        secondaryHeroEffectEfficacy = 0;
        heroEffectLength = 0;

        enemyStatusEffect = "Healthy";
        secondaryEnemyStatusEffect = "Healthy";
        enemyStatusEffectDescription = "Unaffected by any Status Effect.";
        enemyEffectEfficacy = 0;
        secondaryEnemyEffectEfficacy = 0;
        enemyEffectLength = 0;

        enemy2StatusEffect = "Healthy";
        secondaryEnemy2StatusEffect = "Healthy";
        enemy2StatusEffectDescription = "Unaffected by any Status Effect.";
        enemy2EffectEfficacy = 0;
        secondaryEnemy2EffectEfficacy = 0;
        enemy2EffectLength = 0;

        allyStatusEffect = "Healthy";
        secondaryAllyStatusEffect = "Healthy";
        allyStatusEffectDescription = "Unaffected by any Status Effect.";
        allyEffectEfficacy = 0;
        secondaryAllyEffectEfficacy = 0;
        allyEffectLength = 0;

        ally2StatusEffect = "Healthy";
        secondaryAlly2StatusEffect = "Healthy";
        ally2StatusEffectDescription = "Unaffected by any Status Effect.";
        ally2EffectEfficacy = 0;
        secondaryAlly2EffectEfficacy = 0;
        ally2EffectLength = 0;
    }
    

//List of Negative Status Effects: Reduced HP, Poisoned, Bleeding, Weakened, Pacified, Headache, Silenced, Broken Armor, Sundered, Mental Collapse, Submission, Crippled, Immobilized, Stunned, Frozen
//List of Positive Status Effects: Recovery, Rejuvenation.

    /**
     * Reduces the Target's HP by the specified amount for a specified number of turns.
     * The chance to proc reduceHP is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param amount
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean reduceHP(String target, int amount, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, reduces the target's HP.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Reduced HP";
                heroEffectEfficacy = amount;
                heroStatusEffectDescription = "Reduced HP: " + heroName + "'s Max HP is reduced by " + amount + " for " + heroEffectLength + " turns!";
                nosian.setMaxHP(nosian.getMaxHP() - heroEffectEfficacy);
                nosian.setHP(nosian.getHP() - heroEffectEfficacy);
                if(nosian.getHP() < 0)
                {
                    nosian.setHP(0);
                }
                System.out.println("The Hero has Reduced HP! Max HP is reduced by " + amount + " for " + heroEffectLength + " turns!");
                return true;
            }
            if(target.equals(enemyName))
            {
                enemyEffectLength = turns;
                enemyStatusEffect = "Reduced HP";
                enemyEffectEfficacy = amount;
                enemyStatusEffectDescription = "Reduced HP: " + enemyName + "'s Max HP is reduced by " + amount + " for " + enemyEffectLength + " turns!";
                enemy.setEnemyMaxHP(enemy.getEnemyMaxHP() - enemyEffectEfficacy);
                enemy.setEnemyHP(enemy.getEnemyHP() - enemyEffectEfficacy);
                if(enemy.getEnemyHP() < 0)
                {
                    enemy.setEnemyHP(0);
                }
                System.out.println(enemyName + " has Reduced HP! " + enemyName + "'s HP is reduced by " + amount + " for " + enemyEffectLength + " turns!");
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Reduced HP";
                allyEffectEfficacy = amount;
                allyStatusEffectDescription = "Reduced HP: " + allyName + "'s Max HP is reduced by " + amount + " for " + allyEffectLength + " turns!";
                ally.setMaxHP(ally.getMaxHP() - allyEffectEfficacy);
                ally.setHP(ally.getHP() - allyEffectEfficacy);
                if(ally.getHP() < 0)
                {
                    ally.setHP(0);
                }
                System.out.println(allyName + " has Reduced HP! Max HP is reduced by " + amount + " for " + allyEffectLength + " turns!");
                return true;
            }
        }
        return false;
    }
    /**
     * Reduces the Target's HP by the specified amount per turn for a specified number of turns.
     * The chance to proc poison is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param amount
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean poison(String target, int amount, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, poisons the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Poisoned";
                heroEffectEfficacy = amount;
                heroStatusEffectDescription = "Poisoned: " + heroName + " will take " + amount + " damage every turn for " + heroEffectLength + " turns.";
                System.out.println(heroName + " was Poisoned! HP will be reduced by " + amount + " every turn for " + heroEffectLength + " turns!");
                return true;
            }
            if(target.equals(enemyName))
            {
                enemyEffectLength = turns;
                enemyStatusEffect = "Poisoned";
                enemyEffectEfficacy = amount;
                enemyStatusEffectDescription = "Poisoned: " + enemyName + " will take " + amount + " damage every turn for " + enemyEffectLength + " turns.";
                System.out.println(enemyName + " was poisoned! " + enemyName + "'s HP will be reduced by " + amount + " every turn for " + enemyEffectLength + " turns!");
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Poisoned";
                allyEffectEfficacy = amount;
                allyStatusEffectDescription = "Poisoned: " + allyName + " will take " + amount + " damage every turn for " + allyEffectLength + " turns.";
                System.out.println(allyName + " was Poisoned! HP will be reduced by " + amount + " every turn for " + allyEffectLength + " turns!");
                return true;
            }
        }
        return false;
    }
    /**
     * Reduces the Target's HP by the specified amount for a specified number of turns.
     * The chance to proc bleed is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param amount
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean bleed(String target, int amount, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, bleeds the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Bleeding";
                heroEffectEfficacy = amount;
                heroStatusEffectDescription = "Bleeding: " + heroName + " will take " + amount + " damage every turn for " + heroEffectLength + " turns.";
                System.out.println(heroName + " is Bleeding! HP will be reduced by " + amount + " every turn for " + heroEffectLength + " turns!");
                return true;
            }
            if(target.equals(enemyName))
            {
                enemyEffectLength = turns;
                enemyStatusEffect = "Bleeding";
                enemyEffectEfficacy = amount;
                enemyStatusEffectDescription = "Bleeding: " + enemyName + " will take " + amount + " damage every turn for " + enemyEffectLength + " turns.";
                System.out.println(enemyName + " is Bleeding! " + enemyName + "'s HP will be reduced by " + amount + " every turn for " + enemyEffectLength + " turns!");
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Bleeding";
                allyEffectEfficacy = amount;
                allyStatusEffectDescription = "Bleeding: " + allyName + " will take " + amount + " damage every turn for " + allyEffectLength + " turns.";
                System.out.println(allyName + " is Bleeding! HP will be reduced by " + amount + " every turn for " + allyEffectLength + " turns!");
                return true;
            }
        }
        System.out.println("False");
        return false;
    }
    /**
     * Reduces the Target's Constitution by the specified amount for a specified number of turns.
     * The chance to proc weaken is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param amount
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean weaken(String target, int amount, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;

        //If randomNum is lower than chance, weakens the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Weakened";
                if(amount > nosian.getConstitution())
                {
                    amount = nosian.getConstitution();
                }
                heroEffectEfficacy = amount;
                heroStatusEffectDescription = "Weakened: " + heroName + "'s Constitution is reduced by " + amount + " for " + heroEffectLength + " turns!";
                nosian.setConstitution(nosian.getConstitution() - heroEffectEfficacy);
                System.out.println(heroName + " was Weakened! Constitution is reduced by " + amount + " for " + heroEffectLength + " turns!");
                nosian.updateSkillDamage();
                return true;
            }
            if(target.equals(enemyName))
            {
                enemyEffectLength = turns;
                enemyStatusEffect = "Weakened";
                if(amount > enemy.getEnemyConstitution())
                {
                    amount = enemy.getEnemyConstitution();
                }
                enemyEffectEfficacy = amount;
                enemyStatusEffectDescription = "Weakened: " + enemyName + "'s Constitution is reduced by " + amount + " for " + enemyEffectLength + " turns!";
                enemy.addEnemyConstitution(-enemyEffectEfficacy);
                System.out.println(enemyName + " was Weakened! " + enemyName + "'s Constitution is reduced by " + amount + " for " + enemyEffectLength + " turns!");
                enemy.updateSkillDamage(enemyStatusEffect, enemyEffectEfficacy);
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Weakened";
                if(amount > ally.getConstitution())
                {
                    amount = ally.getConstitution();
                }
                allyEffectEfficacy = amount;
                allyStatusEffectDescription = "Weakened: " + allyName + "'s Constitution is reduced by " + amount + " for " + allyEffectLength + " turns!";;
                ally.addConstitution(-allyEffectEfficacy);
                System.out.println(allyName + " was Weakened! Constitution is reduced by " + amount + " for " + allyEffectLength + " turns!");
                ally.updateSkillDamage();
                return true;
            }
        }
        return false;
    }
    /**
     * Reduces the Target's Constitution to 0 for a specified amount of turns.
     * The chance to proc pacify is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean pacify(String target, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, pacifies the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Pacified";
                heroEffectEfficacy = nosian.getConstitution();
                heroStatusEffectDescription = "Pacified: " + heroName + "'s Constitution is reduced to 0 for " + heroEffectLength + " turns!";
                nosian.setConstitution(0);
                System.out.println(heroName + " was Pacified! Constitution is reduced to 0 for " + heroEffectLength + " turns!");
                nosian.updateSkillDamage();
                return true;
            }
            if(target.equals(enemyName))
            {
                enemyEffectLength = turns;
                enemyStatusEffect = "Pacified";
                enemyEffectEfficacy = enemy.getEnemyConstitution();
                enemyStatusEffectDescription = "Pacified: " + enemyName + "'s Constitution is reduced to 0 for " + enemyEffectLength + " turns!";
                enemy.setEnemyConstitution(0);
                System.out.println(enemyName + " was Pacified! " + enemyName + "'s Constitution is reduced to 0 for " + enemyEffectLength + " turns!");
                enemy.updateSkillDamage(enemyStatusEffect, enemyEffectEfficacy);
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Pacified";
                allyEffectEfficacy = ally.getConstitution();
                allyStatusEffectDescription = "Pacified: " + allyName + "'s Constitution is reduced to 0 for " + allyEffectLength + " turns!";;
                ally.setConstitution(0);
                System.out.println(allyName + " was Pacified! Constitution is reduced to 0 for " + allyEffectLength + " turns!");
                ally.updateSkillDamage();
                return true;
            }
        }
        return false;
    }

    /**
     * Reduces the Target's Affinity by the specified amount for a specified number of turns.
     * The chance to proc headache is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param amount
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean headache(String target, int amount, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, gives the target a headache.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Headache";
                if(amount > nosian.getAffinity())
                {
                    amount = nosian.getAffinity();
                }
                heroEffectEfficacy = amount;
                heroStatusEffectDescription = "Headache: " + heroName + "'s Affinity is reduced by " + amount + " for " + heroEffectLength + " turns!";
                nosian.setAffinity(nosian.getAffinity() - heroEffectEfficacy);
                System.out.println(heroName + " has a Headache! Affinity is reduced by " + amount + " for " + heroEffectLength + " turns!");
                nosian.updateSkillDamage();
                return true;
            }
            if(target.equals(enemyName))
            {
                enemyEffectLength = turns;
                enemyStatusEffect = "Headache";
                if(amount > enemy.getEnemyAffinity())
                {
                    amount = enemy.getEnemyAffinity();
                }
                enemyEffectEfficacy = amount;
                enemyStatusEffectDescription = "Headache: " + enemyName + "'s Affinity is reduced by " + amount + " for " + enemyEffectLength + " turns!";
                enemy.addEnemyAffinity(-enemyEffectEfficacy);
                System.out.println(enemyName + " has a Headache! " + enemyName + "'s Affinity is reduced by " + amount + " for " + enemyEffectLength + " turns!");
                enemy.updateSkillDamage(enemyStatusEffect, enemyEffectEfficacy);
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Headache";
                if(amount > ally.getAffinity())
                {
                    amount = ally.getAffinity();
                }
                allyEffectEfficacy = amount;
                allyStatusEffectDescription = "Headache: " + allyName + "'s Affinity is reduced by " + amount + " for " + allyEffectLength + " turns!";;
                ally.addAffinity(-allyEffectEfficacy);
                System.out.println(allyName + " has a Headache! Affinity is reduced by " + amount + " for " + allyEffectLength + " turns!");
                ally.updateSkillDamage();
                return true;
            }
        }
        return false;
    }

    /**
     * Reduces the Target's Affinity to 0 for a specified number of turns.
     * The chance to proc silence is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean silence(String target, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, Silences the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Silenced";
                heroEffectEfficacy = nosian.getAffinity();
                heroStatusEffectDescription = "Silenced: " + heroName + "'s Affinity is reduced to 0 for " + heroEffectLength + " turns!";
                nosian.setAffinity(0);
                System.out.println(heroName + " was Silenced! Affinity is reduced to 0 for " + heroEffectLength + " turns!");
                nosian.updateSkillDamage();
                return true;
            }
            if(target.equals(enemyName))
            {
                enemyEffectLength = turns;
                enemyStatusEffect = "Silenced";
                enemyEffectEfficacy = enemy.getEnemyAffinity();
                enemyStatusEffectDescription = "Silenced: " + enemyName + "'s Affinity is reduced to 0 for " + enemyEffectLength + " turns!";
                enemy.setEnemyAffinity(0);
                System.out.println(enemyName + " was Silenced! " + enemyName + "'s Affinity is reduced to 0 for " + enemyEffectLength + " turns!");
                enemy.updateSkillDamage(enemyStatusEffect, enemyEffectEfficacy);
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Silenced";
                allyEffectEfficacy = ally.getAffinity();
                allyStatusEffectDescription = "Silenced: " + allyName + "'s Affinity is reduced to 0 for " + allyEffectLength + " turns!";;
                ally.setAffinity(0);
                System.out.println(allyName + " was Silenced! Affinity is reduced to 0 for " + allyEffectLength + " turns!");
                ally.updateSkillDamage();
                return true;
            }
        }
        return false;
    }

    /**
     * Reduces the Target's Armor by the specified amount for a specified number of turns.
     * This cannot be procced on Enemies.
     * The chance to proc armorBreak is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param amount
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean armorBreak(String target, int amount, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, Breaks the target's Armor.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Broken Armor";
                if(amount > nosian.getArmor())
                {
                    amount = nosian.getArmor();
                }
                heroEffectEfficacy = amount;
                heroStatusEffectDescription = "Broken Armor: " + heroName + "'s Armor is reduced by " + amount + " for " + heroEffectLength + " turns!";
                nosian.setArmor(nosian.getArmor() - heroEffectEfficacy);
                System.out.println(heroName + "'s Armor Broke! Armor is reduced by " + amount + " for " + heroEffectLength + " turns!");
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Broken Armor";
                if(amount > ally.getArmor())
                {
                    amount = ally.getArmor();
                }
                allyEffectEfficacy = amount;
                allyStatusEffectDescription = "Broken Armor: " + allyName + "'s Armor is reduced by " + amount + " for " + allyEffectLength + " turns!";;
                ally.addArmor(-allyEffectEfficacy);
                System.out.println(allyName + "'s Armor Broke! Armor is reduced by " + amount + " for " + allyEffectLength + " turns!");
                return true;
            }
        }
        return false;
    }

    /**
     * Reduces the Target's Armor to 0 for a specified number of turns.
     * This cannot be procced on enemies.
     * The chance to proc sunder is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean sunder(String target, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, Sunders the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Sundered";
                heroEffectEfficacy = nosian.getArmor();
                heroStatusEffectDescription = "Sundered: " + heroName + "'s Armor is reduced to 0 for " + heroEffectLength + " turns!";
                nosian.setArmor(0);
                System.out.println(heroName + " was Sundered! Armor is reduced to 0 for " + heroEffectLength + " turns!");
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Sundered";
                allyEffectEfficacy = ally.getArmor();
                allyStatusEffectDescription = "Sundered: " + allyName + "'s Armor is reduced to 0 for " + allyEffectLength + " turns!";;
                ally.setArmor(0);
                System.out.println(allyName + " was Sundered! Armor is reduced to 0 for " + allyEffectLength + " turns!");
                return true;
            }
        }
        return false;
    }

    /**
     * Reduces the Target's Resistance by the specified amount for a specified number of turns.
     * Cannot be procced on enemies.
     * The chance to proc mental collapse is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param amount
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean mentalCollapse(String target, int amount, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, Mental Collapses the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Mental Collapse";
                if(amount > nosian.getResistance())
                {
                    amount = nosian.getResistance();
                }
                heroEffectEfficacy = amount;
                heroStatusEffectDescription = "Mental Collapse: " + heroName + "'s Resistance is reduced by " + amount + " for " + heroEffectLength + " turns!";
                nosian.setResistance(nosian.getResistance() - heroEffectEfficacy);
                System.out.println(heroName + " has suffered a Mental Collapse! Resistance is reduced by " + amount + " for " + heroEffectLength + " turns!");
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Mental Collapse";
                if(amount > ally.getResistance())
                {
                    amount = ally.getResistance();
                }
                allyEffectEfficacy = amount;
                allyStatusEffectDescription = "Mental Collapse: " + allyName + "'s Resistance is reduced by " + amount + " for " + allyEffectLength + " turns!";;
                ally.addResistance(-allyEffectEfficacy);
                System.out.println(allyName + " has suffered a Mental Collapse! Resistance is reduced by " + amount + " for " + allyEffectLength + " turns!");
                return true;
            }
        }
        return false;
    }

    /**
     * Reduces the Target's Resistance to 0 for a specified number of turns.
     * Cannot be procced on enemies.
     * The chance to proc submission is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean submission(String target, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, submits the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Submission";
                heroEffectEfficacy = nosian.getResistance();
                heroStatusEffectDescription = "Submission: " + heroName + "'s Resistance is reduced to 0 for " + heroEffectLength + " turns!";
                nosian.setResistance(0);
                System.out.println(heroName + " couldn't resist the vicious attack! Resistance is reduced to 0 for " + heroEffectLength + " turns!");
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Submission";
                allyEffectEfficacy = ally.getResistance();
                allyStatusEffectDescription = "Submission: " + allyName + "'s Resistance is reduced to 0 for " + allyEffectLength + " turns!";;
                ally.setResistance(0);
                System.out.println(allyName + " couldn't resist the vicious attack! Resistance is reduced to 0 for " + allyEffectLength + " turns!");
                return true;
            }
        }
        return false;
    }
    
    /**
     * Heals the Target by (int)(damage * (percentage/100))
     * 
     * Date created: November 3, 2020
     * 
     * @param target //Who casted the drain spell
     * @param amount //damage dealt
     * @param percentage //percentage of drain
     * @return gainedHP
     */
    public int drain(String target, int damage, int percentage)
    {
        int gainedHP = (int)(damage * (percentage/100.0));
        
        if(target.equals(heroName))
        {
            //Prevents the Hero from over-healing.
            if(gainedHP > nosian.getMaxHP() - nosian.getHP())
            {
                gainedHP = nosian.getMaxHP() - nosian.getHP();
            }
            nosian.setHP(nosian.getHP() + gainedHP);
        }
        if(target.equals(allyName))
        {
            //Prevents the Ally from over-healing.
            if(gainedHP > ally.getMaxHP() - ally.getHP())
            {
                gainedHP = ally.getMaxHP() - ally.getHP();
            }
            ally.addHP(gainedHP);
        }
        if(target.equals(enemyName))
        {
            //Prevents the Enemy from over-healing.
            if(gainedHP > enemy.getEnemyMaxHP() - enemy.getEnemyHP())
            {
                gainedHP = enemy.getEnemyMaxHP() - enemy.getEnemyHP();
            }
            enemy.addEnemyHP(gainedHP);
        }
        
        return gainedHP;
    }
    

    /**
     * Reduces the Target's Speed by the specified amount for a specified number of turns.
     * The chance to proc cripple is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param amount
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean cripple(String target, int amount, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, cripples the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Crippled";
                if(amount > nosian.getSpeed())
                {
                    amount = nosian.getSpeed();
                }
                heroEffectEfficacy = amount;
                heroStatusEffectDescription = "Crippled: " + heroName + "'s Speed is reduced by " + amount + " for " + heroEffectLength + " turns.";
                System.out.println(heroName + " was crippled! Speed is reduced by " + amount + " for " + heroEffectLength + " turns.");
                nosian.setSpeed(nosian.getSpeed() - heroEffectEfficacy);
                return true;
            }
            if(target.equals(enemyName))
            {
                enemyEffectLength = turns;
                enemyStatusEffect = "Crippled";
                if(amount > enemy.getEnemySpeed())
                {
                    amount = enemy.getEnemySpeed();
                }
                enemyEffectEfficacy = amount;
                enemyStatusEffectDescription = "Crippled: " + enemyName + "'s Speed is reduced by " + amount + " for " + enemyEffectLength + " turns.";
                System.out.println(enemyName + " was crippled! " + enemyName + "'s Speed is reduced by " + amount + " for " + enemyEffectLength + " turns.");
                enemy.addEnemySpeed(-enemyEffectEfficacy);
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Crippled";
                if(amount > ally.getSpeed())
                {
                    amount = ally.getSpeed();
                }
                allyEffectEfficacy = amount;
                allyStatusEffectDescription = "Crippled: " + allyName + "'s Speed is reduced by " + amount + " for " + allyEffectLength + " turns.";
                System.out.println(allyName + " was crippled! Speed is reduced by " + amount + " for " + allyEffectLength + " turns.");
                ally.addSpeed(-allyEffectEfficacy);
                return true;
            }
        }
        return false;
    }

    /**
     * Reduces the Target's Speed to 0 for a specified number of turns.
     * The chance to proc cripple is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean immobilize(String target, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, immobilizes the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Immobilized";
                heroEffectEfficacy = nosian.getSpeed();
                heroStatusEffectDescription = "Immobilized: " + heroName + "'s Speed is reduced to 0 for " + heroEffectLength + " turns.";
                System.out.println(heroName + " was Immobilized! " + heroName + "'s Speed is reduced to 0 for " + heroEffectLength + " turns.");
                nosian.setSpeed(0);
                return true;
            }
            if(target.equals(enemyName))
            {
                enemyEffectLength = turns;
                enemyStatusEffect = "Immobilized";
                enemyEffectEfficacy = enemy.getEnemySpeed();
                enemyStatusEffectDescription = "Immobilized: " + enemyName + "'s Speed is reduced to 0 for " + enemyEffectLength + " turns.";
                System.out.println(enemyName + " was Immobilized! " + enemyName + "'s Speed is reduced to 0 for " + enemyEffectLength + " turns.");
                enemy.setEnemySpeed(0);
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Immobilized";
                allyEffectEfficacy = ally.getSpeed();
                allyStatusEffectDescription = "Immobilized: " + allyName + "'s Speed is reduced to 0 for " + allyEffectLength + " turns.";
                ally.setSpeed(0);
                System.out.println(allyName + " was Immobilized! " + allyName + "'s Speed is reduced to 0 for " + allyEffectLength + " turns.");
                return true;
            }
            
        }
        return false;
    }

    /**
     * Prevents the target from moving on their turn for a specified number of turns.
     * The chance to proc stun is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean stun(String target, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, stuns the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Stunned";
                heroStatusEffectDescription = "Stunned: " + heroName + " cannot move for " + heroEffectLength + " turns.";
                System.out.println(heroName + " is Stunned! " + heroName + " cannot move for " + heroEffectLength + " turns.");
                return true;
            }
            if(target.equals(enemyName))
            {
                enemyEffectLength = turns;
                enemyStatusEffect = "Stunned";
                enemyStatusEffectDescription = "Stunned: " + enemyName + " cannot move for " + enemyEffectLength + " turns.";
                System.out.println(enemyName + " is Stunned! " + enemyName + " cannot move for " + enemyEffectLength + " turns.");
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Stunned";
                allyStatusEffectDescription = "Stunned: " + allyName + " cannot move for " + allyEffectLength + " turns.";
                System.out.println(allyName + " is Stunned! " + allyName + " cannot move for " + allyEffectLength + " turns.");
                return true;
            }
        }
        return false;
    }

    /**
     * Prevents the target from moving on their turn for a specified number of turns.
     * The chance to proc freeze is chance/100.
     * 
     * Date created: November 3, 2020
     * 
     * @param target
     * @param chance
     * @param turns
     * @return true (if successful), false (if not successful)
     */
    public boolean freeze(String target, int chance, int turns)
    {
        Random rand = new Random();
        //Gets a random number from 1 - 100.
        int randomNum = rand.nextInt(100) + 1;
        
        //If randomNum is lower than chance, freezes the target.
        if(randomNum < chance)
        {
            clearStatusEffects(target);
            if(target.equals(heroName))
            {
                heroEffectLength = turns;
                heroStatusEffect = "Frozen";
                heroStatusEffectDescription = "Frozen: " + heroName + " cannot move for " + heroEffectLength + " turns.";
                System.out.println(heroName + " is Frozen! " + heroName + " cannot move for " + heroEffectLength + " turns.");
                return true;
            }
            if(target.equals(enemyName))
            {
                enemyEffectLength = turns;
                enemyStatusEffect = "Frozen";
                enemyStatusEffectDescription = "Frozen: " + enemyName + " cannot move for " + enemyEffectLength + " turns.";
                System.out.println(enemyName + " is Frozen! " + enemyName + " cannot move for " + enemyEffectLength + " turns.");
                return true;
            }
            if(target.equals(allyName))
            {
                allyEffectLength = turns;
                allyStatusEffect = "Frozen";
                allyStatusEffectDescription = "Frozen: " + allyName + " cannot move for " + allyEffectLength + " turns.";
                System.out.println(allyName + " is Frozen! " + allyName + " cannot move for " + allyEffectLength + " turns.");
                return true;
            }
        }
        return false;
    }

    /**
     * Iterates a turn of the specified person's status effect.
     * Will reduce hero or enemy HP if one is affected by poison or bleed.
     * 
     * Can revert/cure status effects when effectLength for either entity reaches 0.
     * 
     * Date created: November 3, 2020
     * 
     * @param who
     */
    //Update needed
    public String iterateTurn(String who)
    {
        if(who.equalsIgnoreCase(heroName))
        {
            //Once iterated, if the hero's effect length is greater than 0, iterate their effect length by -1.
            if(heroEffectLength > 0)
            {
                heroEffectLength--;
            }

            if(heroStatusEffect.equalsIgnoreCase("Poisoned"))
            {
                System.out.println(heroName + " took " + heroEffectEfficacy + "HP of damage from Poison!");
                nosian.setHP(nosian.getHP() - heroEffectEfficacy);
                if(nosian.getHP() < 0)
                {
                    nosian.setHP(0);
                }

                if(heroEffectLength == 0)
                {
                    //clears status effects for the hero.
                    clearStatusEffects(heroName);
                    return heroName + " took " + heroEffectEfficacy + "HP of damage from Poison! \n" + heroName + "'s HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP.\n" + heroName + " has recovered from Poison.";
                }
                else
                {
                    return heroName + " took " + heroEffectEfficacy + "HP of damage from Poison! \n" + heroName + "'s HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP.\n";
                }
            }
            else if(heroStatusEffect.equalsIgnoreCase("Bleeding"))
            {
                System.out.println(heroName + " took " + heroEffectEfficacy + "HP of damage from Bleeding!");
                nosian.setHP(nosian.getHP() - heroEffectEfficacy);
                if(nosian.getHP() < 0)
                {
                    nosian.setHP(0);
                }
                
                if(heroEffectLength == 0)
                {
                    //clears status effects for the hero.
                    clearStatusEffects(heroName);
                    return heroName + " took " + heroEffectEfficacy + "HP of damage from Bleeding! \n" + heroName + "'s HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP.\n" + heroName + " has recovered from Bleeding.";
                }
                else
                {
                    return heroName + " took " + heroEffectEfficacy + "HP of damage from Bleeding! \n" + heroName + "'s HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP.\n";
                }
            }
            else if(heroStatusEffect.equalsIgnoreCase("Reduced HP"))
            {
                if(heroEffectLength > 0)
                {
                    return heroName + " has " + heroStatusEffect + " for " + heroEffectLength + " more turns!\n";
                }
                else
                {
                    //clears status effects for the hero.
                    clearStatusEffects(heroName);
                    return heroName + " has recovered from " + heroStatusEffect + ".\n";
                }
            }
            else if(heroStatusEffect.equalsIgnoreCase("Headache"))
            {
                if(heroEffectLength > 0)
                {
                    return heroName + " has a " + heroStatusEffect + " for " + heroEffectLength + " more turns!\n";
                }
                else
                {
                    //clears status effects for the hero.
                    clearStatusEffects(heroName);
                    return heroName + " has recovered from " + heroStatusEffect + ".\n";
                }
            }
            else if(heroStatusEffect.equalsIgnoreCase("Broken Armor"))
            {
                if(heroEffectLength > 0)
                {
                    return heroName + " has " + heroStatusEffect + " for " + heroEffectLength + " more turns!\n";
                }
                else
                {
                    //clears status effects for the hero.
                    clearStatusEffects(heroName);
                    return heroName + " has recovered from " + heroStatusEffect + ".\n";
                }
            }
            else if(heroStatusEffect.equalsIgnoreCase("Mental Collapse") || heroStatusEffect.equalsIgnoreCase("Submission"))
            {
                if(heroEffectLength > 0)
                {
                    return heroName + " is under " + heroStatusEffect + " for " + heroEffectLength + " more turns!\n";
                }
                else
                {
                    //clears status effects for the hero.
                    clearStatusEffects(heroName);
                    return heroName + " has recovered from " + heroStatusEffect + ".\n";
                }
            }
            //This includes other status effects not included above like immobilized, sundered, and pacified. (Note the ! at the beginning.)
            else if(!heroStatusEffect.equalsIgnoreCase("Healthy"))
            {
                if(heroEffectLength > 0)
                {
                    return heroName + " is " + heroStatusEffect + " for " + heroEffectLength + " more turns!\n";
                }
                else
                {
                    //Needed to show what the hero has recovered from (after clearing hero's status effects).
                    String tempStatEffect = heroStatusEffect;
                    //clears status effects for the hero.
                    clearStatusEffects(heroName);
                    return heroName + " has recovered from being " + tempStatEffect + ".\n";
                }
            }
        }
        else if(who.equalsIgnoreCase(allyName))
        {
            //Once iterated, if the ally's effect length is greater than 0, iterate their effect length by -1.
            if(allyEffectLength > 0)
            {
                allyEffectLength--;
            }

            if(allyStatusEffect.equalsIgnoreCase("Poisoned"))
            {
                System.out.println(allyName + " took " + allyEffectEfficacy + "HP of damage from Poison!");
                ally.addHP(-allyEffectEfficacy);
                if(ally.getHP() < 0)
                {
                    ally.setHP(0);
                }

                if(allyEffectLength == 0)
                {
                    //clears ally status effects.
                    clearStatusEffects(allyName);
                    return allyName + " took " + allyEffectEfficacy + "HP of damage from Poison! \n" + allyName + "'s HP is now: " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP.\n" + allyName + " has recovered from Poison.";
                }
                else
                {
                    return allyName + " took " + allyEffectEfficacy + "HP of damage from Poison! \n" + allyName + "'s HP is now: " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP.\n";
                }
            }
            else if(allyStatusEffect.equalsIgnoreCase("Bleeding"))
            {
                System.out.println(allyName + " took " + allyEffectEfficacy + "HP of damage from Bleeding!");
                ally.addHP(-allyEffectEfficacy);
                if(ally.getHP() < 0)
                {
                    ally.setHP(0);
                }

                if(allyEffectLength == 0)
                {
                    //clears ally status effects.
                    clearStatusEffects(allyName);
                    return allyName + " took " + allyEffectEfficacy + "HP of damage from Bleeding! \n" + allyName + "'s HP is now: " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP.\n" + allyName + " has recovered from Bleeding.";
                }
                else
                {
                    return allyName + " took " + allyEffectEfficacy + "HP of damage from Bleeding! \n" + allyName + "'s HP is now: " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP.\n";
                }
            }
            else if(allyStatusEffect.equalsIgnoreCase("Reduced HP"))
            {
                if(allyEffectLength > 0)
                {
                    return allyName + " has " + allyStatusEffect + " for " + allyEffectLength + " more turns!\n";
                }
                else
                {
                    //clears ally status effects.
                    clearStatusEffects(allyName);
                    return allyName + " has recovered from " + allyStatusEffect + ".\n";
                }
            }
            else if(allyStatusEffect.equalsIgnoreCase("Headache"))
            {
                if(allyEffectLength > 0)
                {
                    return allyName + " has a " + allyStatusEffect + " for " + allyEffectLength + " more turns!\n";
                }
                else
                {
                    //clears ally status effects.
                    clearStatusEffects(allyName);
                    return allyName + " has recovered from " + allyStatusEffect + ".\n";
                }
            }
            else if(allyStatusEffect.equalsIgnoreCase("Broken Armor"))
            {
                if(allyEffectLength > 0)
                {
                    return allyName + " has " + allyStatusEffect + " for " + allyEffectLength + " more turns!\n";
                }
                else
                {
                    //clears ally status effects.
                    clearStatusEffects(allyName);
                    return allyName + " has recovered from " + allyStatusEffect + ".\n";
                }
            }
            else if(allyStatusEffect.equalsIgnoreCase("Mental Collapse") || allyStatusEffect.equalsIgnoreCase("Submission"))
            {
                if(allyEffectLength > 0)
                {
                    return allyName + " has " + allyStatusEffect + " for " + allyEffectLength + " more turns!\n";
                }
                else
                {
                    //clears ally status effects.
                    clearStatusEffects(allyName);
                    return allyName + " has recovered from " + allyStatusEffect + ".\n";
                }
            }
            //This includes other status effects not included above like immobilized, sundered, and pacified. (Note the ! at the beginning.)
            else if(!allyStatusEffect.equalsIgnoreCase("Healthy"))
            {
                if(allyEffectLength > 0)
                {
                    return allyName + " is " + allyStatusEffect + " for " + allyEffectLength + " more turns!\n";
                }
                else
                {
                    //Needed to show what the ally has recovered from (after clearing ally's status effects).
                    String tempStatEffect = allyStatusEffect;
                    //clears ally status effects.
                    clearStatusEffects(allyName);
                    return allyName + " has recovered from being " + tempStatEffect + ".\n";
                }
            } 
        }
        else if(who.equalsIgnoreCase(enemyName))
        {
            //Once iterated, if the enemy's effect length is greater than 0, iterate their effect length by -1.
            if(enemyEffectLength > 0)
            {
                enemyEffectLength--;
            }

            if(enemyStatusEffect.equalsIgnoreCase("Poisoned"))
            {
                System.out.println("The Enemy took " + enemyEffectEfficacy + "HP of damage from Poison!");
                enemy.addEnemyHP(-enemyEffectEfficacy);
                if(enemy.getEnemyHP() < 0)
                {
                    enemy.setEnemyHP(0);
                }

                if(enemyEffectLength == 0)
                {
                    //clear enemy status effects.
                    clearStatusEffects(enemyName);
                    return enemyName + " took " + enemyEffectEfficacy + "HP of damage from Poison! \n" + enemyName + "'s HP is now: " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP.\n" + enemyName + " has recovered from Poison.";
                }
                else
                {
                    return enemyName + " took " + enemyEffectEfficacy + "HP of damage from Poison! \n" + enemyName + "'s HP is now: " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP.\n";
                }
            }
            else if(enemyStatusEffect.equalsIgnoreCase("Bleeding"))
            {
                System.out.println("The Enemy took " + enemyEffectEfficacy + "HP of damage from Bleeding!");
                enemy.addEnemyHP(-enemyEffectEfficacy);
                if(enemy.getEnemyHP() < 0)
                {
                    enemy.setEnemyHP(0);
                }

                if(enemyEffectLength == 0)
                {
                    //clear enemy status effects.
                    clearStatusEffects(enemyName);
                    return enemyName + " took " + enemyEffectEfficacy + "HP of damage from Bleeding! \n" + enemyName + "'s HP is now: " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP.\n" + enemyName + " has recovered from Bleeding.";
                }
                else
                {
                    return enemyName + " took " + enemyEffectEfficacy + "HP of damage from Bleeding! \n" + enemyName + "'s HP is now: " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP.\n";
                }
                
            }
            else if(enemyStatusEffect.equalsIgnoreCase("Reduced HP"))
            {
                if(enemyEffectLength > 0)
                {
                    return enemyName + " has " + enemyStatusEffect + " for " + enemyEffectLength + " more turns!\n";
                }
                else
                {
                    //clear enemy status effects.
                    clearStatusEffects(enemyName);
                    return enemyName + " has recovered from " + enemyStatusEffect + ".\n";
                }
            }
            else if(enemyStatusEffect.equalsIgnoreCase("Headache"))
            {
                if(enemyEffectLength > 0)
                {
                    return enemyName + " has a " + enemyStatusEffect + " for " + enemyEffectLength + " more turns!\n";
                }
                else
                {
                    //clear enemy status effects.
                    clearStatusEffects(enemyName);
                    return enemyName + " has recovered from " + enemyStatusEffect + ".\n";
                }
            }
            //This includes other status effects not included above like pacified, silenced, and weakened. (Note the ! at the beginning.)
            else if(!enemyStatusEffect.equalsIgnoreCase("Healthy"))
            {
                if(enemyEffectLength > 0)
                {
                    return enemyName + " is " + enemyStatusEffect + " for " + enemyEffectLength + " more turns!\n";
                }
                else
                {
                    //Needed to show what the enemy has recovered from (after clearing enemy's status effects).
                    String tempStatEffect = heroStatusEffect;
                    //clear enemy status effects.
                    clearStatusEffects(enemyName);
                    return enemyName + " has recovered from being " + tempStatEffect + ".\n";
                }
            }
        }
        return "";
    }
    /**
     * Clears status effects from the hero, enemy, and ally
     * to prevent status effects from transferring
     * to the next battle.
     * 
     * Date created: November 3, 2020
     * 
     */
    public void clearStatusEffects(String who)
    {
        if(who.equals(heroName))
        {
            //Restores the Hero's reduced stats before clearing to prevent permanent reduction.
            if(heroStatusEffect.equalsIgnoreCase("Reduced HP"))
            {
                nosian.setMaxHP(nosian.getMaxHP() + heroEffectEfficacy);
                nosian.setHP(nosian.getHP() + heroEffectEfficacy);
            }
            if(heroStatusEffect.equalsIgnoreCase("Weakened") || heroStatusEffect.equalsIgnoreCase("Pacified"))
            {
                nosian.setConstitution(nosian.getConstitution() + heroEffectEfficacy);
            }
            if(heroStatusEffect.equalsIgnoreCase("Headache") || heroStatusEffect.equalsIgnoreCase("Silenced"))
            {
                nosian.setAffinity(nosian.getAffinity() + heroEffectEfficacy);
            }
            if(heroStatusEffect.equalsIgnoreCase("Broken Armor") || heroStatusEffect.equalsIgnoreCase("Sundered"))
            {
                nosian.setArmor(nosian.getArmor() + heroEffectEfficacy);
            }
            if(heroStatusEffect.equalsIgnoreCase("Mental Collapse") || heroStatusEffect.equalsIgnoreCase("Submission"))
            {
                nosian.setResistance(nosian.getResistance() + heroEffectEfficacy);
            }
            if(heroStatusEffect.equalsIgnoreCase("Crippled") || heroStatusEffect.equalsIgnoreCase("Immobilized"))
            {
                nosian.setSpeed(nosian.getSpeed() + heroEffectEfficacy);
            }
            nosian.updateSkillDamage();
            heroStatusEffect = "Healthy";
            heroStatusEffectDescription = "Unaffected by any Status Effect.";
            heroEffectEfficacy = 0;
            heroEffectLength = 0;
        }
        else if(who.equals(enemyName))
        {
            //Restores the Enemy's reduced stats before clearing to prevent permanent reduction.
            if(enemyStatusEffect.equalsIgnoreCase("Reduced HP"))
            {
                enemy.addEnemyMaxHP(enemyEffectEfficacy);
                enemy.addEnemyHP(enemyEffectEfficacy);
            }
            if(enemyStatusEffect.equalsIgnoreCase("Weakened") || enemyStatusEffect.equalsIgnoreCase("Pacified"))
            {
                enemy.addEnemyConstitution(enemyEffectEfficacy);
            }
            if(enemyStatusEffect.equalsIgnoreCase("Headache") || enemyStatusEffect.equalsIgnoreCase("Silenced"))
            {
                enemy.addEnemyAffinity(enemyEffectEfficacy);
            }
            if(enemyStatusEffect.equalsIgnoreCase("Crippled") || enemyStatusEffect.equalsIgnoreCase("Immobilized"))
            {
                enemy.addEnemySpeed(enemyEffectEfficacy);
            }
            enemy.restoreDmg();
            enemyStatusEffect = "Healthy";
            enemyStatusEffectDescription = "Unaffected by any Status Effect.";
            enemyEffectEfficacy = 0;
            enemyEffectLength = 0;
        }
        else if(who.equals(allyName))
        {
            //Restores the Ally's reduced stats before clearing to prevent permanent reduction.
            if(allyStatusEffect.equalsIgnoreCase("Reduced HP"))
            {
                ally.addMaxHP(allyEffectEfficacy);
                ally.addHP(allyEffectEfficacy);
            }
            if(allyStatusEffect.equalsIgnoreCase("Weakened") || allyStatusEffect.equalsIgnoreCase("Pacified"))
            {
                ally.addConstitution(allyEffectEfficacy);
            }
            if(allyStatusEffect.equalsIgnoreCase("Headache") || allyStatusEffect.equalsIgnoreCase("Silenced"))
            {
                ally.addAffinity(allyEffectEfficacy);
            }
            if(allyStatusEffect.equalsIgnoreCase("Broken Armor") || allyStatusEffect.equalsIgnoreCase("Sundered"))
            {
                ally.addArmor(allyEffectEfficacy);
            }
            if(allyStatusEffect.equalsIgnoreCase("Mental Collapse") || allyStatusEffect.equalsIgnoreCase("Submission"))
            {
                ally.addResistance(allyEffectEfficacy);
            }
            if(allyStatusEffect.equalsIgnoreCase("Crippled") || allyStatusEffect.equalsIgnoreCase("Immobilized"))
            {
                ally.addSpeed(allyEffectEfficacy);
            }
            ally.updateSkillDamage();
            allyStatusEffect = "Healthy";
            allyStatusEffectDescription = "Unaffected by any Status Effect.";
            allyEffectEfficacy = 0;
            allyEffectLength = 0;
        }
    }

    /**
     * Clears all status effects from the hero, enemy, and ally
     * to prevent status effects from transferring
     * to the next battle.
     * 
     * Date created: November 3, 2020
     * 
     */
    public void clearAllStatusEffects()
    {
        clearStatusEffects(heroName);
        clearStatusEffects(enemyName);
        if(ally != null)
        {
            clearStatusEffects(allyName);
        }
        System.out.println("All Status Effects have been cleared.");
    }
    //Accessor Methods
    /**
     * returns the hero's status effect.
     * 
     * Date created: November 3, 2020
     * 
     * @return heroStatusEffect - String
     */
    public String getHeroStatusEffect()
    {
        return heroStatusEffect;
    }

    /**
     * return the hero's secondary status effect.
     * 
     * Date Created: 11/24/2021
     * 
     * @return secondaryHeroStatusEffect - String
     */
    public String getHeroSecondaryStatusEffect()
    {
        return secondaryHeroStatusEffect;
    }

    /**
     * returns the hero's status effect description.
     * 
     * Date created: November 3, 2020
     * 
     * @return heroStatusEffectDescription - String
     */
    public String getHeroStatusEffectDescription()
    {
        return heroStatusEffectDescription;
    }
    /**
     * returns the hero's status effect efficacy.
     * 
     * Date created: November 3, 2020
     * 
     * @return heroEffectEfficacy - int
     */
    public int getHeroEffectEfficacy()
    {
        return heroEffectEfficacy;
    }

    /**
     * returns the hero's secondary effect efficacy.
     * 
     * Date Created: 11/24/2021
     * 
     * @return secondaryHeroEffectEfficacy - int
     */
    public int getSecondaryHeroEffectEfficacy()
    {
        return secondaryHeroEffectEfficacy;
    }

    /**
     * returns the hero's status effect length.
     * 
     * Date created: November 3, 2020
     * 
     * @return heroEffectLength - int
     */
    public int getHeroEffectLength()
    {
        return heroEffectLength;
    }


    /***** Enemy 1 Accessor Methods *****/   
    /**
     * returns the enemy's status effect description.
     * 
     * Date created: November 3, 2020
     * 
     * @return enemyStatusEffect - String
     */
    public String getEnemyStatusEffect()
    {
        return enemyStatusEffect;
    }

    /**
     * returns the enemy's secondary status effect.
     * 
     * Date Created: 11/24/2021
     * 
     * @return secondaryEnemyStatusEffect - String
     */
    public String getEnemySecondaryStatusEffect()
    {
        return secondaryEnemyStatusEffect;
    }
    
    /**
     * returns the enemy's status effect description.
     * 
     * Date created: November 3, 2020
     * 
     * @return enemyStatusEffectDescription - String
     */
    public String getEnemyStatusEffectDescription()
    {
        return enemyStatusEffectDescription;
    }

    /**
     * returns the enemy's status effect efficacy.
     * 
     * Date created: November 3, 2020
     * 
     * @return enemyEffectEfficacy - int
     */
    public int getEnemyEffectEfficacy()
    {
        return enemyEffectEfficacy;
    }

    /**
     * returns the enemy's status effect length.
     * 
     * Date created: November 3, 2020
     * 
     * @return enemyEffectLength - int
     */
    public int getEnemyEffectLength()
    {
        return enemyEffectLength;
    }


    /***** Enemy 2 Accessor Methods *****/   
    /**
     * returns the enemy's status effect description.
     * 
     * Date created: 11/24/2021
     * 
     * @return enemy2StatusEffect - String
     */
    public String getEnemy2StatusEffect()
    {
        return enemy2StatusEffect;
    }

    /**
     * returns the enemy's secondary status effect.
     * 
     * Date Created: 11/24/2021
     * 
     * @return secondaryEnemy2StatusEffect - String
     */
    public String getEnemy2SecondaryStatusEffect()
    {
        return secondaryEnemy2StatusEffect;
    }
    
    /**
     * returns the enemy's status effect description.
     * 
     * Date created: 11/24/2021
     * 
     * @return enemy2StatusEffectDescription - String
     */
    public String getEnemy2StatusEffectDescription()
    {
        return enemy2StatusEffectDescription;
    }
    
    /**
     * returns the enemy's status effect efficacy.
     * 
     * Date created: 11/24/2021
     * 
     * @return enemy2EffectEfficacy - int
     */
    public int getEnemy2EffectEfficacy()
    {
        return enemy2EffectEfficacy;
    }

    /**
     * returns the enemy's status effect length.
     * 
     * Date created: 11/24/2021
     * 
     * @return enemy2EffectLength - int
     */
    public int getEnemy2EffectLength()
    {
        return enemy2EffectLength;
    }

    
    /***** Ally 1 Accessor Methods *****/   
    /**
     * returns the ally's status effect description.
     * 
     * Date created: November 3, 2020
     * 
     * @return allyStatusEffect - String
     */
    public String getAllyStatusEffect()
    {
        return allyStatusEffect;
    }

    /**
     * returns the secondary effect of Ally 1.
     * 
     * Date Created: 11/24/2021
     * 
     * @return secondaryAllyStatusEffect - String
     */
    public String getAllySecondaryStatusEffect()
    {
        return secondaryAllyStatusEffect;
    }

    /**
     * returns the efficacy of the second effect of Ally 1.
     * 
     * Date Created: 11/24/2021
     * 
     * @return secondaryAllyEffectEfficacy - int
     */
    public int getAllySecondaryEffectEfficacy()
    {
        return secondaryAllyEffectEfficacy;
    }

    /**
     * returns the ally's status effect description.
     * 
     * Date created: November 3, 2020
     * 
     * @return allyStatusEffectDescription - String
     */
    public String getAllyStatusEffectDescription()
    {
        return allyStatusEffectDescription;
    }
    /**
     * returns the ally's status effect efficacy.
     * 
     * Date created: November 3, 2020
     * 
     * @return allyEffectEfficacy - int
     */
    public int getAllyEffectEfficacy()
    {
        return allyEffectEfficacy;
    }
    /**
     * returns the ally's status effect length.
     * 
     * Date created: November 3, 2020
     * 
     * @return allyEffectLength - int
     */
    public int getAllyEffectLength()
    {
        return allyEffectLength;
    }


    /***** Ally 2 Accessor Methods *****/    
    /**
     * returns the second ally's status effect description.
     * 
     * Date created: November 3, 2020
     * 
     * @return ally2StatusEffect - String
     */
    public String getAlly2StatusEffect()
    {
        return ally2StatusEffect;
    }

    /**
     * returns the secondary effect of Ally 2.
     * 
     * Date Created: 11/24/2021
     * 
     * @return secondaryAlly2StatusEffect - String
     */
    public String getAlly2SecondaryStatusEffect()
    {
        return secondaryAlly2StatusEffect;
    }

    /**
     * returns the efficacy of the second effect of Ally 2.
     * 
     * Date Created: 11/24/2021
     * 
     * @return secondaryAlly2EffectEfficacy - int
     */
    public int getAlly2SecondaryEffectEfficacy()
    {
        return secondaryAlly2EffectEfficacy;
    }

    /**
     * returns the second ally's status effect description.
     * 
     * Date Created: 11/24/2021
     * 
     * @return ally2StatusEffectDescription - String
     */
    public String getAlly2StatusEffectDescription()
    {
        return ally2StatusEffectDescription;
    }
    /**
     * returns the second ally's status effect efficacy.
     * 
     * Date Created: 11/24/2021
     * 
     * @return ally2EffectEfficacy - int
     */
    public int getAlly2EffectEfficacy()
    {
        return ally2EffectEfficacy;
    }
    /**
     * returns the second ally's status effect length.
     * 
     * Date Created: 11/24/2021
     * 
     * @return ally2EffectLength - int
     */
    public int getAlly2EffectLength()
    {
        return ally2EffectLength;
    }

    /**
     * returns a string that has a description of all
     * status effects for all characters.
     * 
     * Date Created: November 4, 2020
     * 
     * @return message - String
     */
    public String getAllStatusEffects()
    {
        String message = "Hero\n--------\n";
        message += "Hero Status Effect: " + heroStatusEffect;
        message += "\nStatus Effect Description: " + heroStatusEffectDescription;
        message += "\nStatus Effect Strength: " + heroEffectEfficacy;
        message += "\nStatus Effect Length: " + heroEffectLength;

        message += "\n\nEnemy\n--------\n";
        message += "Enemy Status Effect: " + enemyStatusEffect;
        message += "\nStatus Effect Description: " + enemyStatusEffectDescription;
        message += "\nStatus Effect Strength: " + enemyEffectEfficacy;
        message += "\nStatus Effect Length: " + enemyEffectLength;

        if(ally != null)
        {
            message += "\n\nAlly\n--------\n";
            message += "Ally Status Effect: " + allyStatusEffect;
            message += "\nStatus Effect Description: " + allyStatusEffectDescription;
            message += "\nStatus Effect Strength: " + allyEffectEfficacy;
            message += "\nStatus Effect Length: " + allyEffectLength;
        }
        return message;
    }
    /**
     * returns a description of Status Effects.
     * 
     * Date Created: November 23, 2020
     * 
     * @return message - String
     */
    public String getStatusEffectInformation()
    {
        String message = "";

        message += "Reduced HP: The target's Max HP is reduced by x for y turns.\n";
        message += "Poisoned: The target's HP is reduced by x every turn for y turns.\n";
        message += "Bleeding: The target's HP is reduced by x every turn for y turns.\n";
        message += "Weakened: The target's Constitution is reduced by x for y turns.\n";
        message += "Pacified: The target's Constitution is reduced to 0 for y turns.\n";
        message += "Headache: The target's Affinity is reduced by x for y turns.\n";
        message += "Silenced: The target's Affinity is reduced to 0 for y turns.\n";
        message += "Broken Armor: The target's Armor is reduced by x for y turns.\n";
        message += "Sundered: The target's Armor is reduced to 0 for y turns.\n";
        message += "Mental Collapse: The target's Resistance is reduced by x for y turns.\n";
        message += "Submission: The target's Resistance is reduced to 0 for y turns.\n";
        message += "Crippled: The target's Speed is reduced by x for y turns.\n";
        message += "Immobilized: The target's Speed is reduced to 0 for y turns.\n";
        message += "Stunned: The target cannot move on their turn for y turns.\n";
        message += "Frozen: The target cannot move on their turn for y turns.\n";

        return message;
    }

    //Mutator Methods
    /**
     * Sets the Hero's status effect to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param heroStatusEffect - String
     */
    public void setHeroStatusEffect(String heroStatusEffect)
    {
        this.heroStatusEffect = heroStatusEffect;
    }
    /**
     * Sets the Hero's status effect description to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param heroStatusEffectDescription - String
     */
    public void setHeroStatusEffectDescription(String heroStatusEffectDescription)
    {
        this.heroStatusEffectDescription = heroStatusEffectDescription;
    }
    /**
     * Sets the Hero's status effect efficacy to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param heroEffectEfficacy - int
     */
    public void setHeroEffectEfficacy(int heroEffectEfficacy)
    {
        this.heroEffectEfficacy = heroEffectEfficacy;
    }
    /**
     * Sets the Hero's status effect length to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param heroEffectLength - int
     */
    public void setHeroEffectLength(int heroEffectLength)
    {
        this.heroEffectLength = heroEffectLength;
    }
    /**
     * Sets the Enemy's status effect to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param enemyStatusEffect - String
     */
    public void setEnemyStatusEffect(String enemyStatusEffect)
    {
        this.enemyStatusEffect = enemyStatusEffect;
    }
    /**
     * Sets the Enemy's status effect description to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param enemyStatusEffectDescription - String
     */
    public void setEnemyStatusEffectDescription(String enemyStatusEffectDescription)
    {
        this.enemyStatusEffectDescription = enemyStatusEffectDescription;
    }
    /**
     * Sets the Enemy's status effect efficacy to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param enemyEffectEfficacy - int
     */
    public void setEnemyEffectEfficacy(int enemyEffectEfficacy)
    {
        this.enemyEffectEfficacy = enemyEffectEfficacy;
    }
    /**
     * Sets the Enemy's status effect length to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param enemyEffectLength - int
     */
    public void setEnemyEffectLength(int enemyEffectLength)
    {
        this.enemyEffectLength = enemyEffectLength;
    }
    /**
     * Sets the Ally's status effect to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param allyStatusEffect - String
     */
    public void setAllyStatusEffect(String allyStatusEffect)
    {
        this.allyStatusEffect = allyStatusEffect;
    }
    /**
     * Sets the Ally's status effect description to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param allyStatusEffectDescription - String
     */
    public void setAllyStatusEffectDescription(String allyStatusEffectDescription)
    {
        this.allyStatusEffectDescription = allyStatusEffectDescription;
    }
    /**
     * Sets the Ally's status effect efficacy to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param allyEffectEfficacy - int
     */
    public void setAllyEffectEfficacy(int allyEffectEfficacy)
    {
        this.allyEffectEfficacy = allyEffectEfficacy;
    }
    /**
     * Sets the Ally's status effect length to a specified value.
     * 
     * Date created: November 4, 2020
     * 
     * @param allyEffectLength - int
     */
    public void setAllyEffectLength(int allyEffectLength)
    {
        this.allyEffectLength = allyEffectLength;
    }
}
