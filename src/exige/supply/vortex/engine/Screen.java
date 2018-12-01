package exige.supply.vortex.engine;

import exige.supply.vortex.levels.Tile;

public class Screen {

    private int width, height;
    private int[] pixels;
    private int xOffset, yOffset;

    public final int MAP_SIZE = 64;
    public final int MAP = MAP_SIZE * MAP_SIZE;
    public final int MAP_MASK = MAP_SIZE - 1;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[this.width * this.height]; // Create pixel array
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.getSprite().getSize(); y++) {
            int yAbsolute = y + yp;
            for (int x = 0; x < tile.getSprite().getSize(); x++) {
                int xAbsolute = x + xp;
                if (xAbsolute < -tile.getSprite().getSize() || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break; // ONLY render what is on the screen
                if (xAbsolute < 0) xAbsolute = 0;
                pixels[xAbsolute + yAbsolute * width] = tile.getSprite().pixels[x + y * tile.getSprite().getSize()];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getWidth (){
        return width;
    }

    public int getHeight() {
        return height;
    }
}