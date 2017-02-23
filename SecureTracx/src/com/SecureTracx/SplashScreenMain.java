package com.SecureTracx;

import java.awt.Dimension;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SplashScreenMain
{
	SplashScreen splash;
	
	public SplashScreenMain() 
	{
	    splashScreenInit();
	    for (int i = 0; i <= 100; i++)
	    {
	      splash.setProgress(i);  
	      try
	      {
			Thread.sleep(10);
		  } 
	      catch (InterruptedException e)
	      {
			  e.printStackTrace();
		  }
	    }
	  
	    splash.dispose();
	    
	  }

	

	  private void splashScreenInit()
	  {
	    ImageIcon myImage = new ImageIcon(com.SecureTracx.SplashScreenMain.class.getResource("se_logo.png"));
	    splash = new SplashScreen(myImage);
	    splash.setLocationRelativeTo(null);
	    splash.setProgressMax(100);
	    splash.setVisible(true);
	 
	 
	  }
	  
	  public static void centerWindow(Window win)
		{
	        Dimension dim = win.getToolkit().getScreenSize();
	        win.setLocation(dim.width/2 - win.getWidth()/2,
	                dim.height/2 - win.getHeight()/2);
	    }

}
