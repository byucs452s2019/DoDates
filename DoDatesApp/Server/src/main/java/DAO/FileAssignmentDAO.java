package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Assignment;
import model.UserClass;

public class FileAssignmentDAO {
    private File assignmentDir;

    public FileAssignmentDAO(){
        assignmentDir = new File("assignments");
        assignmentDir.mkdir();
    }

    protected void write(String data, String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(data);
        }
    }

    protected String readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append('\n');
        }
// delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        return content;
    }

    public boolean addAssignment(String assignmentID, String assignmentName, String classID, String dueDate, String doDate){
        String data = assignmentID + "\n" + assignmentName + "\n" + classID + "\n" + dueDate + "\n" + doDate;
        try{
            write(data, assignmentDir + File.separator + assignmentName);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Assignment> getAssignments(String classID){
        File folder = new File("\\DoDates CS452\\DoDates\\DoDatesApp\\Server\\assignments");
        File[] listOfFiles = folder.listFiles();
        List<Assignment> assignments = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                try{
                    String tempClass[] = readFile(file.toString()).split("\\r?\\n");
                    Assignment myAssignment = new Assignment(tempClass[0], tempClass[1], tempClass[3], tempClass[4]);
                    if(tempClass[2].equals(classID)){
                        assignments.add(myAssignment);
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }

            }
        }
        return assignments;
    }


}
