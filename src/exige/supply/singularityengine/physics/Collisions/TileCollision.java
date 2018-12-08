package exige.supply.singularityengine.physics.Collisions;

import exige.supply.singularityengine.levels.Tile;

public class TileCollision extends Collision {

    private Tile collidedTile;
    private int x, y;

    public TileCollision(Tile collidedTile, int x, int y){
        setCollisionResult(true);
        this.collidedTile = collidedTile;
        this.x = x;
        this.y = y;
    }

    public void setCollidedTile(Tile tile) {
        this.collidedTile = tile;
    }

    public Tile getCollidedTile() {
        return collidedTile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}