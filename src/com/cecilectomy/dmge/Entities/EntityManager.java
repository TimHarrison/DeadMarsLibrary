package com.cecilectomy.dmge.Entities;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Rendering.Renderer;
import com.cecilectomy.dmge.SpacialIndexing.QuadTree.QuadTree;
import com.cecilectomy.dmge.SpacialIndexing.QuadTree.QuadTreeNodeItem;

public class EntityManager {

	ArrayList<Entity> entities = new ArrayList<>();
	ArrayList<Entity> entityActors = new ArrayList<>();
	ArrayList<Entity> entityProps = new ArrayList<>();
	ArrayList<Entity> entityBackgrounds = new ArrayList<>();
	ArrayList<Entity> entitiesToRemove = new ArrayList<>();

	QuadTree entityTree = null;
	
	public Entity[] getEntities() {
		return Arrays.copyOf(entities.toArray(), entities.toArray().length,
				Entity[].class);
	}
	
	public Entity[] getEntityActors() {
		return Arrays.copyOf(entityActors.toArray(),
				entityActors.toArray().length, Entity[].class);
	}
	
	public Entity[] getEntityProps() {
		return Arrays.copyOf(entityProps.toArray(),
				entityProps.toArray().length, Entity[].class);
	}
	
	public Entity[] getEntityBackgrounds() {
		return Arrays.copyOf(entityBackgrounds.toArray(),
				entityBackgrounds.toArray().length, Entity[].class);
	}
	
	public EntityManager(Rectangle tArea, int maxItemsPerTreeNode,
			boolean dynamicTreeResize) {
		entityTree = new QuadTree(tArea, maxItemsPerTreeNode, dynamicTreeResize);
	}
	
	public void addEntity(Entity ent) {
		removeEntity(ent);

		if (ent.entityManager == null) {
			ent.entityManager = this;
			ent.initialize();
			ent.loadContent();

			QuadTreeNodeItem qtEnt = new QuadTreeNodeItem(ent,
					ent.getPosition(), ent.getSize());
			ent.setQuadTreeNodeItemAssociate(qtEnt);

			entityTree.insert(qtEnt);

			entities.add(ent);

			if (ent.entType == EntityType.Actor) {
				entityActors.add(ent);
			} else if (ent.entType == EntityType.Prop) {
				entityProps.add(ent);
			} else if (ent.entType == EntityType.Background) {
				entityBackgrounds.add(ent);
			}
		}
	}
	
	public void removeEntity(Entity ent) {
		if (ent.entityManager == this) {
			ent.entityManager = null;
			ent.unloadContent();

			ent.getQuadTreeNodeItemAssociate().delete();

			entities.remove(ent);
			entityActors.remove(ent);
			entityProps.remove(ent);
			entityBackgrounds.remove(ent);
		}
	}
	
	public void removeAllEntities() {
		while (entities.size() > 0) {
			Entity e = entities.get(0);
			removeEntity(e);
		}
	}
	
	public void updateEntities(GameTime gameTime) {
		entitiesToRemove.clear();

		for (int x = 0; x < entityActors.size(); x++) {
			Entity e = entityActors.get(x);

			if (e.isDestroyed()) {
				entitiesToRemove.add(e);
			} else {
				e.update(gameTime);

				if (e.getLifespan() != 0
						&& e.getElapsedTime() >= e.getLifespan()) {
					entitiesToRemove.add(e);
					continue;
				}

				if (e.isCollidable()
						&& e.collisionClassFilters != null
						&& e.collisionDefinitions != null
						&& e.collisionClassFilters.length == e.collisionDefinitions.length) {
					ArrayList<QuadTreeNodeItem> iColList = new ArrayList<>();
					entityTree.getItems(e.getQuadTreeNodeItemAssociate()
							.getBoundingBox(), e.collisionClassFilters,
							iColList);

					e.setIsColliding(false);

					for (int y = 0; y < iColList.size(); y++) {
						QuadTreeNodeItem item2 = iColList.get(y);
						if (((Entity) item2.parent).isCollidable()
								&& !e.equals((Entity) item2.parent)) {
							e.collision((Entity) item2.parent);
							e.setIsColliding(true);
						}
					}
				} else {
					e.setIsColliding(false);
				}
			}
		}

		for (int x = 0; x < entitiesToRemove.size(); x++) {
			Entity e = entitiesToRemove.get(x);
			this.removeEntity(e);
		}
	}
	
	public void renderEntities(Class<?> filterout[], Rectangle renderViewArea,
			Renderer renderer) {
		ArrayList<QuadTreeNodeItem> renderList = new ArrayList<>();
		entityTree.getItems(renderViewArea, renderList);

		renderEntLoop: for (int x = 0; x < renderList.size(); x++) {
			Entity e = (Entity) renderList.get(x).parent;

			if (filterout != null) {
				for (Class<?> fo : filterout) {
					if (e.getClass() == fo)
						continue renderEntLoop;
				}
			}

			e.render(renderer);
		}
	}
	
	public void renderEntities(Class<?> filterout[], Polygon renderViewArea,
			Renderer renderer) {
		ArrayList<QuadTreeNodeItem> renderList = new ArrayList<>();
		entityTree.getItems(renderViewArea, renderList);

		renderEntLoop: for (int x = 0; x < renderList.size(); x++) {
			Entity e = (Entity) renderList.get(x).parent;

			if (filterout != null) {
				for (Class<?> fo : filterout) {
					if (e.getClass() == fo)
						continue renderEntLoop;
				}
			}

			e.render(renderer);
		}
	}
	
	public ArrayList<Entity> getEntities(Class<?> filterout[], Rectangle viewArea) {
		ArrayList<QuadTreeNodeItem> renderList = new ArrayList<>();
		ArrayList<Entity> entList = new ArrayList<>();
		entityTree.getItems(viewArea, renderList);

		copyEntLoop: for (int x = 0; x < renderList.size(); x++) {
			Entity e = (Entity) renderList.get(x).parent;

			if (filterout != null) {
				for (Class<?> fo : filterout) {
					if (e.getClass() == fo)
						continue copyEntLoop;
				}
			}

			entList.add(e);
		}

		return entList;
	}
	
	public ArrayList<Entity> getEntities(Class<?> filterout[],
			Polygon renderViewArea) {
		ArrayList<QuadTreeNodeItem> renderList = new ArrayList<>();
		ArrayList<Entity> entList = new ArrayList<>();
		entityTree.getItems(renderViewArea, renderList);

		copyEntLoop: for (int x = 0; x < renderList.size(); x++) {
			Entity e = (Entity) renderList.get(x).parent;

			if (filterout != null) {
				for (Class<?> fo : filterout) {
					if (e.getClass() == fo)
						continue copyEntLoop;
				}
			}

			entList.add(e);
		}

		return entList;
	}
	
	public void externalCollision(Point p, Class<?>[] classFilters,
			EntityCollision[] collisionDefinitions) {
		if (classFilters != null && collisionDefinitions != null
				&& classFilters.length == collisionDefinitions.length) {
			ArrayList<QuadTreeNodeItem> iColList = new ArrayList<>();
			entityTree.getItems(p, iColList);

			colLoop: for (int y = 0; y < iColList.size(); y++) {
				QuadTreeNodeItem item = iColList.get(y);
				if (((Entity) item.parent).isCollidable()) {
					int onColIndex = 0;

					for (Class<?> clazz : classFilters) {
						if (((Entity) item.parent).getClass() == clazz) {
							collisionDefinitions[onColIndex].onCollision(null,
									((Entity) item.parent));
							continue colLoop;
						}
						onColIndex++;
					}
				}
			}
		}
	}
	
	public void externalCollision(Rectangle r, Class<?>[] classFilters,
			EntityCollision[] collisionDefinitions) {
		if (classFilters != null && collisionDefinitions != null
				&& classFilters.length == collisionDefinitions.length) {
			ArrayList<QuadTreeNodeItem> iColList = new ArrayList<>();
			entityTree.getItems(r, iColList);

			colLoop: for (int y = 0; y < iColList.size(); y++) {
				QuadTreeNodeItem item = iColList.get(y);
				if (((Entity) item.parent).isCollidable()) {
					int onColIndex = 0;

					for (Class<?> clazz : classFilters) {
						if (((Entity) item.parent).getClass() == clazz) {
							collisionDefinitions[onColIndex].onCollision(null,
									((Entity) item.parent));
							continue colLoop;
						}
						onColIndex++;
					}
				}
			}
		}
	}

}
