public class Monster implements BattleReady
{
	private String name;
	private int health;
	private int level;
	
	public Monster(String name, int level)
	{
		this.name = name;
		this.level = level;
		this.health = level * 100;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public int attack()
	{
		return (int)((Math.random() * 100 * level)/3);
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
	
	public int rewardExperience()
	{
		return level * 25;
	}
	
	public Inventory rewardPotions()
	{
		int smallPotions = (int)(level-Math.random()*level/6);
		int mediumPotions = (int)(level-Math.random()*level/2);
		int largePotions = (int)((level-level/2)-Math.random()*(level-level/2));
		
		return new Inventory(smallPotions, mediumPotions, largePotions);
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getLevel()
	{
		return level;
	}
}