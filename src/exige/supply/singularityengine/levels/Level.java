package exige.supply.singularityengine.levels;

import exige.supply.singularityengine.U;
import exige.supply.singularityengine.entities.Entity;
import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.levels.tiles.TileType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {

    public final int COLLISION_CONST;

    protected String name;
    protected int width, height;
    protected SpawnPoint[] spawnPoints = new SpawnPoint[]{new SpawnPoint(0, 0)}; // Default value
    private Tile[] tiles;
    private List<Entity> entities = new ArrayList<Entity>();

    public Level(String name, int width, int height, int c_Const) {
        this.name = name;
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];
        this.COLLISION_CONST = c_Const;
    }

    public Level(int width, int height, int c_Const) {
        this.name = "?";
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];
        this.COLLISION_CONST = c_Const;
    }

    public Level(String name, String path, int c_Const) {
        loadLevel(path);
        this.name = name;
        this.COLLISION_CONST = c_Const;
    }

    public Level(String path, int c_Const) {
        loadLevel(path);
        this.COLLISION_CONST = c_Const;
    }

    private void loadLevel(String path) {
        try {
            File levelFile = new File(path);
            this.name = levelFile.getName(); // Set the level name to file name if name is not specified
            BufferedImage image = ImageIO.read(levelFile);
            width = image.getWidth();
            height = image.getHeight();
            int[] loadingPixels = new int[width * height];
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

    public void update() {
        for (int i = 0; i < entities.size(); i++) { // For all entities in the level
            entities.get(i).update(); // Update entity state
        }
    }

    public void render(int xMove, int yMove, Screen screen) {
        screen.setOffset(xMove, yMove); // Render the level based on player movement position
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
        for (Entity entity : entities) { // For all entities in the level
            entity.render(screen);
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity e) {
        if (entities == null)
            entities = new ArrayList<Entity>();
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public void setSpawnPoints(SpawnPoint[] spawnPoints) {
        this.spawnPoints = spawnPoints;
    }

    public SpawnPoint[] getSpawnPoints() {
        return spawnPoints;
    }

    public SpawnPoint getSpawnPoint(int number) {
        System.out.println("num=" + number);
        return spawnPoints[number - 1];
    }

    public SpawnPoint getRandomSpawnPoint() {
        return spawnPoints[U.getRandomInt(0, spawnPoints.length - 1)];
    }

    public Tile getTile(int x, int y) {
        if (isLocationOutOfBounds(x, y)) return TileType.AIR.getTileClass(); // Keep tiles in bounds
        return tiles[x + y * width];
    }

    public void setTile(int x, int y, Tile tile){
        if (isLocationOutOfBounds(x, y)) return; // Do not set a tile out of bounds
        tiles [x + y * width] = tile;
    }

    public boolean isLocationOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || y >= height || x >= width); // Check Level boundries
    }

    public String getName() {
        return name;
    }
}