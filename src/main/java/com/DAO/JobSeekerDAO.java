package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Model.JobSeeker;
import com.connectionManager.ConnectionManager;

public class JobSeekerDAO {
    ConnectionManager con = new ConnectionManager();

    
    public int getLastId(){	
    	int id =0;
    	try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT JobSeeker_ID FROM jobseekers ORDER BY JobSeeker_ID DESC LIMIT 1");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("JobSeeker_ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
	}
    
    public void addJobSeeker(JobSeeker jobSeeker) throws SQLException {
        try (Connection connection = con.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO jobseekers (First_Name, Last_Name, Email, Phone_Number, Desired_Country, Desired_Job_Type) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, jobSeeker.getFirst_Name());
            preparedStatement.setString(2, jobSeeker.getLast_Name());
            preparedStatement.setString(3, jobSeeker.getEmail());
            preparedStatement.setInt(4, jobSeeker.getPhone_Number());
            preparedStatement.setString(5, jobSeeker.getDesired_Country());
            preparedStatement.setString(6, jobSeeker.getDesired_Job_Type());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JobSeeker getJobSeekerById(int jobSeekerId) {
        JobSeeker jobSeeker = null;
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM jobseekers WHERE JobSeeker_ID = ?");
            preparedStatement.setInt(1, jobSeekerId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                String email = rs.getString("Email");
                int phoneNumber = rs.getInt("Phone_Number");
                String desiredCountry = rs.getString("Desired_Country");
                String desiredJobType = rs.getString("Desired_Job_Type");
                jobSeeker = new JobSeeker(jobSeekerId, firstName, lastName, email, phoneNumber, desiredCountry, desiredJobType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobSeeker;
    }

    public List<JobSeeker> selectAllJobSeekers() {
        List<JobSeeker> jobSeekers = new ArrayList<>();
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM jobseekers");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int jobSeekerId = rs.getInt("JobSeeker_ID");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                String email = rs.getString("Email");
                int phoneNumber = rs.getInt("Phone_Number");
                String desiredCountry = rs.getString("Desired_Country");
                String desiredJobType = rs.getString("Desired_Job_Type");
                jobSeekers.add(new JobSeeker(jobSeekerId, firstName, lastName, email, phoneNumber, desiredCountry, desiredJobType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobSeekers;
    }

    public boolean deleteJobSeeker(int jobSeekerId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM jobseekers WHERE JobSeeker_ID = ?")) {
            statement.setInt(1, jobSeekerId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateJobSeeker(JobSeeker jobSeeker) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement( "UPDATE jobseekers SET First_Name = ?, Last_Name = ?, Email = ?, Phone_Number = ?, Desired_Country = ?, Desired_Job_Type = ? WHERE JobSeeker_ID = ?")) {
            statement.setString(1, jobSeeker.getFirst_Name());
            statement.setString(2, jobSeeker.getLast_Name());
            statement.setString(3, jobSeeker.getEmail());
            statement.setInt(4, jobSeeker.getPhone_Number());
            statement.setString(5, jobSeeker.getDesired_Country());
            statement.setString(6, jobSeeker.getDesired_Job_Type());
            statement.setInt(7, jobSeeker.getJobSeeker_ID());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}

