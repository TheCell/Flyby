/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flyby;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;


/**
 *
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
	textureHandler.loadAsset(assetPath.resolve("assets").resolve("playerships.png"));
    }
    
    public void update()
    {
	if(isGameTick())
	{
	    Set pressedActions = pActionHandler.getPressedActions();

	    // do player controlls
	    if(pressedActions.contains(PlayerActionsHandler.possibleActions.UP))
	    {
//		allGameObjects.moveTestObjects();
	    }
	    
	    if(pressedActions.contains(PlayerActionsHandler.possibleActions.DOWN))
	    {
//		allGameObjects.moveTestObjects();
	    }
	    
	    if(pressedActions.contains(PlayerActionsHandler.possibleActions.LEFT))
	    {
//		allGameObjects.moveTestObjects();
	    }
	    
	    if(pressedActions.contains(PlayerActionsHandler.possibleActions.RIGHT))
	    {
//		allGameObjects.moveTestObjects();
	    }
	    
	    if(pressedActions.contains(PlayerActionsHandler.possibleActions.SHOOT))
	    {
//		allGameObjects.moveTestObjects();
	    }
	    
	    // do "physics"
	    allGameObjects.updatePhysics();
	    
	    pActionHandler.emptyActions();
	}
    }
    
    public PlayerActionsHandler getActionHandler()
    {
	return pActionHandler;
    }
    
    public DrawPanel getDrawPanel()
    {
	return this.myDrawPanel;
    }
    
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
