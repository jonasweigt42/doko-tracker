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

import dataTypes.Game;
import dataTypes.GameScore;
import dataTypes.Player;
import dataTypes.Session;

public class FileMapper
{
	private static Logger logger = Logger.getLogger(FileMapper.class.getName());

	public static List<Session> calculateSessions()
	{
		List<File> files = FileMapper.loadFiles();

		List<Session> sessions = new ArrayList<>();

		for (File file : files)
		{
			Session session = transformFileToSession(file);
			if (session != null)
			{
				sessions.add(session);
			}
		}

		return sessions;
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

	private static Session transformFileToSession(File file)
	{
		try (BufferedReader csvReader = new BufferedReader(new FileReader(file)))
		{
			Session session = new Session(mapFileNameToDate(file.getName()));

			String firstline = csvReader.readLine();
			String[] players = firstline.split(",");
			Player player1 = new Player(players[1]);
			Player player2 = new Player(players[2]);
			Player player3 = new Player(players[3]);
			Player player4 = new Player(players[4]);

			List<Game> gamesOfSession = extractGames(csvReader, player1, player2, player3, player4);
			session.setGames(gamesOfSession);
			return session;

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}

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

			gamesOfSession.add(new Game(new GameScore(gameData[1], player1), new GameScore(gameData[2], player2),
					new GameScore(gameData[3], player3), new GameScore(gameData[4], player4),
					mapPlayerByName(gameData[0], player1, player2, player3, player4),
					mapPlayerByName(possibleSoloPlayer, player1, player2, player3, player4)));

		}
		return gamesOfSession;
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
