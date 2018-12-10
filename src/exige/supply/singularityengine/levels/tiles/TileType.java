package exige.supply.singularityengine.levels.tiles;

import exige.supply.singularityengine.U;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.graphics.sprites.SpritesManager;
import exige.supply.singularityengine.levels.Tile;

/**
 * TileType Enumerator.
 * An Enumerator is a list of named constants. Here, it allows organization for the different Tile types for each level and the associated Sprite and identification colors.
 *
 * @author Ali Shariatmadari
 */

public enum TileType {

    // Different Tile Types, their ID, and their Tile class
    AIR(0xFFFFFFFF, new AirTile(new Sprite(3, 0, 16, SpritesManager.getInstance().getSheet(1)))),
    PEACHY_FLOOR(0xFFFFAA00, new FloorTile(new Sprite(0, 0, 16, SpritesManager.getInstance().getSheet(1)))),
    DIRT_FLOOR(0xFF4A1E00, new FloorTile(new Sprite(1, 0, 16, SpritesManager.getInstance().getSheet(1)))),
    DIRT_WALL(0xFF4A1E01, new WallTile(new Sprite(1, 0, 16, SpritesManager.getInstance().getSheet(1)))),
    CERAMIC_FLOOR(0xFFAAAAAA, new FloorTile(new Sprite(2, 0, 16, SpritesManager.getInstance().getSheet(1)))),
    CERAMIC_WALL(0xFFAAAAA1, new WallTile(new Sprite(2, 0, 16, SpritesManager.getInstance().getSheet(1))));

    private int ID; // The color used to identify the tile and load levels
    private Tile tileClass; // The tile property class associated with the tile

    TileType(int ID, Tile tileClass) {
        this.ID = ID;
        this.tileClass = tileClass;
    }

    /**
     * Returns the @{@link TileType} from its ID.
     *
     * @return @{@link TileType} basd on ID.
     */
    public static TileType getTypeFromID(int ID) {
        for (TileType s : TileType.values()) { // Loop through all TileTypes
            if (s.ID == ID) { // If the ID matches
                return s; // Return the TileType associated with the ID
            }
        }
        return AIR; // If nothing matches, return AIR.
    }

    /**
     * @return Random @{@link TileType}
     */
    public static TileType getRandomType() {
        return values()[U.getRandomInt(0, values().length - 1)];
    }

    /**
     * @return Tile ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return @{@link Tile} class
     */
    public Tile getTileClass() {
        return tileClass;
    }
}