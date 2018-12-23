package exige.supply.vortex.entities.players;

import exige.supply.singularityengine.entities.PlayerCharacter;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.graphics.sprites.SpritesManager;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.SpawnPoint;
import exige.supply.vortex.VortexGame;

import java.awt.event.KeyEvent;

public class VortexPlayerTwo extends VortexPlayer {

    public VortexPlayerTwo(PlayerCharacter character, Level level) {
        super(character, level);
    }

    @Override // Overrides normal input checking
    protected void checkInput() {
        // shoot vortex with appropriate bullet sprite
        if (input.isPressed(KeyEvent.VK_CLOSE_BRACKET))
            shootVortex(2, new Sprite(7, 0, 8, 3, 4, 4, SpritesManager.getInstance().getSheet(1)));
        if (input.isPressed(KeyEvent.VK_OPEN_BRACKET)) shootBullet(new Sprite(9, 0, 8, 3, 4, 4, SpritesManager.getInstance().getSheet(1))); // Shoot Bullet with appropriate sprite

        // Move if respective movement keys are pressed
        int xa = 0, ya = 0; // Movement vars
        if (input.isPressed(KeyEvent.VK_I)) ya--;
        if (input.isPressed(KeyEvent.VK_K)) ya++;
        if (input.isPressed(KeyEvent.VK_J)) xa--;
        if (input.isPressed(KeyEvent.VK_L)) xa++;
        if (xa != 0 || ya != 0) move(xa, ya); // If movement values are not zero, move
    }

    @Override // Overrides normal spawning
    public void spawn() {
        SpawnPoint point = level.getSpawnPoint(2); // Get spawnpoint number 2 on the map
        // Set player coordinates to spawnpoint coordinates
        x = point.getX();
        y = point.getY();
        addToLevel(); // Add player to level
    }

    @Override
    protected void onDeath() { // Prompt a loss if player dies and ask for a replay
        VortexGame.promptReplay(true);
    }
}