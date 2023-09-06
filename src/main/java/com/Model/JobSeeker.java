package com.Model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class JobSeeker {
	
	
	int JobSeeker_ID;
	
	@NotBlank(message = "First Name should not be blank")
	String First_Name;
	
	@NotBlank(message = "Last Name should not be blank")
	String Last_Name;
	
	@NotBlank(message = "Email should not be blank")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
	String Email;
	
	@NotNull(message = "Phone Number should not be null")
	int Phone_Number;
	
	@NotBlank(message = "Desired Country should not be blank")
	String Desired_Country;
	
	@NotBlank(message = "Desired Job Type should not be blank")
	String Desired_Job_Type;
	
	public JobSeeker(int jobSeeker_ID, String first_Name, String last_Name, String email, int phone_Number,
			String desired_Country, String desired_Job_Type) {
		JobSeeker_ID = jobSeeker_ID;
		First_Name = first_Name;
		Last_Name = last_Name;
		Email = email;
		Phone_Number = phone_Number;
		Desired_Country = desired_Country;
		Desired_Job_Type = desired_Job_Type;
	}

	public JobSeeker(String first_Name, String last_Name, String email, int phone_Number, String desired_Country,
			String desired_Job_Type) {
		First_Name = first_Name;
		Last_Name = last_Name;
		Email = email;
		Phone_Number = phone_Number;
		Desired_Country = desired_Country;
		Desired_Job_Type = desired_Job_Type;
	}

	public int getJobSeeker_ID() {
		return JobSeeker_ID;
	}

	public void setJobSeeker_ID(int jobSeeker_ID) {
		JobSeeker_ID = jobSeeker_ID;
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

	public String getDesired_Country() {
		return Desired_Country;
	}

	public void setDesired_Country(String desired_Country) {
		Desired_Country = desired_Country;
	}

	public String getDesired_Job_Type() {
		return Desired_Job_Type;
	}

	public void setDesired_Job_Type(String desired_Job_Type) {
		Desired_Job_Type = desired_Job_Type;
	}
	
}
