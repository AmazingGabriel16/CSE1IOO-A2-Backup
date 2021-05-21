public abstract class Hero implements BattleReady
{
	private int health;
	private int level;
	private int experience;
	private int maxHealth;
	private Inventory inventory;
	private int nextLevelExperience;
	
	/* Note for inventory
	
	It is a a class to call inventory, example: Potion items.
	
	*/
	
	public Hero()
	{
		health = 100;
		level = 1;
		experience = 0;
		maxHealth = 100;
		inventory = new Inventory();
	}
	
	public Hero(int health, int level, int experience, int maxHealth, int nextLevelExperience, Inventory inventory)
	{
		this.health = health;
		this.level = level;
		this.experience = experience;
		this.maxHealth = maxHealth;
		this.nextLevelExperience = nextLevelExperience;
		this.inventory = inventory;
	}
	
	public int attack()
	{
		return (int)(Math.random() * 100 * level);
	}
	
	public boolean usePotion(int size)
	{
		if (inventory.usePotion(size) == true)
		{
			if (size == 1) // Small
			{
				setHealth(health + 10);
				return true;
			}
			else if (size == 2) // Medium
			{
				setHealth(health + 50);
				return true;
			}
			else if (size == 3) // Large
			{
				setHealth(health + 100);
				return true;
			}
		}
		
		// If potion not available.
		return false;
	}
	
	public boolean takeDamage(int amount)
	{
		setHealth(health - amount);
		
		if (getHealth() >= 1)
		{
			return true;
		}

		// If health less than 1.
		return false;
	}
	
	public boolean gainExperience(int amount)
	{		
		// For any Exp that is above the 1000 level up mark
		
		int overflowExp = 0;
		
		// Checks to see if the amount is greater than 1000
		if (amount >= nextLevelExperience)
		{
			// This keeps left over Exp to the next level
			while(amount >= nextLevelExperience)
			{
				amount -= nextLevelExperience;
				overflowExp = amount;
				incrementLevel();
			}
		
			// The new experience will be whatever was overflowing
			setExperience(overflowExp);
			
			return true;
		}
		
		// If experience <= 1000
		return false;
	}
	
	// Health and experience
	
	public void setHealth(int health)
	{
		this.health = health;
		
		if (health > maxHealth)
		{
			this.health = maxHealth;
		}
	}
	
	public void setMaxHealth(int maxHealth)
	{
		this.maxHealth = maxHealth;
	}
	
	public void setExperience(int experience)
	{
		this.experience = experience;
	}
	
	public void incrementLevel()
	{
		nextLevelExperience = nextLevelExperience*2;
		level++;
		
		// This is a abstract method
		upgradeStats();
	}
	
	// Getters
	
	public int getHealth()
	{
		return health;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	public int getExperience()
	{
		return experience;
	}
	
	public Inventory getInventory()
	{
		return inventory;
	}
	
	public int getNextLevelExperience()
	{
		return nextLevelExperience;
	}
	
	public void addPotions(Inventory otherInventory)
	{
		inventory.addPotions(otherInventory);
	}

	// Abstract methods
	
	public abstract void display();
	public abstract void upgradeStats();
	public abstract String getName();
}