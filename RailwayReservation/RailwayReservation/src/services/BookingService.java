package services;

import java.sql.SQLException;

import models.Booking;
import models.Train;
import repositories.BookingRepository;
import repositories.TrainRepository;

public class BookingService {
    private TrainRepository trainRepository;
    private BookingRepository bookingRepository;

    public BookingService() {
        trainRepository = new TrainRepository();
        bookingRepository = new BookingRepository();
    }

    public synchronized boolean bookSeat(int trainId, int userId) throws SQLException {
        // Check if seats are available
		Train train = trainRepository.getTrainById(trainId);
		if (train != null && train.getAvailableSeats() > 0) {
		    // Proceed with booking
		    // Decrease available seats
		    train.setAvailableSeats(train.getAvailableSeats() - 1);
		    trainRepository.updateTrain(train);

		    // Create a new booking
		    Booking booking = new Booking();
		    booking.setUserId(userId);
		    booking.setTrainId(trainId);
		    bookingRepository.addBooking(booking);

		    return true; // Booking successful
		} else {
		    System.out.println("No available seats to book.");
		    return false; // No seats available
		}
    }
}
