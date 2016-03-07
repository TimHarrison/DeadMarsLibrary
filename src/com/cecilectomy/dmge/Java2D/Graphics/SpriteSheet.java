package com.cecilectomy.dmge.Java2D.Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;
	private Dimension grid;
	private Dimension cells = new Dimension();
	
	public int getCellWidth() {
		return grid.width;
	}
	
	public int getCellHeight() {
		return grid.height;
	}
	
	public int getXCells() {
		return cells.width;
	}
	
	public int getYCells() {
		return cells.height;
	}
	
	public SpriteSheet(BufferedImage sourceImage, Dimension gridCell) {
		sheet = sourceImage;
		grid = gridCell;
		cells.width = (int) (sourceImage.getWidth() / gridCell.width);
		cells.height = (int) (sourceImage.getHeight() / gridCell.height);
	}
	
	public void renderSprite(Graphics g, Point cell, Dimension cells, Point dest) {
		renderSprite(g, cell.x, cell.y, cells.width, cells.height, dest.x,
				dest.y);
	}
	
	public void renderSprite(Graphics g, Point cell, Dimension cells,
			Point dest, double scale) {
		renderSprite(g, cell.x, cell.y, cells.width, cells.height, dest.x,
				dest.y, scale);
	}
	
	public void renderSprite(Graphics g, Point cell, Dimension cells,
			Point dest, double scale, double rot) {
		renderSprite(g, cell.x, cell.y, cells.width, cells.height, dest.x,
				dest.y, scale, rot);
	}
	
	public void renderSprite(Graphics g, int sGridX, int sGridY, int gCellsX,
			int gCellsY, int dX, int dY) {
		renderSprite(g, sGridX, sGridY, gCellsX, gCellsY, dX, dY, 1.0);
	}
	
	public void renderSprite(Graphics g, int sGridX, int sGridY, int gCellsX,
			int gCellsY, int dX, int dY, double scale) {
		int dX2 = dX + (int) ((grid.width * gCellsX) * scale);
		int dY2 = dY + (int) ((grid.height * gCellsY) * scale);
		int sX = sGridX * grid.width;
		int sY = sGridY * grid.height;
		int sX2 = (sGridX + gCellsX) * grid.width;
		int sY2 = (sGridY + gCellsY) * grid.height;

		g.drawImage(sheet, dX, dY, dX2, dY2, sX, sY, sX2, sY2, null);
	}
	
	public void renderSprite(Graphics g, int sGridX, int sGridY, int gCellsX,
			int gCellsY, int dX, int dY, double scale, double rot) {
		int dXW = (int) ((grid.width * gCellsX) * scale);
		int dXH = (int) ((grid.height * gCellsY) * scale);
		int dX2 = dX + dXW;
		int dY2 = dY + dXH;
		int transToX = (int) (dX + ((dX2 - dX) / 2.0));
		int transToY = (int) (dY + ((dY2 - dY) / 2.0));
		rot = (rot * Math.PI) / 180.0;
		int sX = sGridX * grid.width;
		int sY = sGridY * grid.height;
		int sX2 = (sGridX + gCellsX) * grid.width;
		int sY2 = (sGridY + gCellsY) * grid.height;

		Graphics2D g2d = (Graphics2D) g;

		g2d.translate(transToX, transToY);
		g2d.rotate(rot);
		g2d.drawImage(sheet, 0 - dXW / 2, 0 - dXH / 2, dXW / 2, dXH / 2, sX,
				sY, sX2, sY2, null);
		g2d.rotate(-rot);
		g2d.translate(-transToX, -transToY);
	}
	
	public Graphics getSheetGraphics() {
		return sheet.createGraphics();
	}

}
