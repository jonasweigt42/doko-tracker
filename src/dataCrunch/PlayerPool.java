package dataCrunch;

import java.util.ArrayList;
import java.util.List;

import dataTypes.Player;

public class PlayerPool
{

	private static final List<Player> players = new ArrayList<>();
	
	public static Player getOrCreatePlayer(String name)
	{
		for(Player player : players)
		{
			if(player.getName().equals(name))
			{
				return player;
			}
		}
		Player player = new Player(name);
		players.add(player);
		return player;
	}
	
	public static List<Player> getAllPlayers()
	{
		return players;
	}
	
}
