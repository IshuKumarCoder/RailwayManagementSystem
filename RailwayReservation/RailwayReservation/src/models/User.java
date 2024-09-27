package models;

public class User {
    private int id;
    private String username;
    private String password; // In a real application, hash the password
    private String role; // e.g. "admin" or "user"

    // Default constructor
    public User() {}

    // Constructor with parameters
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        setPassword(password); // Use setter for hashing
        this.role = role;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // Here you should implement a method to hash the password.
        // For example, using BCrypt:
        this.password = hashPassword(password);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        // Basic validation for role
        if (role.equals("admin") || role.equals("user")) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Role must be 'admin' or 'user'");
        }
    }

    // Method to hash passwords (stub implementation)
    private String hashPassword(String password) {
        // You can use a library like BCrypt for hashing
        // For example:
        // return BCrypt.hashpw(password, BCrypt.gensalt());
        return password; // Placeholder for demonstration
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

