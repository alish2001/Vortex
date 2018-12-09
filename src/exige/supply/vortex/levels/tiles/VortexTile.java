package exige.supply.vortex.levels.tiles;

import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.Tile;
import exige.supply.singularityengine.physics.Directions;

public class VortexTile extends Tile {

    public static final int LIFESPAN = 10 * 60; // Life span of the tile
    public static final int COOLDOWN = 30; // Cooldown between each usage, 30 UPS = 0.5 seconds
    private int life;
    private Tile tileBelow;

    private final int ID;
    private Directions exitDirection;
    private int x, y;

    public VortexTile(Level level, Sprite sprite, Directions exitDirection, int id, int x, int y) {
        super(sprite, true);
        this.exitDirection = exitDirection;
        this.ID = id;
        this.x = x;
        this.y = y;
        setLevel(level); // Set tile level
        tileBelow = level.getTile(x, y); // Save the tile at the same x and y location before the vortextile was created
        life = 0; // reset lifespan
    }

    public void update() {
        if (life != LIFESPAN) { // If the lifespan of the tile has still not ended
            life++; // increment lifespan
        } else { // If the lifespan has passed
            level.setTile(x, y, tileBelow); // Reset the vortextile to the previous tile
        }
    }

    public int getID() { // Retrieve Vortex Number(ID)
        return ID;
    }

    public int getX() { // Retrieve Vortex X coords in the Tile Plane
        return x;
    }

    public int getY() { // Retrieve Vortex Y coords in the Tile Plane
        return y;
    }

    public Directions getExitDirection() { // Retrieve the direction entities exiting out of the portal must face
        return exitDirection;
    }
}