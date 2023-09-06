package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Model.Appointment;
import com.connectionManager.ConnectionManager;

public class AppointmentDAO {
    ConnectionManager con = new ConnectionManager();

    public void addAppointment(Appointment appointment) {
        try (Connection connection = con.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO appointments (Consultant_ID, JobSeeker_ID, Appointment_DateTime, Status, Type,Country,Job) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, appointment.getConsultant_ID());
            preparedStatement.setInt(2, appointment.getJobSeeker_ID());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(appointment.getAppointment_Date().getTime()));
            preparedStatement.setString(4, appointment.getStatus());
            preparedStatement.setString(5, appointment.getType());
            preparedStatement.setString(6, appointment.getCountry());
            preparedStatement.setString(7, appointment.getJob());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Appointment getAppointmentById(int appointmentId) {
        Appointment appointment = null;
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT appointments.Appointment_ID , appointments.Consultant_ID , consultants.First_Name , consultants.Last_Name , appointments.JobSeeker_ID , jobseekers.First_Name , jobseekers.Last_Name , appointments.Appointment_DateTime , appointments.Status , appointments.Type , appointments.Country , appointments.Job FROM online_appointment_scheduling.appointments INNER JOIN online_appointment_scheduling.consultants ON (appointments.Consultant_ID = consultants.Consultant_ID) INNER JOIN online_appointment_scheduling.jobseekers ON (appointments.JobSeeker_ID = jobseekers.JobSeeker_ID) WHERE Appointment_ID = ?");
            preparedStatement.setInt(1, appointmentId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int consultant_ID = rs.getInt("appointments.Consultant_ID");
                String consultant_name = rs.getString("consultants.First_Name")+ " "+rs.getString("consultants.Last_Name");
                int jobSeeker_ID = rs.getInt("appointments.JobSeeker_ID");
                String jobSeeker_Name = rs.getString("jobseekers.First_Name")+ " "+rs.getString("jobseekers.Last_Name");
                Date appointment_DateTime = rs.getTimestamp("appointments.Appointment_DateTime");
                String status = rs.getString("appointments.Status");
                String type = rs.getString("appointments.Type");
                String Country = rs.getString("appointments.Country");
                String Job = rs.getString("appointments.Job");
                appointment = new Appointment(appointmentId, consultant_ID, consultant_name, jobSeeker_ID, jobSeeker_Name, appointment_DateTime, status, type, Country, Job);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointment;
    }

    public List<Appointment> selectAllAppointments() {
        List<Appointment> appointmentsList = new ArrayList<>();
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT appointments.Appointment_ID , appointments.Consultant_ID , consultants.First_Name , consultants.Last_Name , appointments.JobSeeker_ID , jobseekers.First_Name , jobseekers.Last_Name , appointments.Appointment_DateTime , appointments.Status , appointments.Type , appointments.Country , appointments.Job FROM online_appointment_scheduling.appointments INNER JOIN online_appointment_scheduling.consultants ON (appointments.Consultant_ID = consultants.Consultant_ID) INNER JOIN online_appointment_scheduling.jobseekers ON (appointments.JobSeeker_ID = jobseekers.JobSeeker_ID);");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	int appointmentId =  rs.getInt("appointments.Appointment_ID");
            	int consultant_ID = rs.getInt("appointments.Consultant_ID");
                String consultant_name = rs.getString("consultants.First_Name")+ " "+rs.getString("consultants.Last_Name");
                int jobSeeker_ID = rs.getInt("appointments.JobSeeker_ID");
                String jobSeeker_Name = rs.getString("jobseekers.First_Name")+ " "+rs.getString("jobseekers.Last_Name");
                Date appointment_DateTime = rs.getTimestamp("appointments.Appointment_DateTime");
                String status = rs.getString("appointments.Status");
                String type = rs.getString("appointments.Type");
                String Country = rs.getString("appointments.Country");
                String Job = rs.getString("appointments.Job");
                appointmentsList.add(new Appointment(appointmentId, consultant_ID, consultant_name, jobSeeker_ID, jobSeeker_Name, appointment_DateTime, status, type, Country, Job));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointmentsList;
    }

    public boolean deleteAppointment(int appointmentId) {
        boolean rowDeleted = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM appointments WHERE Appointment_ID = ?")) {
            statement.setInt(1, appointmentId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateAppointment(Appointment appointment) {
        boolean rowUpdated = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE appointments SET Consultant_ID = ?, JobSeeker_ID = ?, Appointment_DateTime = ?, Status = ?, Type = ?,Country = ?,Job = ? WHERE Appointment_ID = ?")) {
            statement.setInt(1, appointment.getConsultant_ID());
            statement.setInt(2, appointment.getJobSeeker_ID());
            statement.setTimestamp(3, new java.sql.Timestamp(appointment.getAppointment_Date().getTime()));
            statement.setString(4, appointment.getStatus());
            statement.setString(5, appointment.getType());
            statement.setString(6, appointment.getCountry());
            statement.setString(7, appointment.getJob());
            statement.setInt(8, appointment.getAppointment_ID());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
