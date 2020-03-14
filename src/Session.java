import java.util.Date;
import java.util.List;

public class Session
{

	private List<Game> games;
	private Date date;
	
	public List<Game> getGames()
	{
		return games;
	}
	public void setGames(List<Game> games)
	{
		this.games = games;
	}
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	
}
