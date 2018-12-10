package exige.supply.singularityengine.entities.projectiles;

import exige.supply.singularityengine.entities.Entity;
import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.physics.Collisions.Collision;
import exige.supply.singularityengine.physics.Directions;
import exige.supply.singularityengine.physics.Vector;

/**
 * Abstract Projectile Class. Allows projectiles to be created.
 * Subclass of Entity.
 * <p>
 * An abstract class is a class that can't be instantiated. It's only purpose is for other classes to extend.
 * Abstract methods in this class must be override by child classes them as they have no body.
 *
 * @author Ali Shariatmadari
 */

public abstract class Projectile extends Entity {

    protected Entity owner;
    protected Vector vector;

    protected final int xOrigin, yOrigin;
    protected final Directions launchDirection;
    protected double renderRange, damage;

    public Projectile(Level level, int x, int y, double speed, double angle) {
        this.level = level;
        xOrigin = x;
        yOrigin = y;
        this.vector = new Vector(speed, angle, false);
        this.launchDirection = Directions.getDirection(angle); // Set launch direction based on angle given
        this.x = x;
        this.y = y;
        addToLevel(); // Add projectile to level
    }

    public Projectile(Level level, int x, int y, Vector vector) {
        this.level = level;
        xOrigin = x;
        yOrigin = y;
        this.launchDirection = vector.getDirection(); // Set launch direction based on vector given
        this.vector = vector;
        this.x = x;
        this.y = y;
        addToLevel(); // Add projectile to level
    }

    @Override
    public void update() { // update method run every update cycle to update the projectile state
        double newX = vector.getXComponent(); // Calculate the value to add to x to move it
        double newY = vector.getYComponent(); // Calculate the value to add to y to move it
        move(newX, newY); // Attempt movement

        if (getPixelDistanceTravelled() > renderRange) remove(); // If projectiles is past render range, remove
    }

    @Override
    public void render(Screen screen) { // render method run every render cycle to render projectile state
        screen.renderSprite(x, y, sprite, false); // render projectile
    }

    @Override
    protected void onCollision(Collision c) { // Collision method run every time a collision happens
        remove(); // Remove if projectiles collides
    }

    /**
     * Returns the distance travelled by projectile.
     *
     * @return Distance travelled.
     */
    public double getPixelDistanceTravelled() {
        return Math.sqrt(Math.pow(xOrigin - x, 2) + Math.pow(yOrigin - y, 2)); // Sqrt(A^2 + B^2) = C (Pythagorean Theorem)
    }

    /**
     * Sets the owner of the projectile.
     *
     * @param entity Owner
     */
    public void setOwner(Entity entity) {
        this.owner = entity;
    }

    /**
     * Returns the entity that owns the projectile.
     *
     * @return Entity Projectile owner
     */
    public Entity getOwner() {
        return owner;
    }

}
