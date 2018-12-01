package exige.supply.vortex;

import exige.supply.vortex.engine.GameEngine;
import exige.supply.vortex.entities.Player;

public class VortexGame {

	private GameEngine renderer;

	private Player[] players = new Player[2];
	private int time;
	private int rounds;
	
	public VortexGame() {
		setTime(180);
		setNumberOfRounds(3);
        renderer = new GameEngine();
	}
	
	public VortexGame(Player player1, Player player2) {
		players[0] = player1;
		players[1] = player2;
		setTime(180);
		setNumberOfRounds(3);
	}
	
	public VortexGame(Player player1, Player player2, int rounds, int time) {
		players[0] = player1;
		players[1] = player2;
		this.setTime(time);
		this.setNumberOfRounds(rounds);
	}

	public void overwritePlayerArray(Player[] players) {
		this.players = players;
	}
	
	public Player[] getPlayerArray() {
		return players;
	}
	
	public int getNumberOfRounds() {
		return rounds;
	}

	public void setNumberOfRounds(int rounds) {
		this.rounds = rounds;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int seconds) {
		this.time = seconds;
	}

}