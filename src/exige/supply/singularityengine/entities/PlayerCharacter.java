package exige.supply.singularityengine.entities;

import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.graphics.sprites.SpritesManager;
import exige.supply.singularityengine.input.Keyboard;
import exige.supply.singularityengine.physics.Directions;

import java.awt.event.KeyEvent;

/**
 * PlayerCharacter Enumerator.
 * An Enumerator is a list of named constants. Here, it allows organization for keys and sprites for each player.
 *
 * @author Ali Shariatmadari
 */

public enum PlayerCharacter {

    // Stores each character with their keys and sprites linked to their nickname
    JACK(new Keyboard(new int[]{KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_F, KeyEvent.VK_G}),
            new Sprite[]{ // Create a new sprite array and hold the various directional sprites
                    new Sprite(0, 1, 16, 11, 0, 3, SpritesManager.getInstance().getSheet(1)),
                    new Sprite(0, 2, 16, 11, 0, 3, SpritesManager.getInstance().getSheet(1)),
                    new Sprite(0, 3, 16, 11, 0, 3, SpritesManager.getInstance().getSheet(1)),
                    new Sprite(0, 4, 16, 11, 0, 3, SpritesManager.getInstance().getSheet(1))}),

    JORDAN(new Keyboard(new int[]{KeyEvent.VK_I, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L, KeyEvent.VK_CLOSE_BRACKET, KeyEvent.VK_OPEN_BRACKET}),
            new Sprite[]{ // Create a new sprite array and hold the various directional sprites
            new Sprite(1, 1, 16, 11, 0, 3, SpritesManager.getInstance().getSheet(1)),
            new Sprite(1, 2, 16, 11, 0, 3, SpritesManager.getInstance().getSheet(1)),
            new Sprite(1, 3, 16, 11, 0, 3, SpritesManager.getInstance().getSheet(1)),
            new Sprite(1, 4, 16, 11, 0, 3, SpritesManager.getInstance().getSheet(1))});

    private Sprite[] sprites;
    private Keyboard keys;

    PlayerCharacter(Keyboard keys, Sprite[] sprites) {
        this.sprites = sprites;
        this.keys = keys;
    }

    /**
     * Returns sprite based on the @{@link Directions} given
     *
     * @param direction
     * @return @{@link Player} @{@link Sprite}*
     */
    public Sprite getSprite(Directions direction) {
        // Return sprite based on the direction the player is facing
        if (direction == Directions.SOUTH) return sprites[0];
        if (direction == Directions.EAST) return sprites[1];
        if (direction == Directions.WEST) return sprites[2];
        if (direction == Directions.NORTH) return sprites[3];
        return sprites[0]; // Return south by default
    }

    /**
     * @return @{@link Player} @{@link Keyboard}*
     */
    public Keyboard getKeys() {
        return keys;
    }
}