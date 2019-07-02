package ntheurer.dodatesapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import model.Assignment;
import model.UserClass;
import ntheurer.dodatesapp.ServerProxy;

public class SingletonModel {
    private static SingletonModel singleton = new SingletonModel();
    private String currUserID;
    private List<UserClass> userClassList;
    private Map<String, UserClass> userClassMap; //key = id(string), value = userClass
    private List<String> colorList;
    private Map<String, String> colorMap;
    private String currClassID;
    private boolean editingClass;
    private Map<String, List<Assignment>> assignmentByDueDateMap; //key = date, value = assignment
    private Map<String, List<Assignment>> assignmentByDoDateMap; //key = date, value = assignment
    private ServerProxy proxy = new ServerProxy();

    private SingletonModel() {
        currUserID = "ntgID"; //FIXME

        userClassList = new ArrayList<>();
        userClassMap = new TreeMap<>();

        colorList = new ArrayList<>();
        colorList.add("red");
        colorList.add("orange");
        colorList.add("green");
        colorList.add("blue");
        colorList.add("purple");

        colorMap = new TreeMap<>();
        colorMap.put("red", "#ce0404");
        colorMap.put("orange", "#F7A92A");
        colorMap.put("green", "#1f7013");
        colorMap.put("blue", "#1325c6");
        colorMap.put("purple", "#8638CA");

        currClassID = null;
        editingClass = false;

        assignmentByDueDateMap = new TreeMap<>();
        assignmentByDoDateMap = new TreeMap<>();
    }

    public static SingletonModel getInstance( ) {
        return singleton;
    }

    public static SingletonModel getSingleton() {
        return singleton;
    }

    public static void setSingleton(SingletonModel singleton) {
        SingletonModel.singleton = singleton;
    }

    public String getCurrUserID() {
        return currUserID;
    }

    public void setCurrUserID(String currUserID) {
        this.currUserID = currUserID;
    }

    public List<UserClass> getUserClassList() {
        //Call class dao
        userClassList.clear();
        userClassList = new ArrayList<>();
        userClassList = proxy.getClasses(currUserID);

        if (userClassList == null) {
            Log.w("SingletonModel", "userClassList was null from classDAO");
            userClassList = new ArrayList<>();
        }

        for (UserClass uc : userClassList) { //add assignments to class objects
            List<Assignment> ucAssignments = proxy.getAssignments(uc.getUniqueID());
            for (Assignment currAssignment : ucAssignments) { //make sure each assignment has the class in it
                if (currAssignment.getUserClass() == null) {
                    currAssignment.setUserClass(uc);
                }
            }
            uc.setAssignments(ucAssignments);
        }

        return userClassList;
    }

    public Map<String, UserClass> getUserClassMap() {
        getUserClassList(); //will make sure the userClassList is up to date
        userClassMap.clear();
        userClassMap = new HashMap<>();
        for (UserClass uc : userClassList) {
            userClassMap.put(uc.getUniqueID(), uc);
        }
        return userClassMap;
    }

    public List<String> getColorList() {
        return colorList;
    }

    public Map<String, String> getColorMap() {
        return colorMap;
    }

    public void addClass(UserClass userClass) {
        //Call class dao (to access class table and studentclass table
        proxy.addClass(userClass.getUniqueID(), userClass.getClassName(), userClass.getColorString(), currUserID);
//        userClassList.add(userClass);
//        userClassMap.put(userClass.getUniqueID(), userClass);
    }

    public void updateClass(String className, String colorStr) {
        proxy.updateClass(currClassID, className, colorStr, currUserID);
    }

    public boolean deleteClass(String classID) {
        return proxy.deleteClass(classID);
    }

    public String getCurrClassID() {
        return currClassID;
    }

    public void setCurrClassID(String currClassID) {
        this.currClassID = currClassID;
    }

    public boolean isEditingClass() {
        return editingClass;
    }

    public void setEditingClass(boolean editingClass) {
        this.editingClass = editingClass;
    }

    public Map<String, List<Assignment>> getAssignmentByDueDateMap() {
        updateAssignmentByDueDateMap();
        return assignmentByDueDateMap;
    }

    public Map<String, List<Assignment>> getAssignmentByDoDateMap() {
        updateAssignmentByDoDateMap();
        return assignmentByDoDateMap;
    }

    public void updateAssignmentByDueDateMap() {
        assignmentByDueDateMap.clear();
        assignmentByDueDateMap = new TreeMap<>();
        getUserClassList(); //makes sure the userClassList is up to date
        for (UserClass userClass : userClassList) {
            for (Assignment assignment : userClass.getAssignments()) {
                if (assignmentByDueDateMap.get(assignment.getDueDate()) == null) {
                    List<Assignment> list = new ArrayList<>();
                    list.add(assignment);
                    assignmentByDueDateMap.put(assignment.getDueDate(), list);
                }
                else {
                    assignmentByDueDateMap.get(assignment.getDueDate()).add(assignment);
                }
                Log.w("SingletonModel", "Size of assignmentByDueDateMap = " + assignmentByDueDateMap.size());
            }
        }
    }

    public void updateAssignmentByDoDateMap() {
        assignmentByDoDateMap.clear();
        assignmentByDoDateMap = new TreeMap<>();
        getUserClassList(); //makes sure the userClassList is up to date
        for (UserClass userClass : userClassList) {
            for (Assignment assignment : userClass.getAssignments()) {
                if (assignmentByDoDateMap.get(assignment.getDoDate()) == null) {
                    List<Assignment> list = new ArrayList<>();
                    list.add(assignment);
                    assignmentByDoDateMap.put(assignment.getDoDate(), list);
                }
                else {
                    assignmentByDoDateMap.get(assignment.getDoDate()).add(assignment);
                }
                Log.w("SingletonModel", "Size of assignmentByDoDateMap = " + assignmentByDoDateMap.size());
            }
        }
    }
}
