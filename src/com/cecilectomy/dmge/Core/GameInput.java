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

/**
 * DeadMarsLib GameInput Class
 * 
 * @author Daniel Cecil
 */
// TODO: Refactor GameInput API
public class GameInput extends GameObject {

	ArrayList<Integer> kbdInputs = new ArrayList<>();
	ArrayList<Integer> mseInputs = new ArrayList<>();

	ArrayList<Integer> lastKey = new ArrayList<>();

	HashMap<String, Object> inputMappings = new HashMap<>();

	int mouseX;
	int mouseY;

	/**
	 * Constructor
	 */
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

	/**
	 * Set the default input mappings.
	 */
	public void setDefaultInputMappings() {
		this.setMapping("A", KeyEvent.VK_A);
		this.setMapping("B", KeyEvent.VK_B);
		this.setMapping("X", KeyEvent.VK_X);
		this.setMapping("Y", KeyEvent.VK_Y);
		this.setMapping("start", KeyEvent.VK_ENTER);
		this.setMapping("back", KeyEvent.VK_BACK_SPACE);
		this.setMapping("guide", KeyEvent.VK_TAB);
	}

	/**
	 * Get the input mapping the for key.
	 * 
	 * @param key
	 *            Name of the key to get mapping for.
	 * @return Input mapping of the key.
	 */
	public Object getMapping(String key) {
		if (this.inputMappings.containsKey(key)) {
			return this.inputMappings.get(key);
		}
		return new Object();
	}

	/**
	 * Set the key to be mapped to a specific input.
	 * 
	 * @param key
	 *            Name of key to map input to.
	 * @param input
	 *            Input object to map to key.
	 */
	public void setMapping(String key, Object input) {
		this.inputMappings.put(key, input);
	}

	/**
	 * Retrieves the {@link Integer} code of the last key that was pressed.
	 * 
	 * @return key code.
	 */
	public Integer lastKeyPressed() {
		if (this.lastKey.size() > 0) {
			Integer key = this.lastKey.get(this.lastKey.size() - 1);
			this.lastKey.clear();
			return key;
		} else {
			return null;
		}
	}

	/**
	 * Checks if a specified key is currently pressed down.
	 * 
	 * @param keyCode
	 *            Key to check.
	 * @return Whether or not the specified key is being pressed.
	 */
	public boolean isKeyDown(Integer keyCode) {
		if (this.kbdInputs.contains(keyCode)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if a specified mouse button is currently pressed down.
	 * 
	 * @param buttonCode
	 *            Mouse button to check.
	 * @return Whether or not the specified mouse button is being pressed.
	 */
	public boolean isMouseDown(Integer buttonCode) {
		if (this.mseInputs.contains(buttonCode)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Remove a key from the list of keys currently being pressed.
	 * 
	 * @param keyCode
	 *            Key to remove.
	 */
	public void removeKeyDown(Integer keyCode) {
		this.kbdInputs.remove(keyCode);
	}

	/**
	 * Remove a mouse button from the list of mouse buttons currently being
	 * pressed.
	 * 
	 * @param keyCode
	 *            Mouse button to remove.
	 */
	public void removeMouseDown(Integer keyCode) {
		this.mseInputs.remove(keyCode);
	}

	/**
	 * Retrieves the mouse's x coordinate within the window.
	 * 
	 * @return X coordinate of the mouse.
	 */
	public int getMouseX() {
		return this.mouseX;
	}

	/**
	 * Retrieves the mouse's y coordinate within the window.
	 * 
	 * @return Y coordinate of the mouse.
	 */
	public int getMouseY() {
		return this.mouseY;
	}

	/**
	 * Retrieves a {@link Point} that represents the mouse's coordinates within
	 * the window.
	 * 
	 * @return The mouse's xy coordinates.
	 */
	public Point getMouseCoords() {
		return new Point(this.mouseX, this.mouseY);
	}

}
