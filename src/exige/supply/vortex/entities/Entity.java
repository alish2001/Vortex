package exige.supply.vortex.entities;

import exige.supply.vortex.levels.Level;
import exige.supply.vortex.engine.Screen;
import exige.supply.vortex.sprites.Sprite;

public abstract class Entity {

    // TODO: GETTERS FOR X Y HELLOW?!
    public int x, y;
    protected boolean collidable;
    protected Level level;

    public abstract void update();

    public void render(Screen screen){

    }

    protected void addToLevel(){
        level.addEntity(this);
    }

    protected void remove(){
        level.removeEntity(this);
    }

    protected boolean doesCollide(double xPos, double yPos, Sprite sprite) {
        if (!collidable) // If the entity is not collidable
            return false; // Return false by default

        boolean collide = false;

        // Corner Collision Calculation
        for (int corner = 0; corner < 4; corner++) { // Check all 4 corners
            double xC_Offset = sprite.getXOffset(); // Retrieve sprite x-offset (from the left)
            double yC_Offset = sprite.getYOffset(); // Retrieve sprite y-offset (from the top)

            double xC_Width = sprite.getWidth() - 1; // Calculate collision width
            double yC_Height = sprite.getHeight() - 1; // Calculate collision height

            // TODO: DRAW DIAGRAM.....
            double xC_Exp = corner % 2 * xC_Width + xC_Offset; // Left and right corner check expression
            double yC_Exp = corner / 2 * yC_Height + yC_Offset; // Top and bottom corner check expression

            double xC = ((x + xPos) + xC_Exp) / level.COLLISION_CONST; // Retrieve possible colliding tile on the X-axis (convert from pixel to tile precision by diving by Sprite size)
            double yC = ((y + yPos) + yC_Exp) / level.COLLISION_CONST; // Retrieve possible colliding tile on the Y-axis (convert from pixel to tile precision by diving by Sprite size)
            if (level.getTile((int)xC, (int)yC).isSolid())
                collide = true; // If the tile at xC and yC is a solid, set collide to true

        }

        return collide; // Return calculation result
    }

}