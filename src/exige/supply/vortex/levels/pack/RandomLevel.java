package exige.supply.vortex.levels.pack;

import exige.supply.vortex.levels.Level;
import exige.supply.vortex.levels.SpawnPoint;
import exige.supply.vortex.levels.tiles.TileType;

public class RandomLevel extends Level {

    public RandomLevel(int width, int height) {
        super("Random", width, height, 16);
        SpawnPoint[] spawnPoints = new SpawnPoint[1];
        spawnPoints[0] = new SpawnPoint(100, 100);
        setSpawnPoints(spawnPoints);
    }

    protected void generate() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setTile(x, y, TileType.getRandomType().getTileClass());
            }
        }
    }
}