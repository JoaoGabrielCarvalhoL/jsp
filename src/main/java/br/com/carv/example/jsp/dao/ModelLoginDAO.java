package br.com.carv.example.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.carv.example.jsp.connection.ConnectionFactory;
import br.com.carv.example.jsp.model.ModelLogin;

public class ModelLoginDAO {

	private Connection connection; 
	
	public ModelLoginDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public boolean validateAuthentication(ModelLogin modelLogin) throws SQLException {
		
		String sql = "SELECT * FROM ModelLogin WHERE login = ? AND password = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, modelLogin.getLogin());
		preparedStatement.setString(2, modelLogin.getPassword());
		ResultSet resultSet  = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			return true; 
		} else {
			return false;
		}
	}
	
	public void saveUser(ModelLogin modelLogin) throws SQLException {
		
		String sql = "INSERT INTO ModelLogin(login, password, name, email) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql); 
		preparedStatement.setString(1, modelLogin.getLogin());
		preparedStatement.setString(2, modelLogin.getPassword());
		preparedStatement.setString(3, modelLogin.getName());
		preparedStatement.setString(4, modelLogin.getEmail());
		preparedStatement.execute(); 
		connection.commit();
		
	}
	
}
