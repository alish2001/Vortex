package exige.supply.vortex.entities.players;

import exige.supply.singularityengine.entities.PlayerCharacter;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.graphics.sprites.SpritesManager;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.SpawnPoint;

import java.awt.event.KeyEvent;

public class VortexPlayerTwo extends VortexPlayer {

    public VortexPlayerTwo(PlayerCharacter character, Level level){
        super(character, level);
    }

    @Override
    protected void checkInput(){
        if (input.isPressed(KeyEvent.VK_NUMPAD2)) shootPortal(2, new Sprite(7, 0, 8,3, 4,4, SpritesManager.getInstance().getSheet(1))); // Shoot Portal
        if (input.isPressed(KeyEvent.VK_NUMPAD1)) shootBullet(); // Shoot Bullet

        int xa = 0, ya = 0; // Movement vars
        if (input.isPressed(KeyEvent.VK_UP)) ya--;
        if (input.isPressed(KeyEvent.VK_DOWN)) ya++;
        if (input.isPressed(KeyEvent.VK_LEFT)) xa--;
        if (input.isPressed(KeyEvent.VK_RIGHT)) xa++;
        if (xa != 0 || ya != 0) move(xa, ya); // If it is required to move, move
    }

    @Override
    public void spawn(){
        SpawnPoint point = level.getSpawnPoint(2); // Get spawnpoint number 2 on the map
        // Set player coordinates to spawnpoint coordinates
        x = point.getX();
        y = point.getY();
        addToLevel(); // Add player to level
    }
}