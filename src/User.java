public class User {
    private String username;
    private String passwordHash;
    private String role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public User(String username, String passwordHash, String role, String firstName, String lastName, String phoneNumber, String email) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}