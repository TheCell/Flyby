/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flyby;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author simon
 */
public class teschtitest
{
    public void printBox(final int height, final int width){
       String variablewidth = "";
       String boxBody = "";
       int tryCount1 = 0;
       int tryCount2 = 0;
       int tryCount3 = 0;
       while(width > tryCount1) {
           variablewidth += "#";
           tryCount1++;
       }
       while(width - 2 > tryCount2) {
           boxBody = boxBody + " ";
           tryCount2++;
       }

       System.out.println(variablewidth);
       do {
           System.out.println("#" + boxBody + "#");
           tryCount3++;
       } while (height - 2 > tryCount3);
       System.out.println(variablewidth);
   }
    
    public void printBox2(final int height, final int width)
    {
	String varWidth = "";
	String boxBody = "";
	
	for( int i = 0; i < width; i++)
	{
	    varWidth += "#";
	}
	
	for (int i = 0; i < width - 2; i++)
	{
	    boxBody += " ";
	}
	
	System.out.println(varWidth);
	for(int i = 0; i < height - 2; i++)
	{
	    System.out.println("#" + boxBody + "#");
	}
	System.out.println(varWidth);
    }
    
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(175, 100));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
