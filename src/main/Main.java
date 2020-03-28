package main;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

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
			System.out.println("Name: " + p.getName());
			
			System.out.println("Gesamtpunkte: " + p.getOverallScore() + ", "
					+ dataCollector.getWonPercentagePerPlayer(p) + " % gewonnen");

			System.out.println("Punkte pro Spiel: " + df.format((double) p.getOverallScore() / (double) anzahlSpiele)
					+ ", Anzahl Solos: " + dataCollector.getSolosForPlayer(p).size() + " - "
					+ dataCollector.getSoloWonPercentagePerPlayer(p) + "% gewonnen");
			
			System.out.println("Spiele gegeben: " + dataCollector.getGamesDealtByPlayer(p).size());

			System.out.println("");
		}

		System.out.println("Anzahl gespielter Spiele: " + anzahlSpiele);

		System.out.println("Anzahl Solos " + anzahlSolos);

		System.out.println("Durchschnittliche Punktzahl pro Spiel: " + dataCollector.getAverageScorePerGame());

	}

}
