/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.thecell.flyby;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;


/**
 * Delegates the draw, updates Physics, starts the PlayerActionsHandler,
 * holds the amount of Gameticks. Controlls the flow of the Application. 
 * @author simon
 */
public class GameController
{
    private long lastTick;
    private DrawPanel myDrawPanel;
    public GameObjects allGameObjects;
    private int gameTickLenghtMS = 32;
    private PlayerActionsHandler pActionHandler;
    private TextureHandler textureHandler;
    
    /**
     * Creates DrawPanel, PlayerActionsHandler, GameObjects
     */
    public GameController()
    {
	lastTick = System.currentTimeMillis();
	myDrawPanel = new DrawPanel();
	pActionHandler = new PlayerActionsHandler();
	
	allGameObjects = new GameObjects(pActionHandler);
	allGameObjects.testObjects();
	myDrawPanel.setGameObjects(allGameObjects);
	
	textureHandler = TextureHandler.getInstance();
	Path assetPath = Paths.get("").toAbsolutePath();
	textureHandler.loadAsset(assetPath.resolve("assets").resolve("spaceShips.png"));
	textureHandler.loadAsset(assetPath.resolve("assets").resolve("playerships.png"), 100, 100);
    }
    
    /**
     * update physics, empty Actionhandler Actions.
     */
    public void update()
    {
	if(isGameTick())
	{
	    Set pressedActions = pActionHandler.getPressedActions();

	    allGameObjects.updatePhysics();
	    
	    pActionHandler.emptyActions();
	}
    }
    
    /**
     * Returns the Actionhandler to manage keyinputs outside of this class.
     * @return PlayerActionsHandler
     */
    public PlayerActionsHandler getActionHandler()
    {
	return pActionHandler;
    }
    
    /**
     * Used to register the Panel in the Window.
     * @return DrawPanel
     */
    public DrawPanel getDrawPanel()
    {
	return this.myDrawPanel;
    }
    
    /**
     * True if the Method is called after a new gameTick started and the Method was not
     * called already once. Gametick length is defined in this Class with gameTickLenghtMS.
     * @return boolean
     */
    private boolean isGameTick()
    {
	if(System.currentTimeMillis() > (lastTick + gameTickLenghtMS))
	{
	    lastTick = System.currentTimeMillis();
	    return true;
	}
	
	return false;
    }
}
