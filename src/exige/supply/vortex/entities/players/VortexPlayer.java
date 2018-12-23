package exige.supply.vortex.entities.players;

import exige.supply.singularityengine.entities.Player;
import exige.supply.singularityengine.entities.PlayerCharacter;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.graphics.sprites.SpritesManager;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.SpawnPoint;
import exige.supply.singularityengine.levels.Tile;
import exige.supply.singularityengine.physics.Collisions.TileCollision;
import exige.supply.vortex.VortexGame;
import exige.supply.vortex.entities.projectiles.ExecutionerBullet;
import exige.supply.vortex.entities.projectiles.VortexBullet;
import exige.supply.vortex.levels.tiles.VortexTile;

import java.awt.event.KeyEvent;

/**
 * VortexPlayer Class. Holds the properties of a vortex game player.
 * Subclass of @{@link Player}
 * Protected methods & fields in this class can only be used by itself and its child classes.
 *
 * @author Ali Shariatmadari
 */

public class VortexPlayer extends Player {

    public VortexPlayer(PlayerCharacter character, Level level) {
        super(character, level, 3); // Set number of tracked cooldowns to 3
    }

    @Override
    protected void checkInput() { // Check player key input
        super.checkInput();
        // shoot vortex with appropriate bullet sprite
        if (input.isPressed(KeyEvent.VK_G))
            shootVortex(1, new Sprite(6, 0, 8, 3, 4, 4, SpritesManager.getInstance().getSheet(1)));
        // Get bullet sprite for player one); // Shoot Bullet with appropriate sprite
        if (input.isPressed(KeyEvent.VK_F)) shootBullet(new Sprite(8, 0, 8, 3, 4, 4, SpritesManager.getInstance().getSheet(1)));
    }

    @Override
    public void spawn() { // Spawn player into level
        SpawnPoint point = level.getSpawnPoint(1); // Get spawnpoint number 1 on the map
        // Set player coordinates to spawnpoint coordinates
        x = point.getX();
        y = point.getY();
        addToLevel(); // Add player to level
    }

    @Override
    protected void onDeath() { // Prompt a loss if player dies and ask for a replay
        VortexGame.promptReplay(false);
    }

    // Shoots executioner bullet
    protected void shootBullet(Sprite bullet) {
        if (coolDowns[0] != 0) return; // If the cooldown hasn't expired, stop
        new ExecutionerBullet(bullet, level, x, y, direction).setOwner(this); // Shoot Executioner Bullet with player as the owner
        coolDowns[0] = ExecutionerBullet.COOLDOWN; // restart shooting cooldown
    }

    // Shoots vortex bullet
    protected void shootVortex(int vortexID, Sprite sprite) {
        if (coolDowns[1] != 0) return;  // If the cooldown hasn't expired, stop

        int playerVortexCount = 0;

        for (int i = 0; i < level.getTiles().length; i++) { // Loop through all level tiles to see how many active vortex tiles the player has
            Tile tile = level.getTiles()[i]; // Retrieve tile at index i from level

            if (!(tile instanceof VortexTile))
                continue; // If the tile in the level is not a VortexTile, skip loop iteration
            VortexTile vortex = (VortexTile) tile;

            // If the IDs of the vortexes does not match the player ID, skip loop iteration
            if (vortex.getID() != vortexID) continue;
            playerVortexCount++; // Otherwise, increment total active vortex count for player
        }

        if (playerVortexCount >= 2) return; // If the player has more than 2 active, stop

        // Shoots vortex with player as the owner
        new VortexBullet(sprite, vortexID, level, x, y, direction).setOwner(this); // Shoot Vortex with player as the owner
        coolDowns[1] = VortexBullet.COOLDOWN; // restart shooting cooldown
    }

    @Override
    protected void onTileCollision(TileCollision c) { // Check when player collides with a tile
        if (!(c.getCollidedTile() instanceof VortexTile)) return; // If the tile collided was not a Vortex, ignore
        if (coolDowns[2] != 0) return; // If the player vortex cooldown has not passed, stop
        VortexTile entranceVortex = (VortexTile) c.getCollidedTile(); // Otherwise, Instantiate entrance VortexTile as the entrance
        VortexTile destinationVortex; // Declare destination tile
        for (int i = 0; i < level.getTiles().length; i++) { // Loop through all level tiles to find the EXIT vortex tile
            Tile tile = level.getTiles()[i]; // Retrieve tile at index i from level

            if (!(tile instanceof VortexTile))
                continue; // If the tile in the level is not a VortexTile, skip loop iteration
            destinationVortex = (VortexTile) tile;

            // If the x and y coordinates of the vortexes match, skip loop iteration
            if (destinationVortex.getX() == entranceVortex.getX() && destinationVortex.getY() == entranceVortex.getY())
                continue;
            // If the IDs of the vortexes don't match, skip loop iteration
            if (destinationVortex.getID() != entranceVortex.getID()) continue;

            // Teleport player by changing their x and y location value if all conditions are met
            direction = destinationVortex.getExitDirection(); // Set direction to the vortex's exit direction
            // Get destination coordinates
            int xD = destinationVortex.getX() * level.TILE_CONST;
            int yD = destinationVortex.getY() * level.TILE_CONST;
            int xM = (destinationVortex.getExitDirection().getXPlacementOffset()) * level.TILE_CONST; // Convert from Tile to Pixel precision by multiplying by default level tile size
            int yM = (destinationVortex.getExitDirection().getYPlacementOffset()) * level.TILE_CONST; // Convert from Tile to Pixel precision by multiplying by default level tile size
            if (!calculateCollision(xD, yD, xM, yM).doesCollide()) { // If no collision happens on the other side of the vortex, teleport
                x = xM + xD;
                y = yM + yD;
            }
            coolDowns[2] = VortexTile.COOLDOWN; // Reset Vortex Cooldown
        }
    }
}