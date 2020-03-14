
public class Game
{

	private GameScore gameScore1;
	private GameScore gameScore2;
	private GameScore gameScore3;
	private GameScore gameScore4;
	private Player dealer;
	private Player soloPlayer;
	
	public Game(GameScore gameScore1, GameScore gameScore2, GameScore gameScore3, GameScore gameScore4, Player dealer)
	{
		this.gameScore1 = gameScore1;
		this.gameScore2 = gameScore2;
		this.gameScore3 = gameScore3;
		this.gameScore4 = gameScore4;
		this.dealer = dealer;
		soloPlayer = calculateSoloPlayer();
	}
	
	public GameScore getGameScore1()
	{
		return gameScore1;
	}
	
	public GameScore getGameScore2()
	{
		return gameScore2;
	}
	
	public GameScore getGameScore3()
	{
		return gameScore3;
	}
	
	public GameScore getGameScore4()
	{
		return gameScore4;
	}
	
	public Player getDealer()
	{
		return dealer;
	}
	
	public Player getSoloPlayer()
	{
		return soloPlayer;
	}
	
	private Player calculateSoloPlayer()
	{
		//TODO implement
		return null;
	}
	
}
