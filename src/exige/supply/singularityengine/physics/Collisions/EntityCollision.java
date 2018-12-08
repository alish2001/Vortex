package exige.supply.singularityengine.physics.Collisions;

import exige.supply.singularityengine.entities.Entity;

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