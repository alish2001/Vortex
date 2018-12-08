package exige.supply.vortex.engine.physics.Collisions;

import exige.supply.vortex.entities.Entity;

public class EntityCollision extends Collision {

    private Entity collidedEntity;

    public EntityCollision(Entity collidedEntity){
        setCollisionResult(true);
        this.collidedEntity = collidedEntity;
    }

    public void setCollidedEntity(Entity tile) {
        this.collidedEntity = tile;
    }

    public Entity getCollidedEntity() {
        return collidedEntity;
    }
}