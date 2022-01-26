/**
 * --------------------------------------------------------------------------
 * File name: Driver.java
 * Project name: Rise of Nosian
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Creation Date: 10/25/2021
 * Completion Date: --/--/2021
 * @version 1.00
 * --------------------------------------------------------------------------
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Desktop;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import armor.Armor;
import weapon.Weapon;

/**
* The Driver class is responsible for running the game.
*
* @author Michael Ng
*/
public class Driver
{
   private static WavePlayer wp;
   private static String difficulty = "Easy";
   private static int xpMultiplier = 1;

	//TODO Uncomment - fix equipment
   private static Hero nosian;

   private static Ally avise;
   private static Ally Cselina;
   private static Ally eurien;
   private static Ally raven;
   private static Ally melquoia;
   private static Ally silts;

   private static Ally currentAlly = avise;
   private static Ally currentAlly2;

   
   private static Map map;



    /**
    * The main method of the Driver class. Runs the game.
    * 
    * Date Created: 10/25/2021
    * Date Completed: 
    * @param args
    */
   public static void main(String[] args)
   {
      playMenuMusic();
      openMenu();
   }

   /**
    * Plays the main menu's music theme
    * 
    * Date Created: 10/25/2021
    * @param play
    */
   private static void playMenuMusic()
   {
		try { 
			wp = new WavePlayer("Soundtrack\\(MGSV) Big Boss Returns - Main theme (Epic) E3.wav", true);
			wp.play();
		}
		catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			e.printStackTrace();
		}
   }

   /**
    * Plays the music file given within the parameter. 
    * 
    * Date Created: 11/13/2021
    * @param filePath, loop
    */
   public static void playMusicFile(String filePath, boolean loop)
   {
		try
		{
			wp = new WavePlayer(filePath, loop);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			e.printStackTrace();
		}
   }

   /**
	* Plays background music depending on area.
	*
	* Date Created: 11/24/2021
    */
   public static void resumeBGAudio()
   {
		int area = map.getArea();

		try 
		{
			if(area <= 60 && area > 10)
			{
				wp = new WavePlayer("Soundtrack\\Snowdrift.wav", true);
			}
			else 
			{
				wp = new WavePlayer("Soundtrack\\A Breeze from Home.wav", true);
			}
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
   }

   /**
	* Stops the music file being played.
	*
	* Date Created: 11/13/2021
    */
   public static void stopMusic()
   {
	   wp.stop();
   }

   /**
    * Opens the main menu
    * 
    * Date Created: 10/25/2021
    */
   private static void openMenu()
   {
		int choice;
		String[] options = {"New Game", "Load Game", "Settings", "Leave"};

		do
		{
			choice = JOptionPane.showOptionDialog(null, "Welcome to Rise of Nosian!", "Rise of Nosian", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			switch(choice)
			{
			case 0:
				stopMusic();
				startNewGame();
				break;
			case 1:
				loadGame();
				break;
			case 2:
				openSettings();
				break;
			case 3:
				leaveGame();
				break;
			}
		}while(choice != 0 || choice != -1);
   }

   /**
    * Start a New Game
    *
    * Date Created: 10/25/2021
    * Date Completed: 
    */
   private static void startNewGame()
   {
	    nosian = new Hero("Enchanting Prodigy");
	   	avise = new Ally(nosian.getSpecialization(), "Avise");
		map = new Map(nosian, avise);
		currentAlly = avise;


		int choice = JOptionPane.showConfirmDialog(null, "Skip the opening?", "Opening", JOptionPane.YES_NO_OPTION);
		
		if(choice == 1) //no - plays the opening and waits.
		{
			try
			{
				Desktop.getDesktop().open(new File("Cutscenes\\Tales of the Abyss - Opening Cinematic.mp4"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//The thread won't be interrupted at any time, but this try/catch is neccessary for the compiler.
			try {
				//Stops the code from running for 151 seconds.
				Thread.sleep(151000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		playMusicFile("Soundtrack\\Backstory.wav", true);

		String[] text = {">>"};
		//Backstory Section
		/*
		JOptionPane.showOptionDialog(null, "In a distant universe, exists a planet known as Quileria. \nA planet with a lush sea, 1 megacontinent, and 4 other smaller continents.", "Backstory", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Our story begins in the largest continent, akin to a megacontinent. \nIt is named \"Bironia\", by its inhabitants. \nGeographically, Bironia is a land mass that looks like Europe, Asia, and Africa combined into 1.", "Backstory", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\nPeople known seperately as Pyronians and Cyronians inhabit the megacontinent.", "Backstory", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
	
		JOptionPane.showOptionDialog(null, "Pyronians banded together and, after serious effort, legislation, and 2 eras, \nformed the Egalitarian Unified Republic Of Pyronian Estates (EUROPE), or \"Pyronia\" for short.\nThey inhabit the Western and Northern parts of Bironia.", "Backstory", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);	
		JOptionPane.showOptionDialog(null, "Likewise, Cyronians banded together to form the Cyronian Councilate (CC), or \"Cyronia\". \nCyronia inhabits the Southern and Eastern parts of Bironia.", "Backstory", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);

		JOptionPane.showOptionDialog(null, "The meaning of Bironia for each race's language carries significant insight." 
		+ "\nIn the Pyronian Language, \"Bironia\" means \"Unified Earth\"." 
		+ "\nIn the Cyronian Language, \"Bironia\" means \"Split Decision\".", "Backstory", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "These insights are not misleading. \nThey are an accurate representation of how each race views the other. " 
		+ "\nDespite intense effort by Pyronian representatives to achieve continental peace, \nCyronian nobility remain largely against unification or integration of any kind.", "Backstory", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);

		JOptionPane.showOptionDialog(null, "Pyronia, with it's flourishing culture, advancements, and resources, have only made the elite class of Cyronia anxious." 
									+ "\nA prosperous country is constantly being compared to a dark and uninnovative country.", "Backstory", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "At the moment, tensions between the countries are tense.\nAn all-out conflict is brewing...", "Backstory", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);


		stopMusic();
		playMusicFile("Soundtrack\\A Breeze from Home.wav", true);

		//Prologue
		JOptionPane.showOptionDialog(null, "My name is Alexandre Von Nosian.\nI live in a cozy little village known as Xespir.", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "I'm about 10 years old. \nI've got a long life ahead of me.", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "These days, I've been either trying my hand at fencing, or studying up on Magecraft. \nI've yet to decide which to specialize in.", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "At the moment, I'm tired after fencing with my friend, Avise Marino Lloyd, in the School's Duelling Grounds.\nI came to know him in our time at the Children's Magecraft School of Eastern Pyronia.", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		
		JOptionPane.showOptionDialog(null, "\"I can't believe I lost,\" I say. \n\"Next time, I'll be enchanting my fencing sword.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Avise frowns. \"Hey! That's not fair. \nI never got the chance to take Enchantment in Magecraft!\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "I smirk, feeling playful. \"I was going easy on you that time. I'm not used to regular fencing.\"\n" 
										+ "\"In fact, I've won top fencer in the school's enchanted sword fencing tournament 7 of 10 times.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"Yeah, yeah\" Avise says, \n\"At least teach me the basics of Enchanting so I can stand a chance and consider an enchanted duel.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		
		JOptionPane.showOptionDialog(null, "I sit up in a confident pose. \"Nope,\" I say.\n" 
										+ "\"You'd enchant everything in your pursuit to beat me: \nthe top student enchanter in the school.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"So, what?\" Avise inquires. \n\"Can't stand a little competition?\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "That remark hit hard. But he was right.\n\"Yup. The best...erm... should stay the best.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Avise chuckles. \n\"Imagine if Professor Austwitz just heard you now.\" Avise says. \n\"He'd be in for a wild goose chase.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"The regular fencing professor?\" I say. \n\"I can beat him in an enchanted fencing tournament. Easily.\nHe doesn't know enchanted fencing, after all.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"You speak big for someone who just lost in regular fencing,\" Avise notes. \n\"At least acknowledge that you lost.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"I already have, and have gotten over it already.\" I say. \n\"All I need is to be good at enchanting.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Avise sighed and slumped back in his seat. \n\"That ego will bite you hard someday. Mark my words.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		
		JOptionPane.showOptionDialog(null, "There was a mediocre moment of silence. \nWe were staring at the clear blue sky above us.", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"Say, Avise,\" I begin. \"I heard in school that tensions with the Cyronian Councilate are rising.\"" 
										+ "\nI turned to him. \"I wanted to know if you'll be going away to Cyronia or not, to be safe.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "He shakes his head. \"I did hear that Cyronia was welcoming in Cyronian citizens back in.\n\"But, I was born here. I like my life here, and I prefer to keep it that way.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "What a relief! But I worried for his safety.\n\"You don't need to stay here on my account, Avise,\" I remark. \n\"Just do what you and your parents think is best.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"I know,\" Avise replies, \"but my parents also want to stay here.\"\n\"They believe that staying here is best for the family's sake.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"What makes you say that?\" I ask. \n\"Surely, it's better to move there than live under high tensions.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Avise shakes his head again. \n\"My mom told me that, \'always, no matter what, follow and do what you believe is right.\'\nI've come to believe that Cyronia, the one causing the tensions, is in the wrong.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "I smile. \nknowing this gave me confidence that our friendship would only grow brighter.", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		
		JOptionPane.showOptionDialog(null, "Avise stands up and begins to walk towards the exit. I follow suit. \n\"Well, Nosian,\" he begins, \"I've got to go back home.\"\n\"What do you say we walk together?\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"Actually,\" I begin, \"I left my schoolwork and materials in my classroom. \nI've gotta get them so I can study this week's materials.\"", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Avise also realizes. \"Me too! Let me go get my materials as well.\nHey, let's meet back at the front of the school once you're done, alright?\"\nAt this point, we had exited the training grounds.\nAvise was headed in the opposite direction as me.", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"Yeah, sure thing!\" \nI yell, hoping the hallways could echo my voice so he could hear.", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);

		JOptionPane.showOptionDialog(null, "", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		*/
		stopMusic();
		Enemy enemy = new Enemy("Cyronian Warrior", 0);
		Battle bat = new Battle(nosian, currentAlly, enemy);
		System.out.println(bat.getResult());


		JOptionPane.showOptionDialog(null, "", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "", "Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);



		/*
		JOptionPane.showOptionDialog(null, "Avise looks at you with fear.\n\"Our village is gone,\" he says.\n\"What are we to do now?\"", "After-Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "I was as panicked and lost as Avise. \nThe home we lived in for our lives... all the people we knew...\nAll gone.", "After-Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"We should run!\" I said, \n\"If we stay here any longer, we'll end up like them!\"", "After-Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Avise, out of fear, nods.\n\"Okay! Let's go then! Before anyone sees us!\"", "After-Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Avise and I run into the woods, further than any of the townpeople would permit.\nThe townspeople would say dangerous animals would eat anyone who went in.", "After-Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Yet, no animal jumped out or pounced us. \nOnce we reached a clearing, we slowed down to catch our breath.", "After-Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Avise had tears in his eyes. Whether it was from fear or sadness, I couldn't tell.\nI sympathized with his pain.", "After-Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"Hey Avise, it's going to be alright. We can make it out of this together.\"", "After-Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Avise, nodded weakly. \"Ever since I heard the war started, I've been worried that those Cryonians would attack us.\"", "After-Prologue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "He sniffs, with a tear rolling down his cheek. \n\"I've been ready for this. I...I just need a minute.\"", "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "\"Of course, Avise,\" I say. \n\"Take as long as you need. We've gotta keep going forward in the meantime.\"", "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		*/
		playMusicFile("Soundtrack\\Side Battle (Introduction).wav", true);
		JOptionPane.showOptionDialog(null, "     ~ 𝐑𝐢𝐬𝐞 𝐨𝐟 𝐍𝐨𝐬𝐢𝐚𝐧 ~" + 
								"\n\t   Writer:      Michael Ng" +
								"\n\t   Coder:      Michael Ng" +
								"\n\t   Director:  Michael Ng", "Prologue End", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Coded in Java using Microsoft Visual Studio Code.\nStarted in 10/25/2021 and completed on ...", "Prologue End", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "All code is original, except for WavePlayer.\nThis uses code from https://www.geeksforgeeks.org/play-audio-file-using-java/.", "Prologue End", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "Special Thanks:\n- My brother, for being a persistent bug tester.\n- You, the player, for trying out this game!", "Prologue End", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "This game draws inspiration from:\n" 
											+ " - Fire Emblem Series\n"
											+ " - Tales of Arise\n"
											+ " - Sonny 1 & 2\n"
											+ " - Previous Projects\n"
											+ "(Dungeon of Dystopia/Sigian Conflict)", "Prologue End", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		JOptionPane.showOptionDialog(null, "~ 𝐑𝐢𝐬𝐞 𝐨𝐟 𝐍𝐨𝐬𝐢𝐚𝐧 ~\n𝗖𝗵𝗮𝗽𝘁𝗲𝗿 𝟭 - 𝗩𝗮𝗹𝗼𝗿", "Prologue End", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		
		//JOptionPane.showOptionDialog(null, "", "Prologue End", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		//JOptionPane.showOptionDialog(null, "", "Prologue End", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, text, text[0]);
		//map.initiateBattleField(5, 5, 1);
		wp.stop();
		
   }


   /**
	 * The loadGame method retrieves game information from an old save file.
	 * Returns true if a file was loaded successfully.
	 * Returns false otherwise.
	 *
	 * <hr>
	 * Date created: 10/27/2021
	 *
	 * <hr>
	 * @return boolean
	 */
	private static void loadGame() 
	{
		//The 2 filepaths to the save files.
		File path = new File("src\\SaveFiles\\File1");
		File path2 = new File("src\\SaveFiles\\File2");
		Scanner inputFile = null;
		Scanner inputFile2 = null;

		String filesInformation = "";
		
		filesInformation = showLoadPreview(inputFile, inputFile2, path, path2);

		//filesInfromation can return null if the saveFile was not found and the user doesn't choose a file.
		if(filesInformation == null)
		{
			//avoids a bug and sends the user back to the main menu.
			return;
		}
		
		//Reset the scanners back to line 1 of the savefile before loading.
		try
		{
			inputFile = new Scanner(path);
			inputFile2 = new Scanner(path2);
		}
		catch (FileNotFoundException e1) //Unlikely to happen because it has already been validated. Still, it's put here just in case.
		{
			JOptionPane.showMessageDialog (null, "A save file was not found.\nPlease select a text file in the following menu.", "Save File not Found.", JOptionPane.ERROR_MESSAGE);
			JFileChooser fileChooser = new JFileChooser("src\\SaveFiles");
			int pick = fileChooser.showOpenDialog (null);
			if(pick == JFileChooser.APPROVE_OPTION) 
			{
				File file = fileChooser.getSelectedFile ( );
				try
				{
					Scanner validate = new Scanner(file);
					validate.close();
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "No File was Selected. Returning to Main Menu.");
				return;
			}
			
		}
		//The user chooses which file to load from.
		String[] options = {"File 1", "File 2"};
		
		//Asks the user which file they would like to load from. Displays the hero summaries of both files. 
		int pick = JOptionPane.showOptionDialog (null, "Which file would you like to load from?\n\n" + filesInformation, "Save Game", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if(pick == 0) //File 1
		{	
			//Loads data from File 1.
			if(inputFile.hasNext ( )) 
			{
				String str = inputFile.nextLine();
				//Line 1 has 1 number, which is the stagesCompleted.
				//1
				//StagesCompleted
				String[] values = str.split ("\\|");
				
				//Stages Completed: values[1];
				int stagesCompleted = Integer.parseInt(values[0]);
				map = new Map(nosian, currentAlly, currentAlly2, stagesCompleted);
				
				//Then, line 2 would have nosian's stats.
				//150|90|3|90|121|500|15|50|50|45|30|10
				//maxHP|maxSP|level|exp|expLevelUp|channelRating|carryWeight|constituion|affinity|armor|resistance|speed
				//get the next line. These are nosian's stats.
				str = inputFile.nextLine ( );
				values = str.split ("\\|");
				int maxHP = Integer.parseInt(values[0]);
				int maxSP = Integer.parseInt(values[1]);
				int level = Integer.parseInt(values[2]);
				int exp = Integer.parseInt(values[3]);
				int expLevelUp = Integer.parseInt(values[4]);
				int channelRating = Integer.parseInt(values[5]);
				int carryWeight = Integer.parseInt(values[6]);
				int constitution = Integer.parseInt(values[7]);
				int affinity = Integer.parseInt(values[8]);
				int armor = Integer.parseInt(values[9]);
				int resistance = Integer.parseInt(values[10]);
				int speed = Integer.parseInt(values[11]);
				int money = Integer.parseInt(values[12]);

				nosian = new Hero(maxHP, maxSP, level, exp, expLevelUp, channelRating, carryWeight, constitution, affinity, armor, resistance, speed, money);

				//get line 3. These are nosian's equipment, with weapon and equipped armor.
				str = inputFile.nextLine();
				values = str.split ("\\|");
				String primary = values[0];
				String head = values[1];
				String torso = values[2];
				String hands = values[3];
				String leg = values[4];
				String foot = values[5];
				String accessory = values[6];
				String accessory2 = values[7];
				//String crystal = values[8];
				//String crystal2 = values[9];
				//String crystal3 = values[10];
				

				//get line 4. This is nosian's unequipped equipment.
				str = inputFile.nextLine();
				values = str.split("\\|");
				ArrayList <String> valuesArrayList = new ArrayList<>();

				//Add string values from the values string array into the arraylist.
				for(int i = 0; i < values.length-1; i++)
				{
					valuesArrayList.add(values[i]);
				}

				
				//Import all the weapons the hero owned in the save file and their equipped weapons.
				nosian.getEquipment ( ).importEquipment(valuesArrayList, primary, head, torso, hands, leg, foot, accessory, accessory2);//, crystal, crystal2, crystal3); - implement later


				
				
				//TODO Ally stats loading
				//get the next line. These are stats of the next ally.
				str = inputFile.nextLine ( );
				values = str.split ("\\|");
			}
			else //If there is no data. Asks the user to start a new game.
			{
				JOptionPane.showMessageDialog (null, "Sorry, this save file has no data associated with it.\nPlease begin a new game.", "No Save Data", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(pick == 1) //File 2
		{
			//Loads data from File 2
			if(inputFile2.hasNext ( )) 
			{
				String str = inputFile.nextLine();
				//Line 1 has 1 number, which is the stagesCompleted.
				//1
				//StagesCompleted
				String[] values = str.split ("\\|");
				
				//Stages Completed: values[1];
				int stagesCompleted = Integer.parseInt(values[0]);
				
				//Then, line 2 would have nosian's stats.
				//150|90|3|90|121|500|15|50|50|45|30|10
				//maxHP|maxSP|level|exp|expLevelUp|channelRating|carryWeight|constituion|affinity|armor|resistance|speed
				//get the next line. These are nosian's stats.
				str = inputFile.nextLine ( );
				values = str.split ("\\|");
				int maxHP = Integer.parseInt(values[0]);
				int maxSP = Integer.parseInt(values[1]);
				int level = Integer.parseInt(values[2]);
				int exp = Integer.parseInt(values[3]);
				int expLevelUp = Integer.parseInt(values[4]);
				int channelRating = Integer.parseInt(values[5]);
				int carryWeight = Integer.parseInt(values[6]);
				int constitution = Integer.parseInt(values[7]);
				int affinity = Integer.parseInt(values[8]);
				int armor = Integer.parseInt(values[9]);
				int resistance = Integer.parseInt(values[10]);
				int speed = Integer.parseInt(values[11]);
				int money = Integer.parseInt(values[12]);

				nosian = new Hero(maxHP, maxSP, level, exp, expLevelUp, channelRating, carryWeight, constitution, affinity, armor, resistance, speed, money);

				//get line 3. These are nosian's equipment, with weapon and equipped armor.
				str = inputFile.nextLine();
				values = str.split ("\\|");
				String primary = values[0];
				String head = values[1];
				String torso = values[2];
				String hands = values[3];
				String leg = values[4];
				String foot = values[5];
				String accessory = values[6];
				String accessory2 = values[7];
				//String crystal = values[8];
				//String crystal2 = values[9];
				//String crystal3 = values[10];
				

				//get line 4. This is nosian's unequipped equipment.
				str = inputFile.nextLine();
				values = str.split("\\|");
				ArrayList <String> valuesArrayList = new ArrayList<>();

				//Add string values from the values string array into the arraylist.
				for(int i = 0; i < values.length-1; i++)
				{
					valuesArrayList.add(values[i]);
				}

				
				//Import all the weapons the hero owned in the save file and their equipped weapons.
				nosian.getEquipment ( ).importEquipment(valuesArrayList, primary, head, torso, hands, leg, foot, accessory, accessory2);//, crystal, crystal2, crystal3); - implement later









				/*
				map = new Map(stagesCompleted);
				
            
            	//Next line has hero stats.
				nosian = new HeroAttributes(maxHP, maxSP, level, exp, expLevelUp, channelWeight, channelRating, channelLevel, carryWeight);

				
				
				//get the next line. These are equipped weapons.
				str = inputFile2.nextLine ( );
				values = str.split ("\\|");
				String primary = values[0];
				String secondary = values[1];
				
				
				//get the next line. These are equipped armors.
				str = inputFile2.nextLine ( );
				values = str.split ("\\|");
				String head = values[0];
				String torso = values[1];
				String hands = values[2];
				String leg = values[3];
				String foot = values[4];
				String accessory = values[5];
				String accessory2 = values[6];
				
				
				//get the next line. These are all owned weapons.
				str = inputFile2.nextLine ( );
				values = str.split ("\\|");
				//Import all the weapons the hero owned in the save file and their equipped weapons.
				nosian.getEquipment ( ).importWeapons (values, primary, secondary);
				
				
				
				//get the next line. These are all owned armors.
				str = inputFile2.nextLine ( );
				values = str.split ("\\|");
				//Imports all the armors the hero owned in the save file and their equipped armors.
				nosian.getEquipment ( ).importArmors (values, head, torso, hands, leg, foot, accessory, accessory2);
				*/


				//TODO uncommment after importing current allies
				//map = new Map(stagesCompleted);
			}
			else //If there is no data in the save file that the player chose.
			{
				JOptionPane.showMessageDialog (null, "Sorry, this save file has no data associated with it.\nPlease begin a new game.", "No Save Data", JOptionPane.ERROR_MESSAGE);
			}
		}
   }

	/**
	 * Saves the game into a text file located in the SaveFiles folder.
	 *
	 * <hr>
	 * Date created: Apr 4, 2021
	 *
	 * <hr>
	 */
	private static void saveGame() 
	{
		boolean notSaved = true;
		while(notSaved) 
		{
			int pick = JOptionPane.showConfirmDialog (null, "Would you like to save your game?\n" + nosian.toString ( ) + "\n", "Save Game", JOptionPane.YES_NO_OPTION);
			
			String filepath = new String("src\\SaveFiles\\File1");
			String filepath2 = new String("src\\SaveFiles\\File2");
			String filesInformation = "";
			
			if(pick == 0) //yes, save game.
			{
				//This section previews both save files.
				PrintWriter writer = null;
				
				//filepaths to the save files.
				File path = new File(filepath);
				File path2 = new File(filepath2);
				Scanner inputFile = null;
				Scanner inputFile2 = null;

				
				int fileNum = 1;
				try //validate both files.
				{
					inputFile = new Scanner(path);
					fileNum++;
					inputFile2 = new Scanner(path2);
				}
				catch (FileNotFoundException e1) 
				{
					JOptionPane.showMessageDialog (null, "Save file " + fileNum + " was not found.\nPlease select a text file in the following menu.", "Save File not Found.", JOptionPane.ERROR_MESSAGE);
					JFileChooser fileChooser = new JFileChooser("src\\SaveFiles");
					pick = fileChooser.showOpenDialog (null);
					if(pick == JFileChooser.APPROVE_OPTION) 
					{
						File file = fileChooser.getSelectedFile ( );
						try
						{
							Scanner validate = new Scanner(file);
							validate.close();
						}
						catch (FileNotFoundException e)
						{
							e.printStackTrace();
						}
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "No File was Selected. Returning to Main Menu.");
						return;
					}
				}
				pick = 0;
				
				filesInformation = showLoadPreview(inputFile, inputFile2, path, path2);
				
				
				//The user chooses which file to save into.
				String[] options = {"File 1", "File 2"};
				
				pick = JOptionPane.showOptionDialog (null, "Which file would you like to save to?\n\n" + filesInformation, "Save Game", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				String savedPath = "";
				if(pick == 0) //File 1
				{
					savedPath = filepath;
				}
				else if(pick == 1) //File 2
				{
					savedPath = filepath2;
				}
				
				if(!savedPath.isBlank ( )) 
				{
					writeToFile(writer, savedPath);

					notSaved = false;
					JOptionPane.showMessageDialog (null, "Your game has successfully been saved!", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else //If the user chose X in the previous choice. (Which file would you like to save to?)
			{
				pick = JOptionPane.showConfirmDialog (null, "Are you sure you do not want to save your game?\nAll progress will be lost when the game is closed.", "Save Game", JOptionPane.YES_NO_OPTION);
				if(pick == 0) //Yes
				{
					notSaved = false;
					break;
				}
			}
		}
	}


   /**
    * Opens the game's settings.
    *
    * Date Created: 10/25/2021
    */
   private static void openSettings()
   {
		int choice;
    	String[] options = {"Change Difficulty", "Change XP Multipler", "Leave Settings"};
		do
		{
			choice = JOptionPane.showOptionDialog(null, "Welcome to Settings!\n\nDifficulty: " + difficulty + "\nXP Multiplier: " + xpMultiplier, "Settings", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);

			if(choice == 0)
			{
				String descText = "";
				switch(difficulty)
				{
					case "Easy":
						difficulty = "Medium";
						descText = "\nEnemies have normal attributes.";
						break;
					case "Medium":
						difficulty = "Hard";
						descText = "\nEnemies have attributes enhanced by 1.5.";
						break;
					case "Hard":
						difficulty = "Easy";
						descText = "\nEnemies have halved attributes.";
						break;
				}
				JOptionPane.showMessageDialog(null, "Difficulty has been set to " + difficulty + descText, "Difficulty Altered", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(choice == 1)
			{
				switch(xpMultiplier)
				{
					case 1:
						xpMultiplier = 2;
						break;
					case 2:
						xpMultiplier = 3;
						break;
					case 3:
						xpMultiplier = 1;
						break;
				}
				JOptionPane.showMessageDialog(null, "XP Multiper has been set to " + xpMultiplier + ".\nYour will receive x" + xpMultiplier + " the XP from battles.", "XP Multipler Altered", JOptionPane.INFORMATION_MESSAGE);
			}
		}while(choice != -1 && choice != 2);
   }

   /**
    * Leave the game if the player requests.
    * 
    * Date Created: 10/25/2021
    */
   private static void leaveGame()
   {
      int leaveChoice = 0;
      String[] leaveOptions = {"Yes", "No"};
      leaveChoice = JOptionPane.showOptionDialog(null, "Are you sure you want to leave?", "Leave Game", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, leaveOptions, leaveOptions[0]);
      if(leaveChoice == 0)
      {
         JOptionPane.showMessageDialog(null, "Thank you for playing Rise of Nosian!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
		 System.exit(0);
      }
   }

   /**
    * Return the game's current difficulty.
    * 
    * @return difficulty
    */
   public static String getDifficulty()
   {
      return difficulty;
   }

   /**
	* returns the XP Multiplier setting.
	*
	* @return
    */
   public static int getXPMultiplier()
   {
	   return xpMultiplier;
   }

   /**
	* returns a string containing a preview of save files. 
    *
	*
	* @param inputFile
	* @param inputFile2
	* @param path
	* @param path2
	* @return filesInformation
    */
   private static String showLoadPreview(Scanner inputFile, Scanner inputFile2, File path, File path2)
   {
		String filesInformation = "";
		int fileNum = 1;
		try //Validate the file paths. Note how cleverly fileNum is used.
		{
			inputFile = new Scanner(path);
			fileNum++;
			inputFile2 = new Scanner(path2);
		}
		catch (FileNotFoundException e1) 
		{
			JOptionPane.showMessageDialog (null, "Save file " + fileNum + " was not found.\nPlease select a text file in the following menu.", "Save File not Found.", JOptionPane.ERROR_MESSAGE);
			JFileChooser fileChooser = new JFileChooser("src\\SaveFiles");
			int pick = fileChooser.showOpenDialog (null);
			if(pick == JFileChooser.APPROVE_OPTION) 
			{
				File file = fileChooser.getSelectedFile ( );
				try
				{
					Scanner validate = new Scanner(file);
					validate.close();
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "No File was Selected. Returning to Main Menu.");
				return null;
			}
		}
		
		//Gets the summary of the hero in File 1.
		if(inputFile.hasNext()) 
		{
			String str = inputFile.nextLine();
			String[] values = str.split ("\\|");

			//Name
			filesInformation += "(File 1) " + values[0];
			filesInformation += "\n--------------------\n";
			//Stages Completed
			filesInformation += "Stages Completed: " + values[1] + "\n";

			//Gets line 2. This has Nosian's stats.
			//150|90|3|90|121|500|15|50|50|45|30|10
			//maxHP|maxSP|level|exp|expLevelUp|channelRating|carryWeight|constituion|affinity|armor|resistance|speed
			str = inputFile.nextLine();
			values = str.split ("\\|");
			//HP
			filesInformation += "HP: " + values[0] + "HP\n";
			//SP
			filesInformation += "SP: " + values[1] + "SP\n";
			//level
			filesInformation += "Level: " + values[2] + "\n";
			//exp / expLevelUp
			filesInformation += "Experience: " + values[3] + "XP / " + values[4] + "\n";
			//channelRating
			filesInformation += "Channel Rating: " + values[5] + "CR\n";
			//constitution
			filesInformation += "Constitution: " + values[7] + "\n";
			//affinity
			filesInformation += "Affinity: " + values[8] + "\n";
			//armor
			filesInformation += "Armor: " + values[9] + "\n";
			//resistance
			filesInformation += "Resistance: " + values[10] + "\n";
			//speed
			filesInformation += "Speed: " + values[11] + "\n";
		}
		else //If there is nothing in File 1.
		{
			filesInformation += "File 1 is Empty.\n\n";
		}

		//Gets the summary of the hero in File 2.
		if(inputFile2.hasNext()) 
		{				
			String str = inputFile2.nextLine();
			//Line 1 has 1 number, which is the stagesCompleted.
			String[] values = str.split ("\\|");

			//Name
			filesInformation +=  "(File 2)";
			filesInformation += "\n--------------------\n";
			//Stages Completed
			filesInformation += "Stages Completed: " + values[0] + "\n";

			//Gets line 2. This has Nosian's stats.
			//150|90|3|90|121|500|15|50|50|45|30|10
			//maxHP|maxSP|level|exp|expLevelUp|channelRating|carryWeight|constituion|affinity|armor|resistance|speed
			str = inputFile.nextLine();
			values = str.split ("\\|");
			//HP
			filesInformation += "HP: " + values[0] + "HP\n";
			//SP
			filesInformation += "SP: " + values[1] + "SP\n";
			//level
			filesInformation += "Level: " + values[2] + "\n";
			//exp / expLevelUp
			filesInformation += "Experience: " + values[3] + "XP / " + values[4] + "\n";
			//channelRating
			filesInformation += "Channel Rating: " + values[5] + "CR\n";
			//constitution
			filesInformation += "Constitution: " + values[7] + "\n";
			//affinity
			filesInformation += "Affinity: " + values[8] + "\n";
			//armor
			filesInformation += "Armor: " + values[9] + "\n";
			//resistance
			filesInformation += "Resistance: " + values[10] + "\n";
			//speed
			filesInformation += "Speed: " + values[11] + "\n";

		}
		else //If there is nothing in File 2.
		{
			filesInformation += "File 2 is Empty.\n\n";
		}
	return filesInformation;
    }

	/**
	 * Writes data to the specified save file.
	 * 
	 * @param writer
	 * @param savedPath
	 */
	private static void writeToFile(PrintWriter writer, String savedPath)
	{
		
		try
		{
			writer = new PrintWriter(savedPath);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		//Line 1: Stages Completed
		writer.println(map.getArea());

		//Line 2: Nosian's basic stats.
		writer.println(nosian.getHP() + "|" + nosian.getHP() + "|" + nosian.getSP() + "|" + nosian.getMaxSP()
		+ "|" + nosian.getExp() + "|" + nosian.getExpLevelUp() + "|" + nosian.getChannelRating()
		+ "|" + nosian.getCarryWeight() + "|" + nosian.getConstitution() + "|" + nosian.getAffinity() 
		+ "|" + nosian.getArmor() + "|" + nosian.getResistance() + "|" + nosian.getSpeed());

		//Create a new arraylist equipment to store weapon and armor names.
		ArrayList<String> equipment = new ArrayList<>();
		ArrayList<Weapon> weapons = nosian.getEquipment ( ).getOwnedWeapons ( );
		for(Weapon weapon: weapons) 
		{
			equipment.add(weapon.getWeaponName());
		}
		ArrayList<Armor> armors = nosian.getEquipment ( ).getOwnedArmors ( );
		for(Armor armor: armors) 
		{
			equipment.add(armor.getArmorName());
		}

		//Line 3: Nosian's equipped equipment
		writer.println (nosian.getEquipment().getPrimaryWeapon ( ).getWeaponName ( ) + "|" + nosian.getEquipment ( ).getHeadArmor ( ).getArmorName ( ) + "|" + nosian.getEquipment ( ).getTorsoArmor ( ).getArmorName ( ) + 
		"|" + nosian.getEquipment ( ).getHandArmor ( ).getArmorName ( ) + "|" + nosian.getEquipment ( ).getLegArmor ( ).getArmorName ( ) + 
		"|" + nosian.getEquipment ( ).getFootArmor ( ).getArmorName ( ) + "|" + nosian.getEquipment ( ).getAccessory ( ).getArmorName ( ) + 
		"|" + nosian.getEquipment ( ).getAccessory2 ( ).getArmorName ( ));

		//Line 4: Nosian's unequipped Equipment
		for(String equipmentName: equipment)
		{
			writer.println(equipmentName + "|");
		}

		//Line 5-9: Stats for allies.

		
		writer.close();
		//TODO alter writeToFile to suit saving style.
		/**
		 * String str = inputFile.nextLine();
				//Line 1 has 1 number, which is the stagesCompleted.
				//1
				//StagesCompleted
				String[] values = str.split ("\\|");
				
				//Stages Completed: values[1];
				int stagesCompleted = Integer.parseInt(values[0]);
				map = new Map(stagesCompleted);
				
				//Then, line 2 would have nosian's stats.
				//150|90|3|90|121|500|15|50|50|45|30|10
				//maxHP|maxSP|level|exp|expLevelUp|channelRating|carryWeight|constituion|affinity|armor|resistance|speed
				//get the next line. These are nosian's stats.
				str = inputFile.nextLine ( );
				values = str.split ("\\|");
				int maxHP = Integer.parseInt(values[0]);
				int maxSP = Integer.parseInt(values[1]);
				int level = Integer.parseInt(values[2]);
				int exp = Integer.parseInt(values[3]);
				int expLevelUp = Integer.parseInt(values[4]);
				int channelRating = Integer.parseInt(values[5]);
				int carryWeight = Integer.parseInt(values[6]);
				int constitution = Integer.parseInt(values[7]);
				int affinity = Integer.parseInt(values[8]);
				int armor = Integer.parseInt(values[9]);
				int resistance = Integer.parseInt(values[10]);
				int speed = Integer.parseInt(values[11]);
				int money = Integer.parseInt(values[12]);

				nosian = new Hero(maxHP, maxSP, level, exp, expLevelUp, channelRating, carryWeight, constitution, affinity, armor, resistance, speed, money);

				//get line 3. These are nosian's equipped equipment, with weapon and armor.
				str = inputFile.nextLine();
				values = str.split ("\\|");
				String primary = values[0];
				String head = values[1];
				String torso = values[2];
				String hands = values[3];
				String leg = values[4];
				String foot = values[5];
				String accessory = values[6];
				String accessory2 = values[7];
				//String crystal = values[8];
				//String crystal2 = values[9];
				//String crystal3 = values[10];
				

				//get line 4. This is nosian's unequipped equipment.
				str = inputFile.nextLine();
				values = str.split("\\|");
				ArrayList <String> valuesArrayList = new ArrayList<>();

				//Add string values from the values string array into the arraylist.
				for(int i = 0; i < values.length-1; i++)
				{
					valuesArrayList.add(values[i]);
				}

				
				//Import all the weapons the hero owned in the save file and their equipped weapons.
				nosian.getEquipment ( ).importEquipment(valuesArrayList, primary, head, torso, hands, leg, foot, accessory, accessory2);//, crystal, crystal2, crystal3); - implement later


				
				
				//TODO Ally stats loading
				//get the next line. These are stats of the next ally.
				str = inputFile.nextLine ( );
				values = str.split ("\\|");
		 */
	}
}