public class Report {
    private int reportID;
    private String type;
    private String submittedBy;
    private String creationDateAndTime;
    private String content;

    public Report(int reportID, String type, String submittedBy, String creationDateAndTime, String content) {
        this.reportID = reportID;
        this.type = type;
        this.submittedBy = submittedBy;
        this.creationDateAndTime = creationDateAndTime;
        this.content = content;
    }

    public int getReportID() { return reportID; }
    public String getType() { return type; }
    public String getSubmittedBy() { return submittedBy; }
    public String getCreationDateAndTime() { return creationDateAndTime; }
    public String getContent() { return content; }
}
