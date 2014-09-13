package com.cecilectomy.dmge.Core;

import java.util.ArrayList;

import com.cecilectomy.dmge.Rendering.Renderer;

public class GameObject {
	
	private ArrayList<GameObject> children;
	private ArrayList<GameObject> childrenToAdd;
	
	private ArrayList<GameComponent> components;
	private ArrayList<GameComponent> componentsToAdd;
	
	private GameBase game = null;
	private GameObject parent = null;

	public GameObject(GameBase game) {
		this.game = game;
		children = new ArrayList<GameObject>();
		childrenToAdd = new ArrayList<GameObject>();
		components = new ArrayList<GameComponent>();
		componentsToAdd = new ArrayList<GameComponent>();
	}
	
	public void initialize() {
		this.addComponentsToAdd();
		this.addChildrenToAdd();
	}

	public void cleanUp() {
	}
	
	public void input(GameTime gameTime) {
		for(GameComponent component : components) {
			component.input(gameTime);
		}
		
		for(GameObject child : children) {
			child.input(gameTime);
		}
	}
	
	public void update(GameTime gameTime) {
		this.addComponentsToAdd();
		
		for(GameComponent component : components) {
			component.update(gameTime);
		}
		
		this.addChildrenToAdd();
		
		for(GameObject child : children) {
			child.update(gameTime);
		}

	}
	
	public void render(Renderer renderer) {
		for(GameComponent component : components) {
			component.render(renderer);
		}
		
		for(GameObject child : children) {
			child.render(renderer);
		}
	}
	
	public ArrayList<GameObject> getChildren() {
		return this.children;
	}
	
	public ArrayList<GameComponent> getComponents() {
		return this.components;
	}
	
	public GameObject addChild(GameObject child) {
		this.childrenToAdd.add(child);
		
		return this;
	}
	
	private void addChildrenToAdd() {
		while(this.childrenToAdd.size() > 0) {
			GameObject child = this.childrenToAdd.remove(this.childrenToAdd.size() - 1);
			child.parent = this;
			child.initialize();
			this.children.add(child);
		}
	}
	
	public GameObject removeChild(GameObject child) {
		this.children.remove(child);
		return this;
	}
	
	public void removeAllChildren() {
		while(this.children.size() > 0) {
			GameObject child = this.children.get(this.children.size()-1);
			child.removeAllChildren();
			child.cleanUp();
			this.children.remove(child);
		}
	}
	
	public GameObject addComponent(GameComponent component) {
		this.componentsToAdd.add(component);
		
		return this;
	}
	
	private void addComponentsToAdd() {
		while(this.componentsToAdd.size() > 0) {
			GameComponent component = this.componentsToAdd.remove(this.componentsToAdd.size() - 1);
			component.initialize();
			this.components.add(component);
		}
	}
	
	public void removeComponent(GameComponent component) {
		component.cleanUp();
		this.components.remove(component);
	}
	
	public void removeAllComponents() {
		while(this.components.size() > 0) {
			GameComponent component = this.components.get(this.components.size()-1);
			this.removeComponent(component);
		}
	}

	public GameBase getGame() {
		return game;
	}

	public GameObject getParent() {
		return parent;
	}

}
