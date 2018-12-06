package exige.supply.vortex.levels;

public class SpawnPoint {

	private final int LEVEL_CONST = 16; // TODO: Link to level builder
	private int x, y;
	
	public SpawnPoint(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public int getX() {
		return x * LEVEL_CONST;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y * LEVEL_CONST;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}