package exige.supply.singularityengine.entities.projectiles;

import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.physics.Directions;

public class ExecutionerBullet extends Projectile {

    public static final int COOLDOWN = 20;
    private int gracePeriod = 3;

    public ExecutionerBullet(Level level, int x, int y, Directions dir) {
        super(level, x, y, 4.0, dir.getAngle());
        renderRange = 200;
        damage = 20.0;
        sprite = new Sprite(10, 5, 0x000000);
        collidable = false; // make projectile uncollidable for the first update cycle so it does not collide with the shooter
        if (dir == Directions.EAST) this.x += 4; // Offset shooting by 4 pixels if to the east
        if (dir == Directions.WEST) this.x -= 4; // Offset shooting by 4 pixels if to the west
    }

    public ExecutionerBullet(Level level, int x, int y, double angle) {
        super(level, x, y, 4.0, angle);
        renderRange = 200;
        damage = 20.0;
        sprite = new Sprite(10, 5, 0x000000);
        collidable = false; // make projectile uncollidable for the first update cycle so it does not collide with the shooter
        if (Directions.getDirection(angle) == Directions.EAST) this.x += 4; // Offset shooting by 4 pixels if to the east
        if (Directions.getDirection(angle) == Directions.WEST) this.x -= 4; // Offset shooting by 4 pixels if to the west
    }

    public void update() {
        super.update();
        if (collidable == false) {
            if (gracePeriod == 0) collidable = true; // If the grace period has passed enable collision
            gracePeriod--; // Decrement grace period otherwise
        }
    }
}