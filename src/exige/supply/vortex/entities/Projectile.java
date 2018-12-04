package exige.supply.vortex.entities;

import exige.supply.vortex.sprites.Sprite;

public abstract class Projectile extends Entity {

    // TODO: UNPROTECTED PLZ !?
    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double x, y, newX, newY;
    protected double speed, renderRange, damage;

    public Projectile(int x, int y, double dir) {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
    }

    public double getDistanceTravelled() {
        return Math.sqrt(Math.pow(xOrigin - x, 2) + Math.pow(yOrigin - y, 2)); // Sqrt(A^2 + B^2) = C (Pythagorean Theorem)
    }

    public double getRenderRange(){
        return renderRange;
    }
}
