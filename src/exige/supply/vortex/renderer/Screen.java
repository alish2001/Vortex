package exige.supply.vortex.renderer;

import java.util.Random;

public class Screen {

    private int width, height;
    private int[] pixels;

    private int[] tiles = new int[4096]; // Map tiles array 64 * 64

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[this.width * this.height]; // Create pixel array

         for (int i = 0; i < 4096; i++){
             tiles[i] = random.nextInt(0xffffff); // Generate random color from white to black spectrum
         }

    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tileIndex = (x / 32) + (y / 32) * 64; // Extrapolate tile index from screen pixels | x / 16 is x shifted to the right by 5
                pixels[x + y * width] = tiles[tileIndex];
            }
        }

    }

    public int[] getPixels() {
        return pixels;
    }
}