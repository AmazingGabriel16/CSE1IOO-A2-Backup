public class Archer extends Hero
{
	private double bonusExp;
	
	public Archer()
	{
		// Gets the hero default constructor
		super();
		
		this.bonusExp = 0.5;
	}
	
	public Archer(int health, int level, int experience, int maxHealth, Inventory inventory, double bonusExp)
	{
		super(health, level, experience, maxHealth, inventory);
		this.bonusExp = bonusExp;
	}
	
	public boolean gainExperience(int amount)
	{
		return super.gainExperience((amount + (int)(amount*bonusExp)));
	}
	
	public double getBonusExp()
	{
		return bonusExp;
	}
	
	public void display()
	{
		System.out.println("Type:				Archer");
		System.out.println("Health:				" + getHealth());
		System.out.println("Level:				" + getLevel());
		System.out.println("Experience:			" + getExperience());
		System.out.println("Max Health:			" + getMaxHealth());
		System.out.println("Potions - Small:		" + getInventory().getSmallPotions());
		System.out.println("        - Medium:		" +	getInventory().getMediumPotions());
		System.out.println("        - Large:		" + getInventory().getLargePotions());
		System.out.println("Bonus Exp:			" + getBonusExp());
	}
	
	public void upgradeStats()
	{
		this.bonusExp = bonusExp + 0.01;
		setMaxHealth(getMaxHealth() + (getLevel() * 10));
		setHealth(getMaxHealth());
	}
	
	public String toString()
	{
		return "Archer[health: " + getHealth() + " | level: " + getLevel() + " | experience: " + getMaxHealth() + " | inventory: " + getInventory() + " | bonusExp: " + getBonusExp() + "]";
	}
}