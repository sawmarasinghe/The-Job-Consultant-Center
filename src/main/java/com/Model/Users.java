package com.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class Users {
	
	private int User_ID;
	@NotBlank(message = "User name should not be blank")
	@NotEmpty(message = "User name should not be empty")
	private String Username;
	@NotBlank(message = "Password should not be blank")
	@NotEmpty(message = "Password should not be empty")
	private String Password;
	@NotBlank(message = "Role should not be blank")
	@NotEmpty(message = "Role should not be empty")
	private String Role;
	
	public Users(int user_ID, String username, String password, String role) {
		User_ID = user_ID;
		Username = username;
		Password = password;
		Role = role;
	}
	public Users(String username, String password, String role) {
		Username = username;
		Password = password;
		Role = role;
	}
	public int getUser_ID() {
		return User_ID;
	}
	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	
	

}
