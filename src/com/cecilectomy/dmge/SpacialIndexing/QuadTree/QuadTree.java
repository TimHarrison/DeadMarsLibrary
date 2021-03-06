package com.cecilectomy.dmge.SpacialIndexing.QuadTree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import com.cecilectomy.dmge.Utility.RectangleEx;

public class QuadTree {

	protected QuadTreeNode rootNode;

	protected int maxItems;

	protected void setTreeMaxItems(int m) {
		this.maxItems = m;
	}
	
	public int getTreeMaxItems() {
		return this.maxItems;
	}
	
	public Rectangle getWorldRect() {
		return rootNode.getRect();
	}

	public QuadTree(Rectangle r, int m, boolean resizeOnMove) {
		final QuadTree qTree = this;
		if (resizeOnMove) {
			this.rootNode = new QuadTreeNode(r, m,
					new QuadTreeResizeDelegate() {

						@Override
						public void resize(Rectangle r) {
							qTree.resize(r);
						}

					});
		} else {
			this.rootNode = new QuadTreeNode(r, m, null);
		}

		this.maxItems = m;
	}

	public void insert(QuadTreeNodeItem i) {
		if (rootNode.resize != null
				&& !rootNode.getRect().contains(i.getBoundingBox())) {
			int newX = Math.min(
					RectangleEx.getLeft(i.getBoundingBox()),
					RectangleEx.getLeft(rootNode.getRect()));
			int newY = Math.min(
					RectangleEx.getTop(i.getBoundingBox()),
					RectangleEx.getTop(rootNode.getRect()));
			int newWidth = Math.max(
					RectangleEx.getRight(i.getBoundingBox()),
					RectangleEx.getRight(this.getWorldRect())) - newX;
			int newHeight = Math.max(
					RectangleEx.getBottom(i.getBoundingBox()),
					RectangleEx.getBottom(this.getWorldRect())) - newY;

			resize(new Rectangle(newX, newY, newWidth, newHeight));
		}

		rootNode.insertItem(i);
	}

	public QuadTreeNodeItem insert(Object parent, Point p, Dimension s) {
		QuadTreeNodeItem item = new QuadTreeNodeItem(parent, p, s);

		insert(item);

		return item;
	}

	public void resize(Rectangle r) {
		ArrayList<QuadTreeNodeItem> components = new ArrayList<QuadTreeNodeItem>();
		getAllItems(null, components);

		QuadTreeResizeDelegate tempResize = rootNode.resize;
		rootNode.destroyNode();
		rootNode = null;

		rootNode = new QuadTreeNode(r, maxItems, tempResize);

		QuadTreeNodeItem[] items = Arrays.copyOf(components.toArray(),
				components.toArray().length, QuadTreeNodeItem[].class);
		for (QuadTreeNodeItem item : items) {
			rootNode.insertItem(item);
		}
	}

	public void getItems(Point point, ArrayList<QuadTreeNodeItem> itemList) {
		if (itemList != null) {
			rootNode.getItems(point, itemList);
		}
	}

	public void getItems(Point point, Class<?> filters[],
			ArrayList<QuadTreeNodeItem> itemList) {
		if (itemList != null) {
			rootNode.getItems(point, filters, itemList);
		}
	}

	public void getItems(Rectangle rect, ArrayList<QuadTreeNodeItem> itemList) {
		if (itemList != null) {
			rootNode.getItems(rect, itemList);
		}
	}

	public void getItems(Rectangle rect, Class<?> filters[],
			ArrayList<QuadTreeNodeItem> itemList) {
		if (itemList != null) {
			rootNode.getItems(rect, filters, itemList);
		}
	}

	public void getItems(Polygon pol, ArrayList<QuadTreeNodeItem> itemList) {
		if (itemList != null) {
			rootNode.getItems(pol, itemList);
		}
	}

	public void getItems(Polygon pol, Class<?> filters[],
			ArrayList<QuadTreeNodeItem> itemList) {
		if (itemList != null) {
			rootNode.getItems(pol, filters, itemList);
		}
	}

	public void getAllItems(ArrayList<QuadTreeNodeItem> itemList) {
		if (itemList != null) {
			rootNode.getAllItems(itemList);
		}
	}

	public void getAllItems(Class<?> filters[],
			ArrayList<QuadTreeNodeItem> itemList) {
		if (itemList != null) {
			rootNode.getAllItems(filters, itemList);
		}
	}

	public void refactorTree(int maxItems) {
		ArrayList<QuadTreeNodeItem> iList = new ArrayList<QuadTreeNodeItem>();
		rootNode.getAllItems(iList);
		rootNode.destroyNode();

		this.maxItems = maxItems;
		rootNode.setMaxItems(maxItems);

		for (int x = 0; x < iList.size(); x++) {
			rootNode.insertItem(iList.get(x));
		}
	}

	public void renderTree(Rectangle rect, Point off, Color color, Graphics g) {
		rootNode.renderTree(rect, off, color, g);
	}

}
