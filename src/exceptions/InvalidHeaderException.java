package exceptions;

import java.io.IOException;

public class InvalidHeaderException extends IOException
{

	private static final long serialVersionUID = 1L;

	public InvalidHeaderException(String message)
	{
		super(message);
	}
}
