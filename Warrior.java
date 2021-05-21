public class Warrior extends Hero
{
	public Warrior()
	{
		// Gets the hero default constructor
		super();
	}
	
	public Warrior(int health, int level, int experience, int maxHealth, int nextLevelExperience, Inventory inventory)
	{
		super(health, level, experience, maxHealth, nextLevelExperience, inventory);
	}
	
	public void display()
	{
		System.out.println("Type:				Warrior");
		System.out.println("Health:				" + getHealth());
		System.out.println("Level:				" + getLevel());
		System.out.println("Experience:			" + getExperience());
		System.out.println("Max Health:			" + getMaxHealth());
		System.out.println("Potions - Small:		" + getInventory().getSmallPotions());
		System.out.println("        - Medium:		" +	getInventory().getMediumPotions());
		System.out.println("        - Large:		" + getInventory().getLargePotions());
	}

	public void upgradeStats()
	{
		setMaxHealth(getMaxHealth() + (getLevel() * 12));
		setHealth(getMaxHealth());
	}
	
	public String getName()
	{
		return "Warrior";
	}
	
	public String toString()
	{
		return "Warrior[health: " + getHealth() + " | level: " + getLevel() + " | experience: " + getMaxHealth() + " | inventory: " + getInventory() + "]";
	}
}