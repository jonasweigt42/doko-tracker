package dataTypes;
import java.time.LocalDate;
import java.util.List;

public class Session
{

	private List<Game> games;
	private LocalDate date;
	
	public Session(LocalDate date)
	{
		this.date = date;
	}
	
	public List<Game> getGames()
	{
		return games;
	}
	public void setGames(List<Game> games)
	{
		this.games = games;
	}
	public LocalDate getDate()
	{
		return date;
	}

	
	
}
