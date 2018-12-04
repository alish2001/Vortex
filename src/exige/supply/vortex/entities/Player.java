package exige.supply.vortex.entities;

import exige.supply.vortex.engine.Screen;
import exige.supply.vortex.input.Keyboard;
import exige.supply.vortex.levels.Level;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

    private PlayerCharacter character = PlayerCharacter.JACK; // TODO: ANIMATE PLAYER
    private List<Entity> boundEntities = new ArrayList<Entity>();
    private int[] coolDowns;
    private Keyboard input; // TODO: SOMETHING WITH GAMEENGINE

    private final double SPEED = 1.0;
    private int dir = 0;
    private boolean walking = false;

    public Player(Level level, Keyboard input, int numberOfProjectiles) {
        this.input = input;
        this.level = level;
        y = +50;
        x = +120;

        this.coolDowns = new int[numberOfProjectiles];
        for (int i = 0; i < coolDowns.length; i++) {
            coolDowns[i] = 0;
        }
    }

    public void move(int xMove, int yMove) {
        dir = xMove + yMove * 3; // Calculate 8-way direction

        if (!doesCollide(xMove, 0)) { // If not colliding on the X-axis, move player on X-axis
            x += xMove * SPEED;
            walking = true;
        }

        if (!doesCollide(0, yMove)) { // If not colliding on the X-axis, move player on Y-axis
            y += yMove * SPEED;
            walking = true;
        } else {
            walking = false;
        }
    }

    private void shoot() {
        // TODO: Window Height/Width methods...???
        // TODO: FIX DIRECTIONAL SHOOTING
        int dx = Math.abs(x  /*- (GameEngine.WIDTH * GameEngine.SCALE / 2)*/);
        int dy = Math.abs(y  /*-(GameEngine.HEIGHT * GameEngine.SCALE / 2)*/);
        double dir = Math.atan2(dy, dx);

        Projectile p = new ExecutionerBullet(dx, dy, dir);
        boundEntities.add(p);
        level.addEntity(p);
        coolDowns[0] = ExecutionerBullet.COOLDOWN; // Reset cooldown
    }

    public void update() {

        // Shoot
        if (input.isPressed(KeyEvent.VK_F) && (coolDowns[0]) <= 0) {
            shoot();

        }

        for (int i = 0; i < coolDowns.length; i++) { // Decrease cooldowns
            if (coolDowns[i] > 0) // If cooldown isn't reset
                coolDowns[i]--; // Subtract
        }

        // Key Moves
        int xa = 0, ya = 0; // Movement vars
        if (input.isPressed(KeyEvent.VK_W)) ya--;
        if (input.isPressed(KeyEvent.VK_S)) ya++;
        if (input.isPressed(KeyEvent.VK_A)) xa--;
        if (input.isPressed(KeyEvent.VK_D)) xa++;
        if (xa != 0 || ya != 0) move(xa, ya); // If it is required to move, move

        // Entity Remover
        //TODO: SEEMLESS SPLICING?!
        for (int i = 0; i < boundEntities.size(); i++) {
            if (boundEntities.get(i) instanceof Projectile) { // If the entity is a subclass of projectile
                Projectile p = (Projectile) boundEntities.get(i); // Cast type to projectile
                if (p.isRemoved()) { // If the projectile has been removed (past render range)
                    level.removeEntity(p); // Remove from render if out of range
                    boundEntities.remove(i); // Remove from bound entities if out of range
                }
            }
        }
    }

    public void render(Screen screen) {
        screen.renderSprite(x, y, character.getSprite());
    }

    private boolean doesCollide(int xPos, int yPos) {

        boolean collide = false;

        // Corner Collision Calculation
        for (int corner = 0; corner < 4; corner++) { // Check all 4 corners
            int xC_Offset = character.getxOffset(); // Retrieve sprite x-offset (from the left)
            int yC_Offset = character.getyOffset(); // Retrieve sprite y-offset (from the top)

            int xC_Width = character.getWidth() - 1; // Calculate collision width
            int yC_Width = character.getHeight() - 1; // Calculate collision height

            // TODO: DRAW DIAGRAM.....
            int xC_Exp = corner % 2 * xC_Width + xC_Offset; // Left and right corner check expression
            int yC_Exp = corner / 2 * yC_Width + yC_Offset; // Top and bottom corner check expression

            int xC = ((x + xPos) + xC_Exp) / character.getSprite().getSize(); // Retrieve possible colliding tile on the X-axis (convert from pixel to tile precision by diving by Sprite size)
            int yC = ((y + yPos) + yC_Exp) / character.getSprite().getSize(); // Retrieve possible colliding tile on the Y-axis (convert from pixel to tile precision by diving by Sprite size)

            // TODO: GET GAMEENGINE PROPERLY
            if (level.getTile(xC, yC).isSolid())
                collide = true; // If the tile at xC and yC is a solid, set collide to true

        }

        return collide; // Return calculation result
    }

}