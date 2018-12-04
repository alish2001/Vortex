package exige.supply.vortex.entities;

import exige.supply.vortex.sprites.Sprite;
import exige.supply.vortex.sprites.SpritesManager;

public enum PlayerCharacter {

    JACK(16, 11, 0, 3, new Sprite(16, 0, 1, SpritesManager.getInstance().getSheet(1)));

    private Sprite sprite;
    private int width, height, xOffset, yOffset;

    PlayerCharacter(int width, int height, int xOffset, int yOffset, Sprite sprite){
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public Sprite getSprite(){
        return sprite;
    }
}