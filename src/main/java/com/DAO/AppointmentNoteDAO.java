package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.Model.Appointment_Note;
import com.connectionManager.ConnectionManager;


public class AppointmentNoteDAO {
	
    ConnectionManager con = new ConnectionManager();

    public void addAppointmentNote(Appointment_Note note) throws SQLException {
        try (Connection connection = con.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO appointment_notes (Appointment_ID, Note_Date, Note_Text) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, note.getAppointment_ID());
            preparedStatement.setDate(2, new java.sql.Date(note.getNote_Date().getTime()));
            preparedStatement.setString(3, note.getNote_Text());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Appointment_Note getAppointmentNoteById(int noteId) {
        Appointment_Note note = null;
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM appointment_notes WHERE Note_ID = ?");
            preparedStatement.setInt(1, noteId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                Date noteDate = rs.getDate("Note_Date");
                String noteText = rs.getString("Note_Text");
                note = new Appointment_Note(noteId, appointmentId, noteDate, noteText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return note;
    }

    public List<Appointment_Note> getAppointmentNotesByAppointmentId(int appointmentId) {
        List<Appointment_Note> notes = new ArrayList<>();
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM appointment_notes WHERE Appointment_ID = ?");
            preparedStatement.setInt(1, appointmentId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int noteId = rs.getInt("Note_ID");
                Date noteDate = rs.getDate("Note_Date");
                String noteText = rs.getString("Note_Text");
                notes.add(new Appointment_Note(noteId, appointmentId, noteDate, noteText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }

    public boolean updateAppointmentNote(Appointment_Note note) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE appointment_notes SET Appointment_ID = ?, Note_Date = ?, Note_Text = ? WHERE Note_ID = ?")) {
            statement.setInt(1, note.getAppointment_ID());
            statement.setDate(2, new java.sql.Date(note.getNote_Date().getTime()));
            statement.setString(3, note.getNote_Text());
            statement.setInt(4, note.getNote_ID());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean deleteAppointmentNoteById(int noteId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM appointment_notes WHERE Note_ID = ?")) {
            statement.setInt(1, noteId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

	
}

