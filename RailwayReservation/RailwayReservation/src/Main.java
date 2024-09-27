import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        UserController userController = new UserController();
        TrainController trainController = new TrainController();
        
        userController.handleRequests(server);
        trainController.handleRequests(server);

        server.start();
        System.out.println("Server is running on port 8080...");
    }
}