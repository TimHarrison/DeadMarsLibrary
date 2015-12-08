package com.cecilectomy.dmge.SpacialIndexing.QuadTree;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class QuadTreeNodeItem {
	
	public Object parent;
	
	protected Point itemPosition;
	protected Dimension itemSize;
	protected Rectangle itemBBox = new Rectangle();
	protected QuadTreeNode itemNode;
	
	public void setPosition(Point p) {
		itemPosition = p;
		onMove();
	}
	
	public Point getPosition() {
		return itemPosition;
	}
	
	public void setSize(Dimension s) {
		itemSize = s;
		onMove();
	}
	
	public Dimension getSize() {
		return itemSize;
	}
	
	public Rectangle getBoundingBox() {
		return itemBBox;
	}

	public QuadTreeNode getItemNode() {
		return itemNode;
	}
	
	protected QuadTreeNodeItemEvent move;
	protected QuadTreeNodeItemEvent destroy;
	
	protected final void onMove() {
		itemBBox.x = (int) itemPosition.x;
		itemBBox.y = (int) itemPosition.y;
		itemBBox.width = itemSize.width;
		itemBBox.height = itemSize.height;

		if (move != null)
			move.doEvent();
	}
	
	protected final void onDestroy() {
		if (destroy != null)
			destroy.doEvent();
	}
	
	public QuadTreeNodeItem(Object parent, Point p, Dimension s) {
		this.parent = parent;
		this.itemPosition = p;
		this.itemSize = s;
		onMove();
	}
	
	public void delete() {
		onDestroy();
	}

}
