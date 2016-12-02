package eu.thecell.flyby;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Extends JPanel, adds the GameObjects to the Class for drawing.
 * @author simon
 */
public class DrawPanel extends JPanel
{
    private boolean inDebug = false;
    private GameObjects allGameObjects;
    
    public DrawPanel()
    {
	this(false);
    }
    
    public DrawPanel(boolean debug)
    {
	this(debug, new GameObjects());
    }
    
    /**
     * Add GameObjects at inizialisation, it is possible to change gameobjects later on.
     * @param debug true = debugmode;
     * @param gameObjs 
     */
    public DrawPanel(boolean debug, GameObjects gameObjs)
    {
	this.inDebug = debug;
	this.allGameObjects = gameObjs;
    }
    
    /**
     * Replace list of GameObjects with a new List.
     * @param gameObjs 
     */
    public void setGameObjects(GameObjects gameObjs)
    {
	this.allGameObjects = gameObjs;
    }
    
    /**
     * Draw and then delegate the Gameobjects to draw them aswell.
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	
	allGameObjects.drawObjects(g);
    }

}
