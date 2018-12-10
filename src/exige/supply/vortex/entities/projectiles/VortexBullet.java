package exige.supply.vortex.entities.projectiles;

import exige.supply.singularityengine.entities.Player;
import exige.supply.singularityengine.entities.projectiles.Projectile;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.graphics.sprites.SpritesManager;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.physics.Collisions.EntityCollision;
import exige.supply.singularityengine.physics.Collisions.TileCollision;
import exige.supply.singularityengine.physics.Directions;
import exige.supply.vortex.levels.tiles.VortexTile;

/**
 * VortexBullet Class.
 * Handles the special properties of the Vortex Gun's VBullets.
 * Subclass of @{@link Projectile}
 *
 * @author Ali Shariatmadari
 */

public class VortexBullet extends Projectile {

    public static final int COOLDOWN = 60; // Cooldown between each use, 1 Seconds
    private int gracePeriod = 3; // The collision immunity period in UPS
    private int ID;

    public VortexBullet(Sprite sprite, int ID, Level level, int x, int y, Directions dir) {
        super(level, x, y, 5.0, dir.getAngle());
        init(dir, ID); // Initialize
        this.sprite = sprite; // Set sprite value
    }

    public VortexBullet(int ID, Level level, int x, int y, Directions dir) {
        super(level, x, y, 4.0, dir.getAngle());
        init(dir, ID); // Initialize
    }

    public VortexBullet(int ID, Level level, int x, int y, double angle) {
        super(level, x, y, 4.0, angle);
        Directions dir = Directions.getDirection(angle); // Get direction from given angle
        init(dir, ID); // Initialize
    }

    private void init(Directions dir, int ID) {
        renderRange = 500;
        damage = 20.0;
        sprite = new Sprite(10, 5, 0x000000);
        collidable = false; // make projectiles uncollidable for the first update cycle so it does not collide with the shooter
        if (dir == Directions.NORTH) this.y += 4; // Offset shooting by 4 pixels if to the north
        if (dir == Directions.EAST) this.x += 4; // Offset shooting by 4 pixels if to the east
        if (dir == Directions.WEST) this.x -= 4; // Offset shooting by 4 pixels if to the west
        if (dir == Directions.SOUTH_WEST) {
            this.y += 6;
            this.x -= 6;
        }// Offset shooting by 6 pixels down and left if shooting southwest

        this.ID = ID; // Assign Vortex ID
    }

    @Override
    protected void onEntityCollision(EntityCollision c) {
        if (c.getCollidedEntity() instanceof Player) { // If the collided entity is a player
            Player p = (Player) c.getCollidedEntity(); // Get the player by casting Player to the entity
            p.setHealth(p.getHealth() - damage); // Set their health accordingly with the damage of the projectiles
        }

        if (owner instanceof Player) { // If the owner of this projectiles is a player
            Player p = (Player) owner; // Get the player by casting Player to the owner
            p.setHealth(p.getHealth() + damage); // Steal the life of the one hit and add it to the hitter
        }
    }

    @Override
    protected void onTileCollision(TileCollision c) {
        // Retrieve the exit direction of objects exiting the vortex by retrieving the direction of opposite its launch
        Directions exitDirection = launchDirection;
        VortexTile vortex;

        if (ID == 1) {
            // Create vortex tile object for player 1
            vortex = new VortexTile(level, new Sprite(4, 0, 16, SpritesManager.getInstance().getSheet(1)), // Set Vortex sprite
                    exitDirection, // Set vortex Exit direction
                    ID, // Set desired ID
                    c.getX(), // Offset x coordinates
                    c.getY()); // Offset y coordinates
        } else {
            // Create vortex tile object for player 2
            vortex = new VortexTile(level, new Sprite(5, 0, 16, SpritesManager.getInstance().getSheet(1)), // Set Vortex sprite
                    exitDirection, // Set vortex Exit direction
                    ID, // Set desired ID
                    c.getX(), // Offset x coordinates
                    c.getY()); // Offset y coordinates
        }

        level.setTile(c.getX(), c.getY(), vortex); // Set the determined tile to a vortex tile
    }

    public void update() {
        super.update();
        if (collidable == false) { // If the object is still uncollidable
            if (gracePeriod == 0) collidable = true; // If the grace period has passed enable collision
            gracePeriod--; // Decrement grace period otherwise
        }
    }
}
