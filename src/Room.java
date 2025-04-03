import javafx.beans.property.*;

public class Room {
    private final IntegerProperty roomNumber;
    private final StringProperty type;
    private final FloatProperty rate;
    private final StringProperty isReserved;

    public Room(int roomNumber, String type, float rate, boolean isReserved) {
        this.roomNumber = new SimpleIntegerProperty(roomNumber);
        this.type = new SimpleStringProperty(type);
        this.rate = new SimpleFloatProperty(rate);
        this.isReserved = new SimpleStringProperty(isReserved ? "Yes" : "No");
    }

    public IntegerProperty getRoomNumber() {
        return roomNumber;
    }

    public StringProperty getType() {
        return type;
    }

    public FloatProperty getRate() {
        return rate;
    }

    public StringProperty getIsReserved() {
        return isReserved;
    }
}
