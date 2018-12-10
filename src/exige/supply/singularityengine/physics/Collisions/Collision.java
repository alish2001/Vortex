package exige.supply.singularityengine.physics.Collisions;

/**
 * Collision Class. Allows for Collisions event values to be stored.
 *
 * @author Ali Shariatmadari
 */

public class Collision {

    private boolean collide = false;

    public Collision() {
    }

    public Collision(boolean doesCollide) {
        this.collide = doesCollide;
    }

    /**
     * Sets collision result
     *
     * @param result
     */
    public void setCollisionResult(boolean result) {
        this.collide = result;
    }

    /**
     * @return Collision result
     */
    public boolean doesCollide() {
        return collide;
    }

}