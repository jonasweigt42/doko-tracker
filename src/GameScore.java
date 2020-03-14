
public class GameScore
{

	private int score;
	private Player player;
	private boolean solo;
	
	public GameScore(int score, Player player, boolean solo)
	{
		this.score = score;
		this.player = player;
		this.solo = solo;
	}
	public int getscore()
	{
		return score;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public boolean isSolo()
	{
		return solo;
	}
	
	
}
