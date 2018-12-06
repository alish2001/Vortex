package exige.supply.vortex.entities.projectiles;

import exige.supply.vortex.engine.Screen;
import exige.supply.vortex.levels.Level;
import exige.supply.vortex.sprites.Sprite;

public class ExecutionerBullet extends Projectile {

    public static final int COOLDOWN = 10;

    public ExecutionerBullet(Level level, int x, int y, double angle) {
        super(level, x, y, 4.0, angle);
        collidable = true;
        renderRange = 200;
        damage = 20.0;
        sprite = new Sprite(10, 5, 0x000000);

    }

    public void update() {

        double newX = vector.getXComponent();
        double newY = vector.getYComponent();
        if (!doesCollide(x + newX, y + newY, sprite)) {
            x += newX;
            y += newY;
        } else {
            //remove();
        }
        if (getPixelDistanceTravelled() > renderRange) remove(); // If projectile is past render range, remove entity
    }

    public void render(Screen screen) {
        screen.renderSprite(x, y, sprite, false);
    }

}