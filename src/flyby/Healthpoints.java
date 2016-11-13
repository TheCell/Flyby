package flyby;

import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simon
 */
public class Healthpoints
{
    private int healthpoints;
    
    public Healthpoints()
    {
	this( 100 );
    }
    
    public Healthpoints(int health)
    {
	this.healthpoints = health;
    }

    public void setHealthpoints(int health)
    {
	this.healthpoints = health;
    }
    
    public void damageHealth(int health)
    {
	this.healthpoints -= health;
    }
    
    public int getHealth()
    {
	return this.healthpoints;
    }
    
    public boolean isAlive()
    {
	if(this.healthpoints > 0)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }
    
    @Override
    public final boolean equals(Object o)
    {
	if(o == this)
	{
	    return true;
	}
	
	if(!(o instanceof Healthpoints))
	{
	    return false;
	}
	
	Healthpoints tempHp = (Healthpoints) o;
	return this.healthpoints == tempHp.healthpoints;
    }
    
    @Override
    public final int hashCode()
    {
	return Objects.hash(this.healthpoints);
    }
}
