package com.SecureTracx.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import com.SecureTracx.Server;

import util.JdbcUtil;

public class ServerListener  {
	
	
	
	//String s1=s.str1;
	//int i=Integer.valueOf(s1);
    //public int port=i;
    //String str =s.str;
    public Inet4Address ip;
    public String str;
    public int port;
    public static String msg="";
    
	public void startServer() {
		final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);

		Runnable serverTask = new Runnable() {

			public void run()  {
				try {
					port = Server.port1;
				    str =Server.str;
					ip = (Inet4Address) Inet4Address.getByName(str);
					//System.out.println("IP ="+s.str +"port =" +s.port1);
					ServerSocket serverSocket = new ServerSocket(port, 50, ip);
					JOptionPane.showMessageDialog(null, "Server Start listing on IP = "+ip+" and Port = "+port+"\n"+"Waiting for client to connect....");
					
					//System.out.println();
					//System.out.println("Waiting for clients to connect and Server...."+"\n"+ "start listining on ip = " +ip +" port = " +port);
					while (true) {
						Socket clientSocket = serverSocket.accept();
						clientProcessingPool.submit(new ClientTask(clientSocket));
					}
				} catch (IOException e) {
					//System.err.println("Unable to process client request");
					JOptionPane.showMessageDialog(null, "Unable to process client request");
					e.printStackTrace();
				}
			}
		};
		Thread serverThread = new Thread(serverTask);
		serverThread.start();
	}

	}