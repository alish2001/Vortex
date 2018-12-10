package exige.supply.singularityengine.graphics.sprites;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton SpriteSheetManager Class. A single instance allows for management of all sprites.
 * A singleton is a class that allows only a single instance of itself to be created and gives access to that created instance.
 *
 * @author Ali Shariatmadari
 */

public class SpritesManager {

    private static SpritesManager manager = new SpritesManager(); // SpritesManager singleton instance
    private List<SpriteSheet> sheets;

    // Private constructor disables the creation of other instances
    private SpritesManager() {
        sheets = new ArrayList<SpriteSheet>();
        loadSheets(); // Load all the SpriteSheets
    }

    /**
     * Returns the Singleton instance of @{@link SpritesManager}.
     *
     * @return @{@link SpritesManager}
     */
    public static SpritesManager getInstance() {
        if (manager == null) // If no instance exists
            manager = new SpritesManager(); // Create an new instance
        return manager; // Return singleton instance
    }

    // Loads the SpriteSheets
    private void loadSheets() {
        addSheet(new SpriteSheet("textures/spritesheet.png", 256));
    }

    /**
     * Returns the SpriteSheet with the given ID.
     *
     * @param sheetNumber
     * @return @{@link SpriteSheet}
     */
    public SpriteSheet getSheet(int sheetNumber) {
        return sheets.get(sheetNumber - 1);
    }

    /**
     * Adds @{@link SpriteSheet} to the @{@link SpritesManager}
     *
     * @param @{@link SpriteSheet}
     */
    public void addSheet(SpriteSheet sheet) {
        sheets.add(sheet);
    }
}