package exige.supply.singularityengine.levels;

import exige.supply.singularityengine.levels.tiles.TileType;

/**
 * Random Level Class.
 * Generates a random level of random tiles.
 * Subclass of @{@link Level}
 *
 * @author Ali Shariatmadari
 */

public class RandomLevel extends Level {

    public RandomLevel(int width, int height) {
        super("Random", width, height, 16); // Set tile size to 16

        // Set one spawnpoint at (10, 10)
        SpawnPoint[] spawnPoints = new SpawnPoint[1];
        spawnPoints[0] = new SpawnPoint(10, 10);
        setSpawnPoints(spawnPoints);
        generate(); // Generate random level
    }

    // Generates random level
    private void generate() {
        // For all tiles
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setTile(x, y, TileType.getRandomType().getTileClass()); // Set a random tile
            }
        }
    }
}