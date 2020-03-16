package dataCrunch;

import java.util.ArrayList;
import java.util.List;

import dataTypes.Player;

public class PlayerPool {

	private static final List<Player> players = new ArrayList<>();

	public static Player getOrCreatePlayer(String name) {

		players.stream()
				.filter(p -> p.getName().equals(name))
				.findFirst()
				.orElse(createPlayer(name));

		return createPlayer(name);
	}

	private static Player createPlayer(String name) {
		Player player = new Player(name);
		players.add(player);
		return player;
	}

	public static List<Player> getAllPlayers() {
		return players;
	}

}
