public class Inventory
{
	private int smallPotions;
	private int mediumPotions;
	private int largePotions;
	
	public Inventory()
	{
		// Default set as:
		
		smallPotions = 2;
		mediumPotions = 1;
		largePotions = 0;
	}
	
	public Inventory(int smallPotions, int mediumPotions, int largePotions)
	{
		this.smallPotions = smallPotions;
		this.mediumPotions = mediumPotions;
		this.largePotions = largePotions;
	}
	
	public int getSmallPotions()
	{
		return smallPotions;
	}
	
	public int getMediumPotions()
	{
		return mediumPotions;
	}
	
	public int getLargePotions()
	{
		return largePotions;
	}
	
	public boolean usePotion(int choice)
	{
		boolean returnVal = false;
		
		if(choice == 1) // Small
		{
			if (getSmallPotions() > 0)
			{
				smallPotions -= 1;
				returnVal = true;
			}
		}
		else if(choice == 2) // Medium
		{
			if (getMediumPotions() > 0)
			{
				smallPotions -= 1;
				returnVal = true;
			}
		}
		else if(choice == 3) // Large
		{
			if (getLargePotions() > 0)
			{
				smallPotions -= 1;
				returnVal = true;
			}
		}
		
		return returnVal;
	}	
	
	public String toString()
	{
		return "Inventory[smallPotions: " + smallPotions  + " | mediumPotions: " + mediumPotions + " | largePotions: " + largePotions + "]";
	}
}