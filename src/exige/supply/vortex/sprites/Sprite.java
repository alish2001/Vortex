package exige.supply.vortex.sprites;

public class Sprite {

	private SpriteSheet sheet;
	public final int SIZE;
	private int x, y;
	public int[] pixels;

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size; // x-Slot on the sheet
		this.y = y * size; // y-Slot on the sheet
		this.sheet = sheet;
		load();
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				// Extract sprite out of the sprite sheet by adding 
				pixels[x + y * SIZE] = sheet.pixels[(this.x + x) + (this.y + y) * sheet.SIZE];
			}

		}
	}
	
	public int getSize() {
		return SIZE;
	}
}