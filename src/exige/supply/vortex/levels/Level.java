package exige.supply.vortex.levels;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import exige.supply.vortex.engine.Screen;
import exige.supply.vortex.entities.Entity;
import exige.supply.vortex.levels.tiles.TileType;

public class Level {

	protected SpawnPoint spawnPoint;
    protected String name;
    protected int width, height;
    private Tile[] tiles;
    
    private List<Entity> entities = new ArrayList<Entity>();

    public Level(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];
    }

    public Level(int width, int height) {
        this.name = "?";
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];
    }

    public Level(String name, String path) {
        loadLevel(path);
        this.name = name;
    }
    
    public Level(String path) {
        loadLevel(path);
    }

    private void loadLevel(String path) {
        try {
        	File levelFile = new File(path);
        	this.name = levelFile.getName(); // Set the level name to file name if name is not specified
            BufferedImage image = ImageIO.read(levelFile);
            width = image.getWidth();
            height = image.getHeight();
            int [] loadingPixels = new int[width * height];
            tiles = new Tile[width * height];
            image.getRGB(0, 0, width, height, loadingPixels, 0, width);
            for (int i = 0; i < tiles.length; i++) {
                tiles[i] = TileType.getTypeFromID(loadingPixels[i]).getTileClass();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("<ERROR> Failed to load level!");
        }
    }

    public void run() {

    }

    public void update() {
        for (Entity entity : entities){ // For all entities in the level
            entity.update(); // Update entity state
        }
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
        for (Entity entity : entities){ // For all entities in the level
            entity.render(screen);
        }
    }

    public List<Entity> getEntities(){
        return entities;
    }

    public void addEntity(Entity e){
        if (entities == null)
            entities = new ArrayList<Entity>();
        entities.add(e);
    }

    public void removeEntity(Entity e){
        entities.remove(e);
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