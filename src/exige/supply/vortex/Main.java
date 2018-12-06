package exige.supply.vortex;

import exige.supply.vortex.engine.GameEngine;

public class Main {

	public static void main(String[] args) {
	    GameEngine renderer = new GameEngine("Vortex"); // Instantiate Game engine
		renderer.start();
	}
}