public class Mage extends Hero
{
	private int healRate;
	
	public Mage()
	{
		// Gets the hero default constructor
		super();
		this.healRate = 10;
	}
	
	public Mage(int health, int level, int experience, int maxHealth, int nextLevelExperience, Inventory inventory, int healRate)
	{
		super(health, level, experience, maxHealth, nextLevelExperience, inventory);
		this.healRate = healRate;
	}
	
	public void magicHeal()
	{
		setHealth(getHealth() + (getLevel() * healRate));
		
		// In case health > max health.
		
		if (getHealth() > getMaxHealth())
		{
			setHealth(getMaxHealth());
		}
	}
	
	public int getHealRate()
	{
		return healRate;
	}
	
	public void display()
	{
		System.out.println("Type:				Mage");
		System.out.println("Health:				" + getHealth());
		System.out.println("Level:				" + getLevel());
		System.out.println("Experience:			" + getExperience());
		System.out.println("Max Health:			" + getMaxHealth());
		System.out.println("Potions - Small:		" + getInventory().getSmallPotions());
		System.out.println("        - Medium:		" +	getInventory().getMediumPotions());
		System.out.println("        - Large:		" + getInventory().getLargePotions());
		System.out.println("Heal Rate:			" + getHealRate());
	}
	
	public void upgradeStats()
	{
		this.healRate += 10;
		setMaxHealth(getMaxHealth() + (getLevel() * 9));
		setHealth(getMaxHealth());
	}
	
	public String toString()
	{
		return "Mage[health: " + getHealth() + " | level: " + getLevel() + " | experience: " + getMaxHealth() + " | inventory: " + getInventory() + " | healRate: " + getHealRate() + "]";
	}
}