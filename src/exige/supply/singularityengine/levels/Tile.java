package exige.supply.singularityengine.levels;

import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.graphics.sprites.Sprite;

public class Tile {

    protected Level level;
    private Sprite sprite;
    private boolean solid;

    public Tile (Sprite sprite, boolean solid){
        this.sprite = sprite;
        this.solid = solid;
    }

    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, this); // Shift back to pixel precision
    }

    public void update(){

    }

    public void setLevel(Level level){
        this.level = level;
    }

    public Level getLevel(){
        return level;
    }

    public boolean isSolid(){
        return solid;
    }

    public Sprite getSprite() {
        return sprite;
    }

}