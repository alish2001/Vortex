package exige.supply.singularityengine.entities;

import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.physics.Collisions.Collision;
import exige.supply.singularityengine.physics.Collisions.EntityCollision;
import exige.supply.singularityengine.physics.Collisions.TileCollision;

/**
 * Abstract Entity Class.
 * An abstract class is a class that can't be instantiated. It's only purpose is for other classes to extend.
 * Abstract methods in this class must be override by child classes them as they have no body.
 * Protected methods & fields in this class can only be used by itself and its child classes.
 *
 * @author Ali Shariatmadari
 */

public abstract class Entity {

    protected int x, y;
    protected Sprite sprite;
    protected boolean collidable;
    protected Level level;

    public abstract void update(); // update method run every update cycle to update the entity state. Abstract makes it a requirement for subclasses to implement

    public void render(Screen screen) { // render method run every render cycle to render entity state

    }

    /**
     * Adds entity to level
     */
    protected void addToLevel() {
        level.addEntity(this);
    }

    /**
     * Adds removes entity from level
     */
    public void remove() {
        level.removeEntity(this);
    }

    /**
     * Run everytime a collision takes place
     */
    protected void onCollision(Collision c) {

    }

    /**
     * Run everytime a collision with another entity takes place
     */
    protected void onEntityCollision(EntityCollision c) {
    }

    /**
     * Run everytime a collision with a tile takes place
     */
    protected void onTileCollision(TileCollision c) {
    }

    // Used to split the processing of collision based on the type of collision
    private void divertCollisionProcessing(Collision c) {
        onCollision(c); // Run standard collision procedure
        if (c instanceof TileCollision) { // If the collision is a tile collision
            TileCollision tC = (TileCollision) c; // Cast TileCollision to the collision
            onTileCollision(tC); // Divert to TileCollision processing
        } else if (c instanceof EntityCollision) {
            EntityCollision eC = (EntityCollision) c; // Cast EntityCollision to the collision
            onEntityCollision(eC); // Divert to EntityCollision processing
        }
    }

    /**
     * Calculates Collision based on the distance to.
     *
     * @param xTo x-coordinate of destination
     * @param yTo y-coordinate of destination
     * @return Collision result
     */
    protected Collision calculateCollision(double xTo, double yTo) {
        return calculateCollision(x, y, xTo, yTo); // Return calculation result
    }

    /**
     * Calculates Collision based on the distance to and from.
     *
     * @param xFrom x-coordinate of the start
     * @param yFrom y-coordinate of the start
     * @param xTo   x-coordinate of destination
     * @param yTo   y-coordinate of destination
     * @return Collision result
     */
    protected Collision calculateCollision(double xFrom, double yFrom, double xTo, double yTo) {
        Collision col = new Collision(); // Create new calculateCollision scenario object

        if (!collidable) // If the entity is not collidable
            return col; // Return false by default

        // Corner Collision Calculation
        for (int corner = 0; corner < 4; corner++) { // Check all 4 corners
            double xC_Offset = sprite.getXOffset(); // Retrieve sprite x-offset (from the left)
            double yC_Offset = sprite.getYOffset(); // Retrieve sprite y-offset (from the top)

            double xC_Width = sprite.getWidth() - 1; // Calculate Collision width
            double yC_Height = sprite.getHeight() - 1; // Calculate Collision height

            // xCorner -> (0, 1, 0, 1)
            // yCorner -> (0, 0.5, 1, 1.5)
            double xC_Exp = corner % 2 * xC_Width + xC_Offset; // Left and right corner check expression
            double yC_Exp = corner / 2 * yC_Height + yC_Offset; // Top and bottom corner check expression

            double xC = ((xFrom + xTo) + xC_Exp);  // Retrieve possible colliding tile on the X-axis
            double yC = ((yFrom + yTo) + yC_Exp); // Retrieve possible colliding tile on the Y-axis

            int xC_Tile = (int) (xC / level.TILE_CONST); // convert from pixel to tile precision by diving by tile collision constant
            int yC_Tile = (int) (yC / level.TILE_CONST); // convert from pixel to tile precision by diving by tile collision constant

            for (int i = 0; i < level.getEntities().size(); i++) { // Check collision with all other entities in the level
                Entity e = level.getEntities().get(i);
                if (e == this) continue; // If the entity is this entity, skip
                boolean xPixelCollide = (e.getX() >= xC - level.TILE_CONST && e.getX() <= xC); // Check to see if the entities collide on their x intervals
                boolean yPixelCollide = (e.getY() >= yC - level.TILE_CONST && e.getY() <= yC); // Check to see if the entities collide on their y intervals
                if (xPixelCollide && yPixelCollide) { // If entities collide on both x and y intervals
                    col = new EntityCollision(e); // Set Collision type to Entity Collision, set collide to true
                }
            }

            if (level.getTile(xC_Tile, yC_Tile).isSolid()) { // If the tile at (xC,yC) is solid
                col = new TileCollision(level.getTile(xC_Tile, yC_Tile), xC_Tile, yC_Tile); // Set Collision type to Tile Collision, set collide to true
            }
        }

        if (col.doesCollide()) { // If a collision takes place
            divertCollisionProcessing(col);// Since a collision has been detected follow onCollision procedures
        }

        return col; // Return calculation result
    }

    /**
     * Moves the entity while taking into account collision.
     *
     * @param xMove Distance to move on the x-axis
     * @param yMove Distance to move on the y-axis
     */
    public void move(double xMove, double yMove) {
        Collision potentialCollision = calculateCollision(xMove, yMove); // Calculate potential collision result

        if (!potentialCollision.doesCollide()) { // If not colliding
            // Move by changing coordinates
            x += (int) xMove;
            y += (int) yMove;
        }
    }

    /**
     * Returns the level class the entity is in.
     *
     * @return @{@link Level}
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets the x-coordinates of the entity in pixel precision.
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinates of the entity in pixel precision.
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the x-coordinates of the entity in the level in pixel precision.
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinates of the entity in the level in pixel precision.
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the Sprite class of the entity.
     *
     * @return @{@link Sprite}
     */
    public Sprite getSprite() {
        return sprite;
    }
}