package exige.supply.singularityengine.physics.Collisions;

import exige.supply.singularityengine.levels.Tile;

/**
 * TileCollision Class. Allows for Tile based Collision event values to be stored.
 * Subclass of @{@link Collision}
 *
 * @author Ali Shariatmadari
 */

public class TileCollision extends Collision {

    private Tile collidedTile;
    private int x, y;

    public TileCollision(Tile collidedTile, int x, int y){
        setCollisionResult(true);
        this.collidedTile = collidedTile;
        this.x = x;
        this.y = y;
    }

    /**
     * Set the collided entity
     *
     * @param tile @{@link Tile}
     */
    public void setCollidedTile(Tile tile) {
        this.collidedTile = tile;
    }

    /**
     * @return Collided @{@link Tile}
     */
    public Tile getCollidedTile() {
        return collidedTile;
    }

    /**
     * @return x coordinate of @{@link Tile}
     */
    public int getX() {
        return x;
    }

    /**
     * @return y coordinate of @{@link Tile}
     */
    public int getY() {
        return y;
    }
}