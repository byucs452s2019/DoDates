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
    private Map<String, List<Assignment>> assignmentByDateMap; //key = date, value = assignment

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

        assignmentByDateMap = new TreeMap<>();
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

    public Map<String, List<Assignment>> getAssignmentByDateMap() {
        return assignmentByDateMap;
    }

    public void updateAssignmentByDateMap() {
        assignmentByDateMap.clear();
        assignmentByDateMap = new TreeMap<>();
        for (UserClass userClass : userClassList) {
            for (Assignment assignment : userClass.getAssignments()) {
                if (assignmentByDateMap.get(assignment.getDueDate()) == null) {
                    List<Assignment> list = new ArrayList<>();
                    list.add(assignment);
                    assignmentByDateMap.put(assignment.getDueDate(), list);
                }
                else {
                    assignmentByDateMap.get(assignment.getDueDate()).add(assignment);
                }
                Log.w("SingleTonModel", "Size of assignmentByDateMap = " + assignmentByDateMap.size());
            }
        }
    }
}
