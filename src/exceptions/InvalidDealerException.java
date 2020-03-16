package exceptions;

public class InvalidDealerException extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	
	public InvalidDealerException(String message)
	{
		super(message);
	}
	
}
