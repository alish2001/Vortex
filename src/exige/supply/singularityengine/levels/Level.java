package exige.supply.singularityengine.levels;

import exige.supply.singularityengine.U;
import exige.supply.singularityengine.entities.Entity;
import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.levels.tiles.TileType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Level Class. Allows different levels to be created and loaded.
 *
 * @author Ali Shariatmadari
 */

public class Level {

    public final int TILE_CONST; // The default tile size in level

    protected String name;
    protected int width, height;
    protected SpawnPoint[] spawnPoints = new SpawnPoint[]{new SpawnPoint(0, 0)}; // Default spawn point values
    private Tile[] tiles;
    private List<Entity> entities = new ArrayList<Entity>();

    public Level(String name, int width, int height, int c_Const) {
        this.name = name;
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];
        this.TILE_CONST = c_Const;
    }

    public Level(int width, int height, int c_Const) {
        this.name = "?";
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];
        this.TILE_CONST = c_Const;
    }

    public Level(String name, String path, int c_Const) {
        loadLevel(path);
        this.name = name;
        this.TILE_CONST = c_Const;
    }

    public Level(String path, int c_Const) {
        loadLevel(path);
        this.TILE_CONST = c_Const;
    }

    /**
     * Loads the level from level file
     */
    private void loadLevel(String path) {
        try {
            URL levelFile = this.getClass().getClassLoader().getResource(path); // Get level file address
            BufferedImage image = ImageIO.read(levelFile); // Read the buffered image from the address
            this.name = levelFile.toString(); // Set the level name to file address
            width = image.getWidth(); // Set level width to level file width
            height = image.getHeight(); // Set level height to level file height
            int[] loadingPixels = new int[width * height]; // Instantiate level loading pixels array
            tiles = new Tile[width * height]; // Instantiate level tiles based on width and height
            image.getRGB(0, 0, width, height, loadingPixels, 0, width); // write RGB value of level pixels in the sheet into loadingPixels[]
            for (int i = 0; i < tiles.length; i++) { // Loop through all tile entries in the level
                // Set the tiles in the level via the TileType associated with the pixel values (assuming the Tile IDs match the pixels from the level file)
                tiles[i] = TileType.getTypeFromID(loadingPixels[i]).getTileClass();
            }

        } catch (IOException e) { // Catch fileIO error just in case
            e.printStackTrace(); // Print error log
            System.err.println("<ERROR> Failed to load level!"); // Print error reason
        }
    }

    // update method run every update cycle to update the level state
    public void update() {
        for (int i = 0; i < entities.size(); i++) { // For all entities in the level
            entities.get(i).update(); // Update entity state
        }

        for (int i = 0; i < tiles.length; i++) { // For all tiles in the level
            tiles[i].update(); // Update tile state
        }
    }

    // render method run every render cycle to render projectile state
    public void render(int xMove, int yMove, Screen screen) {
        screen.setOffset(xMove, yMove); // Render the level based on player movement position
        // Corner Pins (top left right and bottom left right corners to ensure only what is being seen on the screen is being rendered)
        int x0 = xMove >> 4; // Convert into TILE PRECISION | Shift by 4 (divided by 16(2^4))
        int x1 = (xMove + screen.getWidth() + 16) >> 4;
        int y0 = yMove >> 4;
        int y1 = (yMove + screen.getHeight() + 16) >> 4;

        for (int y = y0; y < y1; y++) { // For all tiles between the corners on the y axis
            for (int x = x0; x < x1; x++) { // For all tiles between the corners on the x axis
                getTile(x, y).render(x, y, screen); // Render each tile on the proper position in the screen
            }
        }
        for (Entity entity : entities) { // For all entities in the level
            entity.render(screen); // Render on screen
        }
    }

    /**
     * @return @{@link List<Entity>} list of entities in the level
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Adds an entity to the level.
     *
     * @param e entity
     */
    public void addEntity(Entity e) {
        if (entities == null) // If the entity list is null
            entities = new ArrayList<Entity>(); // Instantiate list
        entities.add(e); // Add entity to the list
    }

    /**
     * Removes an entity from the level.
     *
     * @param e entity
     */
    public void removeEntity(Entity e) {
        entities.remove(e); // Remove entity from list
    }

    /**
     * Sets the spawnpoints array manually.
     *
     * @param spawnPoints @{@link SpawnPoint[]}
     */
    public void setSpawnPoints(SpawnPoint[] spawnPoints) {
        this.spawnPoints = spawnPoints;
    }

    /**
     * @return @{@link SpawnPoint[]} array of the level
     */
    public SpawnPoint[] getSpawnPoints() {
        return spawnPoints;
    }

    /**
     * Specific Spawn point.
     *
     * @param number SpawnPoint number
     * @return @{@link SpawnPoint}
     */
    public SpawnPoint getSpawnPoint(int number) {
        return spawnPoints[number - 1];
    }

    /**
     * Random Spawn point.
     *
     * @return @{@link SpawnPoint}
     */
    public SpawnPoint getRandomSpawnPoint() {
        return spawnPoints[U.getRandomInt(0, spawnPoints.length - 1)];
    }

    /**
     * @param x
     * @param y
     * @return @{@link Tile}
     */
    public Tile getTile(int x, int y) {
        if (isLocationOutOfBounds(x, y))
            return TileType.AIR.getTileClass(); // If the tile is out of bounds just return AIR
        return tiles[x + y * width]; // Return tile at (x, y)
    }

    /**
     * @return Level @{@link Tile[]}
     */
    public Tile[] getTiles() {
        return tiles;
    }

    /**
     * Sets a tile at a specific location to another tile.
     *
     * @param x
     * @param y
     * @param @{@link Tile}
     */
    public void setTile(int x, int y, Tile tile) {
        if (isLocationOutOfBounds(x, y)) return; // Do not set a tile out of bounds
        tiles[x + y * width] = tile;
    }

    /**
     * @return location out of bounds status
     */
    public boolean isLocationOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || y >= height || x >= width); // Check Level boundries
    }

    /**
     * @return @{@link String} Level name
     */
    public String getName() {
        return name;
    }
}