package exige.supply.singularityengine.entities;

import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.input.Keyboard;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.SpawnPoint;
import exige.supply.singularityengine.physics.Directions;

import java.awt.event.KeyEvent;

/**
 * Player Class. Allows Players to be created and has a default player template.
 * Subclass of Entity.
 *
 * @author Ali Shariatmadari
 */

public class Player extends Entity {

    protected PlayerCharacter character; // Player Character properties
    protected int[] coolDowns; // Cooldowns for abilities
    protected Keyboard input; // Player keyboard

    protected double health = 100.0; // Player health
    protected Directions direction = Directions.SOUTH; // Player direction
    protected final int SPEED = 1; // Player speed constant

    public Player(PlayerCharacter character, Level level, int numberOfCooldowns) {
        this.character = character;
        this.sprite = character.getSprite(direction);
        collidable = true;
        this.input = character.getKeys();
        this.level = level;
        this.coolDowns = new int[numberOfCooldowns];
        for (int i = 0; i < coolDowns.length; i++) {
            coolDowns[i] = 0;
        }
        spawn();
    }

    /**
     * Spawns the player inside the level
     */
    public void spawn() {
        SpawnPoint point = level.getRandomSpawnPoint(); // Get a random spawnpoint within the level
        // Spawn player at respective coordinates
        x = point.getX();
        y = point.getY();

        // If the player collides with an object or entity at the spawnpoint, offset spawn by 50 pixels on the x-axis
        if (calculateCollision(0, 0).doesCollide()) x += 50;
        addToLevel(); // Add player to level entities
    }

    /**
     * Moves the entity while taking into account collision, calculated separately on each axis.
     *
     * @param xMove Distance to move on the x-axis
     * @param yMove Distance to move on the y-axis
     */
    public void move(int xMove, int yMove) {

        direction = Directions.getDirection(xMove, yMove);

        xMove = xMove * SPEED;
        yMove = yMove * SPEED;

        // Split axis collision calculation
        if (!calculateCollision(xMove, 0).doesCollide()) { // If not colliding on the X-axis, move player on X-axis
            x += xMove;
        }

        if (!calculateCollision(0, yMove).doesCollide()) { // If not colliding on the X-axis, move player on Y-axis
            y += yMove;
        }
    }

    // update method run every update cycle to update the player state
    public void update() {
        sprite = character.getSprite(Directions.getGeneralDirection(direction)); // Set player sprite based on general direction

        for (int i = 0; i < coolDowns.length; i++) { // Loop through existing cooldowns
            if (coolDowns[i] > 0) // If cooldown isn't reset
                coolDowns[i]--; // Decrement it
        }

        // Check key presses
        checkInput();

        if (level.getEntities().contains(this) && isDead()) { // If the player's health is 0 and they are still in the level
            remove(); // remove them as they are dead
            onDeath(); // Run death procedure
        }
    }

    // render method run every render cycle to render player state
    public void render(Screen screen) {
        screen.renderSprite(x, y, sprite, false); // Render player sprite
    }

    // Death method run every time a player is identified as dead
    protected void onDeath() {
    }

    // Checks user inputs
    protected void checkInput() {
        // Move if respective movement keys are pressed
        int xa = 0, ya = 0; // Movement vars
        if (input.isPressed(KeyEvent.VK_W)) ya--;
        if (input.isPressed(KeyEvent.VK_S)) ya++;
        if (input.isPressed(KeyEvent.VK_A)) xa--;
        if (input.isPressed(KeyEvent.VK_D)) xa++;
        if (xa != 0 || ya != 0) move(xa, ya); // If movement values are not zero, move
    }

    /**
     * @return @{@link PlayerCharacter}
     */
    public PlayerCharacter getCharacter() {
        return character;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * @return Player Health
     */
    public double getHealth() {
        return health;
    }

    /**
     * @return Player Cooldowns Array
     */
    public int[] getCoolDowns() {
        return coolDowns;
    }

    /**
     * @return Alive state of player
     */
    public boolean isDead() {
        return health <= 0;
    }

}