package br.com.carv.example.jsp.model;

import java.io.Serializable;

public class ModelLogin implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	private Long id; 
	
	private String name; 
	
	private String email; 
	
	private String login; 
	
	private String password; 
	
	public ModelLogin() {
		
	}
	
	public ModelLogin(Long id, String name, String email, String login, String password) {
		this.id = id;
		this.name = name; 
		this.email = email; 
		this.login = login; 
		this.password = password;
	}
	
	public ModelLogin(String name, String email, String login, String password) {
		this.name = name; 
		this.email = email; 
		this.login = login; 
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isNew() {
		if(this.id == null) {
			return true;
		} else {
			return false;
		}
	}

}
