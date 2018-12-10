package exige.supply.singularityengine.levels.tiles;

import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Tile;

/**
 * WallTile Class. Holds the properties of a Wall Tile.
 * Subclass of @{@link Tile}
 *
 * @author Ali Shariatmadari
 */

public class WallTile extends Tile {

    public WallTile(Sprite sprite) {
        super(sprite, true); // Set solidity to true
    }
}
