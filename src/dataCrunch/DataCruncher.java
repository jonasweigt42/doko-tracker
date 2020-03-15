package dataCrunch;

import java.text.DecimalFormat;
import java.util.List;

import dataTypes.Game;
import dataTypes.Session;

public class DataCruncher
{

	private List<Session> sessionData;
	
	private int gameCount;
	private int soloCount;
	private int overallScore;
	
	public DataCruncher(List<Session> sessionData)
	{
		this.sessionData = sessionData;
		calculateGames();
	}
	
	private void calculateGames()
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
			 	}
			}
		}
	}
	
	public String getScorePerGame()
	{
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format((double)overallScore / (double)gameCount);
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
