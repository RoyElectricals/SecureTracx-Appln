package com.SecureTracx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class SplashScreen extends JWindow
{
	BorderLayout bl=new BorderLayout();
	JLabel imgL=new JLabel();
	JPanel p=new JPanel();
	FlowLayout fl=new FlowLayout();
	JProgressBar pb=new JProgressBar();
	ImageIcon imageIcon;
	JLabel proc_name;
	
	public SplashScreen(ImageIcon imageIcon)
	{
		this.imageIcon=imageIcon;
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void jbInit()
	{
		imgL.setIcon(imageIcon);
		this.getContentPane().setLayout(bl);
		p.setLayout(fl);
		p.setBackground(Color.WHITE);
		this.getContentPane().add(imgL, BorderLayout.CENTER);
	    this.getContentPane().add(p, BorderLayout.SOUTH);
	    pb.setPreferredSize(new Dimension(480,15));
	    pb.setStringPainted(true);
	    p.add(pb, null);
	    this.pack();
		
	}
	
	 public void setProgressMax(int max)
	 {
	   pb.setMaximum(max);
	 }

	  public void setProgress(int progress)
	  {
	    final int theProgress = progress;
	    SwingUtilities.invokeLater(new Runnable()
	    {
	      public void run()
	      {
	        pb.setValue(theProgress);
	      }
	    });
	  }

	

}

