package exige.supply.vortex.sprites;

import java.util.ArrayList;

public class SpritesManager {

    private static SpritesManager manager = new SpritesManager();

    private ArrayList<SpriteSheet> sheets;

    private SpritesManager() {
        sheets = new ArrayList<SpriteSheet>();
        loadSheets();
    }

    public static SpritesManager getInstance() {
        if (manager == null)
            manager = new SpritesManager();
        return manager;
    }

    private void loadSheets() {
        addSheet(new SpriteSheet("res/textures/spritesheet.png", 256));
    }

    public SpriteSheet getSheet(int sheetNumber) {
        return sheets.get(sheetNumber - 1);
    }

    public void addSheet(SpriteSheet sheet) {
        sheets.add(sheet);
    }
}