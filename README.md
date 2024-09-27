# RailwayManagementSystem


# Railway Management System

This is a simplified Railway Management System designed to allow users to check train availability, book seats, and manage bookings. It mimics functionalities similar to existing systems like IRCTC. The application is built using plain Java and a simple HTTP server.

## Features

- **User Registration**: Users can register with a username and password.
- **User Login**: Users can log in to their accounts.
- **Add Train**: Admins can add new trains with source and destination information.
- **Check Seat Availability**: Users can check available trains between two stations.
- **Book a Seat**: Users can book seats if availability is greater than zero.
- **Fetch Booking Details**: Users can retrieve their booking details.



## Models

1. **User**: Represents a user of the system.
2. **Train**: Represents a train with details like source, destination, total seats, and available seats.
3. **Booking**: Represents a booking made by a user.

## Repositories

- **UserRepository**: Handles user data operations, including registration and login.
- **TrainRepository**: Manages train data, including adding trains and checking availability.
- **BookingRepository**: Manages booking operations.

## Controllers

- **UserController**: Manages user-related HTTP requests.
- **TrainController**: Manages train-related HTTP requests.

## Services

- **AuthService**: Handles authentication logic for users.
- **BookingService**: Manages the seat booking process, including race condition handling.

## Database Connection

- **DatabaseConnection**: Responsible for establishing a connection to the MySQL/PostgreSQL database.

## Main Class

- **Main**: Starts the HTTP server and initializes the controllers to handle requests.

## Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/IshuKumarCoder/RailwayManagementSystem.git
   cd RailwayManagementSystem





## Models

1. **User**: Represents a user of the system.
2. **Train**: Represents a train with details like source, destination, total seats, and available seats.
3. **Booking**: Represents a booking made by a user.

## Repositories

- **UserRepository**: Handles user data operations, including registration and login.
- **TrainRepository**: Manages train data, including adding trains and checking availability.
- **BookingRepository**: Manages booking operations.

## Controllers

- **UserController**: Manages user-related HTTP requests.
- **TrainController**: Manages train-related HTTP requests.

## Services

- **AuthService**: Handles authentication logic for users.
- **BookingService**: Manages the seat booking process, including race condition handling.

## Database Connection

- **DatabaseConnection**: Responsible for establishing a connection to the MySQL/PostgreSQL database.

## Main Class

- **Main**: Starts the HTTP server and initializes the controllers to handle requests.

## Installation

3.Build the Project

If you're using Maven, run the following command:

mvn clean install



1.Running the Project
Compile and run Main.java to start the HTTP server:

java -cp target/RailwayManagementSystem-1.0-SNAPSHOT.jar Main


Access the application using an HTTP client (e.g., Postman) or a web browser. The API endpoints include:

Register User: POST /register
Login User: POST /login
Add Train: POST /admin/addTrain
Get Seat Availability: GET /trains/availability
Book a Seat: POST /booking/bookSeat
Get Booking Details: GET /booking/details




### Instructions

- Replace `yourusername` with your actual GitHub username.
- Modify any section based on the specific details or features of your project.
- Ensure that the instructions for database setup and other configurations are accurate.

Feel free to ask if you need more adjustments or specific implementations!


