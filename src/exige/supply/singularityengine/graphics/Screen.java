package exige.supply.singularityengine.graphics;

import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Tile;

/**
 * Screen Entity Class. Allows for @{@link Sprite}s and @{@link Tile}s to be rendered.
 *
 * @author Ali Shariatmadari
 */

public class Screen {

    private int width, height;
    private int[] pixels;
    private int xOffset, yOffset;

    private final int TRANSPARENT = 0xFFFF00FF; // Transparency constant, this color will be ignored as trasnparent.

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[this.width * this.height]; // Create pixel array
    }

    /**
     * Sets all the pixels values in the level to 0, clearing them.
     */
    public void clear() {
        for (int i = 0; i < pixels.length; i++) { // Loop through every single pixel
            pixels[i] = 0; // Set it to 0 value
        }
    }

    /**
     * Renders a tile at desired x and y position.
     *
     * @param xPos    x Position
     * @param yPos    y Position
     * @param @{@link Tile}
     */
    public void renderTile(int xPos, int yPos, Tile tile) {
        renderSprite(xPos, yPos, tile.getSprite(), false);
    }

    /**
     * Renders a @{@link Sprite} at desired x and y position, fixed or unfixed.
     *
     * @param xPos    x Position
     * @param yPos    y Position
     * @param @{@link Sprite}
     * @param fixed
     */
    public void renderSprite(int xPos, int yPos, Sprite sprite, boolean fixed) {
        renderSprite(xPos, yPos, sprite, 0, 0, fixed);
    }

    /**
     * Renders a @{@link Sprite} at desired x and y position, x and y offset, fixed or unfixed.
     *
     * @param xPos     x Position
     * @param yPos     y Position
     * @param @{@link  Sprite}
     * @param x_Offset x Offset
     * @param y_Offset y Offset
     * @param fixed
     */
    public void renderSprite(int xPos, int yPos, Sprite sprite, int x_Offset, int y_Offset, boolean fixed) {
        if (!fixed) { // If not fixed(does not stick on screen)
            // Apply render offset accordingly
            xPos -= xOffset;
            yPos -= yOffset;
        }

        yPos += sprite.getYOffset() + y_Offset; // Center sprite loading by adding its sprite and local y offset
        xPos += sprite.getXOffset() + x_Offset; // Center sprite loading by adding its sprite and local x offset

        for (int y = 0; y < sprite.getHeight(); y++) {
            int yAbsolute = y + yPos; // Account for sprite loading y offset
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xAbsolute = x + xPos; // Account for sprite loading x offset

                if (xAbsolute < -sprite.getWidth() // If the vortex has to procedurally generate more than ONE FULL TILE
                        || xAbsolute >= width // If the vortex is passed the width of the screen
                        || yAbsolute < 0 // If the vortex is below the max height of the screen
                        || yAbsolute >= height) // If the vortex has to render past the bottom of the screen
                    break; // ONLY render what is on the screen, ignore

                if (xAbsolute < 0) // If the vortex has to a tile on the x-axis procedurally
                    xAbsolute = 0; // Assume the tile render starts from tile origin (top left pixel)

                int color = sprite.pixels[x + y * sprite.getWidth()]; // Retrieve color value for specific pixel from sprite

                if (color != TRANSPARENT) { // If pixel is not considered transparent
                    pixels[xAbsolute + yAbsolute * width] = color; // Write sprite color value into screen pixel array
                }
            }
        }
    }

    /**
     * Sets render offset position.
     *
     * @param xOffset x Offset
     * @param yOffset y Offset
     */
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * @return Screen pixels array
     */
    public int[] getPixels() {
        return pixels;
    }

    /**
     * @return Screen width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return Screen height
     */
    public int getHeight() {
        return height;
    }
}