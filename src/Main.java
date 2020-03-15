import java.util.List;

import dataCrunch.DataCruncher;
import dataTypes.Session;
import fileRead.FileMapper;

public class Main
{

	public static void main(String[] args)
	{
		List<Session> sessions = FileMapper.calculateSessions();
		
		int anzahlSpiele = DataCruncher.countAllGames(sessions);
		
		int anzahlSolos = DataCruncher.countAllSolos(sessions);
		
		System.out.println("Anzahl gespielter Spiele: " + anzahlSpiele);

		System.out.println("Anzahl Solos " + anzahlSolos);
	}


}
