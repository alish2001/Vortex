package exige.supply.vortex.levels;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import exige.supply.vortex.engine.Screen;
import exige.supply.vortex.levels.tiles.TileType;

public class Level {

    //TODO: ADD SPAWNPOINT SYSTEM
    protected String name;
    protected int width, height;
    protected Tile[] tiles;

    private int[] loadingPixels;

    public Level(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];
        generate();
    }

    public Level(int width, int height) {
        this.name = "?";
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];
        generate();
    }

    public Level(String path) {
        loadLevel(path);
        generate();
    }

    private void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            width = image.getWidth();
            height = image.getHeight();
            loadingPixels = new int[width * height];
            tiles = new Tile[width * height];
            image.getRGB(0, 0, width, height, loadingPixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("<ERROR> Failed to load level!");
        }
    }

    protected void generate() {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = TileType.getTypeFromID(loadingPixels[i]).getTileClass();
        }

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

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen); // Render each tile on the proper position in the screen
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (isLocationOutOfBounds(x, y)) return TileType.AIR.getTileClass(); // Keep tiles in bounds
        return tiles[x + y * width];
    }

    public boolean isLocationOutOfBounds(int x, int y){
        return (x < 0 || y < 0 || y >= height || x >= width); // Check Level boundries

    }

    public void setTile(int x, int y, Tile tile){
        tiles[x + y * width] = tile;
    }

    public String getName() {
        return name;
    }
}