package com.cecilectomy.dmge.Core;

import java.util.ArrayList;
import java.util.List;

import com.cecilectomy.dmge.Rendering.RenderDetails;

public class GameComponent {
	
	private ArrayList<GameComponent> children;
	private ArrayList<GameComponent> childrenToAdd;
	
	private GameBase game = null;
	private GameComponent parent = null;

	public GameComponent(GameBase game) {
		this.game = game;
		children = new ArrayList<GameComponent>();
		childrenToAdd = new ArrayList<GameComponent>();
	}
	
	public void initialize() {
		this.addChildrenToAdd();
	}

	public void cleanUp() {
	}
	
	public void input(GameTime gameTime) {
		for(GameComponent child : children) {
			child.input(gameTime);
		}
	}
	
	public void update(GameTime gameTime) {
		this.addChildrenToAdd();
		
		for(GameComponent child : children) {
			child.update(gameTime);
		}

	}
	
//	public void render(Renderer renderer) {
//		for(GameComponent child : children) {
//			child.render(renderer);
//		}
//	}
	
	public List<RenderDetails> getRenderDetails() {
		ArrayList<RenderDetails> renderDetails = new ArrayList<RenderDetails>();
		for(GameComponent child : children) {
			renderDetails.addAll(child.getRenderDetails());
		}
		return renderDetails;
	}
	
	public ArrayList<GameComponent> getChildren() {
		return this.children;
	}
	
	public GameComponent addChild(GameComponent child) {
		this.childrenToAdd.add(child);
		
		return this;
	}
	
	private void addChildrenToAdd() {
		while(this.childrenToAdd.size() > 0) {
			GameComponent child = this.childrenToAdd.remove(this.childrenToAdd.size() - 1);
			child.parent = this;
			child.initialize();
			this.children.add(child);
		}
	}
	
	public GameComponent removeChild(GameComponent child) {
		this.children.remove(child);
		return this;
	}
	
	public void removeAllChildren() {
		while(this.children.size() > 0) {
			GameComponent child = this.children.get(this.children.size()-1);
			child.removeAllChildren();
			child.cleanUp();
			this.children.remove(child);
		}
	}

	public GameBase getGame() {
		return game;
	}

	public GameComponent getParent() {
		return parent;
	}

}
