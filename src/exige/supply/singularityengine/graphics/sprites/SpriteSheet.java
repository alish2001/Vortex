package exige.supply.singularityengine.graphics.sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * SpriteSheet Class. Allows SpriteSheets to be created.
 *
 * @author Ali Shariatmadari
 */

public class SpriteSheet {

    private String path;
    public final int SLOT_SIZE = 16; // Size of each sprite slot on the sprite sheet
    public final int SIZE;
    public int[] pixels;

    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE]; // Generate sheet pixels array based on sheet size
        load();
    }

    // Loads the spritesheet into memeory
    private void load() {
        try {
            URL sheetFile = this.getClass().getClassLoader().getResource(path); // Get the SpriteSheet address
            BufferedImage image = ImageIO.read(sheetFile); // Read the buffered image from the address
            int w = image.getWidth(); // Get the width of the sheet
            int h = image.getHeight(); // Get the height of the sheet
            image.getRGB(0, 0, w, h, pixels, 0, w); // write RGB value of pixels in the sheet into pixels[]
        } catch (IOException e) { // Catch exception in case a fileIO error takes place.
            e.printStackTrace();
        }
    }
}
