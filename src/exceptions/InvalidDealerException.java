package exceptions;

import java.io.IOException;

public class InvalidDealerException extends IOException
{

	private static final long serialVersionUID = 1L;
	
	public InvalidDealerException(String message)
	{
		super(message);
	}
	
}
