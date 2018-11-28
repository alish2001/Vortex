package exige.supply.vortex.renderer;

import java.util.Random;

public class Screen {

	private int width, height;
	private int[] pixels;

	public final int MAP_SIZE = 64;
	public final int MAP = MAP_SIZE * MAP_SIZE;
	public final int MAP_MASK = MAP_SIZE - 1;
	
	private int[] tiles = new int[MAP]; // Map tiles array

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[this.width * this.height]; // Create pixel array

		for (int i = 0; i < MAP; i++) {
			tiles[i] = random.nextInt(0xffffff); // Generate random color from white to black spectrum
		}

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yy = y + yOffset;
			for (int x = 0; x < width; x++) {
				int xx = x + xOffset;
				// Extrapolate tile index from screen pixels | x / 16 is x shifted to the right
				// by 4 | bitwise &, when the result of the shift is above 63, bring back to 0
				int tileIndex = ((xx >> 4) & MAP_MASK) + ((yy >> 4) & MAP_MASK) * MAP_SIZE;
				pixels[x + y * width] = tiles[tileIndex];
			}
		}

	}

	public int[] getPixels() {
		return pixels;
	}
}