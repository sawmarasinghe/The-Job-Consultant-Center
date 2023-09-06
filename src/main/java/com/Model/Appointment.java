package com.Model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Appointment {
	
	int Appointment_ID;
	
	@NotNull(message = "Consultant_ID should not be null")
	int Consultant_ID;
	String Consultant_name;
	
	 @NotNull(message = "JobSeeker_ID should not be null")
	int JobSeeker_ID;
	String JobSeeker_name;
	
	@NotNull(message = "Appointment_Date should not be null")
	@Future(message = "Appointment_Date should be in the past")
	Date Appointment_Date;
	 
	 @NotBlank(message = "Status should not be blank")
	String Status;
	 
	 @NotBlank(message = "Type should not be blank")
	String Type;
	 
	 @NotBlank(message = "Country should not be blank")
	String Country;
	 
	 @NotBlank(message = "Job should not be blank")
	String Job;
	
	public Appointment(int appointment_ID, int consultant_ID, String consultant_name, int jobSeeker_ID,
			String jobSeeker_name, Date appointment_Date, String status, String type, String country, String job) {
		Appointment_ID = appointment_ID;
		Consultant_ID = consultant_ID;
		Consultant_name = consultant_name;
		JobSeeker_ID = jobSeeker_ID;
		JobSeeker_name = jobSeeker_name;
		Appointment_Date = appointment_Date;
		Status = status;
		Type = type;
		Country = country;
		Job = job;
	}

	public Appointment(int consultant_ID, int jobSeeker_ID, Date appointment_Date, String status, String type,
			String country, String job) {
		Consultant_ID = consultant_ID;
		JobSeeker_ID = jobSeeker_ID;
		Appointment_Date = appointment_Date;
		Status = status;
		Type = type;
		Country = country;
		Job = job;
	}
	
	public Appointment(int appointment_ID, int consultant_ID, int jobSeeker_ID, Date appointment_Date, String status,
			String type, String country, String job) {
		Appointment_ID = appointment_ID;
		Consultant_ID = consultant_ID;
		JobSeeker_ID = jobSeeker_ID;
		Appointment_Date = appointment_Date;
		Status = status;
		Type = type;
		Country = country;
		Job = job;
	}

	

	public int getAppointment_ID() {
		return Appointment_ID;
	}

	public void setAppointment_ID(int appointment_ID) {
		Appointment_ID = appointment_ID;
	}

	public int getConsultant_ID() {
		return Consultant_ID;
	}

	public void setConsultant_ID(int consultant_ID) {
		Consultant_ID = consultant_ID;
	}

	public String getConsultant_name() {
		return Consultant_name;
	}

	public void setConsultant_name(String consultant_name) {
		Consultant_name = consultant_name;
	}

	public int getJobSeeker_ID() {
		return JobSeeker_ID;
	}

	public void setJobSeeker_ID(int jobSeeker_ID) {
		JobSeeker_ID = jobSeeker_ID;
	}

	public String getJobSeeker_name() {
		return JobSeeker_name;
	}

	public void setJobSeeker_name(String jobSeeker_name) {
		JobSeeker_name = jobSeeker_name;
	}

	public Date getAppointment_Date() {
		return Appointment_Date;
	}

	public void setAppointment_Date(Date appointment_Date) {
		Appointment_Date = appointment_Date;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getJob() {
		return Job;
	}

	public void setJob(String job) {
		Job = job;
	}
	
	
	
	

	
}
