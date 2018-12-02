package exige.supply.vortex.engine;

import exige.supply.vortex.levels.Tile;
import exige.supply.vortex.sprites.Sprite;

public class Screen {

    private int width, height;
    private int[] pixels;
    private int xOffset, yOffset;

    private final int TRANSPARENT = 0xFFFF00FF;

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
        renderSprite(xp, yp, tile.getSprite());
    }

    public void renderSprite(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < sprite.getSize(); y++) {
            int yAbsolute = y + yp;
            for (int x = 0; x < sprite.getSize(); x++) {
                int xAbsolute = x + xp;
                if (xAbsolute < -sprite.getSize() || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break; // ONLY render what is on the screen
                if (xAbsolute < 0) xAbsolute = 0;

                int color = sprite.pixels[x + y * sprite.getSize()];

                // If pixel isn't transparent, render
                if (color != TRANSPARENT){
                    pixels[xAbsolute + yAbsolute * width] = color;
                }
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