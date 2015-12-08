package com.cecilectomy.dmge.Core;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;

// TODO (Daniel): Refactor GameInput API
// Better mouse and keyboard support.
// Multiple controller support.
public class GameInput extends GameComponent {

	ArrayList<Integer> kbdInputs = new ArrayList<>();
	ArrayList<Integer> mseInputs = new ArrayList<>();

	ArrayList<Integer> lastKey = new ArrayList<>();

	HashMap<String, Object> inputMappings = new HashMap<>();

	int mouseX;
	int mouseY;
	
	public GameInput(GameBase game, Component component) {
		super(game);

		final Component thisGame = component;
		final GameInput me = this;

		component.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!me.kbdInputs.contains(e.getKeyCode())) {
					me.lastKey.clear();
					me.lastKey.add(e.getKeyCode());
					me.kbdInputs.add(e.getKeyCode());
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (me.kbdInputs.contains(e.getKeyCode())) {
					me.kbdInputs.remove((Integer) e.getKeyCode());
				}
			}
		});

		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thisGame.setFocusable(true);
				thisGame.requestFocus();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (!me.mseInputs.contains(e.getButton())) {
					me.mseInputs.add(e.getButton());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (me.mseInputs.contains(e.getButton())) {
					me.mseInputs.remove((Integer) e.getButton());
				}
			}
		});

		component.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);

				me.mouseX = e.getX();
				me.mouseY = e.getY();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);

				me.mouseX = e.getX();
				me.mouseY = e.getY();
			}
		});
	}
	
	public void setDefaultInputMappings() {
		this.setMapping("A", KeyEvent.VK_A);
		this.setMapping("B", KeyEvent.VK_B);
		this.setMapping("X", KeyEvent.VK_X);
		this.setMapping("Y", KeyEvent.VK_Y);
		this.setMapping("start", KeyEvent.VK_ENTER);
		this.setMapping("back", KeyEvent.VK_BACK_SPACE);
		this.setMapping("guide", KeyEvent.VK_TAB);
	}
	
	public Object getMapping(String key) {
		if (this.inputMappings.containsKey(key)) {
			return this.inputMappings.get(key);
		}
		return new Object();
	}
	
	public void setMapping(String key, Object input) {
		this.inputMappings.put(key, input);
	}
	
	public Integer lastKeyPressed() {
		if (this.lastKey.size() > 0) {
			Integer key = this.lastKey.get(this.lastKey.size() - 1);
			this.lastKey.clear();
			return key;
		} else {
			return null;
		}
	}
	
	public boolean isKeyDown(Integer keyCode) {
		if (this.kbdInputs.contains(keyCode)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isMouseDown(Integer buttonCode) {
		if (this.mseInputs.contains(buttonCode)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void removeKeyDown(Integer keyCode) {
		this.kbdInputs.remove(keyCode);
	}
	
	public void removeMouseDown(Integer keyCode) {
		this.mseInputs.remove(keyCode);
	}
	
	public int getMouseX() {
		return this.mouseX;
	}
	
	public int getMouseY() {
		return this.mouseY;
	}
	
	public Point getMouseCoords() {
		return new Point(this.mouseX, this.mouseY);
	}

}
