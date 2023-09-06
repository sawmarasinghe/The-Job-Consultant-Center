package com.connectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private String jdbcURL = "jdbc:mysql://localhost:3306/online_appointment_scheduling?useSSL=false";
	private String JdbcUsername = "root";
	private String jdbcPassword = "hy6Per@lex";

	//create connection
	public Connection getConnection(){
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,JdbcUsername,jdbcPassword);
		}
		catch(SQLException e) {
			e.printStackTrace();
		
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		
		}
		return connection;

	}

}
