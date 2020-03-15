package dataTypes;

import java.util.List;

public class Game
{

	private List<PlayerScore> playerScores;
	private Player dealer;
	private Player soloPlayer;
	private int score;
	
	public Game(List<PlayerScore> playerScores, Player dealer, Player soloPlayer)
	{
		this.playerScores = playerScores;
		this.dealer = dealer;
		this.soloPlayer = soloPlayer;
		calculateScore();
	}
	
	private void calculateScore()
	{
		score = 0;
		for(PlayerScore pScore : playerScores)
		{
			if(pScore.getscore() > 0)
			{
				score = pScore.getscore();
			}
		}
	}
	
	public int getScore()
	{
		return score;
	}

	public List<PlayerScore> getPlayerScores()
	{
		return playerScores;
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
