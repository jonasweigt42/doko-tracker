package main;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import dataCrunch.DataCruncher;
import dataCrunch.PlayerPool;
import dataTypes.Player;
import dataTypes.Session;
import fileRead.FileMapper;

public class Main
{

	public static void main(String[] args) throws IOException
	{
		List<Session> sessions = FileMapper.calculateSessions();

		DataCruncher dataCruncher = new DataCruncher(sessions);

		int anzahlSpiele = dataCruncher.getGameCount();

		int anzahlSolos = dataCruncher.getSoloCount();

		List<Player> players = PlayerPool.getAllPlayers();

		DecimalFormat df = new DecimalFormat("0.00");
		for (Player p : players)
		{
			System.out.println("Name: " + p.getName() + ", Gesamtpunkte: " + p.getOverallScore()
					+ ", " + dataCruncher.getWonPercentagePerPlayer(p) + " % gewonnen"
					+ ", Punkte pro Spiel: " + df.format((double) p.getOverallScore() / (double) anzahlSpiele)
					+ ", Anzahl Solos: " + dataCruncher.getSolosForPlayer(p).size() + " - " + dataCruncher.getSoloWonPercentagePerPlayer(p) + "% gewonnen");
			System.out.println("");
		}

		System.out.println("Anzahl gespielter Spiele: " + anzahlSpiele);

		System.out.println("Anzahl Solos " + anzahlSolos);

		System.out.println("Durchschnittliche Punktzahl pro Spiel: " + dataCruncher.getAverageScorePerGame());

	}

}
