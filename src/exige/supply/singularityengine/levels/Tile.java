package exige.supply.singularityengine.levels;

import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.graphics.sprites.Sprite;

/**
 * Tile Class. Allows different Tiles to be created.
 *
 * @author Ali Shariatmadari
 */

public class Tile {

    protected Level level;
    private Sprite sprite;
    private boolean solid;

    public Tile(Sprite sprite, boolean solid) {
        this.sprite = sprite;
        this.solid = solid;
    }

    // render method run every render cycle to render tile state
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this); // Shift back to pixel precision
    }

    // update method run every update cycle to update the tile state
    public void update() {

    }

    /**
     * Sets the @{@link Level} the tile is located in.
     * @param level @{@link Level}
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * @return @{@link Level} of the tile
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return Solidity of the tile
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * @return @{@link Sprite} of the tile
     */
    public Sprite getSprite() {
        return sprite;
    }

}