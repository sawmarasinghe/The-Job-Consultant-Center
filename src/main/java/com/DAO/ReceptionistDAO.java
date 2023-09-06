package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Model.Receptionist;
import com.connectionManager.ConnectionManager;

public class ReceptionistDAO {
    ConnectionManager con = new ConnectionManager();


    public void addReceptionist(Receptionist receptionist) throws SQLException {
        try (Connection connection = con.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO receptionists (First_Name, Last_Name, Email, Phone_Number) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, receptionist.getFirst_Name());
            preparedStatement.setString(2, receptionist.getLast_Name());
            preparedStatement.setString(3, receptionist.getEmail());
            preparedStatement.setInt(4, receptionist.getPhone_Number());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Receptionist getReceptionistById(int receptionistId) {
        Receptionist receptionist = null;
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM receptionists WHERE Receptionist_ID = ?");
            preparedStatement.setInt(1, receptionistId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                String email = rs.getString("Email");
                int phoneNumber = rs.getInt("Phone_Number");
                receptionist = new Receptionist(receptionistId, firstName, lastName, email, phoneNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receptionist;
    }

    public List<Receptionist> selectAllReceptionists() {
        List<Receptionist> receptionists = new ArrayList<>();
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM receptionists");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int receptionistId = rs.getInt("Receptionist_ID");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                String email = rs.getString("Email");
                int phoneNumber = rs.getInt("Phone_Number");
                receptionists.add(new Receptionist(receptionistId, firstName, lastName, email, phoneNumber));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receptionists;
    }

    public boolean deleteReceptionist(int receptionistId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM receptionists WHERE Receptionist_ID = ?")) {
            statement.setInt(1, receptionistId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateReceptionist(Receptionist receptionist) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE receptionists SET First_Name = ?, Last_Name = ?, Email = ?, Phone_Number = ? WHERE Receptionist_ID = ?")) {
            statement.setString(1, receptionist.getFirst_Name());
            statement.setString(2, receptionist.getLast_Name());
            statement.setString(3, receptionist.getEmail());
            statement.setInt(4, receptionist.getPhone_Number());
            statement.setInt(5, receptionist.getReceptionist_ID());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}

