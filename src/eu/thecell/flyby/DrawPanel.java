/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.thecell.flyby;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author simon
 */
public class DrawPanel extends JPanel
{
    private boolean inDebug = false;
    private int leftTest = 0;
    private int rightTest = 5;
    private GameObjects allGameObjects;
    
    public DrawPanel()
    {
	this(false);
    }
    
    public DrawPanel(boolean debug)
    {
//	this.setPreferredSize(new Dimension(1024, 768));
	this(debug, new GameObjects());
    }
    
    public DrawPanel(boolean debug, GameObjects gameObjs)
    {
	this.inDebug = debug;
	this.allGameObjects = gameObjs;
    }
    
    public void setGameObjects(GameObjects gameObjs)
    {
	this.allGameObjects = gameObjs;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	
	allGameObjects.drawObjects(g);
    }

}
