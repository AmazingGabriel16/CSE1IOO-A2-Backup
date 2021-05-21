import java.util.*;
import java.io.*;

public class Adventure
{
	//Do not edit attributes
	private Hero hero;
	private Scanner keyboard;
	
	public static void main(String [] args)
	{
		Adventure adventure = new Adventure();
		System.out.println("*** Name StudentNumber ***");
		System.out.println("***   Gabriel Tobing   ***");
		System.out.println("***      20722072      ***");
		System.out.println("***  Adventure Part 2  ***");
		adventure.run();
	}
	
	public Adventure() 
	{
		keyboard = new Scanner(System.in);
		
		hero = null;
	}
	
	public void run()
	{	
		int choice;
		while(true)
		{
			try
			{
				displayMenu();
				choice = readInt(0,7);
				process(choice);
			}
			catch(TooManyAttemptsException e)
			{
				System.out.println(e.getMessage());
				return;
			}
			// To prevent multiple 0's not triggering break
			
			if (choice == 0)
			{
				// Breaks out of the loop and stops program
				System.out.println("Exiting - Goodbye!");
				return;
			}
		}
	}
	
	private void displayMenu()
	{
		System.out.println();
		System.out.println("~~~ ADVENTURE MENU ~~~");
		System.out.println("1. Create Hero");
		System.out.println("2. Display Hero");
		System.out.println("3. Heal Hero");
		System.out.println("4. Damage Hero");
		System.out.println("5. Give Hero Experience");
		System.out.println("6. Save Hero to File");
		System.out.println("7. Load Hero from File");
		System.out.println("0. Quit");
		System.out.println();
	}
	
	private void process(int choice)
	{
		if(choice == 1)
		{
			createHero();
		}
		else if(choice == 2)
		{
			displayHero();
		}
		else if(choice == 3)
		{
			healHero();
		}
		else if(choice == 4)
		{
			damageHero();
		}
		else if(choice == 5)
		{
			giveHeroExperience();
		}
		else if(choice == 6)
		{
			saveHero();
		}
		else if (choice == 7)
		{
			loadHero();
		}
	}
	
	private void healHero()
	{
		char userInputChar;
		int userNumericalChoice;
		boolean notUsePotion = false;
		
		// Checks if hero exists
		if (hero != null) // True - The hero exists
		{
			try
			{
				// If the hero is a mage
				if (hero instanceof Mage)
				{
					System.out.println("\nDo you want to use magic heal? Y/N");
					userInputChar = readYNChar();
					
					if (userInputChar == 'Y') // Use magic heal
					{
						((Mage)hero).magicHeal();
					}
				}
				else
				{
					notUsePotion = true;
				}
			
				// Checking if the user wanted to use a potion
				if (notUsePotion == true)
				{
					System.out.println("\nWhat size of potion would you like to use?");
					System.out.println("1. Small");
					System.out.println("2. Medium");
					System.out.println("3. Large");
					
					//User enters choice
					userNumericalChoice = readInt(1,3);
					
					if (hero.usePotion(userNumericalChoice) == true)
					{
						System.out.println("\nPotion consumed!");
					}
					else
					{
						System.out.println("\nThis potion is unavailable!");
					}
				}
			}
			catch(TooManyAttemptsException e)
			{
				System.out.println(e.getMessage());
			}
		}
		else // False - The hero does not exist
		{
			System.out.println("\nNo hero exists!");
		}			
	}
	
	private void giveHeroExperience()
	{		
		// Checks if hero exists
		if (hero != null) // True - The hero exists
		{	
			try
			{
				System.out.println("How much experience do you want to give the hero <0 - 100000>?");
				boolean levelStatus = hero.gainExperience(readInt(0, 100000));
				
				if (levelStatus == true) // Hero has leveld up
				{
					System.out.println("\nHero has leveled up!");
				}
				else // Hero has not leveled up
				{
					System.out.println("\nHero has received some new experience");
				}
			}
			catch (TooManyAttemptsException e)
			{
				System.out.println(e.getMessage());
			}
		}
		else // False - The hero does not exist
		{
			System.out.println("\nNo hero exists!");
		}		
	}
	
	private void damageHero()
	{
		// Checks if hero exists
		if (hero != null) // True - The hero exists
		{
			try
			{
				System.out.println("How much do you want to damage the hero <0 - 100000>?");
				boolean healthStatus = hero.takeDamage(readInt(0, 100000));
				
				if (healthStatus == true) // Hero has not fainted
				{
					System.out.println("\nHero has taken damange");
				}
				else // Hero has fainted
				{
					System.out.println("\nHero has fainted!");
					System.out.println("You will need a new hero!");
				}
			}
			catch (TooManyAttemptsException e)
			{
				System.out.println(e.getMessage());
			}
		}
		else // False - The hero does not exist
		{
			System.out.println("\nNo hero exists!");
		}
		
	}
	
	private void saveHero()
	{		
		int userInputChar;
		boolean overwrite = true;
		
		// Checks if hero exists
		if (hero != null) // True - The hero exists
		{
			File file = new File("data/hero.bin");
			
			// Checks to see if the file exists
			// If it does not exist, most likely path will not have existed
			// Will then check for the path a create a new path is needed
			
			try
			{
				if (!file.exists())
				{
					File fileDir = new File("data");
					
					// Checks to see if the directory exists
					if (!fileDir.isDirectory())
					{
						if (fileDir.mkdir() == true)
						{
							System.out.println("\nDirectory \"data\" has been created! ");
						}
					}
				}
				else
				{				
					System.out.println("Are you sure you want to overwrite the file? Y/N");
					userInputChar = readYNChar();
					
					if (userInputChar == 'N') // Do not overwrite
					{
						overwrite = false;
					}
				}
				
				if (overwrite == true)
				{
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data/hero.bin"));
					
					// Saving type of hero
					
					if (hero instanceof Archer) // Archer
					{
						output.writeUTF("Archer");
					}
					else if (hero instanceof Warrior) // Warrior
					{
						output.writeUTF("Warrior");
					}
					else if (hero instanceof Mage) // Mage
					{
						output.writeUTF("Mage");
					}
					
					// General save section
					
					output.writeInt(hero.getHealth());
					output.writeInt(hero.getLevel());
					output.writeInt(hero.getExperience());
					output.writeInt(hero.getMaxHealth());
					
					if (hero instanceof Archer) // Archer
					{
						output.writeDouble(((Archer)hero).getBonusExp());
					}
					else if (hero instanceof Mage) //  Mage
					{
						output.writeDouble(((Mage)hero).getHealRate());
					}
					
					// Inventory
					
					output.writeInt(hero.getInventory().getSmallPotions());
					output.writeInt(hero.getInventory().getMediumPotions());
					output.writeInt(hero.getInventory().getLargePotions());
					
					output.close();
					
					System.out.println("\u001B[32m" + "\nHero saved!" + "\u001B[0m");
				}
			}
			catch (IOException e)
			{
				System.out.println("\nInput-Output exception caught!\nReturning back to the menu");
			}
			catch (TooManyAttemptsException e)
			{
				System.out.println(e.getMessage());
			}
		}
		else // False - The hero does not exist
		{
			System.out.println("\nNo hero exists!");
		}
	}
	
	private void loadHero()
	{
		boolean noOverwrite = true;
		boolean readConfirmed = false;
		
		// Re-usables
		char userInputChar;
		int userNumericalChoice;
		
		try
		{
			// Checks if hero exists
			if (hero != null) // True - The hero exists
			{
				
				System.out.println("\nHero exists!");
				System.out.println("\nDo you want to overwrite the current hero? Y/N");
				userInputChar = readYNChar();
				
				// If the user has selected yes, then continue
				// Otherwise, exit this method
				
				if (userInputChar == 'Y')
				{
					// The user will create a hero
					noOverwrite = false;
				}
			}		
	
			// If overwrite is confirmed
			if (noOverwrite == false)
			{
				File file = new File("data/hero.bin");
				File fileDir = new File("data");
				
				// Checks to see if the directory exists
				if (fileDir.exists() && fileDir.isDirectory())
				{
					// Checks to see if hero.bin exists
					if(file.exists())
					{
						readConfirmed = true;
					}
					else
					{
						throw new FileNotFoundException("\u001B[31m" + "\nhero.bin file not found!" + "\u001B[0m");
					}
				}
				else
				{
					System.out.println("\u001B[31m" + "\nDirectory \"data\" does not exist!" + "\u001B[0m");
				}

				// If the file exists, then readConfirmed is true
				if (readConfirmed == true)
				{
					ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/hero.bin"));

					// Hero type
					String heroType = input.readUTF();
					
					// Reading data
					int health = input.readInt();
					int level = input.readInt();
					int experience = input.readInt();
					int maxHealth = input.readInt();
					
					
					// Specialised
					
					double bonusExp = 0;
					int healRate = 0;
					
					if (heroType.equals("Archer")) // Archer
					{
						// Input bonusEXP as double
						bonusExp = input.readDouble();
					}
					else if (heroType.equals("Mage")) //  Mage
					{
						// Input healRate as int
						healRate = input.readInt();
					}
					
					// Inventory

					int smallPotion = input.readInt();
					int mediumotion = input.readInt();
					int largePotion = input.readInt();
					
					// Constructors
					if (heroType.equals("Archer")) // Archer
					{
						hero = new Archer(health, level, experience, maxHealth, new Inventory(smallPotion, mediumotion, largePotion), bonusExp);
						System.out.println("\u001B[32m" + "\nArcher loaded!" + "\u001B[0m");
					}
					else if (heroType.equals("Warrior"))
					{
						hero = new Warrior(health, level, experience, maxHealth, new Inventory(smallPotion, mediumotion, largePotion));
						System.out.println("\u001B[32m" + "\nWarrior loaded!" + "\u001B[0m");
					}
					else // Mage
					{
						hero = new Mage(health, level, experience, maxHealth, new Inventory(smallPotion, mediumotion, largePotion), healRate);
						System.out.println("\u001B[32m" + "\nMage loaded!" + "\u001B[0m");
					}
					
				}
			}
		}
		catch (FileNotFoundException e)
		{
			// No return to run() needed as this is a void
			System.out.println(e.getMessage());
			// Ask user if they would like to create a hero later on here
			// Make it go to createHero() method
		}
		catch (StreamCorruptedException e)
		{
			System.out.println("\u001B[31m" + "The binary file has been corrupted and could not be loaded!" + "\u001B[0m");
		}
		catch(EOFException e)
		{
			System.out.println("\u001B[31m" + "The file could not be loaded because it is empty" + "\u001B[0m");
		}
		catch (TooManyAttemptsException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("Input-Output exception caught!\nReturning to the menu");
		}
	}
	
	private void displayHero()
	{
		// Checks if hero exists
		if (hero != null) // True - The hero exists
		{
			System.out.println("\n----- Hero Stats ------\n");
			hero.display();
		}
		else // False - The hero does not exist
		{
			System.out.println("\nNo hero exists!");
		}
	}
	
	private void createHero()
	{
		// To check if the hero creation prompt needs to be displayed.
		boolean createHeroCheck = false;
		
		// Re-usables
		char userInputChar;
		int userNumericalChoice;
	
		try
		{
			// Checks if hero exists
			if (hero != null) // True - The hero exists
			{
				System.out.println("\nHero exists!");
				System.out.println("\nDo you want to overwrite the current hero? Y/N");
				userInputChar = readYNChar();

				// If the user has selected yes, then continue
				// Otherwise, exit this method
				
				if (userInputChar == 'Y')
				{
					// The user will create a hero
					createHeroCheck = true;
				}
			}
			else // False - The hero does not exist
			{
				System.out.println("\nHero does not exist!");
				
				// The user will create a hero
				createHeroCheck = true;
			}
		
			// Hero creation prompt
			
			if (createHeroCheck == true)
			{
				System.out.println("\nWhich hero types would you like to create?");
				System.out.println("1. Archer");
				System.out.println("2. Warrior");
				System.out.println("3. Mage");
				
				// User enters choice
				userNumericalChoice = readInt(1,3);
				
				if (userNumericalChoice == 1) // Archer
				{
					hero = new Archer();
					System.out.println("\u001B[32m" + "\nHero created!" + "\u001B[0m");
				}
				else if (userNumericalChoice == 2) // Warrior
				{
					hero = new Warrior();
					System.out.println("\u001B[32m" + "\nHero created!" + "\u001B[0m");
				}
				else // Mage
				{
					hero = new Mage();
					System.out.println("\u001B[32m" + "\nHero created!" + "\u001B[0m");				
				}
			}
		}
		catch (TooManyAttemptsException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public int readInt(int min, int max) throws TooManyAttemptsException
	{
		// Version 2
		
		for (int userAttempts = 0; userAttempts < 5; userAttempts++)
		{	
			try
			{
				//System.out.println("User Attempts: " + userAttempts);
				System.out.print("\nEnter choice >> ");
				int choice = keyboard.nextInt();
				keyboard.nextLine();
				
				// To check if the choice is in range
				ValueChecker.checkValue(choice, min, max);
				
				// Return the user choice value
				return choice;
			}
			catch (InputMismatchException e)
			{
				System.out.println("\nIncorrect input type!\nPlease try again");
				keyboard.nextLine();
			}
			catch(IntRangeException e) // User defined
			{
				System.out.println(e.getMessage());
			}
		}

		// Send message and make it go back to run();
		
		throw new TooManyAttemptsException("\nNo valid value entered after 5 attempts\nReturning to the menu");
	}
	
	public char readYNChar() throws TooManyAttemptsException
	{
		// Version 2
		
		for (int userAttempts = 0; userAttempts < 5; userAttempts++)
		{
			try
			{
				System.out.print("\nEnter choice >> ");
				char choice = keyboard.next().toUpperCase().charAt(0);
				keyboard.nextLine();
				
				ValueChecker.checkValue(choice);
				
				return choice;
			}
			catch (InputMismatchException e)
			{
				System.out.println("\nIncorrect input type!\nPlease try again");
				keyboard.nextLine();
			}
			catch (YNException e) // User defined
			{
				System.out.println(e.getMessage());
			}
			
		}
		
		throw new TooManyAttemptsException("\nNo valid value entered after 5 attempts\nReturning to the menu");
	}
	
}