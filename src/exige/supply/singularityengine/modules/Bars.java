package exige.supply.singularityengine.modules;

import exige.supply.singularityengine.entities.Player;
import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.graphics.sprites.Sprite;

public class Bars {

    private Screen screen;
    private Player[] players;

    public Bars(Screen screen, Player[] players) {
        this.screen = screen;
        this.players = players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void render() {
        // Render player healthbar and cooldowns on each render cycle
        renderHealthbars();
        renderCooldowns();
    }

    private void renderCooldowns() {
        for (int i = 0; i < players.length; i++) { // Loop through all players
            for (int j = 0; j < players[i].getCoolDowns().length; j++) { // Loop through all cooldowns per player
                if (players[i].getCoolDowns()[j] == 0) continue; // If the cooldown is over, skip
                int renderWidth = players[i].getCoolDowns()[j] ; // Get the width of the cooldown bar based on the time left
                screen.renderSprite(0, 0, new Sprite(renderWidth, 3, 0xF51D37), players[i].getX() + 5, players[i].getY() - 6, false); // Render CD bar
            }
        }
    }

    private void renderHealthbars() {
        for (int i = 0; i < players.length; i++) { // Loop through all players
            if (players[i].isDead()) continue; // If the player is dead, skip
            int healthDisplay = (int) players[i].getHealth() / 4; // the display health width is the health of the player / 4
            screen.renderSprite(0, 0, new Sprite(27, 5, 0x000000), players[i].getX() + 5, players[i].getY() - 3, false); // Render black border
            screen.renderSprite(0, 0, new Sprite(25, 3, 0xF51D37), players[i].getX() + 4, players[i].getY() - 2, false); // Render Red underlayer
            screen.renderSprite(0, 0, new Sprite(healthDisplay, 3, 0x3EF51D), players[i].getX() + 4, players[i].getY() - 2, false); // Render Green health bar
        }
    }

}