package exige.supply.vortex.entities;

import exige.supply.vortex.sprites.Sprite;
import exige.supply.vortex.sprites.SpritesManager;

public enum PlayerCharacter {

    JACK(new Sprite(16, 0, 1, SpritesManager.getInstance().getSheet(1)));

    private Sprite sprite;

    PlayerCharacter(Sprite sprite){
        this.sprite = sprite;
    }

    public Sprite getSprite(){
        return sprite;
    }
}