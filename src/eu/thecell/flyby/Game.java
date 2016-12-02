/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.thecell.flyby;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 * Game Class that initializes the Window, and delegates Keypresses
 * and Gamecontrollers.
 * @author simon
 */
public class Game
{
    private boolean inDebug = false;
    private WindowMgr windowManager;
    private JFrame window;
    private KeyboardManager myManager;
    private GameController myGameController;
    private PlayerActionsHandler pActionHandler;
    
    public Game()
    {
	inDebug = false;
    }
    
    public Game(boolean inDebug)
    {
	this.inDebug = inDebug;
    }
    
    /**
     * Starts the initialisation of this class, Creates the Window and adds the Keylistener
     */
    public void init()
    {
	windowManager = new WindowMgr("Mygame");
	window = windowManager.getWindow();
	window.setMinimumSize(new Dimension(1024, 768));
//	myManager = new KeyboardManager(inDebug);
	myManager = new KeyboardManager();
	window.addKeyListener(myManager);
	
	myGameController = new GameController();
	pActionHandler = myGameController.getActionHandler();
	
	window.add(myGameController.getDrawPanel());
	window.setVisible(true);
	window.pack();
    }
    
    /**
     * register the pressed Keys at the ActionHandler. Pressed keys are registered at the
     * KeyboardManager.
     */
    public void controlls()
    {
	if(myManager.isKeyPressed(KeyEvent.VK_W) || myManager.isKeyPressed(KeyEvent.VK_UP))
	{
	    pActionHandler.activateAction(PlayerActionsHandler.possibleActions.UP);
	}
	
	if(myManager.isKeyPressed(KeyEvent.VK_S) || myManager.isKeyPressed(KeyEvent.VK_DOWN))
	{
	    pActionHandler.activateAction(PlayerActionsHandler.possibleActions.DOWN);
	}
	
	if(myManager.isKeyPressed(KeyEvent.VK_A) || myManager.isKeyPressed(KeyEvent.VK_LEFT))
	{
	    pActionHandler.activateAction(PlayerActionsHandler.possibleActions.LEFT);
	}
	
	if(myManager.isKeyPressed(KeyEvent.VK_D) || myManager.isKeyPressed(KeyEvent.VK_RIGHT))
	{
	    pActionHandler.activateAction(PlayerActionsHandler.possibleActions.RIGHT);
	}
	
	if(myManager.isKeyPressed(KeyEvent.VK_SPACE))
	{
	    pActionHandler.activateAction(PlayerActionsHandler.possibleActions.SHOOT);
	}
    }
    
    /**
     * Tells the Gamecontroller to apply updates.
     */
    public void update()
    {
	myGameController.update();
    }
    
    /**
     * repaint the whole window.
     */
    public void display()
    {
	window.repaint();
	window.pack();
    }
}
