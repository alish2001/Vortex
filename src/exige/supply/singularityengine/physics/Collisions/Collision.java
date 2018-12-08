package exige.supply.singularityengine.physics.Collisions;

public class Collision {

    private boolean collide = false;

    public Collision(){
    }

    public Collision(boolean doesCollide){
        this.collide = doesCollide;
    }

    public void setCollisionResult(boolean result) {
        this.collide = result;
    }

    public boolean doesCollide() {
        return collide;
    }

}