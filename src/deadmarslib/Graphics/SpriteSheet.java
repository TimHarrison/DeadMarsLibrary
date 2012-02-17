package deadmarslib.Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
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
     * @param gridCell Size of sprite sheet grid cells.
     */
    public SpriteSheet(BufferedImage sourceImage, Dimension gridCell) {
        sheet = sourceImage;
        grid = gridCell;
        cells.width = (int)(sourceImage.getWidth() / gridCell.width);
        cells.height = (int)(sourceImage.getHeight() / gridCell.height);
    }
    
    /**
     * Render a sprite to a graphics context.
     * 
     * @param g Graphics context to draw to.
     * @param sCell Cell to start drawing from.
     * @param gCells Dimensions of sprite in cells to draw.
     * @param dX X coordinate of where to draw the sprite to on the graphics context.
     * @param dY Y coordinate of where to draw the sprite to on the graphics context.
     */
    public void renderSprite(Graphics g, int sCell, Dimension gCells, int dX, int dY) {
        int sGridX = (sCell % cells.width);
        int sGridY = ((sCell - sGridX) / cells.width);
        g.drawImage(sheet, dX, dY, dX + grid.width * gCells.width, dY + grid.height * gCells.height, sGridX * grid.width, sGridY * grid.height, (sGridX + gCells.width) * grid.width, (sGridY + gCells.height) * grid.height, null);
    }
    
    /**
     * Render a sprite to a graphics context.
     * 
     * @param g Graphics context to draw to.
     * @param sGridX X of grid cell to start drawing from.
     * @param sGridY Y of grid cell to start drawing from.
     * @param gCellsX Amount of cells wide the sprite is.
     * @param gCellsY Amount of cells high the sprite is.
     * @param dX X coordinate of where to draw the sprite to on the graphics context.
     * @param dY Y coordinate of where to draw the sprite to on the graphics context.
     */
    public void renderSprite(Graphics g, int sGridX, int sGridY, int gCellsX, int gCellsY, int dX, int dY) {
        g.drawImage(sheet, dX, dY, dX + grid.width * gCellsX, dY + grid.height * gCellsY, sGridX * grid.width, sGridY * grid.height, (sGridX + gCellsX) * grid.width, (sGridY + gCellsY) * grid.height, null);
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
