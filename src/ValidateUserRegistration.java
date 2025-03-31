public class ValidateUserRegistration {

    public static boolean isValidFirstName(String firstname) {
        return firstname.matches("^[A-Za-z ]{2,50}$");
    }

    public static boolean isValidLastName(String lastname) {
        return lastname.matches("^[A-Za-z ]{2,50}$");
    }

    public static boolean isValidUsername(String username) {
        return username.matches("^[A-Za-z0-9_]{3,20}$");
    }

    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>?/`~]).{8,}$");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z][A-Za-z0-9-]*(\\.[A-Za-z0-9-]+)*\\.[a-z]{2,63}$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+974\\s\\d{8}$");
    }
}
