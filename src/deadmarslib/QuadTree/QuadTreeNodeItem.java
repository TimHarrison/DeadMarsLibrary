package deadmarslib.QuadTree;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * DeadMarsLibrary QuadTreeNodeItem class
 * 
 * @author Cecil
 */
public class QuadTreeNodeItem {

	/**
	 * A reference to the object that is associated with this item.
	 */
	public Object parent;

	/**
	 * The position of the item in the QuadTree.
	 */
	protected Point itemPosition;

	/**
	 * The size of the item, it's width and height.
	 */
	protected Dimension itemSize;

	/**
	 * The bounding box rectangle of the item, determined by its position and
	 * size.
	 */
	protected Rectangle itemBBox = new Rectangle();

	/*
	 * The node that this item resides in.
	 */
	protected QuadTreeNode itemNode;

	/**
	 * Set position.
	 * 
	 * @param p
	 */
	public void setPosition(Point p) {
		itemPosition = p;
		onMove();
	}

	/**
	 * Get position.
	 * 
	 * @return position
	 */
	public Point getPosition() {
		return itemPosition;
	}

	/**
	 * Set size.
	 * 
	 * @param s
	 */
	public void setSize(Dimension s) {
		itemSize = s;
		onMove();
	}

	/**
	 * Get size.
	 * 
	 * @return size
	 */
	public Dimension getSize() {
		return itemSize;
	}

	/**
	 * Get bounding box rectangle.
	 * 
	 * @return bounding box rectangle
	 */
	public Rectangle getBoundingBox() {
		return itemBBox;
	}

	public QuadTreeNode getItemNode() {
		return itemNode;
	}

	/**
	 * Callback handler for the move event.
	 */
	protected QuadTreeNodeItemEvent move;

	/**
	 * Callback handler for the destroy event.
	 */
	protected QuadTreeNodeItemEvent destroy;

	/**
	 * Attempts to invoke the move event handler.
	 */
	protected final void onMove() {
		itemBBox.x = (int) itemPosition.x;
		itemBBox.y = (int) itemPosition.y;
		itemBBox.width = itemSize.width;
		itemBBox.height = itemSize.height;

		if (move != null)
			move.doEvent();
	}

	/**
	 * Attempts to invoke the destroy event handler.
	 */
	protected final void onDestroy() {
		if (destroy != null)
			destroy.doEvent();
	}

	/**
	 * Class Constructor
	 * 
	 * @param parent
	 * @param p
	 * @param s
	 */
	public QuadTreeNodeItem(Object parent, Point p, Dimension s) {
		this.parent = parent;
		this.itemPosition = p;
		this.itemSize = s;
		onMove();
	}

	/**
	 * Creates a destroy event.
	 */
	public void delete() {
		onDestroy();
	}

}
