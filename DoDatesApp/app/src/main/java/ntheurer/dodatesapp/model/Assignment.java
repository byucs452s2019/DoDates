package ntheurer.dodatesapp.model;

import java.util.Date;

public class Assignment {
    private String assignmentName;
    private String dueDate;
    private UserClass userClass;

    public Assignment(String assignmentName, String dueDate) {
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
    }

    public Assignment(String assignmentName, String dueDate, UserClass userClass) {
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
        this.userClass = userClass;
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

    public UserClass getUserClass() {
        return userClass;
    }

    public void setUserClass(UserClass userClass) {
        this.userClass = userClass;
    }
}
