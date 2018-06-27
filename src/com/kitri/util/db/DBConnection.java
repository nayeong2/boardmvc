package com.kitri.util.db;

import java.sql.*;

public class DBConnection {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 
	
	public static Connection makeConnection() throws SQLException {
		Connection con= null;
		con= DriverManager.getConnection("jdbc:oracle:thin:@192.168.18.43:1521:xe", "kitri", "kitri");		
		return con; 
	}
}
