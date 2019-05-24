package ntheurer.dodatesapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserClass {
    private String className;
    private List<Assignment> assignments;
    private String colorString;
    private String uniqueID = UUID.randomUUID().toString();

    public UserClass(String className, String colorString) {
        this.className = className;
        assignments = new ArrayList<>();
        this.colorString = colorString;
    }

    public UserClass(String className, List<Assignment> assignments, String colorString) {
        this.className = className;
        this.assignments = assignments;
        this.colorString = colorString;
        updateAssignmentUserClasses();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
        updateAssignmentUserClasses();
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
        updateAssignmentUserClasses();
    }

    public String getColorString() {
        return colorString;
    }

    public void setColorString(String colorString) {
        this.colorString = colorString;
        updateAssignmentUserClasses();
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String id) {
        this.uniqueID = id;
    }

    public void addSingleAssignment(Assignment assignment) {
        assignments.add(assignment);
        updateAssignmentUserClasses();
    }

    private void updateAssignmentUserClasses() {
        for (Assignment assignment : assignments) {
            Log.e("UserClass",
                    "setting user class \"" + this.getClassName() +
                            "\" for the assignment\"" + assignment.getAssignmentName() + "\"");
            assignment.setUserClass(this);
        }
    }
}
