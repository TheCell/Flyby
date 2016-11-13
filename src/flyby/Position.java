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
public class Position
{
    private int x;
    private int y;
    
    public Position()
    {
	this(0, 0);
    }
    
    public Position(int x, int y)
    {
	this.x = x;
	this.y = y;
    }
    
    public void updatePos(int x, int y)
    {
	this.x = x;
	this.y = y;
    }
    
    public int getX()
    {
	return this.x;
    }
    
    public int getY()
    {
	return this.y;
    }
    
    public void setX(int x)
    {
	this.x = x;
    }
    
    public void setY(int y)
    {
	this.y = y;
    }
    
    @Override
    public boolean equals(Object o)
    {
	if (o == this)
	{
	    return true;
	}
	
	if (!(o instanceof Position))
	{
	    return false;
	}
	
	Position tempObj = (Position) o;
	return tempObj.x == this.x && tempObj.y == this.y;
    }
    
    @Override
    public int hashCode()
    {
	return Objects.hash(this.x, this.y);
    }
}
