package exige.supply.vortex.entities.players;

import exige.supply.singularityengine.entities.Player;
import exige.supply.singularityengine.entities.PlayerCharacter;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.graphics.sprites.SpritesManager;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.SpawnPoint;
import exige.supply.singularityengine.levels.Tile;
import exige.supply.singularityengine.physics.Collisions.TileCollision;
import exige.supply.vortex.entities.projectile.ExecutionerBullet;
import exige.supply.vortex.entities.projectile.VortexBullet;
import exige.supply.vortex.levels.tiles.VortexTile;

import java.awt.event.KeyEvent;

public class VortexPlayer extends Player {

    public VortexPlayer(PlayerCharacter character, Level level) {
        super(character, level, 3);
    }

    @Override
    protected void checkInput() {
        super.checkInput();
        if (input.isPressed(KeyEvent.VK_G)) shootPortal(1, new Sprite(6, 0, 8,3, 4,4, SpritesManager.getInstance().getSheet(1))); // Shoot Portal
        if (input.isPressed(KeyEvent.VK_F)) shootBullet(); // Shoot Bullet
    }

    @Override
    public void spawn(){
        SpawnPoint point = level.getSpawnPoint(1); // Get spawnpoint number 1 on the map
        // Set player coordinates to spawnpoint coordinates
        x = point.getX();
        y = point.getY();
        addToLevel(); // Add player to level
    }

    protected void shootBullet() {
        if (coolDowns[0] != 0) return; // If the cooldown hasn't expired, stop
        new ExecutionerBullet(level, x, y, direction).setOwner(this); // Shoot Executioner Bullet with player as the owner
        coolDowns[0] = ExecutionerBullet.COOLDOWN; // restart shooting cooldown
    }

    protected void shootPortal(int portalID, Sprite sprite) {
        if (coolDowns[1] != 0) return;  // If the cooldown hasn't expired, stop

        int playerPortalCount = 0;

        for (int i = 0; i < level.getTiles().length; i++) { // Loop through all level tiles to see how many active vortex tiles the player has
            Tile tile = level.getTiles()[i]; // Retrieve tile at index i from level

            if (!(tile instanceof VortexTile)) continue; // If the tile in the level is not a VortexTile, skip loop iteration
            VortexTile vortex = (VortexTile) tile;

            // If the IDs of the vortexes does not match the player ID, skip loop iteration
            if (vortex.getID() != portalID) continue;
            playerPortalCount++; // Otherwise, increment total active portal count for player
        }

        if (playerPortalCount >= 2) return; // If the player has more than 2 active, stop

        // Shoots portal with player as the owner
        new VortexBullet(sprite, portalID, level, x, y, direction).setOwner(this); // Shoot Vortex with player as the owner
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
            int xD = destinationVortex.getX()* level.TILE_CONST;
            int yD = destinationVortex.getY() * level.TILE_CONST;
            int xM = (destinationVortex.getExitDirection().getXPlacementOffset()) * level.TILE_CONST; // Convert from Tile to Pixel precision by multiplying by default level tile size
            int yM = (destinationVortex.getExitDirection().getYPlacementOffset()) * level.TILE_CONST; // Convert from Tile to Pixel precision by multiplying by default level tile size
            if (!calculateCollision(xD, yD, xM, yM).doesCollide()){
                x = xM + xD;
                y = yM + yD;
            }
            coolDowns[2] = VortexTile.COOLDOWN; // Reset Cooldown
        }
    }
}