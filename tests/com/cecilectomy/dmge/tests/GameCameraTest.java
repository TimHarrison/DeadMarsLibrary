package com.cecilectomy.dmge.tests;

import org.junit.Assert;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cecilectomy.dmge.Utility.Camera;

public class GameCameraTest {

	private Camera cam;

	@Before
	public void setUp() {
		cam = new Camera();
	}

	@After
	public void tearDown() {
		cam = null;
	}

	@Test
	public void testSetX() {
		int x = 5;

		cam.setX(x);

		Assert.assertEquals(x, cam.getX(), 0);
	}

	@Test
	public void testSetY() {
		int y = 10;

		cam.setY(y);

		Assert.assertEquals(y, cam.getY(), 0);
	}

	@Test
	public void testSetWidth() {
		int w = 320;

		cam.setWidth(w);

		Assert.assertEquals(w, cam.getWidth(), 0);
	}

	@Test
	public void testSetHeight() {
		int h = 240;

		cam.setHeight(h);

		Assert.assertEquals(h, cam.getHeight(), 0);
	}

	@Test
	public void testSetPositionXY() {
		int x = 5, y = 10;

		cam.setPosition(x, y);

		Assert.assertEquals(x, cam.getPosition().x);
		Assert.assertEquals(y, cam.getPosition().y);
	}

	@Test
	public void testSetPositionPoint() {
		int x = 5, y = 10;

		cam.setPosition(new Point(x, y));

		Assert.assertEquals(x, cam.getPosition().x);
		Assert.assertEquals(y, cam.getPosition().y);
	}

	@Test
	public void testSetBoundsXYWH() {
		int x = 5, y = 10, w = 320, h = 240;

		cam.setBounds(x, y, w, h);

		Assert.assertEquals(x, cam.getBounds().x);
		Assert.assertEquals(y, cam.getBounds().y);
		Assert.assertEquals(w, cam.getBounds().width);
		Assert.assertEquals(h, cam.getBounds().height);
	}

	@Test
	public void testSetBoundsRect() {
		int x = 5, y = 10, w = 320, h = 240;

		cam.setBounds(new Rectangle(x, y, w, h));

		Assert.assertEquals(x, cam.getBounds().x);
		Assert.assertEquals(y, cam.getBounds().y);
		Assert.assertEquals(w, cam.getBounds().width);
		Assert.assertEquals(h, cam.getBounds().height);
	}

	@Test
	public void testGetCameraCoef() {
		int x = 5, y = 10, w = 320, h = 240;
		double xCoef = 0.5, yCoef = 0.75, delta = 1;

		Rectangle rect = new Rectangle(x, y, w, h);

		cam.setBounds(rect);

		Assert.assertEquals(x * xCoef, cam.getBounds(xCoef, yCoef).x, delta);
		Assert.assertEquals(y * yCoef, cam.getBounds(xCoef, yCoef).y, delta);
	}

	@Test
	public void testLockTo() {
		int x = 5, y = 10, w = 320, h = 240;

		Rectangle bounds = new Rectangle(x, y, w, h);
		Rectangle lockTo = new Rectangle(-1000, -1000, 2000, 2000);

		cam.setBounds(bounds);
		
		cam.setPosition(-10000, -10000);
		cam.lockTo(lockTo);

		Assert.assertEquals(-1000, cam.getPosition().x);
		Assert.assertEquals(-1000, cam.getPosition().y);
		
		cam.setPosition(10000, 10000);
		cam.lockTo(lockTo);

		Assert.assertEquals(1000, cam.getPosition().x + cam.getWidth(), 0);
		Assert.assertEquals(1000, cam.getPosition().y + cam.getHeight(), 0);
	}

}
