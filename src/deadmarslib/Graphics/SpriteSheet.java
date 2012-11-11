package deadmarslib.Graphics;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
// </editor-fold>

/**
 * DeadMarsLibrary SpriteSheet Class
 * 
 * @author Daniel Cecil
 */
public class SpriteSheet {
    
    private BufferedImage sheet;
    private Dimension grid;
    private Dimension cells = new Dimension();
    
    /**
     * Retrieves the width of the cells of this {@link SpriteSheet}.
     * 
     * @return cell width.
     */
    public int getCellWidth() {
        return grid.width;
    }
    
    /**
     * Retrieves the height of the cells of this {@link SpriteSheet}.
     * 
     * @return cell height.
     */
    public int getCellHeight() {
        return grid.height;
    }
    
    /**
     * Retrieves the width of the sprite sheet in grid cells.
     * 
     * @return sprite sheet width in cells.
     */
    public int getXCells() {
        return cells.width;
    }
    
    /**
     * Retrieves the height of the sprite sheet in grid cells.
     * 
     * @return sprite sheet height in cells.
     */
    public int getYCells() {
        return cells.height;
    }
    
    /**
     * SpriteSheet Constructor.
     * <p>
     * Takes a BufferedImage as the source image to cut out the sprites.
     * <P>
     * Takes a Dimension to tell it how large each grid cell is.
     * 
     * @param sourceImage Source image.
     * @param gridCell Size of sprite sheet grid cells in pixels.
     */
    public SpriteSheet(BufferedImage sourceImage, Dimension gridCell) {
        sheet = sourceImage;
        grid = gridCell;
        cells.width = (int)(sourceImage.getWidth() / gridCell.width);
        cells.height = (int)(sourceImage.getHeight() / gridCell.height);
    }
    
    /**
     * Render a sprite to a graphics context.
     * <p>
     * Supports non-rotated 1:1 rendering.
     * 
     * @param g Graphics context to draw to.
     * @param cell Location of cell to start rendering from.
     * @param cells Size of sprite in cells.
     * @param dest Destination on Graphics context to render to.
     */
    public void renderSprite(Graphics g, Point cell, Dimension cells, Point dest) {
        renderSprite(g, cell.x, cell.y, cells.width, cells.height, dest.x, dest.y);
    }
    
    /**
     * Render a sprite to a graphics context.
     * <p>
     * Supports scaling.
     * 
     * @param g Graphics context to draw to.
     * @param cell Location of cell to start rendering from.
     * @param cells Size of sprite in cells.
     * @param dest Destination on Graphics context to render to.
     * @param scale scales the render up or down.
     */
    public void renderSprite(Graphics g, Point cell, Dimension cells, Point dest, double scale) {
        renderSprite(g, cell.x, cell.y, cells.width, cells.height, dest.x, dest.y, scale);
    }
    
    /**
     * Render a sprite to a graphics context.
     * <p>
     * Supports scaling and rotation.
     * 
     * @param g Graphics context to draw to.
     * @param cell Location of cell to start rendering from.
     * @param cells Size of sprite in cells.
     * @param dest Destination on Graphics context to render to.
     * @param scale scales the render up or down.
     * @param rot rotates the render from the center of the scaled image.
     */
    public void renderSprite(Graphics g, Point cell, Dimension cells, Point dest, double scale, double rot) {
        renderSprite(g, cell.x, cell.y, cells.width, cells.height, dest.x, dest.y, scale, rot);
    }
    
    /**
     * Render a sprite to a graphics context.
     * <p>
     * Supports non-rotated 1:1 rendering.
     * 
     * @param g Graphics context to draw to.
     * @param sGridX X of grid cells to start drawing from.
     * @param sGridY Y of grid cells to start drawing from.
     * @param gCellsX Amount of cells wide the sprite is.
     * @param gCellsY Amount of cells high the sprite is.
     * @param dX X coordinate of where to draw the sprite to on the graphics context.
     * @param dY Y coordinate of where to draw the sprite to on the graphics context.
     */
    public void renderSprite(Graphics g, int sGridX, int sGridY, int gCellsX, int gCellsY, int dX, int dY) {
        renderSprite(g, sGridX, sGridY, gCellsX, gCellsY, dX, dY, 1.0);
    }
    
    /**
     * Render a sprite to a graphics context.
     * <p>
     * Supports scaling.
     * 
     * @param g Graphics context to draw to.
     * @param sGridX X of grid cells to start drawing from.
     * @param sGridY Y of grid cells to start drawing from.
     * @param gCellsX Amount of cells wide the sprite is.
     * @param gCellsY Amount of cells high the sprite is.
     * @param dX X coordinate of where to draw the sprite to on the graphics context.
     * @param dY Y coordinate of where to draw the sprite to on the graphics context.
     * @param scale scales the render up or down.
     */
    public void renderSprite(Graphics g, int sGridX, int sGridY, int gCellsX, int gCellsY, int dX, int dY, double scale) {
        int dX2 = dX + (int)((grid.width * gCellsX) * scale);
        int dY2 = dY + (int)((grid.height * gCellsY) * scale);
        int sX = sGridX * grid.width;
        int sY = sGridY * grid.height;
        int sX2 = (sGridX + gCellsX) * grid.width;
        int sY2 = (sGridY + gCellsY) * grid.height;
        
        g.drawImage(sheet, dX, dY, dX2, dY2, sX, sY, sX2, sY2, null);
    }

    /**
     * Render a sprite to a graphics context.
     * <p>
     * Supports scaling and rotation.
     * 
     * @param g Graphics context to draw to.
     * @param sGridX X of grid cells to start drawing from.
     * @param sGridY Y of grid cells to start drawing from.
     * @param gCellsX Amount of cells wide the sprite is.
     * @param gCellsY Amount of cells high the sprite is.
     * @param dX X coordinate of where to draw the sprite to on the graphics context.
     * @param dY Y coordinate of where to draw the sprite to on the graphics context.
     * @param scale scales the render up or down.
     * @param rot rotates the render from the center of the scaled image.
     */
    public void renderSprite(Graphics g, int sGridX, int sGridY, int gCellsX, int gCellsY, int dX, int dY, double scale, double rot) {
        int dXW = (int)((grid.width * gCellsX) * scale);
        int dXH = (int)((grid.height * gCellsY) * scale);
        int dX2 = dX + dXW;
        int dY2 = dY + dXH;
        int transToX = (int)(dX + ((dX2 - dX) / 2.0));
        int transToY = (int)(dY + ((dY2 - dY) / 2.0));
        rot = (rot * Math.PI) / 180.0;
        int sX = sGridX * grid.width;
        int sY = sGridY * grid.height;
        int sX2 = (sGridX + gCellsX) * grid.width;
        int sY2 = (sGridY + gCellsY) * grid.height;
        
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.translate(transToX, transToY);
        g2d.rotate(rot);
        g2d.drawImage(sheet, 0 - dXW/2, 0 - dXH/2, dXW/2, dXH/2, sX, sY, sX2, sY2, null);
        g2d.rotate(-rot);
        g2d.translate(-transToX, -transToY);
    }
    
    /**
     * Gets the graphics context of the sprite sheet for drawing on.
     * <p>
     * Lets you modify the sprite sheet at runtime.
     * 
     * @return Graphics context of the sprite sheet.
     */
    public Graphics getSheetGraphics() {
        return sheet.createGraphics();
    }
    
}
