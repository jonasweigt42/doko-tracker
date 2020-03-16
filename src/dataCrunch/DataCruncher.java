package dataCrunch;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dataTypes.Game;
import dataTypes.Player;
import dataTypes.PlayerScore;
import dataTypes.Session;

public class DataCruncher
{

	private List<Session> sessionData;
	
	private int gameCount;
	private int soloCount;
	private int overallScore;
	private List<Game> solos = new ArrayList<>();
	private List<Game> allGames = new ArrayList<>();
	
	private DecimalFormat df = new DecimalFormat("0.00");
	
	public DataCruncher(List<Session> sessionData)
	{
		this.sessionData = sessionData;
		crunchData();
	}
	
	private void crunchData()
	{
		for(Session session : sessionData)
		{
			gameCount += session.getGames().size();
			for(Game game : session.getGames())
			{
				allGames.add(game);
				overallScore += game.getScore();
			 	if(game.getSoloPlayer() != null)
			 	{
			 		soloCount++;
			 		solos.add(game);
			 	}
			}
		}
	}
	
	public String getWonPercentagePerPlayer(Player player)
	{
		int wonCounter = 0;
		for(Game game : allGames)
		{
			for(PlayerScore score : game.getPlayerScores())
			{
				if(score.getPlayer().equals(player) && score.getscore() == 0)
				{
					wonCounter++;
				}
			}
		}
		return df.format(((double)wonCounter / (double)gameCount)*100);
	}
	
	public String getScorePerGame()
	{
		return df.format((double)overallScore / (double)gameCount);
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
