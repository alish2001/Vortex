package exige.supply.vortex.entities.projectiles;

import exige.supply.vortex.engine.Screen;
import exige.supply.vortex.engine.physics.Collisions.Collision;
import exige.supply.vortex.engine.physics.Vector;
import exige.supply.vortex.entities.Entity;
import exige.supply.vortex.levels.Level;

public abstract class Projectile extends Entity {

    protected Entity owner;
    protected Vector vector;

    protected final int xOrigin, yOrigin;
    protected double renderRange, damage;

    public Projectile(Level level, int x, int y, double speed, double angle) {
        this.level = level;
        xOrigin = x;
        yOrigin = y;
        this.vector = new Vector(speed, angle, false);
        this.x = x;
        this.y = y;
        addToLevel();
    }

    public Projectile(Level level, int x, int y, Vector vector) {
        this.level = level;
        xOrigin = x;
        yOrigin = y;
        this.vector = vector;
        this.x = x;
        this.y = y;
        addToLevel();
    }

    public void update() {
        double newX = vector.getXComponent(); // Calculate the value to add to x to move it
        double newY = vector.getYComponent(); // Calculate the value to add to y to move it
        move(newX, newY); // Attempt movement

        if (getPixelDistanceTravelled() > renderRange) remove(); // If projectile is past render range, remove
    }

    public void render(Screen screen) {
        screen.renderSprite(x, y, sprite, false);
    }

    @Override
    protected void onCollision(Collision c) {
        remove(); // Remove if projectile collides
    }

    public double getPixelDistanceTravelled() {
        return Math.sqrt(Math.pow(xOrigin - x, 2) + Math.pow(yOrigin - y, 2)); // Sqrt(A^2 + B^2) = C (Pythagorean Theorem)
    }

    public void setOwner(Entity entity) {
        this.owner = entity;
    }

    public Entity getOwner() {
        return owner;
    }

}
