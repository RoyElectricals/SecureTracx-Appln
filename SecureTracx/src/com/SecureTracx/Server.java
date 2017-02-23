package com.SecureTracx;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.SecureTracx.server.ServerListener;


public class Server extends JPanel
{
	JLabel l1,l2,l3;
	JTextField tf1,tf2;
	JButton b;
	
	public static String str;
	public static String str1;
    public static int port1;
	public Server() 
	{
		
		setLayout(null);
		
		Image img=null;
		try
		{
			File logo=new File("C:/Users/Lenovo/Documents/Java_Project/SecureTracx/src/com/SecureTracx/logo.png");
			img=ImageIO.read(logo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		l1=new JLabel(new ImageIcon(img));
		add(l1);
		l1.setBounds(130, 0, 200, 110);
		
		l2=new JLabel("ServerIP:");
		l3=new JLabel("Port No.:");
		
		tf1=new JTextField(300);
		tf2=new JTextField(300);
		
		b=new JButton("Listener");
		
		
		add(l2);
		l2.setBounds(50,150,100, 20);
		l2.setFont(new Font("Serif",Font.ITALIC | Font.BOLD,15));
		
		add(tf1);
		tf1.setBounds(130,150,300,20);
		
		add(l3);
		l3.setBounds(50,180, 100, 20);
		l3.setFont(new Font("Serif",Font.ITALIC | Font.BOLD,15));
		
		add(tf2);
		tf2.setBounds(130,180,300,20);
		
		add(b);
		b.setBounds(230, 230, 100, 20);
		b.setFont(new Font("Serif",Font.ITALIC | Font.BOLD,15));
		
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				str=tf1.getText();
				str1=tf2.getText();
				port1 = Integer.parseInt(str1);
				
				new ServerListener().startServer();
				
				tf1.setText("");
				tf2.setText("");
				
			}
		});
		
		setVisible(true);
		
	}
	

	
}
