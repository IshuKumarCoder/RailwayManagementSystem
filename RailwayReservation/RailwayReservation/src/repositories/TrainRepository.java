package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Train;

public class TrainRepository {
    private Connection connection;

    public TrainRepository() {
        connection = DatabaseConnection.getConnection();
    }

    // Method to add a new train
    public void addTrain(Train train) {
        String sql = "INSERT INTO trains (source, destination, totalSeats, availableSeats) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, train.getSource());
            stmt.setString(2, train.getDestination());
            stmt.setInt(3, train.getTotalSeats());
            stmt.setInt(4, train.getAvailableSeats());
            stmt.executeUpdate();
            System.out.println("Train added successfully: " + train.getSource() + " to " + train.getDestination());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get available trains between source and destination
    public List<Train> getAvailableTrains(String source, String destination) {
        List<Train> trains = new ArrayList<>();
        String sql = "SELECT * FROM trains WHERE source = ? AND destination = ? AND availableSeats > 0";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, source);
            stmt.setString(2, destination);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Train train = new Train();
                train.setId(rs.getInt("id"));
                train.setSource(rs.getString("source"));
                train.setDestination(rs.getString("destination"));
                train.setTotalSeats(rs.getInt("totalSeats"));
                train.setAvailableSeats(rs.getInt("availableSeats"));
                trains.add(train);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trains; // Returns the list of available trains
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

    public Train getTrainById(int trainId) {
        String sql = "SELECT * FROM trains WHERE id = ?";
        Train train = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, trainId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                train = new Train();
                train.setId(rs.getInt("id"));
                train.setSource(rs.getString("source"));
                train.setDestination(rs.getString("destination"));
                train.setTotalSeats(rs.getInt("totalSeats"));
                train.setAvailableSeats(rs.getInt("availableSeats"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching train by ID: " + e.getMessage());
        }

        return train; // Returns the train object or null if not found
    }
    public void updateTrain(Train train) {
        String sql = "UPDATE trains SET source = ?, destination = ?, totalSeats = ?, availableSeats = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, train.getSource());
            stmt.setString(2, train.getDestination());
            stmt.setInt(3, train.getTotalSeats());
            stmt.setInt(4, train.getAvailableSeats());
            stmt.setInt(5, train.getId()); // Set the ID for the WHERE clause
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Train updated successfully: " + train.getId());
            } else {
                System.err.println("No train found with ID: " + train.getId());
            }
        } catch (SQLException e) {
            System.err.println("Error updating train: " + e.getMessage());
        }
    }

    // Additional methods can be added here for updating train details, deleting trains, etc.
}

