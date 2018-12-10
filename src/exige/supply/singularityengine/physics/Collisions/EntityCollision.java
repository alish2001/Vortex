package exige.supply.singularityengine.physics.Collisions;

import exige.supply.singularityengine.entities.Entity;

/**
 * EntityCollision Class. Allows for Entity based Collision event values to be stored.
 * Subclass of @{@link Collision}
 *
 * @author Ali Shariatmadari
 */

public class EntityCollision extends Collision {

    private Entity collidedEntity;

    public EntityCollision(Entity collidedEntity) {
        setCollisionResult(true);
        this.collidedEntity = collidedEntity;
    }

    /**
     * Set the collided entity
     *
     * @param entity @{@link Entity}
     */
    public void setCollidedEntity(Entity entity) {
        this.collidedEntity = entity;
    }

    /**
     * @return Collided @{@link Entity}
     */
    public Entity getCollidedEntity() {
        return collidedEntity;
    }
}