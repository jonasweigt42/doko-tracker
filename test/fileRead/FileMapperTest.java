package fileRead;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import exceptions.InvalidHeaderException;

class FileMapperTest
{

	@Test
	public void testInvalidHeader() throws IOException
	{
		try
		{
			FileMapper.calculateSessions("test/data/invalid");
			fail("InvalidHeaderException should be thrown");
		} catch (InvalidHeaderException e)
		{
			// everything is fine
		}
	}
	
	@Test
	public void testvalid() throws IOException
	{
//		List<Session> sessions = FileMapper.calculateSessions("test/data/invalid");
	}

}
