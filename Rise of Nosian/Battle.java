/**
 * --------------------------------------------------------------------------
 * File name: Battle.java
 * Project name: Rise of Nosian
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Creation Date: 10/25/2021
 * Completion Date: --/--/2021
 * @version 1.00
 * --------------------------------------------------------------------------
 */
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 * The Battle Class acts as a secondary driver class. The Battle Class simulates
 * a battle by allowing user input and having the hero act based on the user
 * input. This class is the 2nd most important of all classes and the largest class.
 *
 * The Battle Class creates an enemy class and the battle is held in the
 * constructor.
 * 
 * Date created: October 20, 2020
 * 
 * @author Michael Ng, ngmw01@etsu.edu
 */
public class Battle
{
    private Hero nosian;
    private Enemy enemy;
    private Enemy enemy2;
    private Ally ally;
    private Ally ally2;
    private StatusEffect statusEffect;

    private ArrayList<String> tips = new ArrayList<String>();

    // Determines who has the turn.
    private String battleTurn = "Enemy";

    private boolean tutorial = false;
    private boolean friendlyBattle = false;
    private boolean ally2Available;
    private boolean enemy2Available;




    // Fields for the Hero.
    private String heroName;

    // Fields for the Enemy.
    private String enemyName;
    private int enemyXPAward;
    private int enemyMoneyAward;


    /***
     * The primary constructor for the Battle Class.
     * 
     * HeroAttributes nosian is needed to import hero attributes into the battle
     * class. Skills nosian is needed to have access to the hero's skills and
     * skill damage.
     * 
     * Date created: October 20, 2020
     * 
     * @param nosian    - Hero
     * @param ally      - Ally
     * @param area      - int
     */
    // The entirety of the battle is held in the constructor.
    public Battle(Hero nosian, Ally ally, int area) {
        // gets references to HeroAttributes, Skills, and Ally.
        this.nosian = nosian;
        this.ally = ally;
        enemy = new Enemy(area);

        ally2Available = false;
        enemy2Available = false;

        //Create a new statusEffect to keep track of Status Effects in Battle.
        statusEffect = new StatusEffect(nosian, enemy, ally);

        beginBattle(1);
    }

    /**
     * Secondary Battle class constructor. Used to begin a battle with a specific
     * enemy.
     * 
     * HeroAttributes nosian is needed to import hero attributes into the battle
     * class. Skills nosian is needed to have access to the hero's skills and
     * skill damage. Enemy enemy is needed to have a "custom" enemy.
     * 
     * Date created: October 20, 2020
     * 
     * @param nosian    - Hero
     * @param ally      - Ally
     * @param enemy     - Enemy
     */
    public Battle(Hero nosian, Ally ally, Enemy enemy) {
        //Gets references from HeroAttributes, Skills, Ally, and Enemy.
        this.nosian = nosian;
        this.enemy = enemy;
        this.ally = ally;

        ally2Available = false;
        enemy2Available = false;

        /* checks if the specified enemy has the name goblin.
         * Since there is only 1 goblin in the secondary enemy constructor (used as a tutorial),
         * this if statement can change tutorial to true if the name matches.
         */
        if(enemy.getEnemyName().equals("Goblin"))
        {
            tutorial = true;
        }

        //Create a new statusEffect to keep track of Status Effects in Battle.
        statusEffect = new StatusEffect(nosian, enemy, ally);

        beginBattle(1);
    }

    /**
     * Tertiary Battle class constructor. Used to battle against an ally.
     * 
     * HeroAttributes nosian is needed to import hero attributes into the battle
     * class. Skills nosian is needed to have access to the hero's skills and
     * skill damage. Enemy enemy is needed to have a "custom" enemy.
     * 
     * Date created: November 17, 2020
     * 
     * @param nosian    - Hero
     * @param enemy     - Enemy
     */
    public Battle(Hero nosian, Enemy enemy)
    {
        this.nosian = nosian;
        this.enemy = enemy;

        ally2Available = false;
        enemy2Available = false;

        //Create a new statusEffect to keep track of Status Effects in Battle.
        statusEffect = new StatusEffect(nosian, enemy, ally);

        beginFriendlyBattle();
    }

    /**
     * Quadernary Battle class constructor. Begins battle against 2 custom enemies and with 2 allies.
     * 
     * HeroAttributes nosian is needed to import hero attributes into the battle
     * class. Skills nosian is needed to have access to the hero's skills and
     * skill damage. Custom enemies defined by enemy and enemy2.
     * 
     * Date created: November 17, 2020
     * 
     * @param nosian    - Hero
     * @param ally      - Ally
     * @param ally2     - Ally
     * @param enemy     - Enemy
     * @param enemy2    - Enemy
     */
    public Battle(Hero nosian, Ally ally, Ally ally2, Enemy enemy, Enemy enemy2)
    {
        this.nosian = nosian;
        this.ally = ally;
        this.ally2 = ally2;
        this.enemy = enemy;
        this.enemy2 = enemy2;

        ally2Available = true;
        enemy2Available = true;

        //Create a new statusEffect to keep track of Status Effects in Battle.
        statusEffect = new StatusEffect(nosian, enemy, ally);

        beginFriendlyBattle();
    }

    /***
     * The 5th constructor for the Battle Class.
     * 
     * Creates a battle with 2 random enemies and 2 allies.
     * 
     * Date created: October 20, 2020
     * 
     * @param nosian - Hero
     * @param ally   - Ally
     * @param ally2  - Ally
     * @param area   - int
     */
    // The entirety of the battle is held in the constructor.
    public Battle(Hero nosian, Ally ally, Ally ally2, int area) {
        // gets references to HeroAttributes, Skills, and Ally.
        this.nosian = nosian;
        this.ally = ally;
        this.ally2 = ally2;
        enemy = new Enemy(area);
        enemy2 = new Enemy(area);



        ally2Available = true;
        enemy2Available = true;

        //Create a new statusEffect to keep track of Status Effects in Battle.
        statusEffect = new StatusEffect(nosian, ally, ally2, enemy, enemy2);

        beginBattle(2);
    }

    /**
     * Begins Battle. Phases begin with either the player or enemy depending on
     * their speeds.
     * 
     * Date Created: October 20, 2020
     * Date Updated: 11/25/2021
     * 
     * @param numEnemies - int
     */
    private void beginBattle(int numEnemies)
    {
        //Setup before battle.
        initializeTips();
        heroName = nosian.getName();
        enemyName = enemy.getEnemyName();
        enemyXPAward = enemy.getEnemyXPAward();
        enemyMoneyAward = enemy.getEnemyMoneyAward();
        
        //Formats the string based on how many enemies are being encountered.
        if(numEnemies == 1)
        {
            JOptionPane.showMessageDialog(null, enemy.getEnemyName() + " appeared! Beginning Battle Phase...",
            "Battle!", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, enemy.getEnemyName() + " and " + enemy2.getEnemyName() + " appeared! Beginning Battle Phase...",
            "Battle!", JOptionPane.INFORMATION_MESSAGE);
        }


        if(tutorial)
        {
            JOptionPane.showMessageDialog(null, "\"That Goblin must be from the Monster Legion,\" Zacharias says. \n\"Say, do you remember how to fight? You got hit hard back in Fendale.\"", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "\"Let me show you the ropes of battle,\" Zacharias says. \n\"First, let's see who gets to go first.\"", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
        }

        // Speeds are compared to see who goes first in battle.
        if (nosian.getSpeed() >= enemy.getEnemySpeed()) {
            JOptionPane.showMessageDialog(null, heroName + "'s speed is higher!\nNosian moves first this battle!",
                    "Hero Moves First", JOptionPane.INFORMATION_MESSAGE);
            battleTurn = "Hero";
        }
        if (battleTurn.equals("Enemy")) {
            JOptionPane.showMessageDialog(null, enemyName + "'s Speed is higher! \nThey move first in this battle.",
                    enemyName + " Moves First", JOptionPane.INFORMATION_MESSAGE);
            enemyPhase();
        }
        //A mini-tutorial for the game. 
        if(tutorial)
        {
            JOptionPane.showMessageDialog(null, "\"Are you alright?\" Zacharias asks. \n\"That goblin's speed was higher, so he hits first.\"", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "\"Keep watch on your HP counter,\" Zacharias says. \n\"You will faint if it reaches 0.\"", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Zacharias turns towards the Goblin. \n\"No one hurts my friends!\"", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
            allyPhase();
            JOptionPane.showMessageDialog(null, "\"I always attack after the enemy's turn,\" he says. \n\"Be mindful about that.\"", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "\"The goblin appears to be a warrior,\" Zacharias says. \n\"Your Armor stat should reduce the amount of damage he deals.\"", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "\"Armor is not a solution to everything,\" Zacharias says. \n\"Enemy mages can bypass Armor. Instead, Resistance reduces damage from mages.\"", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
            /*if(nosian.getHeroClass().equals("Mage"))
            {
                JOptionPane.showMessageDialog(null, "\"Hey, you're a Mage, aren't you?\" Zacharias asks. \n\"Focus on improving your Affinity.\"\n\"Improving your Constitution will not benefit you.\"", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "\"Hey, you're a Warrior, aren't you?\" Zacharias asks. \n\"Focus on improving your Constitution.\"\n\"Improving your Affinity will not benefit you.\"", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
            }*/
            JOptionPane.showMessageDialog(null, "\"Hey, it's your turn to attack next!\" Zacharias says. \n\"Figure out what you want to do.\"", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Feel free to choose any option under the menu. \nOptions other than Attack or Mend will not cost a turn.", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
        }

        // The Battle begins and loops here.
        if(ally2Available && enemy2Available) //3V2
        {
            //While at least 1 protagonist has HP above 0 OR at least 1 enemy is not fainted.
            while ((nosian.getHP() > 0 || ally.getHP() > 0 || ally2.getHP() > 0) && (enemy.getEnemyHP() > 0 || enemy2.getEnemyHP() > 0))
            {
                if (battleTurn.equals("Hero") && nosian.getHP() > 0)
                {
                    heroPhase();
                }
                if (battleTurn.equals("Enemy") || battleTurn.equals("Enemy 2") && (enemy.getEnemyHP() > 0 || enemy2.getEnemyHP() > 0))
                {
                    enemyPhase();
                }
                if (battleTurn.equals("Ally") || battleTurn.equals("Ally 2") && (ally.getHP() > 0 || ally2.getHP() > 0))
                {
                    allyPhase();
                }
            }
        }
        else if(enemy2Available) //2V2
        {
            //While at least 1 protagonist has HP above 0 OR at least 1 enemy is not fainted.
            while ((nosian.getHP() > 0 || ally.getHP() > 0) && (enemy.getEnemyHP() > 0 || enemy2.getEnemyHP() > 0))
            {
                if (battleTurn.equals("Hero") && nosian.getHP() > 0)
                {
                    heroPhase();
                }
                if ((battleTurn.equals("Enemy") || battleTurn.equals("Enemy 2")) && (enemy.getEnemyHP() > 0 || enemy2.getEnemyHP() > 0))
                {
                    enemyPhase();
                }
                if (ally.getHP() > 0 && battleTurn.equals("Ally"))
                {
                    allyPhase();
                }
            }
        }
        //TODO this
        else //2V1
        {
            //While at least 1 protagonist has HP above 0 OR the enemy is not fainted.
            while ((nosian.getHP() > 0 || ally.getHP() > 0) && enemy.getEnemyHP() > 0)
            {
                System.out.println(enemy.getEnemyHP());
                if (battleTurn.equals("Hero") && nosian.getHP() > 0)
                {
                    heroPhase();
                }
                if (battleTurn.equals("Enemy") && enemy.getEnemyHP() > 0)
                {
                    enemyPhase();
                }
                if (ally.getHP() > 0 && enemy.getEnemyHP() > 0 && battleTurn.equals("Ally"))
                {
                    allyPhase();
                }
            }
        }
    }

    /**
     * Begins Battle with an Ally. 
     * Phases begin with either the player or enemy depending on their speeds.
     * 
     * Date Created: 11/17/2020
     * Updated: 11/25/2021
     */
    private void beginFriendlyBattle()
    {
        //Setup before battle.
        heroName = nosian.getName();
        enemyName = enemy.getEnemyName();
        enemyXPAward = enemy.getEnemyXPAward();
        enemyMoneyAward = 0; //No money is awarded in friendly duels.
        initializeTips();

        
        friendlyBattle = true;
        JOptionPane.showMessageDialog(null, "You challenged " + enemyName + " to a friendly duel! \nBeginning Battle Phase...",
                "Battle!", JOptionPane.INFORMATION_MESSAGE);

        // Speeds are compared to see who goes first in battle.
        //Whoever has the higher speed goes first. If speeds are tied, Hero goes first.
        if (nosian.getSpeed() >= enemy.getEnemySpeed()) {
            JOptionPane.showMessageDialog(null, heroName + "'s speed is higher! \nNosian moves first this battle!",
                    "Hero Moves First", JOptionPane.INFORMATION_MESSAGE);
            battleTurn = "Hero";
        }
        if (battleTurn.equals("Enemy")) {
            JOptionPane.showMessageDialog(null, enemyName + "'s Speed is higher! \nThey move first in this battle.",
                    enemyName + " Moves First", JOptionPane.INFORMATION_MESSAGE);
            enemyPhase();
        }
        // The Battle begins and loops here.
        while (nosian.getHP() > 0 && enemy.getEnemyHP() > 0)
        {
            if(nosian.getHP() > 0 && battleTurn.equals("Hero"))
            {
                heroPhase();
            }
            if(enemy.getEnemyHP() > 0 && battleTurn.equals("Enemy"))
            {
                enemyPhase();
            }
        }
    }


    /**
     * Begins and iterates the Enemy's Turn. Deals damage to the Hero, Ally, and Ally 2 and can
     * inflict Status Effects.
     * 
     * Date created: October 20, 2020
     * Last Updated: 11/25/2021
     */
    private void enemyPhase()
    {
        if (statusEffect.getEnemyStatusEffect().equalsIgnoreCase("Frozen") || statusEffect.getEnemyStatusEffect().equalsIgnoreCase("Stunned"))
        {
            JOptionPane.showMessageDialog(null, enemyName + " is " + statusEffect.getEnemyStatusEffect() + "!\nThey cannot move this turn.\n"
            + statusEffect.iterateTurn(enemyName), statusEffect.getEnemyStatusEffect(), JOptionPane.INFORMATION_MESSAGE);

            //If it is not a Friendly Battle (Duel).
            if(!friendlyBattle)
            {
                if(enemy2Available) //Enemy 2 is available.
                {
                    if(enemy2.getEnemyHP() > 0) //Enemy 2 available and not fainted.
                    {
                        battleTurn = "Enemy 2";
                    }
                    else
                    {
                        if(ally.getHP() > 0) //Enemy 2 fainted; ally is healthy.
                        {
                            battleTurn = "Ally";
                        }
                        else if(ally2.getHP() > 0) //Enemy 2 and ally fainted; ally 2 is healthy.
                        {
                            battleTurn = "Ally 2";
                        }
                        else //All but Enemy 1 and Hero have fainted.
                        {
                            battleTurn = "Hero";
                        }
                    }
                }
                else if(ally.getHP() > 0) //Enemy 2 unavailable; ally is healthy.
                {
                    battleTurn = "Ally";
                }
                else if(ally2Available) //Enemy 2 unavailable; ally 1 fainted.
                {
                    if(ally2.getHP() > 0) //ally 2 is healthy.
                    {
                        battleTurn = "Ally 2";
                    }
                    else //ally 1 and 2 + enemy 2 have fainted.
                    {
                        battleTurn = "Hero";
                    }
                }
                else //All but the Enemy and Hero have fainted.
                {
                    battleTurn = "Hero";
                }
            }
            else //This is a Friendly Battle - Give control to Hero after turn.
            {
                battleTurn = "Hero";
            }
            //this return completes the enemy phase without running through the code below.
            return;
        }

        //We must identify which enemy's turn it is. 
        //This way, we can make things neater and coherent.
        Enemy currentEnemy = null;
        if(battleTurn.equals("Enemy"))
        {
            currentEnemy = enemy;
        }
        else
        {
            currentEnemy = enemy2;
        }
        





        //message stores events that have happened this phase.
        String message = "";

        //This boolean keeps track of if the status effect has been iterated.
        boolean effectIterated = false;

        //the enemy iterates their status effect turn first (if their status effect lasts more than 1 turn) before moving, so status effects can take place before acting.
        if(statusEffect.getEnemyEffectLength() > 1)
        {
            message += statusEffect.iterateTurn(enemyName);
            effectIterated = true;
        }

        Random rand = new Random();
        int randomNum;

        //If statement needed to avoid nullpointerexception for friendly duels.
        //Also Determines who the enemy's options of attacking are.
        if(ally != null && ally2 != null) //3 options of attack.
        {
            //This series of if statements determines who will be attacked by the enemy: the hero or ally.
            if(nosian.getHP() != 0 && ally.getHP() != 0 && ally2.getHP() != 0)
            {
                //Enemy randomly chooses who to attack. Value 0 attacks Hero, Value 1 attacks Ally, and Value 2 attacks Ally2.
                randomNum = rand.nextInt(3);
            }
            else if(nosian.getHP() != 0 && ally.getHP() != 0) //Ally2 has fainted.
            {
                //Can attack nosian or ally.
                randomNum = rand.nextInt(2);
            }
            else if(nosian.getHP() != 0 && ally2.getHP() != 0) //Ally has fainted.
            {
                //Can attack nosian or ally2.
                randomNum = rand.nextInt(2);

                if(randomNum == 1) //Since 1 is assigned to ally1 and they are fainted, target ally2 instead.
                {
                    randomNum = 2;
                }
            }
            else if(ally.getHP() != 0 && ally2.getHP() != 0) //Hero has fainted.
            {
                //Can attack ally or ally2.
                randomNum = rand.nextInt(2) + 1;
            }
            //if hero's HP is not 0
            else if(nosian.getHP() != 0)
            {
                //attacks hero
                randomNum = 0;
            }
            else if(ally2.getHP() != 0)
            {
                randomNum = 2;
            }
            //if ally's HP is not 0
            else //if(ally.getHP() != 0)
            {
                //attacks ally
                randomNum = 1;
            }
        }
        else if(ally != null) //No second ally this time.
        {
            if(nosian.getHP() != 0 && ally.getHP() != 0) //Ally2 has fainted.
            {
                //Can attack nosian or ally.
                randomNum = rand.nextInt(2);
            }
            //if hero's HP is not 0
            else if(nosian.getHP() != 0)
            {
                //attacks hero
                randomNum = 0;
            }
            //if ally's HP is not 0
            else //if(ally.getHP() != 0)
            {
                //attacks ally
                randomNum = 1;
            }
        }
        else //This is a friendly duel.
        {
            //Attack only the hero. This happens only in friendly duels.
            randomNum = 0;
        }

        int randomNum2;
        if(ally != null) //It is not a friendly duel - enemies have 2 skills.
        {
            randomNum2 = rand.nextInt(2);
        }
        else //There is no ally by your side (it is a friendly duel)
        {
            //Gats a random number from 0 to the available skills the enemy has.
            randomNum2 = rand.nextInt(currentEnemy.getAvailableEnemySkills());
            //System.out.println(randomNum2);
        }

        Skill skillUsed = currentEnemy.getSkill(randomNum2);

        //Status Effect - the Status Effect that the Skill has.
        String skillStatusEffect = skillUsed.getEffect();
        //Percent Proc - Chance for an enemy to give a status effect to the target.
        int skillStatusEffectProc = skillUsed.getProcChance();
        //Status Efficacy - Strength of a status effect. 
        //Status Effects like Silence or Pacify will not need skillStatusEfficacy.
        int skillStatusEfficacy = skillUsed.getEffectEfficacy();
        //Status Length - How long a status effect will last.
        int skillStatusLength = skillUsed.getEffectLength();
        //Description of the skill.
        String skillDesc = skillUsed.getDescription();
        
        int beforeSkillDamage = skillUsed.getDamage();
        int afterSkillDamage = 0;
        int blockedDamage;
        String target = skillUsed.getTarget();
        String specificTarget = "";

        //Attacks the Hero, Ally, or Second Ally.
        if(target.equals("Enemy"))
        {
            //Attack Hero
            if(randomNum == 0)
            {
                specificTarget = "Nosian";
                //Attack Hero as a Mage/Sage. Uses Hero's Resistance.
                if (currentEnemy.getEnemyClass().equals("Mage") || currentEnemy.getEnemyClass().equals("Sage"))
                {
                    // Reduces incoming damage by the value of the hero's resistance attribute.
                    if (beforeSkillDamage < nosian.getResistance()) //damage is less than resistance.
                    {
                        afterSkillDamage = 0;
                        blockedDamage = beforeSkillDamage;
                    }
                    else //Damage is more than resistance.
                    {
                        afterSkillDamage = beforeSkillDamage - nosian.getResistance();
                        blockedDamage = nosian.getResistance();
                    }
                    hurtHero(afterSkillDamage);

                    message += currentEnemy.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to deal " + beforeSkillDamage
                            + "HP of Damage to " + specificTarget + "! \n(" + skillDesc
                            + ")\n\n" + specificTarget + "'s Resistance Stat reduced the incoming damage by " + blockedDamage
                            + "HP!\n" + specificTarget + " took " + afterSkillDamage + "HP of damage!\n" + specificTarget + "'s HP is now: " 
                            + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP.";
                    System.out.println(enemyName + " used " + skillUsed.getSkillName() + " to deal " + afterSkillDamage + "HP of Damage to " + heroName);
                }
                else //If enemy class is warrior (Attack Hero as a Warrior/Beserker. Uses Hero Armor.)
                {
                    // Reduces incoming damage by the value of the hero's armor attribute.
                    if ((beforeSkillDamage - nosian.getArmor()) < 0)
                    {
                        afterSkillDamage = 0;
                        blockedDamage = beforeSkillDamage;
                    }
                    else
                    {
                        afterSkillDamage = beforeSkillDamage - nosian.getArmor();
                        blockedDamage = nosian.getArmor();
                    }
                    hurtHero(afterSkillDamage);

                    if(currentEnemy.getEnemyHP() > currentEnemy.getEnemyMaxHP())
                    {
                        currentEnemy.setEnemyHP(currentEnemy.getEnemyMaxHP());
                    }
                    message += currentEnemy.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to deal " + beforeSkillDamage
                                    + "HP of Damage to " + specificTarget + "! \n(" + skillDesc
                                    + ")\n\n " + specificTarget + "'s Armor Stat reduced the incoming damage by " + blockedDamage
                                    + "HP!\nNosian took " + afterSkillDamage + "HP of damage!\n" + specificTarget + "'s HP is now: " 
                                    + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP.";
                    System.out.println(enemyName + " used " + skillUsed.getSkillName() + " to deal " + afterSkillDamage + "HP of Damage to " + heroName);

                }
            }
            else if(randomNum == 1)//Attack Ally 1
            {   
                specificTarget = ally.getName();
                //Attack the ally as a mage! Reduces damage based on ally's resistance attribute.
                if (currentEnemy.getEnemyClass().equals("Mage") || currentEnemy.getEnemyClass().equals("Sage"))
                {
                    // Reduces incoming damage by the value of the ally's resistance attribute.
                    if ((beforeSkillDamage - ally.getResistance()) < 0)
                    {
                        afterSkillDamage = 0;
                        blockedDamage = beforeSkillDamage;
                    }
                    else
                    {
                        afterSkillDamage = beforeSkillDamage - ally.getResistance();
                        blockedDamage = ally.getResistance();
                    }
                    hurtAlly(afterSkillDamage);

                    //Used in case the enemy casts a Drain spell.
                    if(currentEnemy.getEnemyHP() > currentEnemy.getEnemyMaxHP())
                    {
                        currentEnemy.setEnemyHP(currentEnemy.getEnemyMaxHP());
                    }

                    message += currentEnemy.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to deal " + beforeSkillDamage
                            + "HP of Damage to " + specificTarget + "! \n(" + skillDesc
                            + ")\n\n" + specificTarget + "'s Resistance Stat reduced the incoming damage by " + blockedDamage
                            + "HP!\n" + specificTarget + " took " + afterSkillDamage + "HP of damage!\n" 
                            + specificTarget + "'s HP is now: " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP.";
                }
                else //attack the ally as a warrior! Reduces damage based on the ally's armor attribute.
                {
                    // Reduces incoming damage by the value of the ally's armor attribute.
                    if ((beforeSkillDamage - ally.getArmor()) < 0)
                    {
                        afterSkillDamage = 0;
                        blockedDamage = beforeSkillDamage;
                    }
                    else
                    {
                        afterSkillDamage = beforeSkillDamage - ally.getArmor();
                        blockedDamage = ally.getArmor();
                    }
                    hurtAlly(afterSkillDamage);

                    message += currentEnemy.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to deal " + beforeSkillDamage
                                    + "HP of Damage to " + specificTarget + ". \n(" + skillDesc
                                    + ")\n\n" + specificTarget + "'s Armor Stat reduced the incoming damage by " + blockedDamage
                                    + "HP!\n" + specificTarget + " took " + afterSkillDamage + "HP of damage!\n" 
                                    + specificTarget + "'s HP is now: " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP.";
                }

                System.out.println(enemyName + " used " + skillUsed.getSkillName() + " to deal " + afterSkillDamage + "HP of Damage to " + ally.getName() + "!");
            }
            else //Attack Ally 2.
            {
                specificTarget = ally2.getName();
                //Attack the second ally as a mage! Reduces damage based on ally's resistance attribute.
                if (currentEnemy.getEnemyClass().equals("Mage") || currentEnemy.getEnemyClass().equals("Sage"))
                {
                    // Reduces incoming damage by the value of the ally's resistance attribute.
                    if ((beforeSkillDamage - ally2.getResistance()) < 0)
                    {
                        afterSkillDamage = 0;
                        blockedDamage = beforeSkillDamage;
                    }
                    else
                    {
                        afterSkillDamage = beforeSkillDamage - ally2.getResistance();
                        blockedDamage = ally2.getResistance();
                    }
                    hurtAlly(afterSkillDamage);

                    //Used in case the enemy casts a Drain spell.
                    if(currentEnemy.getEnemyHP() > currentEnemy.getEnemyMaxHP())
                    {
                        currentEnemy.setEnemyHP(currentEnemy.getEnemyMaxHP());
                    }

                    message += currentEnemy.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to deal " + beforeSkillDamage
                            + "HP of Damage to " + specificTarget + "!\n(" + skillDesc
                            + ")\n\n" + specificTarget + "'s Resistance Stat reduced the incoming damage by " + blockedDamage
                            + "HP!\n" + specificTarget + " took " + afterSkillDamage + "HP of damage!\n" 
                            + specificTarget + "'s HP is now: " + ally2.getHP() + "HP / " + ally2.getMaxHP() + "HP.";
                }
                else //attack the second ally as a warrior! Reduces damage based on the ally's armor attribute.
                {
                    // Reduces incoming damage by the value of the ally's armor attribute.
                    if ((beforeSkillDamage - ally2.getArmor()) < 0)
                    {
                        afterSkillDamage = 0;
                        blockedDamage = beforeSkillDamage;
                    }
                    else
                    {
                        afterSkillDamage = beforeSkillDamage - ally2.getArmor();
                        blockedDamage = ally2.getArmor();
                    }
                    hurtAlly(afterSkillDamage);

                    message += currentEnemy.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to deal " + beforeSkillDamage
                                    + "HP of Damage to " + specificTarget + ". \n(" + skillDesc
                                    + ")\n\n" + specificTarget + "'s Armor Stat reduced the incoming damage by " + blockedDamage
                                    + "HP!\n" + specificTarget + " took " + afterSkillDamage + "HP of damage!\n" 
                                    + specificTarget + "'s HP is now: " + ally2.getHP() + "HP / " + ally2.getMaxHP() + "HP.";
                }

                System.out.println(enemyName + " used " + skillUsed.getSkillName() + " to deal " + afterSkillDamage + "HP of Damage to " + ally2.getName() + "!");
            }
        }
        else if(target.equals("Ally/Self")) //heals either the fellow enemy or themself, depending on who is hurt more.
        {
            //Having the second enemy present complicates things. However, some battles will not have 2 enemies.
            if(enemy2 != null)
            {
                int currentEnemyHPPercentage;
                int otherEnemyHPPercentage;
                Enemy healer = null;
                Enemy recipient = null;
    
                if(battleTurn.equals("Enemy")) //it is enemy 1's turn.
                {
                    healer = enemy;
                    recipient = enemy2;
    
                    currentEnemyHPPercentage = enemy.getEnemyHP() / enemy.getEnemyMaxHP();
                    otherEnemyHPPercentage = enemy2.getEnemyHP() / enemy2.getEnemyMaxHP();
                }
                else //it is enemy 2's turn.
                {
                    healer = enemy2;
                    recipient = enemy;
    
                    currentEnemyHPPercentage = enemy2.getEnemyHP() / enemy2.getEnemyMaxHP();
                    otherEnemyHPPercentage = enemy.getEnemyHP() / enemy.getEnemyMaxHP();
                }
    
                //If enemy needs healing more than enemy2 and enemy HP is not 0.
                if(currentEnemyHPPercentage < otherEnemyHPPercentage && currentEnemyHPPercentage != 0) 
                {
                    //The enemy heals themself.
                    //if skill would heal enemy HP over maxHP,
                    if(beforeSkillDamage > (healer.getEnemyMaxHP() - healer.getEnemyMaxHP()))
                    {
                        //set healing to maxHP - HP.
                        beforeSkillDamage = healer.getEnemyMaxHP() - healer.getEnemyMaxHP();
                    }
                    healer.addEnemyHP(beforeSkillDamage);
                    message += healer.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to heal themself for " + beforeSkillDamage + "HP.\n(" + skillDesc + ")\n\n" + healer.getEnemyName() + "'s HP is now: " + healer.getEnemyHP() + "HP / " + healer.getEnemyMaxHP() + "HP!";
                    System.out.println(healer.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to heal themself for " + beforeSkillDamage + "HP.");
                }
                else //heal the other enemy.
                {
                    //The enemy heals themself.
                    //if skill would heal enemy HP over maxHP,
                    if(beforeSkillDamage > (recipient.getEnemyMaxHP() - recipient.getEnemyMaxHP()))
                    {
                        //set healing to maxHP - HP.
                        beforeSkillDamage = recipient.getEnemyMaxHP() - recipient.getEnemyMaxHP();
                    }
                    recipient.addEnemyHP(beforeSkillDamage);
                    message += healer.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to heal their comrade, " + recipient.getEnemyName() + ", for " + beforeSkillDamage + "HP.\n(" + skillDesc + ")\n\n" + recipient.getEnemyName() + "'s HP is now: " + recipient.getEnemyHP() + "HP / " + recipient.getEnemyMaxHP() + "HP!";
                    System.out.println(healer.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to heal their comrade, " + recipient.getEnemyName() + ", for " + beforeSkillDamage + "HP.");
                }
            }
            else //Battle has 1 enemy. Only heals self.
            {
                //The enemy heals themself.
                //if skill would heal enemy HP over maxHP,
                if(beforeSkillDamage > (enemy.getEnemyMaxHP() - enemy.getEnemyMaxHP()))
                {
                    //set healing to maxHP - HP.
                    beforeSkillDamage = enemy.getEnemyMaxHP() - enemy.getEnemyMaxHP();
                }
                enemy.addEnemyHP(beforeSkillDamage);
                message += enemy.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to heal themself for " + beforeSkillDamage + "HP.\n(" + skillDesc + ")\n\n" + enemy.getEnemyName() + "'s HP is now: " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP!";
                System.out.println(enemy.getEnemyName() + " used the Skill " + skillUsed.getSkillName() + " to heal themself for " + beforeSkillDamage + "HP.");
            }
           
        }
        
        //Proc the status effect of the enemy's skill.
        if (skillStatusEffect.equals("Drain")) 
        {
            int drainAmount = statusEffect.drain("Enemy", afterSkillDamage, skillStatusEffectProc);
            System.out.println(enemyName + " drained " + drainAmount + "HP from " + specificTarget + "!");
            message += "\n\n" + enemyName + " drained " + drainAmount + "HP from " + specificTarget + "!";
            message += "\n" + enemyName + " now has " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP.";
        }
        else
        {
            message += "\n" + statusEffectProc(skillStatusEffect, enemyName, specificTarget, skillStatusEffectProc, skillStatusEfficacy, skillStatusLength);
        }
        

        //if there is only 1 turn left of the status effect.
        if(!effectIterated)
        {
            message += statusEffect.iterateTurn(enemyName);
            effectIterated = true;
        }

        JOptionPane.showMessageDialog(null, message, "Enemy Phase Results", JOptionPane.INFORMATION_MESSAGE);
        //This if-statement notifies the user that the Hero/Ally has fainted if either have 0 HP.
        if(nosian.getHP() == 0)
        {
            JOptionPane.showMessageDialog(null, heroName + "'s HP has dropped to 0! \nThey have fainted and are unable to move the rest of this battle.", heroName + " has fainted!", JOptionPane.WARNING_MESSAGE);
            System.out.println(heroName + "'s HP is 0! Nosian has Fainted and cannot move the rest of the battle!");
            statusEffect.clearStatusEffects("Hero");
            statusEffect.setHeroStatusEffect("Fainted");
            statusEffect.setHeroStatusEffectDescription("Fainted: Nosian was defeated! They can no longer move this battle.");
            statusEffect.setHeroEffectEfficacy(0);
            statusEffect.setHeroEffectLength(0);
        }
        else if(ally.getHP() == 0)
        {
            System.out.println(ally.getName() + "'s HP is 0!\n" + ally.getName() + " has fainted and is unable to move the rest of this battle!");
            JOptionPane.showMessageDialog(null, ally.getName() + " has fainted! They cannot take any more actions this battle.", ally.getName() + " has fainted!", JOptionPane.WARNING_MESSAGE);
            statusEffect.clearStatusEffects("Ally");
            statusEffect.setAllyStatusEffect("Fainted");
            statusEffect.setAllyStatusEffectDescription("Fainted: " + ally.getName() + " was defeated! They can no longer move this battle.");
            statusEffect.setAllyEffectEfficacy(0);
            statusEffect.setAllyEffectLength(0);
        }
        else if(ally2 != null)
        {
            if(ally2.getHP() == 0)
            {
                System.out.println(ally2.getName() + "'s HP is 0!\n" + ally2.getName() + " has fainted and is unable to move the rest of this battle!");
                JOptionPane.showMessageDialog(null, ally2.getName() + " has fainted! They cannot take any more actions this battle.", ally2.getName() + " has fainted!", JOptionPane.WARNING_MESSAGE);
                statusEffect.clearStatusEffects("Ally");
                statusEffect.setAllyStatusEffect("Fainted");
                statusEffect.setAllyStatusEffectDescription("Fainted: " + ally2.getName() + " was defeated! They can no longer move this battle.");
                statusEffect.setAllyEffectEfficacy(0);
                statusEffect.setAllyEffectLength(0);
            }
        }
        //This if-statement completes the enemyPhase by changing the battleTurn.
        if(!friendlyBattle)
        {
            if(enemy2Available) //Enemy 2 is available.
            {
                if(enemy2.getEnemyHP() > 0)
                {
                    battleTurn = "Enemy 2";
                }
                else
                {
                    if(ally.getHP() > 0)
                    {
                        battleTurn = "Ally";
                    }
                    else if(ally2.getHP() > 0)
                    {
                        battleTurn = "Ally 2";
                    }
                    else 
                    {
                        battleTurn = "Hero";
                    }
                }
            }
            else if(!enemy2Available && ally.getHP() > 0)
            {
                battleTurn = "Ally";
            }
            else if(!enemy2Available && ally2Available)
            {
                if(ally2.getHP() > 0)
                {
                    battleTurn = "Ally 2";
                }
                else
                {
                    battleTurn = "Hero";
                }
            }
            else
            {
                battleTurn = "Hero";
            }
        }
        else
        {
            battleTurn = "Hero";
        }
    }

    /**
     * Begins and iterates the hero's turn. 
     * Accepts user input.
     * 
     * Date created: October 20, 2020
     * Last Updated: 11/25/2021
     */
    private void heroPhase()
    {
        if (statusEffect.getHeroStatusEffect().equalsIgnoreCase("Frozen") || statusEffect.getHeroStatusEffect().equalsIgnoreCase("Stunned")) {
            JOptionPane.showMessageDialog(null,
                    heroName + " is " + statusEffect.getHeroStatusEffect() + "!\nThey cannot move this turn.\n"
                    + statusEffect.iterateTurn(heroName), statusEffect.getHeroStatusEffect(), JOptionPane.INFORMATION_MESSAGE);

            //Complete the phase by giving control to the enemy.
            if(enemy2Available && enemy.getEnemyHP() > 0)
            {
                battleTurn = "Enemy 2";
            }
            else 
            {
                battleTurn = "Enemy";
            }

            return;
        }
        //message stores events that have happened this phase.
        String message = "";

        //This boolean keeps track of if the status effect has been iterated.
        boolean effectIterated = false;

        //the hero iterates their status effect turn first (if their status effect lasts more than 1 turn) before moving, so status effects can take place before acting.
        if(statusEffect.getHeroEffectLength() > 1)
        {
            String tempMessage = statusEffect.iterateTurn(heroName);

            //If the hero was poisoned or bleeding, shows the effect before the Hero Phase menu. Else, shows in when attacking.
            if(statusEffect.getHeroStatusEffect().equals("Poisoned") || statusEffect.getHeroStatusEffect().equals("Bleeding"))
            {
                JOptionPane.showMessageDialog(null, tempMessage, statusEffect.getHeroStatusEffect(), JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                message += tempMessage;
            }

            effectIterated = true;
        }

        do
        {
            //Menu options for the Hero Phase.
            String[] commands = {"Hero Attributes", "Skill Damage",
                    "Status Effects", "Enemy Info", "Ally Info", "Mend", "SP Attack", "Attack"};
            int optionChosen;
            //Checks if the Hero has an ally.
            if(ally != null && ally2Available && enemy2Available) //3V2 Battle Setting
            {
                //This optionChosen shows Ally 1 and Ally 2's HP in the menu.
                optionChosen = JOptionPane.showOptionDialog(null, "What is Nosian going to do?\n\n" 
                                                            + "(" + statusEffect.getHeroStatusEffect() + ")   Nosian's HP:        " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP" 
                                                            + "\n(" + statusEffect.getAllyStatusEffect() + ")   " + ally.getName() + "'s HP:          " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP" 
                                                            + "\n(" + statusEffect.getAlly2StatusEffect() + ")   " + ally2.getName() + "'s HP:          " + ally2.getHP() + "HP / " + ally2.getMaxHP() + "HP" 
                                                            + "\n(" + statusEffect.getEnemyStatusEffect() + ")   " + enemyName + "'s HP:    " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP"
                                                            + "\n(" + statusEffect.getEnemy2StatusEffect() + ")   " + enemy2.getEnemyName() + "'s HP:    " + enemy2.getEnemyHP() + "HP / " + enemy2.getEnemyMaxHP() + "HP", "Hero Phase", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, commands, commands[6]);
            }
            else if(ally != null && enemy2Available) //2V2 Battle Setting
            {
                //This optionChosen shows the Ally's and Enemy 2's HP in the menu.
                optionChosen = JOptionPane.showOptionDialog(null, "What is Nosian going to do?\n\n" 
                                                            + "(" + statusEffect.getHeroStatusEffect() + ")   Nosian's HP:        " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP" 
                                                            + "\n(" + statusEffect.getAllyStatusEffect() + ")   " + ally.getName() + "'s HP:          " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP" 
                                                            + "\n(" + statusEffect.getEnemyStatusEffect() + ")   " + enemyName + "'s HP:    " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP"
                                                            + "\n(" + statusEffect.getEnemy2StatusEffect() + ")   " + enemy2.getEnemyName() + "'s HP:    " + enemy2.getEnemyHP() + "HP / " + enemy2.getEnemyMaxHP() + "HP", "Hero Phase", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, commands, commands[6]);
            }
            else if(ally != null) //2V1 Battle Setting
            {
                //This optionChosen shows the Ally's HP in the menu.
                optionChosen = JOptionPane.showOptionDialog(null, "What is Nosian going to do?\n\n" 
                                                            + "(" + statusEffect.getHeroStatusEffect() + ")   Nosian's HP:        " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP" 
                                                            + "\n(" + statusEffect.getAllyStatusEffect() + ")   " + ally.getName() + "'s HP:          " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP" 
                                                            + "\n(" + statusEffect.getEnemyStatusEffect() + ")   " + enemyName + "'s HP:    " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP"
                                                            , "Hero Phase", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, commands, commands[6]);
            }
            else //1V1 Battle Setting - Friendly Duel
            {
                //The difference here is that optionChosen does not show ally HP since it would be null.
                optionChosen = JOptionPane.showOptionDialog(null, "What is Nosian going to do?\n\n(" + statusEffect.getHeroStatusEffect() + ")   Nosian's HP:        " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP\n(" + statusEffect.getEnemyStatusEffect() + ")   " + enemyName + "'s HP:    " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP", "Hero Phase", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, commands, commands[6]);
            }
            

            //Hero Attributes - Show Hero's Current Attributes
            if (optionChosen == 0)
            {
                String[] listChoices = {"OK", "Attribute Descriptions"};
                int choice = JOptionPane.showOptionDialog(null, nosian.listAttributes(), "Hero Attributes", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, listChoices, listChoices[0]);
                if(choice == 1)
                {
                    JOptionPane.showMessageDialog(null, nosian.attributeDescription(nosian.getSpecialization()), "Attribute Description", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            //skill damage - returns the damage done by every available skill.
            else if (optionChosen == 1)
            {
                if(ally != null && ally2 != null) //an ally is present.
                {
                    JOptionPane.showMessageDialog(null, heroName + "\n_________\n" + nosian.listAllSkillDamage() + "\n__________\n" 
                                                    + ally.getName() + "\n__________\n" + ally.getAllySkills() + "\n__________\n"
                                                    + ally2.getName() + "\n__________\n" + ally2.getAllySkills(), "Skill Damage",
                        JOptionPane.INFORMATION_MESSAGE);
                }
                else if(ally != null) //an ally is present.
                {
                    JOptionPane.showMessageDialog(null, heroName + "\n_________\n" + nosian.listAllSkillDamage() + "\n__________\n" + ally.getName() + "\n__________\n" + ally.getAllySkills(), "Skill Damage",
                        JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, heroName + "\n_________\n" + nosian.listAllSkillDamage() + "\n__________\n", "Skill Damage",
                        JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
            //Status effects - Shows the user status effects of combatants. Also allows the user to ask for more status effect information.
            else if (optionChosen == 2)
            {
                String[] optionChoice = {"OK", "More Information"};
                int optionNum = JOptionPane.showOptionDialog(null, statusEffect.getAllStatusEffects(), "Status Effects", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionChoice, optionChoice[0]);
                switch(optionNum)
                {
                    case 1:
                        JOptionPane.showMessageDialog(null, statusEffect.getStatusEffectInformation(), "Possible Status Effects", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        break;
                }
            }
            //Enemy Info - Returns the enemy's attributes and skill information.
            else if (optionChosen == 3)
            {
                JOptionPane.showMessageDialog(null, enemy.getEnemyInformation(), "Enemy Information", JOptionPane.INFORMATION_MESSAGE);
            }
            //Ally Info - Returns the ally's attributes and their skill information.
            else if (optionChosen == 4)
            {
                if(ally != null && ally2 != null)
                {
                    JOptionPane.showMessageDialog(null, ally.getAllyInformation() + "\n\n--------------------\n" + ally2.getAllyInformation(), "Ally Information", JOptionPane.INFORMATION_MESSAGE);
                }
                //Not a friendly duel
                if(ally != null)
                {
                    JOptionPane.showMessageDialog(null, ally.getAllyInformation(), "Ally Information", JOptionPane.INFORMATION_MESSAGE);
                }
                else //friendly duel
                {
                    JOptionPane.showMessageDialog(null, "You currently have no ally by your side.", "No Allies", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
            //Mend - Allows the user to heal the Hero or Ally.
            else if (optionChosen == 5)
            {
                int healAmount = 0;
                //if statement controls healAmount based on heroClass.
                if(nosian.getSpecialization().equals("Mage"))
                {
                    healAmount = (int)(nosian.getAffinity() * 0.7);
                }
                else
                {
                    healAmount = (int)(nosian.getConstitution() * 0.7);
                }

                //Checks if allyName is null, rather than comparing strings.
                if(ally != null && ally2 != null)
                {
                    //The if statement must be nested to avoid a null pointer exception in friendly duels.
                    //whiel the ally is not fainted, allows the user the option to choose who to heal.
                    if(ally.getHP() > 0 && ally2.getHP() > 0)
                    {
                        String[] healOption = {"Nosian", ally.getName(), ally2.getName()};
                        int option = JOptionPane.showOptionDialog(null, "Who will Nosian heal?\nNosian will heal for: " + healAmount + "HP. " 
                                                                        + "\n\nNosian's HP:   " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP\n" 
                                                                        + ally.getName() + "'s HP:   " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP\n" 
                                                                        + ally2.getName() + "'s HP:   " + ally2.getHP() + "HP / " + ally2.getMaxHP() + "HP", "Heal Target", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, healOption, healOption[0]);

                        //heals the hero.
                        if(option == 0)
                        {
                            if(healAmount > (nosian.getMaxHP() - nosian.getHP()))
                            {
                                healAmount = nosian.getMaxHP() - nosian.getHP();
                            }
                            nosian.setHP(nosian.getHP() + healAmount);
                            JOptionPane.showMessageDialog(null, heroName + " successfully healed themself for " + healAmount + "HP!\n\nNosian's HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!\n", "Healed Nosian for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(heroName + " successfully healed themself for " + healAmount + "HP!");
                            //Complete the phase by giving control to the enemy.
                            if(enemy2Available && enemy.getEnemyHP() > 0)
                            {
                                battleTurn = "Enemy 2";
                            }
                            else 
                            {
                                battleTurn = "Enemy";
                            }
                        }
                        //heals the ally.
                        else if(option == 1)
                        {
                            if(healAmount > (ally.getMaxHP() - ally.getHP()))
                            {
                                healAmount = ally.getMaxHP() - ally.getHP();
                            }
                            ally.addHP(healAmount);
                            JOptionPane.showMessageDialog(null, heroName + " successfully healed " + ally.getName() + " for " + healAmount + "HP!\n\n" + ally.getName() + "'s HP is now: " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP!\n", "Healed " + ally.getName() + " for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(heroName + " successfully healed " + ally.getName() + " for " + healAmount + "HP!");
                            //Complete the phase by giving control to the enemy.
                            if(enemy2Available && enemy.getEnemyHP() > 0)
                            {
                                battleTurn = "Enemy 2";
                            }
                            else 
                            {
                                battleTurn = "Enemy";
                            }
                        }
                        //heals the second ally.
                        else if(option == 2)
                        {
                            if(healAmount > (ally2.getMaxHP() - ally2.getHP()))
                            {
                                healAmount = ally2.getMaxHP() - ally2.getHP();
                            }
                            ally.addHP(healAmount);
                            JOptionPane.showMessageDialog(null, heroName + " successfully healed " + ally2.getName() + " for " + healAmount + "HP!\n\n" + ally2.getName() + "'s HP is now: " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP!\n", "Healed " + ally2.getName() + " for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(heroName + " successfully healed " + ally2.getName() + " for " + healAmount + "HP!");
                            //Complete the phase by giving control to the enemy.
                            if(enemy2Available && enemy.getEnemyHP() > 0)
                            {
                                battleTurn = "Enemy 2";
                            }
                            else 
                            {
                                battleTurn = "Enemy";
                            }
                        }
                    }
                    else if(ally.getHP() > 0)
                    {
                        String[] healOption = {"Nosian", ally.getName()};
                        int option = JOptionPane.showOptionDialog(null, "Who will Nosian heal?\nNosian will heal for: " + healAmount + "HP. " 
                                                                        + "\n\nNosian's HP:   " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP\n" 
                                                                        + ally.getName() + "'s HP:   " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP\n" 
                                                                        + ally2.getName() + "'s HP:   " + ally2.getHP() + "HP / " + ally2.getMaxHP() + "HP", "Heal Target", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, healOption, healOption[0]);

                        //heals the hero.
                        if(option == 0)
                        {
                            if(healAmount > (nosian.getMaxHP() - nosian.getHP()))
                            {
                                healAmount = nosian.getMaxHP() - nosian.getHP();
                            }
                            nosian.setHP(nosian.getHP() + healAmount);
                            JOptionPane.showMessageDialog(null, heroName + " successfully healed themself for " + healAmount + "HP!\n\nNosian's HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!\n", "Healed Nosian for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(heroName + " successfully healed themself for " + healAmount + "HP!");
                            //Complete the phase by giving control to the enemy.
                            if(enemy2Available && enemy.getEnemyHP() > 0)
                            {
                                battleTurn = "Enemy 2";
                            }
                            else 
                            {
                                battleTurn = "Enemy";
                            }
                        }
                        //heals the ally.
                        else
                        {
                            if(healAmount > (ally.getMaxHP() - ally.getHP()))
                            {
                                healAmount = ally.getMaxHP() - ally.getHP();
                            }
                            ally.addHP(healAmount);
                            JOptionPane.showMessageDialog(null, heroName + " successfully healed " + ally.getName() + " for " + healAmount + "HP!\n\n" + ally.getName() + "'s HP is now: " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP!\n", "Healed " + ally.getName() + " for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(heroName + " successfully healed " + ally.getName() + " for " + healAmount + "HP!");
                            //Complete the phase by giving control to the enemy.
                            if(enemy2Available && enemy.getEnemyHP() > 0)
                            {
                                battleTurn = "Enemy 2";
                            }
                            else 
                            {
                                battleTurn = "Enemy";
                            }
                        }
                    }
                    else if(ally2.getHP() > 0)
                    {
                        String[] healOption = {"Nosian", ally2.getName()};
                        int option = JOptionPane.showOptionDialog(null, "Who will Nosian heal?\nNosian will heal for: " + healAmount + "HP. " 
                                                                        + "\n\nNosian's HP:   " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP\n" 
                                                                        + ally.getName() + "'s HP:   " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP\n" 
                                                                        + ally2.getName() + "'s HP:   " + ally2.getHP() + "HP / " + ally2.getMaxHP() + "HP", "Heal Target", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, healOption, healOption[0]);

                        //heals the hero.
                        if(option == 0)
                        {
                            if(healAmount > (nosian.getMaxHP() - nosian.getHP()))
                            {
                                healAmount = nosian.getMaxHP() - nosian.getHP();
                            }
                            nosian.setHP(nosian.getHP() + healAmount);
                            JOptionPane.showMessageDialog(null, heroName + " successfully healed themself for " + healAmount + "HP!\n\nNosian's HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!\n", "Healed Nosian for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(heroName + " successfully healed themself for " + healAmount + "HP!");
                            //Complete the phase by giving control to the enemy.
                            if(enemy2Available && enemy.getEnemyHP() > 0)
                            {
                                battleTurn = "Enemy 2";
                            }
                            else 
                            {
                                battleTurn = "Enemy";
                            }
                        }
                        //heals the ally.
                        else
                        {
                            if(healAmount > (ally2.getMaxHP() - ally2.getHP()))
                            {
                                healAmount = ally2.getMaxHP() - ally2.getHP();
                            }
                            ally2.addHP(healAmount);
                            JOptionPane.showMessageDialog(null, heroName + " successfully healed " + ally2.getName() + " for " + healAmount + "HP!\n\n" + ally2.getName() + "'s HP is now: " + ally2.getHP() + "HP / " + ally2.getMaxHP() + "HP!\n", "Healed " + ally2.getName() + " for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(heroName + " successfully healed " + ally2.getName() + " for " + healAmount + "HP!");
                            //Complete the phase by giving control to the enemy.
                            if(enemy2Available && enemy.getEnemyHP() > 0)
                            {
                                battleTurn = "Enemy 2";
                            }
                            else 
                            {
                                battleTurn = "Enemy";
                            }
                        }
                    }
                    else //if ally has fainted, asks for confirmation to heal the hero.
                    {
                        String[] confirmation = {"YES", "NO"};
                        int choice = JOptionPane.showOptionDialog(null, heroName + " will heal themself for " + healAmount + "HP.\n\nNosian's Current HP: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!\n", "Heal Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmation, confirmation[0]);
                        if(choice == 0)
                        {
                            if(healAmount > (nosian.getMaxHP() - nosian.getHP()))
                            {
                                healAmount = nosian.getMaxHP() - nosian.getHP();
                            }
                            nosian.setHP(nosian.getHP() + healAmount);
                            JOptionPane.showMessageDialog(null, heroName + " successfully healed themself for " + healAmount + "HP!\n\nNosian's HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!\n", "Healed Nosian for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(heroName + " successfully healed themself for " + healAmount + "HP!");
                            //Complete the phase by giving control to the enemy.
                            if(enemy2Available && enemy.getEnemyHP() > 0)
                            {
                                battleTurn = "Enemy 2";
                            }
                            else 
                            {
                                battleTurn = "Enemy";
                            }
                        }
                    }
                }
                else if(ally != null)
                {
                    //The if statement must be nested to avoid a null pointer exception in friendly duels.
                    //whiel the ally is not fainted, allows the user the option to choose who to heal.
                    if(ally.getHP() > 0)
                    {
                        String[] healOption = {"Nosian", ally.getName()};
                        int option = JOptionPane.showOptionDialog(null, "Who will Nosian heal?\nNosian will heal for: " + healAmount + "HP. \n\nNosian's HP:   " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP\n" + ally.getName() + "'s HP:   " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP", "Heal Target", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, healOption, healOption[0]);

                        //heals the hero.
                        if(option == 0)
                        {
                            if(healAmount > (nosian.getMaxHP() - nosian.getHP()))
                            {
                                healAmount = nosian.getMaxHP() - nosian.getHP();
                            }
                            nosian.setHP(nosian.getHP() + healAmount);
                            JOptionPane.showMessageDialog(null, heroName + " successfully healed themself for " + healAmount + "HP!\n\nNosian's HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!\n", "Healed Nosian for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(heroName + " successfully healed themself for " + healAmount + "HP!");
                            //Complete the phase by giving control to the enemy.
                            if(enemy2Available && enemy.getEnemyHP() > 0)
                            {
                                battleTurn = "Enemy 2";
                            }
                            else 
                            {
                                battleTurn = "Enemy";
                            }
                        }
                        //heals the ally.
                        else if(option == 1)
                        {
                            if(healAmount > (ally.getMaxHP() - ally.getHP()))
                            {
                                healAmount = ally.getMaxHP() - ally.getHP();
                            }
                            ally.addHP(healAmount);
                            JOptionPane.showMessageDialog(null, heroName + " successfully healed " + ally.getName() + " for " + healAmount + "HP!\n\n" + ally.getName() + "'s HP is now: " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP!\n", "Healed " + ally.getName() + " for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(heroName + " successfully healed " + ally.getName() + " for " + healAmount + "HP!");
                            //Complete the phase by giving control to the enemy.
                            if(enemy2Available && enemy.getEnemyHP() > 0)
                            {
                                battleTurn = "Enemy 2";
                            }
                            else 
                            {
                                battleTurn = "Enemy";
                            }
                        }
                    }
                    else //if ally has fainted, asks for confirmation to heal the hero.
                    {
                        String[] confirmation = {"YES", "NO"};
                        int choice = JOptionPane.showOptionDialog(null, heroName + " will heal themself for " + healAmount + "HP.\n\nNosian's Current HP: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!\n", "Heal Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmation, confirmation[0]);
                        if(choice == 0)
                        {
                            if(healAmount > (nosian.getMaxHP() - nosian.getHP()))
                            {
                                healAmount = nosian.getMaxHP() - nosian.getHP();
                            }
                            nosian.setHP(nosian.getHP() + healAmount);
                            JOptionPane.showMessageDialog(null, heroName + " successfully healed themself for " + healAmount + "HP!\n\nNosian's HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!\n", "Healed Nosian for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(heroName + " successfully healed themself for " + healAmount + "HP!");
                            //Complete the phase by giving control to the enemy.
                            if(enemy2Available && enemy.getEnemyHP() > 0)
                            {
                                battleTurn = "Enemy 2";
                            }
                            else 
                            {
                                battleTurn = "Enemy";
                            }
                        }
                    }
                }
                else //if this is a friendly battle, automatically asks the user for confirmation to heal the hero.
                {
                    String[] confirmation = {"YES", "NO"};
                    int choice = JOptionPane.showOptionDialog(null, heroName + " will heal themself for " + healAmount + "HP.\n\nNosian's Current HP: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!\n", "Heal Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmation, confirmation[0]);
                    if(choice == 0)
                    {
                        if(healAmount > (nosian.getMaxHP() - nosian.getHP()))
                        {
                            healAmount = nosian.getMaxHP() - nosian.getHP();
                        }
                        nosian.setHP(nosian.getHP() + healAmount);
                        JOptionPane.showMessageDialog(null, heroName + " successfully healed themself for " + healAmount + "HP!\n\nNosian's HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!\n", "Healed Nosian for " + healAmount + "HP", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println(heroName + " successfully healed themself for " + healAmount + "HP!");
                        //Complete the phase by giving control to the enemy.
                        if(enemy2Available && enemy.getEnemyHP() > 0)
                        {
                            battleTurn = "Enemy 2";
                        }
                        else 
                        {
                            battleTurn = "Enemy";
                        }
                    }
                }
            }
            //SP Attack - Allows the user to attack the enemy with a Special skill of their choice.
            else if (optionChosen == 6)
            {
                String[] choices = nosian.getSpecialSkillNames();
                int skillChoice = JOptionPane.showOptionDialog(null,
                        "Which skill will you use?\n" + nosian.getSkillSummary(true), "Skill to Use",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);
                //This if statement is needed so the code does not crash when the user exits the attack screen.
                if(skillChoice != -1)
                {
                    Skill skillUsed = nosian.getSpecialSkill(skillChoice);

                    //Get the status effect being used in the regular skill.
                    String skillStatusEffect = skillUsed.getEffect();
                    //Percent Proc - Chance for an enemy to give a status effect to the target.
                    int skillStatusEffectProc = skillUsed.getProcChance();
                    //Status Efficacy - Strength of a status effect. 
                    //Status Effects like Silence or Pacify will not need skillStatusEfficacy.
                    int skillStatusEfficacy = skillUsed.getEffectEfficacy();
                    //Status Length - How long a status effect will last.
                    int skillStatusLength = skillUsed.getEffectLength();

                    //Gets the base skill damage.
                    int skillDamage = skillUsed.getDamage();

                    hurtEnemy(skillDamage);

                    message += heroName + " used the Skill " + skillUsed.getSkillName() + " to deal " + skillDamage
                                    + "HP of Damage to " + enemy.getEnemyName() + "!\n(" + skillUsed.getDescription() + ")\n\n" + enemy.getEnemyName() + "'s HP is now: " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP!\n";
                    System.out.println(heroName + " used " + skillUsed.getSkillName() + " to deal " + skillDamage + "HP of Damage to " + enemyName);
                    
                    //Complete the phase by giving control to the enemy.
                    if(enemy2Available && enemy.getEnemyHP() > 0)
                    {
                        battleTurn = "Enemy 2";
                    }
                    else 
                    {
                        battleTurn = "Enemy";
                    }

                    // Status Effect Proc
                    // Method calls ask for: Target, Efficacy, Proc Chance, Length.
                    if (skillStatusEffect.equals("Drain")) {
                        int drainAmount = statusEffect.drain("Hero", skillDamage, skillStatusEffectProc);
                        {
                            System.out.println(heroName + " drained " + drainAmount + "HP from " + enemyName + "!");
                            message += "\n\nNosian drained " + drainAmount + "HP from " + enemyName + "!";
                            message += "\nNosian now has " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP.";
                        }
                    }
                    else
                    {
                        message += statusEffectProc(skillStatusEffect, heroName, enemyName, skillStatusEffectProc, skillStatusEfficacy, skillStatusLength);
                    }

                    //if there is only 1 turn left of the status effect.
                    if(!effectIterated)
                    {
                        message += statusEffect.iterateTurn(heroName);
                        effectIterated = true;
                    }

                    JOptionPane.showMessageDialog(null, message, "Hero Phase Results", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            //Attack - Allows the user to attack the enemy with a skill of their choice.
            else if (optionChosen == 7)
            {
                String[] choices = nosian.getRegularSkillNames();
                int skillChoice = JOptionPane.showOptionDialog(null,
                        "Which skill will you use?\n" + nosian.getSkillSummary(false), "Skill to Use",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, choices[1]);
                //This if statement is needed so the code does not crash when the user exits the attack screen.
                if(skillChoice != -1)
                {
                    Skill skillUsed = nosian.getRegularSkill(skillChoice);
                    
                    //Get the status effect being used in the regular skill.
                    String skillStatusEffect = skillUsed.getEffect();
                    //Percent Proc - Chance for an enemy to give a status effect to the target.
                    int skillStatusEffectProc = skillUsed.getProcChance();
                    //Status Efficacy - Strength of a status effect. 
                    //Status Effects like Silence or Pacify will not need skillStatusEfficacy.
                    int skillStatusEfficacy = skillUsed.getEffectEfficacy();
                    //Status Length - How long a status effect will last.
                    int skillStatusLength = skillUsed.getEffectLength();

                    //Gets the base skill damage.
                    int skillDamage = skillUsed.getDamage();

                    hurtEnemy(skillDamage);

                    message += heroName + " used the Skill " + skillUsed.getSkillName() + " to deal " + skillDamage
                                    + "HP of Damage to " + enemy.getEnemyName() + "!\n(" + skillUsed.getDescription() + ")\n\n" + enemy.getEnemyName() + "'s HP is now: " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP!\n";
                    System.out.println(heroName + " used " + skillUsed.getSkillName() + " to deal " + skillDamage + "HP of Damage to " + enemyName);
                    
                    //Complete the phase by giving control to the enemy.
                    if(enemy2Available && enemy.getEnemyHP() > 0)
                    {
                        battleTurn = "Enemy 2";
                    }
                    else 
                    {
                        battleTurn = "Enemy";
                    }

                    // Status Effect Proc
                    // Method calls ask for: Target, Efficacy, Proc Chance, Length.
                    if (skillStatusEffect.equals("Drain")) {
                        int drainAmount = statusEffect.drain("Hero", skillDamage, skillStatusEffectProc);
                        {
                            System.out.println(heroName + " drained " + drainAmount + "HP from " + enemyName + "!");
                            message += "\n\nNosian drained " + drainAmount + "HP from " + enemyName + "!";
                            message += "\nNosian now has " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP.";
                        }
                    }
                    else
                    {
                        message += statusEffectProc(skillStatusEffect, heroName, enemyName, skillStatusEffectProc, skillStatusEfficacy, skillStatusLength);
                    }

                    //if there is only 1 turn left of the status effect.
                    if(!effectIterated)
                    {
                        message += statusEffect.iterateTurn(heroName);
                        effectIterated = true;
                    }

                    JOptionPane.showMessageDialog(null, message, "Hero Phase Results", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } while (battleTurn.equals("Hero"));
    }

    /**
     * Allows the ally to perform a random attack on the enemy or heal themself/hero.
     * 
     * Date Created: November 15, 2020
     * Last Updated: 11/25/2021
     */
    private void allyPhase()
    {
        Ally currentAlly;
        Ally otherAlly; 

        //Figures out which Ally is in control.
        if(battleTurn.equals("Ally"))
        {
            currentAlly = ally;
            otherAlly = ally2;
        }
        else //battleTurn equals Ally 2
        {
            currentAlly = ally2;
            otherAlly = ally;
        }


        if (statusEffect.getAllyStatusEffect().equalsIgnoreCase("Frozen") || statusEffect.getAllyStatusEffect().equalsIgnoreCase("Stunned"))
        {
            JOptionPane.showMessageDialog(null, ally.getName() + " is " + statusEffect.getAllyStatusEffect() + "!\nThey cannot move this turn.", statusEffect.getAllyStatusEffect(), JOptionPane.INFORMATION_MESSAGE);
            statusEffect.iterateTurn(currentAlly.getName());

            //Complete the phase by switching control to the other ally, the hero, or an enemy.
            //The battles should go as follows: Hero -> Enemy -> Enemy 2 -> Ally -> Ally 2 -> (Loops back to Hero)
            if(currentAlly == ally && ally2Available && ally2.getHP() > 0)
            {
                //Currently ally 1's turn. Pass the turn to Ally2 if they are not fainted.
                battleTurn = "Ally 2";
            }
            else if(currentAlly == ally && !ally2Available && nosian.getHP() > 0)
            {
                //Currently Ally 1's turn. Ally 2 is unavailable, so give control to Hero if they are not fainted.
                battleTurn = "Hero";
            }
            else if (currentAlly == ally2 && nosian.getHP() > 0)
            {
                //Currently Ally 2's turn. Pass the turn to the Hero if they are not fainted.
                battleTurn = "Hero";
            }
            else
            {
                //if Ally 2 AND Hero have fainted, give the turn to the enemy.
                battleTurn = "Enemy";
            }
            return;
        }
        //message stores events that have happened this phase.
        String message = "";

        //This boolean keeps track of if the status effect has been iterated.
        boolean effectIterated = false;

        //the ally iterates their status effect turn first (if their status effect lasts more than 1 turn) before moving, so status effects can take place before acting.
        if(statusEffect.getAllyEffectLength() > 1)
        {
            message += statusEffect.iterateTurn(currentAlly.getName());
            effectIterated = true;
        }

        //skills consists of all the Ally's available skills.
        ArrayList<Skill> skills = ally.getAllySkillsArrayList();

        Random rand = new Random();
        //randomNum decides which skill the ally will use on their turn.
        int randomNum = rand.nextInt(skills.size());

        Skill skillUsed = skills.get(randomNum);

        //Gets the Skill's primary status effect.
        String skillStatusEffect = skillUsed.getEffect();

        //Gets the Skill's secondary status effect.
        String secondaryStatusEffect = skillUsed.getSecondaryEffect();

        //Percent Proc - Chance for an enemy to give a status effect to the target.
        int skillStatusEffectProc = skillUsed.getProcChance();

        //Status Efficacy - Strength of a status effect. 
        //Status Effects like Silence or Pacify will not need skillStatusEfficacy.
        int skillStatusEfficacy = skillUsed.getEffectEfficacy();

        //Get the strength of the secondary effect.
        int secondaryStatusEfficacy = skillUsed.getSecondaryEffectEfficacy();
        
        /**
         * Note: Targets can have 4 different values.
         * They are: Enemy, Ally, Self, and Ally/Self.
         * They determine who the skill aims at and who recieves effects.
         */
         String skillTarget = skillUsed.getTarget();

         String effectTarget = skillUsed.getEffectTarget();


         /**
          * Note: Effect Stat Base can have 3 different values:
          * They are: Self, Enemy, or Constant.
          * 
          * StatBases determine how strong an effect is based on stat base. 
          *
          * For example,
          * If the statbase is "Enemy", Effect is "Headache", and efficacy is 35%, 
          * it reduces the Enemy's Affinity by 35% of their own Affinity.
          */
         String effectStatBase = skillUsed.getEfficacyStatBase();

         String secondStatBase = skillUsed.getSecondaryEfficacyStatBase();

        //Status Length - How long a status effect will last.
        int skillStatusLength = skillUsed.getEffectLength();

        int skillDamage = skillUsed.getDamage();

        String skillDesc = skillUsed.getDescription();


        //Skill targets an enemy
        if (skillTarget.equals("Enemy"))
        {
            //TODO Add enemy armor
            hurtEnemy(skillDamage);
            
            message += currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to deal " + skillDamage + "HP of Damage to " + enemyName + ". \n(" + skillDesc + ")\n\n" + enemyName + "'s HP is now: " + enemy.getEnemyHP() + "HP / " + enemy.getEnemyMaxHP() + "HP!";
            System.out.println(currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to deal " + skillDamage + "HP of Damage to " + enemyName + ".");
        }
        else if(skillTarget.equals("Ally"))
        {
            if(ally2Available) //Makes a decision to heal based on who is most damaged - Ally 2 or Hero.
            {
                //Heals the Hero or other Ally, depending on who has lower HP Percentage.
                double heroHPPercentage = nosian.getHP() / nosian.getMaxHP();
                double otherAllyHPPercentage = otherAlly.getHP() / otherAlly.getMaxHP();

                //Heals the hero if their HP percentage is less than ally2's and is not fainted. 
                if(heroHPPercentage < otherAllyHPPercentage && heroHPPercentage != 0)
                {
                    //if healing would exceed the hero's max HP, set healing to heal up to their Max HP.
                    if(skillDamage > (nosian.getMaxHP() - nosian.getHP()))
                    {
                        skillDamage = nosian.getMaxHP() - nosian.getHP();
                    }
                    nosian.setHP(nosian.getHP() + skillDamage);
                    message += currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal Nosian for " + skillDamage + "HP.\n(" + skillDesc + ")\n\nNosian's HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!";
                    System.out.println(currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal Nosian for " + skillDamage + "HP.");
                }
                else //heal the second ally - they have less HP Percentage than the first ally.
                {
                    //Heals the other Ally.
                    //If the skill would heal the Ally over their maxHP.
                    if(skillDamage > (otherAlly.getMaxHP() - otherAlly.getHP()))
                    {
                        skillDamage = otherAlly.getMaxHP() - otherAlly.getHP();
                    }
                    otherAlly.addHP(skillDamage);
                    message += currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal " + otherAlly.getName() + " for " + skillDamage + "HP.\n(" + skillDesc + ")\n" + otherAlly.getName() + "'s HP is now: " + otherAlly.getHP() + "HP / " + otherAlly.getMaxHP() + "HP!";
                    System.out.println(currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal " + otherAlly.getName() + " for " + skillDamage + "HP.");
                }
            }
            else //Ally 2 is not available, so Heal the hero. No decision needed.
            {
                //if healing would exceed the hero's max HP, set healing to heal up to their Max HP.
                if(skillDamage > (nosian.getMaxHP() - nosian.getHP()))
                {
                    skillDamage = nosian.getMaxHP() - nosian.getHP();
                }
                nosian.setHP(nosian.getHP() + skillDamage);
                message += currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal Nosian for " + skillDamage + "HP.\n(" + skillDesc + ")\n\nNosian's HP is now: " + nosian.getHP() + "HP / " + nosian.getMaxHP() + "HP!";
                System.out.println(currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal Nosian for " + skillDamage + "HP.");
            }
            
        }
        else if(skillTarget.equals("Self"))
        {
            //Ally heals self.
            //If the skill would heal the Ally over their maxHP.
            if(skillDamage > (currentAlly.getMaxHP() - currentAlly.getHP()))
            {
                skillDamage = currentAlly.getMaxHP() - currentAlly.getHP();
            }
            
            currentAlly.addHP(skillDamage);
            message += currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal themself for " + skillDamage + "HP.\n(" + skillDesc + ")\n" + currentAlly.getName() + "'s HP is now: " + currentAlly.getHP() + "HP / " + currentAlly.getMaxHP() + "HP!";
            System.out.println(currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal themself for " + skillDamage + "HP.");
        }
        else //if skillTarget is Ally/Self
        {
            //Heals the Hero or other Ally, depending on who has lower HP Percentage.
            double heroHPPercentage = nosian.getHP() / nosian.getMaxHP();
            double currentAllyHPPercentage = ally.getHP() / ally2.getMaxHP();
            double otherAllyHPPercentage = ally2.getHP() / ally2.getMaxHP();

            //Heals the hero if their HP percentage is less than ally2's and ally's and is not fainted. 
            if(heroHPPercentage < currentAllyHPPercentage && heroHPPercentage < otherAllyHPPercentage && heroHPPercentage != 0)
            {
                //if healing would exceed the hero's max HP, set healing to heal up to their Max HP.
                if(skillDamage > (ally.getMaxHP() - ally.getHP()))
                {
                    skillDamage = ally.getMaxHP() - ally.getHP();
                }
                nosian.setHP(nosian.getHP() + skillDamage);
                message += currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal Nosian for " + skillDamage + "HP.\n(" + skillDesc + ")\n\nNosian's HP is now: " + currentAlly.getHP() + "HP / " + currentAlly.getMaxHP() + "HP!";
                System.out.println(currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal Nosian for " + skillDamage + "HP.");
            }
            else if (otherAllyHPPercentage < currentAllyHPPercentage && otherAllyHPPercentage != 0) //heal the other ally - they have less HP Percentage than the first ally.
            {
                //Heals the other Ally.
                //If the skill would heal the Ally over their maxHP.
                if(skillDamage > (ally2.getMaxHP() - ally2.getHP()))
                {
                    skillDamage = ally2.getMaxHP() - ally2.getHP();
                }
                ally2.addHP(skillDamage);
                message += currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal " + ally2.getName() + " for " + skillDamage + "HP.\n(" + skillDesc + ")\n" + currentAlly.getName() + "'s HP is now: " + currentAlly.getHP() + "HP / " + currentAlly.getMaxHP() + "HP!";
                System.out.println(currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal " + ally2.getName() + " for " + skillDamage + "HP.");
            }
            else    //Ally heals self.
            {
                //If the skill would heal the Ally over their maxHP.
                if(skillDamage > (ally.getMaxHP() - ally.getHP()))
                {
                    skillDamage = ally.getMaxHP() - ally.getHP();
                }
                
                ally.addHP(skillDamage);
                message += currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal themself for " + skillDamage + "HP.\n(" + skillDesc + ")\n" + currentAlly.getName() + "'s HP is now: " + currentAlly.getHP() + "HP / " + currentAlly.getMaxHP() + "HP!";
                System.out.println(currentAlly.getName() + " used the Skill " + skillUsed.getSkillName() + " to heal themself for " + skillDamage + "HP.");
            }
        }

        if (skillStatusEffect.equals("Drain")) {
            int drainAmount = statusEffect.drain("Ally", skillDamage, skillStatusEffectProc);
            {
                System.out.println(currentAlly.getName() + " drained " + drainAmount + "HP from " + enemyName + "!");
                message += "\n" + currentAlly.getName() + " drained " + drainAmount + "HP from " + enemyName + "!";
                message += "\n" + currentAlly.getName() + " now has " + ally.getHP() + "HP / " + ally.getMaxHP() + "HP.";
            }
        }
        else
        {
            message += "\n" + statusEffectProc(skillStatusEffect, currentAlly.getName(), enemyName, skillStatusEffectProc, skillStatusEfficacy, skillStatusLength);
        }

        //if there is only 1 turn left of the status effect.
        if(!effectIterated)
        {
            message += "\n" + statusEffect.iterateTurn(currentAlly.getName());
            effectIterated = true;
        }


        JOptionPane.showMessageDialog(null, message, "Ally Phase Results", JOptionPane.INFORMATION_MESSAGE);
        ///Complete the phase by switching control to the other ally, the hero, or an enemy.
        //The battles should go as follows: Hero -> Enemy -> Enemy 2 -> Ally -> Ally 2 -> (Loops back to Hero)
        if(currentAlly == ally && ally2Available && ally2.getHP() > 0)
        {
            //Currently ally 1's turn. Pass the turn to Ally2.
            battleTurn = "Ally 2";
        }
        else if (nosian.getHP() > 0)
        {
            //Currently Ally 2's turn. Pass the turn to the Hero.
            battleTurn = "Hero";
        }
        else
        {
            //if Ally 2 AND Hero have fainted, give the turn to the enemy.
            battleTurn = "Enemy";
        }
    }

    /**
     * Uses the statusEffect object to incur status effects and their effectiveness and length.
     * 
     * Date Created: November 17, 2020
     * 
     * @param effect    - String
     * @param caster    - String
     * @param who       - String
     * @param proc      - int
     * @param efficacy  - int
     * @param length    - int
     * @return message  - String
     */
    private String statusEffectProc(String effect, String caster, String who, int proc, int efficacy, int length)
    {
        String message = "";
        // Status Effect Proc
        // Method calls ask for: Target, Efficacy, Proc Chance, Length.
        if (effect.equals("ReduceHP")) {
            //These methods return true when successfully procced. Otherwise, return false.
            if (!statusEffect.reduceHP(who, efficacy, proc, length)) {
                //If the status effect didn't proc.
                System.out.println(caster + " failed to Reduce " + who + "'s HP!");
                message = "\n" + caster + " failed to Reduce " + who + "'s HP!";
            }
            else
            {
                //The effect procced.
                message = "\n" + caster + " reduced " + who + "'s HP!";
            }
        }
        if (effect.equals("Bleed")) {
            if (!statusEffect.bleed(who, efficacy, proc, length)) {
                System.out.println(caster + " failed to lacerate " + who + "!");
                message = "\n" + caster + " failed to lacerate " + who + "!";
            }
            else
            {
                message = "\n" + caster + " lacerated " + who + "!";
            }
        }
        if (effect.equals("Poison")) {
            if (!statusEffect.poison(who, efficacy, proc, length)) {
                System.out.println(caster + " failed to Poison " + who + "!");
                message = "\n" + caster + " failed to Poison " + who + "!";
            }
            else
            {
                message = "\n" + caster + " poisoned " + who + "!";
            }
        }
        if (effect.equals("Weaken")) {
            if (!statusEffect.weaken(who, efficacy, proc, length)) {
                System.out.println(caster + " failed to Weaken " + who + "!");
                message = "\n" + caster + " failed to Weaken " + who + "!";
            }
            else
            {
                message = "\n" + caster + " weakened " + who + "!";
            }
        }
        if (effect.equals("Pacify")) {
            if (!statusEffect.pacify(who, proc, length)) {
                System.out.println(caster + " failed to Pacify " + who + "!");
                message = "\n" + caster + " failed to Pacify " + who + "!";
            }
            else
            {
                message = "\n" + caster + " pacified " + who + "!";
            }
        }
        if (effect.equals("Headache")) {
            if (!statusEffect.headache(who, efficacy, proc, length)) {
                System.out.println(who + " resisted Headache!");
                message = who + " resisted Headache!";
            }
            else
            {
                message = caster + " gave a headache to " + who + "!";
            }
        }
        if (effect.equals("Silence")) {
            if (!statusEffect.silence(who, proc, length)) {
                System.out.println(caster + " failed to Silence " + who + "!");
                message = caster + " failed to Silence " + who + "!";
            }
            else
            {
                message = caster + " silenced " + who + "!";
            }
        }
        if (effect.equals("ArmorBreak")) {
            if (!statusEffect.armorBreak(who, efficacy, proc, length)) {
                System.out.println(who + " resisted Armor Break!");
                message = who + " resisted Armor Break!";
            }
            else
            {
                message = caster + " broke " + who + "'s armor!";
            }
        }
        if (effect.equals("Sunder")) {
            if (!statusEffect.sunder(who, proc, length)) {
                System.out.println(caster + " failed to Sunder " + who + "!");
                message = caster + " failed to Sunder " + who + "!";
            }
            else
            {
                message = caster + " sundered " + who + "!";
            }
        }
        if (effect.equals("MentalCollapse")) {
            if (!statusEffect.mentalCollapse(who, efficacy, proc, length)) {
                System.out.println(who + " resisted Mental Collapse!");
                message = who + " resisted Mental Collapse!";
            }
            else
            {
                message = who + " suffered a Mental Collapse!";
            }
        }
        if (effect.equals("Submission")) {
            if (!statusEffect.submission(who, proc, length)) {
                System.out.println(who + " resisted Submission!");
                message = who + " resisted Submission!";
            }
            else
            {
                message = who + " fell under Submission!";
            }
        }
        if (effect.equals("Cripple")) {
            if (!statusEffect.cripple(who, efficacy, proc, length)) {
                System.out.println(caster + " failed to Cripple " + who + "!");
                message = caster + " failed to Cripple " + who + "!";
            }
            else
            {
                message = caster + " crippled " + who + "!";
            }
        }
        if (effect.equals("Immobilized")) {
            if (!statusEffect.immobilize(who, proc, length)) {
                System.out.println(caster + " failed to Immobilize " + who + "!");
                message = caster + " failed to Immobilize " + who + "!";
            }
            else
            {
                message = caster + " immobilized " + who + "!";
            }
        }
        if (effect.equals("Stun")) {
            if (!statusEffect.stun(who, proc, length)) {
                System.out.println(caster + " failed to Stun " + who + "!");
                message = caster + " failed to Stun " + who + "!";
            }
            else
            {
                message = caster + " stunned " + who + "!";
            }
        }
        if (effect.equals("Freeze")) {
            if (!statusEffect.freeze(who, proc, length)) {
                System.out.println(caster + " failed to Freeze " + who + "!");
                message = caster + " failed to Freeze " + who + "!";
            }
            else
            {
                message = caster + " froze " + who + "!";
            }
        }
        return message;
    }

    /**
     * Reduces the Hero's HP by the specified amount.
     * If HP would be below 0, set HP to 0.
     * 
     * Date created: October 20, 2020
     * Last Updated: 11/24/2021
     * 
     * Date Created: 
     * @param damage - int
     */
    private void hurtHero(int damage)
    {
        nosian.setHP(nosian.getHP() - damage);
        if(nosian.getHP() < 0)
        {
            nosian.setHP(0);
        }
    }

    /**
     * Reduces the Ally's HP by the specified amount.
     * If HP would be below 0, set HP to 0.
     * 
     * Date created: October 20, 2020
     * Last Updated: 11/24/2021
     * 
     * @param damage - int
     */
    private void hurtAlly(int damage)
    {
        ally.setHP(ally.getHP() - damage);
        if(ally.getHP() < 0)
        {
            ally.setHP(0);
        }
    }
    
    /**
     * Reduces the Enemy's HP by the specified amount.
     * If HP would be below 0, set HP to 0.
     * 
     * Date created: October 20, 2020
     * Last Updated: 11/24/2021
     * 
     * @param damage - int
     */
    private void hurtEnemy(int damage)
    {
        enemy.setEnemyHP(enemy.getEnemyHP() - damage);
        if(enemy.getEnemyHP() < 0)
        {
            enemy.setEnemyHP(0);
        }
    }

    
    /**
     * Returns the result of the battle and awards the Hero with XP and Money based
     * on the enemy they fought.
     * 
     * Date created: October 20, 2020
     * Last Updated: 11/25/2021
     * 
     * @return true if hero&ally win, and false if enemy wins
     */
    public boolean getResult()
    {
        enemy.stopEnemyTheme();
        if(ally2Available) //ally 2 is not null.
        {
            //all protagonist combatants have been downed. (DEFEAT)
            if (nosian.getHP() == 0 && ally.getHP() == 0 && ally2.getHP() == 0)
            {
                WavePlayer SAP;
                try
                {
                    SAP = new WavePlayer("Soundtrack\\Defeat.wav", false);
                    JOptionPane.showMessageDialog(null, heroName + ", " + ally.getName() + ", and " + ally2.getName() + " were Defeated in Battle. \nYou awake elsewhere, far away from the battle.", "Defeat", JOptionPane.INFORMATION_MESSAGE);
                    System.out.print("Defeat...");
                    //restores ally and hero HP.
                    nosian.setHP(nosian.getMaxHP());
                    ally.setHP(ally.getMaxHP());
                    ally2.setHP(ally2.getMaxHP());
                    statusEffect.clearAllStatusEffects();
                    JOptionPane.showMessageDialog(null, getTip(), "After-Battle Tip", JOptionPane.INFORMATION_MESSAGE);
                    SAP.stop();
                    Driver.resumeBGAudio();
                }
                catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
                {
                    e.printStackTrace();
                }
                return false;
            }
            else //if(enemy.getEnemyHP() == 0 && enemy2.getEnemyHP() == 0) //Enemies have been defeated (VICTORY)
            {
                WavePlayer SAP;
                try
                {
                    SAP = new WavePlayer("Soundtrack\\Victory.wav", false);
                    JOptionPane.showMessageDialog(null, "Congratulations! Nosian and " + ally.getName() + " defeated " + enemyName + "!\n" + enemyName + " had $" + enemyMoneyAward + "!\n\nNosian gained " + enemyXPAward + "XP!\n", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                    //restores ally and hero HP.
                    nosian.setHP(nosian.getMaxHP());
                    ally.setHP(ally.getMaxHP());
                    statusEffect.clearAllStatusEffects();
                    SAP.stop();
                    System.out.print("Victory! ");
                    //Rewards Money and XP based on enemyXPAward and enemyMoneyAward.
                    nosian.addMoney(enemyMoneyAward);
                    nosian.addExperience(enemyXPAward);
                    JOptionPane.showMessageDialog(null, getTip(), "After-Battle Tip", JOptionPane.INFORMATION_MESSAGE);
                    Driver.resumeBGAudio();
                }
                catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
                {
                    e.printStackTrace();
                }
                return true;
            }
        }
        else if (nosian.getHP() == 0 && ally.getHP() == 0) //Nosian and Ally have fainted - all protagonist combatants fall (DEFEAT)
        {
            WavePlayer SAP;
            try
            {
                SAP = new WavePlayer("Soundtrack\\Defeat.wav", false);
                JOptionPane.showMessageDialog(null, heroName + " and " + ally.getName() + " were Defeated in Battle. \nYou awake elsewhere, far away from the battle.", "Defeat", JOptionPane.INFORMATION_MESSAGE);
                System.out.print("Defeat...");
                //restores ally and hero HP.
                nosian.setHP(nosian.getMaxHP());
                ally.setHP(ally.getMaxHP());
                statusEffect.clearAllStatusEffects();
                JOptionPane.showMessageDialog(null, getTip(), "After-Battle Tip", JOptionPane.INFORMATION_MESSAGE);
                SAP.stop();
                Driver.resumeBGAudio();
            }
            catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
            {
                e.printStackTrace();
            }
            return false;
        }
        else if (nosian.getHP() == 0) //Hero has fainted in a friendly battle. (DEFEAT)
        {
            WavePlayer SAP;
            try
            {
                SAP = new WavePlayer("Soundtrack\\Defeat.wav", false);
                JOptionPane.showMessageDialog(null, heroName + " and " + ally.getName() + " were Defeated in Battle. \nYou awake elsewhere, far away from the battle.", "Defeat", JOptionPane.INFORMATION_MESSAGE);
                System.out.print("Defeat...");
                //restores ally and hero HP.
                nosian.setHP(nosian.getMaxHP());
                ally.setHP(ally.getMaxHP());
                statusEffect.clearAllStatusEffects();
                JOptionPane.showMessageDialog(null, getTip(), "After-Battle Tip", JOptionPane.INFORMATION_MESSAGE);
                SAP.stop();
                Driver.resumeBGAudio();
            }
            catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
            {
                e.printStackTrace();
            }
            return false;
        }
        
        else //if(enemy.getEnemyHP() == 0 && enemy2.getEnemyHP() == 0) //Enemy Defeated (VICTORY)
        {
            WavePlayer SAP;
            try
            {
                SAP = new WavePlayer("Soundtrack\\Victory.wav", false);
                JOptionPane.showMessageDialog(null, "Congratulations! Nosian and " + ally.getName() + " defeated " + enemyName + "!\n" + enemyName + " had $" + enemyMoneyAward + "!\n\nNosian gained " + enemyXPAward + "XP!\n", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                //restores ally and hero HP.
                nosian.setHP(nosian.getMaxHP());
                ally.setHP(ally.getMaxHP());
                statusEffect.clearAllStatusEffects();
                SAP.stop();
                System.out.print("Victory! ");
                //Rewards Money and XP based on enemyXPAward and enemyMoneyAward.
                nosian.addMoney(enemyMoneyAward);
                nosian.addExperience(enemyXPAward);
                JOptionPane.showMessageDialog(null, getTip(), "After-Battle Tip", JOptionPane.INFORMATION_MESSAGE);
                Driver.resumeBGAudio();
            }
            catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
            {
                e.printStackTrace();
            }
            return true;
        }
    }

    /**
     * Returns the result of a friendly battle and awards the hero XP.
     * 
     * Date created: October 20, 2020
     * 
     * @param stats        - HeroAttributes
     * @param increaseStat - statIncrease
     * @return true if hero&ally win, and false if enemy wins
     */
    public boolean getDuelResults()
    {
        enemy.stopEnemyTheme();
        if (nosian.getHP() == 0)
        {
            WavePlayer SAP;
            try
            {
                SAP = new WavePlayer("Soundtrack\\Defeat.wav", false);
                if(enemyName.equals("Zacharias"))
                {
                    JOptionPane.showMessageDialog(null, "Nosian lost to " + enemyName + "!", "Defeat", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Zacharias twirls his weapon on his finger.\n\"Great effort, Nosian, but it wasn't enough. Better luck next time.\"\n\nThe group gained " + enemyXPAward + "XP!", "Defeat", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Nosian lost to " + enemyName + "!", "Defeat", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Anthiera holds her hand out to you while you are down.\n\"Great effort, Nosian, but better luck next time.\"\n\nThe group gained " + enemyXPAward + "XP!", "Defeat", JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.print("Defeat...");
                nosian.setHP(nosian.getMaxHP());
                statusEffect.clearAllStatusEffects();
                nosian.addExperience(enemyXPAward);
                JOptionPane.showMessageDialog(null, getTip(), "After-Battle Tip", JOptionPane.INFORMATION_MESSAGE);
                SAP.stop();
                Driver.resumeBGAudio();
            }
            catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
            {
                e.printStackTrace();
            }
            return false;
        }
        else //if(enemy.getEnemyHP() = 0)
        {
            WavePlayer SAP;
            try
            {
                SAP = new WavePlayer("Soundtrack\\Victory.wav", false);
                JOptionPane.showMessageDialog(null, "Congratulations! Nosian Defeated " + enemyName + "!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                if(enemyName.equals("Zacharias"))
                {
                    JOptionPane.showMessageDialog(null, "Zacharias smiles and puts his hand on your shoulder.\n\"Great job, Nosian. You're one step closer to becoming stronger.\" \n\nYou and Zacharias gained " + enemyXPAward + "XP!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "\"Great job, Nosian, Anthiera says. \"You're one formidible opponent.\" \n\nYou and Anthiera gained " + enemyXPAward + "XP!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.print("Victory! ");
                nosian.setHP(nosian.getMaxHP());
                statusEffect.clearAllStatusEffects();
                nosian.addExperience(enemyXPAward);
                JOptionPane.showMessageDialog(null, getTip(), "After-Battle Tip", JOptionPane.INFORMATION_MESSAGE);
                SAP.stop();
                Driver.resumeBGAudio();
            }
            catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
            {
                e.printStackTrace();
            }
            return true;
        }
    }
    /**
     * Initializes the tips arraylist.
     * 
     * Date Created: November 19, 2020
     */
    private void initializeTips()
    {
        tips.add("Tip 1: Different Classes benefit from different attributes. \n - For example, Affinity is useful for Mages, and Constitution is useful for Warriors.");
        tips.add("Tip 2: Status Effects such as Silence and Pacify can nullify certain enemies. \n - For example, Silence will cause enemy mages to deal only 0 damage.");
        tips.add("Tip 3: Bosses appear on every 10th area! Be sure to prepare before then. \nVisit the shop, or do Practice Battles!");
        tips.add("Tip 4: Zacharias is a strong ally! \nKeep him safe and he will devestate the enemy while he lives.");
        tips.add("Tip 5: The shop is the best way to increase a hero's statistics. \nBe sure to buy items there!");
        tips.add("Tip 6: Enemies do not have Armor or Resistance! \nDo not worry about a certain skill doing 0 damage!");
        tips.add("Tip 7: Armor reduces damage from physical attacks, and Resistance reduces damage from magical attacks! \nBe sure to balance both stats.");
        tips.add("Tip 8: Stuck on a boss fight? \nGrind XP by doing practice battles or having friendly duels!");
        tips.add("Tip 9: Using Mend will be a lifesaver in certain situations.\nBe sure to keep your Ally alive by mending them.\nThey cannot fight when fainted.");
        tips.add("Tip 10: Ultimate Skills for both the Hero and Allies unlock at level 15.\nThey deal massive amounts of damage.");
        tips.add("Tip 11: Stun and Freeze are match changers! Utilize them by using 'Shield Bash' or 'Ice Spike'!");
        tips.add("Tip 12: Different Allies provide different skills and status effects.\nUtilize tham and learn more about what they can do.");
        tips.add("Tip 13: Status Effects do not stack, but can be replaced or have their length reset! \nBe careful when using other skills that can replace more useful status effects.");
        tips.add("Tip 14: Speed decides who will go first in a battle. \nThose with higher speed will go first.");
        tips.add("Tip 15: Different enemies will have different skills. \nLearn from each skill and plan around their effects!");
        tips.add("Tip 16: Being Pacified or Silenced may reduce your skill damage to 0, as well as mending. \nTry incurring status effects on the enemy when this happens!");
    }

    /**
     * Gets a random tip from the tip arraylist.
     * 
     * Date Created: November 19, 2020
     */
    private String getTip()
    {
        Random rand = new Random();
        int randomNum = rand.nextInt(tips.size());

        return tips.get(randomNum);
    }
}
