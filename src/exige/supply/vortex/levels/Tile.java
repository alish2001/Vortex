package exige.supply.vortex.levels;

import exige.supply.vortex.engine.Screen;
import exige.supply.vortex.sprites.Sprite;

public class Tile {

    private int x, y;
    private Sprite sprite;

    public Tile (Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, this); // Shift back to pixel precision
    }

    public boolean solid(){
        return false;
    }

    public Sprite getSprite() {
        return sprite;
    }

}