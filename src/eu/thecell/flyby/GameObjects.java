package eu.thecell.flyby;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

/**
 * Holds all Gameobjects like FlightObjects.
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
    
    /**
     * updates the PlayerControlled Objects, applies Physics after controllupdate.
     */
    public void updatePhysics()
    {
	updatePlayerControlledFlightObjects();
	applyPhysics();
    }
    
    /**
     * Checks the Keyboardinputs and sets the Direction of all Playercontrolled
     * Objects.
     */
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
		
		/**
		 * If the user does not input, slow the Flightobject down until delta Velocity is 0.
		 */
		if(!actionApplied)
		{
		    playerObject.slowDown();
		}
	    }
	}
    }
    
    /**
     * Iterates all Flightobjects and updates them.
     */
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
//	    Position tempFlightPos = tempFlight.getPosition();
	    
	    if(tempFlight.isPlayerControlled)
	    {
//		BufferedImage playerShipImage = TextureHandler.getInstance().getSpriteAnimated("playerships", 0);
//		g.drawImage(playerShipImage, tempFlightPos.getX() - playerShipImage.getWidth() / 2, tempFlightPos.getY() - playerShipImage.getHeight() / 2, null);
		tempFlight.draw(g, 0);
	    }
	    else
	    {
		tempFlight.draw(g, drawCounter);
//		BufferedImage shipImage = TextureHandler.getInstance().getSpriteAnimated("playerships", drawCounter);
//		g.drawImage(shipImage, tempFlightPos.getX() - shipImage.getWidth() / 2, tempFlightPos.getY() - shipImage.getHeight() / 2, null);
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

    /**
     * Checks the current Cycle based on the defined Cycle length.
     * @return 
     */
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
