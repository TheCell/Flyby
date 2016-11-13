/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flyby;

import java.awt.Graphics;
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
    
    public GameObjects()
    {
	this(new PlayerActionsHandler());
    }
    
    public GameObjects(PlayerActionsHandler actionHandler)
    {
	this.flightObjects = new LinkedList<>();
	flightObjIterator = flightObjects.listIterator();
	pActionsHandler = actionHandler;
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
		if(pressedActions.contains(PlayerActionsHandler.possibleActions.UP))
		{
		    playerObject.applyActionUp();
		    actionApplied = true;
		}

		if(pressedActions.contains(PlayerActionsHandler.possibleActions.DOWN))
		{
		    playerObject.applyActionDown();
		    actionApplied = true;
		}
		
		if(pressedActions.contains(PlayerActionsHandler.possibleActions.LEFT))
		{
		    playerObject.applyActionLeft();
		    actionApplied = true;
		}

		if(pressedActions.contains(PlayerActionsHandler.possibleActions.RIGHT))
		{
		    playerObject.applyActionRight();
		    actionApplied = true;
		}
		
		if(!actionApplied)
		{
//		    playerObject.slowDown();
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
		g.fillOval(tempFlightPos.getX(), tempFlightPos.getY(), 50, 50);
	    }
	    else
	    {
		g.drawOval(tempFlightPos.getX(), tempFlightPos.getY(), 50, 50);
	    }
	}
    }
}
