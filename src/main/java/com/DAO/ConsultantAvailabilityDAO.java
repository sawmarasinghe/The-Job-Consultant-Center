package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.Model.Consultant_Availability;
import com.connectionManager.ConnectionManager;


public class ConsultantAvailabilityDAO {
    ConnectionManager con = new ConnectionManager();

    public void addConsultantAvailability(Consultant_Availability availability) throws SQLException {
        try (Connection connection = con.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO consultant_availability (Consultant_ID, Date, Start_Time, End_Time) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, availability.getConsultant_ID());
            preparedStatement.setDate(2, new java.sql.Date(availability.getDate().getTime()));
            preparedStatement.setTime(3, availability.getStart_Time());
            preparedStatement.setTime(4, availability.getEnd_Time());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Consultant_Availability getConsultantAvailabilityById(int availabilityId) {
        Consultant_Availability availability = null;
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM consultant_availability WHERE Availability_ID = ?");
            preparedStatement.setInt(1, availabilityId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int consultantId = rs.getInt("Consultant_ID");
                Date date = rs.getDate("Date");
                Time startTime = rs.getTime("Start_Time");
                Time endTime = rs.getTime("End_Time");
                availability = new Consultant_Availability(availabilityId, consultantId, date, startTime, endTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availability;
    }

    public List<Consultant_Availability> getConsultantAvailabilitiesByConsultantId(int consultantId) {
        List<Consultant_Availability> availabilities = new ArrayList<>();
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM consultant_availability WHERE Consultant_ID = ?");
            preparedStatement.setInt(1, consultantId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int availabilityId = rs.getInt("Availability_ID");
                Date date = rs.getDate("Date");
                Time startTime = rs.getTime("Start_Time");
                Time endTime = rs.getTime("End_Time");
                availabilities.add(new Consultant_Availability(availabilityId, consultantId, date, startTime, endTime));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availabilities;
    }

    public List<Consultant_Availability> selectAllConsultantAvailabilities() {
        List<Consultant_Availability> availabilities = new ArrayList<>();
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM consultant_availability");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int availabilityId = rs.getInt("Availability_ID");
                int consultantId = rs.getInt("Consultant_ID");
                Date date = rs.getDate("Date");
                Time startTime = rs.getTime("Start_Time");
                Time endTime = rs.getTime("End_Time");
                availabilities.add(new Consultant_Availability(availabilityId, consultantId, date, startTime, endTime));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availabilities;
    }

    public boolean deleteConsultantAvailability(int availabilityId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM consultant_availability WHERE Availability_ID = ?")) {
            statement.setInt(1, availabilityId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateConsultantAvailability(Consultant_Availability availability) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE consultant_availability SET Consultant_ID = ?, Date = ?, Start_Time = ?, End_Time = ? WHERE Availability_ID = ?")) {
            statement.setInt(1, availability.getConsultant_ID());
            statement.setDate(2, new java.sql.Date(availability.getDate().getTime()));
            statement.setTime(3, availability.getStart_Time());
            statement.setTime(4, availability.getEnd_Time());
            statement.setInt(5, availability.getAvailability_ID());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
