package ntheurer.dodatesapp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import DAO.AssignmentDAO;
import DAO.FileAssignmentDAO;
import model.Assignment;

import static org.junit.Assert.*;

public class FileAssignmentDAOTest {

    @Test
    public void addAssignment() {
        String assignmentID = "aID";
        String assignmentName = "Final";
        String classID = "cID";
        String dueDate = "6/19/2019";
        String doDate = "6/18/2019";

        String assignmentID2 = "aID2";
        String assignmentName2 = "project";
        String classID2 = "cID2";
        String dueDate2 = "2/19/2019";
        String doDate2 = "2/18/2019";

        FileAssignmentDAO fileAssignmentDAO = new FileAssignmentDAO();

        try{
            fileAssignmentDAO.addAssignment(assignmentID, assignmentName, classID, dueDate, doDate);
            fileAssignmentDAO.addAssignment(assignmentID2, assignmentName2, classID2, dueDate2, doDate2);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getAssignments(){
        FileAssignmentDAO fileAssignmentDAO = new FileAssignmentDAO();

        try{
            List<Assignment> assignments = new ArrayList<>();
            assignments = fileAssignmentDAO.getAssignments("cID2");
            for (Assignment a : assignments) {
                System.out.println(a.getAssignmentName());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}