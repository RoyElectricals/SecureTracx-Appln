package com.featchData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import com.mysql.jdbc.Connection;

import util.JdbcUtil;

public class GPS_Data_Featching {
	static boolean b=false;
	public void data_featching(){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs;
		ArrayList<String> al = new ArrayList<String>();
		//ArrayList<String> al1 = new ArrayList<String>();
		
		try{
			con=(Connection) JdbcUtil.getMySqlConnection();
					String sql = "select distinct (IMEI) from GPS_Login_Data";
					st=(PreparedStatement) con.prepareStatement(sql);							
					rs = st.executeQuery();
					while(rs.next())
					{
					al.add(rs.getString(1));
					}
					String arr[] = new String[al.size()];
					arr = al.toArray(arr);  
					for(int i =0; i< arr.length; i++)
					{
						//System.out.println(arr[i]);
						String sql1 = "select data from GPS_Raw_Data where IMEI = "+arr[i]+"";
						st = con.prepareStatement(sql1);
						rs = st.executeQuery();
						ArrayList<String> al1 = new ArrayList<String>();
						 
						while(rs.next())
						{
							al1.add(rs.getString(1));
						}
						for(String str2: al1)
						{
							//System.out.println(str2);
						}
						String db_name = "Data_Of_"+arr[i]+"_IMEI";
						String sql2 = "show tables"; 
						st = con.prepareStatement(sql2);
						rs = st.executeQuery();
						ArrayList<String> al2 = new ArrayList<String>();
						while(rs.next())
						{
							al2.add(rs.getString(1));
						}
						String[] arr1 = new String[al2.size()];
						arr1 = al2.toArray(arr1);
						//System.out.println(db_name);
						
						for(int j= 0; j<arr1.length; j++)
						{
							b = db_name.equals(arr1[j]);
							if(b==true)
							{
								break;
							}
							
						}
						if( b== false)
						{
							String sql3 = "create table "+db_name+"(data varchar(100), IMEI varchar(16))";
							st = con.prepareStatement(sql3);
							st.execute();
							String sql4 = "insert into "+db_name+" (data,IMEI) values(?,?)";
							st = con.prepareStatement(sql4);
							for(String str2: al1)
							{
								st.setString(1, str2);
								st.setString(2, arr[i]);
								st.execute();
							}
							
						}
						else
						{
							String sql3 = "delete from "+db_name+"";
							st = con.prepareStatement(sql3);
							st.execute();
							String sql4 = "insert into "+db_name+" (data,IMEI) values(?,?)";
							st = con.prepareStatement(sql4);
							for(String str2: al1)
							{
								st.setString(1, str2);
								st.setString(2, arr[i]);
								st.execute();
							}
						}
							
						
					}
				
					
					
				}catch (Exception e){
					e.printStackTrace();
				}
				finally{
					JdbcUtil.cleanup(st, con);
				}
		
	}
}
