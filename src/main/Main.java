package main;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dataCollect.DataCollector;
import dataCollect.PlayerPool;
import dataTypes.Player;
import dataTypes.Session;
import fileRead.FileMapper;

public class Main
{

	public static void main(String[] args) throws IOException
	{
		List<Session> sessions = FileMapper.calculateSessions();

		DataCollector dataCollector = new DataCollector(sessions);

		int anzahlSpiele = dataCollector.getGameCount();

		int anzahlSolos = dataCollector.getSoloCount();

		List<Player> players = PlayerPool.getAllPlayers();

		DecimalFormat df = new DecimalFormat("0.00");
		for (Player p : players)
		{
			System.out.println("Name: " + p.getName() + ", Gesamtpunkte: " + p.getOverallScore()
					+ ", " + dataCollector.getWonPercentagePerPlayer(p) + " % gewonnen"
					+ ", Punkte pro Spiel: " + df.format((double) p.getOverallScore() / (double) anzahlSpiele)
					+ ", Anzahl Solos: " + dataCollector.getSolosForPlayer(p).size() + " - " + dataCollector.getSoloWonPercentagePerPlayer(p) + "% gewonnen");
			System.out.println("");
		}

		System.out.println("Anzahl gespielter Spiele: " + anzahlSpiele);

		System.out.println("Anzahl Solos " + anzahlSolos);

		System.out.println("Durchschnittliche Punktzahl pro Spiel: " + dataCollector.getAverageScorePerGame());
		
		Map<LocalDate, String> averageScorePerSession = dataCollector.getAverageScorePerSession();

		for(Entry<LocalDate, String> entry : averageScorePerSession.entrySet())
		{
			System.out.println(entry.getKey().toString() + ": " + entry.getValue());
		}
		
		for(Entry<Integer, String> entry : dataCollector.getAverageScorePerQuarter().entrySet())
		{
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

	}

}
