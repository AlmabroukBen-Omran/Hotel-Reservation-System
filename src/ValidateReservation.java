public class ValidateReservation {

    public static boolean isValidCustomerID(int customerID) {
        return String.valueOf(customerID).matches("^\\d{1,3}$");
    }

    public static boolean isValidRoomNumber(int roomNumber) {
        return String.valueOf(roomNumber).matches("^\\d{3}$");
    }

    public static boolean isValidDeposit(float deposit) {
        return String.valueOf(deposit).matches("^\\s*\\d+(\\.\\d{1,2})?\\s*$");
    }

    public static boolean isValidCreatedBy(String createdBy) {
        return createdBy.matches("^[A-Za-z0-9_]{3,20}$");
    }
}
