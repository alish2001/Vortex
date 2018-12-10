package exige.supply.singularityengine.graphics.sprites;

import exige.supply.singularityengine.U;

/**
 * Sprite Class. Allows for Sprites to be created.
 *
 * @author Ali Shariatmadari
 */

public class Sprite {

    private SpriteSheet sheet; // Spritesheet that holds sprites

    private int xSlot, ySlot;
    private int width, height;
    private int xOffset, yOffset = 0; // Default Offset = 0
    private int hitboxSize;
    public int[] pixels; // pixels that make up the sprite

    public Sprite(int xSlot, int ySlot, int squareSize, SpriteSheet sheet) {
        width = squareSize;
        height = squareSize;
        pixels = new int[width * height]; // Create pixels array based on sprite size
        this.xSlot = xSlot * sheet.SLOT_SIZE; // First sprite pixel on the x-axis on the sheet
        this.ySlot = ySlot * sheet.SLOT_SIZE; // First sprite pixel on the y-axis on the sheet
        this.sheet = sheet;
        hitboxSize = squareSize; // Set hitbox to square size, no need for generation
        load();
    }

    public Sprite(int xSlot, int ySlot, int width, int height, SpriteSheet sheet) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height]; // Create pixels array based on sprite size
        this.xSlot = xSlot * sheet.SLOT_SIZE; // First sprite pixel on the x-axis on the sheet
        this.ySlot = ySlot * sheet.SLOT_SIZE; // First sprite pixel on the y-axis on the sheet
        this.sheet = sheet;
        generateHitbox();
        load();
    }

    public Sprite(int xSlot, int ySlot, int width, int height, int xOffset, int yOffset, SpriteSheet sheet) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = width;
        this.height = height;
        pixels = new int[width * height]; // Create pixels array based on sprite size
        this.xSlot = xSlot * sheet.SLOT_SIZE; // First sprite pixel on the x-axis on the sheet
        this.ySlot = ySlot * sheet.SLOT_SIZE; // First sprite pixel on the y-axis on the sheet
        this.sheet = sheet;
        generateHitbox();
        load();
    }

    public Sprite(int width, int height, int color) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height]; // Create pixels array based on sprite size

        for (int i = 0; i < pixels.length; i++) { // Loop through every pixel
            pixels[i] = color; // Set each pixel to desired color
        }
        generateHitbox();
    }

    // Load sprite pixels from spritesheet
    private void load() {
        for (int y = 0; y < height; y++) { // Loop through the pixel rows
            for (int x = 0; x < width; x++) { // Loop through the pixel columns
                // Extract sprite out of the sprite sheet
                pixels[x + y * width] = sheet.pixels[(this.xSlot + x + xOffset) + (this.ySlot + y + yOffset) * sheet.SIZE];
            }
        }
    }

    // Generates hitbox based on dimensions
    private void generateHitbox() {
        hitboxSize = U.getBiggerNumber(width, height);
        if (width > height) {
            xOffset = 16 - width;
        } else {
            yOffset = 16 - height;
        }
    }

    /**
     * @return Hitbox Size
     */
    public int getHitboxSize() {
        return hitboxSize;
    }

    /**
     * @return Sprite Width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return Sprite Height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return Sprite xOffset
     */
    public int getXOffset() {
        return xOffset;
    }

    /**
     * @return Sprite yOffset
     */
    public int getYOffset() {
        return yOffset;
    }

}