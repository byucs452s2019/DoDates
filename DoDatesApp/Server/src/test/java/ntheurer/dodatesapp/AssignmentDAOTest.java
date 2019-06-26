package ntheurer.dodatesapp;

import org.junit.Test;

import java.util.List;

import model.Assignment;

import DAO.AssignmentDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AssignmentDAOTest {

    @Test
    public void addAssignment() {
        AssignmentDAO assignmentDAO = new AssignmentDAO();

        try{
            assignmentDAO.addAssignment("aID", "Final", "cID", "6/19/2019", "6/18/2019");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void getAssignments(){
        AssignmentDAO assignmentDAO = new AssignmentDAO();

        try{
//            classDAO.addClass("cID", "CS452", "blue", "ntgID");
//            classDAO.addClass("cID2", "CS142", "green", "ntgID");
            List<Assignment> myAssignments = assignmentDAO.getAssignments("cID");
            System.out.println(myAssignments.size());
            for (Assignment a : myAssignments) {
                System.out.println(a.getAssignmentID() + ": " + a.getAssignmentName());
            }

            int expectedNumAssignments = 0;
            assertEquals(expectedNumAssignments, myAssignments.size());

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void removeAssignments() {
        AssignmentDAO assignmentDAO = new AssignmentDAO();

        try {
            assignmentDAO.removeAssignment("aID", "classThatDoesntExist");
            assignmentDAO.removeAssignment("aID", "cID");

            int expectedNumAssignments = 0;
            assertEquals(expectedNumAssignments, assignmentDAO.getAssignments("cID").size());
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}