/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flyby;

import java.util.Objects;

/**
 *
 * @author simon
 */
public class FlightObject
{
    private MovableObj movObj;
    private Healthpoints health;
    private String name;
    public boolean isPlayerControlled;
    private PhysicsUtil physUtils;
    
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
    
    public FlightObject(String name, int health, int posX, int posY, int vectorX, int vectorY, int velocity)
    {
	this.movObj = new MovableObj(posX, posY, vectorX, vectorY, velocity);
	this.health = new Healthpoints(health);
	this.name = name;
	this.physUtils = new PhysicsUtil();
    }
    
    public void changeUp()
    {
	movObj.updatePos();
    }
    
    public void resetDirection()
    {
	movObj.setXVector(0);
	movObj.setYVector(0);
    }
    
    public void applyActionUp()
    {
	int yVector = movObj.getYVector();
//	System.out.println(movObj.toString());
	
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
    
    public void applyActionDown()
    {
	int yVector = movObj.getYVector();
//	System.out.println(movObj.toString());
	
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
    
    public void applyActionRight()
    {
	int xVector = movObj.getXVector();
//	System.out.println(movObj.toString());
	
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
    
    public void applyActionLeft()
    {
	int xVector = movObj.getXVector();
//	System.out.println(movObj.toString());
	
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
    
    public void slowDown()
    {
	movObj.setVelocityVector(physUtils.calcSpeedReduce(movObj.getVelocityVector()));
    }
    
    public void enablePlayerControll()
    {
	this.isPlayerControlled = true;
    }
    
    public void updateFlightObject()
    {
	movObj.updatePos();
    }
    
    public Position getPosition()
    {
	return this.movObj.getPosition();
    }
    
    public boolean isPlayerControlled()
    {
	return this.isPlayerControlled;
    }
    
    @Override
    public String toString()
    {
	return "Name: " + name + " Health: " + health.getHealth() + " " + movObj.toString();
    }
    
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
    
    @Override
    public int hashCode()
    {
	return Objects.hash(movObj.hashCode(), health.hashCode(), name);
    }
}
