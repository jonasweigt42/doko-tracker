import java.util.List;

import dataCrunch.DataCruncher;
import dataTypes.Player;
import dataTypes.PlayerPool;
import dataTypes.Session;
import fileRead.FileMapper;

public class Main
{

	public static void main(String[] args)
	{
		List<Session> sessions = FileMapper.calculateSessions();
		
		DataCruncher dataCruncher = new DataCruncher(sessions);
		
		int anzahlSpiele = dataCruncher.getGameCount();
		
		int anzahlSolos = dataCruncher.getSoloCount();
		
		List<Player> players = PlayerPool.getAllPlayers();
		for(Player p : players)
		{
			System.out.println("Name: " + p.getName() + ", Gesamtpunkte: " + p.getOverallScore());
		}
		
		System.out.println("Anzahl gespielter Spiele: " + anzahlSpiele);

		System.out.println("Anzahl Solos " + anzahlSolos);
		
		
	}


}
