import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{

	public static void main(String[] args)
	{
		List<File> files = loadFiles();

		for (File file : files)
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

				List<Game> gamesOfSession = new ArrayList<>();
				String line;
				while ((line = csvReader.readLine()) != null)
				{
					String[] gameData = line.split(",");
					
					gamesOfSession.add(
							new Game(
							new GameScore(gameData[1], player1), 
							new GameScore(gameData[2], player2),
							new GameScore(gameData[3], player3), 
							new GameScore(gameData[4], player4),
							mapDealerByName(gameData[0], player1, player2, player3, player4)
							));

				}
				session.setGames(gamesOfSession);

			} catch (Exception e)
			{
				System.out.println(e.getMessage());
			}

		}

	}

	private static Player mapDealerByName(String name, Player... players)
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

}
