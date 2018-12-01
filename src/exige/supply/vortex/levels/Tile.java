package exige.supply.vortex.levels;

import exige.supply.vortex.renderer.Screen;
import exige.supply.vortex.sprites.Sprite;

public class Tile {

    public int x, y;
    public Sprite sprite;

    public Tile (Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, this); // Shift back to pixel precision
    }

    public boolean solid(){
        return false;
    }

}