package ntheurer.dodatesapp.model;

import android.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SingletonModel {
    private static SingletonModel singleton = new SingletonModel();
    private List<UserClass> userClassList;
    private Map<String, UserClass> userClassMap; //key = id(string), value = userClass
    private List<String> colorList;
    private Map<String, String> colorMap;
    private String currClassID;
    private Map<String, List<Assignment>> assignmentByDueDateMap; //key = date, value = assignment
    private Map<String, List<Assignment>> assignmentByDoDateMap; //key = date, value = assignment

    private SingletonModel() {
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

    public List<UserClass> getUserClassList() {
        return userClassList;
    }

    public Map<String, UserClass> getUserClassMap() {
        return userClassMap;
    }

    public List<String> getColorList() {
        return colorList;
    }

    public Map<String, String> getColorMap() {
        return colorMap;
    }

    public void addClass(UserClass userClass) {
        userClassList.add(userClass);
        userClassMap.put(userClass.getUniqueID(), userClass);
    }

    public String getCurrClassID() {
        return currClassID;
    }

    public void setCurrClassID(String currClassID) {
        this.currClassID = currClassID;
    }

    public Map<String, List<Assignment>> getAssignmentByDueDateMap() {
        return assignmentByDueDateMap;
    }

    public Map<String, List<Assignment>> getAssignmentByDoDateMap() {
        return assignmentByDoDateMap;
    }

    public void updateAssignmentByDueDateMap() {
        assignmentByDueDateMap.clear();
        assignmentByDueDateMap = new TreeMap<>();
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
                Log.w("SingleTonModel", "Size of assignmentByDueDateMap = " + assignmentByDueDateMap.size());
            }
        }
    }

    public void updateAssignmentByDoDateMap() {
        assignmentByDoDateMap.clear();
        assignmentByDoDateMap = new TreeMap<>();
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
                Log.w("SingleTonModel", "Size of assignmentByDoDateMap = " + assignmentByDoDateMap.size());
            }
        }
    }
}
