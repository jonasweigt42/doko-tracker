package fileRead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import dataCrunch.PlayerPool;
import dataTypes.Game;
import dataTypes.PlayerScore;
import dataTypes.Player;
import dataTypes.Session;
import exceptions.InvalidDealerException;
import exceptions.InvalidHeaderException;

public class FileMapper
{
	private static Logger logger = Logger.getLogger(FileMapper.class.getName());

	public static List<Session> calculateSessions() throws IOException
	{
		List<File> files = FileMapper.loadFiles();

		List<Session> sessions = new ArrayList<>();

		for (File file : files)
		{
			Session session = transformFileToSession(file);
			sessions.add(session);
		}

		return sessions;
	}

	private static Session transformFileToSession(File file) throws IOException
	{
		BufferedReader csvReader = new BufferedReader(new FileReader(file));

		Session session = new Session(mapFileNameToDate(file.getName()));

		String firstline = csvReader.readLine();
		String[] firstLineArray = firstline.split(",");
		checkFirstLineArray(firstLineArray);
		Player player1 = PlayerPool.getOrCreatePlayer(firstLineArray[1]);
		Player player2 = PlayerPool.getOrCreatePlayer(firstLineArray[2]);
		Player player3 = PlayerPool.getOrCreatePlayer(firstLineArray[3]);
		Player player4 = PlayerPool.getOrCreatePlayer(firstLineArray[4]);

		List<Game> gamesOfSession = extractGames(csvReader, player1, player2, player3, player4);
		session.setGames(gamesOfSession);
		return session;

	}

	private static void checkFirstLineArray(String[] firstLineArray) throws InvalidHeaderException
	{
		if (firstLineArray[0].equals("Dealer") && firstLineArray[5].equals("Solo"))
		{
			return;
		}
		throw new InvalidHeaderException("Dealer and Solo should be part of the csv header!");
	}

	private static List<Game> extractGames(BufferedReader csvReader, Player player1, Player player2, Player player3,
			Player player4) throws IOException
	{
		List<Game> gamesOfSession = new ArrayList<>();
		String line;
		while ((line = csvReader.readLine()) != null)
		{
			String[] gameData = line.split(",");

			String possibleSoloPlayer = calcSoloPlayer(gameData);

			List<PlayerScore> gameScores = new ArrayList<PlayerScore>();
			gameScores.add(new PlayerScore(gameData[1], player1));
			gameScores.add(new PlayerScore(gameData[2], player2));
			gameScores.add(new PlayerScore(gameData[3], player3));
			gameScores.add(new PlayerScore(gameData[4], player4));

			gamesOfSession.add(new Game(gameScores, calcDealer(gameData[0], player1, player2, player3, player4),
					mapPlayerByName(possibleSoloPlayer, player1, player2, player3, player4)));

		}
		return gamesOfSession;
	}

	private static Player calcDealer(String name, Player... players) throws InvalidDealerException
	{
		Player dealer = mapPlayerByName(name, players);
		if (dealer != null)
		{
			return dealer;
		}
		throw new InvalidDealerException("Dealername was not found!");
	}

	private static Player mapPlayerByName(String name, Player... players)
	{
		for (Player player : players)
		{
			if (player.getName().equals(name))
			{
				return player;
			}
		}
		return null;
	}

	private static List<File> loadFiles()
	{
		final File folder = new File("src/data");
		return Arrays.asList(folder.listFiles());
	}

	private static LocalDate mapFileNameToDate(String fileName)
	{
		String withoutTail = fileName.substring(0, fileName.length() - 4);
		String[] splitted = withoutTail.split("_");
		return LocalDate.of(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]),
				Integer.parseInt(splitted[2]));
	}

	private static String calcSoloPlayer(String[] gameData)
	{
		try
		{
			logger.info("Solo von " + gameData[5]);
			return gameData[5];
		} catch (Exception e)
		{
			logger.info("Kein Solospiel vorhanden");
		}
		return null;
	}

}
