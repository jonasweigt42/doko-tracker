package dataCrunch;

import java.util.List;

import dataTypes.Game;
import dataTypes.Session;

public class DataCruncher
{

	public static int countAllGames(List<Session> sessions)
	{
		int result = 0;
		for(Session session : sessions)
		{
			result += session.getGames().size();
		}
		return result;
	}
	
	public static int countAllSolos(List<Session> sessions)
	{
		int result = 0;
		for(Session session : sessions)
		{
			for(Game game : session.getGames())
			{
			 	if(game.getSoloPlayer() != null)
			 	{
			 		result++;
			 	}
			}
		}
		return result;
	}
	
}
