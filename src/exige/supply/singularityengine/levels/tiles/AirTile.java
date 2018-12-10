package exige.supply.singularityengine.levels.tiles;

import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Tile;

/**
 * AirTile Class. Holds the properties of an AIR Tile.
 * Subclass of @{@link Tile}
 *
 * @author Ali Shariatmadari
 */

public class AirTile extends Tile {

    public AirTile(Sprite sprite) {
        super(sprite, true); // Set solidity to true
    }
}