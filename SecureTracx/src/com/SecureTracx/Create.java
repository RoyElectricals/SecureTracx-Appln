package com.SecureTracx;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalBorders.Flush3DBorder;

import util.JdbcUtil;

public class Create extends JPanel implements ActionListener
{
	JTextField tf1,tf2,tf3,tf4;
	JLabel l,l1,l2,l3,l4;
	JButton b,b1;
	
	boolean a=true;
	boolean s=true;
	boolean d=true;
	
	int flag=1;
	int flag1=1;
	
	static String url="";
	static String db_name="";
	static String pswd="";
	static String user="";
	
	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		Create.url = url;
	}

	public static String getDb_name() {
		return db_name;
	}

	public static void setDb_name(String db_name) {
		Create.db_name = db_name;
	}

	public static String getPswd() {
		return pswd;
	}

	public static void setPswd(String pswd) {
		Create.pswd = pswd;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		Create.user = user;
	}

	final String Driver="com.mysql.jdbc.Driver";
	Connection con=null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public Create()
	{
		
		setSize(600,600);
		
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

		
		l1=new JLabel("DB Name:");
		l2=new JLabel("URL:");
		l3=new JLabel("Password:");
		l4=new JLabel("User ID:");
		
		tf1=new JTextField(300);
		tf2=new JTextField(300);
		tf3=new JTextField(300);
		tf4=new JTextField(300);
		
		b=new JButton("Connection");
		b1=new JButton("Reset");
		
		add(l1);
		l1.setBounds(50, 150, 100, 20);
		l1.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15));
		
		add(tf1);
		tf1.setEditable(true);
		tf1.setBounds(130, 150, 300, 20);
		
		add(l2);
		l2.setBounds(50, 180, 100, 20);
		l2.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15));
		
		add(tf2);
		tf2.setEditable(true);
		tf2.setBounds(130, 180, 300, 20);
		
		add(l3);
		l3.setBounds(50, 210, 100, 20);
		l3.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15));
		
		add(tf3);
		tf3.setEditable(true);
		tf3.setBounds(130, 210, 300, 20);
		
		add(l4);
		l4.setBounds(50, 240, 100, 20);
		l4.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15));
		
		add(tf4);
		tf4.setEditable(true);
		tf4.setBounds(130, 240, 300, 20);
		
		add(b);
		b.setBounds(130, 290, 130, 20);
		b.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15));
		
		b.addActionListener(this);
		
		add(b1);
		b1.setBounds(330, 290, 100, 20);
		b1.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15));

		b1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			
				
				
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText(""); 
				
				Create.db_name=null;
				Create.pswd=null;
				Create.url=null;
				Create.user=null;
				
				
			}
		});  
		
		setLayout(null);
		setVisible(true);
		
	}
	

	public void actionPerformed(ActionEvent e) 
	{
		db_name=tf1.getText();
		url=tf2.getText();
		pswd=tf3.getText();
		user=tf4.getText();
		
		try
		{
			
			Class.forName(Driver);
			con=DriverManager.getConnection(url,user,pswd);
			
			check_Database();
			
			if(d==false)
			{
				check();
				
			}
			else
			{
				new Create();
			}
		}
		catch(SQLException se)
		{
			//se.printStackTrace();
			JOptionPane.showMessageDialog(null, "Plzz insert correct connection info");
		}
		catch (ClassNotFoundException ce) 
		{
			ce.printStackTrace();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try 
			{
				JdbcUtil.cleanup(rs, con, stmt);;
				con.close();
				
				//Create.db_name=null;
				//Create.url=null;
				//Create.pswd=null;
				//Create.user=null;
				
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		
	}
	
	public void check_Database()
	{
		Create c=new Create();
		
		int sub=url.length()-db_name.length();
		if(url.substring(sub,url.length()).equalsIgnoreCase(db_name))
		{
			d=false;
			JOptionPane.showMessageDialog(null, "Connection Successfull");
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Database doesnot match");
		}
	}
	
	public void check()
	{
		try
		{
			String sql2="show tables from "+db_name+";";
			Class.forName(Driver);
			con= DriverManager.getConnection(url,user,pswd);
			stmt=con.prepareStatement(sql2);
			rs=stmt.executeQuery();
			
			ArrayList<String> al2 = new ArrayList<String>();
			while(rs.next())
			{
				al2.add(rs.getString(1));
			}
			String[] arr = new String[al2.size()];
			arr = al2.toArray(arr);
			
			for(int k=0;k<arr.length;k++)
			{
				System.out.println("k= "+ k +"=" + arr[k] + "\n");
			}
			
			if(arr.length==0)
			{
				new Table_1().table_1_method();
			}
			else
			{
				for(int i=0;i<arr.length;i++)
				{
					if(arr[i].equalsIgnoreCase("GPS_Login_data")||arr[i].equalsIgnoreCase("GPS_Raw_data"))
					{
						if(arr[i].equalsIgnoreCase("GPS_Login_Data"))
						{
							a=false;
						}
						else if(arr[i].equalsIgnoreCase("GPS_Raw_Data"))
						{
							s=false;
						}
					}
				}
				if(a==false)
				{
					JOptionPane.showMessageDialog(null, "Table GPS_Login_data Already created");
					for(int j=0;j<arr.length;j++)
					{
						if(arr[j].equalsIgnoreCase("GPS_Raw_Data"))
						{
							flag=0;
						}
					}
					if(flag==1)
					{
						new Table_2();
						new Table_2().dispose();
					}
					
				}
				if(s==false)
				{
					JOptionPane.showMessageDialog(null, "Table GPS_Raw_data already created");
					for(int j=0;j<arr.length;j++)
					{
						if(arr[j].equalsIgnoreCase("GPS_Login_Data"))
						{
							flag1=0;
						}
					}
					if(flag1==1)
					{
						new Table_1().table_1_method();
						new Table_1().dispose();
					}
				}
			}
			
		}
		catch(Exception e3)
		{
			e3.printStackTrace();
			
			
		}
		
	}
	
}
