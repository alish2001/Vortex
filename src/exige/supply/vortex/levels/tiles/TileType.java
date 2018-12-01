package exige.supply.vortex.levels.tiles;

import exige.supply.vortex.levels.Tile;
import exige.supply.vortex.sprites.Sprite;
import exige.supply.vortex.sprites.SpritesManager;

public enum TileType {

    AIR(0, new AirTile(new Sprite(16, 3, 0, SpritesManager.getInstance().getSheet(1)))),
    PEACHY_FLOOR(1, new FloorTile(new Sprite(16, 0, 0, SpritesManager.getInstance().getSheet(1)))),
    DIRT_FLOOR(2, new FloorTile(new Sprite(16, 1, 0, SpritesManager.getInstance().getSheet(1)))),
    CERAMIC_FLOOR(3, new FloorTile(new Sprite(16, 2, 0, SpritesManager.getInstance().getSheet(1))));

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