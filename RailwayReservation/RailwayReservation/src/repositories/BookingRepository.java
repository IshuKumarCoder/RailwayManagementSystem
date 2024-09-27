package repositories;
import java.sql.*;
import java.util.ArrayList;
import config.DatabaseConnection;
import models.Booking;

public class BookingRepository {
    private Connection connection;

    public BookingRepository() {
        connection = DatabaseConnection.getConnection();
    }

    // Method to add a booking to the database
    public void addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (userId, trainId) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getTrainId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve booking details by booking ID
    public Booking getBookingById(int bookingId) {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setUserId(rs.getInt("userId"));
                booking.setTrainId(rs.getInt("trainId"));
                return booking;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Booking not found
    }

    // Method to retrieve all bookings for a specific user
    public ArrayList<Booking> getBookingsByUserId(int userId) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        String sql = "SELECT * FROM bookings WHERE userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setUserId(rs.getInt("userId"));
                booking.setTrainId(rs.getInt("trainId"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings; // Return list of bookings
    }
}

