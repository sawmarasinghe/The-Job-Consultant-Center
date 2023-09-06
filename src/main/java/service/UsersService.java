package service;

import java.sql.SQLException;
import java.util.List;

import com.DAO.UsersDAO;
import com.Model.Users;

public class UsersService {

    private UsersDAO usersDAO;

    public UsersService() {
        usersDAO = new UsersDAO();
    }

    public void addUser(Users user) throws SQLException {
        usersDAO.addUser(user);
    }

    public Users getUserById(int userId) {
        return usersDAO.getUserById(userId);
    }

    public List<Users> getAllUsers() {
        return usersDAO.selectAllUsers();
    }

    public boolean deleteUser(int userId) throws SQLException {
        return usersDAO.deleteUser(userId);
    }

    public boolean updateUser(Users user) throws SQLException {
        return usersDAO.updateUser(user);
    }

    public String loginCheck(String username, String password) {
        return usersDAO.loginCheck(username, password);
    }
}
