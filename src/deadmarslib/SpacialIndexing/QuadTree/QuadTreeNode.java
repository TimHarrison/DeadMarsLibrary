package deadmarslib.SpacialIndexing.QuadTree;

import deadmarslib.Utility.RectUtility;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * DeadMarsLibrary QuadTreeNode class
 * 
 * @author Daniel Cecil
 */
public class QuadTreeNode {

	/**
	 * Whether or not this node has been split.
	 */
	protected boolean isSplit;

	/**
	 * Callback handler for the resize event. A node will attempt to call this
	 * when an item goes out of the QuadTree bounds. If there is no resize
	 * callback set, the item is allowed to exist outside the QuadTree bounds,
	 * and resides in the rootNode. A resize event is expensive.
	 */
	protected QuadTreeResizeDelegate resize;

	/**
	 * A reference to this nodes parent node. If null, then this node is the
	 * rootNode.
	 */
	protected QuadTreeNode parentNode;

	/**
	 * A reference to this nodes Top Left child node.
	 */
	protected QuadTreeNode tlNode;

	/**
	 * A reference to this nodes Top Right child node.
	 */
	protected QuadTreeNode trNode;

	/**
	 * A reference to this nodes Bottom Left child node.
	 */
	protected QuadTreeNode blNode;

	/**
	 * A reference to this nodes Bottom Right child node.
	 */
	protected QuadTreeNode brNode;

	/**
	 * A list of all the items contained directly within this node.
	 */
	protected ArrayList<QuadTreeNodeItem> itemList = new ArrayList<>();

	/**
	 * The 2d space that this node encompasses.
	 */
	protected Rectangle rect;

	/**
	 * The maximum number of items that may exist in this node before the node
	 * is split. There may exist more items than the maximum, only if the items
	 * are on the boundaries of 2 child nodes.
	 */
	protected int maxItems;

	/**
	 * Get the rectangle of the 2d space this node encompasses.
	 * 
	 * @return node rectangle
	 */
	public Rectangle getRect() {
		return rect;
	}

	protected void setMaxItems(int m) {
		maxItems = m;
	}

	/**
	 * Get the maximum number of items this node can contain.
	 * 
	 * @return max items
	 */
	public int getMaxItems() {
		return maxItems;
	}

	/**
	 * Class Constructor
	 * 
	 * @param parent
	 * @param r
	 * @param m
	 */
	public QuadTreeNode(QuadTreeNode parent, Rectangle r, int m) {
		parentNode = parent;
		rect = r;
		maxItems = m;
		isSplit = false;
	}

	/**
	 * Class Constructor
	 * 
	 * @param r
	 * @param m
	 * @param rd
	 */
	public QuadTreeNode(Rectangle r, int m, QuadTreeResizeDelegate rd) {
		parentNode = null;
		rect = r;
		maxItems = m;
		isSplit = false;
		resize = rd;
	}

	public void insertItem(QuadTreeNodeItem i) {
		if (!insertItemIntoChild(i)) {
			final QuadTreeNode thisNode = this;
			final QuadTreeNodeItem thisItem = i;

			i.destroy = new QuadTreeNodeItemEvent() {

				@Override
				public void doEvent() {
					thisNode.itemDestroy(thisItem);
				}

			};

			i.move = new QuadTreeNodeItemEvent() {

				@Override
				public void doEvent() {
					thisNode.itemMove(thisItem);
				}

			};

			i.itemNode = this;
			itemList.add(i);

			if (!isSplit && itemList.size() >= maxItems) {
				splitNode();
			}
		}
	}

	protected boolean insertItemIntoChild(QuadTreeNodeItem i) {
		if (!isSplit)
			return false;

		if (tlNode.getRect().contains(i.getBoundingBox())) {
			tlNode.insertItem(i);
		} else if (trNode.getRect().contains(i.getBoundingBox())) {
			trNode.insertItem(i);
		} else if (blNode.getRect().contains(i.getBoundingBox())) {
			blNode.insertItem(i);
		} else if (brNode.getRect().contains(i.getBoundingBox())) {
			brNode.insertItem(i);
		} else {
			return false;
		}

		return true;
	}

	protected boolean pushItemDown(int i) {
		if (insertItemIntoChild(itemList.get(i))) {
			itemList.remove(i);
			return true;
		}
		return false;
	}

	protected boolean pushItemUp(int i) {
		if (parentNode != null) {
			QuadTreeNodeItem m = itemList.get(i);
			removeItem(i);
			parentNode.insertItem(m);

			return true;
		}
		return false;
	}

	protected void splitNode() {
		int nLeft = this.rect.x;
		int nRight = this.rect.x + this.rect.width;
		int nTop = this.rect.y;
		int nBottom = this.rect.y + this.rect.height;

		int midx = (((nRight - nLeft) / 2) + nLeft);
		int midy = (((nBottom - nTop) / 2) + nTop);
		Point midPoint = new Point(midx, midy);
		int nWidth = midPoint.x - nLeft;
		int nHeight = midPoint.y - nTop;

		tlNode = new QuadTreeNode(this, new Rectangle(nLeft, nTop, nWidth,
				nHeight), maxItems);
		trNode = new QuadTreeNode(this, new Rectangle(midPoint.x, nTop, nWidth,
				nHeight), maxItems);
		blNode = new QuadTreeNode(this, new Rectangle(nLeft, midPoint.y,
				nWidth, nHeight), maxItems);
		brNode = new QuadTreeNode(this, new Rectangle(midPoint.x, midPoint.y,
				nWidth, nHeight), maxItems);

		isSplit = true;

		int i = 0;
		while (i < itemList.size()) {
			if (!pushItemDown(i)) {
				i++;
			}
		}
	}

	protected void joinNode(boolean isempty) {
		ArrayList<QuadTreeNodeItem> iList = new ArrayList<>();

		if (isempty) {
			this.getAllItems(iList);

			if (iList.isEmpty()) {
				this.destroyNode();
				return;
			}
		} else {
			if (this.getCompleteItemCount() < this.maxItems) {
				this.getAllItems(iList);
				this.destroyNode();
			} else {
				return;
			}
		}

		for (int x = 0; x < iList.size(); x++) {
			this.insertItem(iList.get(x));
		}
	}

	public int getCompleteItemCount() {
		if (isSplit) {
			return tlNode.getCompleteItemCount()
					+ trNode.getCompleteItemCount()
					+ blNode.getCompleteItemCount()
					+ brNode.getCompleteItemCount();
		}
		return itemList.size();
	}

	public void getItems(Point p, ArrayList<QuadTreeNodeItem> list) {
		if (this.getRect().contains(p)) {
			QuadTreeNodeItem[] items = Arrays.copyOf(itemList.toArray(),
					itemList.toArray().length, QuadTreeNodeItem[].class);
			for (QuadTreeNodeItem item : items) {
				if (item.getBoundingBox().contains(p)) {
					list.add(item);
				}
			}

			if (isSplit) {
				trNode.getItems(p, list);
				tlNode.getItems(p, list);
				blNode.getItems(p, list);
				brNode.getItems(p, list);
			}
		}
	}

	public void getItems(Point p, Class filters[],
			ArrayList<QuadTreeNodeItem> list) {
		if (this.getRect().contains(p)) {
			QuadTreeNodeItem[] items = Arrays.copyOf(itemList.toArray(),
					itemList.toArray().length, QuadTreeNodeItem[].class);
			for (QuadTreeNodeItem item : items) {
				if (filters != null) {
					for (Class filter : filters) {
						if (item.parent.getClass() == filter) {
							if (item.getBoundingBox().contains(p)) {
								list.add(item);
							}
						}
					}
				} else {
					if (item.getBoundingBox().contains(p)) {
						list.add(item);
					}
				}
			}

			if (isSplit) {
				tlNode.getItems(p, filters, list);
				trNode.getItems(p, filters, list);
				blNode.getItems(p, filters, list);
				brNode.getItems(p, filters, list);
			}
		}
	}

	public void getItems(Rectangle r, ArrayList<QuadTreeNodeItem> list) {
		if (this.getRect().intersects(r)) {
			QuadTreeNodeItem[] items = Arrays.copyOf(itemList.toArray(),
					itemList.toArray().length, QuadTreeNodeItem[].class);
			for (QuadTreeNodeItem item : items) {
				if (item.getBoundingBox().intersects(r)) {
					list.add(item);
				}
			}

			if (isSplit) {
				tlNode.getItems(r, list);
				trNode.getItems(r, list);
				blNode.getItems(r, list);
				brNode.getItems(r, list);
			}
		}
	}

	public void getItems(Rectangle r, Class filters[],
			ArrayList<QuadTreeNodeItem> list) {
		if (this.getRect().intersects(r)) {
			QuadTreeNodeItem[] items = Arrays.copyOf(itemList.toArray(),
					itemList.toArray().length, QuadTreeNodeItem[].class);
			for (QuadTreeNodeItem item : items) {
				if (filters != null) {
					for (Class filter : filters) {
						if (item.parent.getClass() == filter) {
							if (item.getBoundingBox().intersects(r)) {
								list.add(item);
							}
						}
					}
				} else {
					if (item.getBoundingBox().intersects(r)) {
						list.add(item);
					}
				}
			}

			if (isSplit) {
				tlNode.getItems(r, filters, list);
				trNode.getItems(r, filters, list);
				blNode.getItems(r, filters, list);
				brNode.getItems(r, filters, list);
			}
		}
	}

	public void getItems(Polygon p, ArrayList<QuadTreeNodeItem> list) {
		if (p.intersects(this.getRect())) {
			QuadTreeNodeItem[] items = Arrays.copyOf(itemList.toArray(),
					itemList.toArray().length, QuadTreeNodeItem[].class);
			for (QuadTreeNodeItem item : items) {
				if (p.intersects(item.getBoundingBox())) {
					list.add(item);
				}
			}

			if (isSplit) {
				tlNode.getItems(p, list);
				trNode.getItems(p, list);
				blNode.getItems(p, list);
				brNode.getItems(p, list);
			}
		}
	}

	public void getItems(Polygon p, Class filters[],
			ArrayList<QuadTreeNodeItem> list) {
		if (p.intersects(this.getRect())) {
			QuadTreeNodeItem[] items = Arrays.copyOf(itemList.toArray(),
					itemList.toArray().length, QuadTreeNodeItem[].class);
			for (QuadTreeNodeItem item : items) {
				if (filters != null) {
					for (Class filter : filters) {
						if (item.parent.getClass() == filter) {
							if (p.intersects(item.getBoundingBox())) {
								list.add(item);
							}
						}
					}
				} else {
					if (p.intersects(item.getBoundingBox())) {
						list.add(item);
					}
				}
			}

			if (isSplit) {
				tlNode.getItems(p, filters, list);
				trNode.getItems(p, filters, list);
				blNode.getItems(p, filters, list);
				brNode.getItems(p, filters, list);
			}
		}
	}

	public void getAllItems(ArrayList<QuadTreeNodeItem> list) {
		list.addAll(itemList);

		if (isSplit) {
			tlNode.getAllItems(list);
			trNode.getAllItems(list);
			blNode.getAllItems(list);
			brNode.getAllItems(list);
		}
	}

	public void getAllItems(Class filters[], ArrayList<QuadTreeNodeItem> list) {
		QuadTreeNodeItem[] items = Arrays.copyOf(itemList.toArray(),
				itemList.toArray().length, QuadTreeNodeItem[].class);
		for (QuadTreeNodeItem item : items) {
			if (filters != null) {
				for (Class filter : filters) {
					if (item.getClass() == filter)
						list.add(item);
				}
			} else {
				list.add(item);
			}
		}

		if (isSplit) {
			tlNode.getAllItems(filters, list);
			trNode.getAllItems(filters, list);
			blNode.getAllItems(filters, list);
			brNode.getAllItems(filters, list);
		}
	}

	public QuadTreeNode findItemNode(QuadTreeNodeItem i) {
		if (itemList.contains(i)) {
			return this;
		} else if (isSplit) {
			QuadTreeNode n = null;

			if (tlNode.rect.contains(i.getBoundingBox())) {
				n = tlNode.findItemNode(i);
			} else if (trNode.rect.contains(i.getBoundingBox())) {
				n = trNode.findItemNode(i);
			} else if (blNode.rect.contains(i.getBoundingBox())) {
				n = blNode.findItemNode(i);
			} else if (brNode.rect.contains(i.getBoundingBox())) {
				n = brNode.findItemNode(i);
			}

			return n;
		} else {
			return null;
		}
	}

	public void destroyNode() {
		if (isSplit) {
			tlNode.destroyNode();
			trNode.destroyNode();
			blNode.destroyNode();
			brNode.destroyNode();

			tlNode = null;
			trNode = null;
			blNode = null;
			brNode = null;
		}

		resize = null;

		while (itemList.size() > 0) {
			removeItem(0);
		}

		isSplit = false;
	}

	public void removeItem(int i) {
		if (i < itemList.size()) {
			QuadTreeNodeItem item = itemList.get(i);
			item.destroy = null;
			item.move = null;
			itemList.remove(i);
		}
	}

	public void removeItem(QuadTreeNodeItem i) {
		if (itemList.contains(i)) {
			i.destroy = null;
			i.move = null;
			itemList.remove(i);
		}
	}

	protected void itemMove(QuadTreeNodeItem i) {
		if (itemList.contains(i)) {
			int iIndex = itemList.indexOf(i);

			if (!pushItemDown(iIndex)) {
				if (parentNode != null) {
					pushItemUp(iIndex);
				} else if (resize != null
						&& !this.getRect().contains(i.getBoundingBox())) {
					int newX = Math.min(
							RectUtility.getLeft(i.getBoundingBox()),
							RectUtility.getLeft(this.getRect()));
					int newY = Math.min(RectUtility.getTop(i.getBoundingBox()),
							RectUtility.getTop(this.getRect()));
					int newWidth = Math.max(
							RectUtility.getRight(i.getBoundingBox()),
							RectUtility.getRight(this.getRect())) - newX;
					int newHeight = Math.max(
							RectUtility.getBottom(i.getBoundingBox()),
							RectUtility.getBottom(this.getRect())) - newY;

					resize.resize(new Rectangle(newX, newY, newWidth, newHeight));
				}
			}
		}

		joinNode(false);
	}

	protected void itemDestroy(QuadTreeNodeItem i) {
		removeItem(i);

		joinNode(false);
	}

	public void renderTree(Rectangle rect, Point off, Color color, Graphics g) {
		if (isSplit) {
			tlNode.renderTree(rect, off, color, g);
			trNode.renderTree(rect, off, color, g);
			blNode.renderTree(rect, off, color, g);
			brNode.renderTree(rect, off, color, g);
		}

		if (rect.intersects(this.getRect())) {
			g.setColor(color);
			g.drawRect(this.getRect().x - off.x, this.getRect().y - off.y,
					this.getRect().width, this.getRect().height);
		}
	}

}
