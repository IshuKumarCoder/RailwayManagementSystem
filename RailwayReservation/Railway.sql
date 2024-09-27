-- Create database
CREATE DATABASE IF NOT EXISTS railway_db;

-- Use the newly created database
USE railway_db;

-- Create Users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,  -- Use hashed passwords
    role ENUM('admin', 'user') NOT NULL
);

-- Create Trains table
CREATE TABLE IF NOT EXISTS trains (
    id INT AUTO_INCREMENT PRIMARY KEY,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    totalSeats INT NOT NULL,
    availableSeats INT NOT NULL,
    CHECK (availableSeats <= totalSeats)  -- Ensure available seats do not exceed total seats
);

-- Create Bookings table
CREATE TABLE IF NOT EXISTS bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    trainId INT NOT NULL,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (trainId) REFERENCES trains(id) ON DELETE CASCADE,
    UNIQUE (userId, trainId)  -- Prevent double booking by the same user for the same train
);

-- Optionally, insert some initial data for testing

-- Insert sample users
INSERT INTO users (username, password, role) VALUES 
('admin', 'admin_password', 'admin'),  -- Use a hashed password in a real app
('user1', 'user1_password', 'user');

-- Insert sample trains
INSERT INTO trains (source, destination, totalSeats, availableSeats) VALUES 
('Station A', 'Station B', 100, 100),
('Station B', 'Station C', 150, 150),
('Station A', 'Station C', 200, 200);
