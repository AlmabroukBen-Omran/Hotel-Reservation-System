public class ValidateCustomerRegistration {

    public static boolean isValidFirstName(String firstname) {
        return firstname.matches("^[A-Za-z ]{2,50}$");
    }

    public static boolean isValidLastName(String lastname) {
        return lastname.matches("^[A-Za-z ]{2,50}$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+974\\s\\d{8}$");
    }

    public static boolean isValidQid(String qid) {
        return qid.matches("^\\d{11}$");
    }

}
