/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.thecell.flyby;

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
    private List<SpriteSheet> spriteSheets;
    
    // impossibru to call from outside
    protected TextureHandler()
    {
	spriteSheets = new ArrayList<>();
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
    
    // bypass Texturehandler, fuck the System
    public void registerSpriteSheet(SpriteSheet mySprite)
    {
	spriteSheets.add(mySprite);
    }
    
    // load asset and check for xml, if no xml save the filename as Spritename
    public boolean loadAsset(Path assetPath)
    {
	try
	{
	    String Sheetname = "";
	    BufferedImage spriteSheet = ImageIO.read(assetPath.toFile());
	    
	    Sheetname = assetPath.getFileName().toString();
	    if(Sheetname.lastIndexOf(".") > -1)
	    {
		Sheetname = Sheetname.substring(0, Sheetname.lastIndexOf("."));
	    }
	    
	    spriteSheets.add(new SpriteSheet(spriteSheet, Sheetname));
	    return true;
	}
	catch (IOException ex)
	{
	    Logger.getLogger(TextureHandler.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	}
    }
    
    // load asset and check for xml, if no xml save the filename as Spritename
    public boolean loadAsset(Path assetPath, int gridHeight, int gridWidth)
    {
	try
	{
	    String Sheetname = "";
	    BufferedImage spriteSheet = ImageIO.read(assetPath.toFile());
	    
	    Sheetname = assetPath.getFileName().toString();
	    if(Sheetname.lastIndexOf(".") > -1)
	    {
		Sheetname = Sheetname.substring(0, Sheetname.lastIndexOf("."));
	    }
	    
	    spriteSheets.add(new SpriteSheet(spriteSheet, Sheetname, gridHeight, gridWidth));
	    return true;
	}
	catch (IOException ex)
	{
	    Logger.getLogger(TextureHandler.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	}
    }
    
    public BufferedImage getSprite(String spriteName)
    {
	for(int i = 0; i < spriteSheets.size(); i++)
	{
	    if(spriteSheets.get(i).hasSprite(spriteName))
	    {
		return spriteSheets.get(i).getSprite(spriteName);
	    }
	}
	
	return new BufferedImage(32, 32, 1);
    }
    
    public BufferedImage getSpriteAnimated(String spriteName, int frame)
    {
	for(int i = 0; i < spriteSheets.size(); i++)
	{
	    if(spriteSheets.get(i).hasSprite(spriteName))
	    {
		return spriteSheets.get(i).getSpriteAnimationFrame(spriteName, frame);
	    }
	}
	
	return new BufferedImage(32, 32, 1);
    }
}
