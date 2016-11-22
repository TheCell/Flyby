/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flyby;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

/**
 *
 * @author simon
 */
public class GameObjects
{
    private LinkedList<FlightObject> flightObjects;
    private ListIterator<FlightObject> flightObjIterator;
    private PlayerActionsHandler pActionsHandler;
    private int drawCounter;
    // list to store which anims were already displayed in the same timecycle
    private Set<String> animationsUsed;
    private long cycleStart;
    // how long should a cycle be in milliseconds
    private final int cycleLength = 62;
    
    public GameObjects()
    {
	this(new PlayerActionsHandler());
    }
    
    public GameObjects(PlayerActionsHandler actionHandler)
    {
	this.flightObjects = new LinkedList<>();
	flightObjIterator = flightObjects.listIterator();
	animationsUsed = new HashSet<>();
	cycleStart = System.currentTimeMillis();
	pActionsHandler = actionHandler;
	drawCounter = 0;
    }
    
    public void testObjects()
    {
	flightObjects.add(new FlightObject("testi"));
	flightObjects.add(new FlightObject("testiTescht",100, 100, 100, 2, 5, 2));
	flightObjects.add(new FlightObject("testiTeschtMoves",100, 600, 600, -1, -1, 10));
	flightObjects.add(new FlightObject("TheCell", 100, 300, 300, 0));
	flightObjects.getLast().enablePlayerControll();
    }
    
    public void updatePhysics()
    {
	updatePlayerControlledFlightObjects();
	applyPhysics();
    }
    
    private void updatePlayerControlledFlightObjects()
    {
	flightObjIterator = flightObjects.listIterator();
	
	while(flightObjIterator.hasNext())
	{
	    FlightObject playerObject = flightObjIterator.next();
	    Set pressedActions = pActionsHandler.getPressedActions();
	    boolean actionApplied = false;
	    
	    if(playerObject.isPlayerControlled)
	    {
		// go up only
		if(pressedActions.contains(PlayerActionsHandler.possibleActions.UP))
		{
		    playerObject.resetDirection();
		    playerObject.applyActionUp();
		    actionApplied = true;
		}
		
		// go down only
		if(pressedActions.contains(PlayerActionsHandler.possibleActions.DOWN))
		{
		    playerObject.resetDirection();
		    playerObject.applyActionDown();
		    actionApplied = true;
		}
		
		// go left only
		if(pressedActions.contains(PlayerActionsHandler.possibleActions.LEFT))
		{
		    playerObject.resetDirection();
		    playerObject.applyActionLeft();
		    actionApplied = true;
		}

		// go right only
		if(pressedActions.contains(PlayerActionsHandler.possibleActions.RIGHT))
		{
		    playerObject.resetDirection();
		    playerObject.applyActionRight();
		    actionApplied = true;
		}
		
		// apply top left
		if((pressedActions.contains(PlayerActionsHandler.possibleActions.UP)) && (pressedActions.contains(PlayerActionsHandler.possibleActions.LEFT)))
		{
		    playerObject.resetDirection();
		    playerObject.applyActionLeft();
		    playerObject.applyActionUp();
		    actionApplied = true;
		}
		
		// apply top right
		if((pressedActions.contains(PlayerActionsHandler.possibleActions.UP)) && (pressedActions.contains(PlayerActionsHandler.possibleActions.RIGHT)))
		{
		    playerObject.resetDirection();
		    playerObject.applyActionRight();
		    playerObject.applyActionUp();
		    actionApplied = true;
		}
		
		// apply down right
		if((pressedActions.contains(PlayerActionsHandler.possibleActions.DOWN)) && (pressedActions.contains(PlayerActionsHandler.possibleActions.RIGHT)))
		{
		    playerObject.resetDirection();
		    playerObject.applyActionRight();
		    playerObject.applyActionDown();
		    actionApplied = true;
		}
		
		// apply down right
		if((pressedActions.contains(PlayerActionsHandler.possibleActions.DOWN)) && (pressedActions.contains(PlayerActionsHandler.possibleActions.LEFT)))
		{
		    playerObject.resetDirection();
		    playerObject.applyActionLeft();
		    playerObject.applyActionDown();
		    actionApplied = true;
		}
		
		if(!actionApplied)
		{
		    playerObject.slowDown();
		}
	    }
	}
    }
    
    private void applyPhysics()
    {
	flightObjIterator = flightObjects.listIterator();
	
	while(flightObjIterator.hasNext())
	{
	    FlightObject tempObj = flightObjIterator.next();
	    
	    tempObj.updateFlightObject();
	}
    }
    
    public void drawObjects(Graphics g)
    {
	flightObjIterator = flightObjects.listIterator();
	
	while(flightObjIterator.hasNext())
	{
	    FlightObject tempFlight = flightObjIterator.next();
	    Position tempFlightPos = tempFlight.getPosition();
	    
	    if(tempFlight.isPlayerControlled)
	    {
//		BufferedImage playerImage = TextureHandler.getInstance().getSprite(1,0);
//		BufferedImage playerImage = TextureHandler.getInstance().getSprite("playerships");
		BufferedImage playerImage = TextureHandler.getInstance().getSpriteAnimated("playerships", drawCounter);
		g.drawImage(playerImage, tempFlightPos.getX() - playerImage.getWidth() / 2, tempFlightPos.getY() - playerImage.getHeight() / 2, null);
	    }
	    else
	    {
		g.drawOval(tempFlightPos.getX(), tempFlightPos.getY(), 50, 50);
	    }
	}
	
	// update counter only when new drawcycle begins
	if(isNewCycle())
	{
	    if(drawCounter > 30)
	    {
		drawCounter = 0;
	    }
	    else
	    {
		drawCounter ++;
	    }
	}
    }
    
    private boolean isNewCycle()
    {
	if((this.cycleStart + this.cycleLength) < System.currentTimeMillis())
	{
	    this.cycleStart = System.currentTimeMillis();
	    return true;
	}
	
	return false;
    }
}
