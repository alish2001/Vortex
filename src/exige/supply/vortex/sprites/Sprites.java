package exige.supply.vortex.sprites;

import exige.supply.vortex.levels.Tile;
import exige.supply.vortex.levels.tiles.AirTile;
import exige.supply.vortex.levels.tiles.FloorTile;

public enum Sprites {

    AIR(0, new Sprite(16, 3, 0, SpritesManager.getInstance().getSheet(1)), new AirTile(new Sprite(16, 3, 0, SpritesManager.getInstance().getSheet(1)))),
    PEACHY_FLOOR(1, new Sprite(16, 0, 0, SpritesManager.getInstance().getSheet(1)), new FloorTile(new Sprite(16, 0, 0, SpritesManager.getInstance().getSheet(1)))),
    DIRT(2, new Sprite(16, 1, 0, SpritesManager.getInstance().getSheet(1)), new FloorTile(new Sprite(16, 1, 0, SpritesManager.getInstance().getSheet(1)))),
    CERAMIC(3, new Sprite(16, 2, 0, SpritesManager.getInstance().getSheet(1)), new FloorTile(new Sprite(16, 2, 0, SpritesManager.getInstance().getSheet(1))));

    private int ID;
    private Sprite spriteClass;
    private Tile tileClass;

    Sprites(int ID, Sprite spriteClass, Tile tileClass) {
        this.ID = ID;
        this.spriteClass = spriteClass;
        this.tileClass = tileClass;
    }

    public static Sprites getSpriteFromID(int ID){
        for (Sprites s : Sprites.values()){
            if (s.ID == ID) {
                return s;
            }
        }
        return AIR;
    }

    public int getID() {
        return ID;
    }

    public Sprite getSpriteClass() {
        return spriteClass;
    }

    public Tile getTileClass() {
        return tileClass;
    }
}