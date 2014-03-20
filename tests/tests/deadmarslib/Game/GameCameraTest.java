package tests.deadmarslib.Game;

import static org.junit.Assert.*;
import java.awt.Point;
import java.awt.Rectangle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import deadmarslib.Game.GameCamera;

public class GameCameraTest {

	private GameCamera cam;

	@Before
	public void setUp() {
		cam = new GameCamera();
	}

	@After
	public void tearDown() {
		cam = null;
	}

	@Test
	public void testSetX() {
		int x = 5;

		cam.setX(x);

		assertEquals(x, cam.getX());
	}

	@Test
	public void testSetY() {
		int y = 10;

		cam.setY(y);

		assertEquals(y, cam.getY());
	}

	@Test
	public void testSetWidth() {
		int w = 320;

		cam.setWidth(w);

		assertEquals(w, cam.getWidth());
	}

	@Test
	public void testSetHeight() {
		int h = 240;

		cam.setHeight(h);

		assertEquals(h, cam.getHeight());
	}

	@Test
	public void testSetPositionXY() {
		int x = 5, y = 10;

		cam.setPosition(x, y);

		assertEquals(x, cam.getPosition().x);
		assertEquals(y, cam.getPosition().y);
	}

	@Test
	public void testSetPositionPoint() {
		int x = 5, y = 10;

		cam.setPosition(new Point(x, y));

		assertEquals(x, cam.getPosition().x);
		assertEquals(y, cam.getPosition().y);
	}

	@Test
	public void testSetBoundsXYWH() {
		int x = 5, y = 10, w = 320, h = 240;

		cam.setBounds(x, y, w, h);

		assertEquals(x, cam.getBounds().x);
		assertEquals(y, cam.getBounds().y);
		assertEquals(w, cam.getBounds().width);
		assertEquals(h, cam.getBounds().height);
	}

	@Test
	public void testSetBoundsRect() {
		int x = 5, y = 10, w = 320, h = 240;

		cam.setBounds(new Rectangle(x, y, w, h));

		assertEquals(x, cam.getBounds().x);
		assertEquals(y, cam.getBounds().y);
		assertEquals(w, cam.getBounds().width);
		assertEquals(h, cam.getBounds().height);
	}

	@Test
	public void testGetCameraCoef() {
		int x = 5, y = 10, w = 320, h = 240;
		double xCoef = 0.5, yCoef = 0.75, delta = 1;

		Rectangle rect = new Rectangle(x, y, w, h);

		cam.setBounds(rect);

		assertEquals(x * xCoef, cam.getBounds(xCoef, yCoef).x, delta);
		assertEquals(y * yCoef, cam.getBounds(xCoef, yCoef).y, delta);
	}

	@Test
	public void testLockTo() {
		int x = 5, y = 10, w = 320, h = 240;

		Rectangle bounds = new Rectangle(x, y, w, h);
		Rectangle lockTo = new Rectangle(-1000, -1000, 2000, 2000);

		cam.setBounds(bounds);
		
		cam.setPosition(-10000, -10000);
		cam.lockTo(lockTo);

		assertEquals(-1000, cam.getPosition().x);
		assertEquals(-1000, cam.getPosition().y);
		
		cam.setPosition(10000, 10000);
		cam.lockTo(lockTo);

		assertEquals(1000, cam.getPosition().x + cam.getWidth());
		assertEquals(1000, cam.getPosition().y + cam.getHeight());
	}

}
