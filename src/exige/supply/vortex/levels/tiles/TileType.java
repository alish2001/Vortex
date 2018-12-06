package exige.supply.vortex.levels.tiles;

import exige.supply.vortex.U;
import exige.supply.vortex.levels.Tile;
import exige.supply.vortex.sprites.Sprite;
import exige.supply.vortex.sprites.SpritesManager;

public enum TileType {

	// Different Tile Types
    AIR(0xFFFFFFFF, new AirTile(new Sprite(3, 0, 16, SpritesManager.getInstance().getSheet(1)))),
    PEACHY_FLOOR(0xFFFFAA00, new FloorTile(new Sprite(0, 0, 16, SpritesManager.getInstance().getSheet(1)))),
    DIRT_FLOOR(0xFF4A1E00, new FloorTile(new Sprite(1, 0, 16, SpritesManager.getInstance().getSheet(1)))),
    CERAMIC_FLOOR(0xFFAAAAAA, new FloorTile(new Sprite(2, 0, 16, SpritesManager.getInstance().getSheet(1)))),
    CERAMIC_WALL(0xFFAAAAA1, new WallTile(new Sprite(2, 0, 16, SpritesManager.getInstance().getSheet(1))));

    private int ID;
    private Tile tileClass;

    TileType(int ID, Tile tileClass) {
        this.ID = ID;
        this.tileClass = tileClass;
    }

    public static TileType getTypeFromID(int ID){
        for (TileType s : TileType.values()){
            if (s.ID == ID) {
                return s;
            }
        }
        return AIR;
    }

    public static TileType getRandomType(){
        return values()[U.getRandomInt(0, values().length - 1)];
    }

    public int getID() {
        return ID;
    }

    // Redundant
    public Sprite getSpriteClass() {
        return tileClass.getSprite();
    }

    public Tile getTileClass() {
        return tileClass;
    }
}