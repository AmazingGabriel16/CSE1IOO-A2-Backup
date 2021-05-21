public class TooManyAttemptsException extends Exception
{
	public TooManyAttemptsException()
	{
		super();
	}
	
	public TooManyAttemptsException(String message)
	{
		super(message);
	}
}