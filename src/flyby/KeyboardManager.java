/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flyby;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author simon
 */
public class KeyboardManager extends KeyAdapter
{
    boolean inDebug = false;
    public Map<Integer, Boolean> keysPressed;
    
    public KeyboardManager()
    {
	inDebug = false;
	keysPressed = new HashMap<>();
    }
    
    public KeyboardManager(boolean inDebug)
    {
	this.inDebug = inDebug;
	keysPressed = new HashMap<>();
    }
    
    public boolean isKeyPressed(int keycode)
    {
	if (keysPressed.get(keycode) != null && keysPressed.get(keycode))
	{
	    keysPressed.put(keycode, false);
	    return true;
	}
	else
	{
	    return false;
	}
    }
    
    @Override
    public void keyPressed(KeyEvent event)
    {
	char ch = event.getKeyChar();
	keysPressed.put(event.getKeyCode(), true);
	
	if (this.inDebug)
	{
	    System.out.println("Key pressed: " + event.getKeyChar());
	}
    }
    
    @Override
    public void keyReleased(KeyEvent event)
    {
	char ch = event.getKeyChar();
	keysPressed.put(event.getKeyCode(), false);
	
	if (this.inDebug)
	{
	    System.out.println("Key Released: " + event.getKeyChar());
	}
    }
    
    @Override
    public void keyTyped(KeyEvent event)
    {
    }
}
