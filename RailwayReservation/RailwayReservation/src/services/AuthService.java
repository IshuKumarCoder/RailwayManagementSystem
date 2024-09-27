package services;

import java.util.Base64;

import models.User;
import repositories.UserRepository;

public class AuthService {
    private UserRepository userRepository;

    public AuthService() {
        userRepository = new UserRepository();
    }

    public String login(String username, String password) {
        User user = userRepository.login(username, password);
        if (user != null) {
            // Simulate token generation (in real application, use JWT)
            String token = generateToken(user.getId(), user.getRole());
            return token;
        }
        return null;
    }

    private String generateToken(int userId, String role) {
        // Simple token creation using Base64 (for demonstration purposes)
        String tokenData = userId + ":" + role;  // e.g., "1:admin"
        return Base64.getEncoder().encodeToString(tokenData.getBytes());
    }

    // Additional methods for registration, token validation, etc.
}
