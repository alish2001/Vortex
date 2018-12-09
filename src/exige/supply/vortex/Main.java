package exige.supply.vortex;

import exige.supply.singularityengine.SingularityEngine;
import exige.supply.singularityengine.entities.Player;
import exige.supply.singularityengine.entities.PlayerCharacter;
import exige.supply.vortex.entities.players.VortexPlayer;
import exige.supply.vortex.entities.players.VortexPlayerTwo;
import exige.supply.vortex.levels.L_PeachyRuins;

import javax.swing.*;

public class Main {

    public static SingularityEngine renderer;
	public static void main(String[] args) {
	    showSplash(2000);

        renderer = new SingularityEngine("Vortex", true); // Instantiate Game engine
		renderer.setLevel(new L_PeachyRuins());
		Player[] players = new Player[2];
		players[0] = new VortexPlayer(PlayerCharacter.JACK, renderer.getLevel());
        players[1] = new VortexPlayerTwo(PlayerCharacter.JORDAN, renderer.getLevel());
		renderer.setPlayers(players);
		renderer.start();
	}

	public static void showSplash(long time){
        JWindow window = new JWindow();
        window.getContentPane().add(new JLabel("", new ImageIcon("res/VortexLogo.png"), SwingConstants.CENTER));
        window.pack();
        window.setLocationRelativeTo(null); // Center window
        window.setVisible(true);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        window.dispose();
    }
}