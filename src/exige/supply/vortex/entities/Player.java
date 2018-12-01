package exige.supply.vortex.entities;

import exige.supply.vortex.input.Keyboard;
import exige.supply.vortex.sprites.Sprite;

public class Player extends Entity {

    private final double SPEED = 1.0;

    private Sprite sprite;
    private int dir = 0;
    private boolean moving = false;

    private Keyboard input;

    public Player(Keyboard input) {
        this.input = input;
        y = 0;
        x = 0;
    }

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int xMove, int yMove) {
        dir = xMove + yMove * 3; // Calculate 8-way direction

        if (!collision()) { // If not colliding, move player
            x += xMove * SPEED;
            y += yMove * SPEED;
        }
    }

    public void update() {
        int xa = 0, ya = 0; // Movement vars
        if (input.up_W) ya--;
        if (input.down_S) ya++;
        if (input.left_A) xa--;
        if (input.right_D) xa++;
        if (xa != 0 || ya != 0) move(xa, ya); // If it is required to move, move
    }

    private boolean collision() {
        return false;
    }

}