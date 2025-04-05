import javafx.beans.property.*;

public class Reservation {
    private IntegerProperty reservationID, customerID, roomNumber, paymentRequired;
    private StringProperty checkInDate, checkOutDate, createdBy;
    private FloatProperty deposit;

    public Reservation(int reservationID, int customerID, int roomNumber, String checkInDate, String checkOutDate, float deposit, int paymentRequired, String createdBy) {
        this.reservationID = new SimpleIntegerProperty(reservationID);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.roomNumber = new SimpleIntegerProperty(roomNumber);
        this.checkInDate = new SimpleStringProperty(checkInDate);
        this.checkOutDate = new SimpleStringProperty(checkOutDate);
        this.deposit = new SimpleFloatProperty(deposit);
        this.paymentRequired = new SimpleIntegerProperty(paymentRequired);
        this.createdBy = new SimpleStringProperty(createdBy);
    }

    public IntegerProperty getReservationID() { return reservationID; }
    public IntegerProperty getCustomerID() { return customerID; }
    public IntegerProperty getRoomNumber() { return roomNumber; }
    public StringProperty getCheckInDate() { return checkInDate; }
    public StringProperty getCheckOutDate() { return checkOutDate; }
    public FloatProperty getDeposit() { return deposit; }
    public IntegerProperty getPaymentRequired() { return paymentRequired; }
    public StringProperty getCreatedBy() { return createdBy; }
}
