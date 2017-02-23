package com.SecureTracx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class SecureProgress extends JWindow
{
	JLabel l;
	JProgressBar pb;
	static int min=0;
	static int max=100;
	
	
	SecureProgress()
	{
		JFrame f=new JFrame();
		
		setSize(300,150);
		setMinimumSize(new Dimension(300, 0));
		setMaximumSize(new Dimension(150, Integer.MAX_VALUE));
		f.setResizable(false);
		getContentPane().setBackground(new Color(60,171,225));
		setVisible(true);
		setLayout(null);

		
		l=new JLabel("SecureTracx Server");
		add(l);
		l.setBounds(60,50, 200,40);
		l.setFont(new Font("Arial",Font.BOLD,20));
		
		pb=new JProgressBar();
		pb.setMinimum(min);
		pb.setMaximum(max);
		pb.setForeground(new Color(17,195,29));
		add(pb);
		pb.setBounds(70,90,150,15);
		
	   		

	}
	

	public void updateBar(int value)
	{
		pb.setValue(value);
		
	}
	
	
	
}