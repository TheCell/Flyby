/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flyby;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author simon
 */
public class PlayerActionsHandler
{
    Set<possibleActions> isPressed;
    
    public PlayerActionsHandler()
    {
	isPressed = new HashSet<>(possibleActions.values().length);
    }
    
    public enum possibleActions
    {
	UP, DOWN, LEFT, RIGHT, SHOOT
    }
    
    public void showDebug()
    {
	System.out.println("HashSet isPressed: " + isPressed.toString());
    }
    
    /**
     * Activates the Action that was pressed
     * @param action 
     */
    public void activateAction(possibleActions action)
    {
	switch(action)
	{
	    case UP:
		isPressed.add(possibleActions.UP);
		break;
	    case DOWN:
		isPressed.add(possibleActions.DOWN);
		break;
	    case LEFT:
		isPressed.add(possibleActions.LEFT);
		break;
	    case RIGHT:
		isPressed.add(possibleActions.RIGHT);
		break;
	    case SHOOT:
		isPressed.add(possibleActions.SHOOT);
		break;
	    default:
	        break;
	}
    }
    
    public Set getPressedActions()
    {
	return this.isPressed;
    }
    
    public void emptyActions()
    {
	isPressed.clear();
    }
}
