package exige.supply.vortex.entities;

import exige.supply.vortex.Main;
import exige.supply.vortex.engine.Screen;
import exige.supply.vortex.input.Keyboard;

public class Player extends Entity {

    private final double SPEED = 1.0;

    private PlayerCharacter character = PlayerCharacter.JACK;
    private int dir = 0;
    private boolean moving = false;

    private Keyboard input;

    public Player(Keyboard input) {
        this.input = input;
        y = +50;
        x = +120;
    }

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int xMove, int yMove) {
        dir = xMove + yMove * 3; // Calculate 8-way direction

        if (!doesCollide(xMove, 0)) { // If not colliding on the X-axis, move player on X-axis
            x += xMove * SPEED;
        }

        if (!doesCollide(0, yMove)) { // If not colliding on the X-axis, move player on Y-axis
            y += yMove * SPEED;
        }
    }

    public void update() {
        //TODO: CLEANUP UPDATE WITH KEYBOARD LINK
        int xa = 0, ya = 0; // Movement vars
        if (input.up_W) ya--;
        if (input.down_S) ya++;
        if (input.left_A) xa--;
        if (input.right_D) xa++;
        if (xa != 0 || ya != 0) move(xa, ya); // If it is required to move, move
    }

    public void render(Screen screen) {
        screen.renderSprite(x, y, character.getSprite());
    }

    private boolean doesCollide(int xPos, int yPos) {
        //TODO: GET GAMEENGINE PROPERLY
        boolean solid = false;
        // Corner Collision Calculation
        for (int corner = 0; corner < 4; corner++) {
            int xC_Offset = 0;
            int yC_Offset = 3;

            int xC_Width = character.getSprite().SIZE - 1;
            int yC_Width = character.getSprite().SIZE - 6;

            int xC_Exp = corner % 2 * xC_Width + xC_Offset;
            int yC_Exp = corner / 2 * yC_Width + yC_Offset;

            int xC = ((x + xPos) + xC_Exp) / 16;
            int yC = ((y + yPos) + yC_Exp) / 16;

            if (Main.renderer.getLevel().getTile(xC, yC).isSolid()) solid = true;

        }

        return solid;
    }

}