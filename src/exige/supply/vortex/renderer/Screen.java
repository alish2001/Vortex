package exige.supply.vortex.renderer;

import java.util.Random;

import exige.supply.vortex.sprites.Sprites;

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
			int yOff = y + yOffset;
			if (yOff < 0 || yOff >= height) continue;
			for (int x = 0; x < width; x++) {
				int xOff = x + xOffset;
				if (xOff < 0 || xOff >= width) continue;
				int size = Sprites.GRASS.getSpriteClass().SIZE;
				pixels[xOff +  yOff * width] = Sprites.GRASS.getSpriteClass().pixels[(x & size-1) + (y & size-1) * size];
			}
		}

	}

	public int[] getPixels() {
		return pixels;
	}
}