package exige.supply.vortex.levels;

public class SpawnPoint {

	private int x, y;
	
	public SpawnPoint(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getTileIndex(int width) {
		return x + y * width;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}