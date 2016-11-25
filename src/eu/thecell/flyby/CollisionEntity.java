/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.thecell.flyby;

import java.awt.Rectangle;
import java.util.Objects;

/**
 *
 * @author simon
 */
public class CollisionEntity
{
    private Rectangle collisionRect;
    
    public CollisionEntity(int x, int y)
    {
	this(x, y, 0, 0);
    }
    
    public CollisionEntity(int x, int y, int width, int height)
    {
	collisionRect = new Rectangle(x, y, width, height);
    }
    
    public boolean hasCollisionWith(CollisionEntity ce)
    {
	// checks for top left and bottom right
	if(this.collisionRect.contains(ce.getX(), ce.getY()))
	{
	    return true;
	}
	if(this.collisionRect.contains(ce.getX() + ce.getWidth(),ce.getY() + ce.getHeight()))
	{
	    return true;
	}
	
	return false;
    }
    
    public int getX()
    {
	return this.collisionRect.x;
    }
    
    public int getY()
    {
	return this.collisionRect.y;
    }
    
    public int getWidth()
    {
	return this.collisionRect.width;
    }
    
    public int getHeight()
    {
	return this.collisionRect.height;
    }
    
    @Override
    public boolean equals(Object o)
    {
	if(this == o)
	{
	    return true;
	}
	
	if(!(o instanceof CollisionEntity))
	{
	    return false;
	}
	
	return this.collisionRect.equals(this);
    }
    
    @Override
    public int hashCode()
    {
	return Objects.hashCode(this.collisionRect.hashCode());
    }
}
