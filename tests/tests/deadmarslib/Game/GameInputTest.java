package tests.deadmarslib.Game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import deadmarslib.Input.GameInput;
import deadmarslib.Window.GameWindowFrame;

public class GameInputTest {
	
	Robot robot;
	GameWindowFrame game;
	GameInput input;

	@Before
	public void setUp() throws AWTException {
		robot = new Robot();
		game = new GameWindowFrame("", new Dimension(320,240), 60L) {
		};
		input = new GameInput(game);
	}

	@After
	public void tearDown() {
		game.stop();
		game.getFrame().dispose();
		game = null;
		
		robot = null;
		
		input = null;
	}

	@Test
	public void testLastKeyPressed() {
		robot.keyPress(KeyEvent.VK_0);
		robot.keyPress(KeyEvent.VK_1);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_3);
		robot.keyPress(KeyEvent.VK_4);
		robot.keyPress(KeyEvent.VK_5);
		robot.delay(500);
		assertEquals(KeyEvent.VK_5, input.lastKeyPressed().intValue());
	}
	
	@Test
	public void testIsKeyDown() {
		robot.keyPress(KeyEvent.VK_0);
		robot.delay(500);
		assertTrue(input.isKeyDown(KeyEvent.VK_0));
		
		robot.keyRelease(KeyEvent.VK_0);
		robot.delay(500);
		assertFalse(input.isKeyDown(KeyEvent.VK_0));
	}
	
	@Test
	public void testRemoveKeyDown() {
		robot.keyPress(KeyEvent.VK_0);
		robot.delay(500);
		assertTrue(input.isKeyDown(KeyEvent.VK_0));
		input.removeKeyDown(KeyEvent.VK_0);
		assertFalse(input.isKeyDown(KeyEvent.VK_0));
	}
	
	@Test
	public void testIsMouseDown() {
		robot.mouseMove(game.getFrame().getLocationOnScreen().x +160, game.getFrame().getLocationOnScreen().y + 120);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(500);
		assertTrue(input.isMouseDown(MouseEvent.BUTTON1));
		
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(500);
		assertFalse(input.isMouseDown(MouseEvent.BUTTON1));
	}
	
	@Test
	public void testRemoveMouseDown() {
		robot.mouseMove(game.getFrame().getLocationOnScreen().x +160, game.getFrame().getLocationOnScreen().y + 120);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(500);
		assertTrue(input.isMouseDown(MouseEvent.BUTTON1));
		input.removeMouseDown(MouseEvent.BUTTON1);
		assertFalse(input.isMouseDown(MouseEvent.BUTTON1));
	}

	@Test
	public void testMousePosition() {
		int initX = 160;
		int initY = 120;
		int newX = initX + 10;
		int newY = initY + 20;
		int screenX = game.getFrame().getLocationOnScreen().x;
		int screenY = game.getFrame().getLocationOnScreen().y; 
		Insets insets = game.getFrame().getInsets();

		robot.mouseMove(screenX + newX, screenY + newY);
		robot.delay(1500);
		robot.mouseMove(screenX + initX, screenY + initY);
		robot.delay(1500);

		assertEquals(initX - insets.left, input.getMouseX());
		assertEquals(initY - insets.top, input.getMouseY());
		assertEquals(initX - insets.left, input.getMouseCoords().x);
		assertEquals(initY - insets.top, input.getMouseCoords().y);
		
		robot.mouseMove(screenX + newX, screenY + newY);
		
		robot.delay(1500);

		assertEquals(newX - insets.left, input.getMouseX());
		assertEquals(newY - insets.top, input.getMouseY());
		assertEquals(newX - insets.left, input.getMouseCoords().x);
		assertEquals(newY - insets.top, input.getMouseCoords().y);
	}
	
}
