package eu.thecell.flyby;

import java.util.Objects;

/**
 * Flightobject contains MovableObj, Health, Name.
 * @author simon
 */
public class FlightObject
{
    private MovableObj movObj;
    private Healthpoints health;
    private String name;
    private PhysicsUtil physUtils;
    public boolean isPlayerControlled;
    
    public FlightObject()
    {
	this("unknown");
    }
    
    public FlightObject(String name)
    {
	this.movObj = new MovableObj();
	this.health = new Healthpoints();
	this.name = name;
	this.physUtils = new PhysicsUtil();
    }
    
    public FlightObject(String name, int health, int posX, int posY, int velocity)
    {
	this(name, health, posX, posY, 0, 0, velocity);
    }
    
    /**
     * Creates a new Flightobject with a name, health, a Position and Speed.
     * @param name
     * @param health
     * @param posX
     * @param posY
     * @param vectorX
     * @param vectorY
     * @param velocity 
     */
    public FlightObject(String name, int health, int posX, int posY, int vectorX, int vectorY, int velocity)
    {
	this.movObj = new MovableObj(posX, posY, vectorX, vectorY, velocity);
	this.health = new Healthpoints(health);
	this.name = name;
	this.physUtils = new PhysicsUtil();
    }
    
    /**
     * Delegates to Movableobject to update the Position.
     */
    public void changeUp()
    {
	movObj.updatePos();
    }
    
    /**
     * Sets the directions in x and y to 0.
     */
    public void resetDirection()
    {
	movObj.setXVector(0);
	movObj.setYVector(0);
    }
    
    /**
     * Implements the logic how a FlightObject should behabe on Up command.
     */
    public void applyActionUp()
    {
	int yVector = movObj.getYVector();
	
	if(yVector > 0)
	{
	    movObj.setYVector(0);
	    movObj.setVelocityVector(0);
	}
	else
	{
	    movObj.setYVector(-1);
	}
	
	if(movObj.getVelocityVector() != 0)
	{
	    movObj.setVelocityVector(physUtils.calcSpeedup(movObj.getVelocityVector()));
	}
	else
	{
	    movObj.setVelocityVector(2);
	}
    }

    /**
     * Implements the logic how a FlightObject should behabe on Down command.
     */
    public void applyActionDown()
    {
	int yVector = movObj.getYVector();
	
	if(yVector < 0)
	{
	    movObj.setYVector(0);
	    movObj.setVelocityVector(0);
	}
	else if (yVector < 1)
	{
	    movObj.setYVector(1);
	}
	
	if(movObj.getVelocityVector() != 0)
	{
	    movObj.setVelocityVector(physUtils.calcSpeedup(movObj.getVelocityVector()));
	}
	else
	{
	    movObj.setVelocityVector(2);
	}
    }
    
    /**
     * Implements the logic how a FlightObject should behabe on Right command.
     */
    public void applyActionRight()
    {
	int xVector = movObj.getXVector();
	
	if(xVector < 0)
	{
	    movObj.setXVector(0);
	    movObj.setVelocityVector(0);
	}
	else if (xVector < 1)
	{
	    movObj.setXVector(1);
	}
	
	if(movObj.getVelocityVector() != 0)
	{
	    movObj.setVelocityVector(physUtils.calcSpeedup(movObj.getVelocityVector()));
	}
	else
	{
	    movObj.setVelocityVector(2);
	}
    }
    
    /**
     * Implements the logic how a FlightObject should behabe on Left command.
     */
    public void applyActionLeft()
    {
	int xVector = movObj.getXVector();
	
	if(xVector > 0)
	{
	    movObj.setXVector(0);
	    movObj.setVelocityVector(0);
	}
	else
	{
	    movObj.setXVector(-1);
	}
	
	if(movObj.getVelocityVector() != 0)
	{
	    movObj.setVelocityVector(physUtils.calcSpeedup(movObj.getVelocityVector()));
	}
	else
	{
	    movObj.setVelocityVector(2);
	}
    }

    /**
     * gradually slows speed down to 0.
     */
    public void slowDown()
    {
	movObj.setVelocityVector((int) physUtils.calcSpeedReduce(movObj.getVelocityVector()));
    }
    
    /**
     * Sets Boolean isPlayerControlled to true;
     */
    public void enablePlayerControll()
    {
	this.isPlayerControlled = true;
    }
    
    /**
     * Updates the position of the Object based on the speed and direction vectors.
     */
    public void updateFlightObject()
    {
	movObj.updatePos();
    }
    
    /**
     * Returns current Position of the FlightObject.
     * @return Position
     */
    public Position getPosition()
    {
	return this.movObj.getPosition();
    }
    
    /**
     * Returns if it is PlayerControlled.
     * @return boolean
     */
    public boolean isPlayerControlled()
    {
	return this.isPlayerControlled;
    }
    
    /**
     * Contains Name, Health and movableObject String.
     * @return String
     */
    @Override
    public String toString()
    {
	return "Name: " + name + " Health: " + health.getHealth() + " " + movObj.toString();
    }
    
    /**
     * Checks wether the Object is the Same based on the included Attributes.
     * @param o
     * @return boolean true if it is equal
     */
    @Override
    public boolean equals(Object o)
    {
	if(o == this)
	{
	    return true;
	}
	
	if(!(o instanceof FlightObject))
	{
	    return false;
	}
	
	FlightObject tempFlight = (FlightObject) o;
	return movObj.equals(tempFlight.movObj) && health.equals(tempFlight.health) && name.equals(tempFlight.name);
    }
    
    /**
     * Hashcode based on MovableObject, Health and Name.
     * @return int hashCode
     */
    @Override
    public int hashCode()
    {
	return Objects.hash(movObj.hashCode(), health.hashCode(), name);
    }
}
