/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flyby;

/**
 *
 * @author simon
 */
public class PhysicsUtil
{
    private static boolean override;
    
    public PhysicsUtil()
    {
	override = false;
    }
    
    public static double calcSpeedReduce(double speed)
    {
	return slowFactor(speed);
    }
    
    public static int calcSpeedup(int speed)
    {
	return (int) speedFactor(speed);
    }
    
    private static double slowFactor(double speed)
    {
	if(speed < 1)
	{
	    return 0;
	}
	
	return Math.log(speed);
    }
    
    private static double speedFactor(double speed)
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
