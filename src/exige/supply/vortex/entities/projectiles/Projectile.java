package exige.supply.vortex.entities.projectiles;

import exige.supply.vortex.entities.Entity;
import exige.supply.vortex.levels.Level;
import exige.supply.vortex.sprites.Sprite;

public abstract class Projectile extends Entity {

    // TODO: UNPROTECTED PLZ !?
    protected Entity owner;
    protected Sprite sprite;
    protected Vector vector;

    protected final int xOrigin, yOrigin;

    protected int x, y;
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


    public double getPixelDistanceTravelled() {
        return Math.sqrt(Math.pow(xOrigin - x, 2) + Math.pow(yOrigin - y, 2)); // Sqrt(A^2 + B^2) = C (Pythagorean Theorem)
    }

    public void setOwner(Entity entity){
        this.owner = entity;
    }

    public Entity getOwner(){
        return owner;
    }

}
