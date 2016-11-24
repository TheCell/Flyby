/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.thecell.flyby;

/**
 *
 * @author simon
 */
public class PhysicsUtil
{
    private boolean override;
    
    public PhysicsUtil()
    {
	override = false;
    }
    
    public double calcSpeedReduce(double speed)
    {
	return slowFactor(speed);
    }
    
    public int calcSpeedup(int speed)
    {
	return (int) speedFactor(speed);
    }
    
    private double slowFactor(double speed)
    {
	if(speed < 1)
	{
	    return 0;
	}
	
	return Math.log(speed);
    }
    
    private double speedFactor(double speed)
    {
	if(speed == 0)
	{
	    return 0;
	}
	
	double newSpeed = Math.pow(speed, 2);
//	double newSpeed = speed++;
	
//	System.out.println("newspeed" + newSpeed);
	
	if(override)
	{
	    return newSpeed;
	}
	else
	{
	    if (newSpeed > 10)
	    {
		return 10;
	    }
	    else
	    {
		return newSpeed;
	    }
	}
    }
}
