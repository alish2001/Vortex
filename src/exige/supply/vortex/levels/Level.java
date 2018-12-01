package exige.supply.vortex.levels;

import exige.supply.vortex.engine.Screen;
import exige.supply.vortex.levels.tiles.TileType;

public class Level {

	protected int width, height;
	protected int[] tiles;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generate();
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
	    screen.setOffset(xMove, yMove);
	    // Corner Pins
		int x0 = xMove >> 4; // Convert into TILE PRECISION | Shift by 4 (divided by 16(2^4))
		int x1 = (xMove + screen.getWidth() + 16) >> 4;
		int y0 = yMove >> 4;
        int y1 = (yMove + screen.getHeight() + 16) >> 4;

        for (int y = y0; y < y1; y++){
            for (int x = x0; x < x1; x++){
                getTile(x, y).render(x, y, screen); // Render each tile on the proper position in the screen
            }
        }
	}

	public Tile getTile(int x, int y){
	    if (x < 0 || y < 0 || y > height || x > width) return TileType.AIR.getTileClass(); // Keep tiles in bounds
	    return TileType.getTypeFromID(tiles[x + y * width]).getTileClass();
    }
}