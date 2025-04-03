import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private SimpleIntegerProperty customerID;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty phoneNumber;
    private SimpleLongProperty qid;

    public Customer(int customerID, String firstName, String lastName, String phoneNumber, long qid) {
        this.customerID = new SimpleIntegerProperty(customerID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.qid = new SimpleLongProperty(qid);
    }

    public SimpleIntegerProperty getCustomerID() { return customerID; }
    public SimpleStringProperty getFirstName() { return firstName; }
    public SimpleStringProperty getLastName() { return lastName; }
    public SimpleStringProperty getPhoneNumber() { return phoneNumber; }
    public SimpleLongProperty getQid() { return qid; }
}
