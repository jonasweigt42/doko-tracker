package dataCrunch;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dataTypes.Game;
import dataTypes.Player;
import dataTypes.Session;

public class DataCruncher
{

	private List<Session> sessionData;
	
	private int gameCount;
	private int soloCount;
	private int overallScore;
	private List<Game> solos = new ArrayList<>();
	
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
				overallScore += game.getScore();
			 	if(game.getSoloPlayer() != null)
			 	{
			 		soloCount++;
			 		solos.add(game);
			 	}
			}
		}
	}
	
	public String getScorePerGame()
	{
		DecimalFormat df = new DecimalFormat("0.00");
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
