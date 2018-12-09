package exige.supply.singularityengine.entities;

import exige.supply.singularityengine.graphics.sprites.Sprite;
import exige.supply.singularityengine.graphics.sprites.SpritesManager;
import exige.supply.singularityengine.input.Keyboard;

import java.awt.event.KeyEvent;

public enum PlayerCharacter {

    JACK(new Keyboard(new int[]{KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_F, KeyEvent.VK_G}), new Sprite(0, 1, 16, 11, 0, 3, SpritesManager.getInstance().getSheet(1))),
    JORDAN(new Keyboard(new int[]{KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2}), new Sprite(1, 1, 16, 11, 0, 3, SpritesManager.getInstance().getSheet(1)));

    private Sprite sprite;
    private Keyboard keys;

    PlayerCharacter(Keyboard keys, Sprite sprite){
        this.sprite = sprite;
        this.keys = keys;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public Keyboard getKeys(){
        return keys;
    }
}