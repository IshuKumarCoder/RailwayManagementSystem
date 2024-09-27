package models;

public class Booking {
    private int id;        // Unique identifier for the booking
    private int userId;    // ID of the user who made the booking
    private int trainId;   // ID of the train for which the booking is made

    // Default constructor
    public Booking() {}

    // Constructor with parameters
    public Booking(int id, int userId, int trainId) {
        this.id = id;
        this.userId = userId;
        this.trainId = trainId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", userId=" + userId +
                ", trainId=" + trainId +
                '}';
    }
}
