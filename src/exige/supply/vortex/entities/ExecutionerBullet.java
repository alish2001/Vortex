package exige.supply.vortex.entities;

import exige.supply.vortex.engine.Screen;
import exige.supply.vortex.levels.tiles.TileType;

public class ExecutionerBullet extends Projectile {

    public static final int COOLDOWN = 50;

    public ExecutionerBullet(int x, int y, double dir){
        super(x, y, dir); // TODO: MORE SUPER?!

        damage = 20.0;
        speed = 4.0;
        angle = dir;
        newX = speed * Math.cos(angle);
        newY = speed * Math.sin(angle);
        renderRange = 200;
    }

    public void update(){
        x += newX;
        y += newY;
        if (getDistanceTravelled() > renderRange) remove(); // If projectile is past render range, remove
    }

    public void render(Screen screen){
        screen.renderSprite((int) x, (int) y, TileType.DIRT_FLOOR.getSpriteClass());
    }

}