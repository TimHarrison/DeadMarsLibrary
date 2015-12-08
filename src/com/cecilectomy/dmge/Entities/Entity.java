package com.cecilectomy.dmge.Entities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Rendering.Renderer;
import com.cecilectomy.dmge.SeparatingAxisTheorem.SatShape;
import com.cecilectomy.dmge.SpacialIndexing.QuadTree.QuadTreeNodeItem;

public class Entity implements Comparable<Entity> {
	
	public EntityManager entityManager;

	QuadTreeNodeItem entQTItemAssociate = null;

	Class<?>[] collisionClassFilters;
	EntityCollision[] collisionDefinitions;

	boolean entDestroyed = false;
	boolean entCollidable = true;
	boolean entIsColliding = false;

	EntityType entType = null;

	public SatShape shape;

	double entX = 0.0;
	double entY = 0.0;
	double entDirection = 0.0;
	double entSpeed = 0.0;
	double entElapsedTime = 0.0;
	double entLifespan = 0.0;
	
	public void setCollisions(Class<?>[] filters, EntityCollision[] definitions) {
		// Untested FindBugs bug-fix.
		// Assigns copies of filters and definitions arrays.
		collisionClassFilters = Arrays.copyOf(filters, filters.length,
				filters.getClass());
		collisionDefinitions = Arrays.copyOf(definitions, definitions.length,
				definitions.getClass());
	}
	
	public void setQuadTreeNodeItemAssociate(QuadTreeNodeItem i) {
		entQTItemAssociate = i;
	}
	
	public QuadTreeNodeItem getQuadTreeNodeItemAssociate() {
		return entQTItemAssociate;
	}
	
	public void destroy() {
		entDestroyed = true;
	}
	
	public boolean isDestroyed() {
		return entDestroyed;
	}
	
	public boolean isCollidable() {
		return entCollidable;
	}
	
	public void setIsCollidable(boolean state) {
		entCollidable = state;
	}
	
	public boolean isColliding() {
		return entIsColliding;
	}
	
	public void setIsColliding(boolean state) {
		entIsColliding = state;
	}
	
	public EntityType getType() {
		return entType;
	}
	
	public void setType(EntityType type) {
		entType = type;
	}
	
	public double getX() {
		return entX;
	}
	
	public void setX(double x) {
		if ((int) x != (int) entX) {
			entX = x;
			shape.move((int) entX, (int) entY);
			if (getQuadTreeNodeItemAssociate() != null) {
				getQuadTreeNodeItemAssociate().setPosition(
						new Point((int) entX, (int) entY));
			}
		} else {
			entX = x;
		}
	}
	
	public double getY() {
		return entY;
	}
	
	public void setY(double y) {
		if ((int) y != (int) entY) {
			entY = y;
			shape.move((int) entX, (int) entY);
			if (getQuadTreeNodeItemAssociate() != null) {
				getQuadTreeNodeItemAssociate().setPosition(
						new Point((int) entX, (int) entY));
			}
		} else {
			entY = y;
		}
	}
	
	public Point getPosition() {
		return new Point((int) entX, (int) entY);
	}
	
	public void setPosition(double x, double y) {
		if ((int) x != (int) entX || (int) y != (int) entY) {
			entX = x;
			entY = y;
			shape.move((int) entX, (int) entY);
			if (getQuadTreeNodeItemAssociate() != null) {
				getQuadTreeNodeItemAssociate().setPosition(
						new Point((int) entX, (int) entY));
			}
		} else {
			entX = x;
			entY = y;
		}
	}
	
	public void setPosition(Point pos) {
		if ((int) pos.x != (int) entX || (int) pos.y != (int) entY) {
			entX = pos.x;
			entY = pos.y;
			shape.move((int) entX, (int) entY);
			if (getQuadTreeNodeItemAssociate() != null) {
				getQuadTreeNodeItemAssociate().setPosition(
						new Point((int) entX, (int) entY));
			}
		} else {
			entX = pos.x;
			entY = pos.y;
		}
	}
	
	public double getWidth() {
		return shape.getBounds().width;
	}
	
	public double getHeight() {
		return shape.getBounds().height;
	}
	
	public Dimension getSize() {
		return new Dimension(shape.getBounds().width, shape.getBounds().height);
	}
	
	public double getDirection() {
		return entDirection;
	}
	
	public void setDirection(double d) {
		entDirection = d;
	}
	
	public double getSpeed() {
		return entSpeed;
	}
	
	public void setSpeed(double s) {
		entSpeed = s;
	}
	
	public double getElapsedTime() {
		return entElapsedTime;
	}
	
	public void setElapsedTime(double et) {
		entElapsedTime = et;
	}
	
	public double getLifespan() {
		return entLifespan;
	}
	
	public void setLifespan(double ls) {
		entLifespan = ls;
	}
	
	public Rectangle getBoundingBox() {
		return shape.getBounds();
	}
	
	public Entity(Rectangle rect, EntityType type) {
		entX = rect.x;
		entY = rect.y;
		shape = new SatShape(rect);

		entType = type;

		switch (type) {
		case Actor:
		case Prop:
			this.entCollidable = true;
			break;
		case Background:
			this.entCollidable = false;
			break;
		default:
			this.entCollidable = true;
			this.entType = EntityType.Actor;
			break;
		}
	}
	
	public void initialize() {
	}

	@Override
	public int compareTo(Entity o) {
		return 0;
	}
	
	public void loadContent() {
	}
	
	public void unloadContent() {
	}
	
	public void update(GameTime gameTime) {
		entElapsedTime += gameTime.getElapsedGameTime().getMilliseconds();
	}
	
	public void render(Renderer renderer) {
		
	}
	
	public List<RenderDetails> getRenderDetails() {
		return null;
	}
	
	public void collision(Entity ent) {
		int onColIndex = 0;

		if (collisionClassFilters != null && collisionDefinitions != null
				&& collisionClassFilters.length == collisionDefinitions.length) {

			for (Class<?> clazz : collisionClassFilters) {
				if (ent.getClass() == clazz) {
					collisionDefinitions[onColIndex].onCollision(this, ent);
					return;
				}
				onColIndex++;
			}
		}
	}

}
