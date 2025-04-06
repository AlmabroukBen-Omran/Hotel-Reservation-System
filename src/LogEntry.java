public class LogEntry {
    private int logID;
    private String username;
    private String actionType;
    private String timestamp;

    public LogEntry(int logID, String username, String actionType, String timestamp) {
        this.logID = logID;
        this.username = username;
        this.actionType = actionType;
        this.timestamp = timestamp;
    }

    public int getLogID() {
        return logID;
    }

    public String getUsername() {
        return username;
    }

    public String getActionType() {
        return actionType;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
