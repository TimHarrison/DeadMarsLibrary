package com.cecilectomy.dmge.Entities;

/**
 * DeadMarsLib EntityCollision Interface
 * 
 * @author Daniel Cecil
 */
public interface EntityCollision {

	/**
	 * Callback method for a collision between two entities.
	 * 
	 * @param ent1 Primary entity involved in collision.
	 * @param ent2 Secondary entity involved in collision.
	 */
	public void onCollision(Entity ent1, Entity ent2);

}
