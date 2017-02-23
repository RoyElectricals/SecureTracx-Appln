package com.SecureTracx;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//import com.mysql.jdbc.PreparedStatement;

import util.JdbcUtil;

public class Table_2 extends JFrame
{
	JFrame f;
	JTextField tf1,tf2,tf3,tf4,tf5;
	JLabel l,l1,l2,l3,l4,l5;
	
	static String url="";
	static String db_name="";
	static String pswd="";
	static String user="";

	
	String tab,att1,att2,att3,att4;
	
	
	
	Connection con=null;
	PreparedStatement st=null;
	
	Table_2() throws SQLException
	{
		setSize(500,500);
		
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
		
		l=new JLabel(new ImageIcon(img));
		add(l);
		l.setBounds(130, 0, 200, 110);

		
		l1=new JLabel("Table:");
		l2=new JLabel("Attribute:");
		l3=new JLabel("Attribute:");
		l4=new JLabel("Attribute:");
		l5=new JLabel("Attribute:");
			
		tf1=new JTextField(300);
		tf2=new JTextField(300);
		tf3=new JTextField(300);
		tf4=new JTextField(300);
		tf5=new JTextField(300);
			
			
		add(l1);
		l1.setBounds(50, 150, 100, 20);
		l1.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15));
			
		add(tf1);
		tf1.setText("GPS_Raw_Data");
		tf1.setBounds(130, 150, 300, 20);
	
		add(l2);
		l2.setBounds(50, 180, 100, 20);
		l2.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15));
			
		add(tf2);
		tf2.setText("Raw_Data");
		tf2.setBounds(130, 180, 300, 20);
			
		add(l3);
		l3.setBounds(50, 210, 100, 20);
		l3.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15));
			
		add(tf3);
		tf3.setText("IMEI");
		tf3.setBounds(130, 210, 300, 20);
			
		add(l4);
		l4.setBounds(50, 240, 100, 20);
		l4.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15));
			
		add(tf4);
		tf4.setText("Date");
		tf4.setBounds(130, 240, 300, 20);
			
		add(l5);
		l5.setBounds(50, 270, 100, 20);
		l5.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15));
			
		add(tf5);
		tf5.setText("Time");
		tf5.setBounds(130, 270, 300, 20);
		
		setMinimumSize(new Dimension(500, 0));
		setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
		setResizable(false);
		SplashScreenMain.centerWindow(this);

		setLayout(null);
		setVisible(true);
	
		try
		{
			con = JdbcUtil.getMySqlConnection();
			
			
			String sql="create table GPS_Raw_Data(Raw_Data varchar(255), IMEI varchar(255), Date DATE, Time TIME);";
			st= con.prepareStatement(sql);
			st.execute();
			
			
			JOptionPane.showMessageDialog(null, "Table GPS_Raw_Data Created");
			
			dispose();
			
			//Create.db_name=null;
			//Create.url=null;
			//Create.pswd=null;
			//Create.user=null;
	
			
			

		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			//System.out.println(e1);
			//JOptionPane.showMessageDialog(null, "Create new database");
		}
		
	}


}


