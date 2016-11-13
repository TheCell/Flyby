/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flyby;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author simon
 */
public class TextureHandler
{
    private static TextureHandler instance = null;
    private List<BufferedImage> spriteSheets;
    private int gridSize;
    
    // impossibru to call from outside
    protected TextureHandler()
    {
	spriteSheets = new ArrayList<>();
	gridSize = 32;
    }
    
    /**
     * Gives you the Singleton instance back.
     * @return TextureHandler
     */
    public static TextureHandler getInstance()
    {
	if(instance == null)
	{
	    instance = new TextureHandler();
	}
	
	return instance;
    }
    
    public boolean loadAsset(Path assetPath)
    {
	try
	{
	    BufferedImage spriteSheet = ImageIO.read(assetPath.toFile());
	    spriteSheets.add(spriteSheet);
	    return true;
	}
	catch (IOException ex)
	{
	    BufferedImage spriteSheet = null;
	    Logger.getLogger(TextureHandler.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	}
    }
    
    /**
     * returns the whole loaded Asset
     * @param spriteSheetNumber
     * @return 
     */
    public BufferedImage getSpriteSheet(int spriteSheetNumber)
    {
	return this.spriteSheets.get(spriteSheetNumber);
    }
    
    /**
     * returns the Sprite as a row
     * @param spriteSheetNumber
     * @param spriteNumber
     * @return 
     */
    public BufferedImage getSprite(int spriteSheetNumber, int spriteNumber)
    {
	BufferedImage loadedSprite = spriteSheets.get(spriteSheetNumber);
	return loadedSprite.getSubimage(0, gridSize * spriteNumber, loadedSprite.getWidth(), gridSize * spriteNumber + gridSize);
    }
    
    public void setGridSize(int size)
    {
	if(size > 0)
	{
	    this.gridSize = size;
	}
    }
    
    public int getGridSize()
    {
	return this.gridSize;
    }
}
