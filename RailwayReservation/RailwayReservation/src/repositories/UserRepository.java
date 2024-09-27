package repositories;

import java.sql.*;

import config.DatabaseConnection;
import models.User;

public class UserRepository {
    private Connection connection;

    public UserRepository() {
        connection = DatabaseConnection.getConnection();
    }

    // Method to register a new user
    public void registerUser(User user) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); // In a real application, hash the password
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();
            System.out.println("User registered successfully: " + user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to login a user
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?"; // In a real application, hash the password
        User user = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user; // Returns null if no user found
    }

    // Method to close the connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Additional methods can be added here for updating user details, deleting users, etc.
}

