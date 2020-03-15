package dataTypes;

import java.util.List;

public class Game
{

	private List<GameScore> gamesScores;
	private Player dealer;
	private Player soloPlayer;
	
	public Game(List<GameScore> gamesScores, Player dealer, Player soloPlayer)
	{
		this.gamesScores = gamesScores;
		this.dealer = dealer;
		this.soloPlayer = soloPlayer;
	}
	
	public List<GameScore> getGameScores()
	{
		return gamesScores;
	}
	
	public Player getDealer()
	{
		return dealer;
	}
	
	public Player getSoloPlayer()
	{
		return soloPlayer;
	}
	
}
