package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Model.Consultants;
import com.connectionManager.ConnectionManager;

public class ConsultantsDAO {
    ConnectionManager con = new ConnectionManager();


    public void addConsultant(Consultants consultant) throws SQLException {
        try (Connection connection = con.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO consultants (First_Name, Last_Name, Specialization, Email, Phone_Number, Countries, Jobs) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, consultant.getFirst_Name());
            preparedStatement.setString(2, consultant.getLast_Name());
            preparedStatement.setString(3, consultant.getSpecialization());
            preparedStatement.setString(4, consultant.getEmail());
            preparedStatement.setInt(5, consultant.getPhone_Number());
            preparedStatement.setString(6, consultant.getCountries());
            preparedStatement.setString(7, consultant.getJobs());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Consultants getConsultantById(int consultantId) {
        Consultants consultant = null;
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM consultants WHERE Consultant_ID = ?");
            preparedStatement.setInt(1, consultantId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String first_Name = rs.getString("First_Name");
                String last_Name = rs.getString("Last_Name");
                String specialization = rs.getString("Specialization");
                String email = rs.getString("Email");
                int phone_Number = rs.getInt("Phone_Number");
                String countries = rs.getString("Countries");
                String jobs = rs.getString("Jobs");
                consultant = new Consultants(consultantId, first_Name, last_Name, specialization, email, phone_Number, countries, jobs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consultant;
    }

    public List<Consultants> selectAllConsultants() {
        List<Consultants> consultantsList = new ArrayList<>();
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM consultants");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int consultantId = rs.getInt("Consultant_ID");
                String first_Name = rs.getString("First_Name");
                String last_Name = rs.getString("Last_Name");
                String specialization = rs.getString("Specialization");
                String email = rs.getString("Email");
                int phone_Number = rs.getInt("Phone_Number");
                String countries = rs.getString("Countries");
                String jobs = rs.getString("Jobs");
                consultantsList.add(new Consultants(consultantId, first_Name, last_Name, specialization, email, phone_Number, countries, jobs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consultantsList;
    }

    public boolean deleteConsultant(int consultantId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM consultants WHERE Consultant_ID = ?")) {
            statement.setInt(1, consultantId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateConsultant(Consultants consultant) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE consultants SET First_Name = ?, Last_Name = ?, Specialization = ?, Email = ?, Phone_Number = ?, Countries = ?, Jobs = ? WHERE Consultant_ID = ?")) {
            statement.setString(1, consultant.getFirst_Name());
            statement.setString(2, consultant.getLast_Name());
            statement.setString(3, consultant.getSpecialization());
            statement.setString(4, consultant.getEmail());
            statement.setInt(5, consultant.getPhone_Number());
            statement.setString(6, consultant.getCountries());
            statement.setString(7, consultant.getJobs());
            statement.setInt(8, consultant.getConsultant_ID());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
