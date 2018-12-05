package exige.supply.vortex;

import exige.supply.vortex.engine.GameEngine;

public class Main {

	public static GameEngine renderer = new GameEngine("Vortex"); // Instantiate Game engine
	public static strictfp void main(String[] args) {
		// Add sprite
		renderer.start();
	}
}