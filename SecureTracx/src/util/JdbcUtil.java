package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.SecureTracx.Create;
public class JdbcUtil {
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public static Connection getMySqlConnection() throws SQLException{
		//Create c = new Create();
		String url = Create.getUrl();
		String user = Create.getUser();
		String passwd = Create.getPswd();
		Connection con=DriverManager.getConnection(url,user,passwd);
		return con;
	}
	public static void cleanup(Statement st,Connection con){
		try{
			if(st!=null) st.close();
			if(con!=null) con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
public static void cleanup(ResultSet rs,Connection con,Statement st){
	try{
		if(rs!=null) rs.close();
		if(con!=null) con.close();
		if(st!=null) st.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}
}