package com.Model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class Appointment_Note {
	int Note_ID;
	@NotBlank(message = "Appointment_ID should not be blank")
	@NotEmpty(message = "Appointment_ID should not be empty")
	int Appointment_ID;
	@NotBlank(message = "Note_Date should not be blank")
	@NotEmpty(message = "Note_Date should not be empty")
	Date Note_Date;
	@NotBlank(message = "Note_Text should not be blank")
	@NotEmpty(message = "Note_Text should not be empty")
	String Note_Text;
	
	public Appointment_Note(int note_ID, int appointment_ID, Date note_Date, String note_Text) {
		Note_ID = note_ID;
		Appointment_ID = appointment_ID;
		Note_Date = note_Date;
		Note_Text = note_Text;
	}
	public Appointment_Note(int appointment_ID, Date note_Date, String note_Text) {
		Appointment_ID = appointment_ID;
		Note_Date = note_Date;
		Note_Text = note_Text;
	}
	public int getNote_ID() {
		return Note_ID;
	}
	public void setNote_ID(int note_ID) {
		Note_ID = note_ID;
	}
	public int getAppointment_ID() {
		return Appointment_ID;
	}
	public void setAppointment_ID(int appointment_ID) {
		Appointment_ID = appointment_ID;
	}
	public Date getNote_Date() {
		return Note_Date;
	}
	public void setNote_Date(Date note_Date) {
		Note_Date = note_Date;
	}
	public String getNote_Text() {
		return Note_Text;
	}
	public void setNote_Text(String note_Text) {
		Note_Text = note_Text;
	}
}
