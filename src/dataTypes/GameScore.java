package dataTypes;
public class GameScore
{

	private String score;
	private Player player;
	
	public GameScore(String score, Player player)
	{
		this.score = score;
		this.player = player;
		player.addScore(Integer.parseInt(score));
	}
	
	public int getscore()
	{
		return Integer.parseInt(score);
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	
}
