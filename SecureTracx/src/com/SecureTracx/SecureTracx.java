package com.SecureTracx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SecureTracx extends JPanel
{
	Server server=new Server();
	About about=new About();
	Create create=new Create();
	
	SecureTracx()
	{
		super(new GridLayout(1, 1));
	
		JTabbedPane tp=new JTabbedPane();
		ImageIcon icon=createImageIcon("b_logo.png");
		
		tp.addTab("<html><font size=5>"+"DB_Connection"+"</font></html>", icon, create);
		tp.setMnemonicAt(0, KeyEvent.VK_1);
		
		tp.addTab("<html><font size=5>"+"Server"+"</font></html>",icon, server);
		tp.setMnemonicAt(1, KeyEvent.VK_2);
		
		tp.addTab("<html><font size=5>"+"About"+"</font></html>",icon, about);
		tp.setMnemonicAt(2, KeyEvent.VK_2);
		
		add(tp);
		tp.setBounds(50,50, 500, 300);
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
	}
	
	
	
	public static void main(String[] args)
	{
		
		try
	    {
	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
		
		new SplashScreenMain();
	
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				UIManager.put("Swing.boldMetal",Boolean.FALSE);
				createAndShowGUI();

			}		
		});
	} 
	
	
	
	public static void createAndShowGUI()
	{
		ImageIcon logo =createLogoIcon("logo.png");
		
		SecureTracx st=new SecureTracx();
		JFrame f=new JFrame("SecureTracx (V~1.1.0)");
		f.setIconImage(logo.getImage());
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new SecureTracx(),BorderLayout.CENTER);
		f.pack();
		f.setSize(500, 500);
		f.setMinimumSize(new Dimension(500, 0));
		f.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
		f.setResizable(false);
		SplashScreenMain.centerWindow(f);
		f.setVisible(true);
		
	}    
	
	protected static ImageIcon createImageIcon(String path)
	{
		java.net.URL imageURL=SecureTracx.class.getResource(path);
		if(imageURL!=null)
		{
			return new ImageIcon(imageURL);
		}
		else
		{
			System.err.println("Couldn't find file:"+path);
			return null;
		}
	}
	
	
	protected static ImageIcon createLogoIcon(String path)
	{
		java.net.URL imageURL=SecureTracx.class.getResource(path);
		if(imageURL!=null)
		{
			return new ImageIcon(imageURL);
		}
		else
		{
			System.err.println("Couldn't find file:"+path);
			return null;
		}
	}

}

