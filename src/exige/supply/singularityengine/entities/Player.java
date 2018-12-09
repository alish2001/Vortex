package exige.supply.singularityengine.entities;

import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.input.Keyboard;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.SpawnPoint;
import exige.supply.singularityengine.physics.Collisions.EntityCollision;
import exige.supply.singularityengine.physics.Directions;
import exige.supply.vortex.Main;

import java.awt.event.KeyEvent;

public class Player extends Entity {

    protected PlayerCharacter character; // TODO: ANIMATE PLAYER
    protected int[] coolDowns;
    protected Keyboard input;

    protected double health = 100.0;
    protected Directions direction = Directions.SOUTH;
    protected final int SPEED = 1;

    public Player(PlayerCharacter character, Level level, int numberOfCooldowns) {
        this.character = character;
        this.sprite = character.getSprite();
        collidable = true;
        this.input = character.getKeys();
        this.level = level;
        this.coolDowns = new int[numberOfCooldowns];
        for (int i = 0; i < coolDowns.length; i++) {
            coolDowns[i] = 0;
        }
        spawn();
    }

    public void spawn() {
        SpawnPoint point = level.getRandomSpawnPoint();
        x = point.getX();
        y = point.getY();

        // If the player collides with an object or entity at the spawnpoint, offset spawn by 50 pixels on the x-axis
        if (calculateCollision(0, 0).doesCollide()) x += 50;
        addToLevel(); // Add player to level entities
    }

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

    public void update() {
        for (int i = 0; i < coolDowns.length; i++) { // Loop through existing cooldowns
            if (coolDowns[i] > 0) // If cooldown isn't reset
                coolDowns[i]--; // Decrement it
        }
        // Key Moves
        checkInput();

        if (isDead()) remove(); // If the player's health is 0, remove them as they are dead
    }

    public void render(Screen screen) {
        screen.renderSprite(x, y, character.getSprite(), false);
    }

    protected void checkInput() {
        if (input.isPressed(KeyEvent.VK_ESCAPE)) Main.renderer.close();

        int xa = 0, ya = 0; // Movement vars
        if (input.isPressed(KeyEvent.VK_W)) ya--;
        if (input.isPressed(KeyEvent.VK_S)) ya++;
        if (input.isPressed(KeyEvent.VK_A)) xa--;
        if (input.isPressed(KeyEvent.VK_D)) xa++;
        if (xa != 0 || ya != 0) move(xa, ya); // If it is required to move, move
    }

    @Override
    protected void onEntityCollision(EntityCollision c) {
        if (!(c.getCollidedEntity() instanceof Player)) return;
        Directions oppositeDir = Directions.getOppositeDirection(direction);
        /*x += oppositeDir.getXPlacementOffset() * 16;
        y += oppositeDir.getYPlacementOffset() * 16;*/
    }

    public PlayerCharacter getCharacter() {
        return character;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealth() {
        return health;
    }

    public int[] getCoolDowns(){
        return coolDowns;
    }

    public boolean isDead(){
        return health <= 0;
    }

}