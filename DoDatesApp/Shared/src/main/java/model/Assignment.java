package model;

import java.util.UUID;

public class Assignment {
    private String assignmentName;
    private String dueDate;
    private String doDate;
    private UserClass userClass;
    private String assignmentID;

    public Assignment(String assignmentName, String dueDate) {
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
        this.assignmentID = UUID.randomUUID().toString();
    }

    public Assignment(String assignmentName, String dueDate, String doDate, UserClass userClass) {
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
        this.doDate = doDate;
        this.userClass = userClass;
        this.assignmentID = UUID.randomUUID().toString();
    }

    public Assignment(String assignmentName, String dueDate, String doDate){
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
        this.doDate = doDate;
        this.userClass = null;
        this.assignmentID = UUID.randomUUID().toString();
    }

    public Assignment(String assignmentID, String assignmentName, String dueDate, String doDate){
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
        this.doDate = doDate;
        this.userClass = null;
        this.assignmentID = assignmentID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDoDate() {
        return doDate;
    }

    public void setDoDate(String dueDate) {
        this.doDate = doDate;
    }

    public UserClass getUserClass() {
        return userClass;
    }

    public void setUserClass(UserClass userClass) {
        this.userClass = userClass;
    }

    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }
}
