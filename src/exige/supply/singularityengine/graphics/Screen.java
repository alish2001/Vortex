package exige.supply.singularityengine.graphics;

import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Tile;

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
        renderSprite(xp, yp, tile.getSprite(), false);
    }

    public void renderSprite(int xPos, int yPos, Sprite sprite, boolean fixed) {
        renderSprite(xPos, yPos, sprite, 0, 0, fixed);
    }

    public void renderSprite(int xPos, int yPos, Sprite sprite, int x_Offset, int y_Offset, boolean fixed) {
        if (!fixed) { // If not fixed(does not stick on screen), apply render offset
            xPos -= xOffset;
            yPos -= yOffset;
        }

        yPos += sprite.getYOffset() + y_Offset; // Center sprite loading by adding its sprite and local y offset
        xPos += sprite.getXOffset() + x_Offset; // Center sprite loading by adding its sprite and local x offset

        for (int y = 0; y < sprite.getHeight(); y++) {
            int yAbsolute = y + yPos;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xAbsolute = x + xPos;// Account for sprite loading x offset

                if(xAbsolute < -sprite.getWidth() // If the renderer has to procedurally generate more than ONE FULL TILE
                        || xAbsolute >= width // If the renderer is passed the width of the screen
                        || yAbsolute < 0 // If the renderer is below the max height of the screen
                        || yAbsolute >= height) // If the renderer has to render past the bottom of the screen
                    break; // ONLY render what is on the screen

                if (xAbsolute < 0) // If the renderer has to a tile on the x-axis procedurally
                    xAbsolute = 0; // Assume the tile render starts from tile origin (top left pixel)

                int color = sprite.pixels[x + y * sprite.getWidth()]; // Retrieve color value for specific pixel from sprite

                if (color != TRANSPARENT){ // If pixel is not considered transparent
                    pixels[xAbsolute + yAbsolute * width] = color; // Write sprite color value into screen pixel array
                }
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}