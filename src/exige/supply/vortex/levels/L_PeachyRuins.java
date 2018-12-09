package exige.supply.vortex.levels;

import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.SpawnPoint;

public class L_PeachyRuins extends Level {

	public L_PeachyRuins() {
		super("Peachy Ruins","res/levels/PeachyRuins.png", 16);
        SpawnPoint[] spawnPoints = new SpawnPoint[2];
        spawnPoints[0] = new SpawnPoint(12, 20);
        spawnPoints[1] = new SpawnPoint(16, 16);
        setSpawnPoints(spawnPoints);
	}
}