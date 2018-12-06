package exige.supply.vortex.levels.pack;

import exige.supply.vortex.levels.Level;
import exige.supply.vortex.levels.SpawnPoint;

public class L_PeachyRuins extends Level {

	public L_PeachyRuins() {
		super("Peachy Ruins","res/levels/PeachyRuins.png", 16);
        SpawnPoint[] spawnPoints = new SpawnPoint[1];
        spawnPoints[0] = new SpawnPoint(10, 10);
        setSpawnPoints(spawnPoints);
	}
}