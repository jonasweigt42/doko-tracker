package dataTypes;

public class Player
{
	private String name;
	private int overallScore;
	
	public Player(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getOverallScore()
	{
		return overallScore;
	}
	
	public void addScore(int scoreToAdd)
	{
		overallScore = overallScore + scoreToAdd;
	}

}
