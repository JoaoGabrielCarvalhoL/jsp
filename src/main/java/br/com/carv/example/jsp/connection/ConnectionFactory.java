package br.com.carv.example.jsp.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public static Connection getConnection() {
		
		Connection connection = null;
		
		final String driver = "com.mysql.cj.jdbc.Driver"; 
		final String url = "jdbc:mysql://localhost:3306/jsp_project?serverTimezone=UTC";
		final String user = "adm"; 
		final String password = "123456789";
		
		try {
			Class.forName(driver); 
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}

}
