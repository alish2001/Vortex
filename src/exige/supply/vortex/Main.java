package exige.supply.vortex;

import exige.supply.vortex.renderer.Renderer;

public class Main {

	public static void main(String[] args) {
		Renderer renderer = new Renderer("Vortex"); // Instantiate Game renderer
		renderer.start();
	}
}