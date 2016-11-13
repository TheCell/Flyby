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
public class MovableObj
{
    private Position objectPosition;
    private int deltaV = 0;
    private int[] deltaVector = new int[2];
    
    public MovableObj()
    {
	this(0, 0);
    }
    
    public MovableObj(int velocity)
    {
	this(0, 0, velocity);
    }
    
    public MovableObj(int x, int y)
    {
	this(x, y, 0);
    }
    
    public MovableObj(int x, int y, int velocity)
    {
	this(x, y, 1, 1, velocity);
    }
    
    public MovableObj(int x, int y, int vx, int vy, int velocity)
    {
	this.objectPosition = new Position(x, y);
	this.deltaV = velocity;
	this.deltaVector[0] = vx;
	this.deltaVector[1] = vy;
    }
    
    public void updatePos()
    {
	if(this.deltaV > 0.0 || this.deltaV < 0.0)
	{
	    objectPosition.setX(objectPosition.getX()+deltaV*deltaVector[0]);
	    objectPosition.setY(objectPosition.getY()+deltaV*deltaVector[1]);
	}
    }
    
    public int getXVector()
    {
	return this.deltaVector[0];
    }
    
    public int getYVector()
    {
	return this.deltaVector[1];
    }
    
    public int getVelocityVector()
    {
	return this.deltaV;
    }
    
    public void setVelocityVector(int newV)
    {
	this.deltaV = newV;
    }
    
    public void setXVector(int xVector)
    {
	this.deltaVector[0] = xVector;
    }
    
    public void setYVector(int yVector)
    {
	this.deltaVector[1] = yVector;
    }
    
    public Position getPosition()
    {
	return this.objectPosition;
    }
    
    @Override
    public String toString()
    {
	return "Object: " + this.hashCode() + 
		" PosX: " + this.objectPosition.getX() +
		" PosY: " + this.objectPosition.getY() +
		" VectorX: " + this.deltaVector[0] +
		" VectorY: " + this.deltaVector[1] +
		" Velocity: " + this.deltaV;
    }
    @Override
    public final boolean equals(Object o)
    {
	if (o == this)
	{
	    return true;
	}
	
	if (!(o instanceof MovableObj))
	{
	    return false;
	}
	
	MovableObj tempObj = (MovableObj) o;
	return objectPosition.equals(tempObj) && tempObj.deltaV == this.deltaV;
    }
    
    @Override
    public final int hashCode()
    {
	return Objects.hash(this.objectPosition.hashCode(), this.deltaV);
    }
}
