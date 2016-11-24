/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.thecell.flyby;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author simon
 */
public class WindowMgr
{
    JFrame window;

    public WindowMgr()
    {
	window = new JFrame();
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setPreferredSize(new Dimension(300, 300));
	/*JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(575, 100));
        window.getContentPane().add(emptyLabel, BorderLayout.CENTER);*/
	
    }
    
    public WindowMgr(String windowName)
    {
	window = new JFrame(windowName);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setPreferredSize(new Dimension(300, 300));
	/*JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(575, 100));
        window.getContentPane().add(emptyLabel, BorderLayout.CENTER);*/
    }

    public JFrame getWindow()
    {
	return window;
    }
    
}
