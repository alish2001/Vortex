package exige.supply.singularityengine.levels;

/**
 * SpawnPoint Class.
 * Allows storing of Spawn Points for @{@link Level}s.
 *
 * @author Ali Shariatmadari
 */

public class SpawnPoint {

	private final int LEVEL_CONST = 16; // TODO: Link to level builder
	private int x, y;
	
	public SpawnPoint(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

    /**
     * @return X Coordinate in PIXEL precision
     */
	public int getX() {
		return x * LEVEL_CONST;
	}

    /**
     * Sets x-coordinate for spawnpoint
     * @param x
     */
	public void setX(int x) {
		this.x = x;
	}

    /**
     * @return Y Coordinate in PIXEL precision
     */
	public int getY() {
		return y * LEVEL_CONST;
	}

    /**
     * Sets y-coordinate for spawnpoint
     * @param y
     */
	public void setY(int y) {
		this.y = y;
	}
	
}