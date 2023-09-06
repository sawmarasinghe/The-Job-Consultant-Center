package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Model.Users;
import com.connectionManager.ConnectionManager;


public class UsersDAO {
    ConnectionManager con = new ConnectionManager();


    public void addUser(Users user) throws SQLException {
        try (Connection connection = con.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (Username, Password, Role) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Users getUserById(int userId) {
        Users user = null;
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE User_ID = ?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String role = rs.getString("Role");
                user = new Users(userId, username, password, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Users> selectAllUsers() {
        List<Users> users = new ArrayList<>();
        try {
            Connection connection = con.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String role = rs.getString("Role");
                users.add(new Users(userId, username, password, role));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(int userId) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE User_ID = ?")) {
            statement.setInt(1, userId);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateUser(Users user) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = con.getConnection();
                PreparedStatement statement = connection.prepareStatement( "UPDATE users SET Username = ?, Password = ?, Role = ? WHERE User_ID = ?")) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getUser_ID());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public String loginCheck(String username, String password) {
        String islogin = null;
    	try (Connection connection = con.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE Username = ? AND Password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
            	islogin = rs.getString("Role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return islogin;
    }
}
