package exige.supply.vortex.entities;

import exige.supply.vortex.engine.Screen;
import exige.supply.vortex.engine.physics.Collisions.Collision;
import exige.supply.vortex.engine.physics.Directions;
import exige.supply.vortex.entities.projectiles.ExecutionerBullet;
import exige.supply.vortex.input.Keyboard;
import exige.supply.vortex.levels.Level;
import exige.supply.vortex.levels.SpawnPoint;

import java.awt.event.KeyEvent;

public class Player extends Entity {

    private PlayerCharacter character; // TODO: ANIMATE PLAYER
    private int[] coolDowns;
    private Keyboard input;

    private Directions direction = Directions.SOUTH;
    private final int SPEED = 1;
    private boolean walking = false;

    public Player(PlayerCharacter character, Level level, int numberOfProjectiles) {
        this.character = character;
        this.sprite = character.getSprite();
        collidable = true;
        this.input = character.getKeys();
        this.level = level;
        this.coolDowns = new int[numberOfProjectiles];
        for (int i = 0; i < coolDowns.length; i++) {
            coolDowns[i] = 0;
        }
        spawn();
    }

    public void spawn(){
        SpawnPoint point = level.getRandomSpawnPoint();
        x = point.getX();
        y = point.getY();
        // If the player collides with an object or entity at the spawnpoint, offset spawn by 50 pixels on the x-axis
        if (calculateCollision(0,0).doesCollide()) x += 50;
        addToLevel(); // Add player to level entities
    }

    public void move(int xMove, int yMove) {

        direction = Directions.getDirection(xMove, yMove);

        xMove = xMove *  SPEED;
        yMove = yMove * SPEED;

        if (!calculateCollision(xMove, 0).doesCollide()) { // If not colliding on the X-axis, move player on X-axis
            x += xMove;
            walking = true;
        }

        if (!calculateCollision(0, yMove).doesCollide()) { // If not colliding on the X-axis, move player on Y-axis
            y += yMove;
            walking = true;
        } else {
            walking = false;
        }
    }

    private void shoot() {
        // Shoots with player as the owner
        new ExecutionerBullet(level, x, y, direction).setOwner(this);
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
        if (character == PlayerCharacter.JACK) {
            int xa = 0, ya = 0; // Movement vars
            if (input.isPressed(KeyEvent.VK_W)) ya--;
            if (input.isPressed(KeyEvent.VK_S)) ya++;
            if (input.isPressed(KeyEvent.VK_A)) xa--;
            if (input.isPressed(KeyEvent.VK_D)) xa++;
            if (xa != 0 || ya != 0) move(xa, ya); // If it is required to move, move
        }
    }

    public void render(Screen screen) {
        screen.renderSprite(x, y, character.getSprite(), false);
    }

    public void controllerCheck(){

    }

    public PlayerCharacter getCharacter() {
        return character;
    }
}