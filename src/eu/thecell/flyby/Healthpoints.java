package eu.thecell.flyby;

import java.util.Objects;

/**
 * Keeps track of the Healthpoints, default Value is 100.
 * @author simon
 */
public class Healthpoints
{
    private int healthpoints;
    
    public Healthpoints()
    {
	this( 100 );
    }
    
    /**
     * Set the Healthpoints.
     * @param health 
     */
    public Healthpoints(int health)
    {
	this.healthpoints = health;
    }
    
    /**
     * Override the Healthpoints with new Value.
     * @param health 
     */
    public void setHealthpoints(int health)
    {
	this.healthpoints = health;
    }
    
    /**
     * Reduce the Health by the given amount of Health.
     * @param damage 
     */
    public void damageHealth(int damage)
    {
	this.healthpoints -= damage;
    }
    
    /**
     * The currently saved Healthpoints
     * @return int actual Healthpoints
     */
    public int getHealth()
    {
	return this.healthpoints;
    }
    
    /**
     * Checks if Health is still above 0.
     * @return boolean true if still alive
     */
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
    
    /**
     * Checks is Health is the same as the given Object.
     * @param o
     * @return 
     */
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
    
    /**
     * returns hashCode
     * @return 
     */
    @Override
    public final int hashCode()
    {
	return Objects.hash(this.healthpoints);
    }
}
