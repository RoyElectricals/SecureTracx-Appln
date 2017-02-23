package com.SecureTracx.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.JdbcUtil;

 class ClientTask implements Runnable {
	private final Socket clientSocket;
	int counter = 0;
    public static byte[] main_data = new byte[36];
	

	public static byte[] getMain_data() {
		return main_data;
	}

	public static void setMain_data(byte[] main_data) {
		ClientTask.main_data = main_data;
	}

	ClientTask(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		System.out.println("Got a client with Ip" + clientSocket.getInetAddress() + "port" + clientSocket.getPort());
		try {
			/* Get Data From Client */
			PreparedStatement st = null;
			Connection con = null;
			int red = -1;
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			byte[] b22 = new byte[10];
			byte[] imei_byte = new byte[8];
			String imei_string ="";
			String loginData ="";
		
		while(true){
				
			if(counter == 0){
				byte[] buffer = new byte[100];
				red = is.read(buffer, 0, 18);
				byte[] redData = new byte[18];
				System.arraycopy(buffer, 0, redData, 0, red);
				b22[0] = redData[0];
				b22[1] = redData[1];
				b22[2] = (byte) 5;
				b22[3] = redData[3];
				b22[4] = redData[12];
				b22[5] = redData[13];
				b22[6] = redData[14];
				b22[7] = redData[15];
				b22[8] = redData[16];
				b22[9] = redData[17];
				os.write(b22);
				System.arraycopy(redData, 4, imei_byte, 0, 8);
				for (byte ime : imei_byte) {
				imei_string =imei_string+String.format("%02X", ime);
				}
				for (byte b : redData) {
				loginData = loginData+String.format("%02X", b);
				}
				System.out.print("Login Data:" +loginData +"\t");
				System.out.print(" : " +imei_string +"\t");
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				String date1 = dateFormat.format(date);
				System.out.println(date1 +"\t");
				
				/*JDBC Connection code*/
				try{
					con=JdbcUtil.getMySqlConnection();
							String sql = "insert into GPS_Login_Data(Login_Data, IMEI, Date, Time) values(?, ?, ?, ?)";
							st=(PreparedStatement) con.prepareStatement(sql);							
							st.setString(1, loginData);
							st.setString(2, imei_string);
							st.setString(3, date1);
							st.setString(4, date1);
							st.execute();
						
						}catch (Exception e){
							e.printStackTrace();
						}
						finally{
							JdbcUtil.cleanup(st, con);
						}
				
				counter = counter+1;
			}
			else{
					byte[] redData = new byte[36];
					byte[] buffer = new byte[36];
				
					red = is.read(buffer, 0, 36);
					System.arraycopy(buffer, 0, redData, 0, red);
					
					buffer = null;
					String rawData="";
					for(byte b: redData)
					{
						rawData = rawData +String.format("%02X", b);
					}
					ClientTask.setMain_data(redData);
					
					System.out.print("Original data:" +rawData +"\t");
					System.out.print(" : " +imei_string +"\t");
					SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date d = new Date();
					String dt = df.format(d);
					System.out.println(dt +"\t");
					TranslatorOfGps_Data t = new TranslatorOfGps_Data();
					/*JDBC Connection code*/
					try{
						con=JdbcUtil.getMySqlConnection();
								String sql = "insert into GPS_Raw_Data(Raw_Data, IMEI, Date, Time) values(?, ?, ?, ?) ";
								st=(PreparedStatement)con.prepareStatement(sql);							
								st.setString(1, rawData);
								st.setString(2, imei_string);
								st.setString(3, dt);
								st.setString(4, dt);
								st.execute();
							
							}catch (Exception e){
								e.printStackTrace();
							}
							finally{
								JdbcUtil.cleanup(st, con);
							}
					
					
			
				}
			}
			
		}  catch (IOException e) {
			System.out.println("Client disconnect" + clientSocket.getInetAddress());
		}
	}
}


