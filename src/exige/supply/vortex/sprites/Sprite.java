package exige.supply.vortex.sprites;

import exige.supply.vortex.U;

public class Sprite {

	//TODO: make sprite using Color

	private SpriteSheet sheet;

	private int xSlot, ySlot;
	private int width, height;
	private int xOffset, yOffset = 0; // Default Offset = 0
    private int hitboxSize;
	public int[] pixels;

	public Sprite(int xSlot, int ySlot, int squareSize, SpriteSheet sheet) {
        width = squareSize;
        height = squareSize;
		pixels = new int[width * height];
		this.xSlot = xSlot * sheet.SLOT_SIZE; // First sprite pixel on the x-axis on the sheet
		this.ySlot = ySlot * sheet.SLOT_SIZE; // First sprite pixel on the y-axis on the sheet
		this.sheet = sheet;
        generateHitbox();
		load();
	}

    public Sprite(int xSlot, int ySlot, int width, int height, SpriteSheet sheet) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
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
        pixels = new int[width * height];
        this.xSlot = xSlot * sheet.SLOT_SIZE; // First sprite pixel on the x-axis on the sheet
        this.ySlot = ySlot * sheet.SLOT_SIZE; // First sprite pixel on the y-axis on the sheet
        this.sheet = sheet;
        generateHitbox();
        load();
    }

    public Sprite(int width, int height, int color) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = color; // Set each pixel to desired color
            }
        }
        generateHitbox();
    }

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Extract sprite out of the sprite sheet by adding 
				pixels[x + y * width] = sheet.pixels[(this.xSlot + x + xOffset) + (this.ySlot + y + yOffset) * sheet.SIZE];
			}
		}
	}

	private void generateHitbox(){
	    hitboxSize = U.getBiggerNumber(width, height);
	    if (width > height){
	        xOffset = 16 - width;
        } else {
	        yOffset = 16 - height;
        }
    }

    public int getHitboxSize() {
        return hitboxSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

}