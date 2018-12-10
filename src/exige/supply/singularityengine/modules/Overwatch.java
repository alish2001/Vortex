package exige.supply.singularityengine.modules;

import exige.supply.singularityengine.entities.Player;
import exige.supply.singularityengine.graphics.Screen;

/**
 * Overwatch Class.
 * Allows for @{@link Screen} offset adjustment based on @{@link Player} movement.
 *
 * @author Ali Shariatmadari
 */

public class Overwatch {

    private Screen screen;
    private Player[] players;
    private int x, y;

    public Overwatch(Screen screen, Player[] players) {
        this.screen = screen;
        this.players = players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    // Calculate camera x value based on the midpoint of players
    private void calculateX() {
        int x = 0;
        for (int i = 0; i < players.length; i++) { // Loop through all the players given
            x += players[i].getX(); // Add the x-coordinates of all players
        }
        x /= players.length; // Take the average(midpoint) on the x axis
        x -= screen.getWidth() / 2; // Offset to the center of the screen
        this.x = x; // Set new x value
    }

    public int getX() {
        calculateX();
        return x; // Return midpoint x-coordinate
    }

    // Calculate camera y value based on the midpoint of players
    public void calculateY() {
        int y = 0;
        for (int i = 0; i < players.length; i++) { // Loop through all the players given
            y += players[i].getY(); // Add the y-coordinates of all players
        }
        y /= players.length; // Take the average(midpoint) on the y axis
        y -= screen.getHeight() / 2; // Offset to the center of the screen
        this.y = y; // Set new y value
    }

    public int getY() {
        calculateY();
        return y; // Return midpoint y-coordinate
    }
}
