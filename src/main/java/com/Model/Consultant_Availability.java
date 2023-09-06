package com.Model;
import java.sql.Time;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class Consultant_Availability {
	int Availability_ID;
	@NotBlank(message = "Consultant_ID should not be blank")
	@NotEmpty(message = "Consultant_ID should not be empty")
	int Consultant_ID;
	@NotBlank(message = "Date should not be blank")
	@NotEmpty(message = "Date should not be empty")
	Date Date;
	@NotBlank(message = "Start_Time should not be blank")
	@NotEmpty(message = "Start_Time should not be empty")
	Time Start_Time;
	@NotBlank(message = "End_Time should not be blank")
	@NotEmpty(message = "End_Time should not be empty")
	Time End_Time;
	
	public Consultant_Availability(int availability_ID, int consultant_ID, java.util.Date date, Time start_Time,
			Time end_Time) {
		Availability_ID = availability_ID;
		Consultant_ID = consultant_ID;
		Date = date;
		Start_Time = start_Time;
		End_Time = end_Time;
	}
	public Consultant_Availability(int consultant_ID, java.util.Date date, Time start_Time, Time end_Time) {
		Consultant_ID = consultant_ID;
		Date = date;
		Start_Time = start_Time;
		End_Time = end_Time;
	}
	public int getAvailability_ID() {
		return Availability_ID;
	}
	public void setAvailability_ID(int availability_ID) {
		Availability_ID = availability_ID;
	}
	public int getConsultant_ID() {
		return Consultant_ID;
	}
	public void setConsultant_ID(int consultant_ID) {
		Consultant_ID = consultant_ID;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public Time getStart_Time() {
		return Start_Time;
	}
	public void setStart_Time(Time start_Time) {
		Start_Time = start_Time;
	}
	public Time getEnd_Time() {
		return End_Time;
	}
	public void setEnd_Time(Time end_Time) {
		End_Time = end_Time;
	}
}
