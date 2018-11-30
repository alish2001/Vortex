package exige.supply.vortex.levels;

import exige.supply.vortex.renderer.Screen;

public class Level {

	protected int width, height;
	protected int[] tiles;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
	}
	
	public Level (String path) {
		loadLevel();
	}
	
	private void loadLevel() {
		
	}
	
	protected void generate() {
		
	}

	public void run() {

	}

	public void update() {

	}

	public void render(int xMove, int yMove, Screen screen) {

	}
}