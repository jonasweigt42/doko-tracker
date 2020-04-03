package fileRead;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import dataTypes.Game;
import dataTypes.Player;
import dataTypes.PlayerScore;
import dataTypes.Session;
import exceptions.InvalidDealerException;
import exceptions.InvalidHeaderException;

class FileMapperTest
{

	@Test
	public void testInvalidHeader() throws IOException
	{
		try
		{
			FileMapper.calculateSessions("test/data/invalidHeader");
			fail("InvalidHeaderException should be thrown");
		} catch (InvalidHeaderException e)
		{
			// everything is fine
		}
	}
	
	@Test
	public void testInvalidDealer() throws IOException
	{
		try
		{
			FileMapper.calculateSessions("test/data/invalidDealer");
			fail("InvalidHeaderException should be thrown");
		} catch (InvalidDealerException e)
		{
			// everything is fine
		}
	}
	
	@Test
	public void testValidSessionCount() throws IOException
	{
		List<Session> sessions = FileMapper.calculateSessions("test/data/valid");
		assertEquals(1, sessions.size());
	}
	
	@Test
	public void testValidGameCount() throws IOException
	{
		List<Session> sessions = FileMapper.calculateSessions("test/data/valid");
		assertEquals(2, sessions.get(0).getGames().size());
	}
	
	@Test
	public void testValidSessionDate() throws IOException
	{
		List<Session> sessions = FileMapper.calculateSessions("test/data/valid");
		assertEquals(LocalDate.of(1999, 01, 03), sessions.get(0).getDate());
	}
	
	@Test
	public void testValidGameDates() throws IOException
	{
		List<Session> sessions = FileMapper.calculateSessions("test/data/valid");
		for(Game game : sessions.get(0).getGames())
		{
			assertEquals(LocalDate.of(1999, 01, 03), game.getDate());
		}
	}
	
	@Test
	public void testValidSoloPlayer() throws IOException
	{
		List<Session> sessions = FileMapper.calculateSessions("test/data/validSolo");
		Game game = sessions.get(0).getGames().get(0);
		assertEquals(new Player("Eva"), game.getSoloPlayer());
	}
	
	@Test
	public void testValidDealerPlayer() throws IOException
	{
		List<Session> sessions = FileMapper.calculateSessions("test/data/validSolo");
		Game game = sessions.get(0).getGames().get(0);
		assertEquals(new Player("Eva"), game.getDealer());
	}
	
	@Test
	public void testValidGameScore() throws IOException
	{
		List<Session> sessions = FileMapper.calculateSessions("test/data/valid");
		Game game1 = sessions.get(0).getGames().get(0);
		Game game2 = sessions.get(0).getGames().get(1);
		assertEquals(13, game1.getScore());
		assertEquals(1, game2.getScore());
	}
	
	@Test
	public void testValidPlayerScores() throws IOException
	{
		List<Session> sessions = FileMapper.calculateSessions("test/data/valid");
		Game game1 = sessions.get(0).getGames().get(0);
		List<PlayerScore> playerScores = game1.getPlayerScores();
		assertEquals(1, playerScores.get(0).getPosition());
		assertEquals(new Player("Jochen"), playerScores.get(0).getPlayer());
		assertEquals(0, playerScores.get(0).getScore());
	}

}
