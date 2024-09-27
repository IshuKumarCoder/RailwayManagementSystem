package controllers;
import com.sun.net.httpserver.HttpServer;

import repositories.TrainRepository;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;

public class TrainController {
    private TrainRepository trainRepository;

    public TrainController(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public void handleRequests(HttpServer server) {
        server.createContext("/admin/addTrain", new AddTrainHandler());
        server.createContext("/trains/availability", new AvailabilityHandler());
        server.createContext("/booking/bookSeat", new BookSeatHandler());
    }

    private class AddTrainHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream inputStream = exchange.getRequestBody();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder requestBody = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    requestBody.append(line);
                }

                String[] trainInfo = requestBody.toString().split(",");
                if (trainInfo.length == 4) {
                    String source = trainInfo[0];
                    String destination = trainInfo[1];
                    int totalSeats = Integer.parseInt(trainInfo[2]);
                    int availableSeats = Integer.parseInt(trainInfo[3]);

                    Train train = new Train();
                    train.setSource(source);
                    train.setDestination(destination);
                    train.setTotalSeats(totalSeats);
                    train.setAvailableSeats(availableSeats);
                    trainRepository.addTrain(train);

                    String response = "Train added successfully.";
                    exchange.sendResponseHeaders(200, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } else {
                    String response = "Invalid train data.";
                    exchange.sendResponseHeaders(400, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } else {
                String response = "Method Not Allowed";
                exchange.sendResponseHeaders(405, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    private class AvailabilityHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String query = exchange.getRequestURI().getQuery();
                String[] params = query.split("&");
                String source = null, destination = null;

                for (String param : params) {
                    String[] pair = param.split("=");
                    if (pair[0].equals("source")) {
                        source = pair[1];
                    } else if (pair[0].equals("destination")) {
                        destination = pair[1];
                    }
                }

                if (source != null && destination != null) {
                    List<Train> trains = trainRepository.getAvailableTrains(source, destination);
                    StringBuilder response = new StringBuilder();

                    for (Train train : trains) {
                        response.append("Train ID: ").append(train.getId())
                                .append(", Source: ").append(train.getSource())
                                .append(", Destination: ").append(train.getDestination())
                                .append(", Available Seats: ").append(train.getAvailableSeats())
                                .append("\n");
                    }

                    exchange.sendResponseHeaders(200, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.toString().getBytes());
                    os.close();
                } else {
                    String response = "Invalid query parameters.";
                    exchange.sendResponseHeaders(400, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } else {
                String response = "Method Not Allowed";
                exchange.sendResponseHeaders(405, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    private class BookSeatHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream inputStream = exchange.getRequestBody();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder requestBody = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    requestBody.append(line);
                }

                String[] bookingInfo = requestBody.toString().split(",");
                if (bookingInfo.length == 2) {
                    int trainId = Integer.parseInt(bookingInfo[0]);
                    int userId = Integer.parseInt(bookingInfo[1]);

                    boolean success = trainRepository.bookSeat(trainId, userId);
                    if (success) {
                        String response = "Seat booked successfully.";
                        exchange.sendResponseHeaders(200, response.length());
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    } else {
                        String response = "Booking failed. No available seats.";
                        exchange.sendResponseHeaders(400, response.length());
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }
                } else {
                    String response = "Invalid booking data.";
                    exchange.sendResponseHeaders(400, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } else {
                String response = "Method Not Allowed";
                exchange.sendResponseHeaders(405, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
        TrainRepository trainRepository = new TrainRepository();
        TrainController trainController = new TrainController(trainRepository);

        trainController.handleRequests(server);

        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Train server started on port 8001");
    }
}
