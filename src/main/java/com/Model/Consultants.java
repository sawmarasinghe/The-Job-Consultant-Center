package com.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class Consultants {
	int Consultant_ID;
	
	 @NotBlank(message = "First name should not be blank")
	String First_Name;
	 
	 @NotBlank(message = "Last name should not be blank")
	String Last_Name;
	 
	 @NotBlank(message = "Specialization should not be blank")
	String Specialization;
	 
	 @NotBlank(message = "Email should not be blank")
	 @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
	String Email;
	 
	 @NotNull(message = "Phone number should not be null")
	int Phone_Number;
	 
	 @NotBlank(message = "Countries should not be blank")
	String Countries;
	 
	 @NotBlank(message = "Jobs should not be blank")
	String Jobs;
	
	public Consultants(int consultant_ID, String first_Name, String last_Name, String specialization, String email,
			int phone_Number, String countries, String jobs) {
		Consultant_ID = consultant_ID;
		First_Name = first_Name;
		Last_Name = last_Name;
		Specialization = specialization;
		Email = email;
		Phone_Number = phone_Number;
		Countries = countries;
		Jobs = jobs;
	}
	
	public Consultants(String first_Name, String last_Name, String specialization, String email, int phone_Number,
			String countries, String jobs) {
		First_Name = first_Name;
		Last_Name = last_Name;
		Specialization = specialization;
		Email = email;
		Phone_Number = phone_Number;
		Countries = countries;
		Jobs = jobs;
	}

	public int getConsultant_ID() {
		return Consultant_ID;
	}

	public void setConsultant_ID(int consultant_ID) {
		Consultant_ID = consultant_ID;
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

	public String getSpecialization() {
		return Specialization;
	}

	public void setSpecialization(String specialization) {
		Specialization = specialization;
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

	public String getCountries() {
		return Countries;
	}

	public void setCountries(String countries) {
		Countries = countries;
	}

	public String getJobs() {
		return Jobs;
	}

	public void setJobs(String jobs) {
		Jobs = jobs;
	}
	
	
	
}
