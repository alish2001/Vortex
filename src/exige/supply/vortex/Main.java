package exige.supply.vortex;

import exige.supply.singularityengine.SingularityEngine;
import exige.supply.singularityengine.entities.Player;
import exige.supply.singularityengine.entities.PlayerCharacter;
import exige.supply.vortex.entities.players.VortexPlayer;
import exige.supply.vortex.entities.players.VortexPlayerTwo;
import exige.supply.vortex.levels.L_PeachyRuins;

public class Main {

	public static void main(String[] args) {
	    SingularityEngine renderer = new SingularityEngine("Vortex"); // Instantiate Game engine
		renderer.setLevel(new L_PeachyRuins());
		Player[] players = new Player[2];
		players[0] = new VortexPlayer(PlayerCharacter.JACK, renderer.getLevel());
        players[1] = new VortexPlayerTwo(PlayerCharacter.JORDAN, renderer.getLevel());
		renderer.setPlayers(players);
		renderer.start();
	}
}