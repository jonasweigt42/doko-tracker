package dataTypes;
public class PlayerScore
{

	private String score;
	private Player player;
	
	public PlayerScore(String score, Player player)
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
