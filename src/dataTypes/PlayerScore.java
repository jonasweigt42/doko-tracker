package dataTypes;
public class PlayerScore
{

	private String score;
	private Player player;
	private int position;
	
	public PlayerScore(String score, Player player, int position)
	{
		this.score = score;
		this.player = player;
		this.position = position;
		player.addScore(Integer.parseInt(score));
	}
	
	public int getScore()
	{
		return Integer.parseInt(score);
	}
	
	public Player getPlayer()
	{
		return player;
	}

	public int getPosition()
	{
		return position;
	}

}
