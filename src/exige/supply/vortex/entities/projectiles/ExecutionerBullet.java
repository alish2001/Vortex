package exige.supply.vortex.entities.projectiles;

import exige.supply.singularityengine.entities.Player;
import exige.supply.singularityengine.entities.projectiles.Projectile;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.Tile;
import exige.supply.singularityengine.physics.Collisions.Collision;
import exige.supply.singularityengine.physics.Collisions.EntityCollision;
import exige.supply.singularityengine.physics.Collisions.TileCollision;
import exige.supply.singularityengine.physics.Directions;
import exige.supply.vortex.levels.tiles.VortexTile;

/**
 * ExecutionerBullet Class.
 * Handles the special properties of the Executioner Gun's bullet.
 * Subclass of @{@link Projectile}
 *
 * @author Ali Shariatmadari
 */

public class ExecutionerBullet extends Projectile {

    public static final int COOLDOWN = 20; // Cooldown between each use, 1 Seconds
    private int gracePeriod = 3; // The collision immunity period in UPS

    public ExecutionerBullet(Sprite sprite, Level level, int x, int y, Directions dir) {
        super(level, x, y, 4.0, dir.getAngle());
        init(dir);
        this.sprite = sprite;
    }

    public ExecutionerBullet(Level level, int x, int y, Directions dir) {
        super(level, x, y, 4.0, dir.getAngle());
        init(dir);
    }

    public ExecutionerBullet(Level level, int x, int y, double angle) {
        super(level, x, y, 4.0, angle);
        Directions dir = Directions.getDirection(angle); // Get direction from given angle
        init(dir);
    }

    private void init(Directions dir) { // Initialize default values
        renderRange = 500;
        damage = 10.0;
        sprite = new Sprite(10, 5, 0x000000);
        collidable = false; // make projectiles uncollidable for the first update cycle so it does not collide with the shooter
        if (dir == Directions.NORTH) this.y += 4; // Offset shooting by 4 pixels if to the north
        if (dir == Directions.EAST) this.x += 4; // Offset shooting by 4 pixels if to the east
        if (dir == Directions.WEST) this.x -= 4; // Offset shooting by 4 pixels if to the west
        if (dir == Directions.SOUTH_WEST) {
            this.y += 6;
            this.x -= 6;
        }// Offset shooting by 6 pixels down and left if shooting southwest
    }

    public void update() { // Update projectile state
        super.update();
        if (collidable == false) {  // If the object is still uncollidable
            if (gracePeriod == 0) collidable = true; // If the grace period has passed enable collision
            gracePeriod--; // Decrement grace period otherwise
        }
    }

    @Override
    protected void onEntityCollision(EntityCollision c) {
        if (c.getCollidedEntity() instanceof Player) { // If the collided entity is a player
            Player p = (Player) c.getCollidedEntity(); // Get the player by casting Player to the entity
            p.setHealth(p.getHealth() - damage); // Set their health accordingly with the damage of the projectiles
        }
        remove();
    }

    @Override
    protected void onCollision(Collision c) {

    }

    @Override
    protected void onTileCollision(TileCollision c) { // Check when player collides with a tile
        if (c.getCollidedTile() instanceof VortexTile) { // If the tile collided was a Vortex
            VortexTile entranceVortex = (VortexTile) c.getCollidedTile(); // Otherwise, Instantiate entrance VortexTile as the entrance
            VortexTile destinationVortex; // Declare destination tile
            for (int i = 0; i < level.getTiles().length; i++) { // Loop through all level tiles to find the EXIT vortex tile
                Tile tile = level.getTiles()[i]; // Retrieve tile at index1111 i from level

                if (!(tile instanceof VortexTile))
                    continue; // If the tile in the level is not a VortexTile, skip loop iteration
                destinationVortex = (VortexTile) tile;

                // If the x and y coordinates of the vortexes match, skip loop iteration
                if (destinationVortex.getX() == entranceVortex.getX() && destinationVortex.getY() == entranceVortex.getY())
                    continue;
                // If the IDs of the vortexes don't match, skip loop iteration
                if (destinationVortex.getID() != entranceVortex.getID()) continue;

                // Teleport player by changing their x and y location value if all conditions are met
                // direction = destinationVortex.getExitDirection(); // Set direction to the vortex's exit direction

                Directions exitRev = destinationVortex.getExitDirection();
                x = (destinationVortex.getX() + exitRev.getXPlacementOffset()) * level.TILE_CONST; // Convert from Tile to Pixel precision by multiplying by default level tile size
                y = (destinationVortex.getY() + exitRev.getYPlacementOffset()) * level.TILE_CONST; // Convert from Tile to Pixel precision by multiplying by default level tile size
                vector.setDegreeAngle(Directions.getOppositeDirection(exitRev).getAngle()); // Change the trajectory of the projectiles to the opposite of its exit direction
            }
        } else {
            remove(); // Remove entity if the tile was not a vortex
        }
    }
}