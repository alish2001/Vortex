package exige.supply.vortex.levels;

import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.SpawnPoint;

/**
 * Peachy Ruins Level Class.
 * Stores the properties of the Peachy Ruins level
 * Subclass of @{@link Level}
 *
 * @author Ali Shariatmadari
 */

public class L_PeachyRuins extends Level {

    public L_PeachyRuins() {
        super("Peachy Ruins", "levels/PeachyRuins.png", 16); // Store level file path

        // Set level SpawnPoints
        SpawnPoint[] spawnPoints = new SpawnPoint[2];
        spawnPoints[0] = new SpawnPoint(12, 20);
        spawnPoints[1] = new SpawnPoint(16, 16);
        setSpawnPoints(spawnPoints);
    }
}