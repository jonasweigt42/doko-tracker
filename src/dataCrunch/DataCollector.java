package dataCrunch;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import dataTypes.Game;
import dataTypes.Player;
import dataTypes.PlayerScore;
import dataTypes.Session;

public class DataCollector
{

	private List<Session> sessionData;

	private int gameCount;
	private int soloCount;
	private int overallScore;
	private List<Game> solos = new ArrayList<>();
	private List<Game> allGames = new ArrayList<>();
	private Map<LocalDate, String> averageScorePerSession = new TreeMap<>();

	private DecimalFormat df = new DecimalFormat("0.00");

	public DataCollector(List<Session> sessionData)
	{
		this.sessionData = sessionData;
		collectData();
	}

	private void collectData()
	{
		for (Session session : sessionData)
		{
			int sessionScore = 0;
			for (Game game : session.getGames())
			{
				collectGameData(game);
				sessionScore += game.getScore();
			}
			averageScorePerSession.put(session.getDate(),
					df.format((double) sessionScore / (double) session.getGames().size()));
		}
	}

	private void collectGameData(Game game)
	{
		gameCount++;
		allGames.add(game);
		overallScore += game.getScore();
		if (game.getSoloPlayer() != null)
		{
			soloCount++;
			solos.add(game);
		}
	}
	
	public String getWonPercentagePerPlayer(Player player)
	{
		int wonCounter = 0;
		for (Game game : allGames)
		{
			for (PlayerScore score : game.getPlayerScores())
			{
				if (score.getPlayer().equals(player) && score.getscore() == 0)
				{
					wonCounter++;
				}
			}
		}
		return df.format(((double) wonCounter / (double) gameCount) * 100);
	}

	public String getSoloWonPercentagePerPlayer(Player player)
	{
		int wonCounter = 0;
		int soloCounter = 0;
		for (Game game : solos)
		{
			if (game.getSoloPlayer().equals(player))
			{
				soloCounter++;
				for (PlayerScore score : game.getPlayerScores())
				{
					if (score.getPlayer().equals(player) && score.getscore() == 0)
					{
						wonCounter++;
					}
				}
			}
		}
		return df.format(((double) wonCounter / (double) soloCounter) * 100);
	}

	public String getAverageScorePerGame()
	{
		return df.format((double) overallScore / (double) gameCount);
	}

	public Map<LocalDate, String> getAverageScorePerSession()
	{
		return averageScorePerSession;
	}

	public List<Game> getSolosForPlayer(Player player)
	{
		return solos.stream().filter(game -> game.getSoloPlayer().equals(player)).collect(Collectors.toList());
	}

	public int getOverallScore()
	{
		return overallScore;
	}

	public int getGameCount()
	{
		return gameCount;
	}

	public int getSoloCount()
	{
		return soloCount;
	}

}
