package com.SecureTracx;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Background_Test extends JFrame
{
	JLabel l;
	JButton b;
	
	Background_Test() 
	{
		setSize(900,900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		JLabel l1=new JLabel(new ImageIcon("C:/Users/Lenovo/Documents/Java_Project/SecureTracx/src/com/SecureTracx/se_logo.jpg"));
		add(l1);
		l1.setLayout(new FlowLayout());
		
		l=new JLabel("This is the button");
		b=new JButton("Save");
		
		add(l);
		add(b);
		
		setVisible(true);
		setLayout(new FlowLayout());
		
	}
	
	public static void main(String[] args)
	{
		new Background_Test();
	}
	
}
