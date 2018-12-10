package exige.supply.singularityengine.levels.tiles;

import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Tile;

/**
 * FloorTile Class. Holds the properties of a Floor Tile.
 * Subclass of @{@link Tile}
 *
 * @author Ali Shariatmadari
 */

public class FloorTile extends Tile {

    public FloorTile(Sprite sprite) {
        super(sprite, false); // Set solidity to false
    }

}