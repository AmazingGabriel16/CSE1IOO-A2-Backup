public class ValueChecker
{
	public static void checkValue(int value, int min, int max) throws IntRangeException
	{
		if (value < min)
		{
			throw new IntRangeException("Value is below minimum");
		}
		else if (value > max)
		{
			throw new IntRangeException("Value is above maximum");
		}
	}
	
	public static void checkValue(char input) throws YNException
	{
		if (input != 'Y')
		{
			if (input != 'N')
			{
				throw new YNException("Entry not Y or N\nPlease try again");
			}
		}
	}
}