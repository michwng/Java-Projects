This project was created in the Visual Studio Code IDE.
This project was started on October 8th, 2020 and completed on November 17th, 2020. Updates have been provided beginning in September 1, 2021 through September 14th, 2021.
This project was made as part of my Introduction to Computer Science I class at ETSU in the Fall 2020 semester.

This game strongly draws inspiration from:
- Sonny & Sonny 2 (Flash Games)
- Fire Emblem series


A person may need 15-30 minutes to complete the storyline of the game.
This project has been heavily tested.

This project has Audio! Be sure to turn down your volume to the right amount.
Audio has been compressed so the size of all music files combined are 152MB.

All classes except SimpleAudioPlayer are created by Michael Ng.
SimpleAudioPlayer was originally created by geeksforgeeks.org.
I have modified SimpleAudioPlayer to fit the project's needs.
Website link to the original: https://www.geeksforgeeks.org/play-audio-file-using-java/

Game Name: The Dungeon of Dystopia
Genre: Dungeon-Crawler RPG
Team Members: Michael Ng

Class Description
Statistics.java
- Stores the hero's attributes, as well as their name and money.
- Present throughout all other classes, except Enemy.java and Ally.java.
- Method calls such as getHP() or setMaxHP() can be called in classes that have a reference to Statistics.
- Has several accessor and mutator methods used to access and change attributes.

StatIncrease.java
- Keeps track of the hero's level and XP.
- Has references to a statistics object and 2 ally objects. 
- Used to calculate level ups for all classes through probability.
- Has access to each object's mutator methods.

Skills.java
- Keeps track of the Hero's skills, skillDamage, and skill status effects.
- Uses the Statistics object to determine the amount of damage each move deals.
- Uses the StatIncrease object to determine heroLevel. 
- New skills are unlocked depending on heroLevel!

Driver.java
- The main class used for input.
- Has the ability to use the SimpleAudioPlayer object to play background music.
- Has static methods that can be called in other classes to end/resume audio playback.
- Asks for the Hero's Name and Specialization.
- Initializes objects from all classes (except Enemy and StatusEffect classes).
- Allows the user to pick from a variety of actions, such as Friendly Duel, Open Shop, etc.
- Has a method that allows the user to switch allies (if possible). 

Battle.java
- A class that is separated from the Driver class due to its great size. Also allows user input.
- Can play victory or defeat music.
- Has 3 different constructors. Constructor 1 creates a random enemy and begins battle, 
- Constructor 2 begins battle with a specified enemy, and Constructor 3 allows friendly battles with allies.
- Has 3 main phases - Hero, Enemy, and Ally. 
- Creates a StatusEffect object, which can change and alter each combatant's attributes depending on status effect.

Enemy.java
- A class similar to Statistics and Skills, combining both together as an Enemy class.
- Keeps track of enemy attributes as well as their skills, skill damage, and skill status effects.
- Unlike Statistics, Enemies do not have Armor or Resistance.
- Enemy attributes also include their rewards at the end of battle.
- Enemy attributes are pre-defined and can increase based on area.
- Has 3 constructors. Constructor 1 randomly chooses a pre-defined enemy.
- Constructor 2 uses a specified enemy, and Constructor 3 constructs an ally as an enemy.
- Enemies can have their own themes, depending on the chosen enemy or constructor.
- Has several accessor and mutator methods used to access and change attributes.

Map.java
- Keeps track of area and location.
- Plays soundtrack 13 (Hope) before fighting the boss on area 60.
- Has references to Statistics, Skills, StatIncrease, and Ally objects.
- Has an ArrayList that holds events that can be randomly chosen.
- Using the method getScenario() allows the user to undergo a scenario. Iterates area by 1 afterwards.
- Can create enemies and battles if area is divisible by 10 (boss battles) or if the scenario is "Enemy Encounter".
- The reference to Ally changes when the user switches allies in Driver.java.

Ally.java
- Like the Enemy class, keeps track of ally attributes as well as skills, skill damage, and skill status effects.
- Takes in the Hero's class and allyName in the constructor. Allies shift their classes depending on the Hero's class.
- There are 2 allies in-game, one is unlocked later in-game. Until the ally is unlocked, some references for the second ally remain null.
- Allies start out very strong! However, they begin to slack late-game, as the Hero buys from the Shop.
- Has several accessor and mutator methods used to access and change attributes.

SimpleAudioPlayer (Credit to geeksforgeeks)
- Class used to play audio files.
- Constructor plays audio file upon creation. 
- Has a method to stop playback. 
- Stopping the playback ends the audio file. SimpleAudioPlayer must be reinitialized in order to replay.
- Music Files are roughly 152MB in total.
- Music Files are from copyrighted games! 

StatusEffect.java
- Keeps track of all combatants' status effects during battle. A StatusEffect object is created only in the Battle class.
- Skill Status Effects trigger the StatusEffect object, and probability decides whether the victim recieves the status effect.
- There are 15 status effects in total, each with their own unique effect.
- Status Effects cannot stack! Instead, they are replaced by the new status effect if it was successful.
- Status Effects take place at the beginning of Hero Phase, Enemy Phase, and Ally Phase.
- Has several accessor and mutator methods in case a special condition in the Battle class arises (such as, an Ally or Hero faints).


Music Credits go to these following video games:
- Fire Emblem Echoes: Shadows of Valentia
	- (Serenity [Intro Sequence], The Heritors of Arcadia (Instrumental) [Ending], Fanfare(Recruitment) [Level Up], Echoes [Title Screen], With Pride in Your Heart [Aftermath])

- Fire Emblem: Three Houses 
	- (The Apex of the World (Rain) [BattleLeaderoftheLegion], A World for Humanity [Hope], Broken Weapon [Defeat])

- Sonny 2
	- (Internal Conflict [RegularBattle1], Outnumbered [RegularBattle2], Call to Arms [RegularBattle3])

- Fire Emblem: Path of Radiance
	- (The First Fight)

- Fire Emblem: Awakening
	- (Destiny [Next Step])

- Pokemon Black and White 2
	- (Battle! (Rival) [BattleZacharias])

- Pokemon Omega Ruby & Alpha Sapphire (ORAS)
	- (Battle! (Lorekeeper Zinnia) [BattleAnthiera])

- Pokemon Let's Go! Pikachu/ Let's Go! Eevee
	- (The Final Road)

- Pokemon Sword and Shield
	- (Battle! (Gym Leader Rematch) [BattleBoss])

- Pokemon Mystery Dungeon: Gates to Infinity
	- (Dungeon Cleared! [Victory])

- Deltarune
	- (THE WORLD REVOLVING [BattleSecretEnemy])


** UPDATE LOG **
Version 1: Original Project with base game. Submitted 11/23/2020

Version 1.5 (Anniversary Update): 
- Added Save/Load mechanic. 
- Added 5 new strong bosses in the post-game.
- Command Console has been changed.
- Battle Menu has been improved.
- When battling, proper nouns are used for status effects now. (For example, instead of Ally lacerated Enemy, a possibility is "Zacharias lacerated Goblin")
- Added Credits and Music section to Title Menu.
- Various code improvements and rearrangements.
- Some bosses have been rebalanced.
- Financial Support encounter is much more likely to trigger a battle (money requirement changed from $10 to $200). Otherwise, it is more rewarding.
- BalanceArea() has been adjusted. Enemies in higher floors will be stronger.
- Bleeding now shows first in the Hero Phase Results, as opposed to after.


- Code has been tested heavily after making these changes. Bugs encountered and introduced in this round of testing have been resolved.
	- Bug fixed where bleeding damage is applied twice in the same turn it was procced. For example, when enemy lacerates ally for 50HP per turn, the ally will lose 100HP that turn. (Bleed damage is applied once per turn now.)
	- Similarly, the Poison status effect had the same bug as Bleed, and this issue has also been fixed.
	- Fixed display bug where, when enemy bleeds, output shows Ally's Max HP instead of Enemy's Max HP.
	- Description of Leader of the Legion's 'Decimate' skill now accurately reflects the Sunder status effect (before, said armor is reduced by 20 for 3 turns. Now, armor is reduced to 0 for 3 turns).
	
Version 1.5 was Submitted 10/05/2021


Thank you for reading!
