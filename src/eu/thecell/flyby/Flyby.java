/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.thecell.flyby;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author simon
 */
public class Flyby
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
	boolean inDebug = false;
	
	Set<String> parameters = new TreeSet<>();
	Collections.addAll(parameters, args);
	System.out.println(parameters);
	
	for (String param : parameters)
	{
	    if (param.equals("-debug"))
	    {
		inDebug = true;
	    }
	}
		
	/*
	if (args.length > 0)
	{
	    for(String param : args)
	    {
		if (param.equals("-debug"))
		{
		    inDebug = true;
		}
	    }
	}
	*/
	
	Game flyGame = new Game(inDebug);
	flyGame.init();
	boolean gameRuns = true;
	
	do
	{
	    flyGame.controlls();
	    flyGame.update();
	    flyGame.display();
	}
	while (gameRuns);
	
    }
    
}
