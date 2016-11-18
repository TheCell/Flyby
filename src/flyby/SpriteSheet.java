/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flyby;

import java.awt.image.BufferedImage;

/**
 *
 * @author simon
 */
public class SpriteSheet
{
    private String xmlinfo;
    private boolean hasXmlinfo;
    private BufferedImage spriteSheet;
    private int gridHeight;
    private int gridWidth;
    private String sheetName;
    
    public SpriteSheet()
    {
	this(new BufferedImage(32, 32, 1));
    }
    
    public SpriteSheet(BufferedImage spriteSheet)
    {
	this(spriteSheet, 32, 32);
    }
    
    public SpriteSheet(BufferedImage spriteSheet, String name)
    {
	this(spriteSheet, name, 32, 32);
    }
    
    public SpriteSheet(BufferedImage spriteSheet, int gridSize)
    {
	this(spriteSheet, "unknown", gridSize, gridSize);
    }
    
    public SpriteSheet(BufferedImage spriteSheet, String name, int gridSize)
    {
	this(spriteSheet, name, gridSize, gridSize);
    }
    
    public SpriteSheet(BufferedImage spriteSheet, int gridHeight, int gridWidth)
    {
	this(spriteSheet, "unknown", gridHeight, gridWidth);
    }
    
    public SpriteSheet(BufferedImage spriteSheet, String name, int gridHeight, int gridWidth)
    {
	this.spriteSheet = spriteSheet;
	this.gridHeight = gridHeight;
	this.gridWidth = gridWidth;
	this.sheetName = name;
    }
    
    public BufferedImage getSprite(String SpriteName)
    {
	if(!hasXmlinfo && sheetName.equalsIgnoreCase(SpriteName))
	{
	    return spriteSheet;
	}
	
	// nothing found, return empty image
	return new BufferedImage(32, 32, 1);
    }
    
    public boolean hasSprite(String spritename)
    {
	if(!hasXmlinfo && sheetName.equalsIgnoreCase(spritename))
	{
	    return true;
	}
	else
	{
	    // todo handle xml filter
	    return false;
	}
    }
}
