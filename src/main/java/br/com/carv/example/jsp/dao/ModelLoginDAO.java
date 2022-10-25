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
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return true;
		} else {
			return false;
		}
	}

	public ModelLogin saveUser(ModelLogin modelLogin) throws SQLException {
		
		if (modelLogin.isNew()) {
			String sql = "INSERT INTO ModelLogin(login, password, name, email) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, modelLogin.getLogin());
			preparedStatement.setString(2, modelLogin.getPassword());
			preparedStatement.setString(3, modelLogin.getName());
			preparedStatement.setString(4, modelLogin.getEmail());
			preparedStatement.execute();
			connection.commit();
			
		} else {
			updateUser(modelLogin);
		}
		return findByLogin(modelLogin.getLogin());

	}


	public boolean loginValid(String login) throws SQLException {
		String sql = "SELECT COUNT(1) > 0 as EXIST FROM ModelLogin WHERE login = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, login);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.next();
		return resultSet.getBoolean("EXIST");
	
	}
	
	public ModelLogin findByLogin(String login) throws SQLException {
		String sql = "SELECT * FROM ModelLogin WHERE login = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, login);
		ModelLogin modelLogin = new ModelLogin();
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setName(resultSet.getString("name"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setPassword(resultSet.getString("password"));

		}

		return modelLogin;
	}
	
	public void updateUser(ModelLogin modelLogin) throws SQLException {
		String sql = "UPDATE ModelLogin SET login = ?, password = ?, name = ?, email = ? WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, modelLogin.getLogin());
		preparedStatement.setString(2, modelLogin.getPassword());
		preparedStatement.setString(3, modelLogin.getName());
		preparedStatement.setString(4, modelLogin.getEmail());
		preparedStatement.setLong(5, modelLogin.getId());
		preparedStatement.executeUpdate();
		connection.commit();
	}
	
	public void deleteUser(Long id) throws SQLException {
		String sql = "DELETE FROM ModelLogin WHERE id = ?"; 
		PreparedStatement preparedStatement = connection.prepareStatement(sql); 
		preparedStatement.setLong(1, id);
		preparedStatement.execute(); 
		connection.commit();
	}

}
