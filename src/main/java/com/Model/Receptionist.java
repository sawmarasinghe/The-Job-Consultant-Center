package com.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class Receptionist {

	int Receptionist_ID;
	
	@NotBlank(message = "First name should not be blank")
	String First_Name;
	
	@NotBlank(message = "Last name should not be blank")
	String Last_Name;
	
	@NotBlank(message = "Email should not be blank")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
	String Email;
	
	@NotNull(message = "Phone number should not be null")
	int Phone_Number;
	
	public Receptionist(int receptionist_ID, String first_Name, String last_Name, String email, int phone_Number) {
		Receptionist_ID = receptionist_ID;
		First_Name = first_Name;
		Last_Name = last_Name;
		Email = email;
		Phone_Number = phone_Number;
	}
	
	public Receptionist(String first_Name, String last_Name, String email, int phone_Number) {
		First_Name = first_Name;
		Last_Name = last_Name;
		Email = email;
		Phone_Number = phone_Number;
	}
	
	public int getReceptionist_ID() {
		return Receptionist_ID;
	}
	public void setReceptionist_ID(int receptionist_ID) {
		Receptionist_ID = receptionist_ID;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getPhone_Number() {
		return Phone_Number;
	}
	public void setPhone_Number(int phone_Number) {
		Phone_Number = phone_Number;
	}
}
