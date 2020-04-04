package dataTypes;
public class PlayerScore
{

	private String score;
	private Player player;
	private Position position;
	
	public PlayerScore(String score, Player player, Position position)
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

	public Position getPosition()
	{
		return position;
	}

}
