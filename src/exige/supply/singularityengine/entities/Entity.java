package exige.supply.singularityengine.entities;

import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.physics.Collisions.Collision;
import exige.supply.singularityengine.physics.Collisions.EntityCollision;
import exige.supply.singularityengine.physics.Collisions.TileCollision;

public abstract class Entity {

    protected int x, y;
    protected Sprite sprite;
    protected boolean collidable;
    protected Level level;

    public abstract void update();

    public void render(Screen screen) {

    }

    protected void addToLevel() {
        level.addEntity(this);
    }

    public void remove() {
        level.removeEntity(this);
    }

    protected void onEntityCollision(EntityCollision c) {

    }

    protected void onTileCollision(TileCollision c) {

    }

    protected void onCollision(Collision c) {

    }

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

    protected Collision calculateCollision(double xPos, double yPos) {
        Collision col = new Collision(); // Create new calculateCollision scenario object

        if (!collidable) // If the entity is not collidable
            return col; // Return false by default

        // Corner Collision Calculation
        for (int corner = 0; corner < 4; corner++) { // Check all 4 corners
            double xC_Offset = sprite.getXOffset(); // Retrieve sprite x-offset (from the left)
            double yC_Offset = sprite.getYOffset(); // Retrieve sprite y-offset (from the top)

            double xC_Width = sprite.getWidth() - 1; // Calculate Collision width
            double yC_Height = sprite.getHeight() - 1; // Calculate Collision height

            double xC_Hitbox = xC_Width + xC_Offset; // Calculate Collision width
            double yC_Hitbox = yC_Height + yC_Offset; // Calculate Collision height

            // TODO: DRAW DIAGRAM.....
            double xC_Exp = corner % 2 * xC_Width + xC_Offset; // Left and right corner check expression
            double yC_Exp = corner / 2 * yC_Height + yC_Offset; // Top and bottom corner check expression

            double xC = ((x + xPos) + xC_Exp);  // Retrieve possible colliding tile on the X-axis
            double yC = ((y + yPos) + yC_Exp); // Retrieve possible colliding tile on the Y-axis

            int xC_Tile = (int) (xC / level.COLLISION_CONST); // convert from pixel to tile precision by diving by tile collision constant
            int yC_Tile = (int) (yC / level.COLLISION_CONST); // convert from pixel to tile precision by diving by tile collision constant

            for (int i = 0; i < level.getEntities().size(); i++) {
                Entity e = level.getEntities().get(i);
                if (e == this) continue;
                boolean xPixelCollide = (e.getX() >= xC - xC_Hitbox && e.getX() <= xC);
                boolean yPixelCollide = (e.getY() >= yC - yC_Hitbox && e.getY() <= yC);
                if (xPixelCollide && yPixelCollide) {
                    col = new EntityCollision(e); // Set Collision type to Entity Collision, set collide to true
                }
            }

            if (level.getTile(xC_Tile, yC_Tile).isSolid()) // If the tile at (xC,yC) is solid
                col = new TileCollision(level.getTile(xC_Tile, yC_Tile), xC_Tile, yC_Tile); // Set Collision type to Tile Collision, set collide to true
        }

        return col; // Return calculation result
    }

    public void move(double xMove, double yMove) {
        Collision potentialCollision = calculateCollision(xMove, yMove); // Calculate potential collision result

        if (!potentialCollision.doesCollide()) { // If not colliding, move
            x += (int) xMove;
            y += (int) yMove;
        } else {
            divertCollisionProcessing(potentialCollision); // If the potential collision stemming from the movement results in a collision then follow onCollision procedures
        }
    }

    public Level getLevel() {
        return level;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Sprite getSprite() {
        return sprite;
    }
}