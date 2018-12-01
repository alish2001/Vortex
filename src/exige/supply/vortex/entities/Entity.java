package exige.supply.vortex.entities;

import exige.supply.vortex.levels.Level;
import exige.supply.vortex.engine.Screen;

public abstract class Entity {

    public int x, y;
    private boolean removed = false;
    protected Level level;

    public void update() {
    }

    public void render(Screen screen){
    }

    public void remove(){
        // Remove from level
        removed = true;
    }

    public boolean isRemoved(){
        return removed;
    }

}